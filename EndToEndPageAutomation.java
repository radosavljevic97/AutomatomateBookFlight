import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EndToEndPageAutomation {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // FROM -> place
        driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
        driver.findElement(By.xpath("//a[@value='DEL']")).click();
        Thread.sleep(1000);

        // TO -> place
        driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value='MAA']")).click();

        // Pick today from Calendar
        driver.findElement(By.cssSelector(".ui-state-default.ui-state-highlight")).click();

        // Assertujemo da li je return date calendar disable-ovan
        if(driver.findElement(By.id("Div1")).getAttribute("style").contains("1")){
            System.out.println("It's disabled");
            // Ili
            Assert.assertTrue(true);
        }
        else{
            Assert.assertTrue(false);
        }

        // Biramo broj putnika
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        driver.findElement(By.id("divpaxinfo")).click();
        Thread.sleep(2000);
        for(int i = 1; i < 5; i++){
            driver.findElement(By.id("hrefIncAdt")).click();
        }

        driver.findElement(By.id("btnclosepaxoption")).click();
        Assert.assertEquals(driver.findElement(By.id("divpaxinfo")).getText(), "5 Adult");
        System.out.println(driver.findElement(By.id("divpaxinfo")).getText());

        // Biramo valutu
        WebElement staticDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select dropdown = new Select(staticDropdown);
        dropdown.selectByIndex(3);

        // Chekiramo Family and Friends checkbox
        driver.findElement(By.id("ctl00_mainContent_chk_friendsandfamily")).click();

        // Kliknemo na search
        driver.findElement(By.id("ctl00_mainContent_btn_FindFlights")).click();
    }
}
