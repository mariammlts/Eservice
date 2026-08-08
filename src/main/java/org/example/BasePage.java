package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
   // protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);

    }



    public void sendKeys(WebElement locator, String text){
        locator.clear();
        locator.sendKeys(text);
    }

    public void click(WebElement locator) {
       locator.click();
    }


    public String getText(WebElement locator) {
        return locator.getText();

    }

    public String getCssValue(WebElement locator, String propertyName){
        return locator.getCssValue(propertyName);

    }


}
