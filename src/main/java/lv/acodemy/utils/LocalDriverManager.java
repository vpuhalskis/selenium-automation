package lv.acodemy.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LocalDriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private LocalDriverManager() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            if (ConfigurationProperties.getConfig().getBoolean("run.locally")) {
                driver.set(new ChromeDriver());
            } else {
                driver.set(configureRemote());
            }
        } else {
            return driver.get();
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }

    @SneakyThrows
    public static RemoteWebDriver configureRemote() {
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 11");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-v.puhalskis-c3696");
        sauceOptions.put("accessKey", "30932a5d-854f-4d50-91f8-ce87faa0febc");
        sauceOptions.put("build", "selenium-build-7ML6U");
        sauceOptions.put("name", "<your test name>");
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
        return new RemoteWebDriver(url, browserOptions);
    }
}

