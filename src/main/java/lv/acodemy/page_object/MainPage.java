package lv.acodemy.page_object;

import lv.acodemy.utils.LocalDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainPage {

    WebDriver driver = LocalDriverManager.getInstance();

//    public MainPage(ChromeDriver driver) {
//        this.driver = driver;
//    }

    private final By addStudentButton = By.id("addStudentButton");

    public void addStudentClick(){
        driver.findElement(addStudentButton).click();

    }
}
