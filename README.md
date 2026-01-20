# Ejercicio 1: Spotify Desktop

## Reto de Pensamiento: Gestión de la sesión en escritorio

### ¿Cómo gestionamos la sesión en escritorio?
En el desarrollo Desktop, la gestión de sesión es más sencilla que en una API REST. Mientras que en REST el servidor no guarda estado y necesita un token en cada petición, en Desktop aprovechamos la memoria RAM local. El usuario se identifica una vez y sus datos permanecen cargados en el programa mientras este no se cierre.

### Tarea: Definición de la ubicación del currentUser
Para esta aplicación JavaFX, he definido que el usuario autenticado se guardará en la siguiente ubicación:

* **Clase**: `com.marcog.spotify_de_escritorio.util.SessionManager`
* **Variable**: `public static Usuario usuarioActual;`

Esta variable estática actúa como un contenedor global. Una vez que el servicio de Spotify valida el login, asigna el objeto recuperado a esta variable para que esté disponible en cualquier pantalla de la aplicación.