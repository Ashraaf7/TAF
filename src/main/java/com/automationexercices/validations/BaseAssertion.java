package com.automationexercices.validations;

import com.automationexercices.FileUtils;
import com.automationexercices.utils.WaitManager;
import com.automationexercices.utils.actions.ElementActions;
import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.automationexercices.utils.report.AllureConstants.USER_DIR;
import static java.nio.file.Paths.*;

public abstract class BaseAssertion {
    protected WebDriver driver;
    protected WaitManager waitManager;
    protected ElementActions elementActions;

    protected BaseAssertion() {

    }

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
        this.elementActions = new ElementActions(driver);
    }

    protected abstract void assertTrue(boolean condition, String message);

    protected abstract void assertFalse(boolean condition, String message);

    protected abstract void assertEquals(String actual, String expected, String message);

    public BaseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    public void isElementVisible(By locator) {
        boolean flag = waitManager.fluentWait().until(driver1 ->
        {
            try {
                driver1.findElement(locator).isDisplayed();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
        assertTrue(flag, "Element is not visible: " + locator);
    }

    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }
    public boolean doesFileExist(String fileName, int numberOfRetries) {
        boolean doesFileExit = false;
        int i = 0;
        while (i < numberOfRetries) {
            try {
                String filePath = USER_DIR + "/src/test/resources/downloads/" ;
                doesFileExit = (new File(filePath + fileName)).getAbsoluteFile().exists();
            } catch (Exception rootCauseException) {
                LogsManager.error( rootCauseException.getMessage());
            }
            if (!doesFileExit) {
                try {
                    Thread.sleep(500);
                } catch (Exception rootCauseException) {
                    LogsManager.error(rootCauseException.getMessage());
                }
            }
            i++;
        }
        return doesFileExit;
    }

    // Verify that file exists
    public void assertFileExists(String fileName, String message) {
        boolean exists = doesFileExist(fileName,3);
        assertTrue(exists, message); // Use the result from wait instead of re-checking
    }

}
