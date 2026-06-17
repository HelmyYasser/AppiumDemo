package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class HomePage extends BaseScreen {
    private final By firstProductItem = AppiumBy.xpath("(//*[@content-desc='test-Item'])[1]");
    private final By thirdAddToCartButton = AppiumBy.xpath("(//*[@text='ADD TO CART'])[3]");

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public ProductPage addProduct1() {
        click(firstProductItem);
        return new ProductPage(driver);
    }

    public HomePage addProduct2() {
        click(thirdAddToCartButton);
        return this;
    }

    public HomePage scrollToText(String text) {
        scrollIntoView(text);
        return this;
    }
}
