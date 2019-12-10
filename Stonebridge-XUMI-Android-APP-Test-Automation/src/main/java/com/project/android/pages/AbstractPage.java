package com.project.android.pages;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;



/**
 * This class includes test steps that are common to all modules.
 *
 * @author Optimus
 */
public abstract class AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(AbstractPage.class);
    private static final String ELEMENT_DISABLED_ERROR = "Unable to click on specified element. Element is disabled";
    private static final String UNIQUE_ELEMENT_TEXT = "Unique Element";
    private static final String NOT_FOUND_TEXT = "Not Found on page";
    private static final String CONSTRUCTOR_INFO = "In Abstract Page Constructor";
    private static final String WAIT_FOR_PAGE_TO_LOAD_INFO = "Waiting for page to load";
    private static final String ASSERT_PAGE_IS_LOADED_INFO = "Asserting that the page is loaded";
    private static final String DOES_ELEMENT_EXIST_INFO = "Verifying that the element exists identified by - ";
    private static final String CLICK_ELEMENT_INFO = "Clicking on the element identified by ";
    private static final String WAIT_FOR_ELEMENT_INFO = "Waiting for the element identified by ";
    private static final String KEYBOARD_NOT_FOUND_INFO = "The keyboard is not present, hence handling the exception and continuing with the scripts";
    private static final String VERSION_REGEX = "\\d.\\d";
    private static final String PARSE_ANDROID_VERSION_INFO = "Parsing the android version of the device to single decimal place";
    private static final int NUMERAL_FIFTY = 50;
    private static final String expConnectionRequiredPopupText = "Your profile has not been set up yet, but an Internet connection is needed to do so.";
    private static int counter;
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    private By xumiPasswordNumberId = By.id("keyboard_button_textview");
    private By xumiPasswordTextBoxId = By.id("pin_code_step_textview");
    private By countryCodeBtnId = By.id("com.stonebridge.xumi.android.emu:id/countryViewName");
    private By connectionRequiredPopupTitleId = By.xpath("//android.widget.TextView[contains(@text,'Network connection required')]");
    private By quitButtonId = By.id("android:id/button2");
    private By settingButtonId = By.id("android:id/button1");
    private By wifiScreen = By.xpath("//android.widget.TextView[contains(@text,'Wi')]");
    private By firstNameId = By.id("firstNameEditText");
    private By lastNameId = By.id("lastNameEditText");
    private By phoneNumberTextBoxId = By.id("com.stonebridge.xumi.android.emu:id/phoneNumberView");
    private By enterNumberNextBtnId = By.id("fab");
    private By emailUser = By.id("com.stonebridge.xumi.android.emu:id/emailAddressEditText");
    private By addressLineOneId = By.id("address1EditText");
    private By addressCityId = By.id("cityInputView");
    private By addressZipCodeId = By.id("postalCodeInputView");
    private By userStateListClick = By.id("com.stonebridge.xumi.android.emu:id/stateProvinceTextView");
    private By statesListId = By.xpath("//android.widget.TextView[@text='Alaska']");
    private By xumiToolbarId = By.id("com.stonebridge.xumi.android.emu:id/toolbarWordmark");
    private By cancelButton = By.id("android:id/button2");
    private By cardNumber = By.xpath("//android.widget.EditText[@text='Card number']");
    private By wifiXpath = By.xpath("//android.widget.Switch[contains(@content-desc,'Wi-Fi')]");
    private By nextBtnId = By.id("fab");
    private By cancelNFCSetupButtonId = By.id("button2");
    private By manualSetupButtonId = By.id("addManualButton");
    private By SetPINScreenTittleId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/title");

    private By enterFirstNameTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/userInputViewEditText");
    private By EmailAddressCodeVerificationScreenTitleId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/customFontTextView2");
    private By iUnderstandBtn = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/permissionsButton");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in BaseTest.java
     */
    public AbstractPage(AndroidDriver driver) {
        LOGGER.info(CONSTRUCTOR_INFO);
        this.driver = driver;
        wait = new WebDriverWait(driver, NUMERAL_FIFTY);
        waitForPageToLoad();
        assertPageIsLoaded();
    }

    /**
     * This method parses the android version to single decimal place
     *
     * @param version Version of the android device
     */
    public static String parseAndroidVersion(String version) {
        LOGGER.info(PARSE_ANDROID_VERSION_INFO);
        String androidVer = null;
        Matcher versionMatcher = Pattern.compile(VERSION_REGEX).matcher(version);
        if (versionMatcher.find()) {
            androidVer = versionMatcher.group(0);
        }
        return androidVer;
    }

    protected abstract By getUniqueElement();

    /**
     * This method waits for the page to load and checks for presence of element
     * of the particular page
     */
    private void waitForPageToLoad() {
        LOGGER.info(WAIT_FOR_PAGE_TO_LOAD_INFO);
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(getUniqueElement()));
    }

    /**
     * This method verifies that the page is loaded
     */
    private void assertPageIsLoaded() {
        LOGGER.info(ASSERT_PAGE_IS_LOADED_INFO);
        Assert.assertTrue(UNIQUE_ELEMENT_TEXT.concat(getUniqueElement().toString()).
                        concat(NOT_FOUND_TEXT).concat(this.getClass().getSimpleName()),
                driver.findElements(getUniqueElement()).size() > 0);
    }

    /**
     * This element verifies the existence of element
     *
     * @param locator locator of the element
     * @return true if element exists
     */
    public boolean doesElementExist(By locator) {
        LOGGER.info(DOES_ELEMENT_EXIST_INFO + locator);
        return (driver.findElements(locator).size() != 0);
    }

    /**
     * This method enters input in the field specified in the parameters
     *
     * @param identifier locator of the text element
     * @param input      text to be entered in the text element
     */
    protected void enterInput(By identifier, String input) {
        LOGGER.info("Entering " + input + " in the field identified by " + identifier);
        wait.until(ExpectedConditions.presenceOfElementLocated(identifier));
        WebElement element = driver.findElement(identifier);
        if (element.isEnabled()) {
            element.click();
            element.clear();
            element.sendKeys(input);
            try {
                driver.hideKeyboard();
            } catch (WebDriverException exception) {
                LOGGER.info(KEYBOARD_NOT_FOUND_INFO);
            }
        } else {
            LOGGER.error("Unable to enter input. Text field is disabled");
        }

    }

    /**
     * This method clicks on the element with id given in parameter
     *
     * @param identifier locator of the element
     */
    protected void clickElement(By identifier) {
        LOGGER.info(CLICK_ELEMENT_INFO + identifier);
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
        WebElement element = driver.findElement(identifier);
        if (element.isEnabled()) {
            element.click();
        } else {
            LOGGER.error(ELEMENT_DISABLED_ERROR);
        }
    }

    /**
     * Waiting for element to be visible
     *
     * @param identifier locator of the element
     */
    public void waitForElement(By identifier) {
        LOGGER.info(WAIT_FOR_ELEMENT_INFO + identifier + " to be visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
    }

    /**
     * Waiting for element to be visible
     *
     * @param identifier locator of the element
     */
    public void waitForElementToBeEnabled(By identifier) {
        LOGGER.info(WAIT_FOR_ELEMENT_INFO + identifier + " to be enabled");
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
        for (int i = 0; i < 3; i++) {
            WebElement element = driver.findElement(identifier);
            if (element.isEnabled()) {
                break;
            } else {
                try {
                    driver.runAppInBackground(1);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clickBackButton() {
        LOGGER.info("Clicking BACK button/key");
        driver.navigate().back();
    }

    public void clickNextButton() {
        LOGGER.info("Clicking on Next button");
        wait.until(ExpectedConditions.presenceOfElementLocated(nextBtnId));
        clickElement(nextBtnId);
    }

    public void clickIUnderstandPermissionButton() {
        LOGGER.info("Clicking on 'I Understand' button");
        wait.until(ExpectedConditions.presenceOfElementLocated(iUnderstandBtn));
        clickElement(iUnderstandBtn);
    }

    public void clickCountryCodeDropDown() {
        waitForElement(countryCodeBtnId);
        clickElement(countryCodeBtnId);
    }

    public boolean verifyConnectionRequiredPopupIsDisplayed() {
        LOGGER.info("Verifying 'Connection Required' popup is displayed");
        return (doesElementExist(connectionRequiredPopupTitleId));
    }

    public void clickOnQuitButton() {
        LOGGER.info("Clicking on QUIT button");
        clickElement(quitButtonId);
    }

    public void clickOnSettingsButton() {
        LOGGER.info("Clicking on SETTINGS button");
        clickElement(settingButtonId);
    }

    public boolean verifyWifiSettingsScreenIsDisplayed() {
        LOGGER.info("Verifying Wifi settings screen is displayed");
        return (doesElementExist(wifiScreen));
    }

    public void takeScreenshot(String screenshotName) {
        counter = counter + 1;
        String fullFileName = "screenshots/" + getScreenshotsCounter() + "_" + screenshotName + ".png";
        screenshot(fullFileName);
    }

    private File screenshot(String name) {
        System.out.println("Taking screenshot...");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {

            File testScreenshot = new File(name);
            FileUtils.copyFile(scrFile, testScreenshot);
            System.out.println("Screenshot stored to " + testScreenshot.getAbsolutePath());

            return testScreenshot;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getScreenshotsCounter() {
        if (counter < 10) {
            return "0" + counter;
        } else {
            return String.valueOf(counter);
        }
    }

    /**
     * SCrolling to the element specified by text
     *
     * @param text text till application will be scrolled
     */
    public void scrollTo(String text) {
        try {
            driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("
                    + "new UiSelector().textContains(\"" + text + "\"));"));
        } catch (Exception exception) {
            LOGGER.info(exception.getMessage());
        }
    }

    public void enterXumiPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xumiPasswordNumberId));
        List<WebElement> numbers = driver.findElements(xumiPasswordNumberId);
        for (int index = 1; index <= 6; index++) {
            for (WebElement ele : numbers) {
                if (ele.getText().equalsIgnoreCase(String.valueOf(index))) {
                    ele.click();
                    break;
                }
            }
        }
    }

    public boolean verifyPinScreenIsVisible() {
        LOGGER.info("Verify that the pin screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(SetPINScreenTittleId));
        return (driver.findElement(SetPINScreenTittleId).isDisplayed());
    }

    public boolean verifyEmailAddressCodeVerificationScreenIsVisible() {
        LOGGER.info("Verify that the pin screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(EmailAddressCodeVerificationScreenTitleId));
        return (driver.findElement(EmailAddressCodeVerificationScreenTitleId).isDisplayed());
    }

    public boolean verifyNameScreenIsVisible() {
        LOGGER.info("Verify that the pin screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(enterFirstNameTextId));
        return (driver.findElement(enterFirstNameTextId).isDisplayed());
    }

    public void enterPhoneNumber(String phoneNumber, boolean clickNext) throws InterruptedException {
        LOGGER.info("Entering the phone number");
        driver.runAppInBackground(1);
        wait.until(ExpectedConditions.presenceOfElementLocated(phoneNumberTextBoxId));
        enterInput(phoneNumberTextBoxId, phoneNumber);
        if (clickNext) {
            waitForElementToBeEnabled(enterNumberNextBtnId);
            clickElement(enterNumberNextBtnId);
        }
    }

    public void enterUserName(String firstName, String lastName, boolean clickNext) {
        LOGGER.info("Entering the user Name");
        wait.until(ExpectedConditions.presenceOfElementLocated(firstNameId));
        enterInput(firstNameId, firstName);
        wait.until(ExpectedConditions.presenceOfElementLocated(lastNameId));
        enterInput(lastNameId, lastName);
        if (clickNext) {
            waitForElementToBeEnabled(enterNumberNextBtnId);
            clickElement(enterNumberNextBtnId);
        }
    }

    public UserProfilePage enterEmailAddress(String emailAddress, boolean clickNext) {
        LOGGER.info("Entering the email address");
        wait.until(ExpectedConditions.presenceOfElementLocated(emailUser));
        if (emailAddress == null) {
            int value = (int) Math.floor(Math.random() * 90000 + 10000);
            emailAddress = "autoxumi" + value + "@xumi.com";
        }
        enterInput(emailUser, emailAddress);
        if (clickNext) {
            waitForElementToBeEnabled(enterNumberNextBtnId);
            clickElement(enterNumberNextBtnId);
            return new UserProfilePage(driver);
        } else {
            return null;
        }
    }

    public void enterUserAddress(String userAddress, String city, String zipCode, String stateName, boolean clickNext) {
        LOGGER.info("Entering the user address");
        wait.until(ExpectedConditions.presenceOfElementLocated(addressLineOneId));
        enterInput(addressLineOneId, userAddress);
        enterInput(addressCityId, city);

        clickElement(userStateListClick);
        List<WebElement> statesList = driver.findElements(statesListId);
        for (WebElement state : statesList) {
            if (state.getText().trim().equalsIgnoreCase(stateName)) {
                state.click();
                break;
            }
        }
        enterInput(addressZipCodeId, zipCode);
        if (clickNext) {
            driver.runAppInBackground(1);
            waitForElementToBeEnabled(enterNumberNextBtnId);
            clickElement(enterNumberNextBtnId);
        }
    }

    public void scrollDownPage() {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * .8);
        int endY = (int) (size.height * .2);
        TouchAction ta = new TouchAction(driver);
        driver.swipe(startX, startY, startX, endY, 5000);
    }

    public boolean verifyAppClosed() {
        LOGGER.info("Verifying application is closed");
        return (doesElementExist(xumiToolbarId));
    }

    public void clickOnCancelButtonOnAddNewFingerprintScreen() {
        clickElement(cancelButton);
    }

    public boolean verifyAddNewCreditCardScreenIsVisible() {
        LOGGER.info("Verifying 'Add Credit Card' screen is visible");
        if (doesElementExist(cancelNFCSetupButtonId)) {
            clickElement(cancelNFCSetupButtonId);
            driver.runAppInBackground(2);
            clickElement(manualSetupButtonId);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(cardNumber));
        return (driver.findElement(cardNumber).isDisplayed());
    }

    public void turnOffWifi() {
        LOGGER.info("Turning OFF Wifi connection");
        driver.openNotifications();
        wait.until(ExpectedConditions.presenceOfElementLocated(wifiXpath));
        if (driver.findElement(wifiXpath).getText().equalsIgnoreCase("On")) {
            clickElement(wifiXpath);
        }
        driver.navigate().back();
        driver.runAppInBackground(1);
    }

    public void turnOnWifi() {
        LOGGER.info("Turning ON Wifi connection");
        driver.openNotifications();
        wait.until(ExpectedConditions.presenceOfElementLocated(wifiXpath));
        if (driver.findElement(wifiXpath).getText().equalsIgnoreCase("Off")) {
            clickElement(wifiXpath);
        }
        driver.navigate().back();
    }

    public String getRandomNumberInRange() {
        LOGGER.info(String.format("Get random numbers"));
        Random r = new Random();
        return Integer.toString(r.nextInt(99999));
    }

    public void scrollAndClick(String text) {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + text + "\").instance(0))").click();

    }

//    public void horizontalScroll() {
//        Dimension size = driver.manage().window().getSize();
//        int x_start = (int) (size.width * 0.60);
//        int x_end = (int) (size.width * 0.30);
//        int y = 130;
//        driver.swipe(x_start, y, x_end, y, 4000);

    }






