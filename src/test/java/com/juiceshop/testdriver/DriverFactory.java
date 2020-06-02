package com.juiceshop.testdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    public static Browser browser = Browser.CHROME;
    public static String environment = "remote";
    public static WebDriver webDriver;
    public static String hubUrl = "http://hub.com:4444/wd/hub";
    public static String baseUrl = "http://juice-shop:3000";
    static String CHROME_DRIVER_PATH = "/Users/ramprasanth/Downloads/chromedriver";
    static DesiredCapabilities capabilities = new DesiredCapabilities();

    public static WebDriver createDriver() {
        if (environment.equals("local")) {
            switch (browser) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
                    webDriver= new ChromeDriver();
                    break;
                case FIREFOX:
                    webDriver= new FirefoxDriver();
                    break;
                default:
                    webDriver= new SafariDriver();
            }
            return  webDriver;
        } else {
            switch (browser) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    capabilities.setCapability("browserName", "chrome");
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    capabilities.setCapability("browserName", "firefox");
                    break;
            }
            try {
                return new RemoteWebDriver(new URL(hubUrl), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        throw new WebDriverException("Something went wrong browser");
    }

    public synchronized static WebDriver getDriver() {
        if (webDriver != null) {
            return webDriver;
        } else {
            webDriver = createDriver();
            webDriver.get(baseUrl);
            webDriver.manage().window().maximize();
            return webDriver;
        }
    }

    public static void closeDriver() {
        if (webDriver != null)
            webDriver.quit();
    }
}
