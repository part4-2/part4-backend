package com.example.demo.global.aop;

import com.amazonaws.HttpMethod;
import com.example.demo.global.exception.UriException;
import lombok.Getter;
import org.springframework.web.util.UriTemplate;

import java.util.Arrays;

@Getter
public enum UriInfo {
    SPOT_SAVE("/api/users/spots", "여행지 저장(DB)", HttpMethod.POST),
    SPOT_VIEW("/api/main/spots/{spot-id}", "여행지 조회", HttpMethod.GET),
    VALUE_VIEW("/api/main/spots/{spot-id}/any/value", "테스트용", HttpMethod.GET),
    TEST_LOGIN("/test/tokens", "테스트 로그인", HttpMethod.POST),
    UNDEFINED(null, null, null);
    ;

    private final String uri;
    private final String description;
    private final HttpMethod httpMethod;

    public static UriInfo getInstance(String requestUri, HttpMethod httpMethod) {
        return Arrays.stream(values())
                .filter(uriInfo -> matchesUriTemplate(uriInfo.uri, requestUri) && uriInfo.httpMethod.equals(httpMethod))
                .findAny()
                .orElse(
                        UNDEFINED
                );
    }

    private static boolean matchesUriTemplate(String uriPattern, String requestUri) {
        if (uriPattern == null || requestUri == null) {
            return false;
//            throw new UriException.UriNotFoundException(requestUri);
        }

        UriTemplate uriTemplate = new UriTemplate(uriPattern);

        return uriTemplate.matches(requestUri);
    }

    UriInfo(String uri, String description, HttpMethod httpMethod) {
        this.uri = uri;
        this.description = description;
        this.httpMethod = httpMethod;
    }
}