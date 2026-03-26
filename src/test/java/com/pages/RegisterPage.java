package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;

    // ✅ Locators using XPath
    By registerLink = By.xpath("//a[@class='ico-register']");
    By genderMale = By.xpath("//input[@id='gender-male']");
    By firstNameInput = By.xpath("//input[@id='FirstName']");
    By lastNameInput = By.xpath("//input[@id='LastName']");
    By emailInput = By.xpath("//input[@id='Email']");
    By passwordInput = By.xpath("//input[@id='Password']");
    By confirmPasswordInput = By.xpath("//input[@id='ConfirmPassword']");
    By registerBtn = By.xpath("//input[@id='register-button']");
    
    By successMsg = By.xpath("//div[@class='result']");
    By existingEmailError = By.xpath("//div[@class='validation-summary-errors']//li");
    By passwordMismatchError = By.xpath("//span[@for='ConfirmPassword']");
    By userAccountHeader = By.xpath("//div[@class='header-links']//a[@class='account']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Actions
    public void clickRegisterLink() { driver.findElement(registerLink).click(); }
    
    public void fillRegistrationForm(String fname, String lname, String email, String pass, String confirmPass) {
        driver.findElement(genderMale).click();
        driver.findElement(firstNameInput).sendKeys(fname);
        driver.findElement(lastNameInput).sendKeys(lname);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(pass);
        driver.findElement(confirmPasswordInput).sendKeys(confirmPass);
    }

    public void clickRegisterButton() { driver.findElement(registerBtn).click(); }
    
    public String getSuccessText() { return driver.findElement(successMsg).getText(); }
    
    public String getExistingEmailError() { return driver.findElement(existingEmailError).getText(); }
    
    public String getPasswordMismatchError() { return driver.findElement(passwordMismatchError).getText(); }
    
    public String getLoggedInUser() { return driver.findElement(userAccountHeader).getText(); }
}