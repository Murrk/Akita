package ru.netology.akita.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement replenishCardOne = $$("[data-test-id=action-deposit").first();
    private SelenideElement replenishCardTwo = $$("[data-test-id=action-deposit").last();
    private SelenideElement balanceOneCard = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement balanceTwoCard = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public CardReplenishmentPage ChoiceCardOne(){
        replenishCardOne.click();
        return new CardReplenishmentPage();
    }

    public CardReplenishmentPage ChoiceCardTwo(){
        replenishCardTwo.click();
        return new CardReplenishmentPage();
    }

    public int getBalanceOneCard(){
        String balanceOneCardStr = balanceOneCard.innerText();
        String balanceOneCardStrClear = balanceOneCardStr.substring(balanceOneCardStr.indexOf(':') + 1, balanceOneCardStr.indexOf('р')).trim();
        int balance = Integer.parseInt(balanceOneCardStrClear);
        return balance;
    }

    public int getBalanceTwoCard(){
        String balanceTwoCardStr = balanceTwoCard.innerText();
        String balanceTwoCardStrClear = balanceTwoCardStr.substring(balanceTwoCardStr.indexOf(':') + 1, balanceTwoCardStr.indexOf('р')).trim();
        int balance = Integer.parseInt(balanceTwoCardStrClear);
        return balance;
    }
}
