package Ejercicio1.sinPageObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    //@Test
    public void loginOk(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo-store.seleniumacademy.com/");


        //1. Verificamo título "Madison Island
        Assertions.assertEquals("Madison Island", driver.getTitle());

        //2. Seleccionamos Account
        driver.findElement(By.cssSelector("a.skip-account")).click();
        //2. Seleccionamos el hiperenlace Login
        //Otra opción: driver.findElement(By.cssSelector("[href='http://demo-store.seleniumacademy.com/customer/account/login/']")).click();
        driver.findElement(By.linkText("Log In")).click();


        Assertions.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.cssSelector("#email")).sendKeys("mgl121@gmail.com");
        driver.findElement(By.cssSelector("#send2")).submit();
        Assertions.assertEquals("This is a required field.", driver.findElement(By.cssSelector("#advice-required-entry-pass")).getText());
        driver.findElement(By.cssSelector("#pass")).sendKeys("12345678");
        driver.findElement(By.cssSelector("#send2[title='Login']")).submit();

        Assertions.assertEquals("My Account", driver.getTitle());
    }

    //@Test
    public void loginFailed(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo-store.seleniumacademy.com/");


        //1. Verificamo título "Madison Island
        Assertions.assertEquals("Madison Island", driver.getTitle());

        //2. Seleccionamos Account
        driver.findElement(By.cssSelector("a.skip-account")).click();
        //2. Seleccionamos el hiperenlace Login
        //Otra opción: driver.findElement(By.cssSelector("[href='http://demo-store.seleniumacademy.com/customer/account/login/']")).click();
        driver.findElement(By.linkText("Log In")).click();

        Assertions.assertEquals("Customer Login", driver.getTitle());
        driver.findElement(By.cssSelector("#email")).sendKeys("mgl121@gmail.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("qqqqqqqqqqqqqqqq");
        driver.findElement(By.cssSelector("#send2[title='Login']")).submit();
        Assertions.assertEquals("Invalid login or password.", driver.findElement(By.cssSelector("li.error-msg")).getText());

    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }
}
