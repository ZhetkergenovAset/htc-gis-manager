package com.dilau.server.service.httpservice;


import com.dilau.server.excetion.ConnectionWithRestApiException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class HttpUrlRestConnectionImpl implements HttpUrlRestConnection {

    public HttpURLConnection getHttpConnect(String urlAddress) throws ConnectionWithRestApiException {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlAddress).openConnection();
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.connect();
            return connection;
        } catch (IOException e) {
            throw new ConnectionWithRestApiException();
        }
    }


}
