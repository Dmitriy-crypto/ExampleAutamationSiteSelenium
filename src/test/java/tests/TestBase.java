package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {

    protected static pages.ApplicationManager app = new pages.ApplicationManager();

    @BeforeSuite
    public void setUp() {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    //---------------------Logger--------------------------------------------
    Logger log = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void startLogger(Method m) {
        log.info("Start test " + m.getName());
    }

    @AfterMethod
    public void stopLogger(Method method) {
        log.info("Stop " + method);
    }

    public void logException(String s, Exception e) {
        log.warn("Exception - " + s + " " + e);
    }

    public void logInfo(String s) {
        log.info(s);
    }

    public void logError(String s) {
        log.info(s);
    }
}
