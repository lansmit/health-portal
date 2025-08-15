package pages.sberhealth;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

import io.qameta.allure.Step;
import pages.base.BasePage;

import java.time.Duration;

public class AnalysesPage extends BasePage {
    private static final String ADD_TO_CART_BUTTON = "button[data-testid='analysis-detail-card-add-desktop']";
    private static final String CART_COUNTER = "//span[text()='Корзина']/following-sibling::*[1]";
    private static final String URL = "https://lk.sberhealth.ru/laboratory-analyses/list?category=vitamins";

    @Step("Открываем главную страницу заказа анализов")
    public AnalysesPage openAnalysesPage() {
        openPage(URL);
        return this;
    }

    @Step("Добавляем в корзину первый анализ в списке")
    public AnalysesPage addFirstAnalysisToCart() {
        $$(ADD_TO_CART_BUTTON)
                .first()
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        return this;
    }

    @Step("Проверяем, что счётчик в корзине равен: {expectedCount}")
    public AnalysesPage verifyCartCounterEquals(String expectedCount) {
        $x(CART_COUNTER)
                .shouldBe(visible, Duration.ofSeconds(30))
                .shouldHave(text(expectedCount));
        return this;
    }

    @Step("Открываем страницу анализов, добавляем первый анализ в корзину и проверяем счётчик")
    public AnalysesPage openAnalysesPageAddFirstAnalysisAndVerifyCounter(String expectedCount) {
        return openAnalysesPage()
                .addFirstAnalysisToCart()
                .verifyCartCounterEquals(expectedCount);
    }
}
