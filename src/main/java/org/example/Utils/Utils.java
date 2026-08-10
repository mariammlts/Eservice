package org.example.Utils;

public class Utils {
    public static void logInfo(String message){
        if(ExtentReportManager.getTest() != null){
            ExtentReportManager.getTest().info(message);
        }
    }

    public static void logFail(String message){
        if(ExtentReportManager.getTest() != null){
            ExtentReportManager.getTest().info(message);
        }
    }

    public static void logPass(String message){
        if(ExtentReportManager.getTest() != null){
            ExtentReportManager.getTest().info(message);
        }
    }

}
