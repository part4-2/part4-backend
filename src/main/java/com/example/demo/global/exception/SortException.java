package com.example.demo.global.exception;

public class SortException extends RuntimeException {
    public static class SortConditionNotFoundException extends DateTimeCustomException {

        public SortConditionNotFoundException() {
            super("검색 조건 값이 없습니다.");
        }
    }
}
