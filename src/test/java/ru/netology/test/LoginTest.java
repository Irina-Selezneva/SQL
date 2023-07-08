package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void clear() {
        SQLHelper.clearBD();}

    @Test
    void shouldRunAppWithValidData() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.validFields();

    }

    @Test
    void shouldWarnIfRunAppWithInvalidLogin() {
        var loginPage = new LoginPage();
        var invalidAuthInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLogin(invalidAuthInfo);
    }
}
