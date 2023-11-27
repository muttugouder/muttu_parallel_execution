
package testng;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
public class TestNGTodo{
    public String username = "gmuttu90";
    public String accesskey = "r4VHysUP7KD39xWoBJjWYYwn09h3faCuhMkLXNSpDjumCPVnUB";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    @Parameters(value={"browser","version","platform"})
	@BeforeClass
	
    public void setUp(String browser, String version, String platform) throws Exception {
       DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("version", version);
        capabilities.setCapability("platform", platform); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp");
        capabilities.setCapability("name", "LambdaTestJavaSample");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		    System.out.println("Browser: " + browser + ", Version: " + version + ", Platform: " + platform);
            System.out.println("Driver initialized: " + (driver != null));
    }
    @Test
    public void testSimple() throws Exception {
       driver.get("https://www.amazon.com");
		       
            // 2. Search for iPhone 13
			WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("twotabsearchtextbox")));

            searchBox.sendKeys("iphone13");
            searchBox.submit();

            // 3. Get the price
           // WebDriverWait wait = new WebDriverWait(driver, 10);
WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".a-price-whole")));
            String price = priceElement.getText();

            System.out.println("iPhone 13 price on your ecommerce site: " + price);
      
    }
    @AfterClass
    public void tearDown() throws Exception {
       if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}