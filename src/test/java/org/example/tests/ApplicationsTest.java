package org.example.tests;


import org.example.BaseTest;
import org.example.Utils.ConfigReader;
import org.example.pages.ApplicationsPage;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;


public class ApplicationsTest extends BaseTest {

    //login
    //openApplication
    //send
    //check id
    //check status

    @Test
    public void testSendApplication()  {
        //login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("user.name"),ConfigReader.get("user.password"));

        ApplicationsPage applicationsPage = new ApplicationsPage(driver);

        applicationsPage.openApplications();
        compareText(applicationsPage.getHeaderName(), "განცხადებები");
        applicationsPage.submitApplicationAndGetId(ConfigReader.get("from.month"),ConfigReader.get("from.year"),
                ConfigReader.get("to.month"), ConfigReader.get("to.year"));

        compareText(applicationsPage.getSuccessText(), "თქვენი განცხადება წარმატებით დარეგისტრირდა ჩვენს მონაცემთა ბაზაში");
        String applicationId = applicationsPage.applicationIdNumber();
        System.out.println("განცხადების ID ნომერი არის:" + applicationsPage.applicationIdNumber());
        applicationsPage.goBackToApplicationsList();
        compareText(applicationsPage.getApplicationStatusById(applicationId), "განიხილება");

        String currentStatus = applicationsPage.getApplicationStatusById(applicationId);
        String newStatus = applicationsPage.waitForApplicationStatusChange(applicationId,currentStatus);
        System.out.println("ახალი სტატუსია: " + newStatus);
    }






}
