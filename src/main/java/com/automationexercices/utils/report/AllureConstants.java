package com.automationexercices.utils.report;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.automationexercices.utils.dataReader.PropertyReader.getProperty;

public class AllureConstants {
    //Paths > final - static
    public static final Path USER_DIR = Paths.get(getProperty("user.dir"), File.separator);
    public static final Path USER_HOME = Paths.get(getProperty("user.home"), File.separator);

    public static final Path RESULTS_FOLDER = Paths.get(String.valueOf(USER_DIR),"test-output", "allure-results",File.separator);
    public static final Path REPORT_PATH = Paths.get(String.valueOf(USER_DIR),"test-output", "reports",File.separator); //single report .html
    public static final Path FULL_REPORT_PATH = Paths.get(String.valueOf(USER_DIR),"test-output", "full-report",File.separator);

    public static final Path HISTORY_FOLDER= Paths.get(FULL_REPORT_PATH.toString(), "history", File.separator); //after allure generate
    public static final Path RESULTS_HISTORY_FOLDER= Paths.get(RESULTS_FOLDER.toString(), "history", File.separator); // copy generated history to it

    public static final String INDEX_HTML = "index.html";
    public static final String REPORT_PREFIX = "AllureReport_"; //timestamp
    public static final String REPORT_EXTENSION = ".html";

    public static final String ALLURE_ZIP_BASE_URL = "https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/";

    public static final Path EXTRACTION_DIR = Paths.get(String.valueOf(USER_HOME),".m2/repository/allure",File.separator);
}
