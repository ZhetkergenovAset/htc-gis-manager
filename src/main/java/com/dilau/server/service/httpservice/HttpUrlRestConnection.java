package com.dilau.server.service.httpservice;

import com.dilau.server.excetion.ConnectionWithRestApiException;

import java.net.HttpURLConnection;

public interface HttpUrlRestConnection {

    HttpURLConnection getHttpConnect(String urlAddress) throws ConnectionWithRestApiException, ConnectionWithRestApiException;
}
