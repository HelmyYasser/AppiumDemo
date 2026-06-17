package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CheckOutPage extends BaseScreen {
    private final By firstNameField = AppiumBy.accessibilityId("test-First Name");
    private final By lastNameField = AppiumBy.accessibilityId("test-Last Name");
    private final By postalCodeField = AppiumBy.accessibilityId("test-Zip/Postal Code");
    private final By continueButton = AppiumBy.accessibilityId("test-CONTINUE");

    public CheckOutPage(AppiumDriver driver) {
        super(driver);
    }

    public CheckOutPage enterFirstName(String firstName) {
        sendKeys(firstNameField, firstName);
        return this;
    }

    public CheckOutPage enterLastName(String lastName) {
        sendKeys(lastNameField, lastName);
        return this;
    }

    public CheckOutPage enterPostalCode(String postalCode) {
        sendKeys(postalCodeField, postalCode);
        return this;
    }

    public CheckOutReviewPage clickOnContinue() {
        click(continueButton);
        return new CheckOutReviewPage(driver);
    }
}