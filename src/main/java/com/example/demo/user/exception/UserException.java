package com.example.demo.user.exception;

public class UserException extends RuntimeException{
    public UserException(String message) {
        super(message);
    }

    public static class UserNotFoundException extends UserException{

        public UserNotFoundException(Long id) {
            super(String.format("조회한 멤버가 존재하지 않습니다. - request info { email : %s }", id));
        }

        public UserNotFoundException(String email) {
            super(String.format("조회한 멤버가 존재하지 않습니다. - request info { email : %s }", email));
        }

        public UserNotFoundException(){
            super("사용자 인증에 실패했습니다. 다시 로그인을 시도 해 주세요.");
        }
    }
}
