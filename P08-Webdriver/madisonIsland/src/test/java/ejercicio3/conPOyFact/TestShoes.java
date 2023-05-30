package ejercicio3.conPOyFact;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class TestShoes {
    WebDriver driver;
    static Cookies cookies;
    ejercicio3.conPOyFact.MyAccountPage account;
    ShoesPage shoes;
    ProductComparisonPage compare;

    @BeforeAll
    public static void cookies(){
        //Acceder al sistema y guardar las Cookies en un fichero
        cookies.storeCookiesToFile("mgl121@gmail.com","12345678");
    }

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        //Cargar las cookies loadCookiesFromFile(WebDriver driver)
        cookies.loadCookiesFromFile(driver);
        //Acceder directamente a la página de la cuenta de usuario
        driver.get("http://demo-store.seleniumacademy.com/customer/account/");
        account = PageFactory.initElements(driver, MyAccountPage.class);
        shoes = PageFactory.initElements(driver, ShoesPage.class);
        compare = PageFactory.initElements(driver, ProductComparisonPage.class);
    }

    @Test
    public void compareShoes(){
        //1. Verificamos titulo "My Account"
        Assertions.assertEquals("My Account", account.getPageTitle());
        //2. Seleccionamos Accesories -> Shoes
        shoes = account.shoes();
        //3. Verificamos titulo "Shoes - Acesories")
        Assertions.assertEquals("Shoes - Accessories" , shoes.getPageTitle());
        //4. Seleccionamos Add to compare

        shoes.selectShoeToCompare(5);
        shoes.selectShoeToCompare(6);
        compare = shoes.comparison();


        Assertions.assertEquals("Products Comparison List - Magento Commerce" , compare.getPageTitle());
        shoes = compare.closeCompare();
        Assertions.assertEquals("Shoes - Accessories" , shoes.getPageTitle());
        Assertions.assertEquals("Are you sure you would like to remove all products from your comparison?" , shoes.clear());
        Assertions.assertEquals("The comparison list was cleared." , shoes.msj());

    }


    @AfterEach
    public void tearDown(){
        driver.close();
    }
}
