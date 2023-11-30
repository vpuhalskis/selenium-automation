package lv.acodemy.page_object;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class Notifications {
    WebDriver driver = LocalDriverManager.getInstance();
    WebDriverWait waiter;

//    public Notifications(ChromeDriver driver, WebDriverWait waiter) {
//        this.driver = driver;
//        this.waiter = waiter;
//
//    }
    public Notifications(WebDriverWait waiter) {
        this.waiter = waiter;
    }

    public Notifications(ChromeDriver driver) {
        this.driver = driver;
    }

    private final By notificationMessage = By.className("ant-notification-notice-message");

    public WebElement getNotificationLocator(){
        return driver.findElement(notificationMessage);
    }
    public String getNotificationSuccessMessage() {
        waiter.until(textToBePresentInElement(getNotificationLocator(), "Student successfully added"));
        return getNotificationLocator().getText();
    }
}
