package ejercicio3.conPOyFact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.Set;

public class ShoesPage {
    WebDriver driver;
    @FindBy(css = "body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(5) > div > div.actions > ul > li:nth-child(2) > a")
    WebElement wingtipShoe;

    @FindBy(css = "body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(6) > div > div.actions > ul > li:nth-child(2) > a")
    WebElement suedeShoe;

    @FindBy(css = "body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-right.sidebar > div > div.block-content > div > button")
    WebElement buttonCompare;

    @FindBy(css = "body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-right.sidebar > div > div.block-content > div > a")
    WebElement clearAll;

    @FindBy(css = "body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > ul > li > ul > li")
    WebElement msj;

    ProductComparisonPage comparisonPage;

    public ShoesPage(WebDriver driver){

        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public ProductComparisonPage comparison(){
        //Obtenemos el manejador de la venta ShoesPage
        String myHandleId = driver.getWindowHandle();
        //Pulsamos sobre el botón para hacer la comparación
        buttonCompare.click();

        Set<String> setIds = driver.getWindowHandles();


        String[] handlesIds = setIds.toArray(new String[setIds.size()]);
        System.out.println("ID 0: "+handlesIds[0]);
        System.out.println("ID 1: "+handlesIds[1]);
        comparisonPage = PageFactory.initElements(driver, ProductComparisonPage.class);
        comparisonPage.setMyHandleId(handlesIds[1]);
        comparisonPage.setMyHandleIdFROM(handlesIds[0]);
        this.driver.switchTo().window(handlesIds[1]);
        return comparisonPage;
    }

    public void selectShoeToCompare(int number){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        switch (number){
            case 5: jse.executeScript("arguments[0].scrollIntoView();", wingtipShoe);
                    wingtipShoe.click();
                    break;
            case 6: jse.executeScript("arguments[0].scrollIntoView();", suedeShoe);
                    suedeShoe.click();
                    break;
        }
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String clear(){
        clearAll.click();
        Alert alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        alert.accept();
        return mensaje;
    }

    public String msj(){
        return msj.getText();
    }
}
