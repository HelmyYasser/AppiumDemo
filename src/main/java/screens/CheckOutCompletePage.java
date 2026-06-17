package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CheckOutCompletePage extends BaseScreen {
    private final By backHomeButton = AppiumBy.accessibilityId("test-BACK HOME");

    public CheckOutCompletePage(AppiumDriver driver) {
        super(driver);
    }

    public HomePage clickOnBackHome() {
        click(backHomeButton);
        return new HomePage(driver);
    }
}
