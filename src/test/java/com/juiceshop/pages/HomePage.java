package com.juiceshop.pages;

import com.juiceshop.testdriver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    static WebDriver webDriver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    String SEARCH_ICON = "//mat-icon[text()[normalize-space() ='search']]";
    String SEARCH_CLOSE_ICON = "//mat-icon[text()[normalize-space() ='close']]";
    String SEARCH_TEXT = "#searchQuery input";
    String EMAIL_TEXTBOX = "email";
    String PASSWORD_TEXTBOX = "password";
    String LOGIN_BUTTON = "loginButton";
    String EMAIL = "emailControl";
    String PASSWORD = "passwordControl";
    String REPEAT_PASSWORD = "repeatPasswordControl";
    String SECURITY_QUESTION_DROPDOWN = "//mat-select[@name='securityQuestion']";
    String SECURITY_QUESTION = "//span[(@class='mat-option-text') and text()[normalize-space() = '%s']]";
    String SECURITY_QUESTION_ANSWER = "securityAnswerControl";
    String REGISTER_ID = "registerButton";
    String WELCOME_BANNER = "//button[@aria-label='Close Welcome Banner']";
    String ACCEPT_COOKIES = "a[aria-label='dismiss cookie message']";
    String COOKIE_BANNER="div[aria-label='cookieconsent']";
    String GROWL="//div[contains(@class,'cdk-overlay-container')]//button";

    public void login(String email, String password) {
        closeBanner();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EMAIL_TEXTBOX)));
        webDriver.findElement(By.id(EMAIL_TEXTBOX)).sendKeys(email);
        webDriver.findElement(By.id(PASSWORD_TEXTBOX)).sendKeys(password);
        webDriver.findElement(By.id(LOGIN_BUTTON)).click();
    }

    public void searchProduct(String searchText) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SEARCH_ICON)));
        webDriver.findElement(By.xpath(SEARCH_ICON)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SEARCH_TEXT)));
        webDriver.findElement(By.cssSelector(SEARCH_TEXT)).sendKeys(searchText);
        webDriver.findElement(By.cssSelector(SEARCH_TEXT)).sendKeys(Keys.ENTER);
        webDriver.findElement(By.xpath(SEARCH_CLOSE_ICON)).click();
    }

    public void registerUser(String email, String password, String securityQuestion, String answer) {
        closeBanner();
        navigate("/#/register");
        webDriver.findElement(By.id(EMAIL)).sendKeys(email);
        webDriver.findElement(By.id(PASSWORD)).sendKeys(password);
        webDriver.findElement(By.id(REPEAT_PASSWORD)).sendKeys(password);
        webDriver.findElement(By.xpath(SECURITY_QUESTION_DROPDOWN)).click();
        webDriver.findElement(By.xpath(String.format(SECURITY_QUESTION, securityQuestion))).click();
        webDriver.findElement(By.id(SECURITY_QUESTION_ANSWER)).sendKeys(answer);
        webDriver.findElement(By.id(REGISTER_ID)).click();
    }

    void closeBanner() {
        if (webDriver.findElements(By.xpath(WELCOME_BANNER)).size() > 0)
            webDriver.findElement(By.xpath(WELCOME_BANNER)).click();
        if (webDriver.findElements(By.cssSelector(COOKIE_BANNER)).size() > 0 && webDriver.findElement(By.cssSelector(COOKIE_BANNER)).isDisplayed())
            webDriver.findElement(By.cssSelector(ACCEPT_COOKIES)).click();
    }

    void closeGrowl(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(GROWL)));
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", webDriver.findElement(By.xpath(GROWL)));
    }

    void navigate(String url) {
        webDriver.get(DriverFactory.baseUrl + url);
    }
}
