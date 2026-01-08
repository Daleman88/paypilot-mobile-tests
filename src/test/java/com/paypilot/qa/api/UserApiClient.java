package com.paypilot.qa.api;

/**
 * EJEMPLO DE ARQUITECTURA AVANZADA: Clase que representa un Cliente de API.
 *
 * ¿QUÉ ES?: Un cliente de API es una clase que se comunica directamente con el backend
 * de la aplicación (su API REST), saltándose por completo la interfaz de usuario.
 * Para esto se usan librerías como RestAssured o Retrofit.
 *
 * ¿POR QUÉ USARLO?: Para acelerar drásticamente la ejecución de las pruebas. Las pruebas de UI son lentas.
 * En lugar de usar la UI para crear un usuario, iniciar sesión y navegar a una pantalla específica
 * (lo que podría tardar minutos), podemos hacerlo en milisegundos con una llamada a la API.
 * Se usa principalmente para gestionar PRECONDICIONES Y POST-CONDICIONES de las pruebas.
 *
 * EJEMPLO DE FLUJO:
 * 1. @BeforeMethod: Se ejecuta un método que llama a `UserApiClient.createUser()` para crear un
 *    usuario de prueba directamente en la base de datos a través de la API.
 * 2. @Test: La prueba de UI comienza directamente en la pantalla de login, introduce los datos del
 *    usuario recién creado y verifica la funcionalidad deseada (ej. "subir foto de perfil").
 * 3. @AfterMethod: Se puede ejecutar un método que llame a `UserApiClient.deleteUser()` para limpiar
 *    la base de datos y dejarla en un estado conocido para la siguiente prueba.
 *
 */
public class UserApiClient {

    // En un proyecto real, aquí se inicializaría el cliente HTTP (ej. RestAssured).
    // private static final RequestSpecification requestSpec = new RequestSpecBuilder()
    //         .setBaseUri("https://api.example.com/v1")
    //         .setContentType(ContentType.JSON)
    //         .build();

    /**
     * Crearía un usuario de prueba haciendo una petición POST a la API.
     * @param username El nombre de usuario deseado.
     * @param password La contraseña deseada.
     * @return El ID del usuario creado.
     */
    // public String createUser(String username, String password) {
    //     // Lógica para enviar la petición a la API...
    //     // given().spec(requestSpec).body(...).when().post("/users").then()...
    //     return "user-id-from-api";
    // }

    /**
     * Borraría un usuario de prueba para limpiar el entorno.
     * @param userId El ID del usuario a borrar.
     */
    // public void deleteUser(String userId) {
    //     // Lógica para enviar la petición DELETE a la API...
    //     // given().spec(requestSpec).when().delete("/users/" + userId).then()...
    // }
}
