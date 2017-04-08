package ohtu;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class Tester {

    public static void main(String[] args) {

        File pathBinary = new File("/home/jaakvirt/Downloads/firefox/firefox");
        FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(firefoxBinary, firefoxProfile);

        driver.get("http://localhost:4567");

        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("asdgjldsagk");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("gadsojiod");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(1);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(1);

        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);
        element = driver.findElement(By.name("username"));
        element.sendKeys("kalle");
        element = driver.findElement(By.name("password"));
        element.sendKeys("ellak");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ellak");
        element = driver.findElement(By.name("signup"));

        sleep(1);
        element.submit();

        sleep(1);

        element = driver.findElement(By.linkText("continue to application webpage"));
        sleep(1);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        sleep(1);

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}
