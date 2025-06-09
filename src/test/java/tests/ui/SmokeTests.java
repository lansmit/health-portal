package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static io.qameta.allure.Allure.step;

public class SmokeTests {

    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");;
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "https://" + SELENOID_LOGIN + ":" + SELENOID_PASSWORD + "@" + SELENOID_URL + "/wd/hub";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @Test
    @DisplayName("Проверяем наличие баннера с cookies")
    void cookiesBannerShouldBeVisibleTest() {
        step("Открываем сайт https://docdoc.ru/", () -> {
            open("https://docdoc.ru/");
        });

        step("Проверяем, что отображается баннер cookies с data-test-id='cookies-banner'", () -> {
            $("div[data-test-id='cookies-banner']")
                    .shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверяем, что при выборе Санкт-Петербурга подгружаются соответствующие станции метро")
    void selectCityAndVerifySubwayStationTest() {
        step("Открываем главную страницу", () -> {
            open("https://docdoc.ru/");
        });
        step("Нажимаем на выбор города", () -> {
            $("[data-test-id='city-select-button']")
                    .shouldBe(visible)
                    .click();
        });
        step("Выбираем Санкт-Петербург", () -> {
            $$("span").findBy(text("Санкт-Петербург"))
                    .shouldBe(visible)
                    .click();
        });
        step("Проверяем открытие нужной страницы города", () -> {
            webdriver().shouldHave(url("https://spb.docdoc.ru/"));
        });
        step("Нажимаем на поле поиска метро/района/города", () -> {
            $("[data-test-id='search_geo_input']")
                    .shouldBe(visible)
                    .click();
        });
        step("Проверяем наличие станции метро 'Автово'", () -> {
            $$("[data-test-id='search_geo_items']")
                    .findBy(text("Автово"))
                    .shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Проверяем, что по иконке категории открывается нужная категория врачей")
    void verifyDoctorTypeTest() {
        step("Открываем главную страницу", () -> {
            open("https://docdoc.ru/doctor");
        });
        step("Выбираем специальность 'Гастроэнтеролог'", () -> {
            $("img[alt='Гастроэнтеролог']")
                    .click();
        });
        step("Проверяем, что открылась нужная страница", () -> {
            webdriver().shouldHave(urlContaining("gastroenterolog"));
            $("[data-testid='top_content_seo']")
                    .shouldBe(visible)
                    .$("h1")
                    .shouldHave(text("Гастроэнтерологи"));
        });
    }

    @Test
    @DisplayName("Проверяем, что по фильтру 'Детские врачи' со страницы скрываются врачи для взрослых")
    void verifyKidsFilterTest() {
        step("Открываем главную страницу", () -> {
            open("https://docdoc.ru/doctor");
        });
        step("Включаем фильтр по детским врачам", () -> {
            $("#kidsReceptionSearch")
                    .sibling(0)
                    .click();
        });
        step("Проверяем, что врачи для взрослых скрыты со страницы", () -> {
            $("[data-testid='popular-specialties__adult']").shouldNotBe(visible);
        });
    }

        @Test
        @DisplayName("rm -rf .git\n")
        void verifyAddingAnalysesToCart() {
            step("Открываем главную страницу заказа анализов", () -> {
                open("https://lk.sberhealth.ru/laboratory-analyses");
            });
            step("Добавляем в корзину первый анализ в списке", () -> {
                $$("button[data-testid='analysis-detail-card-add-desktop']")
                        .first()
                        .shouldBe(visible)
                        .click();
            });
            step("Проверяем, что счётчик в корзине стал равен '1'", () -> {
                $("[data-testid='cart-counter']")
                        .shouldBe(visible)
                        .shouldHave(text("1"));
            });
        }
    }





