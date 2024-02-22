package com.example.demo.global.exception;

public class DateTimeCustomException extends RuntimeException {

    public DateTimeCustomException(String message) {
        super(message);
    }

    public static class InvalidFormatOfDateCustomException extends DateTimeCustomException {

        public InvalidFormatOfDateCustomException(final String regex, final String input) {
            super(String.format(
                            "날짜 형식이 잘못되었습니다 - {regex : %s, input : %s}",
                            regex,
                            input
                    )
            );
        }
    }
}
