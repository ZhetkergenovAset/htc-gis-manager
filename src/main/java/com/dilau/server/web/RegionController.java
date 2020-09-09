package com.dilau.server.web;

import com.dilau.server.config.Constants;
import com.dilau.server.web.dto.*;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.process.AllStartProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(Constants.REST_ENDPOINT)
public class RegionController {

    @Autowired
    private AllStartProcess allStartProcess;

    @PostMapping(value = "region", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultDto> startAllUpdate(@RequestBody ParameterDto parameterDto) throws ResponseStatusException{
        try {
            allStartProcess.processStartSave(parameterDto.getId(), parameterDto.getParam());
        } catch (ConnectionWithRestApiException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return ResponseEntity.ok(new ResultDto("Все данные обновлены"));
    }
}
