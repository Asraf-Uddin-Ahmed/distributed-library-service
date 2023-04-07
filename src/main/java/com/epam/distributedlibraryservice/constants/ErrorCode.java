package com.epam.distributedlibraryservice.constants;


public class ErrorCode {

    private ErrorCode() {
    }

    public static final class Password {
        private static final String VALUE = "password";
        public static final String MISMATCH = Password.VALUE + ".mismatch";
        private Password() {
        }
    }

}
