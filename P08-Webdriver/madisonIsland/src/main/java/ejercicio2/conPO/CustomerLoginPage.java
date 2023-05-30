package ejercicio2.conPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    WebDriver driver;
    WebElement email;
    WebElement pass;
    WebElement login;
    String error;

    public CustomerLoginPage(WebDriver driver){
        this.driver = driver;
        email = driver.findElement(By.cssSelector("#email"));
        pass = driver.findElement(By.cssSelector("#pass"));
        login = driver.findElement(By.cssSelector("#send2[title='Login']"));
    }

    public MyAccountPage login_correct(String email, String pass){
        this.email.sendKeys(email);
        this.pass.sendKeys(pass);
        login.submit();
        return new MyAccountPage(driver);
    }

    public String login_fail(String email, String pass){
        this.email.sendKeys(email);
        this.pass.sendKeys(pass);
        login.submit();
        error = driver.findElement(By.cssSelector("li.error-msg")).getText();
        return error;
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
