package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddCandidatePage extends BasePage {
    public AddCandidatePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "button[type='submit']")
    private WebElement saveBtnInput;

    @FindBy(xpath = "//div[@class='oxd-input-group']//div[1]//span[1]")
    private WebElement firstNameMessageInput;

    @FindBy(xpath = "//div[@class='oxd-form-row']//div[3]//span[1]")
    private WebElement lastNameMessageInput;

    @FindBy(xpath = "//div[@class='oxd-grid-3 orangehrm-full-width-grid']//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required']")
    private WebElement emailMessageInput;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancelBtnInput;

    @FindBy(xpath = "//div[3]//div[1]//div[1]//div[1]//div[2]//input[1]")
    private WebElement emailInput;

    @FindBy(xpath = "//span[normalize-space()='Expected format: admin@example.com']")
    private WebElement invalidEmailMessageInput;

    @FindBy(css = "input[placeholder='First Name']")
    private WebElement firstNameInput;

    @FindBy(css = "input[placeholder='Middle Name']")
    private WebElement middleNameInput;

    @FindBy(css = "input[placeholder='Last Name']")
    private WebElement lastNameInput;

    @FindBy(css = ".oxd-icon.bi-caret-down-fill.oxd-select-text--arrow")
    private WebElement vacancyInput;

    @FindBy(xpath = "//div[@role='option']//span[text()='Junior Account Assistant']")
    private WebElement vacancyAllInput;

    @FindBy(xpath = "//body/div[@id='app']/div[@class='oxd-layout orangehrm-upgrade-layout']/div[@class='oxd-layout-container']/div[@class='oxd-layout-context']/div[@class='orangehrm-background-container orangehrm-save-candidate-page']/div[@class='orangehrm-card-container']/form[@class='oxd-form']/div[@class='oxd-form-row']/div[@class='oxd-grid-3 orangehrm-full-width-grid']/div[2]/div[1]/div[2]/input[1]")
    private WebElement contactNrInput;

    @FindBy(css = "input[type='file']")
    private WebElement fileUploadInput;

    @FindBy(css = "input[placeholder='Enter comma seperated words...']")
    private WebElement keywordsInput;

    @FindBy(css = "textarea[placeholder='Type here']")
    private WebElement notesInput;

    @FindBy(css = ".oxd-icon.bi-check.oxd-checkbox-input-icon")
    private WebElement consentBtnInput;



    public void clickSaveBtn() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForVisibility(saveBtnInput);
        saveBtnInput.click();
    }

    public String checkFirstMessage() {
        return firstNameMessageInput.getText();
    }

    public String checkLastnameMessage() {
        return lastNameMessageInput.getText();
    }

    public String checkEmailMessage() {
        return emailMessageInput.getText();
    }

    public void clickCancelBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        waitForVisibility(cancelBtnInput);
        cancelBtnInput.click();
    }

    public void setInvalidEmail(String invalidEmail) {
        waitForVisibility(emailInput);
        emailInput.sendKeys(invalidEmail);
        saveBtnInput.click();
    }

    public String chechInvalidEmailMessage() {
        return invalidEmailMessageInput.getText();
    }

    public void fillAllFields(String firstname, String middlename, String lastname, String email, String contactnr, String keywords, String notes) {
        waitForVisibility(firstNameInput);
        firstNameInput.sendKeys(firstname);
        middleNameInput.sendKeys(middlename);
        lastNameInput.sendKeys(lastname);
        waitForVisibility(vacancyInput);
        vacancyInput.click();
        waitForVisibility(vacancyAllInput);
        vacancyAllInput.click();
        emailInput.sendKeys(email);
        contactNrInput.sendKeys(contactnr);


        fileUploadInput.sendKeys("/Users/aurelijakudriavceviene/Desktop/Java/Aurelija_OrangeHRM/src/test/resources/sample.pdf");
        keywordsInput.sendKeys(keywords);
        notesInput.sendKeys(notes);
        consentBtnInput.click();
        saveBtnInput.click();

    }

    public void fillStaticFields(String firstname, String lastname, String email) {
        waitForVisibility(firstNameInput);
        firstNameInput.sendKeys(firstname);
        lastNameInput.sendKeys(lastname);
        emailInput.sendKeys(email);
        consentBtnInput.click();
        saveBtnInput.click();
    }
}
