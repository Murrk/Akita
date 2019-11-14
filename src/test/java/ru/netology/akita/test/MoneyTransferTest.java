package ru.netology.akita.test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.akita.data.DataHelper;
import ru.netology.akita.page.CardReplenishmentPage;
import ru.netology.akita.page.LoginPage;

public class MoneyTransferTest {
    private String serviceUrl = "http://localhost:9999/";
    private int amount1000 = 1000;
    private int amount20000 = 20000;

    @Test
    @DisplayName("Успешное пополнение карты один с карты два")
    void shouldTransferMoneyFromCardTwoToCardOne(){
        val loginPage = open(serviceUrl, LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = dashboardPage.getBalanceOneCard();
        val balanceSecondCardBefore = dashboardPage.getBalanceTwoCard();
        dashboardPage.choiceCardOne();
        val cardNumberTwo = DataHelper.getCardNumberTwo();
        val cardReplenishmentPage = new CardReplenishmentPage();
        cardReplenishmentPage.replenishmentCardOne(amount1000, cardNumberTwo);
        assertEquals(balanceFirstCardBefore + amount1000,dashboardPage.getBalanceOneCard());
        assertEquals(balanceSecondCardBefore - amount1000,dashboardPage.getBalanceTwoCard());
    }

    @Test
    @DisplayName("Успешное пополнение карты два с карты один")
    void shouldTransferMoneyFromCardOneToCardTwo(){
        val loginPage = open(serviceUrl, LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCardBefore = dashboardPage.getBalanceOneCard();
        val balanceSecondCardBefore = dashboardPage.getBalanceTwoCard();
        dashboardPage.choiceCardTwo();
        val cardNumberOne = DataHelper.getCardNumberOne();
        val cardReplenishmentPage = new CardReplenishmentPage();
        cardReplenishmentPage.replenishmentCardTwo(amount1000, cardNumberOne);
        assertEquals(balanceFirstCardBefore - amount1000,dashboardPage.getBalanceOneCard());
        assertEquals(balanceSecondCardBefore + amount1000,dashboardPage.getBalanceTwoCard());
    }

    @Test
    @DisplayName("Проверка перевода денег на карту с неверным номером")
    void shouldShowErrorIfCardIsNotTheSame(){
        val loginPage = open(serviceUrl, LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.choiceCardTwo();
        val cardNumberOne = DataHelper.getCardNumberForError();
        val cardReplenishmentPage = new CardReplenishmentPage();
        cardReplenishmentPage.replenishmentCardTwo(amount1000, cardNumberOne);
        cardReplenishmentPage.errorNotification();
    }

    @Test
    @DisplayName("Проверка, что нельзя перевести больше, чем на балансе карты")
    void shouldShowErrorIfTransferAmountIsGreaterThanBalance(){
        val loginPage = open(serviceUrl, LoginPage.class);
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.choiceCardOne();
        val cardNumberTwo = DataHelper.getCardNumberTwo();
        val cardReplenishmentPage = new CardReplenishmentPage();
        cardReplenishmentPage.replenishmentCardOne(amount20000, cardNumberTwo);
        cardReplenishmentPage.errorNotification();
    }
}
