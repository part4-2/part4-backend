package com.example.demo.star.exception;

public class StarException extends RuntimeException{
    public StarException(String message) {
        super(message);
    }

    public static class StarNotFoundException extends StarException {

        public StarNotFoundException(Long userId, String spotName) {
            super(String.format("별점 정보가 존재하지 않습니다. - request info { userId : %s , spotName : %s }", userId, spotName));
        }

    }

    public static class InvalidStarRankException extends StarException {
        public InvalidStarRankException(Double value) {
            super(String.format("입력된 별점 값이 잘못되었습니다. - request info { value : %s }", value));
        }
    }

    public static class StarRankAlreadyExistsException extends StarException {

        public StarRankAlreadyExistsException(Long userId, String spotName) {
            super(String.format("이미 평점을 남겼습니다. - request info { userId : %s, spotName : %s }", userId, spotName));
        }
    }
}
