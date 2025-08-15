package pages.docdoc;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

import io.qameta.allure.Step;
import models.Doctor;
import pages.base.BasePage;

import java.time.Duration;

public class DoctorsPage extends BasePage {

    private static final String KIDS_RECEPTION_SEARCH = "#kidsReceptionSearch";
    private static final String KIDS_SPECIALTIES = "//div[@data-testid='top_content_seo']//*[contains(@class, '') and contains(text(), 'Детские неврологи')]";
    private static final String TOP_CONTENT_SEO = "[data-testid='top_content_seo']";
    private static final String TODAY_FILTER = "//span[contains(text(), 'Сегодня')]";
    private static final String APPLIED_FILTERS_BADGE = "[data-testid='badge-applied-filters-count']";

    @Step("Открываем общую страницу врачей")
    public DoctorsPage openDoctorsPage() {
        openPage("https://docdoc.ru/doctor/");
        return this;
    }

    @Step("Открываем страницу c неврологами")
    public DoctorsPage openNevrologPage() {
        openPage("https://docdoc.ru/doctor/nevrolog");
        return this;
    }

    @Step("Выбираем специальность врача: {specialty.displayName}")
    public DoctorsPage selectDoctorSpecialty(Doctor.Specialty specialty) {
        String selector = String.format("img[alt='%s']", specialty.getDisplayName());
        element(selector).click();
        return this;
    }

    @Step("Проверяем, что открылась страница специальности с заголовком: {expectedTitle}")
    public DoctorsPage verifySpecialtyPageOpened(String urlPart, String expectedTitle) {
        webdriver().shouldHave(urlContaining(urlPart));
        element(TOP_CONTENT_SEO)
                .shouldBe(visible, Duration.ofSeconds(30))
                .$("h1")
                .shouldHave(text(expectedTitle));
        return this;
    }

    @Step("Включаем фильтр детских врачей")
    public DoctorsPage enableKidsFilter() {
        element(KIDS_RECEPTION_SEARCH)
                .sibling(0)
                .click();
        return this;
    }

    @Step("Проверяем появление детских врачей")
    public DoctorsPage verifyKidsDoctorsAppear() {
        $x(KIDS_SPECIALTIES).shouldBe(visible, Duration.ofSeconds(30));
        return this;
    }

    @Step("Нажимаем на фильтр Сегодня")
    public DoctorsPage clickTodayFilter() {
        $x(TODAY_FILTER)
                .shouldBe(visible, Duration.ofSeconds(30))
                .click();
        return this;
    }

    @Step("Проверяем что счетчик примененных фильтров показывает: {expectedCount}")
    public DoctorsPage verifyAppliedFiltersCount(String expectedCount) {
        element(APPLIED_FILTERS_BADGE)
                .shouldBe(visible, Duration.ofSeconds(30))
                .shouldHave(text(expectedCount));
        return this;
    }

    @Step("Проверяем открытие страницы указанной специальности")
    public DoctorsPage openDoctorsPageAndSelectSpecialty(Doctor.Specialty specialty) {
        return openDoctorsPage()
                .selectDoctorSpecialty(specialty)
                .verifySpecialtyPageOpened(specialty.getUrlPart(), specialty.getPageTitle());
    }

    @Step("Включаем фильтр детских врачей и проверяем смену заголовка")
    public DoctorsPage openDoctorsPageAndEnableKidsFilter() {
        return openNevrologPage()
                .enableKidsFilter()
                .verifyKidsDoctorsAppear();
    }
}