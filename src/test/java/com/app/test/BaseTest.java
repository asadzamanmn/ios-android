package com.app.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public WebDriver driver;

    @Parameters({"appos"})
    @BeforeMethod
    public  void beforeSuite(String appos, Method methodName) throws Exception {

        try{
            startServer();
        }
        catch (Exception e) {
            System.out.println("Unable to start appium server");
            throw new Exception(e.getMessage());
        }

        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "apps");
        System.out.println("App directory : ---------' "+ appDir +" '-----------");

        if(appos.equalsIgnoreCase("android"))
        {
            File app = new File(appDir, "app-debug.apk");

            DesiredCapabilities cap= new DesiredCapabilities();
            cap.setCapability("deviceName","Android Emulator");
            cap.setCapability("platformName","Android");
            cap.setCapability("app", app.getAbsolutePath());
            cap.setCapability("name", methodName.getName());
            driver=new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        }
        else if (appos.equalsIgnoreCase("ios"))
        {
            File app = new File(appDir, "MASAuthentication.zip");

            DesiredCapabilities cap= new DesiredCapabilities();
            cap.setCapability("deviceName","iPhone Simulator");
            cap.setCapability("platformName","iOS");
            cap.setCapability("automationName","XCUITest");
            cap.setCapability("platformVersion","12.4");
            cap.setCapability("app", app.getAbsolutePath());
            cap.setCapability("name", methodName.getName());
            driver=new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),cap);

        }

    }

    @BeforeSuite
    public  void beforeSuite() throws Exception {

        try{
            startServer();
        }
        catch (Exception e) {
            System.out.println("Unable to start appium server");
            throw new Exception(e.getMessage());
        }

    }


    AppiumDriverLocalService service = null;

    public void startServer() {
        //Set Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    @AfterSuite
    public void stopServer() {
        service.stop();
    }

}
