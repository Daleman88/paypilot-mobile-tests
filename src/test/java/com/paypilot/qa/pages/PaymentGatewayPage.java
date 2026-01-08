package com.paypilot.qa.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Esta clase representa la pantalla de la pasarela de pago que se carga en un WebView.
 */
public class PaymentGatewayPage {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public PaymentGatewayPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Localizadores ---

    // SOLUCIÓN AL BUG: Se declara la variable como el tipo base 'By' que es lo que devuelve el método.
    private static final By WEB_VIEW_CONTAINER = AppiumBy.className("android.webkit.WebView");

    // --- Verificaciones ---

    /**
     * Verifica si el contenedor del WebView está visible en la pantalla.
     * @return true si el WebView es visible, false en caso contrario.
     */
    public boolean isWebViewDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(WEB_VIEW_CONTAINER));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
