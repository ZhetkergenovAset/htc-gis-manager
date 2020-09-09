package com.dilau.server.util;

import com.dilau.server.domain.Building;
import com.dilau.server.domain.Objects;
import com.dilau.server.domain.Street;
import com.dilau.server.service.daoservice.ObjectsService;
import org.springframework.stereotype.Component;

@Component
public class BuildHostForYandex {
    private final ObjectsService objectsService;

    public BuildHostForYandex(ObjectsService objectsService) {
        this.objectsService = objectsService;
    }

    public String createUrlFromBuildyng(Building building) {
        StringBuilder sb = new StringBuilder();
        sb = findNameStreetForHost(building.getStreet(), sb);
        sb.append(building.getType().getNameRus().toLowerCase() + " " + building.getNumber() + ",");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private StringBuilder findNameStreetForHost(Street street, StringBuilder sb) {
        sb = findNameObjectsForHost(street.getObject(), sb);
        sb.append(street.getType().getNameRus().toLowerCase() + " " + street.getNameRus() + ",");
        return sb;
    }

    private StringBuilder findNameObjectsForHost(Objects object, StringBuilder sb) {
        Objects objectDB = objectsService.getObjectById(object.getParentId());
        if (objectDB != null) {
            findNameObjectsForHost(objectDB, sb);
        }
        sb.append(object.getType().getNameRus().toLowerCase() + " " + object.getNameRus() + ",");
        return sb;
    }
}
