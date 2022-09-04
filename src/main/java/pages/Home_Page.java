package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;

public class Home_Page {
    private WebDriver driver;
    private Actions action;
    private By BankLink = By.xpath("//img[@alt='banking']//parent::figure//parent::a");
    public Home_Page(WebDriver driver){
        this.driver = driver;
        action = new Actions(driver);
    }

    public Login_Page navigateToLoginPage(){
        String currentHandle = driver.getWindowHandle();
        String Url = driver.findElement(BankLink).getAttribute("href");
        driver.findElement(BankLink).click();
        Set<String> Windows=driver.getWindowHandles();
        for(String actual: Windows) {
            if (!actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(actual);
            }
        }
        driver.navigate().to(Url);
        return new Login_Page(driver);
    }
}
