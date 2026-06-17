package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class CartPage extends BaseScreen {
    private final By checkoutButton = AppiumBy.accessibilityId("test-CHECKOUT");
    private final By continueShoppingButton = AppiumBy.accessibilityId("test-CONTINUE SHOPPING");

    public CartPage(AppiumDriver driver) {
        super(driver);
    }

    public HomePage clickOnContinueShopping() {
        click(continueShoppingButton);
        return new HomePage(driver);
    }

    public CheckOutPage clickOnCheckOut() {
        click(checkoutButton);
        return new CheckOutPage(driver);
    }
}
