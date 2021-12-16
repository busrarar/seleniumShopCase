import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class Driver {
    public static WebDriver webDriver;

    public static WebDriver getDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @BeforeSuite
    public void setupDriver(){
        webDriver = getDriver();
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void closeDriver(){
        webDriver.close();
    }

}
