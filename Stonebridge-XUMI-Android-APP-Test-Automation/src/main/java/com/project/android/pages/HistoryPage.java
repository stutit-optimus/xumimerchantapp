package com.project.android.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Optimus on 07/10/2018
 */

public class HistoryPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(HistoryPage.class);
    private static final String CONSTRUCTOR_INFO = "In HistoryPage() constructor";

    // Identifiers used in this page
    private By searchButtonId = By.id("search_button");
    private By merchantNameListId = By.id("com.stonebridge.xumi.android.emu:id/merchantName");
    private By receiptTitleText = By.xpath("//android.widget.TextView[@text='Receipt']");
    private By searchTextFieldId = By.id("com.stonebridge.xumi.android.emu:id/search_src_text");
    private By searchCloseButtonId = By.id("com.stonebridge.xumi.android.emu:id/search_close_btn");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseTest.java
     */
    public HistoryPage(AndroidDriver driver) {
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
        return By.id("search_button");
    }

    public boolean verifyHistoryScreenIsVisible() {
        LOGGER.info("Verifying History screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchButtonId));
        return (driver.findElement(searchButtonId).isDisplayed());
    }

    public boolean selectMerchantReceiptFromList(String merchantName) {
        LOGGER.info("Selecting merchant's receipt from the list");
        scrollTo(merchantName);
        List<WebElement> merchantNameList = driver.findElements(merchantNameListId);
        for (WebElement merchantReceipt : merchantNameList) {
            if (merchantReceipt.getText().equalsIgnoreCase(merchantName)) {
                merchantReceipt.click();
                break;
            }
        }
        return (driver.findElement(receiptTitleText).getText().equalsIgnoreCase("Receipt"));
    }

    public void clickOnSearchButton() {
        LOGGER.info("Clicking on Search button");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchButtonId));
        clickElement(searchButtonId);
    }

    public boolean searchForTransactionReceipt(String merchantName) {
        LOGGER.info("Searching for the transaction receipt using the merchant's name");
        enterInput(searchTextFieldId, merchantName);
        return (driver.findElement(merchantNameListId).getText().equalsIgnoreCase(merchantName));
    }

    public void clickOnSearchCloseButton() {
        LOGGER.info("Clicking on Search Close button");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchCloseButtonId));
        clickElement(searchCloseButtonId);
    }

    public boolean verifyMerchantReceiptList() {
        LOGGER.info("Verifying the merchant's receipt list");
        List<WebElement> merchantNameList = driver.findElements(merchantNameListId);
        return (merchantNameList.size() > 1);
    }
}

