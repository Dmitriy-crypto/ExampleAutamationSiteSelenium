package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import util.Check_Links;


public class LinkBrokenTest extends TestBase {
    //WebDriver driver;


    //public static String urlHomePageOfImages = "http://automationpractice.com";
    //public final String HOME_PAGE = "http://automationpractice.com/index.php";
    //public final String HOME_PAGE_IMAGES = "http://automationpractice.com";
    // public String urlCheckingPage = "http://automationpractice.com/index.php?id_category=3&controller=category";

   /* @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        try {

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    @Test(enabled = true)
    public void getListBrokenAllLinks() throws Exception {

        /*Assert.assertEquals(utils.Utils.CheckLinksAndLinksImages.listBrokenLinks(driver, HOME_PAGE,
                urlCheckingPage), 0);*/
        Assert.assertEquals(Check_Links.listBrokenAll_Links( app.HOME_PAGE, app.HOME_PAGE_IMAGES), 0);


    }

   /* @Test(enabled = false)
    public void getListBrokenLinks() {

        Assert.assertEquals(CheckLinksAndLinksImages.listBrokenLinks(driver, app.HOME_PAGE, app.HOME_PAGE_IMAGES), 0);
        arrayImagesNoPresent!=0
    }

    @Test(enabled = false)
    public void getListBrokenImages() throws IOException {

        Assert.assertEquals(CheckLinksAndLinksImages.listBrokenImages(driver, app.HOME_PAGE_IMAGES, app.HOME_PAGE_IMAGES), 0);
    }

*/

}

