package com.juiceshop;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class JuiceShopTest {
    WebDriver webDriver;
    String EMAIL = "test5.93@gmail.com";
    String PASSWORD = "test@123";
    String CHROME_DRIVER_PATH="/Users/ramprasanth/Downloads/chromedriver";
    WebDriverWait wait;


    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        webDriver = new ChromeDriver();
        wait= new WebDriverWait(webDriver,20);
        webDriver.manage().window().maximize();
        webDriver.get("http://127.0.0.1:3000/#/register");
    }

    @Test
    public void accountCreation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Close Welcome Banner']")));
        webDriver.findElement(By.xpath("//button[@aria-label='Close Welcome Banner']")).click();
        webDriver.findElement(By.id("emailControl")).sendKeys(EMAIL);
        webDriver.findElement(By.id("passwordControl")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("repeatPasswordControl")).sendKeys(PASSWORD);
        webDriver.findElement(By.xpath("//mat-select[@name='securityQuestion']")).click();
        webDriver.findElement(By.xpath("//span[(@class='mat-option-text') and text()[normalize-space() = 'Your eldest siblings middle name?']]")).click();
        webDriver.findElement(By.id("securityAnswerControl")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("registerButton")).click();
    }

    @Test
    public void login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        webDriver.findElement(By.id("email")).sendKeys(EMAIL);
        webDriver.findElement(By.id("password")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("loginButton")).click();
    }
}
