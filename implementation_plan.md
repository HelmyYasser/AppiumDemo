# Implementation Plan - Appium Restructuring

This plan outlines the restructuring of the Appium automation project to align with modern Page Object Model (POM) clean code practices and strict Appium 3 / W3C architectural specifications.

## Proposed Architecture Changes

1. **Decoupling and Packaging:**
   - Organize screen classes inside `screens` package under `src/main/java/screens/`.
   - Organize test classes inside `tests` package under `src/test/java/tests/`.
   - Create a dedicated test base package `base` under `src/test/java/base/` containing `BaseTest.java`.
   - Implement `BaseScreen.java` as the parent class for all screen objects.

2. **W3C Appium 3.x Driver Configuration:**
   - Migrate from the deprecated `DesiredCapabilities` to `UiAutomator2Options` in `BaseTest.java`.
   - Disable implicit waits (`implicitlyWait(Duration.ofSeconds(0))`) to ensure only W3C explicit waits are utilized, preventing wait conflicts.
   - Implement `@BeforeMethod` and `@AfterMethod` hooks to clean up driver instances and prevent dangling Appium sessions.

3. **Page Object Model (POM) & Locators:**
   - Standardize locators to use `AppiumBy.accessibilityId` (Accessibility IDs) instead of brittle XPaths where possible.
   - Move all locator elements to `private final By` fields at the top of each screen class.
   - Encapsulate common header actions (e.g., clicking the cart badge, side menu, logout) into `BaseScreen.java` so they are inherited by all screens.
   - Standardize page methods to return the next page object to allow clean method chaining.

4. **Explicit Waits Only:**
   - Implement a robust `WebDriverWait` in `BaseScreen.java` and wrap elements interactions in helper methods (`click`, `sendKeys`, `waitForVisibility`).

---

## Proposed Changes

### [screens] - Page / Screen Objects

#### [NEW] [BaseScreen.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/BaseScreen.java)
- Parent class for all screens. Holds `AppiumDriver` and `WebDriverWait`.
- Provides central, explicit wait helper methods for interactions.
- Contains common header objects (Cart Badge, Menu, Logout) to avoid code duplication.

#### [MODIFY] [LoginPage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/LoginPage.java)
- Extends `BaseScreen`.
- Updates locators to utilize Accessibility IDs.
- Cleans up `successLogin` method using base interaction helpers.

#### [MODIFY] [HomePage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/HomePage.java)
- Extends `BaseScreen`.
- Inherits header actions.
- Cleans up locator definitions and actions.

#### [MODIFY] [ProductPage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/ProductPage.java)
- Extends `BaseScreen`.
- Removes duplicate cart badge elements (now inherited).
- Renames/simplifies interaction methods.

#### [MODIFY] [CartPage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/CartPage.java)
- Extends `BaseScreen`.
- Employs accessibility ID locator strategy.

#### [MODIFY] [CheckOutPage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/CheckOutPage.java)
- Extends `BaseScreen`.
- Simplifies locator definitions and chains methods.

#### [MODIFY] [CheckOutReviewPage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/CheckOutReviewPage.java)
- Extends `BaseScreen`.
- Simplifies locators and returns the complete page.

#### [NEW] [CheckOutCompletePage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/CheckOutCompletePage.java)
- Extends `BaseScreen`.
- Replaces typo-named class.

#### [DELETE] [CheckOutCopletePage.java](file:///c:/Helmy/Automation/AppiumDemo/src/main/java/screens/CheckOutCopletePage.java)
- Removed due to spelling correction.

---

### [tests] - Base & Test Scripts

#### [NEW] [BaseTest.java](file:///c:/Helmy/Automation/AppiumDemo/src/test/java/base/BaseTest.java)
- Configures `UiAutomator2Options` for Android.
- Explicitly manages Appium session setup and teardown (`@BeforeMethod`, `@AfterMethod`).

#### [DELETE] [BasePage.java](file:///c:/Helmy/Automation/AppiumDemo/src/test/java/BasePage.java)
- Replaced by `BaseTest.java` in the proper package directory.

#### [NEW] [DemoTest.java](file:///c:/Helmy/Automation/AppiumDemo/src/test/java/tests/DemoTest.java)
- Extends `BaseTest`.
- Implements a streamlined, fully-chained test scenario using the refactored page objects.

#### [DELETE] [DemoTest.java](file:///c:/Helmy/Automation/AppiumDemo/src/test/java/DemoTest.java)
- Replaced by the package-nested version.

---

## Verification Plan

### Manual Verification
- Verify that code compiles successfully with standard Java Maven configuration.
- Validate POM structure alignment with explicit waits.
