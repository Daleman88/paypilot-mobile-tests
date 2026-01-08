package com.paypilot.qa.data;

/**
 * POJO (Plain Old Java Object) que representa los datos necesarios
 * para rellenar el formulario de pago.
 * Su propósito es encapsular los datos de prueba en un objeto único,
 * haciendo el código más limpio y fácil de mantener.
 */
public class PaymentData {

    // --- CAMPOS DE DATOS ---
    private String amount;
    private String merchantKey;
    private String email;

    // --- CONSTRUCTOR ---
    public PaymentData(String amount, String merchantKey, String email) {
        this.amount = amount;
        this.merchantKey = merchantKey;
        this.email = email;
    }

    // --- GETTERS Y SETTERS ---
    // Permiten acceder y modificar los campos de forma controlada.

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMerchantKey() {
        return merchantKey;
    }

    public void setMerchantKey(String merchantKey) {
        this.merchantKey = merchantKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
