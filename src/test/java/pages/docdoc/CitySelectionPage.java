package pages.docdoc;

import pages.base.BasePage;
import models.City;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CitySelectionPage extends BasePage {

    private static final String SEARCH_GEO_INPUT = "[data-test-id='search_geo_input']";
    private static final String SEARCH_GEO_ITEMS = "[data-test-id='search_geo_items']";

    public CitySelectionPage verifyPageUrl(String expectedUrl) {
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

    public CitySelectionPage clickSearchGeoInput() {
        element(SEARCH_GEO_INPUT)
                .shouldBe(visible)
                .click();
        return this;
    }

    public CitySelectionPage verifySubwayStationVisible(String stationName) {
        $$(SEARCH_GEO_ITEMS)
                .findBy(text(stationName))
                .shouldBe(visible);
        return this;
    }

    public CitySelectionPage selectCity(City city) {
        $$("span")
                .findBy(text(city.getName()))
                .shouldBe(visible)
                .click();
        return this;
    }

}