package com.test;

import com.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class CartTest {
    WebDriver driver;
    CartPage cart;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.get("https://demowebshop.tricentis.com/");
        cart = new CartPage(driver);
    }

    @Test(priority = 1)
    
    public void testAddToCart() {
        // 🚀 Step 1: Go to Books category first
        cart.navigateToBooks(); 
        
        // 🚀 Step 2: Now select the product
        cart.selectProduct();
        
        cart.enterQuantity("2");
        cart.clickAddToCart();
        
        // Wait for green notification bar
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='content']")));
        
        Assert.assertTrue(cart.getSuccessMessage().contains("The product has been added"));
        Assert.assertEquals(cart.getCartQtyText(), "(2)");
    }

    @Test(priority = 2, dependsOnMethods = "testAddToCart")
    public void testUpdateQuantity() {
        cart.goToCartPage();
        String oldPrice = cart.getSubTotal();
        
        cart.updateQuantityInCart("5");
        
        String newPrice = cart.getSubTotal();
        System.out.println("Updated Price: " + newPrice);
        
        Assert.assertNotEquals(oldPrice, newPrice, "Price did not update after quantity change!");
    }

    @Test(priority = 3, dependsOnMethods = "testUpdateQuantity")
    public void testRemoveProduct() {
        cart.removeItem();
        
        String msg = cart.getEmptyMessage();
        Assert.assertTrue(msg.contains("Your Shopping Cart is empty!"), "Cart was not empty after removal!");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) driver.quit();
    }
}