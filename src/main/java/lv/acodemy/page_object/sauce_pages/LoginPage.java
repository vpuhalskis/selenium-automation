package lv.acodemy.page_object.sauce_pages;

import lombok.Getter;
import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.How.XPATH;

public class LoginPage {

    private WebDriver driver = LocalDriverManager.getInstance();
    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = XPATH, xpath = "//input[@data-test='username']")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;
@Getter
    @FindBy(how = XPATH, xpath = "//h3[@data-test='error']")
    WebElement errorMesage;

    public void authorize(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

}
