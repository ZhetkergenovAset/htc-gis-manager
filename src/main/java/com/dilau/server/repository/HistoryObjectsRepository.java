package com.dilau.server.repository;

import com.dilau.server.domain.HistoryObjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryObjectsRepository extends JpaRepository<HistoryObjects, Long> {
}
