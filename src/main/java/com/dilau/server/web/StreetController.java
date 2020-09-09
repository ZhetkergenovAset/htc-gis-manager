package com.dilau.server.web;

import com.dilau.server.config.Constants;
import com.dilau.server.domain.Street;
import com.dilau.server.service.daoservice.StreetService;
import com.dilau.server.web.dto.*;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.process.ProcessSaveBuildings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(Constants.REST_ENDPOINT)
public class StreetController {
    @Autowired
    private StreetService streetService;
    @Autowired
    private ProcessSaveBuildings processSaveBuildings;

    @PostMapping(value = "street", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> startStreetUpdate(@RequestBody ParameterDto parameterDto)throws ResponseStatusException {
        Street streetByIdKazpost = streetService.getStreetByIdKazpost(parameterDto.getId());
        try {
            processSaveBuildings.saveAllBuildings(streetByIdKazpost, parameterDto.getParam());
        } catch (ConnectionWithRestApiException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return ResponseEntity.ok(new ResultDto("Улица обновлена"));
    }
}
