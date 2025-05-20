package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import util.ElementHelper;

import java.time.Duration;

public class CreateInvoicePages {

    static WebDriver driver;
    static ElementHelper elementHelper;
    WebDriverWait wait;

    //Fatura Oluşturma
    static By createInvoiceButton = By.xpath("/html/body/div[1]/div/logo-elements-app-layout/logo-elements-horizontal-layout/aside/div/ul/li[3]/span/a");
    static By receiverVknTckn = By.cssSelector("input[placeholder='VKN/TCKN']");
    static By titleInfo = By.cssSelector("input[placeholder='Unvan']");
    static By addProduct = By.cssSelector("button[class='ant-btn ant-btn-primary sc-iqHYmW sc-bqyKOL eNFcSc itMihb']");
    static By productName = By.cssSelector("div[class='ant-col ant-col-16'] input[type='text']");
    static By saveInvoiceButton = By.cssSelector("button[class='ant-btn ant-btn-primary sc-iqHYmW sc-bqyKOL sc-hBEYId eNFcSc itMihb dJwCxV'] span");
    static By addToNumberAndSendButton = By.xpath("//div[normalize-space()='Numara Ver ve Gönder']");
    static By okNumberButton = By.cssSelector("logo-elements-button[role='button'][theme='primary']");
    static By cancelNumberButton = By.cssSelector("logo-elements-button[role='button'][theme='tertiary']");
    static By alertMessageElement = By.cssSelector("#overlay > div > div:nth-child(1)");

    //Fatura Oluşturma değişkenleri

    static String alertMessage;
    static String belgeNo = null;

    //Giden e-Faturalar

    static By invoiceSubMenu = By.xpath("//div[@role='menuitem']//div[contains(text(),'e-Fatura')]");
    static By invoiceOutgoingListMenu = By.cssSelector("a[href='/Invoice/EInvoiceOutgoingList']");
    static By invoiceOutgoingListNumber = By.xpath("//*[@id='reactRoot']/logo-elements-app-layout/logo-elements-horizontal-layout/div/logo-elements-form-layout/logo-elements-form-item[2]/vaadin-logo-grid-pro/vaadin-grid-cell-content[64]/div");
    public static By invoiceOutgoingListStatus = By.cssSelector("#reactRoot > logo-elements-app-layout > logo-elements-horizontal-layout > div > logo-elements-form-layout > logo-elements-form-item:nth-child(5) > vaadin-logo-grid-pro > vaadin-grid-cell-content:nth-child(90) > div > span");
    public static By invoiceOutgoingFilterButton = By.cssSelector("#reactRoot > logo-elements-app-layout > logo-elements-horizontal-layout > div > logo-elements-form-layout > logo-elements-form-item:nth-child(1) > logo-elements-vertical-layout > div:nth-child(2) > div:nth-child(3) > logo-elements-form-layout > logo-elements-form-item > logo-elements-vertical-layout > logo-elements-horizontal-layout > logo-elements-horizontal-layout > logo-elements-button:nth-child(2)");

    public CreateInvoicePages(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.elementHelper = new ElementHelper(driver);
    }

    public static void clickCreateInvoice() {
        elementHelper.click(createInvoiceButton);
    }

    public static void clickAddtoNew() {
        elementHelper.waitForSeconds(3);
        WebElement spanElement = elementHelper.getNestedShadowDomElement(
                "#reactRoot-alt",
                "#invoiceCreationList-addNew",
                "span"
        );
        spanElement.click();
        elementHelper.waitForSeconds(2);
    }

    public static void addtoReceiverVknTckn() {
        elementHelper.sendKey(receiverVknTckn,"6090408038");
    }

    public static void addToTitleInfo() {
        elementHelper.sendKey(titleInfo,"LOGO ELEKTRONİK TİCARET HİZMETLERİ ANONİM ŞİRKETİ");
    }


    public static void addToProduct() {
        elementHelper.waitForSeconds(2);
        elementHelper.click(addProduct);
        elementHelper.sendKey(productName,"testProduct");
        elementHelper.waitForSeconds(2);
    }

    public static void saveInvoice() {
        elementHelper.click(saveInvoiceButton);
    }


    public static void addToNumberAndSendReceiver() {
        elementHelper.waitForSeconds(5);
        WebElement threeDotsElement = elementHelper.getNestedShadowDomElement(
                "#reactRoot-alt",
                "#invoiceCreationList-menu-grid",
                "#invoiceCreationList-three-dots-0 > logo-elements-icon",
                "svg"
        );
        threeDotsElement.click();
        elementHelper.waitForSeconds(2);
        elementHelper.click(addToNumberAndSendButton);
        elementHelper.waitForSeconds(3);
        alertMessage = elementHelper.getText(alertMessageElement);
        int baslangicIndex = alertMessage.indexOf("Oluşturulacak belge numarası : ") + "Oluşturulacak belge numarası : ".length();
        int bitisIndex = alertMessage.indexOf(" olarak belirlenmiştir");

        if (baslangicIndex >= 0 && bitisIndex > baslangicIndex) {
            belgeNo = alertMessage.substring(baslangicIndex, bitisIndex).trim();
            System.out.println("Belge Numarası: " + belgeNo);
        }
        //elementHelper.click(cancelNumberButton);
        elementHelper.waitUntilClickable(okNumberButton);
        elementHelper.click(okNumberButton);
        elementHelper.waitUntilClickable(okNumberButton);
        elementHelper.click(okNumberButton);
    }

    public static void gotoInvoiceOutgoingList() {
        elementHelper.waitUntilClickable(invoiceSubMenu);
        elementHelper.click(invoiceSubMenu);
        elementHelper.waitUntilClickable(invoiceOutgoingListMenu);
        elementHelper.click(invoiceOutgoingListMenu);
    }

    public static void controlToInvoiceStatus() {
        elementHelper.waitForSeconds(3);
        Assert.assertEquals(belgeNo,elementHelper.getText(invoiceOutgoingListNumber),"Gönderilen belge numarası ile mevcut aynı değildir.");
        String message = elementHelper.getText(invoiceOutgoingListStatus);
        elementHelper.waitForValueToBeWithClick(
                CreateInvoicePages.invoiceOutgoingListStatus,
                CreateInvoicePages.invoiceOutgoingFilterButton,
                "Başarılı",
                300);
    }
}
