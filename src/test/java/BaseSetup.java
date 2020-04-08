import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseSetup {

    public AndroidDriver<MobileElement> driver;
    public WebDriverWait wait;


    @BeforeMethod
    public void setup () throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Nexus 5");
        caps.setCapability("udid", "emulator-5554"); //DeviceId from "adb devices" command
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("appPackage", "com.pozitron.hepsiburada");
        caps.setCapability("appActivity","com.hepsiburada.ui.startup.SplashActivity");
        caps.setCapability("noReset","false");
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void loginTest () throws InterruptedException {

        dialogsCloseAndCloseAnimation();
        loginAccount();
    }

    public void loginAccount(){
        WebElement account = wait.until(ExpectedConditions.elementToBeClickable(By.id("account_icon")));
        account.click();
        WebElement userLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("llUserAccountLogin")));
        userLogin.click();
        WebElement loginEmail = wait.until(ExpectedConditions.elementToBeClickable(By.id("etLoginEmail")));
        loginEmail.sendKeys("mb@mb.com");
        WebElement loginPassword = wait.until(ExpectedConditions.elementToBeClickable(By.id("etLoginPassword")));
        loginPassword.sendKeys("123456");
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnLoginLogin")));
        loginButton.click();
    }

    public void dialogsCloseAndCloseAnimation(){
        WebElement check = wait.until(ExpectedConditions.elementToBeClickable(By.id("cb_dialog_perm_rationale_location_dont_show")));
        check.click();
        WebElement noButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button2")));
        noButton.click();
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("button1")));
        okButton.click();
        //Click animation close buton
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("close_button")));
        element.click();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}

