package StepDefinitions;
import Pages.LoginPages;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class LoginStepDefinitions {
    WebDriver driver = DriverFactory.getDriver();
    LoginPages amazonPages = new LoginPages(driver);

    @Given("Entegratorluk portal giris yapılır")
    public void goToUrlStep() {
    }

    @When("ePosta ve Sifre alani doldurulur")
    public void addEmailandPasswordStep() {
        LoginPages.addEmailandPassword();
    }

    @When("Giris Yap butonuna tiklanir")
    public void clickLoginStep() {
        LoginPages.clickLogin();
    }

    @When("Firma secimi yapilir")
    public void checkFirmStep() {
        LoginPages.checkFirm();
    }

    @When("Devam butonuna tiklanir")
    public void clickContinueStep() {
        LoginPages.clickContinue();
    }

    @When("Turu Kapat tiklanir")
    public void closeTourStep() {
        LoginPages.closeTour();
    }

}
