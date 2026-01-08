package com.paypilot.qa.pages;

import com.paypilot.qa.data.PaymentData;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Clase que representa la pantalla principal de la demo de PayU (MainActivity).
 * Sigue el patrón Page Object Model (POM).
 */
public class PaymentPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public PaymentPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- LOCALIZADORES (LOCATORS) ---
    // SOLUCIÓN AL BUG: Se declara la variable como el tipo base 'By' que es lo que devuelve el método.
    private static final By TOAST_MESSAGE = AppiumBy.xpath("//android.widget.Toast");

    // --- MÉTODOS PARA OBTENER ELEMENTOS ---

    public WebElement getAmountField() {
        return driver.findElement(AppiumBy.id("com.payu.testapp:id/editTextAmount"));
    }

    public WebElement getMerchantKeyField() {
        return driver.findElement(AppiumBy.id("com.payu.testapp:id/editTextMerchantKey"));
    }

    public WebElement getEmailField() {
        return driver.findElement(AppiumBy.id("com.payu.testapp:id/editTextEmail"));
    }

    public WebElement getPayNowButton() {
        return driver.findElement(AppiumBy.id("com.payu.testapp:id/btnPayNow"));
    }

    // --- MÉTODOS DE ACCIÓN DE LA PÁGINA ---

    /**
     * REFACTORIZACIÓN: Rellena el formulario de pago usando un objeto PaymentData.
     * @param data El objeto que contiene toda la información del formulario.
     */
    public void fillPaymentForm(PaymentData data) {
        getAmountField().clear();
        getAmountField().sendKeys(data.getAmount());

        getMerchantKeyField().clear();
        getMerchantKeyField().sendKeys(data.getMerchantKey());

        getEmailField().clear();
        getEmailField().sendKeys(data.getEmail());
    }

    public void clickPayNow() {
        getPayNowButton().click();
    }

    public String getToastMessage() {
        try {
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_MESSAGE));
            return toast.getText();
        } catch (Exception e) {
            System.err.println("No se encontró ningún mensaje Toast.");
            return "";
        }
    }
}
