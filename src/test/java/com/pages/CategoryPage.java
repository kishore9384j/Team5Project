package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage {

    WebDriver driver;

    // ✅ Top menu
    By computersMenu = By.linkText("Computers");
    By electronicsMenu = By.linkText("Electronics");
    By booksMenu = By.linkText("Books");

    // ✅ Sub categories
    By desktops = By.linkText("Desktops");
    By notebooks = By.linkText("Notebooks");
    By accessories = By.linkText("Accessories");
    By cellPhones = By.linkText("Cell phones");

    // ✅ Page title
    By pageTitle = By.xpath("//div[@class='page-title']/h1");

    // ✅ Product items
    By productItems = By.cssSelector(".product-item");

    // ✅ Dropdown
    By displayDropdown = By.id("products-pagesize");

    // ✅ Constructor
    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Actions

    public void clickComputers() {
        driver.findElement(computersMenu).click();
    }

    public void clickElectronics() {
        driver.findElement(electronicsMenu).click();
    }

    public void clickBooks() {
        driver.findElement(booksMenu).click();
    }

    public void clickCellPhones() {
        driver.findElement(cellPhones).click();
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean subCategoriesVisible() {
        return driver.findElement(desktops).isDisplayed()
                && driver.findElement(notebooks).isDisplayed()
                && driver.findElement(accessories).isDisplayed();
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public void selectDisplayCount() {
        driver.findElement(displayDropdown).sendKeys("4");
    }
}