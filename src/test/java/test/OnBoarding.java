package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.JVM)
public class OnBoarding {

    static WebDriver driver;
    static Logger Log = Logger.getLogger ("test");
    CareerForm career = new CareerForm();
    PeronalForm personal = new PeronalForm();

    // POM - about
    By userLinkedIn = By.id("user-linkedin-about");


    //By selectOccupation = By.xpath("//div[contains(@id, 'user-occupation') and span()='Select your Occupation']");


    //By selectOccupation = By.xpath("//*[@id=\"user-occupation\"]/div[@class=\"btn-group bootstrap-select\"]/button/span[@class='filter-option pull-left']");
    By selectOccupation = By.cssSelector("#user-occupation > div.btn-group.bootstrap-select > button > span.filter-option.pull-left");

//@class='filter-option pull-left'
    public void assertClassPresent(WebDriver driver, String className){
        try{
            driver.findElement(By.className(className));

        } catch (NoSuchElementException ex){}
    }

    @BeforeClass
    public static void init() throws MalformedURLException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
        driver = new ChromeDriver();
    }
    @Test
    public void launchWeb(){
        driver.get("https://qa-dot-stellar-test.appspot.com/launch_chatbot?tsn=applicant&tg=homepage&lpn=100\n");
        assertTrue("couldn't load web page",driver.findElement(By.className("page-title")).isDisplayed());
        Log.info("launch web");
    }

    @Test
    public void careerForm() throws InterruptedException {
        driver.findElement(career.city).sendKeys("stam");

        // user occupation
        driver.findElement(career.button1).click();
        driver.findElement(By.xpath("//div[@id='user-occupation']/div/div/ul/li[2]/a/span")).click();
//        driver.findElement(By.xpath("css=select.selectpicker"));
        //selectOccupation.ByXPath("contains(text), 'Software Engineer'")
        //driver.findElement(selectOccupation);//.click();//("Software Engineer");//.sendKeys("");

        // job status
        driver.findElement(career.button2).click();
        driver.findElement(By.xpath("//div[@id='user-status']/div/div/ul/li[2]/a/span")).click();

        // click next
        driver.findElement(career.buttonNext).click();
        sleep(1000);
        // assert
        assertEquals("assertTrue - career form didn't ended succuessfuly", true, driver.findElement(personal.firstName).isDisplayed());//
    }

    @Test
    public void personalInfo() throws InterruptedException {
        Log.info("starting personal info");
        try {
            if (!(driver.findElement(personal.firstName).isDisplayed()))
                Log.info("could't find careerForm, thus running it again");
                careerForm();
        } catch(Exception e) {}

        driver.findElement(personal.firstName).sendKeys("aaa");
        driver.findElement(personal.lastName).sendKeys("last name");
        driver.findElement(personal.phoneNumber).sendKeys("0509999888");
        driver.findElement(personal.emailAddress).sendKeys("1@2.com");

        driver.findElement(career.buttonNext).click();

        // assert
        sleep(1000);
        assertEquals("couldn't find about form", true, driver.findElement(userLinkedIn).isDisplayed());

    }
    @AfterClass
    public static void teardown() throws InterruptedException {
        sleep(1000);
        Log.info("teardown - closing driver");
        driver.close();
    }

}
