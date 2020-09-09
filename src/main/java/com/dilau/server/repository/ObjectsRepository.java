package com.dilau.server.repository;

import com.dilau.server.domain.Objects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectsRepository extends JpaRepository<Objects, Long> {
    Objects findByIdKazpost(String id);
}
