package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebElement account;
    WebElement login;


    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get("http://demo-store.seleniumacademy.com/");
        account = driver.findElement(By.cssSelector("a.skip-account"));
    }

    public CustomerLoginPage home(){
        account.click();
        login = driver.findElement(By.linkText("Log In"));
        login.click();
        return new CustomerLoginPage(driver);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

}
