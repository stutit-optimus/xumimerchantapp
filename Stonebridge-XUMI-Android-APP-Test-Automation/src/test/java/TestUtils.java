import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.TimeZone;

/**
 * This class is an utility class and has all the functions which are not directly related to the AUT
 *
 * @author Optimus
 */
public class TestUtils {
    public static final Logger LOGGER = Logger.getLogger(TestUtils.class);
    private static final String PROPERTIES_FILE = "setup.properties";
    private static final String TEST_REPORT_FILE = "cloudReports.txt";
    private static final String APP_ID = "appId";
    private static final String LOG_4J_PATH = "log4jPath";
    private static final String LOG_LEVEL = "logLevel";
    private static final String RETRY_COUNT = "retryCount";
    private static final String GMT_TEXT = "(GMT +";
    private static final String CLEAR_REPORTS_INFO = "Deleting the previous report Urls";
    private static final String SET_REPORTS_INFO = "Adding the report URLs into the reports file";
    private static final String SET_REPORTS_ERROR = "Error while adding the report URLs into the reports file";
    private static final String SET_PROPERTY_INFO = "Setting the value against a property in ";
    private static final String SET_PROPERTY_ERROR = "Error while setting the property in setup.properties file";
    private static final String GET_PROPERTY_INFO = "Fetching the property value from setup.properties file";
    private static final String GET_PROPERTIES_INFO = "Fetching all the properties";
    private static final String GET_PROPERTIES_ERROR = "Received an exception while fetching all the properties";
    private static final String GET_PROPERTIES_FILE_MISSING_ERROR = "Unable to find the specified file while fetching all the properties";
    private static final String GET_CURRENT_DATE_INFO = "Fetching and returning the current date";
    private static final String GET_CURRENT_DATE_ERROR = "Received an errot while fetching or returning the current date";
    private static final String LOAD_PROPERTIES_FILE_INFO = "Loading the properties file based on the fileName received in the parameters";
    private static final String CLOSED_BRACKET_TEXT = ")";
    private static final String EQUAL_TO_TEXT = " = ";
    public static String suiteTime;
    public static String appId = "app-emu-debug.apk";
    public static String log4jPath = "\\src\\main\\resources\\log4j.properties";
    public static String logLevel = "INFO";
    public static int retryCount;

    /**
     * This method fetches and returns the current date time of the machine
     *
     * @return String
     */
    public static String getCurrentDate() {
        try {
            LOGGER.info(GET_CURRENT_DATE_INFO);
            SimpleDateFormat sdformat = new SimpleDateFormat("dd MMM yyyy hh:mm a z");
            Calendar calendar = Calendar.getInstance();
            suiteTime = sdformat.format(calendar.getTime());
            TimeZone mTimeZone = calendar.getTimeZone();
            float offsetValue = mTimeZone.getRawOffset();
            offsetValue = offsetValue / (1000 * 60 * 60);
            suiteTime = suiteTime.concat(GMT_TEXT).concat(String.valueOf(offsetValue)).concat(CLOSED_BRACKET_TEXT);
        } catch (Exception exception) {
            BaseTest.LOGGER.error(GET_CURRENT_DATE_ERROR, exception);
        }
        return suiteTime;
    }

    /**
     * This method loads the properties file based on the fileName received in the parameters
     *
     * @param fileName name of the properties file
     * @return Properties Object
     */
    public static Properties loadPropertiesFile(String fileName) {
        LOGGER.info(LOAD_PROPERTIES_FILE_INFO);
        Properties properties = new Properties();
        try {
            InputStream in;
            in = new FileInputStream(fileName);
            //Properties file Loaded
            properties.load(in);
        } catch (FileNotFoundException exception) {
            BaseTest.LOGGER.error(GET_PROPERTIES_FILE_MISSING_ERROR, exception);
        } catch (IOException exception) {
            BaseTest.LOGGER.error(GET_PROPERTIES_ERROR, exception);
        }
        return properties;
    }

    /**
     * This method fetches all the properties defined in the setup.properties
     */
    public static void getProperties() {
        LOGGER.info(GET_PROPERTIES_INFO);
        Properties prop = loadPropertiesFile(PROPERTIES_FILE);
        log4jPath = prop.getProperty(LOG_4J_PATH);
        logLevel = prop.getProperty(LOG_LEVEL);
        appId = prop.getProperty(APP_ID);
        retryCount = Integer.parseInt(prop.getProperty(RETRY_COUNT));
    }

    /**
     * This method sets the test reports urls into a different reports file
     *
     * @param methodName test case name
     * @param reportUrl  URL of the specified test case
     */
    public static void setReports(String methodName, Object reportUrl) {
        LOGGER.info(SET_REPORTS_INFO);
        PrintWriter pw;
        try {
            File file = new File(TEST_REPORT_FILE);
            if (!file.exists()) {
                file.createNewFile();
            }
            pw = new PrintWriter(new FileWriter(TEST_REPORT_FILE, true));
            pw.write(methodName.concat(EQUAL_TO_TEXT));
            pw.write(reportUrl.toString());
            pw.println();

            pw.close();
        } catch (IOException exception) {
            BaseTest.LOGGER.error(SET_REPORTS_ERROR, exception);
        }
    }

    /**
     * This method clears all the entries in the reports file
     */
    public static void clearReports() {
        LOGGER.info(CLEAR_REPORTS_INFO);
        File reportsFile = new File(TEST_REPORT_FILE);
        if (reportsFile.exists()) {
            reportsFile.delete();
        }
    }


    /**
     * This method sets the property in the setup.properties file
     *
     * @param propertyName name of the property
     * @param value        value against that property
     */
    public static void setProperty(String propertyName, String value, String propertiesFile) {
        LOGGER.info(SET_PROPERTY_INFO + propertiesFile + " file");
        try {
            Properties prop = loadPropertiesFile(propertiesFile);
            prop.setProperty(propertyName, value);
            OutputStream out;
            out = new FileOutputStream(propertiesFile);
            prop.store(out, "Property Saved");
        } catch (IOException exception) {
            BaseTest.LOGGER.error(SET_PROPERTY_ERROR, exception);
        }
    }

    /**
     * This method gets the property from the setup.properties file
     *
     * @param propertyName name of the property
     */
    public static String getProperty(String propertyName) {
        LOGGER.info(GET_PROPERTY_INFO);
        String value;
        BaseTest.LOGGER.info(GET_PROPERTIES_INFO);
        Properties prop = loadPropertiesFile(PROPERTIES_FILE);
        value = prop.getProperty(propertyName);
        return value;
    }

}
