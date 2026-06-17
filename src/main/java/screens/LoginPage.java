package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage extends BaseScreen {
    private final By userNameField = AppiumBy.accessibilityId("test-Username");
    private final By passwordField = AppiumBy.accessibilityId("test-Password");
    private final By loginButton = AppiumBy.accessibilityId("test-LOGIN");

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public HomePage successLogin(String username, String password) {
        sendKeys(userNameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
        return new HomePage(driver);
    }
}
