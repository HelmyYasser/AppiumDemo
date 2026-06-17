package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Objects;

public class BaseTest {

    // BaseTest
    /*
     * This class is the base class for all tests in the application.
     * In this layer, we do the following:
     * 1. Initialize the driver.
     * 2. Set the desired capabilities.
     * 3. Set the explicit wait time.
     * 4. Set the null-safety guards.
     * 5. Set the cleanup methods.
     */

    protected AppiumDriver driver;

    @BeforeMethod
    public void setUp() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setPlatformVersion("15");
        options.setDeviceName("Pixel 9 Pro XL API 35");
        options.setApp(System.getProperty("user.dir") + "/apps/Android.SauceLabs.Mobile.Sample.app.2.6.0.apk");
        options.setAppWaitActivity("*");

        // Ensure W3C compliance with Appium 3.x guidelines
        driver = new AndroidDriver(URI.create("http://127.0.0.1:4723/").toURL(), options);
        // Explicit waits only - remove legacy implicit waits to avoid conflicts
        driver.manage().timeouts().implicitlyWait(Objects.requireNonNull(Duration.ofSeconds(0)));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
