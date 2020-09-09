package com.dilau.server.service.daoservice;


import com.dilau.server.domain.Street;

public interface StreetService {
    Street saveStreet(Street street);

    Street getStreetByIdKazpost(String id);
}
