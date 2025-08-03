package com.automationexercices.pages;

import com.automationexercices.drivers.GUIDriver;
import com.automationexercices.pages.components.NavigationBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class SignupPage {
    private final GUIDriver driver;

    public SignupPage(GUIDriver driver) {
        this.driver = driver;
    }

    //locators
    private final By name = By.cssSelector("[data-qa=\"name\"]");
    private final By email = By.cssSelector("[data-qa=\"email\"]");
    private final By password = By.id("password");
    private final By day = By.id("days");
    private final By month = By.id("months");
    private final By year = By.id("years");
    private final By newsletter = By.id("newsletter");
    private final By specialOffers = By.id("optin");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By company = By.id("company");
    private final By address1 = By.id("address1");
    private final By address2 = By.id("address2");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipcode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("[data-qa=\"create-account\"]");
    private final By accountCreatedLabel = By.tagName("p");
    private final By continueButton = By.cssSelector("[data-qa=\"continue-button\"]");


    //actions
    @Step("Choose title {title}") //Mr - Mrs
    private SignupPage chooseTitle(String title) {
        // Implementation for choosing title
        By titleLocator = By.xpath("//input [@value='" + title + "']");
        driver.element().click(titleLocator);
        return this;
    }

    @Step("Fill Registration Form")
    public SignupPage fillRegisterationForm(String title,
                                            String passwordText,
                                            String dayText,
                                            String monthText,
                                            String yearText,
                                            String firstNameText,
                                            String lastNameText,
                                            String companyText,
                                            String address1Text,
                                            String address2Text,
                                            String countryText,
                                            String stateText,
                                            String cityText,
                                            String zipcodeText,
                                            String mobileNumberText) {

        chooseTitle(title);
        driver.element().type(password, passwordText);
        driver.element().selectFromDropdown(day, dayText);
        driver.element().selectFromDropdown(month, monthText);
        driver.element().selectFromDropdown(year, yearText);
        driver.element().click(newsletter);
        driver.element().click(specialOffers);
        driver.element().type(firstName, firstNameText);
        driver.element().type(lastName, lastNameText);
        driver.element().type(company, companyText);
        driver.element().type(address1, address1Text);
        driver.element().type(address2, address2Text);
        driver.element().selectFromDropdown(country, countryText);
        driver.element().type(state, stateText);
        driver.element().type(city, cityText);
        driver.element().type(zipcode, zipcodeText);
        driver.element().type(mobileNumber, mobileNumberText);
        return this;
    }
    @Step("Click Create Account Button")
    public SignupPage clickCreateAccountButton() {
        driver.element().click(createAccountButton);
        return this;
    }

    @Step("Click Continue Button")
    public NavigationBarComponent clickContinueButton() {
        driver.element().click(continueButton);
        return  new NavigationBarComponent(driver);
    }


    //validations
    @Step("Verify Account Created")
    public SignupPage verifyAccountCreated() {
        driver.verification().isElementVisible(accountCreatedLabel);
        return this;
    }
}
