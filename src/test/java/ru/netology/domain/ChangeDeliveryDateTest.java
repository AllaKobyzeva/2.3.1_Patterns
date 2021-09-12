package ru.netology.domain;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class ChangeDeliveryDateTest {


    @Test
    void shouldChangeDeliveryDate() {
        String city = DataGenerator.generateCity();
        String name = DataGenerator.generateName();
        String phone = DataGenerator.generatePhone();
        String firstDate = DataGenerator.generateDate();
        String secondDate = DataGenerator.generateDate();

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(firstDate);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible);
        $(withText(firstDate)).shouldBe(visible);
        refresh();
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(secondDate);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldBe(visible);
        $$("button").find(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible);
        $(withText(secondDate)).shouldBe(visible);
    }
}
