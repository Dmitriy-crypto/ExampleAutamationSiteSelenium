package util;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Check_Images  {
    static WebDriver driver;

    public Check_Images(WebDriver driver) {
        this.driver = driver;
    }

    public static int getListBrokenImages(String urlHomePage, String urlCheckingPage) {
        long startTime = System.currentTimeMillis();
        String url = "";
        HttpURLConnection huc = null;
        int responseCode = 200;
        driver.get(urlCheckingPage);
        List<WebElement> arrayLinksImg = driver.findElements(By.tagName("img"));

        List<String> arrayBrokenImg = new ArrayList<>(10);
        List<String> arrayLinksForCheckImages = new ArrayList<>(10);
        List<String> arrayAnotherImg = new ArrayList<>(10);//!!!!!!!!!!!!!!!!!

        Iterator<WebElement> iterator = arrayLinksImg.iterator();
        int countEmptyImg = 1;
        int countBroken = 0;//!!!!!!!!!!!!!
        while (iterator.hasNext()) {
            url = iterator.next().getAttribute("src");
            if (url == null || url.isEmpty()) {
                countEmptyImg++;
                continue;
            }
            if (!url.startsWith(urlHomePage)) {
                arrayAnotherImg.add(url);
                continue;
            }
            try {
                huc = (HttpURLConnection) (new URL(url).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                responseCode = huc.getResponseCode();
                if (responseCode >= 400) {
                    arrayBrokenImg.add(url);
                } else {
                    arrayLinksForCheckImages.add(url);

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Check_Images.checkingImagesIsPresent(arrayLinksForCheckImages);
               return arrayBrokenImg.size();
    }

    /*

     * */
    public static void checkingImagesIsPresent(List<String> arrayLinksForCheckImages) {
        int i = 0;
        List<String> arrayImagesNoPresent = new ArrayList<>(10);
        for (String l : arrayLinksForCheckImages) {
            driver.get(l);

            String width = null;
            try {
                width = (driver.findElement(By.xpath("//img[not(@style='hidden')]")).getCssValue("width"));
                width = StringUtils.chop(width);
                width = StringUtils.chop(width);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String height = null;
            try {
                height = driver.findElement(By.xpath("//img[not(@style='hidden')]")).getCssValue("height");
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                height = StringUtils.substring(height, 0, height.length() - 2);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int heightInt, widthInt;

            try {
                heightInt = Integer.parseInt(height);
                widthInt = Integer.parseInt(width);

            } catch (NumberFormatException e) {
                heightInt = 0;
                widthInt = 0;
            }
            if (!(heightInt > 0 & widthInt > 0)) {
                arrayImagesNoPresent.add(l);
            }
        }
    }
}
