package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//input[@placeholder='Username']")
    private WebElement usernameInput;

    @FindBy(css = "input[placeholder='Password']")
    private WebElement passwordInput;

    @FindBy(css = "button[type='submit']")
    private WebElement loginBtnInput;


    public void fillLoginForm(String username, String password) {
        waitForVisibility(usernameInput);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtnInput.click();
    }



}
