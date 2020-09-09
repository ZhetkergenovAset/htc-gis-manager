package com.dilau.server.mapper;

import com.dilau.server.domain.HistoryStreet;
import com.dilau.server.domain.Street;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StreetMapper {
    Street toStreet(HistoryStreet historyStreet);

    HistoryStreet toHistoryStreet(Street street);
}
