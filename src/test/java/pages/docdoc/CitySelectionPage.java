package pages.docdoc;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

import io.qameta.allure.Step;
import models.City;
import pages.base.BasePage;

import java.time.Duration;

public class CitySelectionPage extends BasePage {

    private static final String SEARCH_GEO_INPUT = "[data-testid='search-form__location-field']";
    private static final String ELEMENT_BY_TEXT = "//*[contains(normalize-space(text()), '%s')]";
    private static final String TO_DOCTOR_LINK = "a[href='/doctor']";

    @Step("Проверяем URL страницы: {expectedUrl}")
    public CitySelectionPage verifyPageUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    @Step("Нажимаем на поле поиска метро/района/города")
    public CitySelectionPage clickSearchGeoInput() {
        $(TO_DOCTOR_LINK)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
                switchTo().window(1);

        $(SEARCH_GEO_INPUT)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();

        return this;
    }

    @Step("Проверяем наличие элемента с текстом: {text}")
    public CitySelectionPage verifyElementWithTextVisible(String text) {
        $x(String.format(ELEMENT_BY_TEXT, text))
                .shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Выбираем город: {city.name}")
    public CitySelectionPage selectCity(City city) {
        $$("span")
                .findBy(text(city.getName()))
                .shouldBe(visible, Duration.ofSeconds(20))
                .click();
        return this;
    }

    @Step("Выбираем город {city.name} и проверяем, что открылась страница для этого города")
    public CitySelectionPage selectCityAndVerifyUrl(City city) {
        return selectCity(city).verifyPageUrl(city.getUrl());
    }

    @Step("Нажимаем на поле поиска метро/района/города и проверяем наличие станции {stationName}")
    public CitySelectionPage clickSearchGeoInputAndVerifyStation(String stationName) {
        return clickSearchGeoInput().verifyElementWithTextVisible(stationName);
    }
}