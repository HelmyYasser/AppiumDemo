package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import screens.LoginPage;

public class DemoTest extends BaseTest {

    @Test
    public void validEndToEndFlowTest() {
        new LoginPage(driver)
                .successLogin("standard_user", "secret_sauce")
                .addProduct1()
                .clickAddToCartButton()
                .clickCartBadge()
                .clickOnContinueShopping()
                .scrollToText("Sauce Labs Onesie")
                .addProduct2()
                .clickCartBadge()
                .clickOnCheckOut()
                .enterFirstName("Helmy")
                .enterLastName("Yasser")
                .enterPostalCode("555")
                .clickOnContinue()
                .scrollToFinish()
                .clickOnFinish()
                .clickOnBackHome()
                .clickMenu()
                .clickLogout();
    }
}
