import com.github.javafaker.Faker;
import lv.acodemy.utils.ConfigurationProperties;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SauceDemoFail {
    ChromeDriver driver = new ChromeDriver();
    Faker fakeData = new Faker();
    @Test

    public void sauceDemo (){


        driver.get(ConfigurationProperties.getConfig().getString("add_url2"));
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys(fakeData.name().username());
        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys(fakeData.internet().password());
        driver.findElement(By.id("login-button")).click();

        WebElement errorElement = driver.findElement(By.className("error-button"));
        WebElement errorTag = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assertions.assertThat(errorTag.getText()).isEqualTo
                ("Epic sadface: Username and password do not match any user in this service");

    }
    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
