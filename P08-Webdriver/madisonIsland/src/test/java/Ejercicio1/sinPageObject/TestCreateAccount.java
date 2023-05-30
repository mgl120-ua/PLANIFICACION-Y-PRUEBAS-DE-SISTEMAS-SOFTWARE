package Ejercicio1.sinPageObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestCreateAccount {
    WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    //@Test
    public void createAccount(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo-store.seleniumacademy.com/");

        //1. Verificamos titulo página inicio "Madison Island"
        Assertions.assertTrue(driver.getTitle().contains("Madison Island"));

        //2. Seleccionamos Account
        driver.findElement(By.cssSelector("a.skip-account")).click();
        //2. Seleccionamos el hiperenlace Login
        //Otra opción: driver.findElement(By.cssSelector("[href='http://demo-store.seleniumacademy.com/customer/account/login/']")).click();
        driver.findElement(By.linkText("Log In")).click();

        //3. Verificamos el titulo de la página "Customer Login"
        Assertions.assertTrue(driver.getTitle().contains("Customer Login"));

        //4. Seleccionamos el botón "Create Account"
        driver.findElement(By.cssSelector("#login-form > div > div.col-1.new-users > div.buttons-set")).click();
        //5. Verificamos titulo "Create new Customer Account"
        Assertions.assertEquals( "Create New Customer Account", driver.getTitle());

        //6. Rellenamos campos para crear cuenta menos confirmation
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Marta");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Grimi");
        driver.findElement(By.name("email")).sendKeys("mgl120@gmail.com");
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");

        //6. Enviamos formulario
        driver.findElement(By.cssSelector("button[title='Register']")).submit();

        //7. Verificamos mensaje de error This is a required field.
        Assertions.assertEquals( "This is a required field.", driver.findElement(By.cssSelector("#advice-required-entry-confirmation")).getText());

        //8. Rellenamos el campo que nos falta
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
        //8. Volvemos a enviar formulario
        driver.findElement(By.cssSelector("button[title='Register']")).submit();

        //Titulo pagina "My Account"
        //Assertions.assertEquals( "My Account", driver.getTitle());
    }
    @AfterEach
    public void tearDown(){
        driver.close();
    }
}
