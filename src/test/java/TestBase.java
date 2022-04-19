import configuration.AppProperties;
import configuration.browser.BrowserHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBase {
    protected static WebDriver driver;
    private static Logger log = LoggerFactory.getLogger("BaseTest.class");
    protected static BrowserHandler browserHandler;
    private static AppProperties appProperties;

    @BeforeAll
    static void beforeAll() {
        appProperties = AppProperties.getInstance();
        browserHandler = new BrowserHandler();
        driver = browserHandler.getDriver();
        log.info("<<<<<Driver initialized>>>");
        driver.get(System.getProperty("appUrl"));
    }

    @AfterAll
    static void quit() {
        driver.quit();
        log.debug("<<<<<<driver closed properly>>>>>");
    }
}
