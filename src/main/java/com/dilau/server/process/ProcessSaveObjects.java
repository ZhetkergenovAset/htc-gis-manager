package com.dilau.server.process;

import com.dilau.server.config.Constants;
import com.dilau.server.domain.Objects;
import com.dilau.server.domain.Street;
import com.dilau.server.domain.Type;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.service.daoservice.ObjectsService;
import com.dilau.server.service.daoservice.StreetService;
import com.dilau.server.service.daoservice.TypeService;
import com.dilau.server.util.DataProcessingResponse;
import com.dilau.server.service.httpservice.HttpUrlRestConnection;
import com.dilau.server.util.ObjectStreetConverter;
import com.dilau.server.util.ObjectsFromConverterJson;
import com.dilau.server.util.TypeFromJsonConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessSaveObjects {
    private static List<Street> streetList = new ArrayList<>();
    private final TypeService typeService;
    private final ObjectsService objectService;
    private final StreetService streetService;
    private final ProcessSaveBuildings processSaveBuildings;
    private final HttpUrlRestConnection httpUrlRestConnection;

    public ProcessSaveObjects(TypeService typeService, ObjectsService objectService, StreetService streetService, ProcessSaveBuildings processSaveBuildings, HttpUrlRestConnection httpUrlRestConnection) {
        this.processSaveBuildings = processSaveBuildings;
        this.typeService = typeService;
        this.objectService = objectService;
        this.streetService = streetService;
        this.httpUrlRestConnection = httpUrlRestConnection;
    }

    public void saveObjectsAndStreets(Object object, int parameter) throws ConnectionWithRestApiException {
        Objects objects = null;
        if (object instanceof Objects) {
            objects = (Objects) object;
        }
        String query = Constants.GET_OBJECT_URL + objects.getIdKazpost() + Constants.PARAM_NAME + parameter;
        try {
            String json = DataProcessingResponse.getJsonFromRestApi(httpUrlRestConnection.getHttpConnect(query));
            try {
                JsonElement jsonElement = JsonParser.parseString(json);
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                int total = jsonObject.get("total").getAsInt();
                JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                int size = jsonArray.size();
                if (total == 0) {
                    ObjectStreetConverter converter = new ObjectStreetConverter(objectService);
                    Street street = converter.getStreetFromObject((Objects) object);
                    street = streetService.saveStreet(street);
                    streetList.add(street);
                } else {
                    objectService.saveObject(objects);
                    for (int i = 0; i < size; i++) {
                        JsonObject jsonObjectIter = jsonArray.get(i).getAsJsonObject();
                        Type type = TypeFromJsonConverter.getFromJsonType(jsonObjectIter.getAsJsonObject("type"));
                        type = typeService.saveType(type);
                        Objects objectIn = ObjectsFromConverterJson.getObjectFromJson(jsonObjectIter);
                        objectIn.setType(type);
                        objects.setTotal(total);
                        saveObjectsAndStreets(objectIn, 0);
                        if (i + 1 == size) {
                            parameter += i + 1;
                            if (parameter == total) {
                                for (Street street : streetList) {
                                    processSaveBuildings.saveAllBuildings(street, 0);
                                }
                                streetList = new ArrayList<>();
                                return;
                            } else {
                                saveObjectsAndStreets(objects, parameter);
                            }
                        }

                    }
                }
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        } catch (ConnectionWithRestApiException e) {
            throw new ConnectionWithRestApiException("Проблемы с соединения с сервисом kazpost");
        }

    }

}
