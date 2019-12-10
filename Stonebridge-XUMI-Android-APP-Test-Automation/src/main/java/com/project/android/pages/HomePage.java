package com.project.android.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;

/**
 * Created by Optimus on 06/25/2018
 */

public class HomePage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(HomePage.class);
    private static final String CONSTRUCTOR_INFO = "In HomePage() constructor";

    // Identifiers used in this page
    // Ids
    private By readyYourPaymentTextId = By.id("com.stonebridge.xumi.android.emu:id/homeMainTextView");
    private By homePinId = By.id("com.stonebridge.xumi.android.emu:id/homePinView");
    private By hamburgerId = By.id("com.stonebridge.xumi.android.emu:id/action_open_home_nav");
    private By userProfileButton = By.xpath("//android.widget.TextView[@text='User Profile']");
    private By termsAndConditionsButtonId = By.id("com.stonebridge.xumi.android.emu:id/navTerms");
    private By aboutButtonId = By.id("com.stonebridge.xumi.android.emu:id/navAbout");
    private By userProfileScreenId = By.id("com.stonebridge.xumi.android.emu:id/toolbarTitle");
    private By aboutVersionId = By.id("com.stonebridge.xumi.android.emu:id/versionHeader");
    private By historyButtonId = By.id("com.stonebridge.xumi.android.emu:id/historyButton");
    private By myWalletButton = By.xpath("//android.widget.TextView[@text='My Wallet']");
    private By addButtonId = By.id("com.stonebridge.xumi.android.emu:id/fab");
    private By hamburgerMenuId = By.id("com.stonebridge.xumi.android.emu:id/sideNavItems");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseTest.java
     */
    public HomePage(AndroidDriver driver) {
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
        return By.id("homeMainTextView");
    }

    public boolean verifyHomePageIsVisible() {
        LOGGER.info("Verifying Home page is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(readyYourPaymentTextId));
        return (driver.findElement(readyYourPaymentTextId).isDisplayed());
    }

    public void clickOnHomePin() {
        LOGGER.info("Clicking on Home Pin");
        wait.until(ExpectedConditions.presenceOfElementLocated(homePinId));
        clickElement(homePinId);
    }

    public void clickOnHamburger() {
        LOGGER.info("Clicking on hamburger");
        wait.until(ExpectedConditions.presenceOfElementLocated(hamburgerId));
        clickElement(hamburgerId);
    }

    public void clickOnUserProfileMenuOption() {
        LOGGER.info("Clicking on 'User Profile' menu option");
        wait.until(ExpectedConditions.presenceOfElementLocated(userProfileButton));
        clickElement(userProfileButton);
    }

    public void clickOnMyWalletMenuOption() {
        LOGGER.info("Clicking on 'My Wallet' menu option");
        wait.until(ExpectedConditions.presenceOfElementLocated(myWalletButton));
        clickElement(myWalletButton);
    }


    public void clickOnAboutButton() {
        LOGGER.info("Clicking on 'About' button");
        wait.until(ExpectedConditions.presenceOfElementLocated(aboutButtonId));
        clickElement(aboutButtonId);
    }


    public boolean verifyAboutScreenIsVisible() {
        LOGGER.info("Verifying 'About' screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(aboutVersionId));
        return (driver.findElement(aboutVersionId).isDisplayed());
    }

    public void clickOnHistoryButton() {
        LOGGER.info("Clicking on 'History' button");
        wait.until(ExpectedConditions.presenceOfElementLocated(historyButtonId));
        clickElement(historyButtonId);
    }

    public boolean verifyMyWalletScreenIsVisible() {
        LOGGER.info("Verifying 'My Wallet' screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(addButtonId));
        return (driver.findElement(addButtonId).isDisplayed());
    }

    public void clickOnAddButton() {
        LOGGER.info("Clicking on '+' button");
        wait.until(ExpectedConditions.presenceOfElementLocated(addButtonId));
        clickElement(addButtonId);
    }

    public boolean verifyHamburgerMenuScreenIsVisible() {
        LOGGER.info("Verifying hamburger menu screen is visible on");
        return (doesElementExist(hamburgerMenuId));
    }
}
