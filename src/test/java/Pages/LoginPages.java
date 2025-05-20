package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ElementHelper;

import java.time.Duration;

public class LoginPages {
    static WebDriver driver;
    static ElementHelper elementHelper;
    WebDriverWait wait;

    static By emailText = By.id("logo-elements-text-field-0");
    static By passwordTest = By.id("input-logo-elements-password-field-3");
    static By loginButton = By.cssSelector("#reactRoot .ant-col-lg-10 logo-elements-vertical-layout > logo-elements-horizontal-layout:nth-child(10)");
    static By firmBox = By.id("overlay");
    static By checkFirm = By.id("vaadin-checkbox-14");
    static By continueButton = By.cssSelector("#overlay > div > div > logo-elements-horizontal-layout > logo-elements-button:nth-child(2)");
    public static By loaderPopup = By.cssSelector("#test-thing > div > div");
    static By closeTourButton = By.cssSelector("button[data-test-id='button-skip']");



    public LoginPages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementHelper = new ElementHelper(driver);
    }

    public static void addEmailandPassword() {
        elementHelper.sendKey(emailText,"ElogoKaliteGuvence@elogo.com.tr");
        elementHelper.sendKey(passwordTest,"Aa802030");
    }

    public static void clickLogin() {
        elementHelper.click(loginButton);
    }

    public static void checkFirm() {
        elementHelper.checkVisible(firmBox);
        elementHelper.click(checkFirm);
    }

    public static void clickContinue() {
        elementHelper.waitUntilClickable(continueButton);
        elementHelper.click(continueButton);
    }

    public static void closeTour() {
        elementHelper.waitUntilLoaderDisappears(LoginPages.loaderPopup, 60);
        elementHelper.click(closeTourButton);
        elementHelper.waitForSeconds(1);
    }



}
