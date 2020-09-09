package com.dilau.server.service.daoservice;


import com.dilau.server.domain.Objects;

import java.util.Collection;

public interface ObjectsService {
    Objects saveObject(Objects objects);
    Objects getObjectById(String id);
}
