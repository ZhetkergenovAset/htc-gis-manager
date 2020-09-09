package com.dilau.server.service.daoservice;


import com.dilau.server.domain.Building;

import java.util.List;

public interface BuildingsService {
    Building saveBuilding(Building department);

    Building getBuildingsByPostcode(String postcode);

    List<Building> getBuildingsByRka(String rka);

    List<Building> getAllBuildings();

    List<Building> getAllForCoordinate();

    Building updateBuildings(Building building);

}
