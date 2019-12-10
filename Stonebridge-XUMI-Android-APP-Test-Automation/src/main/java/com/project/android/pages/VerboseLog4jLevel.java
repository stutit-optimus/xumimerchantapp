package com.project.android.pages;

import org.apache.log4j.Level;

/**
 * This class is a custom log level created so that the level of detail can be managed
 *
 * @author optimus
 */

@SuppressWarnings("serial")
public class VerboseLog4jLevel extends Level {

    /**
     * Value of VerboseLog4jLevel level. This value is lesser than INFO_INT and higher
     * than DEBUG_INT
     */
    public static final int VERBOSE_INT = INFO_INT - 10;

    /**
     * Level representing log level
     */
    public static final Level VERBOSE = new VerboseLog4jLevel(VERBOSE_INT, "VERBOSE", 10);

    /**
     * Constructor
     */
    protected VerboseLog4jLevel(int arg0, String arg1, int arg2) {
        super(arg0, arg1, arg2);

    }

    /**
     * Checks whether logArgument is "VERBOSE" level. If yes then returns
     * VERBOSE}, else calls VerboseLog4jLevel#toLevel(String, Level) passing
     * it Level#DEBUG as the defaultLevel.
     */
    public static Level toLevel(String logArgument) {
        if (logArgument != null && logArgument.toUpperCase().equals("VERBOSE")) {
            return VERBOSE;
        }
        return (Level) toLevel(logArgument);
    }

    /**
     * Checks whether val is VerboseLog4jLevel#VERBOSE_INT. If yes then
     * returns VerboseLog4jLevel#VERBOSE, else calls
     * VerboseLog4jLevel#toLevel(int, Level) passing it Level#INFO as the
     * defaultLevel
     */
    public static Level toLevel(int val) {
        if (val == VERBOSE_INT) {
            return VERBOSE;
        }
        return (Level) toLevel(val, Level.INFO);
    }

    /**
     * Checks whether val is VerboseLog4jLevel#VERBOSE_INT. If yes
     * then returns VerboseLog4jLevel#VERBOSE, else calls Level#toLevel(int, org.apache.log4j.Level)
     */
    public static Level toLevel(int val, Level defaultLevel) {
        if (val == VERBOSE_INT) {
            return VERBOSE;
        }
        return Level.toLevel(val, defaultLevel);
    }

    /**
     * Checks whether logArgument is "VERBOSE" level. If yes then returns
     * VerboseLog4jLevel#VERBOSE, else calls
     * Level#toLevel(java.lang.String, org.apache.log4j.Level)
     */
    public static Level toLevel(String logArgument, Level defaultLevel) {
        if (logArgument != null && logArgument.toUpperCase().equals("VERBOSE")) {
            return VERBOSE;
        }
        return Level.toLevel(logArgument, defaultLevel);
    }
}