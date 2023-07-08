package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement errorBox = $("[data-test-id=error-notification]");

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        errorBox.shouldBe(visible, Duration.ofSeconds(5));
        $("[data-test-id=error-notification]>.notification__title")
                .shouldHave(text("Ошибка"));
        $("[data-test-id=error-notification]>.notification__content")
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }
    //public void invalidPassword(DataHelper.AuthInfo info) {
       // loginField.setValue(info.getLogin());
    //passwordField.setValue(info.getPassword());
    //  loginButton.click();
    //  passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    //  passwordField.setValue(info.getPassword());
    //  loginButton.click();
    //  passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    //  passwordField.setValue(info.getPassword());
    //  loginButton.click();
    //  errorBox.shouldBe(visible, Duration.ofSeconds(30));
    //  $("[data-test-id=error-notification]>.notification__content")
    //          .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    //  $("[data-test-id=error-notification]>.notification__content")
    //          .shouldHave(text("Превышено допустимое количество попыток. Вход в личный кабинет заблокирован. Обратитесь в службу поддержки банка"));
//
}