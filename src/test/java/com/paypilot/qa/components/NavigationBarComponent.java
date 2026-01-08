package com.paypilot.qa.components;

import io.appium.java_client.AppiumDriver;

/**
 * EJEMPLO DE ARQUITECTURA AVANZADA: Clase que representa un Componente de UI reutilizable.
 *
 * ¿QUÉ ES?: Un componente es una parte de la UI que aparece en MÚLTIPLES pantallas, como una barra
 * de navegación inferior, un menú lateral o un pop-up de diálogo.
 *
 * ¿POR QUÉ USARLO?: Para no repetir la lógica de los localizadores y las acciones de estos elementos
 * en cada una de las clases de Página (Page Class). Sigue el principio DRY (Don't Repeat Yourself).
 *
 * EJEMPLO DE USO:
 * Si la apps tuviera una barra de navegación inferior con botones de 'Inicio', 'Buscar' y 'Perfil',
 * esta clase contendría los localizadores y métodos para esos botones.
 * Luego, las clases de página como HomePage, SearchPage y ProfilePage tendrían una instancia
 * de NavigationBarComponent para poder interactuar con ella.
 *
 *     public class HomePage {
 *         public NavigationBarComponent navigationBar;
 *
 *         public HomePage(AppiumDriver driver) {
 *             this.navigationBar = new NavigationBarComponent(driver);
 *         }
 *     }
 *
 */
public class NavigationBarComponent {

    protected AppiumDriver driver;

    /**
     * El constructor recibe el driver para poder interactuar con los elementos del componente.
     * @param driver La instancia del driver de Appium.
     */
    public NavigationBarComponent(AppiumDriver driver) {
        this.driver = driver;
    }

    // --- EJEMPLO DE LOCALIZADORES DEL COMPONENTE ---
    // private final AppiumBy profileButton = AppiumBy.id("com.example.apps:id/nav_profile");

    // --- EJEMPLO DE ACCIONES DEL COMPONENTE ---
    /**
     * Haría clic en el botón de perfil de la barra de navegación.
     */
    // public void clickProfileButton() {
    //     driver.findElement(profileButton).click();
    // }
}
