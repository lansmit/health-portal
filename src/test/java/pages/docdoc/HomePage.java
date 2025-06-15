package pages.docdoc;

import pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;

public class HomePage extends BasePage {

    // Локаторы
    private static final String COOKIES_BANNER = "div[data-test-id='cookies-banner']";
    private static final String CITY_SELECT_BUTTON = "[data-test-id='city-select-button']";

    // Конструктор
    public HomePage() {
    }

    // Методы страницы
    public HomePage openHomePage() {
        openPage("https://docdoc.ru/");
        return this;
    }

    public SelenideElement getCookiesBanner() {
        return element(COOKIES_BANNER);
    }

    public HomePage verifyCookiesBannerVisible() {
        getCookiesBanner().shouldBe(visible);
        return this;
    }

    public CitySelectionPage clickCitySelectButton() {
        element(CITY_SELECT_BUTTON)
                .shouldBe(visible)
                .click();
        return new CitySelectionPage();
    }
}