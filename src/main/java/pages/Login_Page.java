package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Page {

    private WebDriver driver;
    private Wait wait;
    private By bankManagerLogin = By.cssSelector("button[ng-click='manager()']");
    public Login_Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public Bank_Manager_Page navigateToManagerPage(){
        wait.until(ExpectedConditions.elementToBeClickable(bankManagerLogin));
        driver.findElement(bankManagerLogin).click();
        return new Bank_Manager_Page(driver);
    }
}
