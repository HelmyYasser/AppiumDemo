package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class BaseScreen {
    // BaseScreen
    /*
     * This class is the superclass for all screens in the application.
     * It contains common methods that are used by all screens.
     * It has an explicit wait time of 10 seconds for all elements.
     * It has null-safety guards for all methods.
     * It has common header elements that are present across all screens.
     * It has methods to interact with the common header elements.
     */

    protected final AppiumDriver driver;
    protected final WebDriverWait wait;

    // Common Header elements present across application screens
    private final By cartBadge = AppiumBy.accessibilityId("test-Cart");
    private final By menuButton = AppiumBy.accessibilityId("test-Menu");
    private final By logoutButton = AppiumBy.accessibilityId("test-LOGOUT");

    public BaseScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                Objects.requireNonNull(driver, "Driver must not be null"),
                Objects.requireNonNull(Duration.ofSeconds(10)));
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                Objects.requireNonNull(locator, "Locator must not be null")));
    }

    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(
                Objects.requireNonNull(locator, "Locator must not be null")));
    }

    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    protected void sendKeys(By locator, String text) {
        waitForVisibility(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return waitForVisibility(locator).getText();
    }

    protected void scrollIntoView(String text) {
        driver.findElement(new AppiumBy.ByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().textContains(\""
                        + text + "\"))"));
    }

    // Shared Header Actions
    public CartPage clickCartBadge() {
        click(cartBadge);
        return new CartPage(driver);
    }

    public BaseScreen clickMenu() {
        click(menuButton);
        return this;
    }

    public LoginPage clickLogout() {
        click(logoutButton);
        return new LoginPage(driver);
    }
}
