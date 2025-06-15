package pages.docdoc;

import pages.base.BasePage;
import models.Doctor;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class DoctorsPage extends BasePage {

    private static final String KIDS_RECEPTION_SEARCH = "#kidsReceptionSearch";
    private static final String ADULT_SPECIALTIES = "[data-testid='popular-specialties__adult']";
    private static final String TOP_CONTENT_SEO = "[data-testid='top_content_seo']";

    public DoctorsPage openDoctorsPage() {
        openPage("https://docdoc.ru/doctor");
        return this;
    }

    public DoctorsPage selectDoctorSpecialty(Doctor.Specialty specialty) {
        String selector = String.format("img[alt='%s']", specialty.getDisplayName());
        element(selector).click();
        return this;
    }

    public DoctorsPage verifySpecialtyPageOpened(String urlPart, String expectedTitle) {
        webdriver().shouldHave(urlContaining(urlPart));
        element(TOP_CONTENT_SEO)
                .shouldBe(visible)
                .$("h1")
                .shouldHave(text(expectedTitle));
        return this;
    }

    public DoctorsPage enableKidsFilter() {
        element(KIDS_RECEPTION_SEARCH)
                .sibling(0)
                .click();
        return this;
    }

    public DoctorsPage verifyAdultDoctorsHidden() {
        element(ADULT_SPECIALTIES).shouldNotBe(visible);
        return this;
    }
}