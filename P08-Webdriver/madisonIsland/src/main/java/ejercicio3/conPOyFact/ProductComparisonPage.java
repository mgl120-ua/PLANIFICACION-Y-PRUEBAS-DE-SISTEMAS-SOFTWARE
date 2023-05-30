package ejercicio3.conPOyFact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ProductComparisonPage {
    WebDriver driver;
    String MyHandleId;
    String MyHandleIdFROM;
    @FindBy(css = "body > div > div.buttons-set > button")
    WebElement buttonClose;

    public ProductComparisonPage(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public ShoesPage closeCompare(){
        buttonClose.click();
        this.driver.switchTo().window(MyHandleIdFROM);
        return PageFactory.initElements(driver, ShoesPage.class);
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public void setMyHandleId(String MyHandleId){
        this.MyHandleId = MyHandleId;
    }

    public void setMyHandleIdFROM(String MyHandleIdFROM) {
        this.MyHandleIdFROM = MyHandleIdFROM;
    }
}
