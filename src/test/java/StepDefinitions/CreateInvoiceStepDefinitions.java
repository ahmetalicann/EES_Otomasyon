package StepDefinitions;
import Pages.CreateInvoicePages;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class CreateInvoiceStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    CreateInvoicePages amazonPages = new CreateInvoicePages(driver);

    @When("Fatura Olusturma butonuna tiklanir")
    public void clickCreateInvoiceStep() {
        CreateInvoicePages.clickCreateInvoice();
    }

    @When("Yeni Ekle butonuna tiklanir")
    public void clickAddtoNewStep() {
        CreateInvoicePages.clickAddtoNew();
    }

    @When("VKN TCKN bilgisi eklenir")
    public void addtoReceiverVknTcknStep() {
        CreateInvoicePages.addtoReceiverVknTckn();
    }

    @When("Unvan bilgisi eklenir")
    public void addToTitleInfoStep() {
        CreateInvoicePages.addToTitleInfo();
    }

    @When("Mal Hizmet eklenir")
    public void addToProductStep() {
        CreateInvoicePages.addToProduct();
    }

    @When("Kaydet butonuna tiklanir")
    public void saveInvoiceStep() {
        CreateInvoicePages.saveInvoice();
    }

    @When("Fatura Numara eklenip aliciya gonderilir")
    public void addToNumberAndSendReceiverStep() {
        CreateInvoicePages.addToNumberAndSendReceiver();
    }

    @When("Giden Faturalar ekranina girilir")
    public void gotoInvoiceOutgoingListStep() {
        CreateInvoicePages.gotoInvoiceOutgoingList();
    }

    @When("Olusturulan belgenin durumu kontrol edilir")
    public void controlToInvoiceStatusStep() {
        CreateInvoicePages.controlToInvoiceStatus();
    }
}
