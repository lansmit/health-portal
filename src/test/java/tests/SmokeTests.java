package tests;

import pages.docdoc.CitySelectionPage;
import pages.docdoc.HomePage;
import pages.docdoc.DoctorsPage;
import pages.sberhealth.AnalysesPage;
import models.City;
import models.Doctor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.qameta.allure.Allure.step;

public class SmokeTests extends BaseTest {

    private final HomePage homePage = new HomePage();
    private final DoctorsPage doctorsPage = new DoctorsPage();
    private final AnalysesPage analysesPage = new AnalysesPage();

    @Test
    @DisplayName("Проверяем наличие баннера с cookies")
    void cookiesBannerShouldBeVisibleTest() {
        step("Открываем главную страницу и проверяем баннер cookies", () -> {
            homePage.openHomePage()
                    .verifyCookiesBannerVisible();
        });
    }

    @Test
    @DisplayName("Проверяем выбор города и станции метро")
    void selectCityAndVerifySubwayStationTest() {
        City saintPetersburg = City.saintPetersburg();
        CitySelectionPage citySelectionPage = step("Открываем главную страницу", () -> {
            return homePage.openHomePage()
                    .clickCitySelectButton();
        });
        step("Выбираем город Санкт-Петербург и проверяем, что открылась страница для Петербурга", () -> {
            citySelectionPage.selectCity(saintPetersburg)
                    .verifyPageUrl(saintPetersburg.getUrl());
        });
        step("Нажимаем на поле поиска метро/района/города и проверяем наличие станции Автово", () -> {
            citySelectionPage.clickSearchGeoInput()
                    .verifySubwayStationVisible("Автово");
        });
    }

    @Test
    @DisplayName("Проверяем переход по специальности врача")
    void verifyDoctorTypeTest() {
        step("Проверяем открытие страницы гастроэнтеролога", () -> {
            Doctor.Specialty specialty = Doctor.Specialty.GASTROENTEROLOGIST;
            doctorsPage.openDoctorsPage()
                    .selectDoctorSpecialty(specialty)
                    .verifySpecialtyPageOpened(specialty.getUrlPart(), specialty.getPageTitle());
        });
    }

    @Test
    @DisplayName("Проверяем фильтр детских врачей")
    void verifyKidsFilterTest() {
        step("Включаем фильтр детских врачей и проверяем скрытие взрослых", () -> {
            doctorsPage.openDoctorsPage()
                    .enableKidsFilter()
                    .verifyAdultDoctorsHidden();
        });
    }

    @Test
    @DisplayName("Проверяем работу счётчика в корзине")
    void verifyAddingAnalysesToCart() {
        step("Открываем главную страницу заказа анализов", () -> {
            analysesPage.openAnalysesPage();
        });
        step("Добавляем в корзину первый анализ в списке", () -> {
            analysesPage.addFirstAnalysisToCart();
        });
        step("Проверяем, что счётчик в корзине стал равен '1'", () -> {
            analysesPage.verifyCartCounterEquals("1");
        });
    }
}
