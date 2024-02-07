package com.example.demo.review.exception;

public class ReviewException extends RuntimeException {
    public ReviewException(final String message) {
        super(message);
    }

    public static class ContentLengthException extends ReviewException {

        public ContentLengthException(final int allowedLength, final String inputValue) {
            super(String.format(
                            "입력 내용이 너무 깁니다. - {allowedLength : %d, input_value_length : %d }",
                            allowedLength,
                            inputValue.length()
                    )
            );
        }
    }

    public static class ContentBlankException extends ReviewException {

        public ContentBlankException() {
            super("내용을 입력 해 주세요.");
        }
    }

    public static class ReviewNotFoundException extends ReviewException {

        public ReviewNotFoundException(Long id) {
            super(String.format("조회한 리뷰가 존재하지 않습니다. - request info { email : %s }", id));
        }
    }
}
