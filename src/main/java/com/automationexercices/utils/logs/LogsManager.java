package com.automationexercices.utils.logs;

import com.automationexercices.utils.report.AllureConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

public class LogsManager {
    public static final String LOGS_PATH = AllureConstants.USER_DIR + "/test-output/Logs/";

    private static Logger logger() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[3].getClassName());
    }

    //info("errorMessage"+ " " +e.getMessage()); > //info("errorMessage",e.getMessage());
    public static void info(String... message) {
        logger().info(String.join(" ", message));
    }

    public static void error(String... message) {
        logger().error(String.join(" ", message));
    }

    public static void warn(String... message) {
        logger().warn(String.join(" ", message));
    }

    public static void fatal(String... message) {
        logger().fatal(String.join(" ", message));
    }

    public static void debug(String... message) {
        logger().debug(String.join(" ", message));
    }
}
