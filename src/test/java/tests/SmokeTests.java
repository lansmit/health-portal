package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import pages.docdoc.CitySelectionPage;
import pages.docdoc.HomePage;
import pages.docdoc.DoctorsPage;
import pages.sberhealth.AnalysesPage;
import models.City;
import models.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Функциональное тестирование")
@Feature("Smoke тесты")
@Owner("trubnikov")
public class SmokeTests extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final DoctorsPage doctorsPage = new DoctorsPage();
    private final AnalysesPage analysesPage = new AnalysesPage();

    @Test
    @Story("Проверка основных элементов интерфейса")
    @DisplayName("Проверяем наличие баннера с cookies")
    void cookiesBannerShouldBeVisibleTest() {
        homePage.openHomePageAndVerifyCookiesBanner();
    }

    @Test
    @Story("Функциональность выбора города")
    @DisplayName("Проверяем выбор города и станции метро")
    void selectCityAndVerifySubwayStationTest() {
        City saintPetersburg = City.saintPetersburg();
        CitySelectionPage citySelectionPage = homePage.openHomePage()
                .clickCitySelectButton();
        citySelectionPage.selectCityAndVerifyUrl(saintPetersburg)
                .clickSearchGeoInputAndVerifyStation("Автово");
    }

    @Test
    @Story("Навигация по специальностям врачей")
    @DisplayName("Проверяем переход по специальности врача")
    void verifyDoctorTypeTest() {
        Doctor.Specialty specialty = Doctor.Specialty.GASTROENTEROLOGIST;
        doctorsPage.openDoctorsPageAndSelectSpecialty(specialty);
    }

    @Test
    @Story("Фильтры по врачам")
    @DisplayName("Проверяем фильтр детских врачей на странице неврологов")
    void verifyKidsFilterTest() {
        doctorsPage.openDoctorsPageAndEnableKidsFilter();
    }

    @Test
    @Story("Фильтры по врачам")
    @DisplayName("Проверяем фильтр Сегодня на странице врачей")
    void verifyTodayFilterTest() {
        doctorsPage.openDoctorsPage()
                .clickTodayFilter()
                .verifyAppliedFiltersCount("1");
    }

    @Test
    @Story("Функциональность корзины")
    @DisplayName("Проверяем работу счётчика в корзине")
    void verifyAddingAnalysesToCart() {
        analysesPage.openAnalysesPageAddFirstAnalysisAndVerifyCounter("1");
    }

    @Test
    @Story("Функциональность промокода")
    @DisplayName("Проверяем появление раздела для применения промокода")
    void verifyPromoCodeApplyButtonTest() {
        homePage.openHomePage()
                .clickConfirmCityButton()
                .clickPromoCodeButton()
                .verifyApplyButtonVisible();
    }
}
