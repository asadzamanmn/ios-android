package com.app.test.android;

import com.app.test.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AndroidSmokeTest extends BaseTest
{

    @Test
    public void logInAndroid() throws InterruptedException {
        driver.findElement(By.id("username")).sendKeys("anayet@gmail.com");
        driver.findElement(By.id("password")).sendKeys("anayet123");
        driver.findElement(By.id("login")). click();
    }
}
