package com.dilau.server.mapper;

import com.dilau.server.domain.HistoryObjects;
import com.dilau.server.domain.Objects;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ObjectsMapper {
    Objects toObjects(HistoryObjects historyObjects);

    HistoryObjects toHistoryObjects(Objects objects);

}
