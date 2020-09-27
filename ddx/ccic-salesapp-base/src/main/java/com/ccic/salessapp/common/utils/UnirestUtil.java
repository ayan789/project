package com.ccic.salessapp.common.utils;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.jackson.JacksonObjectMapper;
import io.micrometer.core.ipc.http.HttpSender.Response;

public class UnirestUtil {

    public static Response postForResponse(String url, Object request) {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        Response response = Unirest
                .post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(request)
                .asObject(Response.class)
                .getBody();
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

    public static String postForString(String url, Object request) {
        String response = Unirest
                .post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(request)
                .asString()
                .getBody();
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

    public static JsonNode postForJson(String url, Object request) {
        HttpResponse<JsonNode> response = Unirest
                .post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(request)
                .asJson();
        if (response != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public static Response getForResponse(String url) {
        Unirest.config().setObjectMapper(new JacksonObjectMapper());
        Response response = Unirest
                .get(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asObject(Response.class)
                .getBody();
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

    public static JsonNode getForJson(String url) {
        HttpResponse<JsonNode> response = Unirest
                .get(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
        if (response != null) {
            return response.getBody();
        } else {
            return null;
        }
    }

    public static String getForString(String url) {
        String response = Unirest
                .get(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .asString()
                .getBody();
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }
}
