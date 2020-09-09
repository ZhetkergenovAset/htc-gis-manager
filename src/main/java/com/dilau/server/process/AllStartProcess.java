package com.dilau.server.process;

import com.dilau.server.config.Constants;
import com.dilau.server.domain.Objects;
import com.dilau.server.domain.Type;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.service.daoservice.TypeService;
import com.dilau.server.util.DataProcessingResponse;
import com.dilau.server.service.httpservice.HttpUrlRestConnection;
import com.dilau.server.util.ObjectsFromConverterJson;
import com.dilau.server.util.TypeFromJsonConverter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

@Component
public class AllStartProcess {
    private TypeService typeService;
    private ProcessSaveObjects process;
    private HttpUrlRestConnection httpUrlRestConnection;


    public AllStartProcess(TypeService typeService, ProcessSaveObjects process, HttpUrlRestConnection httpUrlRestConnection) {
        this.typeService = typeService;
        this.process = process;
        this.httpUrlRestConnection = httpUrlRestConnection;
    }


    public void processStartSave(String id, int parameter) throws ConnectionWithRestApiException {
        try {
            String query = Constants.GET_OBJECT_URL + id + Constants.PARAM_NAME + parameter;
            String json = DataProcessingResponse.getJsonFromRestApi(httpUrlRestConnection.getHttpConnect(query));
            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            int total = jsonObject.get("total").getAsInt();
            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
            int size = jsonArray.size();
            for (int i = 0; i < size; i++) {
                JsonElement dataJsonElement = jsonArray.get(i);
                JsonObject jsonObjectIter = dataJsonElement.getAsJsonObject();
                Type type = TypeFromJsonConverter.getFromJsonType(jsonObjectIter.getAsJsonObject("type"));
                Objects objects = ObjectsFromConverterJson.getObjectFromJson(jsonObjectIter);
                type = typeService.saveType(type);
                objects.setType(type);
                objects.setTotal(total);
                process.saveObjectsAndStreets(objects, 0);
            }
            ProcessSaveBuildings.count=0;
        } catch (ConnectionWithRestApiException e) {
            throw new ConnectionWithRestApiException("Проблемы с соединения с сервисом kazpost");
        }
    }
}


