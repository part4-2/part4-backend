package com.example.demo.aop;

import com.amazonaws.HttpMethod;
import com.example.demo.global.aop.UriInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UriInfoTest {
    @ParameterizedTest
    @ValueSource(strings = {"qwtwqtt", "124", "qwtiqwtpqtqpowtqwt", "1"})
    void getInstance_pathVariable(String uriValue) {
        String uri = "/api/main/spots/{spot-id}/any/value";
        UriInfo actual = UriInfo.getInstance(uri, HttpMethod.GET);
        String actualUri = "/api/main/spots/" + uriValue + "/any/value";
        UriInfo expected = UriInfo.getInstance(actualUri, HttpMethod.GET);
        assertEquals(expected, actual);
    }

    @Test
    void getInstance() {
        UriInfo actual = UriInfo.SPOT_SAVE;
        UriInfo expected = UriInfo.getInstance(actual.getUri(), actual.getHttpMethod());
        assertEquals(expected, actual);
    }

    @Test
    void name() {
        String get = "GET";
        HttpMethod httpMethod = HttpMethod.valueOf(get);
        assertEquals(HttpMethod.GET, httpMethod);
    }
}