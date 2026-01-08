package com.paypilot.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.paypilot.qa.basetest.BaseTest;
import com.paypilot.qa.reports.ExtentManager;
import com.paypilot.qa.utils.ScreenshotUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Clase "Oyente" que reacciona a los eventos de la ejecución de pruebas de TestNG.
 */
public class TestListener implements ITestListener {

    private ExtentReports extent;
    // ThreadLocal para manejar la concurrencia si se ejecutan pruebas en paralelo.
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    // --- MEJORA: Método para exponer el test actual ---
    /**
     * Permite que otras clases (como nuestra utilidad de Log) accedan al objeto
     * del test actual que se está ejecutando, para poder escribir en el reporte.
     * @return El objeto ExtentTest del test en curso.
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.createInstance();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getDescription();
        ExtentTest test = extent.createTest(testName);
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getDescription();
        getTest().log(Status.PASS, MarkupHelper.createLabel("Prueba Exitosa", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getDescription();

        getTest().log(Status.FAIL, MarkupHelper.createLabel("Prueba Fallida", ExtentColor.RED));
        getTest().fail(result.getThrowable());

        Object testClass = result.getInstance();
        AppiumDriver driver = ((BaseTest) testClass).driver;

        if (driver != null) {
            String screenshotPath = ScreenshotUtils.takeScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                getTest().addScreenCaptureFromPath(screenshotPath, "Captura de pantalla del error");
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getDescription();
        getTest().log(Status.SKIP, MarkupHelper.createLabel("Prueba Omitida", ExtentColor.ORANGE));
    }
}
