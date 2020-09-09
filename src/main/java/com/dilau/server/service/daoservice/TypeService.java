package com.dilau.server.service.daoservice;


import com.dilau.server.domain.Type;

import java.util.Map;
import java.util.Optional;

public interface TypeService {
    Type saveType(Type type);

    Type getTypeById(String id);
}
