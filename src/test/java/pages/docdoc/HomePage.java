package pages.docdoc;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.SelenideElement;

import io.qameta.allure.Step;
import pages.base.BasePage;

import java.time.Duration;

public class HomePage extends BasePage {

    private static final String COOKIES_BANNER = "div[data-test-id='cookies-banner']";
    private static final String HEADER_LOGO_IMG = "img[data-test-id='adaptive-header__logo-image']";
    private static final String CITY_SELECT_BUTTON = "[data-test-id='city-select-button']";
    private static final String PROMO_CODE_BUTTON = "//button[@class='the-button the-button--type-primary the-button--size-small' and contains(text(), 'Промокод')]";
    private static final String APPLY_BUTTON = "//button[contains(@class, 'PromoCodeModal__button') and contains(text(), 'Применить')]";
    private static final String CONFIRM_CITY_BUTTON = "//button[contains(@class, 'CitySelect__approve-button') and contains(text(), 'Да, верно')]";

    @Step("Открываем главную страницу")
    public HomePage openHomePage() {
        openPage("https://docdoc.ru/");
        $(HEADER_LOGO_IMG).shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    public SelenideElement getCookiesBanner() {
        return element(COOKIES_BANNER);
    }

    @Step("Проверяем видимость баннера cookies")
    public HomePage verifyCookiesBannerVisible() {
        getCookiesBanner().shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Нажимаем на кнопку выбора города")
    public CitySelectionPage clickCitySelectButton() {
        element(CITY_SELECT_BUTTON)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        return new CitySelectionPage();
    }

    @Step("Нажимаем на кнопку Промокод")
    public HomePage clickPromoCodeButton() {
        $x(PROMO_CODE_BUTTON)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        return this;
    }

    @Step("Проверяем видимость кнопки Применить")
    public HomePage verifyApplyButtonVisible() {
        $x(APPLY_BUTTON).shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Нажимаем на кнопку Да, верно")
    public HomePage clickConfirmCityButton() {
        $x(CONFIRM_CITY_BUTTON)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        return this;
    }

    @Step("Открываем главную страницу и проверяем баннер cookies")
    public HomePage openHomePageAndVerifyCookiesBanner() {
        return openHomePage().verifyCookiesBannerVisible();
    }
}