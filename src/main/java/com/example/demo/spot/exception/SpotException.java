package com.example.demo.spot.exception;

public class SpotException extends RuntimeException{
    public SpotException(String message) {
        super(message);
    }

    public static class SpotNotFoundException extends SpotException {
        public SpotNotFoundException(Long id) {
            super(String.format("여행지 정보가 존재하지 않습니다. - request info { id : %s }", id));
        }

        public SpotNotFoundException(String formattedAddress){
            super(String.format("여행지 정보가 존재하지 않습니다. - request info { address : %s }", formattedAddress));
        }
    }

    public static class DuplicatedSpotExistsException extends SpotException {

        public DuplicatedSpotExistsException(String formattedAddress) {
            super(String.format("중복된 주소입니다. - request info { formattedAddress : %s }", formattedAddress));
        }
    }
}
