package pages.sberhealth;

import pages.base.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AnalysesPage extends BasePage {
    private static final String ADD_TO_CART_BUTTON = "button[data-testid='analysis-detail-card-add-desktop']";
    private static final String CART_COUNTER = "[data-testid='cart-counter']";
    private static final String URL = "https://lk.sberhealth.ru/laboratory-analyses";

    public AnalysesPage openAnalysesPage() {
        openPage(URL);
        return this;
    }

    public AnalysesPage addFirstAnalysisToCart() {
        $$(ADD_TO_CART_BUTTON)
                .first()
                .shouldBe(visible)
                .click();
        return this;
    }

    public AnalysesPage verifyCartCounterEquals(String expectedCount) {
        $(CART_COUNTER)
                .shouldBe(visible)
                .shouldHave(text(expectedCount));
        return this;
    }
}