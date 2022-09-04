package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Bank_Manager_Page {
    private WebDriver driver;
    private Wait wait;
    private By addCust = By.cssSelector("button[ng-class=btnClass1]");

    public Bank_Manager_Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public Add_Customer_Page openAddCustomerPage(){
        wait.until(ExpectedConditions.elementToBeClickable(addCust));
        driver.findElement(addCust).click();
        return new Add_Customer_Page(driver);

    }
}
