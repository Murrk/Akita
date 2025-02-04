package ru.netology.akita.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardNumber {
        private String number;
    }

    public static CardNumber getCardNumberOne() {
         return new CardNumber("5559 0000 0000 0001");
    }

    public static CardNumber getCardNumberTwo() {
        return new CardNumber("5559 0000 0000 0002");
    }

    public static CardNumber getCardNumberForError() {
        return new CardNumber("5559 0000 0000 0003");
    }
}
