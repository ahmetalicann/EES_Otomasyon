package util;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementHelper {
    WebDriver driver;
    WebDriverWait wait;
    Actions action;

    public ElementHelper(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.action = new Actions(driver);
    }

    public WebElement presenceElement(By key){
        return
                wait.until(ExpectedConditions.presenceOfElementLocated(key));
    }

    public WebElement findElement(By key){
        WebElement element = presenceElement(key);
        return element;
    }

    public void click(By key){
        findElement(key).click();
    }

    public void sendKey(By key,String text){
        findElement(key).sendKeys(text);
    }

    public String getText(By key){
        return findElement(key).getText();
    }

    public void checkVisible (By key){
        wait.until(ExpectedConditions.visibilityOf(findElement(key)));
    }

    public WebElement waitUntilClickable(By key) {
        return wait.until(ExpectedConditions.elementToBeClickable(key));
    }

    public void waitForSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000); // saniyeyi milisaniyeye çeviriyoruz
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public WebElement getNestedShadowDomElement(String... selectors) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        StringBuilder script = new StringBuilder("let el = document;");
        for (String selector : selectors) {
            String escapedSelector = selector.replace("'", "\\'");
            script.append("el = el.querySelector('").append(escapedSelector).append("');")
                    .append("if(!el) throw 'Element not found: ").append(escapedSelector).append("';")
                    .append("if(el.shadowRoot){ el = el.shadowRoot; }");
            try {
                Thread.sleep(250); // 0,25 saniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace(); // İsteğe göre loglama yapılabilir
            }
        }
        script.append("return el;");
        return (WebElement) js.executeScript(script.toString());
    }

    public void waitForValueToBeWithClick(By valueLocator, By buttonLocator, String expectedValue, int timeoutSeconds) {
        long startTime = System.currentTimeMillis();
        int pollIntervalMillis = 2000;

        while (true) {
            try {
                // Her denemede butona tıkla
                click(buttonLocator);

                WebElement element = findElement(valueLocator);
                String currentValue = element.getText();

                if (currentValue != null && currentValue.equalsIgnoreCase(expectedValue)) {
                    System.out.println("İstenen değere ulaşıldı: " + currentValue);
                    break;
                }

                if (System.currentTimeMillis() - startTime > timeoutSeconds * 1000) {
                    throw new TimeoutException("Belirtilen süre içinde değer '" + expectedValue + "' olmadı.");
                }

                Thread.sleep(pollIntervalMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Bekleme sırasında hata oluştu", e);
            } catch (Exception e) {
                System.out.println("Beklerken hata oluştu: " + e.getMessage());
            }
        }
    }

    public void waitUntilLoaderDisappears(By loaderLocator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loaderLocator));
    }

}
