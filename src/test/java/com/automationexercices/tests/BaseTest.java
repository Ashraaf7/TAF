package com.automationexercices.tests;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.drivers.WebDriverProvider;
import com.automationexercices.utils.dataReader.JsonReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest implements WebDriverProvider {
    protected GUIDriver driver;
    protected JsonReader testData;





    @Override
    public WebDriver getWebDriver() {
        return driver.get();
    }
}
