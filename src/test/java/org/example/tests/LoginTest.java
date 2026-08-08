package org.example.tests;

import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    @Parameters({"username","password"})
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_leftHeaderName")));

        verifyCurrentUrl("https://eservices.rs.ge/MainPage.aspx");
        compareText(loginPage.getUserProfileName(),"სატესტო კოდი1");
    }

    @Test
    public void testLoginButtonTextColor() {
        LoginPage loginPage = new LoginPage(driver);
        //dagvibrundeba rgb formatiT
        String actualColor = loginPage.getLoginButtonTextColor();
        String expectedColor = "rgba(60, 118, 204, 1)";

        compareText(actualColor,expectedColor);
    }

    @Test
    public void testIncorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tbilisi", "123");
        String actualMessage = loginPage.getWrongPasswordMessage();
        compareText(actualMessage, "გთხოვთ შეამოწმოთ მომხმარებელი და პაროლი.");
        System.out.println(actualMessage);
    }

    @Test
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("tbilisi", "");
        String actualMessage = loginPage.getEmptyPasswordMessage();
        compareText(actualMessage, "გთხოვთ შეავსოთ პაროლის ველი!");
        System.out.println(actualMessage);
    }

}
