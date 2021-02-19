package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import tests.TestBase;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Check_Links  {
    static WebDriver driver;

    public Check_Links(WebDriver driver) {
        this.driver = driver;
    }

    static TestBase Check_Links = new TestBase();

    //String urlHomePage, S
    // tring urlCheckingPage

    public static int listBrokenAll_Links(String urlHomePage_Index, String urlHomePage) {

        long startTime = System.currentTimeMillis();
        String stringURL = "";
        HttpURLConnection huc = null;
        int responseCode = 200;
        List<WebElement> arrayTag_a = null;
        int a = 0;
        int w = 0;
        int keyWriteAllLinks = 0;
        int keyStart1 = 0;
        int keyStart2 = 0;
        int keyStart3 = 0;
        int keyEmpty = 0;
        String name = "reports/ListAllLinksFromPageSTART.txt";
        String name1 = "reports/LinksEmpty.txt";
        String name2 = "reports/Links_Another_Sites.txt";
        String name4 = "Links_Response_Code_400.txt";
        String name3 = "reports/Links_Valid.txt";
        /*
         *initiation array links from first page
         *
         * */
        Set<String> arrayValidLinksFromFirstPage = new HashSet<>();
        List<String> arrayAnotherLinks = new ArrayList<>(10);
        List<String> arrayBrokenLinks = new ArrayList<>(10);
        List<String> arrayLinks = new ArrayList<>(10);


        /*
         * START
         * Start is preferable from the main page of the site.
         * Get array of links at tag "a".
         * */
        try {
            arrayTag_a = driver.findElements(By.tagName("a"));
        } catch (Exception e) {
           // Check_Links.logException("Set link for arrayTag_a ", e);
            e.printStackTrace();
        }


        /*
         * checking element href in loop
         * */
        try {
            assert arrayTag_a != null;
        } catch (Exception e) {
            e.printStackTrace();
            //Check_Links.logException("ArrayTag_a is null ", e);
        }

        for (WebElement webElementTag_a : arrayTag_a) {

            stringURL = webElementTag_a.getAttribute("href");
            //Check_Links.logInfo("Checking in loop the href element" + stringURL);
            try {
                if (keyWriteAllLinks == 0) {
                    WriterRider.writerToFilesLinks(stringURL, startTime, name, keyWriteAllLinks, urlHomePage_Index);
                } else {
                    WriterRider.writerToFilesLinks(stringURL, startTime, name, keyWriteAllLinks, stringURL);
                }
            } catch (Exception e) {
               //Check_Links.logException("not write link to  ListAllLinksFromPageSTART", e);
                e.printStackTrace();
            }
            keyWriteAllLinks++;
            //---------------------------Error Link------------------------------------------------
            if (stringURL == null || stringURL.isEmpty()) {
                if (keyEmpty == 0) {
                    WriterRider.writerToFilesLinks("Error Link", startTime, name1, keyEmpty, urlHomePage_Index);
                } else {
                    WriterRider.writerToFilesLinks("LinksEmpty " + keyEmpty, startTime, name1, keyEmpty, stringURL);
                }
                keyEmpty++;
                continue;
            }

            if (!(stringURL.startsWith(urlHomePage_Index) || stringURL.startsWith(urlHomePage))) {

                if (keyStart1 == 0) {

                    WriterRider.writerToFilesLinks(stringURL, startTime, name2, keyStart1, stringURL);
                } else {
                    WriterRider.writerToFilesLinks(stringURL, startTime, name2, keyStart1, stringURL);
                }
                keyStart1++;

                if (stringURL.contains(".xlsx")) {
                    continue;
                } else if (stringURL.contains(".ppt")) {
                    continue;
                } else if (stringURL.contains(".docx")) {
                    continue;
                } else if (stringURL.contains(".xls")) {
                    continue;
                } else if (stringURL.contains(".doc")) {
                    continue;
                } else if (stringURL.contains(".pptx")) {
                    continue;
                } else if (stringURL.contains(".odt")) {
                    continue;
                } else if (stringURL.contains(".jpg")) {
                    continue;
                } else if (stringURL.contains(".png")) {
                    continue;
                } else if (stringURL.contains(".zip")) {
                    continue;
                } else if (stringURL.contains(".rar")) {
                    continue;
                } else if (stringURL.contains(".ods")) {
                    continue;
                } else if (stringURL.contains("javascript:")) {
                    continue;
                } else if (stringURL.contains("mailto:")) {
                    continue;
                }
                continue;
            }

            try {
                huc = (HttpURLConnection) (new URL(stringURL).openConnection());
                huc.setRequestMethod("HEAD");
                huc.connect();
                responseCode = huc.getResponseCode();
                if (responseCode >= 400) {
                    arrayBrokenLinks.add(stringURL);
                    if (keyStart2 == 0) {

                        WriterRider.writerToFilesLinks(stringURL, startTime, name4, keyWriteAllLinks, stringURL);
                    } else {
                        WriterRider.writerToFilesLinks(stringURL, startTime, name4, keyWriteAllLinks, stringURL);
                    }
                    //System.out.println("keyStart2 " + keyStart2);
                    keyStart2++;

                } else {
                    try {
                        arrayValidLinksFromFirstPage.add(stringURL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int k = 0;
        for (String linkUnikum : arrayValidLinksFromFirstPage) {
            Check_Images.getListBrokenImages(urlHomePage, linkUnikum);
            if (k == 0) {
                WriterRider.writerToFilesLinks(linkUnikum, startTime, name3, k, urlHomePage_Index);
            } else {
                WriterRider.writerToFilesLinks(linkUnikum, startTime, name3, k, stringURL);
            }
            k++;
        }

        for (String validLink : arrayValidLinksFromFirstPage) {
            //System.out.println("\n loop #2 " + a++);
            driver.get(validLink);
            List<WebElement> arrayLinksFromMainPage = driver.findElements(By.tagName("a"));
            int loop = 0;
            keyStart1 = 0;
            keyStart2 = 0;
            keyStart3 = 0;
            for (WebElement arrayAllLink : arrayLinksFromMainPage) {

                startTime = System.currentTimeMillis();
                //System.out.println("цикл в цикле " + loop++);

                stringURL = arrayAllLink.getAttribute("href");
                if (stringURL == null || stringURL.isEmpty()) {
                    continue;
                }
                if (!(stringURL.startsWith(urlHomePage_Index) || stringURL.startsWith(urlHomePage))) {
                    arrayAnotherLinks.add(stringURL);
                    if (keyStart1 == 0) {

                        WriterRider.writerToFilesLinks(stringURL, startTime, name2, keyStart1, validLink);
                    } else {
                        WriterRider.writerToFilesLinks(stringURL, startTime, name2, keyStart1, stringURL);
                    }
                    //System.out.println("keyStart1 " + keyStart1);
                    keyStart1++;
                    continue;
                }
                try {
                    huc = (HttpURLConnection) (new URL(stringURL).openConnection());
                    huc.setRequestMethod("HEAD");
                    huc.connect();
                    responseCode = huc.getResponseCode();
                    if (responseCode >= 400) {

                        if (keyStart2 == 0) {

                            WriterRider.writerToFilesLinks(stringURL, startTime, name4, keyStart2, validLink);
                        } else {
                            WriterRider.writerToFilesLinks(stringURL, startTime, name4, keyStart2, stringURL);
                        }
                        keyStart2++;
                    } else {
                        if (keyStart3 == 0) {
                            WriterRider.writerToFilesLinks(stringURL, startTime, name3, keyStart3, validLink);
                        } else {
                            WriterRider.writerToFilesLinks(stringURL, startTime, name3, keyStart3, stringURL);
                        }
                        //System.out.println("keyStart3 " + keyStart3);
                        keyStart3++;
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*for (
                String l : arrayAnotherLinks)
            System.out.println(a++ + " arrayAnotherLinks   " + l);

        for (
                WebElement l : arrayTag_a)
            System.out.println(w++ + " arrayTag_a  " + l.getAttribute("href"));*/
        return arrayBrokenLinks.size();
    }
}
