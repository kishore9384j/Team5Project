package com.test;

import com.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class RegisterTest {
    WebDriver driver;
    RegisterPage reg;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        reg = new RegisterPage(driver);
    }

    // 🔥 This runs BEFORE EVERY @Test to ensure the Register link is visible
    @BeforeMethod
    public void resetState() {
        driver.get("https://demowebshop.tricentis.com/");
        
        // If the user is logged in from a previous test, log them out
        try {
            if (driver.findElements(org.openqa.selenium.By.linkText("Log out")).size() > 0) {
                driver.findElement(org.openqa.selenium.By.linkText("Log out")).click();
            }
        } catch (Exception e) {
            // No logout needed
        }
    }

    @Test(priority = 1)
    public void testSuccessfulRegistration() {
        reg.clickRegisterLink();
        String randomEmail = "testuser" + System.currentTimeMillis() + "@gmail.com";
        reg.fillRegistrationForm("Team", "Five", randomEmail, "Pass123!", "Pass123!");
        reg.clickRegisterButton();
        Assert.assertEquals(reg.getSuccessText(), "Your registration completed");
    }

    @Test(priority = 2)
    public void testExistingEmailRegistration() {
        reg.clickRegisterLink();
        reg.fillRegistrationForm("Team", "Five", "tester123@gmail.com", "Pass123!", "Pass123!");
        reg.clickRegisterButton();
        Assert.assertEquals(reg.getExistingEmailError(), "The specified email already exists");
    }

    @Test(priority = 3)
    public void testPasswordMismatch() {
        reg.clickRegisterLink();
        reg.fillRegistrationForm("Team", "Five", "mismatch@test.com", "Pass123!", "WrongPass1");
        reg.clickRegisterButton();
        Assert.assertEquals(reg.getPasswordMismatchError(), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}