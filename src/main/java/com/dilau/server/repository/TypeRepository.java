package com.dilau.server.repository;

import com.dilau.server.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByIdKazpost(String id);
}
