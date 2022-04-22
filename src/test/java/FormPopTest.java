import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.FormPage;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FormPopTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger("FormPopTest.class");

    @Test
    public void shouldFillFormWithSuccess() throws InterruptedException {
        FormPage formPage = new FormPage(driver);

        formPage.setFirstName(System.getProperty("firstName"))
                .setLastName(System.getProperty("lastName"))
                .setEmail(System.getProperty("email"))
                .setAge(System.getProperty("age"))
                .selectRandomProfession()
                .selectRandomExperience()
                .selectMale()
                .selectContinent(System.getProperty("continent"))
                .selectSeleniumCommand(System.getProperty("command"))
                .uploadFile(System.getProperty("filepath"))
                .submitForm();

        String message = formPage.getValidationMsg();

        assertThat("Wrong message", message, equalTo(System.getProperty("message")));
    }
}
