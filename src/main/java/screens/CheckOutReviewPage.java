package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CheckOutReviewPage extends BaseScreen {
    private final By finishButton = AppiumBy.accessibilityId("test-FINISH");

    public CheckOutReviewPage(AppiumDriver driver) {
        super(driver);
    }

    public CheckOutReviewPage scrollToFinish() {
        scrollIntoView("FINISH");
        return this;
    }

    public CheckOutCompletePage clickOnFinish() {
        click(finishButton);
        return new CheckOutCompletePage(driver);
    }
}
