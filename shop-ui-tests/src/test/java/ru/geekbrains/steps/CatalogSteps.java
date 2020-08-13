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

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CatalogSteps {
    private WebDriver webDriver = null;

    @Given("^I open web browser$")
    public void iOpenChromeBrowser() throws Throwable {
        webDriver = DriverInitializer.getDriver();
    }

    @When("^I navigate to product index page$")
    public void iNavigateToCatalogHtmlPage() throws Throwable {
        webDriver.get(DriverInitializer.getProperty("admin.login"));
    }

    @Then("^Page has products$")
    public void pageHaseProduct() throws Throwable {
        List<WebElement> products = webDriver.findElements(By.className("f_p_item"));
        assertTrue(products.size() > 2);
    }

    @After
    public void quitBrowser() {
        if (webDriver != null)
            webDriver.quit();
    }
}
