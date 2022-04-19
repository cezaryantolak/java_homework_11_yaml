package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormPage {
    public FormPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputFirstName3")
    private WebElement firstName;

    @FindBy(id = "inputLastName3")
    private WebElement lastName;

    @FindBy(id = "inputEmail3")
    private WebElement email;

    @FindBy(id = "inputAge3")
    private WebElement age;

    @FindBy(id = "gridRadiosMale")
    private WebElement male;

    @FindBy(name = "gridCheckboxProfession")
    private List<WebElement> professions;

    @FindBy(name = "gridRadiosExperience")
    private List<WebElement> expreriences;

    @FindBy(id = "selectContinents")
    private WebElement continents;

    @FindBy(id = "selectSeleniumCommands")
    private WebElement seleniumCommands;

    @FindBy(id = "validator-message")
    private WebElement validationLabel;

    @FindBy(id = "chooseFile")
    private WebElement fileInput;

    @FindBy(css = ".btn-primary")
    private WebElement submitFormBtn;

    public FormPage uploadFile(String path) {
        File file = new File(path);
        fileInput.sendKeys(file.getAbsolutePath());
        return this;
    }

    public String getValidationMsg() {
        return validationLabel.getText();
    }

    public FormPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public FormPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public FormPage setAge(String age) {
        this.age.sendKeys(age);
        return this;
    }

    public FormPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public FormPage selectRandomProfession() {
        getRandomElement(professions).click();
        return this;
    }

    public FormPage selectRandomExperience() {
        getRandomElement(expreriences).click();
        return this;
    }

    public FormPage selectMale() {
        male.click();
        return this;
    }

    public FormPage selectContinent(String continent) {
        new Select(continents).selectByValue(continent);
        return this;
    }

    public FormPage selectSeleniumCommand(String seleniumCommand) {
        new Select(seleniumCommands).selectByValue(seleniumCommand);
        return this;
    }

    public FormPage submitForm() {
        submitFormBtn.click();
        return this;
    }

    private WebElement getRandomElement(List<WebElement> elements) {
        Random rnd = new Random();
        int randomIndexFromList = rnd.nextInt(elements.size());
        return elements.get(randomIndexFromList);
    }
}
