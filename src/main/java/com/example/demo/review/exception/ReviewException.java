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

    public static class NotValidUserToDelete extends ReviewException {

        public NotValidUserToDelete(Long userId) {
            super(String.format("내 리뷰가 아니면 삭제 불가합니다. - request info { userId : %s }", userId));
        }
    }

    public static class SortConditionNotFoundException extends ReviewException {

        public SortConditionNotFoundException(String value) {
            super(String.format("정의되지 않은 정렬 조건입니다 - request info { value : %s }", value));
        }
    }
}
