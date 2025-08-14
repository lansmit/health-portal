package pages.base;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public abstract class BasePage {

    protected void openPage(String url) {
        open(url);
    }

    protected SelenideElement element(String locator) {
        return $(locator);
    }

    protected void clickElement(String locator) {
        element(locator).click();
    }

    protected void typeText(String locator, String text) {
        element(locator).setValue(text);
    }
}