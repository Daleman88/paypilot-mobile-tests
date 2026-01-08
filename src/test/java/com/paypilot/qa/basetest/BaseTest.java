package com.paypilot.qa.basetest;

import com.paypilot.qa.utils.ConfigReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    public AppiumDriver driver;

    @BeforeClass
    public void setup() {
        try {
            System.out.println("--- @BeforeClass: Iniciando configuración de Appium ---");

            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName(ConfigReader.getProperty("platform.name"))
                    .setUdid(ConfigReader.getProperty("device.udid"))
                    .setAutomationName(ConfigReader.getProperty("automation.name"));

            String appPath = ConfigReader.getProperty("app.path");

            if (appPath != null && !appPath.isEmpty()) {
                System.out.println("Usando la capability 'app' para instalar/reinstalar el APK desde: " + appPath);
                String absoluteAppPath = System.getProperty("user.dir") + File.separator + appPath;
                options.setApp(absoluteAppPath);
            } else {
                System.out.println("Usando las capabilities 'appPackage' y 'appActivity' para lanzar la app ya instalada.");
                options.setAppPackage(ConfigReader.getProperty("app.package"));
                // CORRECCIÓN: Se usa ConfigReader en lugar del inexistente Config.
                options.setAppActivity(ConfigReader.getProperty("app.activity"));
            }

            URL appiumServerUrl = new URL(ConfigReader.getProperty("appium.server.url"));

            System.out.println("Creando la sesión del driver de Appium...");
            driver = new AndroidDriver(appiumServerUrl, options);
            System.out.println("¡Driver de Appium creado exitosamente!");

        } catch (MalformedURLException e) {
            System.err.println("La URL del servidor de Appium es inválida.");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.err.println("¡¡¡ ERROR FATAL durante la configuración en @BeforeClass !!!");
            e.printStackTrace();
            throw new RuntimeException("Fallo al configurar el driver de Appium", e);
        }
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void resetAppToMainMenu() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            // Ignoramos el error, puede que ya estemos en el menú principal.
        }
    }
}
