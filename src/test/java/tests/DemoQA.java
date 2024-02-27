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
        WebElement buttons = driver.findElement(new By.ByCssSelector("li#item-4"));
        buttons.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");
        WebElement clickmeButton = driver.findElement(new By.ByCssSelector(".col-md-6 div:nth-of-type(3) > .btn"));
        clickmeButton.click();
        WebElement messageElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p#dynamicClickMessage")));
        String message = messageElement.getText();
        System.out.println(message);
        Assert.assertEquals(message, "You have done a dynamic click");
        driver.get("https://demoqa.com/webtables");
        WebElement addButton = driver.findElement(new By.ByCssSelector("button#addNewRecordButton"));
        addButton.click();
        WebElement firstName = driver.findElement(new By.ByCssSelector("input#firstName"));
        firstName.sendKeys("Begüm");
        WebElement lastName = driver.findElement(new By.ByCssSelector("input#lastName"));
        lastName.sendKeys("Pala");
        WebElement eMail = driver.findElement(new By.ByCssSelector("input#userEmail"));
        eMail.sendKeys("email@test.com");
        WebElement age = driver.findElement(new By.ByCssSelector("input#age"));
        age.sendKeys("25");
        WebElement salary = driver.findElement(new By.ByCssSelector("input#salary"));
        salary.sendKeys("15000");
        WebElement department = driver.findElement(new By.ByCssSelector("input#department"));
        department.sendKeys("ARGE");
        WebElement submitButton = driver.findElement(new By.ByCssSelector(" button#submit"));
        submitButton.click();
        List<WebElement> rows = driver.findElements(new By.ByCssSelector("div.rt-table > div.rt-tbody > div.rt-tr-group"));
        for (WebElement row : rows) {
            WebElement nameElement = row.findElement(new By.ByCssSelector("div.rt-td"));
            String name = nameElement.getAttribute("textContent").trim();
            System.out.println(name);
            if (name.equals("Begüm")) {
                WebElement search = driver.findElement(new By.ByCssSelector("input#searchBox"));
                search.sendKeys("Begüm");
                js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0, 500);");
                Thread.sleep(2000);
                WebElement editButton = driver.findElement(new By.ByCssSelector("span#edit-record-4"));
                editButton.click();
                WebElement editName = driver.findElement(new By.ByCssSelector("input#firstName"));
                editName.sendKeys(" Edit");
                WebElement editLastName = driver.findElement(new By.ByCssSelector("input#lastName"));
                editLastName.sendKeys(" Edit");
                WebElement eMailEdit = driver.findElement(new By.ByCssSelector("input#userEmail"));
                eMailEdit.sendKeys("emailEdit@test.com");
                WebElement ageEdit = driver.findElement(new By.ByCssSelector("input#age"));
                ageEdit.sendKeys("2");
                WebElement salaryEdit = driver.findElement(new By.ByCssSelector("input#salary"));
                salaryEdit.sendKeys("120");
                WebElement departmentEdit = driver.findElement(new By.ByCssSelector("input#department"));
                departmentEdit.sendKeys(" Edit");
                WebElement submitButtonEdit = driver.findElement(new By.ByCssSelector(" button#submit"));
                submitButtonEdit.click();

            }


        }
    }
    @AfterMethod(alwaysRun = true)
    public void after () {
        webDriver.quitDriver();
    }
}
