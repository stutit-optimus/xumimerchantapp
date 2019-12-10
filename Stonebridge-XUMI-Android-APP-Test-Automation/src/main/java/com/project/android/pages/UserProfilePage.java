package com.project.android.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.appium.java_client.android.AndroidDriver;

public class UserProfilePage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(UserProfilePage.class);
    private static final String CONSTRUCTOR_INFO = "In User Profile page constructor";

    private By usePrimaryAddressCheckboxId = By.id("com.stonebridge.xumi.android.emu:id/primaryAddressCheckbox");
    private By addressLineOneId = By.id("address1EditText");
    private By addressCityId = By.id("cityInputView");
    //      private By addressCityId = By.xpath("//android.widget.EditText[@text='City']");
    private By addressZipCodeId = By.id("postalCodeInputView");
    //      private By addressZipCodeId = By.xpath("//android.widget.EditText[@text='ZIP']");
    // Ids
    private By enterNumberNextBtnId = By.id("com.stonebridge.xumi.android.emu:id/fab");
    private By userStateListClick = By.id("com.stonebridge.xumi.android.emu:id/stateProvinceTextView");

    // Identifiers used in this page
    // Ids
    private By countryCodeBtnId = By.id("com.stonebridge.xumi.android.emu:id/countryViewName");
    private By toolbarTitleId = By.id("toolbarTitle");
    private By countryListItemNameId = By.id("countryListItemName");
    private By stateListItemNameId = By.id("stateProvinceListItemName");
    private By cardExpirationDatePickerId = By.id("expiration_picker");
    private By expiryDateFieldId = By.id("creditCardExpiryDateView");
    private By datePickerCancelBtnId = By.id("cancel_button");
    private By datePickerOkBtnId = By.id("done_button");
    //Class Name
    private By buttonClassName = By.className("android.widget.Button");
    private By editTextClassName = By.className("android.widget.EditText");
    // Xpaths
    private By cardNumber = By.xpath("//android.widget.EditText[@text='Card number']");
    private By invalidCardNumberErrorMessage = By.xpath("//android.widget.TextView[@text='INVALID CARD NUMBER']");
    private By expDateMonth = By.xpath("//android.widget.Button[@text='JAN']");
    private By expDateyear = By.xpath("//android.widget.Button[@text='2']");
    private By expDateyear2 = By.xpath("//android.widget.Button[@text='9']");
    private By cvc = By.xpath("//android.widget.EditText[@text='CVC code']");
    private By cardHolderName = By.xpath("//android.widget.EditText[@text='Cardholder name']");
    private By addressLineOneXpath = By.xpath("//android.widget.EditText[@text='Address']");
    private By userProfileScreenId = By.id("com.stonebridge.xumi.android.emu:id/toolbarTitle");
    private By saveButtonId = By.id("com.stonebridge.xumi.android.emu:id/customFab");
    private By confirmationPopupId = By.id("com.stonebridge.xumi.android.emu:id/alertTitle");
    private By cancelButtonId = By.id("android:id/button2");
    private By discardChangesButtonId = By.id("android:id/button1");
    private By userFirstNameId = By.id("com.stonebridge.xumi.android.emu:id/firstNameEditText");
    private By userFirstNameTextId = By.id("com.stonebridge.xumi.android.emu:id/userInputViewEditText");
    private By cancelNFCSetupButtonId = By.id("button2");
    private By manualSetupButtonId = By.id("addManualButton");
    private By youAreAllSetTextXpath = By.xpath("//android.widget.TextView[contains(@text,\"You're all set\")]");
    private By thatsItTextXpath = By.xpath("//android.widget.TextView[contains(@text,\"That's it\")]");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseTest.java
     */
    public UserProfilePage(AndroidDriver driver) {
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
        return By.id("address1EditText");
    }


    public void addCreditCard(String creditCardNumber, String creditCardCvc, String userName, boolean clickNext) throws InterruptedException {
        LOGGER.info("Adding a new credit card");
        if(doesElementExist(cancelNFCSetupButtonId)){
            clickElement(cancelNFCSetupButtonId);
            driver.runAppInBackground(1);
            clickElement(manualSetupButtonId);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(cardNumber));
        enterInput(cardNumber, creditCardNumber);
        clickElement(expiryDateFieldId);
        clickElement(expDateMonth);
        clickElement(expDateyear);
        waitForElementToBeEnabled(expDateyear2);
        clickElement(expDateyear2);
        waitForElementToBeEnabled(datePickerOkBtnId);
        clickElement(datePickerOkBtnId);
        enterInput(cvc, creditCardCvc);
        enterInput(cardHolderName, userName);
        if (clickNext) {
            waitForElementToBeEnabled(enterNumberNextBtnId);
            clickElement(enterNumberNextBtnId);
        }
        if(doesElementExist(youAreAllSetTextXpath)){
            wait.until(ExpectedConditions.presenceOfElementLocated(youAreAllSetTextXpath));
            clickNextButton();
            wait.until(ExpectedConditions.presenceOfElementLocated(thatsItTextXpath));
            clickNextButton();
        }
    }

    public boolean verifyAddressScreenIsVisible() {
        LOGGER.info("Verifying 'What's your address?' screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(userStateListClick));
        return (driver.findElement(userStateListClick).isDisplayed());
    }

    public boolean verifyInvalidCardDetailsErrorMessageIsVisible() {
        LOGGER.info("verify invalid card details error message");
        wait.until(ExpectedConditions.presenceOfElementLocated(invalidCardNumberErrorMessage));
        return (driver.findElement(invalidCardNumberErrorMessage).isDisplayed());
    }


    public boolean verifyCountryTitlePresent() {
        waitForElement(toolbarTitleId);
        String elementText = driver.findElement(toolbarTitleId).getText();
        return elementText.equalsIgnoreCase("Country");
    }

    public void selectCountry(String countryName) {
        scrollTo(countryName);
        List<WebElement> countryNameList = driver.findElements(countryListItemNameId);
        for (WebElement countryItem : countryNameList) {
            if (countryItem.getText().equalsIgnoreCase(countryName)) {
                countryItem.click();
                break;
            }
        }
    }

    public void clickOnUsePrimaryAddressCheckbox() {
        LOGGER.info("Clicking 'Use primary address' checkbox");
        clickElement(usePrimaryAddressCheckboxId);
    }

    public boolean verifyUsePrimaryAddressCheckbox() {
        LOGGER.info("Checking the status of 'Use primary address' checkbox");
        String checkBoxStatus = driver.findElement(usePrimaryAddressCheckboxId).getAttribute("checked");
        if (checkBoxStatus.equalsIgnoreCase("true")) {
            LOGGER.info("'Use primary address' checkbox is checked");
            return true;
        } else {
            LOGGER.info("'Use primary address' checkbox is unchecked");
            return false;
        }
    }

    public boolean verifyAddressFieldIsVisible() {
        LOGGER.info("Checking the address field is visible on 'Add a new credit card' screen");
        if (doesElementExist(addressLineOneXpath)) {
            LOGGER.info("Address field is visible on 'Add a new credit card' screen");
            return true;
        } else {
            LOGGER.info("Address field is not visible on 'Add a new credit card' screen");
            return false;
        }
    }

    public boolean verifyComponentsOfAddressPage() {
        boolean stateListPresent = true;
        boolean addressTextBoxPresent = doesElementExist(addressLineOneId);
        boolean cityTextBoxPresent = doesElementExist(addressCityId);
        boolean zipCodeTextBoxPresent = doesElementExist(addressZipCodeId);
        clickCountryCodeDropDown();
        boolean countryCodeListPresent = verifyCountryTitlePresent();
        clickBackButton();
        if (doesElementExist(userStateListClick)) {
            clickElement(userStateListClick);
            stateListPresent = doesElementExist(stateListItemNameId);
        }
        return (addressTextBoxPresent && cityTextBoxPresent && zipCodeTextBoxPresent && countryCodeListPresent && stateListPresent);
    }

    public boolean verifyDatePickerDisplayedOnCardExpirationField() {
        LOGGER.info("Verify if date picker is present");
        waitForElement(expiryDateFieldId);
        clickElement(expiryDateFieldId);
        return doesElementExist(cardExpirationDatePickerId);
    }

    public void enterCreditCardExpirationYear() {
        SimpleDateFormat formatter = new SimpleDateFormat("yy");
        String year = formatter.format(Calendar.getInstance().getTime());
        LOGGER.info(formatter.format(Calendar.getInstance().getTime()));
        List<WebElement> yearDigits = driver.findElements(buttonClassName);
        for (int index = 0; index < year.length(); index++) {
            for (WebElement digit : yearDigits) {
                if (digit.getText().trim().equalsIgnoreCase(String.valueOf(year.charAt(index)))) {
                    digit.click();
                    break;
                }
            }
        }
    }


    public boolean verifyCurrentYearIsEnabled() {
        boolean flag = true;
        SimpleDateFormat formatter = new SimpleDateFormat("yy");
        String year = formatter.format(Calendar.getInstance().getTime());
        Calendar calendar = Calendar.getInstance();
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        List<WebElement> yearDigits = driver.findElements(buttonClassName);
        for (WebElement digit : yearDigits) {
            if (digit.getText().trim().equalsIgnoreCase(String.valueOf(year.charAt(0)))) {
                digit.click();
                break;
            }
        }
        for (WebElement digit : yearDigits) {
            if(monthName.equalsIgnoreCase("Jan")){
                int previousYearInt = Integer.parseInt(formatter.format(Calendar.getInstance().getTime())) - 1;
                String previousYear = Integer.toString(previousYearInt);
                if(digit.getText().trim().equalsIgnoreCase(String.valueOf(previousYear.charAt(1)))){
                    flag = digit.isEnabled();
                }
            }
            else {
                if (digit.getText().trim().equalsIgnoreCase(String.valueOf(year.charAt(1)))) {
                    flag = digit.isEnabled();
                }
            }
        }
        return flag;
    }

    public void enterCreditCardExpirationMonth(boolean valid) {
        Calendar calendar = Calendar.getInstance();
        int index = 1;
        if (!valid)
            index = -1;
        calendar.add(Calendar.MONTH, index);
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
        LOGGER.info(monthName);
        List<WebElement> months = driver.findElements(buttonClassName);
        for (WebElement month : months) {
            if (month.getText().trim().equalsIgnoreCase(monthName.trim())) {
                month.click();
                break;
            }
        }
    }

    public boolean verifyUserIsOnYearSelectionPicker() {
        List<WebElement> months = driver.findElements(buttonClassName);
        return months.get(0).getText().equalsIgnoreCase("1");
    }

    public void clickCancelOnDatePicker() {
        LOGGER.info("Clicking cancel on the date picker screen");
        waitForElementToBeEnabled(datePickerCancelBtnId);
        clickElement(datePickerCancelBtnId);
    }

    public void clickOkOnDatePicker() {
        LOGGER.info("Clicking ok on the date picker screen");
        waitForElementToBeEnabled(datePickerOkBtnId);
        clickElement(datePickerOkBtnId);
    }

    public boolean verifyDatePickerFieldIsEmpty() {
        LOGGER.info("Verify if date picker is present");
        waitForElement(expiryDateFieldId);
        WebElement datePickerEle = driver.findElement(expiryDateFieldId);
        String datePickerText = datePickerEle.findElement(editTextClassName).getText().trim();
        return (datePickerText.equalsIgnoreCase("MM / YY"));
    }

    public boolean verifyUserProfileScreenIsVisible() {
        LOGGER.info("Verifying the User Profile screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(userProfileScreenId));
        String actUserProfileTitleText = driver.findElement(userProfileScreenId).getText();
        return (actUserProfileTitleText.equalsIgnoreCase("User Profile"));
    }

    public void clickSaveUserProfileButton() {
        LOGGER.info("Clicking on Save button on User Profile screen");
        if (!doesElementExist(saveButtonId)) {
            driver.findElement(addressLineOneId).click();
            driver.hideKeyboard();
            scrollDownPage();
        }
        clickElement(saveButtonId);
    }

    public boolean verifyConfirmationPopupOnUserProfileScreenIsVisible() {
        LOGGER.info("Verifying confirmation popup is visible on User Profile screen");
        return doesElementExist(confirmationPopupId);
    }

    public void clickCancelButtonOnConfirmationPopup() {
        LOGGER.info("Clicking on Cancel button confirmation popup on User Profile screen");
        clickElement(cancelButtonId);
    }

    public void clickDiscardChangesButtonOnConfirmationPopup() {
        LOGGER.info("Clicking on Discard Changes button on User Profile screen");
        clickElement(discardChangesButtonId);
    }

    public boolean verifyChangesSavedOnUSerProfileScreen(String newUserFirstName) {
        LOGGER.info("Verifying the changes are saved or not on User Profile screen");
        String actUserFirstName = driver.findElement(userFirstNameTextId).getText();
        return (actUserFirstName.equalsIgnoreCase(newUserFirstName));
    }

    public void clickStateDropDown() {
        scrollDownPage();
        clickElement(userStateListClick);
    }

    public boolean selectAndVerifyState(String stateName) {
        scrollTo(stateName);
        List<WebElement> stateNameList = driver.findElements(stateListItemNameId);
        for (WebElement stateItem : stateNameList) {
            if (stateItem.getText().equalsIgnoreCase(stateName)) {
                stateItem.click();
                break;
            }
        }
        String selectedStateName = driver.findElement(userStateListClick).getText();
        return (selectedStateName.equalsIgnoreCase("CA"));
    }

    public boolean verifyStateDropDownIsVisible() {
        LOGGER.info("Verifying the State drop down is visible");
        return (doesElementExist(userStateListClick));
    }
}


