package com.dilau.server.util;

import com.dilau.server.domain.Type;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public class TypeFromJsonConverter {

    public static Type getFromJsonType(JsonObject jsonObjectType) {
        Type type = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            type = mapper.readValue(jsonObjectType.toString(), Type.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return type;
    }

}
