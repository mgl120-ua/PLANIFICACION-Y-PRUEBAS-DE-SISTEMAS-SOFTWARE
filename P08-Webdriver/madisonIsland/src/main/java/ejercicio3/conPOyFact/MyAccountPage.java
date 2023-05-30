package ejercicio3.conPOyFact;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MyAccountPage {
    WebDriver driver;
    @FindBy(css  = "#nav > ol > li.level0.nav-3.parent > a") WebElement accessories;

    @FindBy(css = "#nav > ol > li.level0.nav-3.parent > ul > li.level1.nav-3-3 > a") WebElement shoes;
    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public ShoesPage shoes(){
        Actions builder = new Actions(driver);
        builder.moveToElement(accessories);
        builder.perform();
        shoes.click();
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }
}
