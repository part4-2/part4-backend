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
    }
}
