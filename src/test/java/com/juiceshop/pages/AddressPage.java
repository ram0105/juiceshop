package com.juiceshop.pages;

import com.juiceshop.testdriver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddressPage extends HomePage {
    String ADD_ADDRESS="button[aria-label='Add a new address']";
    String COUNTRY="//input[contains(@placeholder,'country')]";
    String ADDRESS_LABEL="//input[contains(@placeholder,'name')]";
    String MOBILE_NUMBER="//input[contains(@placeholder,'mobile number')]";
    String ADDRESS="address";
    String ZIP_CODE="//input[contains(@placeholder,'ZIP code')]";
    String CITY="//input[contains(@placeholder,'city')]";
    String STATE="//input[contains(@placeholder,'state')]";
    String SUBMIT="//app-address-create//button[@id='submitButton']";

    public void addAddress(String country,String addressLabel,String mobileNumber,String address,String zipcode,String city,String state){
        webDriver.findElement(By.cssSelector(ADD_ADDRESS)).click();
        webDriver.findElement(By.xpath(COUNTRY)).sendKeys(country);
        webDriver.findElement(By.xpath(ADDRESS_LABEL)).sendKeys(addressLabel);
        webDriver.findElement(By.xpath(MOBILE_NUMBER)).sendKeys(mobileNumber);
        webDriver.findElement(By.id(ADDRESS)).sendKeys(address);
        webDriver.findElement(By.xpath(ZIP_CODE)).sendKeys(zipcode);
        webDriver.findElement(By.xpath(CITY)).sendKeys(city);
        webDriver.findElement(By.xpath(STATE)).sendKeys(state);
        webDriver.findElement(By.xpath(SUBMIT)).click();
        closeGrowl();
    }

}
