package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public final String HOME_PAGE = "https://impulse-design.com.ua";
    public final String HOME_PAGE_IMAGES = "https://impulse-design.com.ua";
//tyhrtjhrt
    WebDriver driver;
    pages.HelperBase helperBase;
    util.Check_Images check_Images;
    util.Check_Links check_Links;


    public void init() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        check_Images = new util.Check_Images(driver);
        check_Links = new util.Check_Links(driver);
        helperBase = new pages.HelperBase(driver);
        openSite(HOME_PAGE);

    }

    public void login(String email, String password) {

    }

    public void type(By locator, String text) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    private void openSite(String url) {
        driver.get(url);
    }


    public void stop() {
        driver.quit();
    }
}
