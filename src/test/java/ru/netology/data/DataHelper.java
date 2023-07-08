package ru.netology.data;


import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));
    private DataHelper() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo("oleg", faker.internet().password());
    }
    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getInvalidVerificationCode() {
        return String.valueOf(faker.random().nextLong());
    }
}
