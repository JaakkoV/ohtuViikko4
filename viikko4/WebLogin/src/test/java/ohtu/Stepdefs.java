package ohtu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Stepdefs {

    File pathBinary = new File("/home/jaakvirt/Downloads/firefox/firefox");
    FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
    FirefoxProfile firefoxProfile = new FirefoxProfile();
    WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);
    String baseUrl = "http://localhost:4567";

    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
    }

    @Given("^new user is selected$")
    public void new_user_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given to create new$")
    public void username_and_incorrect_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWith(username, password);
    }

    @When("^too short username \"([^\"]*)\" and password \"([^\"]*)\" are given to create new$")
    public void too_short_username_and_incorrect_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and short password \"([^\"]*)\" are given to create new$")
    public void username_and_short_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and mismatching passwords \"([^\"]*)\" are given to create new$")
    public void username_and_mis_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWithFalse(username, password);
    }

    @When("^correct username \"([^\"]*)\" and false password \"([^\"]*)\" are given to create new$")
    public void username_and_false_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWith(username, password);
    }

    @When("^taken username \"([^\"]*)\" and password \"([^\"]*)\" are given to create new$")
    public void taken_uname_and_password_are_given_to_create_new(String username, String password) throws Throwable {
        createUserWith(username, password);
        Thread.sleep(100000);
    }

    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }

    @Then("^user gets welcome page$")
    public void user_gets_welcome() throws Throwable {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void user_gets_error(String error) throws Throwable {
        pageHasContent(error);
    }

    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void user_with_username_with_password_is_succesfully_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);

        element = driver.findElement(By.name("password"));
        element.sendKeys(password);

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element.submit();
    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is unsuccessfully created$")
    public void user_with_username_and_password_is_unsuccesfully_created(String username, String password) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);

        element = driver.findElement(By.name("password"));
        element.sendKeys(password);

        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element.submit();
    }

    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("^incorrect username \"([^\"]*)\" and any random password \"([^\"]*)\" is given$")
    public void incorrect_username_and_any_random_password_is_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /* helper methods */
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createUserWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }

    private void createUserWithFalse(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("fkdasogk");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
