package configuration;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

public class TestConfiguration {

    private static final String SELENOID_URL = System.getProperty("selenoid.url");
    private static final String SELENOID_LOGIN = System.getProperty("selenoid.login");
    private static final String SELENOID_PASSWORD = System.getProperty("selenoid.password");

    public static void configure() {
        configureBrowser();
        configureSelenoid();
    }

    private static void configureBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    private static void configureSelenoid() {
        if (SELENOID_URL != null) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub",
                    SELENOID_LOGIN, SELENOID_PASSWORD, SELENOID_URL);

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}