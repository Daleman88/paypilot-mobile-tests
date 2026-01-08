package com.paypilot.qa.utils;

import com.aventstack.extentreports.Status;
import com.paypilot.qa.listeners.TestListener;

/**
 * Clase de utilidad para centralizar el logging de la ejecución de las pruebas.
 */
public class Log {

    /**
     * Registra un mensaje como un "paso" de la prueba.
     */
    public static void step(String message) {
        System.out.println("--- PASO: " + message);
        if (TestListener.getTest() != null) {
            TestListener.getTest().log(Status.INFO, message);
        }
    }

    /**
     * Registra un mensaje de advertencia.
     */
    public static void warn(String message) {
        System.out.println("--- WARN: " + message);
        if (TestListener.getTest() != null) {
            TestListener.getTest().log(Status.WARNING, message);
        }
    }

    /**
     * Registra un mensaje de error que no necesariamente detiene la prueba.
     * En el reporte, este paso aparecerá como "fallido" (rojo).
     */
    public static void error(String message) {
        System.err.println("--- ERROR: " + message);
        if (TestListener.getTest() != null) {
            // CORRECCIÓN: El estatus correcto para un error en ExtentReports es FAIL.
            TestListener.getTest().log(Status.FAIL, message);
        }
    }
}
