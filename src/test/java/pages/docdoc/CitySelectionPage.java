package pages.docdoc;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

import io.qameta.allure.Step;
import models.City;
import pages.base.BasePage;

public class CitySelectionPage extends BasePage {

    private static final String SEARCH_GEO_INPUT = "[data-test-id='search_geo_input']";
    private static final String SEARCH_GEO_ITEMS = "[data-test-id='search_geo_items']";

    @Step("Проверяем URL страницы: {expectedUrl}")
    public CitySelectionPage verifyPageUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    @Step("Нажимаем на поле поиска метро/района/города")
    public CitySelectionPage clickSearchGeoInput() {
        element(SEARCH_GEO_INPUT)
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Проверяем наличие станции метро: {stationName}")
    public CitySelectionPage verifySubwayStationVisible(String stationName) {
        $$(SEARCH_GEO_ITEMS)
                .findBy(text(stationName))
                .shouldBe(visible);
        return this;
    }

    @Step("Выбираем город: {city.name}")
    public CitySelectionPage selectCity(City city) {
        $$("span")
                .findBy(text(city.getName()))
                .shouldBe(visible)
                .click();
        return this;
    }

    @Step("Выбираем город {city.name} и проверяем, что открылась страница для этого города")
    public CitySelectionPage selectCityAndVerifyUrl(City city) {
        return selectCity(city).verifyPageUrl(city.getUrl());
    }

    @Step("Нажимаем на поле поиска метро/района/города и проверяем наличие станции {stationName}")
    public CitySelectionPage clickSearchGeoInputAndVerifyStation(String stationName) {
        return clickSearchGeoInput().verifySubwayStationVisible(stationName);
    }
}