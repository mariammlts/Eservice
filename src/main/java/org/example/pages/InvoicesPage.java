package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InvoicesPage extends BasePage {

    public InvoicesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(text(),'ანგარიშ-ფაქტურები')]")
    private WebElement invoicesBtn;

    @FindBy(xpath = "//td[@id='ctl00_HeaderTitle']/p")
    private WebElement headerName;

    @FindBy(id = "btnNewInvoice")
    private WebElement newInvoiceBtn;

    @FindBy(id = "Inv_Buyer_Tin_inputID")
    private WebElement buyerTin;

    @FindBy(id = "Buyer_Name_inputID")
    private WebElement buyerName;

    @FindBy(id = "btnExcise")
    private WebElement searchProductBtn;

    @FindBy(id = "good_name_inputID")
    private WebElement productName;

    @FindBy(id = "good_unit")
    private WebElement goodUnit;

    @FindBy(id = "good_quantity")
    private WebElement quantity;

    @FindBy(id = "good_amount")
    private WebElement amount;

    @FindBy(id = "btnAddGood")
    private WebElement addProductBtn;

    @FindBy(id = "btnSave")
    private WebElement saveBtn;

    @FindBy(id = "btnSend")
    private WebElement sendBtn;


    public void openInvoices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(invoicesBtn));
        click(invoicesBtn);
    }

    public String getHeaderName() {
        return getText(headerName);
    }

    public void addProduct(String name, String unit, String qty, String amountVat) {
       sendKeys(productName,name);
       new Select(goodUnit).selectByValue(unit);
       sendKeys(quantity,qty);
       sendKeys(amount, amountVat);
       click(addProductBtn);
    }

    public void confirmSendInvoicePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void fillInvoiceForm(String tin, String x, String y) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        click(newInvoiceBtn);
        sendKeys(buyerTin, tin);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(buyerName, "value"));
        addProduct("ტესტ", "ცალი", "1", "2000");
        wait.until(ExpectedConditions.elementToBeClickable(sendBtn));
        click(sendBtn);
        confirmSendInvoicePopup();


    }

    public void sendInvoice() {
        click(sendBtn);
    }

    public void saveInvoice() {
        click(saveBtn);
    }

    public String getLastRecordStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement statusCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//tr[contains(@class,'rsGridDataRow')])[last()]//td[contains(@class,'rsGridFixColumn')]")
        ));
        return statusCell.getText();
    }

    public void closeAllPopups() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> closeButtons = driver.findElements(By.cssSelector(".rsPopup.popupShow .rsPopupClose"));

        for (int i = closeButtons.size() - 1; i >= 0; i--) {
            WebElement closeButton = closeButtons.get(i);
            if (closeButton.isDisplayed()) {
                wait.until(ExpectedConditions.elementToBeClickable(closeButton));
                click(closeButton);
            }
        }


    }
}



