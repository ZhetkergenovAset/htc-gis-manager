package com.dilau.server.service.daoservice.history;

import com.dilau.server.repository.HistoryBuildingsRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryBuildyngsServiceImpl implements HistoryBuildyngsService {
    private HistoryBuildingsRepository historyBuildingsRepository;

    public HistoryBuildyngsServiceImpl(HistoryBuildingsRepository historyBuildingsRepository) {
        this.historyBuildingsRepository = historyBuildingsRepository;
    }
}
