package com.dilau.server.service.daoservice;

import com.dilau.server.domain.Building;
import com.dilau.server.domain.HistoryBuildings;
import com.dilau.server.mapper.BuildingsMapper;
import com.dilau.server.repository.BuildingsRepository;
import com.dilau.server.repository.HistoryBuildingsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@Service
@Transactional
public class BuildingsServiceImpl implements BuildingsService {
    private final BuildingsRepository buildingsRepository;
    private final HistoryBuildingsRepository historyBuildingsRepository;
    private final BuildingsMapper buildingsMapper;

    public BuildingsServiceImpl(BuildingsRepository buildingsRepository, HistoryBuildingsRepository historyBuildingsRepository, BuildingsMapper buildingsMapper) {
        this.buildingsRepository = buildingsRepository;
        this.historyBuildingsRepository = historyBuildingsRepository;
        this.buildingsMapper = buildingsMapper;
    }

    @Override
    public Building saveBuilding(Building building) {
        List<Building> buildingList = buildingsRepository.findAllByPostCode(building.getPostCode());
        building.setDateUpdate(LocalDate.now());
        if (buildingList.size() == 0) {
            return buildingsRepository.save(building);
        }
        for (Building buildingDB : buildingList) {
            if (!building.equals(buildingDB)) {
                List<Building> byRka = buildingsRepository.findAllByRka(building.getRka());
                if (byRka.size() == 0) {
                    return buildingsRepository.save(building);
                }
                HistoryBuildings historyBuildings = buildingsMapper.toHistoryBuildyng(buildingDB);
                historyBuildings.setId(0);
                historyBuildingsRepository.save(historyBuildings);
                building.setId(buildingDB.getId());
                return buildingsRepository.save(building);
            }
        }
        return buildingList.get(0);
    }

    @Override
    public Building getBuildingsByPostcode(String postcode) {
        return buildingsRepository.findByPostCode(postcode);
    }

    @Override
    public List<Building> getBuildingsByRka(String rka) {
        return buildingsRepository.findAllByRka(rka);
    }

    @Override
    public List<Building> getAllBuildings() {
        return buildingsRepository.findAll();
    }

    @Override
    public List<Building> getAllForCoordinate() {
        return buildingsRepository.findTop1000ByLonglatIsNullAndVerifiedIsNull();
    }

    @Override
    public Building updateBuildings(Building building) {
        return buildingsRepository.save(building);
    }

}
