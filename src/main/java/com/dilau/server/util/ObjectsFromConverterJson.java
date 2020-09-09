package com.dilau.server.util;


import com.dilau.server.domain.Objects;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;


public class ObjectsFromConverterJson {

    public static Objects getObjectFromJson(JsonObject jsonObject) {
        ObjectMapper mapper = new ObjectMapper();
        Objects object = null;
        try {
            object = mapper.readValue(jsonObject.toString(), Objects.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return object;
    }
}
