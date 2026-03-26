package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class CategoryPage {
    WebDriver driver;

    // ✅ Top menu XPaths
    By computersMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Computers')]");
    By electronicsMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Electronics')]");
    By booksMenu = By.xpath("//ul[@class='top-menu']//a[contains(text(),'Books')]");

    // ✅ Sub categories XPaths
    By desktops = By.xpath("//div[@class='sub-category-grid']//a[contains(text(),'Desktops')]");
    By cellPhones = By.xpath("//div[@class='sub-category-grid']//a[contains(text(),'Cell phones')]");

    // ✅ Page elements XPaths
    By pageTitle = By.xpath("//div[@class='page-title']/h1");
    By productItems = By.xpath("//div[@class='product-item']");
    By displayDropdown = By.xpath("//select[@id='products-pagesize']");

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickComputers() { driver.findElement(computersMenu).click(); }
    public void clickElectronics() { driver.findElement(electronicsMenu).click(); }
    public void clickBooks() { driver.findElement(booksMenu).click(); }
    public void clickCellPhones() { driver.findElement(cellPhones).click(); }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean subCategoriesVisible() {
        return driver.findElement(desktops).isDisplayed();
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    // 💡 This "String size" parameter is what was missing!
    public void selectDisplayCount(String size) {
        WebElement dropdown = driver.findElement(displayDropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(size);
    }
}