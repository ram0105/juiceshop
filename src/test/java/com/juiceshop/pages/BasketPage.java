package com.juiceshop.pages;

import com.juiceshop.testdriver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasketPage extends HomePage {
    String ADD_TO_BASKET="button[aria-label='Add to Basket']";
    String BASKET="button[aria-label='Show the shopping cart']";
    String BASKET_ITEMS="app-basket mat-table mat-row";
    String CHECKOUT="app-basket button[id='checkoutButton']";
    HomePage homePage=new HomePage();

    public void openBasket(){
        webDriver.findElement(By.cssSelector(BASKET)).click();
    }

    public void searchAndAddItemsToBasket(String [] items){
        for(String eachItem:items){
            homePage.searchProduct(eachItem);
            addToBasket();
            closeGrowl();
        }
    }
    public void checkout(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(BASKET_ITEMS)));
        webDriver.findElement(By.cssSelector(CHECKOUT)).click();
    }

    public void addToBasket(){
        webDriver.findElement(By.cssSelector(ADD_TO_BASKET)).click();
    }
}
