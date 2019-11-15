package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {
    protected WebDriver driver;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        try {
            driver.findElement(locator).click();
        } catch (StaleElementReferenceException e){
            driver.findElement(locator).click();
        }

    }

    protected void type(By locator, String text) {
        click(locator);
        if (text!=null){
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)){
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected boolean isElementPresent(By locator){
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }
}
