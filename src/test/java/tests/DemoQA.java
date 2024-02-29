package tests;

import drivers.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import utils.PropertyManager;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import static java.awt.SystemColor.window;

public class DemoQA {
    public static WebDriver driver;
    Driver webDriver = new Driver();
    PropertyManager propertyManager = new PropertyManager();
    String url = propertyManager.getProperty("APP_URL");


    @BeforeMethod(alwaysRun = true)
    public void before() throws MalformedURLException {
        driver = webDriver.initializeDriver();
        driver.get(url);
    }

    @Test
    public void openGooglePageTest() {
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle, "DEMOQA");
    }

    @Test
    public void clickButtons() throws InterruptedException {
        WebElement buttons = driver.findElement(new By.ByXPath("//span[text()=\"Buttons\"]"));
        buttons.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
      WebElement clickmeButton = driver.findElement(new By.ByXPath("//button[starts-with(., 'Click Me')]"));
        clickmeButton.click();
        WebElement messageElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id='dynamicClickMessage']")));
         String message = messageElement.getText();
          System.out.println(message);
         Assert.assertEquals(message, "You have done a dynamic click");
        driver.get("https://demoqa.com/webtables");
        WebElement addButton = driver.findElement(new By.ByXPath("//button[@id='addNewRecordButton']"));
        addButton.click();
        WebElement firstName = driver.findElement(new By.ByXPath("//input[@id='firstName']"));
        firstName.sendKeys("Begüm");
        WebElement lastName = driver.findElement(new By.ByXPath("//input[@id='lastName']"));
        lastName.sendKeys("Pala");
        WebElement eMail = driver.findElement(new By.ByXPath("//input[@id='userEmail']"));
        eMail.sendKeys("email@test.com");
        WebElement age = driver.findElement(new By.ByXPath("//input[@id='age']"));
        age.sendKeys("25");
        WebElement salary = driver.findElement(new By.ByXPath("//input[@id='salary']"));
        salary.sendKeys("15000");
        WebElement department = driver.findElement(new By.ByXPath("//input[@id='department']"));
        department.sendKeys("ARGE");
        WebElement submitButton = driver.findElement(new By.ByXPath("//button[@id='submit']"));
        submitButton.click();
        WebElement search = driver.findElement(new By.ByXPath("//input[@id='searchBox']"));
        search.sendKeys("Begüm");
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        Thread.sleep(2000);
        WebElement editButton = driver.findElement(new By.ByXPath("//span[@title='Edit']"));
        editButton.click();
        WebElement editName = driver.findElement(new By.ByXPath("//input[@id='firstName']"));
        editName.sendKeys(" Edit");
        WebElement editLastName = driver.findElement(new By.ByXPath("//input[@id='lastName']"));
        editLastName.sendKeys(" Edit");
        WebElement eMailEdit = driver.findElement(new By.ByXPath("//input[@id='userEmail']"));
        eMailEdit.clear();
        eMailEdit.sendKeys("Edit@hotmail.com");
        WebElement ageEdit = driver.findElement(new By.ByXPath("//input[@id='age']"));
        ageEdit.sendKeys("2");
        WebElement salaryEdit = driver.findElement(new By.ByXPath("//input[@id='salary']"));
        salaryEdit.sendKeys("120");
        WebElement departmentEdit = driver.findElement(new By.ByXPath("//input[@id='department']"));
        departmentEdit.sendKeys(" Edit");
        WebElement submitButtonEdit = driver.findElement(new By.ByXPath("//button[@id='submit']"));
        submitButtonEdit.click();

    }

    @AfterMethod(alwaysRun = true)
    public void after () {
        webDriver.quitDriver();
    }
}