package com.app.test.ios;

import com.app.test.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class IosSmokeTest extends BaseTest
{

    @Test
    public void logIn() throws InterruptedException {
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        driver.switchTo().alert().accept();
        driver.findElement(By.name("Explicit Login")).click();
        driver.findElement(By.xpath("//XCUIElementTypeAlert[@name=\"Explicit User Login\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther")).sendKeys("anayet@gmail.com");
        driver.findElement(By.xpath("//XCUIElementTypeAlert[@name=\"Explicit User Login\"]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther")).sendKeys("anayet1231");
        driver.findElement(By.name("OK")).click();

    }
}
