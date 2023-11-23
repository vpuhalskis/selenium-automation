import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notifications;
import lv.acodemy.utils.ConfigurationProperties;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StudentAppTest {

    Faker fakeData = new Faker();
    //ChromeDriver driver = LocalDriverManager.getInstance();

    WebDriverWait wait = new WebDriverWait
            (LocalDriverManager.getInstance(), Duration.ofSeconds(ConfigurationProperties.getConfig().getLong("wait_time")));
    MainPage mainPage = new MainPage();
    AddStudentPage addStudentPage = new AddStudentPage();

    Notifications notifications = new Notifications(wait);
//    WebDriverWait wait = new WebDriverWait
//            (driver, Duration.ofSeconds(ConfigurationProperties.getConfig().getLong("wait_time")));

    private final String APP_URL = "http://acodemy-app-springboot-env.eba-pagku2yg.eu-north-1.elasticbeanstalk.com/";
    @Test
    public void createStudentTest(){
       LocalDriverManager.getInstance().manage()
               .timeouts()
               .implicitlyWait(Duration.ofSeconds(ConfigurationProperties.getConfig().getLong("wait_time"))); //nejavnoe ozidanie

        //driver.get(APP_URL);

        logger.info("123");
        LocalDriverManager.getInstance().get(ConfigurationProperties.getConfig().getString("add_url"));
        mainPage.addStudentClick();
//
//        WebElement nameInputField = driver.findElement(By.id("name"));
//        nameInputField.sendKeys(fakeData.name().fullName());
        addStudentPage.setNameField(fakeData.name().fullName());
//
//        WebElement emailInputField = driver.findElement(By.id("email"));
//        emailInputField.sendKeys(fakeData.internet().emailAddress());
        addStudentPage.setEmailField(fakeData.internet().emailAddress());
//
//        driver.findElement(By.id("gender")).click();
//        driver.findElement(By.xpath("//div[@class='rc-virtual-list-holder-inner']//div[text()='MALE']")).click();
        addStudentPage.setGender("male");
//
//        WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']//parent::button"));
//        submitButton.click();
        addStudentPage.setSubmitButton();
//
//        WebElement notificationMessage = driver.findElement(By.className("ant-notification-notice-message"));
//        wait.until(ExpectedConditions.textToBePresentInElement(notificationMessage,"Student successfully added"));
        notifications.getNotificationLocator();
        assertThat(notifications.getNotificationSuccessMessage()).isEqualTo("Student successfully added");
    }
    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }
}
