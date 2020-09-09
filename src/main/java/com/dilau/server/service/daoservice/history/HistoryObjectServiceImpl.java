package com.dilau.server.service.daoservice.history;

import com.dilau.server.repository.HistoryObjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryObjectServiceImpl implements HistoryObjectsService {

    private HistoryObjectsRepository historyObjectsRepository;

    public HistoryObjectServiceImpl(HistoryObjectsRepository historyObjectsRepository) {
        this.historyObjectsRepository = historyObjectsRepository;
    }
}
