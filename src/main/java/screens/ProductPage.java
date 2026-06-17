package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ProductPage extends BaseScreen {
    private final By addToCartButton = AppiumBy.accessibilityId("test-ADD TO CART");

    public ProductPage(AppiumDriver driver) {
        super(driver);
    }

    public ProductPage clickAddToCartButton() {
        click(addToCartButton);
        return this;
    }
}
