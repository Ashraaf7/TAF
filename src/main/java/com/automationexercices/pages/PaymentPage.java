package com.automationexercices.pages;

import com.automationexercices.FileUtils;
import com.automationexercices.drivers.GUIDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private GUIDriver driver;

    public PaymentPage(GUIDriver driver) {
        this.driver = driver;
    }

    //vars
    private String paymentEndpoint = "/payment";

    //locators
    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By cardCvc = By.name("cvc");
    private final By cardMonthExpiration = By.name("expiry_month");
    private final By cardYearExpiration = By.name("expiry_year");
    private final By payButton = By.id("submit");
    private final By paymentSuccessMessage = By.cssSelector("h2 > b");
    private final By downloadInvoiceButton = By.xpath("//a[.='Download Invoice']");

    //actions
    @Step("Fill card info")
    public PaymentPage fillCardInfo(String nameOnCard, String cardNumber, String cardCvc, String cardMonthExpiration, String cardYearExpiration) {
        driver.element().type(this.nameOnCard, nameOnCard)
                .type(this.cardNumber, cardNumber)
                .type(this.cardCvc, cardCvc)
                .type(this.cardMonthExpiration, cardMonthExpiration)
                .type(this.cardYearExpiration, cardYearExpiration)
                .click(payButton);
        return this;
    }

    @Step("Click on download invoice button")
    public PaymentPage clickOnDownloadInvoiceButton() {
        driver.element().click(downloadInvoiceButton);
        return this;
    }

    //validations
    @Step("Verify payment success message")
    public PaymentPage verifyPaymentSuccessMessage(String expectedMessage) {
        driver.verification().Equals(driver.element().getText(paymentSuccessMessage), expectedMessage, "Payment success message");
        return this;
    }

    public PaymentPage verifyDownloadedFile(String invoiceName) {
        driver.verification().assertFileExists(invoiceName, "File is not existed");
        return this;
    }
}
