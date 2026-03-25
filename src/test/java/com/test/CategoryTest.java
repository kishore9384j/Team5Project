package com.test;

import com.pages.CategoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class CategoryTest {

    WebDriver driver;
    CategoryPage category;

    @BeforeClass
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demowebshop.tricentis.com/");
        Thread.sleep(2000);

        category = new CategoryPage(driver);
    }

    // 🔥 Scenario 1
    @Test
    public void navigateMainCategory() throws InterruptedException {

        category.clickComputers();
        Thread.sleep(2000);

        String title = category.getPageTitle();

        System.out.println("Page Title: " + title);

        Assert.assertEquals(title, "Computers");
        Assert.assertTrue(category.subCategoriesVisible());
    }

    // 🔥 Scenario 2
    @Test
    public void filterSubCategory() throws InterruptedException {

        category.clickElectronics();
        Thread.sleep(2000);

        category.clickCellPhones();
        Thread.sleep(2000);

        String title = category.getPageTitle();

        System.out.println("Page Title: " + title);

        Assert.assertTrue(title.contains("Cell phones"));
        Assert.assertTrue(category.getProductCount() > 0);
    }

    // 🔥 Scenario 3
    @Test
    public void changeDisplayCount() throws InterruptedException {

        category.clickBooks();
        Thread.sleep(2000);

        category.selectDisplayCount();
        Thread.sleep(3000);

        int count = category.getProductCount();

        System.out.println("Products shown: " + count);

        Assert.assertTrue(count <= 4);
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}