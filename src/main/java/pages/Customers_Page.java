package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

import static Utilities.Configuration_Data_Reader.getConfigData;

public class Customers_Page {
    private WebDriver driver;
    private By OpenCustomerBTN = By.cssSelector("button[ng-class='btnClass2']");
    private By DeleteBTN = By.cssSelector("button[ng-click='deleteCust(cust)']");
    private By CustomerTab = By.cssSelector("button[ng-class='btnClass3']");
    private By CustSelect = By.id("userSelect");
    private By Currency = By.id("currency");
    private By ProcessBTN = By.cssSelector("button[type=Submit]");
    private By Rows = By.cssSelector("tr[ng-repeat='cust in Customers | orderBy:sortType:sortReverse | filter:searchCustomer']");
    private By Cols = By.tagName("td");
    private By CustNoCol = By.cssSelector("span[class='ng-binding ng-scope']");
    private Wait wait;

    public Customers_Page(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    //Related with Scenario 1
    public HashMap ReturnCustData(int id){
        driver.findElement(CustomerTab).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        HashMap CustData = new HashMap<>();
        CustData.put("FIRSTName",driver.findElements(Rows).get(id).findElements(Cols).get(0));
        CustData.put("LASTName",driver.findElements(Rows).get(id).findElements(Cols).get(1));
        CustData.put("PostCode",driver.findElements(Rows).get(id).findElements(Cols).get(2));

        return CustData;
    }


    //Related with Scenario 2
    public String AddCustNumber(int id){
        driver.findElement(OpenCustomerBTN).click();
        wait.until(ExpectedConditions.elementToBeClickable(ProcessBTN));
        Select Customer = new Select(driver.findElement(CustSelect));
        Customer.selectByValue(String.valueOf(id));
        Select currncy = new Select(driver.findElement(Currency));
        currncy.selectByIndex(new Random().nextInt(1,3));
        driver.findElement(ProcessBTN).click();
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String CNumber = alert.getText().split("Account created successfully with account Number :",alert.getText().length()-1)[1];
        alert.accept();
        return CNumber;
    }


    //Related with Scenario 2
    public String ReturnCustNumber(int id){
        driver.findElement(CustomerTab).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        int index = driver.findElements(Rows).get(id).findElements(CustNoCol).size();
        String CustNum = driver.findElements(Rows).get(id).findElements(CustNoCol).get(index-1).getText();
        return CustNum;
    }


    //Related with Scenario 3
    public boolean DeleteCust(int id){
        driver.findElement(CustomerTab).click();
        driver.findElements(DeleteBTN).get(id-1).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        boolean Deleted = true ;
        for(int i = 0 ; i< driver.findElements(Rows).size(); i++){
            if (getConfigData("FirstName") == driver.findElements(Rows).get(i).findElements(Cols).get(0).getText()){
                Deleted = false;
            }
        }
        return Deleted;
    }


}
