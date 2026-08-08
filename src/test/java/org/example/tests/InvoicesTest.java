package org.example.tests;

import org.example.BaseTest;
import org.example.Utils.ConfigReader;
import org.example.pages.InvoicesPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class InvoicesTest extends BaseTest {

    @Test
    public void TestSendInvoice() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("user.name"),ConfigReader.get("user.password"));

        InvoicesPage invoicesPage = new InvoicesPage(driver);

        invoicesPage.openInvoices();
        compareText(invoicesPage.getHeaderName(),"ანგარიშ-ფაქტურები");
        invoicesPage.closeAllPopups();
        invoicesPage.fillInvoiceForm("12345678910", "1", "2000");
        invoicesPage.sendInvoice();

        Thread.sleep(3000);
        System.out.println("ფაქტურის სტატუსია: " + invoicesPage.getLastRecordStatus());

    }
}
