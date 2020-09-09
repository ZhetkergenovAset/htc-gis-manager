package com.dilau.server.service.daoservice.history;

import com.dilau.server.repository.HistoryStreetRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryStreetServiceImpl implements HistoryStreetService {
    private HistoryStreetRepository historyStreetRepository;

    public HistoryStreetServiceImpl(HistoryStreetRepository historyStreetRepository) {
        this.historyStreetRepository = historyStreetRepository;
    }
}
