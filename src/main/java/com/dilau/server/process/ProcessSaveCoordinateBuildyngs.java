package com.dilau.server.process;

import com.dilau.server.config.Constants;
import com.dilau.server.domain.Building;
import com.dilau.server.excetion.ConnectionWithRestApiException;
import com.dilau.server.service.daoservice.BuildingsService;
import com.dilau.server.service.httpservice.HttpUrlRestConnection;
import com.dilau.server.util.BuildHostForYandex;
import com.dilau.server.util.DataProcessingResponse;
import com.dilau.server.util.UrlEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ProcessSaveCoordinateBuildyngs {
    private final static int limit = 25;
    private static int count = 0;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constants.FORMAT_DATETIME);
    private final BuildHostForYandex buildHostForYandex;
    private final BuildingsService buildingsService;
    private final HttpUrlRestConnection httpUrlRestConnection;

    public ProcessSaveCoordinateBuildyngs(BuildHostForYandex buildHostForYandex, BuildingsService buildingsService, HttpUrlRestConnection httpUrlRestConnection) {
        this.buildHostForYandex = buildHostForYandex;
        this.buildingsService = buildingsService;
        this.httpUrlRestConnection = httpUrlRestConnection;
    }

    public void getCoordinate() throws ConnectionWithRestApiException {
        for (int i = 0; i < limit; i++) {
            getCoordinateFromYandex();
        }
        count = 0;
    }

    public void getCoordinateFromYandex() throws ConnectionWithRestApiException {
        List<Building> allForUpdate = buildingsService.getAllForCoordinate();
        for (Building building : allForUpdate) {
            String urlFromBuilding = buildHostForYandex.createUrlFromBuildyng(building);
            String query = Constants.GET_DATA_YANDEX_URL + UrlEncoder.encodeValue(urlFromBuilding);
            try {
                String jsonFromRestApi = DataProcessingResponse.getJsonFromRestApi(httpUrlRestConnection.getHttpConnect(query));
                building = SaveCoordinate.getCoordinate(jsonFromRestApi, building);
                buildingsService.updateBuildings(building);
            } catch (ConnectionWithRestApiException | IllegalStateException e) {
                throw new ConnectionWithRestApiException("Проблемы при соединении с yandex api");
            }
            count++;
            if ((count / 1000) * 1000 == count) {
                System.out.println("Обработано записей= " + count + " время:" + dtf.format(LocalDateTime.now()));
            }

        }
    }


}
