import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.page_object.Notifications;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.time.Duration.ofSeconds;
import static lv.acodemy.utils.ConfigurationProperties.*;
import static lv.acodemy.utils.LocalDriverManager.closeDriver;
import static org.assertj.core.api.Assertions.assertThat;
@Slf4j
public class StudentAppTest2 {

        WebDriver driver;
        Faker fakeData;
        WebDriverWait wait;
        MainPage mainPage;
        AddStudentPage addStudentPage;
        Notifications notifications;

        @BeforeMethod
        public void beforeTest() {
            driver = LocalDriverManager.getInstance();
            wait = new WebDriverWait(driver, ofSeconds(getConfig().getLong("wait.time")));
            fakeData = new Faker();
            mainPage = new MainPage();
            addStudentPage = new AddStudentPage();
            notifications = new Notifications(wait);
        }

        @Test(invocationCount = 2)
        public void createStudentTest() {
            driver.manage().timeouts().implicitlyWait(ofSeconds(getConfig().getLong("wait.time")));

            logger.info("Will open now: " + getConfig().getString("add_url"));
            driver.get(getConfig().getString("add_url"));


            mainPage.addStudentClick();

            addStudentPage.setNameField(fakeData.name().fullName());
            addStudentPage.setEmailField(fakeData.internet().emailAddress());
            addStudentPage.setGender("female");
            addStudentPage.setSubmitButton();

            assertThat(notifications.getNotificationSuccessMessage()).isEqualTo("Student successfully added");
        }

        @AfterMethod
        public void tearDown() {
            closeDriver();
        }
    }

