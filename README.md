# 📱 Appium Mobile Automation Project

[![Appium](https://img.shields.io/badge/Appium-3.x-orange.svg?style=for-the-badge&logo=appium)](https://appium.io/)
[![Java](https://img.shields.io/badge/Java-21-blue.svg?style=for-the-badge&logo=openjdk)](https://oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-red.svg?style=for-the-badge&logo=apachemaven)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-Framework-yellow.svg?style=for-the-badge&logo=testing)](https://testng.org/)
[![Android](https://img.shields.io/badge/Platform-Android_15-green.svg?style=for-the-badge&logo=android)](https://developer.android.com/)

A modern, highly-structured mobile test automation framework for Android using **Appium 3 / W3C specifications**, **Java 21**, **Maven**, and **TestNG**. This project automates a complete end-to-end shopping scenario on the SauceLabs Mobile Sample Application.

---

## 🎥 Execution Recording

Below is the video recording showcasing the full automated test execution on the Android emulator:

https://github.com/user-attachments/assets/0c46b0fa-6284-46f1-864d-931f23a7632d


<div align="center">
  <video src="https://github.com/user-attachments/assets/0c46b0fa-6284-46f1-864d-931f23a7632d" width="700" controls poster="apps/Android.SauceLabs.Mobile.Sample.app.2.6.0.apk">
    Your browser does not support the video tag. You can view the file directly: <a href="recording.mp4">recording.mp4</a>
  </video>
</div>

> [!IMPORTANT]
> **To make the video play inline on GitHub:**
> 1. Create a draft Issue, Pull Request, or Discussion on your repository.
> 2. Drag and drop your `recording.mp4` file into the description/comment box.
> 3. Once uploaded, GitHub will generate a URL (e.g., `https://github.com/user-attachments/assets/xxxxxxxx`).
> 4. Replace `YOUR_GITHUB_UPLOADED_VIDEO_URL` in the `src` attribute of the `<video>` tag above with that generated URL.

---

## 🛠️ Key Enhancements & Modernizations

This codebase has been fully refactored and modernized to align with clean coding standards and advanced test automation design patterns:

### 1. Robust Page Object Model (POM) Restructuring
- **Decoupled Packages**: Screens and tests are separated into standalone packages:
  - `screens` under `src/main/java/screens/` holds screen objects.
  - `tests` under `src/test/java/tests/` holds test files.
  - `base` under `src/test/java/base/` holds test-level configurations.
- **Shared Layout Elements**: Common header objects (Cart Badge, Side Menu, Logout Button) are managed inside the [BaseScreen](src/main/java/screens/BaseScreen.java) superclass, making them inherited by all page objects.

### 2. Flawless Driver Configuration (Appium 3 & W3C Aligned)
- **Modern Options Pattern**: Replaced the deprecated `DesiredCapabilities` with the modern `UiAutomator2Options` driver builder.
- **Zero Implicit Waits**: Set implicit wait timeouts to `0` seconds to eliminate conflicts with explicit waits.
- **Session Lifecycle Safety**: Added `@BeforeMethod` and `@AfterMethod(alwaysRun = true)` hooks in [BaseTest](src/test/java/base/BaseTest.java) to guarantee clean setup and tear down of the Appium sessions, avoiding zombie sessions on the Appium server.

### 3. Reliable Explicit Wait Mechanism
- **Explicit Waits Only**: Integrated `WebDriverWait` with a default 10-second window in [BaseScreen](src/main/java/screens/BaseScreen.java).
- **Safe Interaction Wrappers**: Wrapped all action methods (`click`, `sendKeys`, `getText`, and visibility/clickability checks) inside safe handlers that intercept elements only after they satisfy wait conditions.
- **Null-Safety Guards**: Implemented strict null checks (`Objects.requireNonNull`) across all parameters (locators, text fields, and driver instances) to prevent runtime `NullPointerException` failures.

### 4. Fluent Method Chaining (DSL)
- Screen interaction methods return the appropriate screen object instance (either `this` or a newly instantiated screen).
- Enables writing intuitive, fluent, and highly readable end-to-end test scenarios.

```java
// Example from DemoTest.java
new LoginPage(driver)
    .successLogin("standard_user", "secret_sauce")
    .addProduct1()
    .clickAddToCartButton()
    .clickCartBadge()
    .clickOnContinueShopping()
    ...
```

---

## 📁 Project Directory Structure

```text
AppiumDemo/
├── apps/
│   └── Android.SauceLabs.Mobile.Sample.app.2.6.0.apk  # Target Android Application (SauceLabs Sample)
├── src/
│   ├── main/
│   │   └── java/
│   │       └── screens/                               # Page Object Screens (POM)
│   │           ├── BaseScreen.java                   # Common Parent Screen (Header Actions, Explicit Waits)
│   │           ├── LoginPage.java                    # Login screen locators & actions
│   │           ├── HomePage.java                     # Main product catalog screen
│   │           ├── ProductPage.java                  # Product details screen
│   │           ├── CartPage.java                     # Cart details screen
│   │           ├── CheckOutPage.java                 # Checkout information screen
│   │           ├── CheckOutReviewPage.java           # Order overview screen
│   │           └── CheckOutCompletePage.java         # Confirmation success screen
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java                     # Configuration, driver setup (W3C), teardown
│           └── tests/
│               └── DemoTest.java                     # End-to-End Test Suite (Fluent DSL)
├── pom.xml                                            # Maven configuration & dependencies (Java 21, Appium 10.x, Selenium 4.x)
└── README.md                                          # Project documentation
```

---

## ⚙️ Getting Started & Local Setup

To run this project locally, ensure you meet the following system requirements and configurations.

### 📋 Prerequisites
1. **Java Development Kit (JDK 21)** or higher.
2. **Apache Maven** installed and added to your system path.
3. **Android Studio** (with an Android Emulator configured, e.g., `Pixel 9 Pro XL API 35` running Android 15).
4. **Appium Server 2.x** installed globally via npm:
   ```bash
   npm install -g appium
   appium driver install uiautomator2
   ```

### 🚀 Execution Steps

1. **Launch the Appium Server**:
   ```bash
   appium
   ```
   *By default, the server runs on `http://127.0.0.1:4723/`.*

2. **Start the Android Emulator**:
   Launch your emulator from Android Studio AVD Manager. Ensure the device name matches the configuration in [BaseTest.java](src/test/java/base/BaseTest.java#L34):
   - Device Name: `Pixel 9 Pro XL API 35`
   - Platform Version: `15`

3. **Run the Tests**:
   Open a terminal in the project root directory and execute:
   ```bash
   mvn clean test
   ```
