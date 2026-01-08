package com.paypilot.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * Clase de utilidad para inicializar y configurar el reporte de ExtentReports.
 * Sigue un patrón Singleton para asegurar una única instancia del reporte.
 */
public class ExtentManager {

    private static ExtentReports extent;

    /**
     * Crea y configura una instancia de ExtentReports si no existe.
     * @return La instancia única de ExtentReports.
     */
    public static ExtentReports createInstance() {
        if (extent == null) {
            // Define la ruta y el nombre del archivo del reporte.
            String reportPath = "reports/ExtentReport.html";

            // Asegura que el directorio de reportes exista.
            File reportDir = new File("reports");
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }

            // Inicializa el reportero de tipo Spark (el que genera el HTML).
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            // Configura la apariencia del reporte.
            sparkReporter.config().setDocumentTitle("Reporte de Pruebas de Automatización");
            sparkReporter.config().setReportName("Resultados de Pruebas de PayPilot Mobile");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setEncoding("UTF-8");

            // Inicializa el objeto principal de ExtentReports y le adjunta el reportero Spark.
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Opcional: Añade información del sistema al reporte.
            extent.setSystemInfo("Proyecto", "PayPilot Mobile Tests");
            extent.setSystemInfo("Framework", "TestNG + Appium");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }
}
