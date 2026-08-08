package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    //ელემენტს ავღწერთ თავშივე, ნებისმიერ მეთოდში გამოვიძახებთ მარტივად



    //findby არის ანოტაცია - ეს არ იმუშავებს თუ დინამიური ცვლადია
    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(id = "ctl00_leftHeaderName")
    private WebElement userProfileName;

    @FindBy(xpath = "//div[contains(text(),'გთხოვთ შეამოწმოთ მომხმარებელი და პაროლი.')]")
    private WebElement wrongPasswordPopup;

    @FindBy(xpath = "//div[contains(text(),'გთხოვთ შეავსოთ პაროლის ველი!')]")
    private WebElement emptyPasswordPopup;







    public LoginPage(WebDriver driver) {
        super(driver);

        //ამის საშუალებით როდესაც გამოიძახებ ელემენტს მაშინ მოვძებნი
        PageFactory.initElements(driver, this);
    }


    public void login(String username, String password) {
        sendKeys(usernameField,username);
        sendKeys(passwordField,password);
        click(loginButton);
    }

    public  String getLoginButtonTextColor() {
        return getCssValue(loginButton, "background-color");
    }

    public String getUserProfileName() {
        return getText(userProfileName);
    }

    public String getWrongPasswordMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(wrongPasswordPopup));
        return getText(wrongPasswordPopup);
    }

    public String getEmptyPasswordMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(emptyPasswordPopup));
        return getText(emptyPasswordPopup);
    }
}
