package com.juiceshop.tests;

import com.juiceshop.testdriver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    WebDriver webDriver;
    @BeforeClass
    void initializeDriver(){
        webDriver=new DriverFactory().createDriver();
    }
}
