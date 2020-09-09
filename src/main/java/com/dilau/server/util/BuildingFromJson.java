package com.dilau.server.util;

import com.dilau.server.domain.Building;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

public class BuildingFromJson {

    public static Building getDepartmentFromJson(JsonObject jsonObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        Building building = null;
        try {
            building = objectMapper.readValue(jsonObject.toString(), Building.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return building;
    }
}
