package com.project.android.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Optimus on 06/25/2018
 */

public class PaymentPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(PaymentPage.class);
    private static final String CONSTRUCTOR_INFO = "In PaymentPage() constructor";

    // Identifiers used in this page
    // Ids
    private By tapTerminalToPayTextId = By.id("com.stonebridge.xumi.android.emu:id/paymentSelectPrompt");
    private By offlineSecurityWarningId = By.id("com.stonebridge.xumi.android.emu:id/snackbar_text");
    private By registeredCardId = By.id("com.stonebridge.xumi.android.emu:id/placeholderNum");
    private By wifiXpath = By.xpath("//android.widget.Switch[contains(@content-desc,'Wi-Fi')]");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseTest.java
     */
    public PaymentPage(AndroidDriver driver) {
        super(driver);
        LOGGER.info(CONSTRUCTOR_INFO);
    }

    /**
     * This method returns unique element of current page to abstract page to
     * verify that the page has been loaded. The identifier returned in this
     * method is being used in the constructor of AbstractPage, hence cannot be
     * initialized in this page.
     */
    @Override
    protected By getUniqueElement() {
        return By.id("paymentSelectPrompt");
    }

    public boolean verifyPaymentScreenIsVisible() {
        LOGGER.info("Verifying Payment screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(tapTerminalToPayTextId));
        return (driver.findElement(tapTerminalToPayTextId).isDisplayed());
    }

    public boolean verifySecurityWarningWhenOffline() {
        LOGGER.info("Verifying security warning is displayed when offline");
        driver.openNotifications();
        wait.until(ExpectedConditions.presenceOfElementLocated(wifiXpath));
        if (driver.findElement(wifiXpath).getText().equalsIgnoreCase("On")) {
            clickElement(wifiXpath);
        }
        driver.navigate().back();
        return (driver.findElement(offlineSecurityWarningId).isDisplayed());
    }

    public boolean verifyRegisteredCreditCardIsDisplayed() {
        LOGGER.info("Verifying the registered credit card is displayed on Payment Card Selection screen");
        wait.until(ExpectedConditions.presenceOfElementLocated(registeredCardId));
        String actCardNumber = driver.findElement(registeredCardId).getText();
        return (actCardNumber.contains("4242"));
    }
}
