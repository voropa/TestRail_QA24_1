package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import utils.InvokedListener;
import utils.TestListener;

import java.time.Duration;

@Listeners({TestListener.class, InvokedListener.class})
public abstract class BaseTest {
    protected final static String USERNAME = "tmsqa24@mailinator.com";
    protected final static String PASSWORD = "P@ssword1";
    private final static String URL = "https://tmsqa24.testrail.io/";
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("safari")) {
            driver = new SafariDriver();
        } else {
            throw new Exception("Unsupported browser");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        context.setAttribute("driver", driver);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod(alwaysRun = true)
    public void clearCookies() {
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
        ((JavascriptExecutor) driver).executeScript("window.sessionStorage.clear();");
    }

    @BeforeMethod(alwaysRun = true)
    public void navigate() {
        driver.get(URL);
    }

}
