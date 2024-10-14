package codexstester.bdd.stepsdef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSteps {

    private static final Log log = LogFactory.getLog(LoginSteps.class);
    private final String urlWebsite = "https://practicetestautomation.com/practice-test-login/";

    WebDriverWait wait;
    private static WebDriver driver;

    private static final String USERNAME_INPUT_XPATH = "//input[@id='username']";
    private static final String PASSWORD_INPUT_XPATH = "//input[@id='password']";
    private static final String SUBMIT_BUTTON_XPATH = "//button[@id='submit']";
    private static final String LOGOUT_BUTTON_XPATH = "//a[text()='Log out']";
    private static final String LOGIN_ERROR_XPATH = "//div[@id='error']";

    public LoginSteps() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 15L);
    }

    public void tearDown() {
        try {
            // Force login page alive for 5 seconds (just for visualize the login successfully)
            Thread.sleep(2000);
            driver.quit();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Given("user is on login page")
    public void userIsOnLoginPage() {
        driver.get(urlWebsite);
    }

    @When("user login with {string} and {string}")
    public void userLoginWithUsernameAndPassword(String username, String password) {

        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(USERNAME_INPUT_XPATH)));
        usernameField.sendKeys(username);

        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INPUT_XPATH)));
        passwordField.sendKeys(password);

        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath(SUBMIT_BUTTON_XPATH)));
        submitButton.click();

    }

    @Then("login status should be {}")
    public void loginSuccessfully(boolean status) {

        if (status) {

            // When login is successfully the button logout is visible
            WebElement logoutButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_BUTTON_XPATH)));
            Assert.assertEquals(status, logoutButton.isDisplayed());

        } else {

            WebElement loginError = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath(LOGIN_ERROR_XPATH)));
            Assert.assertTrue(loginError.isDisplayed());

            if (loginError.getText().contains("username")) {
                Assert.assertEquals("Your username is invalid!", loginError.getText());
            } else {
                Assert.assertEquals("Your password is invalid!", loginError.getText());
            }

        }

        this.tearDown();

    }

}
