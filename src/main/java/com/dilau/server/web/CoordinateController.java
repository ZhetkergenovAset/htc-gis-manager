package com.dilau.server.web;

import com.dilau.server.config.Constants;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.process.ProcessSaveCoordinateBuildyngs;
import com.dilau.server.web.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(Constants.REST_ENDPOINT)
public class CoordinateController {
    @Autowired
    private ProcessSaveCoordinateBuildyngs processSaveCoordinateBuildyngs;

    @GetMapping("coordinate")
    public ResponseEntity<ResultDto> startGetCoordinate()throws ResponseStatusException {
        try {
            processSaveCoordinateBuildyngs.getCoordinate();
        } catch (ConnectionWithRestApiException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
        return ResponseEntity.ok(new ResultDto("Данные обновлены"));
    }
}
