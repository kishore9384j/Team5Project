package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;

    // ✅ Locators using XPath
    By productLink = By.xpath("//div[@class='product-item']//a[contains(text(),'Computing and Internet')]");
    By booksMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Books')]");
    By qtyInput = By.xpath("//input[@class='qty-input']");
    By addToCartBtn = By.xpath("//input[@value='Add to cart']");
    By successBar = By.xpath("//p[@class='content']");
    By cartQtyLabel = By.xpath("//span[@class='cart-qty']");
    
    // ✅ Cart Page Locators
    By cartTopLink = By.xpath("//span[text()='Shopping cart']");
    By cartGridQty = By.xpath("//input[@class='qty-input']"); // In the table
    By updateCartBtn = By.xpath("//input[@name='updatecart']");
    By subTotal = By.xpath("//span[@class='product-subtotal']");
    By removeCheckbox = By.xpath("//input[@name='removefromcart']");
    By emptyCartMsg = By.xpath("//div[@class='order-summary-content']");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Actions
    public void selectProduct() { driver.findElement(productLink).click(); }
    
    public void enterQuantity(String qty) {
        WebElement element = driver.findElement(qtyInput);
        element.clear();
        element.sendKeys(qty);
    }

    public void clickAddToCart() { driver.findElement(addToCartBtn).click(); }
 // Add this action method
    public void navigateToBooks() {
        driver.findElement(booksMenu).click();
    }
    
    public String getSuccessMessage() { return driver.findElement(successBar).getText(); }
    
    public String getCartQtyText() { return driver.findElement(cartQtyLabel).getText(); }

    public void goToCartPage() { driver.findElement(cartTopLink).click(); }

    public void updateQuantityInCart(String newQty) {
        WebElement element = driver.findElement(cartGridQty);
        element.clear();
        element.sendKeys(newQty);
        driver.findElement(updateCartBtn).click();
    }

    public String getSubTotal() { return driver.findElement(subTotal).getText(); }

    public void removeItem() {
        driver.findElement(removeCheckbox).click();
        driver.findElement(updateCartBtn).click();
    }

    public String getEmptyMessage() { return driver.findElement(emptyCartMsg).getText(); }
}