package com.dilau.server.repository;

import com.dilau.server.domain.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, Long> {
    Street findByIdKazpost(String id);
}
