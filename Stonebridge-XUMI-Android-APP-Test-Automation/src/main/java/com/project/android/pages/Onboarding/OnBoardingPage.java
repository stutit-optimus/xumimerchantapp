package com.project.android.pages.Onboarding;

import com.project.android.pages.AbstractPage;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.jetty.html.Script;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.util.List;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;


/**
 * This class includes methods that are used to perform actions on Login page
 *
 * @author Optimus
 */
public class OnBoardingPage extends AbstractPage {
    private static final Logger LOGGER = Logger.getLogger(OnBoardingPage.class);
    private static final String CONSTRUCTOR_INFO = "In Splash page constructor";
    private static final String expManagePhoneCallsText = "Allow Xumi to make and manage phone calls?";
    private static final String expDeviceLocationText = "Allow Xumi to access this device's location?";

    // Identifiers used in this page
    // Ids
    private By enterPinId = By.id("com.stonebridge.xumi.android.emu:id/container");
    private By enterNumberNextBtnId = By.id("com.stonebridge.xumi.android.emu:id/fab");
    private By letsGetStartedBtnId = By.id("splashStartButton");
    private By verifyYourFingerprintTextId = By.id("com.stonebridge.xumi.android.xumimerchant.noemu:id/fingerprintMainText");
    private By xumiPasswordTextBoxId = By.id("pin_code_step_textview");
    private By xumiPinCodeViewId = By.id("pin_code_view");
    private By xumiPasswordNumberId = By.id("keyboard_button_textview");
    private By xumiPasswordNextId = By.id("com.stonebridge.xumi.android.emu:id/fab");
    private By androidPasswordTextBoxId = By.id("com.android.settings:id/password_entry");
    private By androidBtn1Id = By.id("android:id/button1");
    private By xumiLogoTextId = By.id("com.stonebridge.xumi.android.emu:id/splash_subtitle");
    private By androidPasswordFieldId = By.id("com.android.settings:id/password_entry");
    private By signInSixDigitPinText = By.id("com.stonebridge.xumi.android.emu:id/pin_code_step_textview");
    private By backButtonId = By.id("com.stonebridge.xumi.android.emu:id/keyboard_button_imageview");
    private By okButtonId = By.id("com.stonebridge.xumi.android.emu:id/snackbar_action");
    private By welcomePageTextXpath = By.xpath
            ("//android.widget.TextView[contains(@text,'Welcome to the next generation of secure payment.')]");
    private By iUnderstandBtn = By.id("com.stonebridge.xumi.android.xumimerchant.noemu:id/permissionsButton");
    private By appPermissionsPageTextXpath = By.xpath
            ("//android.widget.TextView[contains(@text,'We need a few permissions to maximize the security of your XUMI transactions.')]");
    private By androidPermissionMessageId = By.id("com.android.packageinstaller:id/permission_message");
    private By androidPermissionDeniedMessageId = By.id("android:id/message");
    private By androidPermissionAllowBtnId = By.id("com.android.packageinstaller:id/permission_allow_button");
    private By androidPermissionDenyBtnId = By.id("com.android.packageinstaller:id/permission_deny_button");
    private By dontAskAgainCheckBoxId = By.id("com.android.packageinstaller:id/do_not_ask_checkbox");
    private By phoneIntegrityCheckPageTitleTextXpath = By.xpath
            ("//android.widget.TextView[contains(@text,'Phone integrity check')]");
    private By PICBackButtonId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/backButton");
    private By PICNextButtonId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");

    private By SetPINScreenTittleId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/title");
    private By pinScreenNextButtonId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By enterEmailAddressTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/title");
    private By enterEmailId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/userInputViewEditText");
    private By emailNextBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By enterFirstCodeId = By.xpath("//android.widget.EditText[@index='0']");
    private By enterSecondCodeId = By.xpath("//android.widget.EditText[@index='1']");
    private By enterThirdCodeId = By.xpath("//android.widget.EditText[@index='2']");
    private By enterFourthCodeId = By.xpath("//android.widget.EditText[@index='3']");
    private By enterFifthCodeId = By.xpath("//android.widget.EditText[@index='4']");
    private By enterSixthCodeId = By.xpath("//android.widget.EditText[@index='5']");
    private By enterSeventhCodeId = By.xpath("//android.widget.EditText[@index='6']");
    private By enterEighthCodeId = By.xpath("//android.widget.EditText[@index='7']");
    private By enterFirstNameTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/userInputViewEditText");
    private By enterSecondNameTextId = By.xpath("//android.widget.EditText[@text='Last name']");
    private By nextButtonOfEnterYourName = (By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab"));
    private By verifyPhoneNotitleId = By.xpath("//android.widget.TextView[@text='Verify this phone number?']");
    private By verifyphoneNoTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/customFontTextView");
    private By countryCodeTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/countryViewName");
    private By CountryCodeTitleId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/toolbarTitle");
    private By BackButtonId = By.className("android.widget.ImageButton");
    private By EnterPhoneNoTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/userInputViewEditText");
    private By nextBtnOfphoneScreen = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By enterTheCodeWeTextedToYourPhoneTitleId = By.xpath("//android.widget.TextView[@text='Enter the code we texted to your phone.']");
    private By merchantAccountregistrationPageIsLoaded = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/pageHeader");
    private By codeTextedToPhoneTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/customFontTextView2");
    private By enterFirstPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='0']");
    private By entersecondPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='1']");
    private By enterThirdPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='2']");
    private By enterForthPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='3']");
    private By enterFifthPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='4']");
    private By entersixthPhoneCodeTxtId = By.xpath("//android.widget.EditText[@index='5']");
    private By registerNewXumiMerchantAccountBtnId = By.xpath("//android.widget.TextView[@text='REGISTER a new XUMI merchant account']");
    private By businessNameTextId = By.xpath("//android.widget.TextView[@text='Business details']");
    private By incorrectVerificationCodetextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/verifyCodeViewErrorText");
    // private By resendTheCodeTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/resendButton");
    private By resendTheCodeTextId = By.xpath("//android.widget.TextView[@text='Resend the code?']");
    //private By resendTheCodeTextId=By.xpath("//android.widget.TextView[contains(@resouse-id,'com.stonebridge.xumi.android.xumimerchant.emu:id/resendButton')]");

    private By enterBusinessNameTextid = By.xpath("//android.widget.EditText[@text='Business name']");
    private By selectMarketCategoryTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/marketCategoryPicker");
    private By marketCategoryEcommerceBtnid = By.xpath("//android.widget.CheckedTextView[@text='ECOMMERCE']");
    private By marketcategoryMotoBtnId = By.xpath("//android.widget.CheckedTextView[@text='MOTO']");
    private By marketCategoryRetailBtnId = By.xpath("//android.widget.CheckedTextView[@text='RETAIL']");
    private By clickOkBtnId = By.xpath("//android.widget.Button[@text='OK']");
    private By selectMarketCodeTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/marketCodePicker");
    private By selectMarketcategoryCodeBtnId = By.xpath("//android.widget.TextView[@text='Electrical Contractors']");
    private By nextBtnOfBusinessScreenBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By backBtnOfBusinessScreenBtnId = By.className("android.widget.ImageButton");
    private By globalPaymentBusinessNameTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/businessNameDesc");
    // private By merchantAddressTextId = By.xpath("//android.widget.TextView[@text='What's your merchant address?']");
    private By merchantAddressTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/viewPager");
    private By enterMerchantAdressTextId = By.xpath("//android.widget.EditText[@text='Address']");
    private By enterMerchantAdresssNextLineTextId = By.xpath("//android.widget.EditText[@index='7']");
    private By provinceTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/stateProvinceTextView");
    private By selectProvienceTextId = By.xpath("//android.widget.TextView[@text='Uttar Pradesh']");
    private By cityTextId = By.xpath("//android.widget.EditText[@text='City']");
    private By postalCodeTestid = By.xpath("//android.widget.EditText[@text='Postal code']");
    private By timeZoneTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/timeZonePicker");
    //private By timeZoneTextId=By.xpath("//android.widget.TextView[@Text='Time zone']");
    private By selectTimeZoneTextId = By.xpath("//android.widget.TextView[@text='Pacific/Pago Pago']");
    private By nextBtnOfMerchantAddressScreenBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By bankDetailsTextId = By.xpath("//android.widget.TextView[@text='Bank details']");
    private By banktransitNoTextId = By.xpath("//android.widget.EditText[@text='Transit number']");
    private By bankInstitutionNoTextId = By.xpath("//android.widget.EditText[@text='Institution number']");
    private By bankAccountNoTextId = By.xpath("//android.widget.EditText[@text='Account number']");
    private By nextBtnOfbankDetailsBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By globalPaymentAccountTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/createAccBtn");
    private By productOrServiceYouSellTextId = By.xpath("//android.widget.EditText[@text='Product or service that you sell']");
    private By yearsInBusinessTextId = By.xpath("//android.widget.EditText[@text='Years in business']");
    private By websiteTextId = By.xpath("//android.widget.EditText[@text='Website']");
    private By existingMerchantAccountTextId = By.xpath("//android.widget.EditText[@text='Existing Global merchant account']");
    private By nextBtnOfBusinesssInformationScreenBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By applicantInformationTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/applicantInfo");
    private By businessinformationtextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/businessInfo");
    private By motoTextId = By.xpath("//android.widget.TextView[@text='MOTO']");
    private By merchantcodeTextid = By.xpath(("//android.widget.TextView[@text='Caterers']"));
    private By merchantCategoryCodeTextId = By.xpath("//android.widget.TextView[@text='Merchant Category Code']");
    private By backBtnOfMerchantCategoryCodeBtnId = By.className("android.widget.ImageButton");
    private By merchantCodeActualTextId = By.xpath("//android.widget.TextView[@text='5811 - Caterers']");
    private By applicantPositionTextId = By.xpath("//android.widget.EditText[@text='Position or Title']");
    private By applicantSinTextId = By.xpath("//android.widget.EditText[@text='SIN # (Optional)']");
    private By applicantDateOfBirthTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/dateOfBirth");
    private By selectDateOfBirthBtnId = By.xpath("//android.view.View[@content-desc='20 November 2019']");
    private By calenderOkBtnId = By.id("android:id/button1");
    private By nextBtnOfApplicantInformationBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By billingInformationTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/billingInfo");
    private By backBtnOfStateBtnId = By.className("android.widget.ImageButton");
    private By backBtnOfTimeZoneBtnId = By.className("android.widget.ImageButton");
    private By TimeZoneScreenTextId = By.xpath("//android.widget.TextView[@text='Time zone']");
    private By countryTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/countryView");
    private By countryScreenTextId = By.xpath("//android.widget.TextView[@text='Country']");
    private By backBtnOfCountryScreenBtnId = By.className("android.widget.ImageButton");
    private By averageTransactionOfBillingTextId = By.xpath("//android.widget.EditText[@text='Est. average transaction size ($)']");
    private By highesttransactionOfBillingTextid = By.xpath("//android.widget.EditText[@text='Est. highest transaction size ($)']");
    private By averageAnnualVolumeOfBillingTextId = By.xpath("//android.widget.EditText[@text='Est. average annual volume ($)']");
    private By nextBtnOfBillingInformation = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By takeAPictureOfVoidCheckTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/title");
    private By sameAsBusinessAddressCheckBoxId = By.xpath("//android.widget.CheckBox[@text='Same as business address']");
    private By prepopulatedCountryTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/countryView");
    private By takePictureButtonId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/btnTakePicture");
    private By clickPictureBtnId = By.id("com.google.android.GoogleCamera:id/shutter_button");
    // private By clickPictureOkbtnid=By.id("com.sec.android.app.camera:id/okay");
    private By clickPictureOkbtnid = By.id("com.google.android.GoogleCamera:id/shutter_button");
    private By checkboxOfsubmissionForm = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/confirmProvidedInfo");
    private By submitBtnOfSubmissionForm = By.xpath("//android.widget.TextView[@text='Submit']");
    private By iConfirmCheckBoxId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/confirmProvidedInfo");
    private By approvedStatusTextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/applicationStatus");
    private By applicationStatusFinalStepButtonId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/finalStepButton");
    private By administrationPaymentTerminalTextId = By.xpath("//android.widget.EditText[@text='Terminal name']");
    private By selectPaymentTerminalIdDropdownId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/terminalId");
    private By selectTerminalIdtextId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/terminalId");
    private By saveBtnOfPaymentTerminalBtnId = By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/btnSave");
    private By youAreAllSetTextXpath = By.xpath("//android.widget.TextView[contains(@text,\"You're all set\")]");
    private By nextBtnOfYouAreSetBtnId=By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");
    private By tapYourPhoneTextId=By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/screenDescription");
    private By nextBtnOfTapYourPhone=By.id("com.stonebridge.xumi.android.xumimerchant.emu:id/fab");


    private String userFirstName;
    private String userLastName;
    private JSONObject userDetails;
    private void init() throws JSONException {
        //LOGGER.info(INIT_INFO);
        userFirstName = userDetails.getString("firstName");
        userLastName = userDetails.getString("lastName");
    }
    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseTest.java
     */
    public OnBoardingPage(AndroidDriver driver) {
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
        return By.className("android.widget.TextView");
    }


    public void ignoreInAppUpdates() {
        LOGGER.info("Ignoring the in-app updates disabled pop-up");
        if (driver.findElements(androidBtn1Id).size() > 0) {
            driver.findElement(androidBtn1Id).click();
        }
    }

    /**
     * This method types the host name in the desired field
     */
    public void clickLetsGetsStartedButton() {
        LOGGER.info("Clicking on Lets get started button");
        wait.until(ExpectedConditions.presenceOfElementLocated(letsGetStartedBtnId));
        clickElement(letsGetStartedBtnId);
    }

    public void enterAndroidPassword(String androidPassword) {
        LOGGER.info("Entering the phone password");
        if (driver.findElements(androidPasswordFieldId).size() > 0) {
            wait.until(ExpectedConditions.presenceOfElementLocated(androidPasswordFieldId));
            enterInput(androidPasswordFieldId, androidPassword);
            driver.pressKeyCode(AndroidKeyCode.ENTER);
        }
    }


    public OnBoardingPage setXumiPassword() throws InterruptedException {
        LOGGER.info("Setting a new xumi password");
        wait.until(ExpectedConditions.presenceOfElementLocated(xumiPasswordTextBoxId));
        enterXumiPassword();
        wait.until(ExpectedConditions.presenceOfElementLocated(xumiPasswordNextId));
        clickElement(xumiPasswordNextId);
        wait.until(ExpectedConditions.presenceOfElementLocated(xumiPasswordTextBoxId));
        enterXumiPassword();
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.id("com.stonebridge.xumi.android.emu:id/phoneNumberView")));
        return new OnBoardingPage(driver);
    }

    public void enterIncorrectXumiPassword() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xumiPasswordNumberId));
        List<WebElement> numbers = driver.findElements(xumiPasswordNumberId);
        for (int index = 3; index <= 8; index++) {
            for (WebElement ele : numbers) {
                if (ele.getText().equalsIgnoreCase(String.valueOf(index))) {
                    ele.click();
                    break;
                }
            }
        }
    }

    public void enterPin(String password) throws InterruptedException {
        LOGGER.info("Entering the xumi password");
        wait.until(ExpectedConditions.presenceOfElementLocated(enterPinId));
        enterInput(enterPinId, password);
        waitForElementToBeEnabled(enterNumberNextBtnId);
        clickElement(enterNumberNextBtnId);
        Thread.sleep(6000);
    }

    public boolean verifyPageIsLoaded() {
        LOGGER.info("Verify if the page is loaded");
        wait.until(ExpectedConditions.presenceOfElementLocated(letsGetStartedBtnId));
        return (driver.findElement(letsGetStartedBtnId).isDisplayed());
    }

    public boolean verifyWelcomePageIsLoaded() {
        LOGGER.info("Verify if the welcome page is loaded");
        wait.until(ExpectedConditions.presenceOfElementLocated(welcomePageTextXpath));
        return (driver.findElement(welcomePageTextXpath).isDisplayed());
    }

    public boolean verifyFirstAppPermissionsPageIsLoaded() {
        LOGGER.info("Verify if the 'First, app permissions'page is loaded");
        wait.until(ExpectedConditions.presenceOfElementLocated(iUnderstandBtn));
        return (driver.findElement(iUnderstandBtn).isDisplayed());
    }

    public boolean verifyAppPermissionsPageContextIsLoaded() {
        LOGGER.info("Verify if the 'First, app permissions'page context is loaded");
        wait.until(ExpectedConditions.presenceOfElementLocated(appPermissionsPageTextXpath));
        return (driver.findElement(appPermissionsPageTextXpath).isDisplayed());
    }

    public boolean verifyPhoneIntegrityCheckPageContextIsLoaded() {
        LOGGER.info("Verify if the 'First, app permissions'page context is loaded");
        wait.until(ExpectedConditions.presenceOfElementLocated(phoneIntegrityCheckPageTitleTextXpath));
        return (driver.findElement(phoneIntegrityCheckPageTitleTextXpath).isDisplayed());
    }

    public boolean verifyAndroidPinScreenIsVisible() {
        LOGGER.info("Verify that the android 4 digit pin screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(androidPasswordTextBoxId));
        return (driver.findElement(androidPasswordTextBoxId).isDisplayed());
    }

    public boolean verifyFingerprintScreenIsVisible() {
        LOGGER.info("Verify that the fingerprint screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(verifyYourFingerprintTextId));
        return (driver.findElement(verifyYourFingerprintTextId).isDisplayed());
    }

    public boolean verifyPinIsEncrypted() {
        LOGGER.info("Verify that the pin entered is encrypted");
        wait.until(ExpectedConditions.presenceOfElementLocated(xumiPinCodeViewId));
        String pinCodeText = driver.findElement(xumiPinCodeViewId).getText();
        return (pinCodeText.contains("xxxxx6"));
    }

    public boolean verifyNextButtonDisabledAfterDeletingPin() {
        LOGGER.info("Verify that the next button gets disabled after deleting pin");
        clickElement(backButtonId);
        driver.runAppInBackground(1);
        wait.until(ExpectedConditions.presenceOfElementLocated(xumiPasswordNextId));
        WebElement nextButton = driver.findElement(xumiPasswordNextId);
        return (nextButton.isEnabled());
    }

    public void verifyStartOverAfterPinMismatch() {
        LOGGER.info("Entering 6 Digit XUMI Pin as 123456 ");
        enterXumiPassword();
        clickElement(xumiPasswordNextId);
        LOGGER.info("Entering 6 Digit XUMI Pin as 345678 to check mismatch");
        enterIncorrectXumiPassword();
    }

    public void verifyErrorAfterPinMismatch() {
        LOGGER.info("Entering 6 Digit XUMI Pin as 123456 ");
        enterXumiPassword();
        clickElement(xumiPasswordNextId);
        LOGGER.info("Entering 6 Digit XUMI Pin as 345678 to check mismatch");
        enterIncorrectXumiPassword();
    }

    public boolean verifyXumiLogoText() {
        LOGGER.info("Verifying the XUMI logo on the welcome screen");
        String xumiLogoText = driver.findElement(xumiLogoTextId).getText();
        return (xumiLogoText.equalsIgnoreCase("The smart way to pay."));
    }

    public boolean verifyAppPasswordRequireSixDigits() {
        waitForElement(xumiPasswordTextBoxId);
        String text = driver.findElement(xumiPasswordTextBoxId).getText();
        return text.trim().equalsIgnoreCase("Set a 6 digit PIN");
    }

    public boolean verifySignInWithSixDigitsPinScreenIsVisible() {
        LOGGER.info("Verifying the 'Sign-in with your 6 digit PIN' screen is visible");
        return (doesElementExist(signInSixDigitPinText));
    }

    public void clickOkButton() {
        LOGGER.info("Clicking OK button");
        clickElement(okButtonId);
    }

    public void clickOkPICBackButton() {
        LOGGER.info("Clicking on back button on Phone integrity check screen");
        wait.until(ExpectedConditions.presenceOfElementLocated(PICBackButtonId));
        clickElement(PICBackButtonId);
    }

    public OnBoardingPage clickOkPICNextButton() {
        LOGGER.info("Clicking on next button on Phone integrity check screen");
        wait.until(ExpectedConditions.presenceOfElementLocated(PICNextButtonId));
        clickElement(PICNextButtonId);
        return new  OnBoardingPage(driver);
    }

    public void acceptAllPermissions() {
        LOGGER.info("Accepting all the permissions being displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionAllowBtnId));
        clickElement(androidPermissionAllowBtnId);
        clickPermissionAllowButton(1);
    }

    public void denyAllPermissions() {
        LOGGER.info("Denying all the permissions being displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionAllowBtnId));
        clickElement(androidPermissionAllowBtnId);
        clickPermissionDenyButton(1);
    }

    public void denyFirstPermission() {
        LOGGER.info("Denying the first permission being displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionDenyBtnId));
        clickElement(androidPermissionDenyBtnId);
    }


    private void clickPermissionAllowButton(int noOfTimes) {
        for (int index = 0; index < noOfTimes; index++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionAllowBtnId));
            clickElement(androidPermissionAllowBtnId);
        }
    }

    private void clickPermissionDenyButton(int noOfTimes) {
        for (int index = 0; index < noOfTimes; index++) {
            wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionDenyBtnId));
            clickElement(androidPermissionDenyBtnId);
        }
    }

    public String getAndroidPermissionMessage() {
        waitForElement(androidPermissionMessageId);
        return (driver.findElement(androidPermissionMessageId).getText());
    }

    public void denyFirstPermissionUsingDontAskAgain() {
        LOGGER.info("Denying the first permission being displayed after checking dont ask again checkbox");
        wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionDenyBtnId));
        clickElement(dontAskAgainCheckBoxId);
        clickElement(androidPermissionDenyBtnId);
    }

    public boolean verifyPermissionsDialogPresent() {
        LOGGER.info("Verifying the permission dialog box is present");
        clickElement(androidBtn1Id);
        String actManagePhoneCallsText = getAndroidPermissionMessage();
        return (expManagePhoneCallsText.equalsIgnoreCase(actManagePhoneCallsText));
    }

    public boolean verifyPermissionPermanentlyDeniedMessage() {
        LOGGER.info("Verifying the permission dialog box of permission permanently denied is present");
        waitForElement(androidPermissionDeniedMessageId);
        String permanentlyDeniedMsg = (driver.findElement(androidPermissionDeniedMessageId).getText());
        return (permanentlyDeniedMsg.toLowerCase().contains("permission was permanently denied"));
    }

    public boolean verifyAllPermissionsDialogBox() {
        LOGGER.info("Verifying the appearance of different permission dialog boxes");
        String actManagePhoneCallsText = getAndroidPermissionMessage();
        if (expManagePhoneCallsText.equalsIgnoreCase(actManagePhoneCallsText)) {
            LOGGER.info("Manage phone calls dialog box displayed");
            clickElement(androidPermissionAllowBtnId);
        } else {
            return false;
        }
        String actPermissionText = getAndroidPermissionMessage();
        if (expDeviceLocationText.equalsIgnoreCase(actPermissionText)) {
            LOGGER.info("Device's Location dialog box displayed");
            clickElement(androidPermissionAllowBtnId);
        } else {
            return false;
        }
        return true;
    }

    public boolean verifyAcceptDenyBtnsPresentOnEachPermission() {
        LOGGER.info("Verify that all the permissions have accept and deny buttons");
        if (!verifyAcceptDenyBtnsPresent()) {
            return false;
        }
        clickElement(androidPermissionAllowBtnId);
        for (int index = 0; index < 1; index++) {
            boolean present = verifyAcceptDenyBtnsPresent();
            if (!present)
                return false;
            clickElement(androidPermissionAllowBtnId);
        }
        return true;
    }

    private boolean verifyAcceptDenyBtnsPresent() {
        boolean acceptPresent = driver.findElement(androidPermissionAllowBtnId).isDisplayed();
        boolean denyPresent = driver.findElement(androidPermissionDenyBtnId).isDisplayed();
        return (acceptPresent && denyPresent);
    }

    public void denyManagePhoneCallsPermission() {
        LOGGER.info("Clicking on DENY button on 'Manage phone calls' permission dialog box");
        if (getAndroidPermissionMessage().equalsIgnoreCase(expManagePhoneCallsText)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(androidPermissionDenyBtnId));
            clickElement(androidPermissionDenyBtnId);
        }
    }

    public void setPin() {
        LOGGER.info("enter pin");
        wait.until(ExpectedConditions.presenceOfElementLocated(SetPINScreenTittleId));
        driver.findElement(By.xpath("//android.widget.TextView[@text='1']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='9']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='3']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='5']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='7']")).click();
    }

    public void clickNextButtonofPinScreen() {
        LOGGER.info("clicked pin screen btn");
        wait.until(ExpectedConditions.presenceOfElementLocated(pinScreenNextButtonId));
        clickElement(pinScreenNextButtonId);
    }

    public String enterEmailAddress() {
        LOGGER.info("enter email address");
        String expectedEmail = "approve"
                .concat(getRandomNumberInRange()).concat("@example.com");
        enterInput(enterEmailId, expectedEmail);
        clickElement(emailNextBtnId);
        return (expectedEmail);
    }

    public void enterAlreadyRegisteredEmailAddress() {
        LOGGER.info("enter email address");
        driver.findElement(enterEmailId);
        enterInput(enterEmailId, "approvest@example.com");
        clickElement(emailNextBtnId);
    }

    public void emailVerificationCode() {
        LOGGER.info("enter the code we sent to your email address");
       wait.until(ExpectedConditions.presenceOfElementLocated(enterSecondCodeId));
       driver.findElement(enterFirstCodeId).click();
        for (int i = 1; i <= 8; i++) {
            driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[" + i + "]")).click();
            driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[" + i + "]"))
                    .sendKeys(Integer.toString(i));
        }
    }

    public void incorrectEmailVerificationCode() {
        LOGGER.info("enter the code we sent to your email address");
        wait.until(ExpectedConditions.presenceOfElementLocated(enterSecondCodeId));
        driver.findElement(enterFirstCodeId).click();
        enterInput(enterFirstCodeId, "1");
        enterInput(enterSecondCodeId, "9");
        enterInput(enterThirdCodeId, "3");
        enterInput(enterFourthCodeId, "4");
        enterInput(enterFifthCodeId, "5");
        enterInput(enterSixthCodeId, "6");
        enterInput(enterSeventhCodeId, "7");
        enterInput(enterEighthCodeId, "8");

        //wait.until(ExpectedConditions.presenceOfElementLocated(incorrectVerificationCodetextId));
        // driver.getKeyboard().sendKeys(Keys.CLEAR);
        //  enterInput(enterEighthCodeId, "0");
        driver.findElement(enterSeventhCodeId).sendKeys("0");
        //wait.until(ExpectedConditions.presenceOfElementLocated(incorrectVerificationCodetextId));
        //driver.getKeyboard().sendKeys(Keys.CANCEL);
        //enterInput(enterThirdCodeId, "2");


        //driver.getKeyboard().sendKeys(Keys.CLEAR);
        driver.findElement(enterEighthCodeId).sendKeys("1");
        //WebElement element = driver.findElement(resendTheCodeTextId);
//        JavascriptExecutor executor = (JavascriptExecutor) driver;
//        executor.executeScript("arguments[0].click();", element);
//        System.out.println("clicked on resend button");
//        Actions action = new Actions(driver);
//        action.moveToElement(element).click().perform();
        //TouchAction action = new TouchAction(driver);
        //action.moveTo(element).tap(element).perform();


        driver.findElement(resendTheCodeTextId).click();
//        try {
//            Thread.sleep(5000);
//        }
//        catch (Exception exception) {
//            }

        // wait.until(ExpectedConditions.presenceOfElementLocated(incorrectVerificationCodetextId));
        // wait.until(ExpectedConditions.presenceOfElementLocated(resendTheCodeTextId));
        //clickElement(resendTheCodeTextId);


    }

    public void enterYourName(String FirstName,String LastName) {
        LOGGER.info("enter your name");
        enterInput(enterFirstNameTextId, FirstName);
        enterInput(enterSecondNameTextId, LastName);
        clickElement(nextButtonOfEnterYourName);
    }

    public boolean verifyPhoneNoScreenIsVisible() {
        LOGGER.info("Verify that phone no screen is displayed");
        wait.until(ExpectedConditions.presenceOfElementLocated(verifyPhoneNotitleId));
        return (driver.findElement(verifyPhoneNotitleId).isDisplayed());
    }

    public void countryCode(String countryCodeId) {
        LOGGER.info("verify phone no");
        wait.until(ExpectedConditions.presenceOfElementLocated(verifyphoneNoTextId));
        clickElement(countryCodeTextId);
        scrollAndClick(countryCodeId);
    }

    public boolean verifyCountryCodeScreenAndbackButtonIsVisible() {
        LOGGER.info("verify that the country code screen and back button is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(CountryCodeTitleId));
        return (driver.findElement(CountryCodeTitleId).isDisplayed()) && (driver.findElement(BackButtonId).isDisplayed());

    }

    public void enterPhoneNo(String phoneNo) {
        clickElement(EnterPhoneNoTextId);
        enterInput(EnterPhoneNoTextId, phoneNo);
        clickElement(nextBtnOfphoneScreen);
    }

    public boolean verifyEnterTheCodeScreenIsVisible() {
        LOGGER.info("verify that the country code screen and back button is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(enterTheCodeWeTextedToYourPhoneTitleId));
        return (driver.findElement(enterTheCodeWeTextedToYourPhoneTitleId).isDisplayed());

    }

    public boolean verifyMerchantAccounScreenIsloaded() {
        LOGGER.info("verify that the merchant screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(merchantAccountregistrationPageIsLoaded));
        return (driver.findElement(merchantAccountregistrationPageIsLoaded).isDisplayed());
    }


    public void phoneNoTextedCode() {
        wait.until(ExpectedConditions.presenceOfElementLocated(entersecondPhoneCodeTxtId));
        driver.findElement(enterFirstPhoneCodeTxtId).click();
        for (int i = 1; i <= 6; i++) {
            driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[" + i + "]")).click();
            driver.findElement(By.xpath("(//*[@class='android.widget.EditText'])[" + i + "]"))
                    .sendKeys(Integer.toString(i));
        }
    }
    public void merchantAccountRegistration() {
        wait.until(ExpectedConditions.presenceOfElementLocated(registerNewXumiMerchantAccountBtnId));
        driver.findElement(registerNewXumiMerchantAccountBtnId).click();
    }

    public void backButtonOfMerchantAccounregistration() {
        clickElement(BackButtonId);
    }

    public boolean verifyBusinessNameScreenIsLoaded() {
        LOGGER.info("verify that the business screen is loaded ");
        wait.until(ExpectedConditions.presenceOfElementLocated(businessNameTextId));
        return (driver.findElement(businessNameTextId).isDisplayed());
    }

    public boolean verifyPreviousEmailAddressIsDisplayedByClickingResendBtn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(enterEmailAddressTextId));
        return (driver.findElement(enterEmailAddressTextId).isDisplayed());
    }

    public String verifySameEmailId() {
        wait.until(ExpectedConditions.presenceOfElementLocated(enterEmailAddressTextId));
        driver.findElement(enterEmailId).getText();
        return (driver.findElement(enterEmailId).getText());
    }

    public void businessDetailsScreen(String businessName,String MarketCode ) {
        wait.until(ExpectedConditions.presenceOfElementLocated(businessNameTextId));
        enterInput(enterBusinessNameTextid,businessName);
        wait.until(ExpectedConditions.presenceOfElementLocated(globalPaymentBusinessNameTextId));
        clickElement(selectMarketCodeTextId);
        scrollAndClick(MarketCode);
        clickElement(nextBtnOfBusinessScreenBtnId);
    }

    public boolean verifyBusinessAddressScreenIsVisible() {
        LOGGER.info("All business details are present and address screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(merchantAddressTextId));
        return (driver.findElement(merchantAddressTextId).isDisplayed());
    }

    public void merchantAddressScreen(String address,String city,String postalCode) {
        //wait.until(ExpectedConditions.presenceOfElementLocated(enterMerchantAdressTextId));
        enterInput(enterMerchantAdressTextId,address);
        clickElement(provinceTextId);
        clickElement(selectProvienceTextId);
        enterInput(cityTextId,city);
        clickElement(postalCodeTestid);
        driver.findElement(postalCodeTestid);
        driver.findElement(postalCodeTestid).sendKeys(postalCode);
        driver.hideKeyboard();
        driver.findElement(timeZoneTextId).click();
        driver.findElement(selectTimeZoneTextId).click();
        clickElement(nextBtnOfMerchantAddressScreenBtnId);

    }

    public Boolean verifyBankDetailsScreenIsVisible() {
        LOGGER.info("bank details screen is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(bankDetailsTextId));
        return (driver.findElement(bankDetailsTextId).isDisplayed());
    }

    public void merchantBankDetails(String banktransitNo,String bankInstitutionNo,String bankAccountNo) {
        enterInput(banktransitNoTextId,banktransitNo);
        enterInput(bankInstitutionNoTextId, bankInstitutionNo);
        enterInput(bankAccountNoTextId, bankAccountNo);
        clickElement(nextBtnOfbankDetailsBtnId);
    }

    public boolean verifyGlobalPaymentScreenIsVisible() {
        LOGGER.info("global payment screen is visible");
        wait.until((ExpectedConditions.presenceOfElementLocated(globalPaymentAccountTextId)));
        return (driver.findElement(globalPaymentAccountTextId).isDisplayed());
    }

    public void globalpayment() {
        clickElement(globalPaymentAccountTextId);
    }

    public void businessInformation(String productOrService,String yearsInBusiness,
                                    String website,String existingMerchantAccount) {
        enterInput(productOrServiceYouSellTextId, productOrService);
        enterInput(yearsInBusinessTextId,yearsInBusiness);
        enterInput(websiteTextId, website);
        enterInput(existingMerchantAccountTextId, existingMerchantAccount);
        clickElement(nextBtnOfBusinesssInformationScreenBtnId);
    }

    public boolean verifyApplicantInformationScreenIsVisible() {
        LOGGER.info("Applicant Information screen is visible");
        wait.until((ExpectedConditions.presenceOfElementLocated(applicantInformationTextId)));
        return (driver.findElement(applicantInformationTextId).isDisplayed());
    }

//    public void globalMarketCategoryScreen() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(businessNameTextId));
//        enterInput(enterBusinessNameTextid, "test");
//        wait.until(ExpectedConditions.presenceOfElementLocated(globalPaymentBusinessNameTextId));
//        clickElement(selectMarketCategoryTextId);
//        clickElement(marketCategoryEcommerceBtnid);
//        clickElement(clickOkBtnId);
//        clickElement(selectMarketCategoryTextId);
//        clickElement(marketcategoryMotoBtnId);
//        clickElement(clickOkBtnId);
//    }

//    public boolean verifySelectedMarketCategoryIsSameOnBusinessDetailsScreen() {
//        LOGGER.info("user is able to select market category");
//        String expectedText = driver.findElement(motoTextId).getText();
//        clickElement(motoTextId);
//        String actualText = driver.findElement(marketcategoryMotoBtnId).getText();
//        clickElement(clickOkBtnId);
//        if (expectedText.equals(actualText)) {
//            System.out.println("Pass");
//        } else {
//            System.out.println("Fail");
//        }
//        return expectedText.equals(actualText);
//    }

    public void marketCodeScreen() {
        wait.until(ExpectedConditions.presenceOfElementLocated(businessNameTextId));
        clickElement(selectMarketCodeTextId);
    }

    public boolean scrollUpDownAndSelectOptionInMarketCode() {
        LOGGER.info("user is able to select market code ");
        scrollAndClick("Caterers");
        String expectedText = "5811 - Caterers";
        String ActualText = driver.findElement(merchantCodeActualTextId).getText();
        if (expectedText.contains(ActualText)) {
            System.out.println("Pass");
        } else {
            System.out.println("Fail");
        }
        return expectedText.equals(ActualText);
    }

    public boolean verifyMarketCodeScreenIsVisibleAndBackButtonOnIt() {
        LOGGER.info("market code screen and backButton is displayed");
        return driver.findElement(merchantCategoryCodeTextId).isDisplayed()
                && driver.findElement(backBtnOfMerchantCategoryCodeBtnId).isDisplayed();
    }

    public void clickBackButtonOfMarketCode() {
        LOGGER.info("Clicking on back button on market code screen");
        clickElement(selectMarketCodeTextId);
        wait.until(ExpectedConditions.presenceOfElementLocated(backBtnOfMerchantCategoryCodeBtnId));
        clickElement(backBtnOfMerchantCategoryCodeBtnId);
    }

    public void applicantInformation(String applicantPosition,String applicantSin) {
        try {
            enterInput(applicantPositionTextId,applicantPosition);
            enterInput(applicantSinTextId,applicantSin);
            clickElement(applicantDateOfBirthTextId);

            //driver.findElement(applicantDtaeOfBirthTextId).click();
            //clickElement(selectDateOfBirthBtnId);
            driver.findElement(By.xpath("//android.view.View[@content-desc='01 December 2019']")).click();
            clickElement(calenderOkBtnId);
            clickElement(nextBtnOfApplicantInformationBtnId);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public boolean verifyBillingInformationScreen() {
        LOGGER.info("Billing information screen is visible");
        wait.until((ExpectedConditions.presenceOfElementLocated(billingInformationTextId)));
        return (driver.findElement(billingInformationTextId).isDisplayed());
    }

    public void stateCodeScreen() {
        clickElement(provinceTextId);
        scrollDownPage();
    }

    public boolean verifyUserNavigateToSelectedCityStateAndBackBtnIsVisible() {
        LOGGER.info("state screen and back btn is visible");
        wait.until((ExpectedConditions.presenceOfElementLocated(selectProvienceTextId)));
        return driver.findElement(selectProvienceTextId).isDisplayed() && driver.findElement(backBtnOfStateBtnId).isDisplayed();
    }

    public void clickBackButtonOfstateScreen() {
        clickElement(backBtnOfStateBtnId);
    }

    public void timeZoneScreen() {
//        enterInput(enterMerchantAdressTextId, "test");
//        clickElement(provinceTextId);
//        clickElement(selectProvienceTextId);
//        enterInput(cityTextId, "test");
//        clickElement(postalCodeTestid);
//        driver.findElement(postalCodeTestid).sendKeys("123456");
//        driver.hideKeyboard();
        driver.findElement(timeZoneTextId).click();
    }

    public boolean verifyUserNavigateToTimeZoneScreenAndBackBtnIsDisplayed() {
        LOGGER.info("user navigate to time zone screen and back arrow is visible");
        return driver.findElement(TimeZoneScreenTextId).isDisplayed() &&
                driver.findElement(backBtnOfTimeZoneBtnId).isDisplayed();
    }

    public void timeZoneBackBtnClick() {
        scrollDownPage();
        driver.findElement(backBtnOfTimeZoneBtnId).click();
    }

    public void countryScreen() {
        clickElement(countryTextId);
        scrollDownPage();
    }

    public boolean verifyCountryScreenIsVisibleAndScrollableAndBackBtnIsVisible() {
        LOGGER.info("user able to view country screen and back button is visible");
        return driver.findElement(countryScreenTextId).isDisplayed() &&
                driver.findElement(backBtnOfCountryScreenBtnId).isDisplayed();
    }

    public void countryScreenBackBtnClick() {
        driver.findElement(backBtnOfCountryScreenBtnId).click();
    }

    public boolean verifyUserNavigateBackToBusinessAddressScreen() {
        LOGGER.info("user able to navigate to business adress screen by clicking back btn ");
        return driver.findElement(countryTextId).isDisplayed();
    }

    public void billingInformation(String averageTransaction,String highesttransaction,
                                   String averageAnnualVolume) {
        enterInput(averageTransactionOfBillingTextId,averageTransaction);
        enterInput(highesttransactionOfBillingTextid,highesttransaction);
        enterInput(averageAnnualVolumeOfBillingTextId,averageAnnualVolume);
    }

    public void clickCheckBox() {
        driver.findElement(sameAsBusinessAddressCheckBoxId).click();
        scrollDownPage();
    }

    public boolean verifyPrepopulatedCountryAndDetailsAreVisible() {
        LOGGER.info("details are visible and country is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(prepopulatedCountryTextId));
        return driver.findElement(prepopulatedCountryTextId).isDisplayed();
    }

    public boolean verifyValidDetailsOnBillingInformationScreenAndUserNavigateToTakePictureScreen() {
        clickElement(nextBtnOfBillingInformation);
        LOGGER.info("details are visible and country is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(takeAPictureOfVoidCheckTextId));
        return driver.findElement(takeAPictureOfVoidCheckTextId).isDisplayed();

    }

    public void nextBtnOfBillingInformation() {
        LOGGER.info("Clicking on next button on billing information screen");
        wait.until(ExpectedConditions.presenceOfElementLocated(nextBtnOfBillingInformation));
        clickElement(nextBtnOfBillingInformation);
    }

    public void takeAPicture() {
        clickElement(takePictureButtonId);
        clickElement(clickPictureBtnId);
    }

    public boolean verifyUserAbleToTakePictureOfVoidCheque() {
        LOGGER.info("user able to take picture of void cheque");
        wait.until(ExpectedConditions.presenceOfElementLocated(clickPictureOkbtnid));
        return driver.findElement(clickPictureOkbtnid).isDisplayed();
    }

    public boolean verifyUserNavigateBackToBillingAddressScreen() {
        LOGGER.info("user able to navigate to billing adress screen by clicking back btn ");
        return driver.findElement(countryTextId).isDisplayed();
    }

    public void clickTimeZone() {
        driver.findElement(timeZoneTextId).click();

    }
    public void confirmSubmittionForm() {
        clickElement(checkboxOfsubmissionForm);
        clickElement(submitBtnOfSubmissionForm);
    }

    public void clickPictureOkBtnId() {
        clickElement(clickPictureOkbtnid);
    }

    public void clickOnICofirmCheckbox() {
        LOGGER.info("Clicking 'I confirm ' checkbox");
        clickElement(iConfirmCheckBoxId);
    }

    public boolean verifyIConfirmCheckbox() {
        LOGGER.info("Checking the status of 'i confirm' checkbox");
        String checkBoxStatus = driver.findElement(iConfirmCheckBoxId).getAttribute("checked");
        if (checkBoxStatus.equalsIgnoreCase("true")) {
            LOGGER.info("'I confirm' checkbox is checked");
            return true;
        } else {
            LOGGER.info("'I confirm' checkbox is unchecked");
            return false;
        }
    }

    public void clickSubmitBtnid() {
        LOGGER.info("Clicking on submit button");
        clickElement(submitBtnOfSubmissionForm);

    }

    public boolean verifyUserNavigateToApplicationStatusScreen() {
        LOGGER.info("user able to navigate to application status screen by clicking Submit btn ");
        return driver.findElement(approvedStatusTextId).isDisplayed();

    }

    public boolean verifyApplicationStatusIsShowingApproved() {
        LOGGER.info("application status is showing approved");
        String approvedStatus = driver.findElement(approvedStatusTextId).getText();
        String expectedText = "Approved";
        if (approvedStatus.equals(expectedText)) {
            LOGGER.info("approved");
            return true;
        } else {
            LOGGER.info("Not approved");
            return false;
        }
    }

    public void applicationStatus() {
        clickElement(applicationStatusFinalStepButtonId);

    }
    public void terminalIdPage(String administrationPaymentTerminal){
        enterInput(administrationPaymentTerminalTextId, administrationPaymentTerminal);
        clickElement(selectPaymentTerminalIdDropdownId);
        clickElement(selectTerminalIdtextId);
        clickElement(saveBtnOfPaymentTerminalBtnId );

    }

    public boolean verifyUserNavigateToYouAreSetPage(){
        LOGGER.info("user able to navigate to you are set page");
        String actualText = driver.findElement(youAreAllSetTextXpath).getText();
        String expectedText = "You're all set!";
        if (actualText.equals(expectedText)) {
            LOGGER.info("yes");
            return true;
        } else {
            LOGGER.info("No");
            return false;
        }

    }
    public void tapYourphone(){
        LOGGER.info("tap your phone");
        wait.until(ExpectedConditions.presenceOfElementLocated(tapYourPhoneTextId));
        clickElement(nextBtnOfTapYourPhone);
    }
    public boolean verifyUserNavigateToHomeScreen(){
        LOGGER.info(ExpectedConditions.presenceOfElementLocated(tapYourPhoneTextId));
        return driver.findElement(tapYourPhoneTextId).isDisplayed();


    }

}




















