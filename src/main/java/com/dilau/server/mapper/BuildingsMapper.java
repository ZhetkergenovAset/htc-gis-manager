package com.dilau.server.mapper;

import com.dilau.server.domain.Building;
import com.dilau.server.domain.HistoryBuildings;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingsMapper {
    Building toBuildings(HistoryBuildings historyBuildings);

    HistoryBuildings toHistoryBuildyng(Building building);
}
