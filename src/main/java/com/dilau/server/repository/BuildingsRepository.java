package com.dilau.server.repository;

import com.dilau.server.domain.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildingsRepository extends JpaRepository<Building, Long> {
    Building findByPostCode(String postcode);

    List<Building> findAllByRka(String rka);

    List<Building> findAllByPostCode(String postcode);

    List<Building> findTop1000ByLonglatIsNullAndVerifiedIsNull();
}