package com.paypilot.qa.tests;

import com.paypilot.qa.basetest.BaseTest;
import com.paypilot.qa.data.PaymentData;
import com.paypilot.qa.pages.PaymentGatewayPage;
import com.paypilot.qa.pages.PaymentPage;
import com.paypilot.qa.utils.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Clase que contiene todos los casos de prueba relacionados con la pantalla de pago.
 */
public class PaymentTest extends BaseTest {

    @Test(priority = 1, description = "Realiza un flujo de pago E2E con datos de prueba.")
    public void performE2EPaymentTest() {
        // ARRANGE
        Log.step("Iniciando el caso de prueba del Happy Path.");
        PaymentPage paymentPage = new PaymentPage(driver);
        PaymentData testData = new PaymentData("150", "gtKFFx", "test.user@example.com");

        // ACT
        Log.step("Rellenando el formulario de pago con datos válidos.");
        paymentPage.fillPaymentForm(testData);
        Log.step("Haciendo clic en 'Pagar Ahora'.");
        paymentPage.clickPayNow();

        // ASSERT
        Log.step("Verificando que la pasarela de pago se ha cargado...");
        PaymentGatewayPage gatewayPage = new PaymentGatewayPage(driver);
        boolean isGatewayDisplayed = gatewayPage.isWebViewDisplayed();

        Assert.assertTrue(isGatewayDisplayed, 
            "FALLO DE HAPPY PATH: La pasarela de pago no se mostró. Causa probable: El bug de la apps que muestra un error de conexión o de hash.");
        
        Log.step("¡Verificación exitosa! La pasarela de pago se cargó correctamente.");
    }

    @Test(priority = 2, description = "Verifica el mensaje de error al intentar pagar con campos vacíos.")
    public void testEmptyFields() {
        // ARRANGE
        Log.step("Iniciando el caso de prueba de campos vacíos.");
        PaymentPage paymentPage = new PaymentPage(driver);
        PaymentGatewayPage gatewayPage = new PaymentGatewayPage(driver);
        
        List<String> expectedErrorMessages = Arrays.asList(
            "Something went wrong : Invalid Hash.",
            "Could not receive data",
            "Something went wrong : https://test.payu.in/merchant/postservice?form=2"
        );

        // ACT
        Log.step("Haciendo clic en 'Pagar Ahora' sin rellenar datos.");
        paymentPage.clickPayNow();
        String actualErrorMessage = paymentPage.getToastMessage();

        // ASSERT
        Log.step("Verificando que el mensaje de error ('" + actualErrorMessage + "') es uno de los esperados...");
        Assert.assertTrue(expectedErrorMessages.contains(actualErrorMessage), 
            "El mensaje de error mostrado ('" + actualErrorMessage + "') no es ninguno de los errores conocidos y esperados.");

        Log.step("Verificando que la prueba NO avanzó a la siguiente pantalla.");
        Assert.assertFalse(gatewayPage.isWebViewDisplayed(), 
            "La aplicación avanzó a la pasarela de pago incorrectamente.");
    }

    @Test(priority = 3, description = "Verifica el error al pagar con un formato de email inválido.")
    public void testInvalidEmailFormat() {
        // ARRANGE
        Log.step("Iniciando el caso de prueba de email inválido.");
        PaymentPage paymentPage = new PaymentPage(driver);
        PaymentGatewayPage gatewayPage = new PaymentGatewayPage(driver);
        PaymentData testData = new PaymentData("200", "gtKFFx", "test.user.com");

        List<String> expectedErrorMessages = Arrays.asList(
            "Something went wrong : Invalid Hash.",
            "Could not receive data",
            "Something went wrong : https://test.payu.in/merchant/postservice?form=2"
        );

        // ACT
        Log.step("Rellenando formulario con email inválido y haciendo clic en 'Pagar Ahora'.");
        paymentPage.fillPaymentForm(testData);
        paymentPage.clickPayNow();
        String actualErrorMessage = paymentPage.getToastMessage();

        // ASSERT
        Log.step("Verificando que el mensaje de error ('" + actualErrorMessage + "') es uno de los genéricos esperados.");
        Assert.assertTrue(expectedErrorMessages.contains(actualErrorMessage),
            "La apps no mostró un error genérico esperado. En su lugar, mostró: '" + actualErrorMessage + "'.");

        Log.step("Verificando que la prueba NO avanzó a la siguiente pantalla.");
        Assert.assertFalse(gatewayPage.isWebViewDisplayed(),
            "La aplicación avanzó a la pasarela de pago a pesar del email inválido.");
    }
}
