package com.juiceshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class PaymentPage extends HomePage {
    String SELECT_DELIVERY_ADDRESS="//mat-cell[text()[normalize-space() ='%s']]/parent::mat-row//mat-radio-button";
    String PROCEED_TO_PAYMENT_SELECTION ="button[aria-label='Proceed to payment selection']";
    String SELECT_DELIVERY_SPEED="//mat-cell[text()[normalize-space() ='%s']]/parent::mat-row//mat-radio-button";
    String ADD_NEW_CARD="//mat-panel-title[text()[normalize-space()='Add new card']]";
    String FILL_TEXT="//mat-label[text()[normalize-space()='%s']]/parent::label/parent::span/parent::div/input";
    String SELECT_DROPDOWN="//mat-label[text()[normalize-space()='%s']]/parent::label/parent::span/parent::div/select";
    String SELECT_CARD="//mat-cell[text()[normalize-space() ='%s']]/parent::mat-row//mat-radio-button";
    String PROCEED_SUMMARY="button[aria-label='Proceed to review']";
    String COMPLETE_PAYMENT="button[aria-label='Complete your purchase']";
    String SUBMIT="submitButton";
    String CONTINUE_TO_PAYMENT="button[aria-label='Proceed to delivery method selection']";

    public void selectDeliveryAddress(String deliveryAddress){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(SELECT_DELIVERY_ADDRESS,deliveryAddress))));
        webDriver.findElement(By.xpath(String.format(SELECT_DELIVERY_ADDRESS,deliveryAddress))).click();
    }

    public void proceedToPayment(){
        webDriver.findElement(By.cssSelector(PROCEED_TO_PAYMENT_SELECTION)).click();
    }

    public void selectDeliverySpeed(String speed){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(SELECT_DELIVERY_SPEED,speed))));
        webDriver.findElement(By.xpath(String.format(SELECT_DELIVERY_SPEED,speed))).click();
    }
    public void addCreditOrDebitCard(String name,String cardNumber,String expiryMonth,String expiryYear){
        webDriver.findElement(By.xpath(ADD_NEW_CARD)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//mat-expansion-panel-header[contains(@class,'mat-expanded')]")));
        fillText("Name",name);
        fillText("Card Number",cardNumber);
        selectValueFromDropDown("Expiry Month",expiryMonth);
        selectValueFromDropDown("Expiry Year",expiryYear);
        webDriver.findElement(By.id(SUBMIT)).click();
        closeGrowl();
    }
    public void fillText(String label,String value){
        webDriver.findElement(By.xpath(String.format(FILL_TEXT,label))).sendKeys(value);
    }
    void selectValueFromDropDown(String label,String option){
        webDriver.findElement(By.xpath(String.format(SELECT_DROPDOWN,label))).click();
        Select dropdown=new Select(webDriver.findElement(By.xpath(String.format(SELECT_DROPDOWN,label))));
        dropdown.selectByVisibleText(option);
    }
    public void selectPaymentCard(String cardLabel){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(SELECT_CARD,cardLabel))));
        webDriver.findElement(By.xpath(String.format(SELECT_CARD,cardLabel))).click();
    }
    public void proceedToReview(){
        webDriver.findElement(By.cssSelector(PROCEED_SUMMARY)).click();
    }
    public void placeOrder(){
        webDriver.findElement(By.cssSelector(COMPLETE_PAYMENT)).click();
    }
    public void continueToPayment(){
        webDriver.findElement(By.cssSelector(CONTINUE_TO_PAYMENT)).click();
    }

}
