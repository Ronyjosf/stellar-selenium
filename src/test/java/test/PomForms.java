package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

class CareerForm {

    static WebDriver driver;
    Logger Log = Logger.getLogger ("test");

    // POM - career
    By city = By.id("geocomplete");
    By button1 = By.xpath("//button[@title='Select your Occupation']");
    By button2 = By.xpath("//button[@title='Select your Job Status']");
    By buttonNext = By.xpath("//button[@class='actions__next']");
}

class PeronalForm {

    static WebDriver driver;
    Logger Log = Logger.getLogger ("test");

    // POM - personal
    By firstName = By.id("user-first-name-input");
    By lastName = By.id ("user-last-name-input");
    By phoneNumber = By.id("user-phone-number-input");
    By emailAddress = By.id ("user-email-address-input");
}
