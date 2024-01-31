package com.example.demo.review.exception;

public class WeatherException extends RuntimeException{
    public WeatherException(String message) {
        super(message);
    }

    public static class WeatherNotExists extends WeatherException {

        public WeatherNotExists() {
            super("날씨를 찾을 수 없습니다");
        }
    }
}
