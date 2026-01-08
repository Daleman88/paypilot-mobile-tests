package com.paypilot.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase de utilidad para leer las propiedades desde el archivo config.properties.
 * Sigue un patrón Singleton para asegurar que el archivo se lee una sola vez.
 */
public class ConfigReader {

    // Objeto que almacenará todas las propiedades cargadas del archivo.
    private static final Properties properties = new Properties();

    // Bloque de inicialización estático. Este código se ejecuta UNA SOLA VEZ, la primera vez
    // que esta clase es utilizada. Es perfecto para cargar la configuración.
    static {
        try {
            // Ruta al archivo de configuración. src/test/resources es una carpeta estándar
            // que Maven añade al "classpath", por lo que podemos acceder a ella directamente.
            String configFilePath = "src/test/resources/config.properties";
            // Creamos un flujo de entrada para leer el archivo.
            FileInputStream fis = new FileInputStream(configFilePath);
            // El objeto 'properties' carga todas las claves y valores del archivo.
            properties.load(fis);
        } catch (IOException e) {
            // Si el archivo no se encuentra o no se puede leer, lanzamos una excepción
            // para detener la ejecución, ya que el framework no puede funcionar sin su configuración.
            throw new RuntimeException("No se pudo cargar el archivo de configuración config.properties", e);
        }
    }

    /**
     * Obtiene el valor de una propiedad específica del archivo de configuración.
     * @param key El nombre de la propiedad (ej. "appium.server.url").
     * @return El valor de la propiedad como un String.
     */
    public static String getProperty(String key) {
        // Busca y devuelve el valor asociado a la clave. Si no lo encuentra, devuelve null.
        return properties.getProperty(key);
    }
}
