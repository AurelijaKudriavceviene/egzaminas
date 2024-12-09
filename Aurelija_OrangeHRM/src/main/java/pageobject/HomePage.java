package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[normalize-space()='Recruitment']")
    private WebElement recruitmentBtnInput;

    @FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
    private WebElement addBtnInput;

    public void clickRecruitmentBtn() {
        waitForVisibility(recruitmentBtnInput);
        recruitmentBtnInput.click();
    }

    public void clickAddBtn() {
        waitForVisibility(addBtnInput);
        addBtnInput.click();
    }

    public boolean checkCurrentUrl(String expectedUrlFragment) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains(expectedUrlFragment));
        return driver.getCurrentUrl().contains(expectedUrlFragment);
    }
}
