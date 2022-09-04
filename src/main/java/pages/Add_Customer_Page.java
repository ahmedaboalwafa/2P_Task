package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Add_Customer_Page {
    private WebDriver driver;
    private Wait wait;
    private By FName = By.cssSelector("input[ng-model=fName]");
    private By LName = By.cssSelector("input[ng-model=lName]");
    private By PCode = By.cssSelector("input[ng-model=postCd");
    private By AddCustBTN = By.cssSelector("button[type=submit]");
    private static int Id;

    public Add_Customer_Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    //Related with Scenario 1
    public Customers_Page addCustomerData(String fName, String lName, String pCode){
        wait.until(ExpectedConditions.elementToBeClickable(AddCustBTN));
        driver.findElement(FName).sendKeys(fName);
        driver.findElement(LName).sendKeys(lName);
        driver.findElement(PCode).sendKeys(pCode);
        driver.findElement(AddCustBTN).click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Id = Integer.parseInt( alert.getText().split("Customer added successfully with customer id :")[1]);
        alert.accept();
        return new Customers_Page(driver);
    }

    //Related with Scenario 1
    public static int returnId(){
        return Id;
    }

}
