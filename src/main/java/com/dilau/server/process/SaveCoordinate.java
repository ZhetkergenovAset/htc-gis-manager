package com.dilau.server.process;

import com.dilau.server.domain.Building;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SaveCoordinate {

    public static Building getCoordinate(String json, Building building) throws IllegalStateException {
        JsonElement jsonElement = JsonParser.parseString(json);
        try {
            JsonElement response = jsonElement.getAsJsonObject().get("response");
            JsonElement geoObjectCollection = response.getAsJsonObject().get("GeoObjectCollection");
            JsonArray featureMember = geoObjectCollection.getAsJsonObject().get("featureMember").getAsJsonArray();
            if (featureMember.size() > 0) {
                JsonObject asJsonObject = featureMember.get(0).getAsJsonObject();
                JsonObject geoObject = asJsonObject.get("GeoObject").getAsJsonObject();
                JsonObject geocoderMetaData = geoObject.getAsJsonObject("metaDataProperty").get("GeocoderMetaData").getAsJsonObject();
                String precision = geocoderMetaData.get("precision").getAsString();
                if (precision.equals("exact")) {
                    String pos = geoObject.get("Point").getAsJsonObject().get("pos").toString();
                    pos = pos.substring(1, pos.length() - 1);
                    String[] s = pos.split(" ");
                    building.setLonglat(s[0]);
                    building.setWidth(s[1]);
                    return building;
                }
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Возможно все 25000 разрешенных запросов изтрачены");
        }
        building.setVerified("incorrect");
        return building;
    }

}
