package com.dilau.server.util;

import com.dilau.server.domain.Objects;
import com.dilau.server.domain.Street;
import com.dilau.server.service.daoservice.ObjectsService;

public class ObjectStreetConverter {
    private static ObjectsService objectsService;

    public ObjectStreetConverter(ObjectsService objectsService) {
        this.objectsService = objectsService;
    }

    public Street getStreetFromObject(Objects objects) {
        Street street = new Street();
        street.setIdKazpost(objects.getIdKazpost());
        street.setNameKaz(objects.getNameKaz());
        street.setNameRus(objects.getNameRus());
        Objects objectById = objectsService.getObjectById(objects.getParentId());
        street.setObject(objectById);
        street.setType(objects.getType());
        street.setTotal(objects.getTotal());
        return street;
    }
}
