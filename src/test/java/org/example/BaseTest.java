package org.example;

import org.example.Utils.ConfigReader;
import org.example.Utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
       // driver.get("https://eservices.rs.ge/Login.aspx");
        driver.get(ConfigReader.get("base.url"));
    }


    @AfterMethod
    public void tearDown(){
        DriverManager.quitDriver();
    }

    public String getCurrentUrl() {
        return  driver.getCurrentUrl();
    }

    public  void verifyCurrentUrl(String expectedUrl) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

    }

    public void compareText(String act, String exp){
        Assert.assertEquals(act,exp);
    }
}
