package ru.geekbrains.steps;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.geekbrains.DriverInitializer;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class AdminSteps {
    private WebDriver webDriver = null;

    @Given("^I open admin web browser$")
    public void iOpenChromeBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to login page$")
    public void iNavigateToLoginHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("admin.login"));
    }


    @When("^I provide username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void iProvideUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.id("username"));
        webElement.sendKeys(username);
        webElement = webDriver.findElement(By.id("password"));
        webElement.sendKeys(password);
        Thread.sleep(2000);
    }

    @When("^I click on login button$")
    public void iClickOnLoginButton() throws Throwable {
        webDriver.findElement(By.cssSelector(".form-signin button")).click();
    }

    @When("^I navigate to product create page$")
    public void iNavigateToProductCreatePage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("admin.product.create"));
        Thread.sleep(2000);
    }

    @When("^I provide product title as \"([^\"]*)\" and description as \"([^\"]*)\" and cost as \"([^\"]*)\"$")
    public void iProvideProductInformation(String title, String description, String cost) throws Throwable {
        Thread.sleep(1000);
        WebElement webElement = webDriver.findElement(By.id("name"));
        webElement.sendKeys(title);
        webElement = webDriver.findElement(By.id("description"));
        webElement.sendKeys(description);
        webElement = webDriver.findElement(By.id("price"));
        webElement.sendKeys(cost);
        Thread.sleep(2000);
    }

    @When("^I click on submit button$")
    public void iClickOnSubmitButton() throws Throwable {
        webDriver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    }

    @When("^Then product title should be \"([^\"]*)\"$")
    public void productShouldBe(String title) throws Throwable {
        Thread.sleep(10000);
        webDriver.findElement(By.xpath("//*[contains(text(), '" + title + "')]"));
    }

    @After
    public void quitBrowser() {
        if (webDriver != null)
            webDriver.quit();
    }
}
