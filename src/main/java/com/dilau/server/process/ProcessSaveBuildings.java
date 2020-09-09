package com.dilau.server.process;

import com.dilau.server.config.Constants;
import com.dilau.server.domain.Building;
import com.dilau.server.domain.Street;
import com.dilau.server.domain.Type;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.service.daoservice.BuildingsService;
import com.dilau.server.service.daoservice.StreetService;
import com.dilau.server.service.daoservice.TypeService;
import com.dilau.server.util.DataProcessingResponse;
import com.dilau.server.service.httpservice.HttpUrlRestConnection;
import com.dilau.server.util.BuildingFromJson;
import com.dilau.server.util.TypeFromJsonConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class ProcessSaveBuildings {
    public static int count = 0;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constants.FORMAT_DATETIME);
    private final StreetService streetService;
    private final TypeService typeService;
    private final BuildingsService buildingsService;
    private final HttpUrlRestConnection httpUrlRestConnection;

    public ProcessSaveBuildings(StreetService streetService, TypeService typeService, BuildingsService buildingsService, HttpUrlRestConnection httpUrlRestConnection) {
        this.streetService = streetService;
        this.typeService = typeService;
        this.buildingsService = buildingsService;
        this.httpUrlRestConnection = httpUrlRestConnection;
    }

    public void saveAllBuildings(Street street, int parameter) throws ConnectionWithRestApiException {
        String query = Constants.GET_BUILDINGS_URL + street.getIdKazpost() + Constants.PARAM_NAME + parameter;
        try {
            String json = DataProcessingResponse.getJsonFromRestApi(httpUrlRestConnection.getHttpConnect(query));
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            int total = jsonObject.get("total").getAsInt();
            if (total != 0) {
                streetService.saveStreet(street);
                if (jsonObject.get("data").isJsonArray()) {
                    JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
                    int size = jsonArray.size();
                    for (int i = 0; i < size; i++) {
                        JsonElement dataJson = jsonArray.get(i);
                        JsonObject buildingJson = dataJson.getAsJsonObject();
                        Type type = TypeFromJsonConverter.getFromJsonType(buildingJson.get("type").getAsJsonObject());
                        type = typeService.saveType(type);
                        Building building = BuildingFromJson.getDepartmentFromJson(buildingJson);
                        building.setStreet(street);
                        building.setType(type);
                        buildingsService.saveBuilding(building);
                        count++;
                        if ((count / 1000) * 1000 == count) {
                            System.out.println("Обработано записей= " + count + " время:" + dtf.format(LocalDateTime.now()));
                        }
                        if (i + 1 == size) {
                            parameter += i + 1;
                            if (parameter == total) {
                                return;
                            } else {
                                saveAllBuildings(street, parameter);
                            }
                        }
                    }

                }
            }
        } catch (ConnectionWithRestApiException e) {
            throw new ConnectionWithRestApiException("Проблемы с соединения с сервисом kazpost");
        }
    }

}
