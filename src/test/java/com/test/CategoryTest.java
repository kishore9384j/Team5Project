package com.test;

import com.pages.CategoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class CategoryTest {
    WebDriver driver;
    CategoryPage category;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        // 🚀 Intelligent Dynamic Wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://demowebshop.tricentis.com/");
        category = new CategoryPage(driver);
    }

    @Test(priority = 1)
    public void navigateMainCategory() {
        category.clickComputers();
        wait.until(ExpectedConditions.urlContains("computers"));
        Assert.assertEquals(category.getPageTitle(), "Computers");
    }

    @Test(priority = 2)
    public void filterSubCategory() {
        category.clickElectronics();
        category.clickCellPhones();
        wait.until(ExpectedConditions.titleContains("Cell phones"));
        Assert.assertTrue(category.getPageTitle().contains("Cell phones"));
    }

    @Test(priority = 3)
    public void changeDisplayCount() {
        category.clickBooks();
        // 🚀 This line should now be error-free!
        category.selectDisplayCount("4"); 
        
        wait.until(ExpectedConditions.urlContains("pagesize=4"));
        Assert.assertTrue(category.getProductCount() <= 4);
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}