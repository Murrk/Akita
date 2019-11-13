package ru.netology.akita.page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.akita.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishmentPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement fieldAmount = $("[data-test-id=amount] input");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private SelenideElement fieldFrom = $("[data-test-id=from] input");
    private SelenideElement fieldTo = $("[data-test-id=to] input");
    private SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");


    public CardReplenishmentPage() {
        heading.shouldBe(visible);
    }

    public DashboardPage ReplenishmentCardOne(int amount, DataHelper.CardNumber cardNumber){
        fieldAmount.setValue(String.valueOf(amount));
        fieldFrom.setValue(cardNumber.getNumber());
        fieldTo.find(withText("**** **** **** 0001"));
        buttonTransfer.click();
        return new DashboardPage();
    }

    public DashboardPage ReplenishmentCardTwo(int amount, DataHelper.CardNumber cardNumber){
        fieldAmount.setValue(String.valueOf(amount));
        fieldFrom.setValue(cardNumber.getNumber());
        fieldTo.find(withText("**** **** **** 0002"));
        buttonTransfer.click();
        return new DashboardPage();
    }

    public boolean errorNotification(){
        return errorNotification.is(visible);
    }

}
