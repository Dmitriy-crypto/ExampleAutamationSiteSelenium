package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class HelperBase {
    static WebDriver driver;
    public HelperBase(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForElementAndClick(By locator, int time) {
        new WebDriverWait(driver, time)
                .until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public static WebElement waitToBeClickable(WebDriver driver, By selector, int waitInterval) {
        WebElement element = null;
        try {
            element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public static WebElement waitForElementPresence(WebDriver driver, By selector, int waitInterval) {
        WebElement element = null;
        try {
            element = (new WebDriverWait(driver, waitInterval)).until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }


    public static boolean isPresent(WebDriver driver, By selector) {

        try {
            driver.findElement(selector);
        } catch (NoSuchElementException e) {

            return false;
        }
        return true;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
}
