package com.example.demo.spot.exception;

public class SpotException extends RuntimeException {
    public SpotException(String message) {
        super(message);
    }

    public static class SpotNotFoundException extends SpotException {
        public SpotNotFoundException(Long id) {
            super(String.format("여행지 정보가 존재하지 않습니다. - request info { placeId : %s }", id));
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

    public static class LocationLengthException extends SpotException {
        public LocationLengthException(final int allowedLength, final String latitude, final String longitude) {
            super(String.format(
                            "좌표 입력값이 너무 깁니다. - {allowedLength : %d, latitude_length : %d, longitude_length : %d }",
                            allowedLength,
                            latitude.length(),
                            longitude.length()
                    )
            );
        }
    }

    public static class LocationBlankException extends SpotException {
        public LocationBlankException() {
            super("좌표 중 빈 값이 있습니다.");
        }
    }
}