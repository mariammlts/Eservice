package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ApplicationsPage extends BasePage {

    public ApplicationsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='mainDiv'][.//div[contains(@class, 'divModuleName') and normalize-space(.)='განცხადებები']]")
    private WebElement applicationsBtn;

    @FindBy(xpath = "//td[@id='ctl00_HeaderTitle']/p")
    private WebElement headerName;

    @FindBy(id = "toolAddSr")
    private WebElement addNewApplication;

    @FindBy(xpath = "//div[contains(@class, 'resultService') and @title='დღგ-ის ბრუნვის შესახებ ინფორმაცია (ავტო+)']")
    private WebElement applicationId700;

    // id 700-ის "დან" ველი (ATTRIBUTE1)
    @FindBy(xpath = "//div[@id='ATTRIBUTE1']//div[@class='rsInputBox']/input")
    private WebElement periodFromInput;

    @FindBy(xpath = "//div[@id='ATTRIBUTE1']//select[contains(@class,'rsDateMonth')]")
    private WebElement periodFromMonth;

    @FindBy(xpath = "//div[@id='ATTRIBUTE1']//select[contains(@class,'rsDateYear')]")
    private WebElement periodFromYear;

    @FindBy(xpath = "//div[@id='ATTRIBUTE1']//div[contains(@class,'rsDateRefresh')]")
    private WebElement periodFromRefresh;

    // id 700-ის "მდე" ველი (ATTRIBUTE2 )
    @FindBy(xpath = "//div[@id='ATTRIBUTE2']//div[@class='rsInputBox']/input")
    private WebElement periodToInput;

    @FindBy(xpath = "//div[@id='ATTRIBUTE2']//select[contains(@class,'rsDateMonth')]")
    private WebElement periodToMonth;

    @FindBy(xpath = "//div[@id='ATTRIBUTE2']//select[contains(@class,'rsDateYear')]")
    private WebElement periodToYear;

    @FindBy(xpath = "//div[@id='ATTRIBUTE2']//div[contains(@class,'rsDateRefresh')]")
    private WebElement periodToRefresh;

    @FindBy(id = "btnSend")
    private WebElement sendBtn;

    @FindBy(xpath = "//div[@id='divResult']//div[@class='title']/h3/l")
    private WebElement successText;

    @FindBy(id = "spnRequestNumber")
    private WebElement idNumber;

    @FindBy(xpath = "//i[@class='material-icons' and contains(@onclick,'ServiceRequests.aspx')]")
    private WebElement backButton;


    public void openApplications() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(applicationsBtn));
        click(applicationsBtn);
    }

    public String getHeaderName() {
        return getText(headerName);
    }


    public void clickNewApplication() {
        click(addNewApplication);
    }


    public void selectApplicationId700() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(applicationId700));
        click(applicationId700);
    }


    public void fillApplicationPeriod(String fromMonth, String fromYear, String toMonth, String toYear) {
        // საანგარიშო პერიოდი (დან)
        click(periodFromInput);
        new Select(periodFromMonth).selectByValue(fromMonth);
        new Select(periodFromYear).selectByValue(fromYear);
        click(periodFromRefresh);

        // საანგარიშო პერიოდი (მდე)
        click(periodToInput);
        new Select(periodToMonth).selectByValue(toMonth);
        new Select(periodToYear).selectByValue(toYear);
        click(periodToRefresh);
    }

    public void sendApplication() {
        click(sendBtn);
    }

    public String getSuccessText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(successText));
        return getText(successText);
    }

    public String applicationIdNumber() {
        return getText(idNumber);
    }


    public void submitApplicationAndGetId(String fromMonth, String fromYear, String toMonth, String toYear) {
        clickNewApplication();
        selectApplicationId700();
        fillApplicationPeriod(fromMonth, fromYear, toMonth, toYear);
        sendApplication();
        //applicationIdNumber();
    }


    public void goBackToApplicationsList() {
        driver.navigate().back();
    }

    public String getApplicationStatusById(String applicationId) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[contains(@class,'rsGridDataRow') and .//td[contains(@title,'ID " + applicationId + "')]]")));

        WebElement statusCell = row.findElement(By.xpath("./td[2]"));
        return statusCell.getText();
    }

    public String waitForApplicationStatusChange(String applicationId, String oldStatus) {
        for (int i = 0; i < 60; i++) {
            driver.navigate().refresh();

            String currentStatus = getApplicationStatusById(applicationId);

            if (!currentStatus.equals(oldStatus)) {
                return currentStatus;
            }
        }

        return oldStatus;
    }

}

