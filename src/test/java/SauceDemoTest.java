import lombok.extern.slf4j.Slf4j;
import lv.acodemy.utils.ConfigurationProperties;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Slf4j
public class SauceDemoTest {
    ChromeDriver driver = new ChromeDriver();

    @Test

    public void sauceDemo (){

        logger.info("Testim polozitelno");
        driver.get(ConfigurationProperties.getConfig().getString("add_url2"));
        WebElement userName = driver.findElement(By.id("user-name"));
        userName.sendKeys("standard_user");
        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        WebElement secondPageTag = driver.findElement(By.xpath("//div[text()='Products']"));
        Assertions.assertThat(secondPageTag.getText()).isEqualTo("Products");

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
