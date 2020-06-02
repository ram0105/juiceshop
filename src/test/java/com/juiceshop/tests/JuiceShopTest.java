package com.juiceshop.tests;

import com.github.javafaker.Faker;
import com.juiceshop.pages.*;
import com.juiceshop.testdriver.DriverFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class JuiceShopTest {
    HomePage homePage = new HomePage();
    BasketPage basketPage = new BasketPage();
    PaymentPage paymentPage = new PaymentPage();
    AddressPage addressPage = new AddressPage();
    public Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String securityQuestion = "Your eldest siblings middle name?";
    String answer = "Swathi";
    String[] items = {"Eggfruit Juice (500ml)", "Lemon Juice (500ml)", "Green Smoothie"};
    String deliverySpeed = "3 Days";
    String country = faker.address().country();
    String state = faker.address().state();
    String city = faker.address().city();
    String address = faker.address().fullAddress();
    String mobileNumber = faker.number().digits(10);
    String zipCode = faker.number().digits(8);
    String addressLabel = faker.address().streetName();
    String cardHolderName = faker.name().fullName();
    String cardNumber = faker.business().creditCardNumber().replaceAll("-", "");
    String expiryMonth = String.valueOf(faker.number().numberBetween(1, 12));
    String expiryYear = String.valueOf(faker.number().numberBetween(2080, 2090));

    @Test(testName = "Register a user & login,add items to basket,delivery address,payment methods and place order")
    public void endToEndTest() {
        homePage.registerUser(email, password, securityQuestion, answer);
        homePage.login(email, password);
        basketPage.searchAndAddItemsToBasket(items);
        basketPage.openBasket();
        basketPage.checkout();
        addressPage.addAddress(country, addressLabel, mobileNumber, address, zipCode, city, state);
        paymentPage.selectDeliveryAddress(addressLabel);
        paymentPage.proceedToPayment();
        paymentPage.selectDeliverySpeed(deliverySpeed);
        paymentPage.continueToPayment();
        paymentPage.addCreditOrDebitCard(cardHolderName, cardNumber, expiryMonth, expiryYear);
        paymentPage.selectPaymentCard(cardHolderName);
        paymentPage.proceedToReview();
        paymentPage.placeOrder();
    }

    @AfterClass
    public void close() {
        DriverFactory.closeDriver();
    }
}
