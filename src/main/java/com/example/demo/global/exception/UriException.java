package com.example.demo.global.exception;

public class UriException extends RuntimeException{
    public UriException(final String message) {
        super(message);
    }

    public static class UriNotFoundException extends UriException {

        public UriNotFoundException(String requestUri) {
            super(String.format("여행지 정보가 존재하지 않습니다. - request info { requestUri : %s }", requestUri));
        }
    }
}
