import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobject.AddCandidatePage;
import pageobject.CandidatePage;
import pageobject.HomePage;
import pageobject.LoginPage;
import utils.GeneratorUtil;

import java.util.List;

public class ValidationOfRequiredFieldsTest extends BaseTest {

    @Test
    void validationOfRequiredFields() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AddCandidatePage addCandidatePage = new AddCandidatePage(driver);

        String username = "Admin";
        String password = "admin123";

        loginPage.fillLoginForm(username, password);
        homePage.clickRecruitmentBtn();
        homePage.clickAddBtn();
        addCandidatePage.clickSaveBtn();
        Assertions.assertEquals(addCandidatePage.checkFirstMessage(),
                "Required");
        Assertions.assertEquals(addCandidatePage.checkLastnameMessage(),
                "Required");
        Assertions.assertEquals(addCandidatePage.checkEmailMessage(),
                "Required");
    }

    @Test
    void CancellationOfForm() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AddCandidatePage addCandidatePage = new AddCandidatePage(driver);

        String username = "Admin";
        String password = "admin123";
        String expectedUrlFragment = "viewCandidates";

        loginPage.fillLoginForm(username, password);
        homePage.clickRecruitmentBtn();
        homePage.clickAddBtn();
        addCandidatePage.clickCancelBtn();

        Assertions.assertTrue(homePage.checkCurrentUrl(expectedUrlFragment),
                    "URL does not indicate View Candidates");
    }

    @Test
    void EmailFormatValidation() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AddCandidatePage addCandidatePage = new AddCandidatePage(driver);

        String username = "Admin";
        String password = "admin123";
        String invalidemail = "brokolisgmail.com";

        loginPage.fillLoginForm(username, password);
        homePage.clickRecruitmentBtn();
        homePage.clickAddBtn();
        addCandidatePage.setInvalidEmail(invalidemail);
        Assertions.assertEquals(addCandidatePage.chechInvalidEmailMessage(),
                "Expected format: admin@example.com");
    }

    @Test
    void SavingTheCandidateInformation() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AddCandidatePage addCandidatePage = new AddCandidatePage(driver);
        CandidatePage candidatePage = new CandidatePage(driver);

        String username = "Admin";
        String password = "admin123";
        String firstname = GeneratorUtil.getFirstName();
        String middlename = GeneratorUtil.getMiddleName();
        String lastname = GeneratorUtil.getLastName();
        String email = GeneratorUtil.getEmail();
        String contactnr = GeneratorUtil.getContactNr();
        String keywords = GeneratorUtil.getKeywords();
        String notes = GeneratorUtil.getNotes();

        loginPage.fillLoginForm(username, password);
        homePage.clickRecruitmentBtn();
        homePage.clickAddBtn();
        addCandidatePage.fillAllFields(firstname, middlename, lastname, email, contactnr, keywords, notes);

        List<String> expectedHeaders = List.of("Performed Date", "Description", "Actions");
        List<WebElement> headerCells = candidatePage.getTableHeaders();

        // Check if the number of actual headers matches the expected number
        Assertions.assertEquals(expectedHeaders.size(), headerCells.size(), "Number of headers does not match the expected count.");

        // Verify the text of each header
        for (int i = 0; i < headerCells.toArray().length; i++) {
            String actualHeader = headerCells.get(i).getText();
            Assertions.assertEquals(expectedHeaders.toArray()[i], actualHeader, "Header at index " + i + " does not match the expected value.");
        }
    }

    @Test
    void EditingSavedCandidateInformation() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        AddCandidatePage addCandidatePage = new AddCandidatePage(driver);
        CandidatePage candidatePage = new CandidatePage(driver);

        String username = "Admin";
        String password = "admin123";
        String firstname = "Burokelis";
        String lastname = "Darzove";
        String email = "burokelis@gmail.com";
        String editedfirstname = "Morka";

        loginPage.fillLoginForm(username, password);
        homePage.clickRecruitmentBtn();
        homePage.clickAddBtn();
        addCandidatePage.fillStaticFields(firstname, lastname, email);

        Assertions.assertFalse(candidatePage.checkThatISClickable(), "Element is clickable but should not be.");

        candidatePage.clickSwitchBtn();

        Assertions.assertTrue(candidatePage.checkThatISClickable(), "Element is not editable.");

        candidatePage.setEditFirstName(editedfirstname);

        candidatePage.clickSaveBtn();

        Assertions.assertEquals(candidatePage.checkIsFirstNameEdited(), editedfirstname);
    }
}
