package com.automationexercices.validations;

import com.automationexercices.utils.logs.LogsManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

//Soft Assertion
public class Validation extends BaseAssertion{
    private static  SoftAssert softAssert = new SoftAssert();
    private static boolean used = false; // Flag to track usage
    public Validation() {
        super();
    }
    public Validation(WebDriver driver) {
        super(driver);
    }

    @Override
    protected void assertTrue(boolean condition, String message) {
        used = true; // Mark that an assertion was made
        softAssert.assertTrue(condition, message);
    }

    @Override
    protected void assertFalse(boolean condition, String message) {
        used = true; // Mark that an assertion was made
        softAssert.assertFalse(condition, message);
    }

    @Override
    protected void assertEquals(String actual, String expected, String message) {
        used = true; // Mark that an assertion was made
        softAssert.assertEquals(actual, expected, message);
    }
    public static void assertAll(ITestResult result) {
        if (!used) return; // If no assertions were made, do nothing
       try {
            softAssert.assertAll();
       }
       catch (AssertionError e)
       {
           LogsManager.error("Assertion failed:", e.getMessage());
           result.setStatus(ITestResult.FAILURE);
           result.setThrowable(e);
       }
       finally {
            softAssert = new SoftAssert(); // Reset the soft assert instance
       }
    }
}
