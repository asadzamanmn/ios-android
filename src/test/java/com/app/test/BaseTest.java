package com.app.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public WebDriver driver;

    @Parameters({"os"})
    @BeforeSuite
    public  void beforeSuite(String os) throws MalformedURLException
    {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        System.out.println("App directory : ---------' "+ appDir +" '-----------");

        if(os.equalsIgnoreCase("android"))
        {
            File app = new File(appDir, "app-debug.apk");

            DesiredCapabilities cap= new DesiredCapabilities();
            cap.setCapability("deviceName","Android Emulator");
            cap.setCapability("platformName","Android");
            cap.setCapability("app", app.getAbsolutePath());
            driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        }
        else if (os.equalsIgnoreCase("ios"))
        {
            File app = new File(appDir, "MASAuthentication.zip");

            DesiredCapabilities cap= new DesiredCapabilities();
            cap.setCapability("deviceName","iPhone Simulator");
            cap.setCapability("platformName","iOS");
            cap.setCapability("automationName","XCUITest");
            cap.setCapability("platformVersion","12.4");
            cap.setCapability("app", app.getAbsolutePath());
            driver=new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        }

    }

}
