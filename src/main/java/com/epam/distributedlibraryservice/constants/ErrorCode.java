package com.epam.distributedlibraryservice.constants;


public class ErrorCode {

    private ErrorCode() {
    }

    public static final class Password {
        private static final String VALUE = "password";
        public static final String MISMATCH = Password.VALUE + ".mismatch";
    }

    public static final class UserStatus {
        private static final String USER_STATUS = "user-status";
        public static final String REQUIRED = USER_STATUS + ".required";
    }

}
