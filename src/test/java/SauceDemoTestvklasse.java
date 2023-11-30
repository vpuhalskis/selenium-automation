import lv.acodemy.page_object.sauce_pages.InventoryPage;
import lv.acodemy.page_object.sauce_pages.LoginPage;
import lv.acodemy.utils.ConfigurationProperties;
import lv.acodemy.utils.Constants.ErrorMessages;
import lv.acodemy.utils.LocalDriverManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static lv.acodemy.utils.ConfigurationProperties.getConfig;
import static lv.acodemy.utils.Constants.ErrorMessages.*;
import static lv.acodemy.utils.LocalDriverManager.closeDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElements;

public class SauceDemoTestvklasse {

    WebDriver driver = LocalDriverManager.getInstance();
    LoginPage loginPage = new LoginPage();
    InventoryPage inventoryPage = new InventoryPage();
    Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    @Test (description = "Test successful login")
    public void testLogin(){
    driver.get(getConfig().getString("sauce.demo.url"));
    loginPage.authorize("standard_user", "secret_sauce");
    inventoryPage.getInventoryItems();
    wait.until(visibilityOfAllElements(inventoryPage.getInventoryItems()));
        assertThat(inventoryPage.getInventoryItems()).hasSize(6);
    }

    @Test(description = "Test authorization incorrect password")
    public void testLoginIncorrectCerdentails(){
        driver.get(getConfig().getString("sauce.demo.url"));
        loginPage.authorize("standard", "incorrect");
        assertThat(loginPage.getErrorMesage().getText()).isEqualTo(USER_AND_PASSWORD_NOT_MATCH);
    }

    @Test(description = "Test authorization no login ")
    public void testLoginNoCerdentails(){
        driver.get(getConfig().getString("sauce.demo.url"));
        loginPage.authorize("", "incorrect");
        assertThat(loginPage.getErrorMesage().getText()).isEqualTo(USER_NAME_IS_REQUIRED);
    }
    @Test(description = "Test authorization no password ")
    public void testPasswordNoCerdentails(){
        driver.get(getConfig().getString("sauce.demo.url"));
        loginPage.authorize("standard_user", "");
        assertThat(loginPage.getErrorMesage().getText()).isEqualTo(PASSWORD_IS_REQUIRED);
    }






    @AfterMethod
    public void after (){
        closeDriver();
    }
}
