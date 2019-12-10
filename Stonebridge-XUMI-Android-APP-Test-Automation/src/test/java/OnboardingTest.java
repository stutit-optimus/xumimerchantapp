import com.project.android.pages.HomePage;
import com.project.android.pages.Onboarding.OnBoardingPage;
import com.project.android.pages.Onboarding.SplashPage;
import com.project.android.pages.UserProfilePage;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;

import java.time.Duration;

import io.appium.java_client.android.AndroidKeyCode;

/**
 * Created by optimus153 on 4/10/2018.
 */
public class OnboardingTest extends BaseTest {
    public static final Logger LOGGER = Logger.getLogger(OnboardingTest.class);
    private static final String JSON_FILE_NAME = "OnboardingTest.json";
    private static final String INIT_INFO = "Initializing test data from OnboardingTest.json file";
    private static final String CONSTRUCTOR_INFO = "In OnboardingTest() constructor";
    private static final String INVALID_PHONE_NUMBER_ERROR = "INVALID PHONE NUMBER";
    private static final String INVALID_SMS_CODE_ERROR = "INCORRECT VERIFICATION CODE";
    private static final String INVALID_SMS_CODE_2_TRIES_ERROR = "REMAINING TRIES: 2";
    private String emailAddress = null;
    private JSONObject companyConfig;
    private JSONObject userDetails;
    private JSONObject userAddress;
    private JSONObject userCreditCard;
    private JSONObject userName;
    private JSONObject userphoneNo;
    private JSONObject userBusinessName;
    private JSONObject BusinessAddress;
    private JSONObject BankDetails;
    private JSONObject BusinessInformation;
    private JSONObject ApplicantInformation;
    private JSONObject BillingInformation;
    private JSONObject TerminalId;
    private JSONObject CountryCode;
    private String FirstName;
    private String LastName;
    private String phoneNo;
    private String businessName;
    private String address;
    private String city;
    private String postalCode;
    private String banktransitNo;
    private String bankInstitutionNo;
    private String bankAccountNo;
    private String productOrService;
    private String yearsInBusiness;
    private String website;
    private String existingMerchantAccount;
    private String applicantPosition;
    private String applicantSin;
    private String averageTransaction;
    private String highesttransaction;
    private String averageAnnualVolume;
    private String administrationPaymentTerminal;
    private String MarketCode;
    private String countryCodeId;


    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
    private String userPhoneNumberDisplayed;
    private String userInvalidPhoneNumber;
    private String invalidUserPhoneNumberDisplayed;
    private static String invalidEmailAddress;
    private static String alphanumericUserFirstName;
    private static String alphanumericUserLastName;
    private String userAddressLine;
    private String userAddressCity;
    private String userAddressZipCode;
    private String userAddressState;
    private String creditCardNumber;
    private String invalidCardNumber;
    private String creditCardCvc;
    public OnBoardingPage onBoardingPage;
    private UserProfilePage userProfilePage;
    private HomePage homePage;


    //public SplashPage onBoardingPage = new SplashPage(driver);
    //public OnBoardingPage onBoardingPage = new OnBoardingPage(driver);

    /**
     * Constructor
     *
     * @throws JSONException
     */
    public OnboardingTest() throws JSONException {
        super();
        LOGGER.info(CONSTRUCTOR_INFO);
        companyConfig = this.getCompanyConfig(JSON_FILE_NAME);
        userDetails = companyConfig.getJSONObject("userDetails");
        userAddress = companyConfig.getJSONObject("userAddress");
        userCreditCard = companyConfig.getJSONObject("userCreditCard");
        userName = companyConfig.getJSONObject("userName");
        userphoneNo = companyConfig.getJSONObject("userPhoneNo");
        userBusinessName = companyConfig.getJSONObject("userBusinessName");
        BusinessAddress = companyConfig.getJSONObject("BusinessAddress");
        BankDetails = companyConfig.getJSONObject("BankDetails");
        BusinessInformation = companyConfig.getJSONObject("BusinessInformation");
        ApplicantInformation = companyConfig.getJSONObject("ApplicantInformation");
        BillingInformation = companyConfig.getJSONObject("BillingInformation");
        TerminalId = companyConfig.getJSONObject("TerminalId");
        CountryCode=companyConfig.getJSONObject("CountryCode");


        init();
    }

    /**
     * This method reads all the test data from "OnboardingTest.json" file
     *
     * @throws JSONException
     */
    private void init() throws JSONException {
        LOGGER.info(INIT_INFO);
//        userFirstName = userDetails.getString("firstName");
//        userLastName = userDetails.getString("lastName");
//        userPhoneNumber = userDetails.getString("phoneNumber");
//        userPhoneNumberDisplayed = userDetails.getString("phoneNumberActual");
//        userInvalidPhoneNumber = userDetails.getString("invalidPhoneNumber");
//        invalidUserPhoneNumberDisplayed = userDetails.getString("invalidPhoneNumberActual");
//        invalidEmailAddress = userDetails.getString("invalidEmailAddress");
//        alphanumericUserFirstName = userDetails.getString("alphanumericFirstName");
//        alphanumericUserLastName = userDetails.getString("alphanumericLastName");
//        userAddressLine = userAddress.getString("addressLine");
//        userAddressCity = userAddress.getString("city");
//        userAddressZipCode = userAddress.getString("zipCode");
//        userAddressState = userAddress.getString("state");
//        creditCardNumber = userCreditCard.getString("cardNumber");
//        invalidCardNumber = userCreditCard.getString("invalidCardNumber");
//        creditCardCvc = userCreditCard.getString("cvc");
        FirstName = userName.getString("FirstName");
        LastName = userName.getString("LastName");
        phoneNo = userphoneNo.getString("phoneNo");
        businessName = userBusinessName.getString("businessName");
        MarketCode=userBusinessName.getString("MarketCode");
        address = BusinessAddress.getString("address");
        city = BusinessAddress.getString("city");
        postalCode = BusinessAddress.getString("postalCode");
        banktransitNo = BankDetails.getString("banktransitNo");
        bankInstitutionNo = BankDetails.getString("bankInstitutionNo");
        bankAccountNo = BankDetails.getString("bankAccountNo");
        productOrService = BusinessInformation.getString("productOrService");
        yearsInBusiness = BusinessInformation.getString("yearsInBusiness");
        website = BusinessInformation.getString("website");
        existingMerchantAccount = BusinessInformation.getString("existingMerchantAccount");
        applicantPosition = ApplicantInformation.getString("applicantPosition");
        applicantSin = ApplicantInformation.getString("applicantSin");
        averageTransaction = BillingInformation.getString("averageTransaction");
        highesttransaction = BillingInformation.getString("highesttransaction");
        averageAnnualVolume = BillingInformation.getString("averageAnnualVolume");
        administrationPaymentTerminal = TerminalId.getString("administrationPaymentTerminal");
        countryCodeId=CountryCode.getString("countryCodeId");


        LOGGER.info(FirstName);
        LOGGER.info(LastName);
    }

    /**
     * Verify that the application successfully launches
     */
    @Test
    public void verifySuccessfulAppLaunch() {
        try {
            LOGGER.info("Verify User is able to launch the application successfully");
            onBoardingPage = new OnBoardingPage(driver);
            Assert.assertTrue(onBoardingPage.verifyPageIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error while launching the application", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the App permission screen is displayed after clicking on lets get started button
     */
    @Test
    public void verifyAppPermissionsScreen() {
        try {
            LOGGER.info("Verify app permission screen after clicking lets get started");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            Assert.assertTrue(onBoardingPage.verifyFirstAppPermissionsPageIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error in app permission screen", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that Phone Integrity Check screen is displayed after accepting App permission
     */
    @Test
    public void verifyPopupOnAppPermissionsScreen() {
        try {
            LOGGER.info("Verify app permission screen accept all permission");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            Assert.assertTrue(onBoardingPage.verifyPhoneIntegrityCheckPageContextIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error while accepting all permission", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify Phone Integrity Check screen is displayed
     */
    @Test
    public void verifyPhoneIntegrityCheckScreen() {
        try {
            LOGGER.info("Verify phone integrity check screen page is loaded");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            Assert.assertTrue(onBoardingPage.verifyPhoneIntegrityCheckPageContextIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error while loading phone integrity check screen", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that back button is functional on Phone Integrity Check screen
     */
    @Test
    public void verifyBackBtnOnPhoneIntegrityCheckScreen() {
        try {
            LOGGER.info("Verify back button is functional on phone integrity check screen");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICBackButton();
            Assert.assertTrue(onBoardingPage.verifyAppPermissionsPageContextIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error while clicking on back button", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that next button is functional on Phone Integrity Check screen
     */
    @Test
    public void verifyNextBtnOnPhoneIntegrityCheckScreen() {
        try {
            LOGGER.info("Verify next button is functional on phone integrity check screen");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            Assert.assertTrue(onBoardingPage.verifyFingerprintScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while clicking on next button", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the pin screen is displayed after clicking next button
     */
    @Test
    public void verifyPinRegistrationScreen() {
        try {
            LOGGER.info("Verify pin registration screen is visible");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            Assert.assertTrue(onBoardingPage.verifyPinScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error in registration screen is not visible", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*To verify email is valid

     */

    @Test
    public void verifyValidEmailAddress() {
        try {
            LOGGER.info("Verify USer is able to enter valid email address");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            Assert.assertTrue(onBoardingPage.verifyEmailAddressCodeVerificationScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while launching the application", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify already registerd email address

     */

    @Test
    public void verifyAlreadyRegisteredEmailAddress() {
        try {
            LOGGER.info("Verify USer is able to enter already registered email address");
            onBoardingPage= new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.enterAlreadyRegisteredEmailAddress();
            Assert.assertTrue(onBoardingPage.verifyEmailAddressCodeVerificationScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while entering already registerd email address", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify email address verification code

     */

    @Test
    public void verifyValidEmailAddressVerificationCode() {
        try {
            LOGGER.info("Verify USer is able to enter valid email verification code");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            Assert.assertTrue(onBoardingPage.verifyNameScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while enter email verification code", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

   /* *//**
     * TCID : Verify Onboarding Screen
     *//*
    @Test
    public void verifyOnBoardingScreen() {
        try {
            LOGGER.info("Verify User is able to complete onboarding successfully");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickNextButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.enterAndroidPassword(androidPassword);
            onBoardingPage = onBoardingPage.setXumiPassword();
            onBoardingPage.enterPhoneNumber(userPhoneNumber, true);
            onBoardingPage.enterPin(xumiPassword);
            onBoardingPage.clickBackButton();
            onBoardingPage.enterUserName(userFirstName, userLastName, true);
            userProfilePage = onBoardingPage.enterEmailAddress(emailAddress, true);
            userProfilePage.enterUserAddress(userAddressLine, userAddressCity, userAddressZipCode, userAddressState, true);
            userProfilePage.addCreditCard(creditCardNumber, creditCardCvc, userFirstName + " " + userLastName, true);
            // To cancel NFC set up
            driver.findElement(By.id("android:id/button2")).click();
            driver.runAppInBackground(1);
            homePage = new HomePage(driver);
            Assert.assertTrue(homePage.verifyHomePageIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while onboarding the application", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }*/

    /**
     * Verify the functionality of Next Button on pin screen
     */
    @Test
    public void verifyNextButtonOnPinScreen() {
        try {
            LOGGER.info("Verify the functionality of Next button on pin screen");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickNextButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.enterAndroidPassword(androidPassword);
            Assert.assertTrue(onBoardingPage.verifyPinScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while checking the Next button on pin screen", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * verify Application behaviour for valid name and last name
     */
    @Test
    public void verifyValidNameAndLastName() {
        try {
            LOGGER.info("verify user is able to enter valid First name and last name");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            //onBoardingPage = onBoardingPage.emailVerificationCode();
            LOGGER.info(FirstName + " " + LastName);
            onBoardingPage.enterYourName(FirstName, LastName);
            //onBoardingPage.enterUserName(userFirstName, userLastName, true);
            Assert.assertTrue(onBoardingPage.verifyPhoneNoScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while enter the name", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * verify country code and back button is visible
     */

    @Test
    public void verifyCountryCodeScreenAndBackButton() {
        try {
            LOGGER.info("verify country code  screen is visible and back button");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            Assert.assertTrue(onBoardingPage.verifyCountryCodeScreenAndbackButtonIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while displaying the country code screen", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * verify valid phone number
     */

    @Test
    public void verifyValidPhoneNumber() {
        try {

            LOGGER.info("verify that user is able to enter valid phone number");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            Assert.assertTrue(onBoardingPage.verifyEnterTheCodeScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while enter the valid phone number", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /* to verify alresdy registered phone number

     */

    @Test
    public void verifyAlreadyRegisteredPhoneNumber() {
        try {

            LOGGER.info("verify that  phone number is already registered");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            Assert.assertTrue(onBoardingPage.verifyMerchantAccounScreenIsloaded());
            onBoardingPage.backButtonOfMerchantAccounregistration();
            onBoardingPage.enterPhoneNo(phoneNo);
            Assert.assertTrue(onBoardingPage.verifyMerchantAccounScreenIsloaded());
        } catch (Exception exception) {
            LOGGER.error("Error message phone number is already verified", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify valid phone verification code

     */


    @Test
    public void verifyValidPhoneVerificationCode() {
        try {

            LOGGER.info("verify that  user is able to enter  phone verfication code ");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();

            Assert.assertTrue(onBoardingPage.verifyMerchantAccounScreenIsloaded());
        } catch (Exception exception) {
            LOGGER.error("Error while enter the phone verification code", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify register a new xumi account

     */

    @Test
    public void verifyRegisterANewXumiAccountButton() {
        try {

            LOGGER.info("verify that  user is able to select an option register a new Xumi account");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            Assert.assertTrue(onBoardingPage.verifyBusinessNameScreenIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("Error to register a new xumi account", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    @Test
    public void verifyResendCodeButton() {
        try {

            LOGGER.info("verify that after entered wrong verification code resend code displayed");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.incorrectEmailVerificationCode();
            Assert.assertTrue(onBoardingPage.verifyPreviousEmailAddressIsDisplayedByClickingResendBtn());
            Assert.assertEquals("email address is not same", onBoardingPage.enterEmailAddress()
                    , onBoardingPage.verifySameEmailId());
        } catch (Exception exception) {
            LOGGER.error("previous entered email is not same", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the the business address  screen is displayed after entering all
     * valid details in business detail screen
     */

    @Test
    public void verifyBusinessDetailsScreen() {
        try {
            LOGGER.info("verify that user is able to fill all business details");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            Assert.assertTrue(onBoardingPage.verifyBusinessAddressScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while entering business details", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the the bank details screen  is displayed after entering all  valid
     * details merchant address screen
     */

    @Test
    public void verifyMerchantBusinessAddressScreen() {
        try {

            LOGGER.info("verify that user is able to enter all  merchant business address details");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            Assert.assertTrue(onBoardingPage.verifyBankDetailsScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("error while entering business address", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the the global payment screen is displayed after entering all  valid
     * details in bank detail screen
     */

    @Test
    public void verifyBankDetailsScreen() {
        try {
            LOGGER.info("verify that user is able to enter all  merchant business address details");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            Assert.assertTrue(onBoardingPage.verifyGlobalPaymentScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("error while entering bank details", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the Applicant information screen is displayed after entering all
     * valid details in business information screen
     */

    @Test
    public void verifyBusinessInformationDetailsScreen() {
        try {
            LOGGER.info("verify that user is able to enter all business information details");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            Assert.assertTrue(onBoardingPage.verifyApplicantInformationScreenIsVisible());
        } catch (Exception exception) {
            LOGGER.error("error while entering business information", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the user is able to select the options of market category different
     * from the default one
     */
//    @Test
//    public void verifyMarketCategoryScreen() {
//        try {
//            LOGGER.info("verify that user is able to select market category");
//            onBoardingPage = new OnBoardingPage(driver);
//            onBoardingPage.clickLetsGetsStartedButton();
//            onBoardingPage.clickIUnderstandPermissionButton();
//            onBoardingPage.acceptAllPermissions();
//            onBoardingPage.clickOkPICNextButton();
//            onBoardingPage.setPin();
//            onBoardingPage.clickNextButtonofPinScreen();
//            onBoardingPage.setPin();
//            onBoardingPage.enterEmailAddress();
//            onBoardingPage.emailVerificationCode();
//            onBoardingPage.enterYourName(FirstName,LastName);
//            onBoardingPage.countryCode();
//            onBoardingPage.enterPhoneNo(phoneNo);
//            onBoardingPage.phoneNoTextedCode();
//            onBoardingPage.merchantAccountRegistration();
//            onBoardingPage.globalMarketCategoryScreen();
//            Assert.assertTrue(onBoardingPage.verifySelectedMarketCategoryIsSameOnBusinessDetailsScreen());
//        } catch (Exception exception) {
//            LOGGER.error("market category is not same", exception);
//            // Terminating test case execution because of an unexpected
//            // application/environment/network error
//            Assert.fail(exception.getMessage());
//        }
//    }

    /**
     * TCID: To verify that the user is able to select the options from market code screen which is
     * scrollable and back button is functional
     */


    @Test
    public void verifyMarketCodeScreenAndBackButtonOnIt() {
        try {
            LOGGER.info("verify that user is able to select market code and back button is functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.marketCodeScreen();
            Assert.assertTrue(onBoardingPage.verifyMarketCodeScreenIsVisibleAndBackButtonOnIt());
            Assert.assertTrue(onBoardingPage.scrollUpDownAndSelectOptionInMarketCode());
            onBoardingPage.clickBackButtonOfMarketCode();
            Assert.assertTrue(onBoardingPage.verifyBusinessNameScreenIsLoaded());
        } catch (Exception exception) {
            LOGGER.error("error while verifying market code screen", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /**
     * TCID: To verify that the user is able to enter all applicant information and billing
     * screen is visible
     */


    @Test
    public void verifyApplicantInformationDetailsScreen() {
        try {

            LOGGER.info("verify that user is able to enter all Applicant information details");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            Assert.assertTrue(onBoardingPage.verifyBillingInformationScreen());
        } catch (Exception exception) {
            LOGGER.error("error while entering applicant information ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*To verify that state screen and back button is visible  and back btn is functional
    /
     */

    @Test
    public void verifyStateScreenAndBackBtnIsVisibleAndClickable() {
        try {

            LOGGER.info("verify that user navigate to state screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.stateCodeScreen();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToSelectedCityStateAndBackBtnIsVisible());
            onBoardingPage.clickBackButtonOfstateScreen();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBusinessAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to state screen and back btn is " +
                    "not functional and address screen is not visible", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /* To verify that timezone screen  and back button is visible and back btn is functional
    /
     */

    @Test
    public void verifyTimeZoneScreenAndBackBtnIsVisibleAndClickable() {
        try {
            LOGGER.info("verify that user navigate to timeZone screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.timeZoneScreen();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToTimeZoneScreenAndBackBtnIsDisplayed());
            onBoardingPage.timeZoneBackBtnClick();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBusinessAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to timeZone screen and back btn is " +
                    "not functional ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*  To verify that country screen and back button is visible and back btn is functional

     */

    @Test
    public void verifyCountryScreenAndBackBtnIsVisibleAndClickable() {
        try {
            LOGGER.info("verify that user navigate to country screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.countryScreen();
            Assert.assertTrue(onBoardingPage.verifyCountryScreenIsVisibleAndScrollableAndBackBtnIsVisible());
            onBoardingPage.countryScreenBackBtnClick();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBusinessAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to business screen and back btn is " +
                    "not functional ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /* To verify valid details of billing information screen with billing address same as business address

     */
    @Test
    public void verifyValidDetialsOnBillingInformationScreenWithBillingAddressSameAsBusinessAddress() {
        try {
            LOGGER.info("verify that user is able to fill valid details on billing information screen with same address");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            Assert.assertTrue(onBoardingPage.verifyValidDetailsOnBillingInformationScreenAndUserNavigateToTakePictureScreen());
        } catch (Exception exception) {
            LOGGER.error("error while entering valid details on billing information screen and address is not same", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*  To verify valid details of billing information screen with billing address different from  business address

     */

    @Test
    public void verifyValidDetialsOnBillingInformationScreenWithBillingAddressDifferentFromBusinessAddress() {
        try {
            LOGGER.info(" verify that user is able to fill valid details on billing information screen with different address");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.clickCheckBox();
            Assert.assertTrue(onBoardingPage.verifyPrepopulatedCountryAndDetailsAreVisible());
            Assert.assertTrue(onBoardingPage.verifyValidDetailsOnBillingInformationScreenAndUserNavigateToTakePictureScreen());
        } catch (Exception exception) {
            LOGGER.error("error while entering the billing information and address is same", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*To verify taking a picture of void cheque
     */

    @Test
    public void verifyTakingAPictureOfVoidCheque() {
        try {
            LOGGER.info(" verify that user is able to take picture of cheque");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.nextBtnOfBillingInformation();
            onBoardingPage.takeAPicture();
            Assert.assertTrue(onBoardingPage.verifyUserAbleToTakePictureOfVoidCheque());
        } catch (Exception exception) {
            LOGGER.error("error while taking a picture", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }

    /*verify country screen and back btn on billing information screen is visible and clickable

     */
    @Test
    public void verifyCountryScreenAndBackBtnOnBillingInformationIsVisibleAndClickable() {
        try {
            LOGGER.info("verify that user navigate to country screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.clickCheckBox();
            onBoardingPage.countryScreen();
            Assert.assertTrue(onBoardingPage.verifyCountryScreenIsVisibleAndScrollableAndBackBtnIsVisible());
            onBoardingPage.countryScreenBackBtnClick();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBillingAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to billing address screen and back btn is " +
                    "not functional ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify state screen and back btn on billing information is visible and clickable

     */

    @Test
    public void verifystateScreenAndBackBtnOnBillingInformationIsVisibleAndClickable() {
        try {
            LOGGER.info("verify that user navigate to state screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.clickCheckBox();
            onBoardingPage.stateCodeScreen();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToSelectedCityStateAndBackBtnIsVisible());
            onBoardingPage.clickBackButtonOfstateScreen();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBillingAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to billing address screen and back btn is " +
                    "not functional ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify time zone screen ,back btn on billing information screen is visible and clickable

     */

    @Test
    public void verifyTimeZoneScreenAndBackBtnOnBillingInformationIsVisibleAndClickable() {
        try {
            LOGGER.info("verify that user navigate to Timezone screen and back button is visible and functional");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.clickCheckBox();
            onBoardingPage.clickTimeZone();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToTimeZoneScreenAndBackBtnIsDisplayed());
            onBoardingPage.timeZoneBackBtnClick();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateBackToBillingAddressScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to billing address screen and back btn is " +
                    "not functional ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify subit button on confirm submission page after check the checkbox

     */

    @Test
    public void verifySubmitButtonOnConfirmSubmissionScreenAfterCheck() {
        try {
            LOGGER.info("verify that user able to check the check box and navigate to next screen");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.nextBtnOfBillingInformation();
            onBoardingPage.takeAPicture();
            onBoardingPage.clickPictureOkBtnId();
            // onBoardingPage.confirmSubmittionForm();
            onBoardingPage.clickOnICofirmCheckbox();
            Assert.assertTrue(onBoardingPage.verifyIConfirmCheckbox());
            onBoardingPage.clickSubmitBtnid();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToApplicationStatusScreen());
        } catch (Exception exception) {
            LOGGER.error("user not able to navigate to application status screen ", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify application status is approved for auto approved emails

     */

    @Test
    public void verifyApplicationStatusIsApprovedForAutoApprovedEmails() {
        try {
            LOGGER.info("verify application status is showing approved for auto approved emails");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.nextBtnOfBillingInformation();
            onBoardingPage.takeAPicture();
            onBoardingPage.clickPictureOkBtnId();
            onBoardingPage.clickOnICofirmCheckbox();
            onBoardingPage.clickSubmitBtnid();
            Assert.assertTrue(onBoardingPage.verifyApplicationStatusIsShowingApproved());
        } catch (Exception exception) {
            LOGGER.error("application status is not approved for auto approved email", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
    /*verify vallid details of admin terminal id

     */

    @Test
    public void verifyvalidDetailsForAdminTerminalId() {
        try {
            LOGGER.info("verify user able to enter valid terminal name and select terminal Id");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.nextBtnOfBillingInformation();
            onBoardingPage.takeAPicture();
            onBoardingPage.clickPictureOkBtnId();
            onBoardingPage.clickOnICofirmCheckbox();
            onBoardingPage.clickSubmitBtnid();
            onBoardingPage.applicationStatus();
            onBoardingPage.terminalIdPage(administrationPaymentTerminal);
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToYouAreSetPage());
        } catch (Exception exception) {
            LOGGER.error("user not able to select terminal id  and navigate to next page", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }


    }
    /*verify home page on successful onboarding

     */

    @Test
    public void verifyHomeScreenAfterSuccessfulOnBoarding() {
        try {
            LOGGER.info("verify user able to navigate to home screen on successful onbarding");
            onBoardingPage = new OnBoardingPage(driver);
            onBoardingPage.clickLetsGetsStartedButton();
            onBoardingPage.clickIUnderstandPermissionButton();
            onBoardingPage.acceptAllPermissions();
            onBoardingPage.clickOkPICNextButton();
            onBoardingPage.setPin();
            onBoardingPage.clickNextButtonofPinScreen();
            onBoardingPage.setPin();
            onBoardingPage.enterEmailAddress();
            onBoardingPage.emailVerificationCode();
            onBoardingPage.enterYourName(FirstName, LastName);
            onBoardingPage.countryCode(countryCodeId);
            onBoardingPage.enterPhoneNo(phoneNo);
            onBoardingPage.phoneNoTextedCode();
            onBoardingPage.merchantAccountRegistration();
            onBoardingPage.businessDetailsScreen(businessName,MarketCode);
            onBoardingPage.merchantAddressScreen(address, city, postalCode);
            onBoardingPage.merchantBankDetails(banktransitNo, bankInstitutionNo, bankAccountNo);
            onBoardingPage.globalpayment();
            onBoardingPage.businessInformation(productOrService, yearsInBusiness, website, existingMerchantAccount);
            onBoardingPage.applicantInformation(applicantPosition, applicantSin);
            onBoardingPage.billingInformation(averageTransaction, highesttransaction, averageAnnualVolume);
            onBoardingPage.nextBtnOfBillingInformation();
            onBoardingPage.takeAPicture();
            onBoardingPage.clickPictureOkBtnId();
            onBoardingPage.clickOnICofirmCheckbox();
            onBoardingPage.clickSubmitBtnid();
            onBoardingPage.applicationStatus();
            onBoardingPage.terminalIdPage(administrationPaymentTerminal);
            onBoardingPage.tapYourphone();
            Assert.assertTrue(onBoardingPage.verifyUserNavigateToHomeScreen());
        } catch (Exception exception) {
            LOGGER.error("home screen is not displayed", exception);
            // Terminating test case execution because of an unexpected
            // application/environment/network error
            Assert.fail(exception.getMessage());
        }
    }
}





































