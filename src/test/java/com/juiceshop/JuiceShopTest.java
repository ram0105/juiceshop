package com.juiceshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JuiceShopTest {
    WebDriver webDriver;
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","/Users/ramprasanth/Downloads/chromedriver");
        webDriver= new ChromeDriver();
    }

    @Test
    public void accountCreation() {
        webDriver.get("http://127.0.0.1:3000/#/register");
        webDriver.findElement(By.xpath("//span[text()='Dismiss']")).click();
        webDriver.findElement(By.id("emailControl")).sendKeys("test.93@gmail.com");
        webDriver.findElement(By.id("passwordControl")).sendKeys("test@123");
        webDriver.findElement(By.id("repeatPasswordControl")).sendKeys("test@123");
        webDriver.findElement(By.xpath("//mat-select[@name='securityQuestion']")).click();
        webDriver.findElement(By.xpath("//span[(@class='mat-option-text') and text()[normalize-space() = 'Your eldest siblings middle name?']]")).click();
        webDriver.findElement(By.id("securityAnswerControl")).sendKeys("test");
        webDriver.findElement(By.id("registerButton")).click();
    }

    @Test
    public void login() {
        webDriver.findElement(By.id("email")).sendKeys("test.93@gmail.com");
        webDriver.findElement(By.id("password")).sendKeys("test@123");
        webDriver.findElement(By.id("loginButton")).click();
    }
}
