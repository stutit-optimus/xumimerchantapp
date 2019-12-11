import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.Factory;
import com.project.android.pages.AbstractPage;
import com.project.android.pages.HomePage;
import com.project.android.pages.Onboarding.OnBoardingPage;
import com.project.android.pages.Onboarding.SplashPage;
import com.project.android.pages.UserProfilePage;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;
import java.time.Clock;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidKeyCode;


/**
 * This class instantiates Android driver and includes test steps that are
 * common to all modules.
 *
 * @author Optimus
 */
public class BaseTest {
    public static final Logger LOGGER = Logger.getLogger(BaseTest.class);
    private static final String JSON_FILE_NAME = "BaseTest.json";
    private static final String INITIALIZE_INFO = "Initializing test data from BaseTest.json file";
    private static final String CONSTRUCTOR_INFO = "In BaseTest() constructor";
    private static final String CONSTRUCTOR_ERROR = "Thrown by constructor: BaseTest()";
    private static final String TEAR_DOWN_INFO = "Quitting driver";
    private static final String GET_COMPANY_CONFIG_INFO = "Instantiating JSON Object for .json files";
    private static final String LOAD_LOGIN_PAGE_INFO = "Instantiating android driver";
    private static final int NUMERAL_FIFTY = 50;
    public static String androidVersion;
    public static long initialTimeMillis = 0;
    public static DesiredCapabilities capabilities;
    public static EnhancedAndroidDriver<MobileElement> driver;
    public static String userNameAdmin;
    public static String xumiPassword;
    public static String androidPassword;
    private static int counter;
    private JSONObject companyConfig;
    private JSONObject phoneConfiguration;
    private JSONObject xumiConfiguration;
    public SplashPage splashPage;
    public OnBoardingPage onBoardingPage;
    public UserProfilePage userProfilePage;
    private String userFirstName;
    private String userLastName;
    private String userPhoneNumber;
    private String userAddressLine;
    private String userAddressCity;
    private String userAddressZipCode;
    private String userAddressState;
    private String creditCardNumber;
    private String creditCardCvc;
    private JSONObject userDetails;
    private JSONObject userAddress;
    private JSONObject userCreditCard;
    private JSONObject userName;
    private String FirstName;
    private String LastName;

    private String emailAddress = null;
    private static boolean flag = true;
    public static WebDriverWait wait;
    private static By screenLockButton = By.xpath("//android.widget.TextView[@text='Screen lock']");
    private static By pinButton = By.xpath("//android.widget.TextView[@text='PIN']");
    private static By OkButton = By.id("android:id/button1");
    private static By yesButton = By.id("com.android.settings:id/encrypt_require_password");
    private static By passwordTextId = By.id("com.android.settings:id/password_entry");
    private static By nextButtonId = By.id("com.android.settings:id/next_button");
    private static By doneButtonId = By.id("com.android.settings:id/redaction_done_button");
    private static By noneButtonId = By.xpath("//android.widget.TextView[@text='None']");

    static {
        TestUtils.setProperty("log4j.appender.stdout.Threshold", TestUtils.logLevel, System.getProperty("user.dir").concat(TestUtils.log4jPath));
        PropertyConfigurator.configure(System.getProperty("user.dir").concat(TestUtils.log4jPath));
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        TestUtils.retryCount = 2;
    }

    @Rule
    public TestName testName = new TestName();

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        protected void succeeded(final Description description) {
            LOGGER.info(String.format("SUCCESS: %s", description));
            driver.pressKeyCode(AndroidKeyCode.HOME);
        }

        @Override
        protected void failed(final Throwable e, final Description description) {
            LOGGER.error(String.format("FAILED: %s", description));
            takeScreenshot((testName.getMethodName()));
            driver.pressKeyCode(AndroidKeyCode.HOME);
        }

        @Override
        protected void starting(final Description description) {
            LOGGER.info(String.format("STARTING: %s", description));
            if (initialTimeMillis != 0) {
                long seconds = (TimeUnit.MILLISECONDS.toSeconds(initialTimeMillis)) - (TimeUnit.MILLISECONDS.
                        toSeconds(System.currentTimeMillis()));
                long quotient = seconds / 60;
                long remainder = seconds % 60;
                LOGGER.info("Test Case start Time: ".concat(String.valueOf(quotient)).concat(" minutes").
                        concat(String.valueOf(remainder)).concat(" seconds").concat(" from build start time"));
            }
        }
    };
    @Rule
    public Retry retry = new Retry(TestUtils.retryCount);


    /**
     * Constructor
     *
     * @throws JSONException
     */
    public BaseTest() throws JSONException {
        LOGGER.info(CONSTRUCTOR_INFO);
        try {
            companyConfig = this.getCompanyConfig(JSON_FILE_NAME);
            phoneConfiguration = companyConfig.getJSONObject("phoneConfiguration");
            xumiConfiguration = companyConfig.getJSONObject("xumiConfiguration");
            userDetails = companyConfig.getJSONObject("userDetails");
            userAddress = companyConfig.getJSONObject("userAddress");
            userCreditCard = companyConfig.getJSONObject("userCreditCard");
            userName=companyConfig.getJSONObject("userName");
            initialize();
        } catch (JSONException exception) {
            LOGGER.error(CONSTRUCTOR_ERROR);
            throw exception;
        }
    }


    /**
     * This method reads test data from BaseTest.json
     *
     * @throws JSONException
     */
    public void initialize() throws JSONException {
        LOGGER.info(INITIALIZE_INFO);
        userNameAdmin = xumiConfiguration.getString("userName");
        xumiPassword = xumiConfiguration.getString("password");
        androidPassword = phoneConfiguration.getString("androidPassword");
        userFirstName = userDetails.getString("firstName");
        userLastName = userDetails.getString("lastName");
        userPhoneNumber = userDetails.getString("phoneNumber");
        userAddressLine = userAddress.getString("addressLine");
        userAddressCity = userAddress.getString("city");
        userAddressZipCode = userAddress.getString("zipCode");
        userAddressState = userAddress.getString("state");
        creditCardNumber = userCreditCard.getString("cardNumber");
        creditCardCvc = userCreditCard.getString("cvc");
        FirstName=userName.getString("Firstname");
        LastName=userName.getString("LastName");


    }

    /**
     * Instantiate AndroidDriver
     *
     * @throws MalformedURLException
     * @throws JSONException
     */
    @BeforeClass
    public static void instantiateDriver() throws IOException, JSONException, InterruptedException {
        LOGGER.info(LOAD_LOGIN_PAGE_INFO);
        String appId = "app-emu-debug.apk";
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("noReset", "true");
        try {
            capabilities.setCapability("deviceName", "Pixel2");
            capabilities.setCapability("app", System.getProperty("user.dir").concat("/").concat(appId));
            capabilities.setCapability("appPackage", "com.stonebridge.xumi.android.xumimerchant.emu");
            capabilities.setCapability("appActivity", "com.stonebridge.xumi.android.xumimerchant.ui.screens.SplashActivity");
            driver = Factory.createAndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            BaseTest.initialTimeMillis = System.currentTimeMillis();
            setAndroidPin();
        } catch (Exception exception) {
            LOGGER.error("Something wrong happened while starting the test case session", exception);
        }
    }

    @AfterClass
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Before
    public void beforeTest() {
        try {
            androidVersion = AbstractPage.parseAndroidVersion(driver.getCapabilities().getCapability("platformVersion").toString());
            driver.manage().timeouts().implicitlyWait(NUMERAL_FIFTY, TimeUnit.SECONDS);
            driver.resetApp();
        } catch (Exception exception) {
            LOGGER.error("Something wrong happened while starting the test case session", exception);

        }
    }

    /**
     * Quit AndroidDriver
     */
    @After
    public void tearDown() throws InterruptedException {
        try {
            LOGGER.info(TEAR_DOWN_INFO);
        } catch (Exception exception) {
            LOGGER.error("Something wrong happened while ending the test case session", exception);

        }
    }

    /**
     * This method return a reference to the json file whose name is passed as
     * an argument to the method
     *
     * @param jsonFile Name of the json file
     * @return object Reference to the json file
     * @throws JSONException
     */
    protected JSONObject getCompanyConfig(String jsonFile) throws JSONException {
        LOGGER.info(GET_COMPANY_CONFIG_INFO);
        JSONObject object;
        InputStream input;
        input = this.getClass().getClassLoader().getResourceAsStream((jsonFile));
        object = new JSONObject(new JSONTokener(new InputStreamReader(input)));
        return object;
    }

    protected void takeScreenshot(String screenshotName) {
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
     * Scrolling to the element specified by text
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

    /**
     * This method completes the onboarding process and
     * returns the instance of HomePage class
     */
    public HomePage completeOnboarding() throws InterruptedException {
        LOGGER.info("Verify user is navigated to Home page after completing onboarding successfully");
        splashPage = new SplashPage(driver);
        splashPage.clickLetsGetsStartedButton();
        splashPage.clickNextButton();
        splashPage.clickIUnderstandPermissionButton();
        splashPage.acceptAllPermissions();
        splashPage.enterAndroidPassword(androidPassword);
        onBoardingPage = splashPage.setXumiPassword();
        onBoardingPage.enterPhoneNumber(userPhoneNumber, true);
        onBoardingPage.enterPin(xumiPassword);
        onBoardingPage.clickBackButton();
        onBoardingPage.enterUserName(userFirstName, userLastName, true);
        userProfilePage = onBoardingPage.enterEmailAddress(emailAddress, true);
        userProfilePage.enterUserAddress(userAddressLine, userAddressCity, userAddressZipCode, userAddressState, true);
        userProfilePage.addCreditCard(creditCardNumber, creditCardCvc, userFirstName +" "+ userLastName, true);
        // To cancel NFC set up
        driver.findElement(By.id("android:id/button2")).click();
        driver.runAppInBackground(1);
        return new HomePage(driver);
    }

    private class Retry implements TestRule {
        private int retryCount;

        public Retry(int retryCount) {
            this.retryCount = retryCount;
        }

        public Statement apply(Statement base, Description description) {
            return statement(base, description);
        }

        private Statement statement(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    Throwable caughtThrowable = null;

                    // implement retry logic here
                    for (int i = 0; i < retryCount; i++) {
                        try {
                            base.evaluate();
                            return;
                        } catch (Throwable t) {
                            caughtThrowable = t;
                            System.err.println(description.getDisplayName() + ": run " + (i + 1) + " failed");
                        }
                    }
                    System.err.println(description.getDisplayName() + ": giving up after " + retryCount + " failures");
                    throw caughtThrowable;
                }
            };
        }
    }

    private static void setAndroidPin() {
        if (driver.findElements(OkButton).size() > 0) {
            wait = new WebDriverWait(driver, NUMERAL_FIFTY);
            wait.until(ExpectedConditions.presenceOfElementLocated(OkButton));
            driver.findElement(OkButton).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(screenLockButton));
            driver.findElement(screenLockButton).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(pinButton));
            driver.findElement(pinButton).click();
            if (driver.findElements(yesButton).size() > 0) {
                wait.until(ExpectedConditions.presenceOfElementLocated(yesButton));
                driver.findElement(yesButton).click();
                wait.until(ExpectedConditions.presenceOfElementLocated(OkButton));
                driver.findElement(OkButton).click();
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(passwordTextId));
            driver.findElement(passwordTextId).sendKeys("1234");
            wait.until(ExpectedConditions.presenceOfElementLocated(nextButtonId));
            driver.findElement(nextButtonId).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(passwordTextId));
            driver.findElement(passwordTextId).sendKeys("1234");
            wait.until(ExpectedConditions.presenceOfElementLocated(nextButtonId));
            driver.findElement(nextButtonId).click();
            if (driver.findElements(doneButtonId).size() > 0) {
                wait.until(ExpectedConditions.presenceOfElementLocated(doneButtonId));
                driver.findElement(doneButtonId).click();
            } else if (driver.findElements(nextButtonId).size() > 0) {
                wait.until(ExpectedConditions.presenceOfElementLocated(nextButtonId));
                driver.findElement(nextButtonId).click();
            }
            driver.pressKeyCode(AndroidKeyCode.HOME);
            flag = false;
        }
    }

    public static void removeAndroidPin(){
        LOGGER.info("Removing the device PIN lock");
        driver.startActivity("com.android.settings", "com.android.settings.Settings$SecurityDashboardActivity");
        wait = new WebDriverWait(driver, NUMERAL_FIFTY);
        wait.until(ExpectedConditions.presenceOfElementLocated(screenLockButton));
        driver.findElement(screenLockButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordTextId));
        driver.findElement(passwordTextId).sendKeys("1234");
        driver.pressKeyCode(AndroidKeyCode.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(noneButtonId));
        driver.findElement(noneButtonId).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(OkButton));
        driver.findElement(OkButton).click();
        driver.pressKeyCode(AndroidKeyCode.HOME);
        LOGGER.info("Successfully removed the device PIN lock");
        driver.resetApp();
    }
}
