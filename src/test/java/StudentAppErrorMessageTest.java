import lv.acodemy.page_object.AddStudentPage;
import lv.acodemy.page_object.MainPage;
import lv.acodemy.utils.ConfigurationProperties;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class StudentAppErrorMessageTest {

    WebDriverWait wait = new WebDriverWait
            (LocalDriverManager.getInstance(), Duration.ofSeconds(ConfigurationProperties.getConfig().getLong("wait.time")));
    MainPage mainPage = new MainPage();
    AddStudentPage addStudentPage = new AddStudentPage();
@Test
    public void studentCreationError(){
        LocalDriverManager.getInstance().get(ConfigurationProperties.getConfig().getString("add_url"));
        mainPage.addStudentClick();

        addStudentPage.setSubmitButton();

    WebElement error = LocalDriverManager.getInstance().findElement(By.id("name_help"));
    Assertions.assertThat(error.getText()).isEqualTo("Please enter student name");


}
    @AfterMethod
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }

}


