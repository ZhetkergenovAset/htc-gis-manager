package com.dilau.server.service.daoservice;

import com.dilau.server.domain.HistoryObjects;
import com.dilau.server.domain.Objects;
import com.dilau.server.mapper.ObjectsMapper;
import com.dilau.server.repository.HistoryObjectsRepository;
import com.dilau.server.repository.ObjectsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class ObjectsServiceImpl implements ObjectsService {
    private final ObjectsRepository objectsRepository;
    private final HistoryObjectsRepository historyObjectsRepository;
    private final ObjectsMapper objectsMapper;

    public ObjectsServiceImpl(ObjectsRepository objectsRepository, HistoryObjectsRepository historyObjectsRepository, ObjectsMapper objectsMapper) {
        this.objectsRepository = objectsRepository;
        this.historyObjectsRepository = historyObjectsRepository;
        this.objectsMapper = objectsMapper;
    }

    @Override
    public Objects saveObject(Objects objects) {
        Objects objectsDB = objectsRepository.findByIdKazpost(objects.getIdKazpost());
        objects.setDateUpdate(LocalDate.now());
        if (objectsDB == null) {
            return objectsRepository.save(objects);
        }
        if (!objectsDB.equals(objects)) {
            HistoryObjects historyObjects = objectsMapper.toHistoryObjects(objectsDB);
            historyObjects.setId(0);
            historyObjectsRepository.save(historyObjects);
            objects.setId(objectsDB.getId());
            return objectsRepository.save(objects);
        }
        return objectsDB;
    }

    @Override
    public Objects getObjectById(String id) {
        return objectsRepository.findByIdKazpost(id);
    }
}
