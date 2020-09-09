package com.dilau.server.service.daoservice;

import com.dilau.server.domain.HistoryStreet;
import com.dilau.server.domain.Street;
import com.dilau.server.mapper.StreetMapper;
import com.dilau.server.repository.HistoryStreetRepository;
import com.dilau.server.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;
    private final HistoryStreetRepository historyRepository;
    private final StreetMapper streetMapper;

    public StreetServiceImpl(StreetRepository streetRepository, HistoryStreetRepository historyStreetRepository, StreetMapper streetMapper) {
        this.streetRepository = streetRepository;
        this.historyRepository = historyStreetRepository;
        this.streetMapper = streetMapper;
    }

    @Override
    public Street saveStreet(Street street) {
        Street streetDB = streetRepository.findByIdKazpost(street.getIdKazpost());
        street.setDateUpdate(LocalDate.now());
        if (streetDB == null) {
            Street save = streetRepository.save(street);
            return save;
        }
        if (!streetDB.equals(street)) {
            HistoryStreet historyStreet = streetMapper.toHistoryStreet(streetDB);
            historyStreet.setId(0);
            historyRepository.save(historyStreet);
            street.setId(streetDB.getId());
            return streetRepository.save(street);

        }
        return streetDB;
    }

    @Override
    public Street getStreetByIdKazpost(String id) {
        return streetRepository.findByIdKazpost(id);
    }
}
