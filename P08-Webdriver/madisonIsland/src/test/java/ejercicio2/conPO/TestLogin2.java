package ejercicio2.conPO;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin2 {
    WebDriver driver;
    HomePage home;
    CustomerLoginPage login;
    MyAccountPage account;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        home = new HomePage(driver);
    }

    //@Test
    public void test_Login_Correct(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assertions.assertEquals("Madison Island", home.getPageTitle());
        home.home();
        login = new CustomerLoginPage(driver);
        Assertions.assertEquals("Customer Login", login.getPageTitle());
        login.login_correct("mgl121@gmail.com","12345678");
        account = new MyAccountPage(driver);
        Assertions.assertEquals("My Account", account.getPageTitle());

    }

    //@Test
    public void test_Login_Incorrect(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assertions.assertEquals("Madison Island", home.getPageTitle());
        home.home();
        login = new CustomerLoginPage(driver);
        Assertions.assertEquals("Customer Login", login.getPageTitle());
        Assertions.assertEquals("Invalid login or password.", login.login_fail("mgl121@gmail.com","qqqqqqqqq"));
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }
}
