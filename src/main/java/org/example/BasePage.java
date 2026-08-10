package org.example;

import org.example.Utils.ConfigReader;
import org.example.Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("wait")));

        PageFactory.initElements(driver, this);

    }

    public void waiTForElementToBeVisible(WebElement locator){
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

    public void waiTForElementToBeClickable(WebElement locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }



    public void sendKeys(WebElement locator, String text){
        waiTForElementToBeVisible(locator);
        locator.clear();
        Utils.logInfo("Locator is cleared");
        locator.sendKeys(text);
        Utils.logInfo("Send Key: " + text);
    }

    public void click(WebElement locator) {
       waiTForElementToBeClickable(locator);
        locator.click();
       Utils.logInfo("click to: " + locator);
    }


    public String getText(WebElement locator) {
        Utils.logInfo("returned Text: " + locator.getText());
        return locator.getText();


    }

    public String getCssValue(WebElement locator, String propertyName){
        return locator.getCssValue(propertyName);

    }


}
