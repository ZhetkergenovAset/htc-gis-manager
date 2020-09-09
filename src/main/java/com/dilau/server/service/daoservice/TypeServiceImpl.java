package com.dilau.server.service.daoservice;

import com.dilau.server.domain.Type;
import com.dilau.server.repository.TypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public Type saveType(Type type) {
        Optional<Type> types = typeRepository.findByIdKazpost(type.getIdKazpost());
        Type typeDB = types.orElse(null);
        if (typeDB == null) {
            typeDB = typeRepository.save(type);
        }
        return typeDB;
    }

    @Override
    public Type getTypeById(String id) {

        return typeRepository.findByIdKazpost(id).get();
    }
}
