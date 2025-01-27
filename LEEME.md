# [CS50]: Proyecto Final

> Una aplicación más de lista de tareas... pero divirtiéndome con [htmx].


## Descripción

Este proyecto consiste en una aplicación de gestión de tareas simple que permite a los usuarios registrarse, crear, visualizar, editar, marcar como completadas y eliminar tareas. Adicionalmente, usuarios con perfil de administrador pueden gestionar otros usuarios.

El objetivo principal no es simplemente resolver el problema de la gestión de tareas, pues ya existen excelentes aplicaciones para eso. La idea es hacerlo utilizando buenas prácticas de desarrollo de software, con el apoyo de tecnologías modernas que permiten una experiencia de usuario fluida, sin la necesidad de complejas configuraciones de frontend o backend, y que pueda servir como referencia para otros desarrolladores que quieran integrar dichas tecnologías en un proyecto más complejo.

#### Video Demo: https://youtu.be/jI2FjRabNhU


## Tecnologías y Diseño

### Interfaz de Usuario

La elección de tecnologías para la interfaz de usuario se basó principalmente en el deseo de construir una aplicación simple, pero moderna y atractiva; evitando escribir todo el código [JavaScript] desde cero, o el uso de frameworks más complejos para mantener el _estado global_ de la aplicación.

[htmx] permite agregar interactividad dinámica a una página web utilizando un estilo declarativo con [HTML] estándar y peticiones [AJAX]. Esto reduce la complejidad del proyecto y permite mantener un enfoque más limpio y accesible. Cada tarea puede ser creada, editada, completada o eliminada sin necesidad de recargar la página; brindando una experiencia similar a una aplicación [SPA].

[Alpine.js], por su parte, permite agregar reactividad a la interfaz en un formato minimalista y con una sintaxis similar a la de [HTML], ideal para tareas como ocultar/mostrar elementos, manejar formularios o interacciones básicas, sin sobrecargar el proyecto con una arquitectura pesada y contribuyendo a una experiencia de usuario ligera y fluida.

Juntos, [htmx] y [Alpine.js] permiten construir aplicaciones rápidas, con menos código y sin la necesidad de un proceso de construcción o empaquetado complejo, lo que mejora tanto la velocidad de desarrollo como la de ejecución, especialmente en proyectos pequeños o medianos.

Adicionalmente, se utilizó [no-class.css] para proveer los estilos esenciales de la aplicación, garantizando un _look and feel_ moderno y adaptable. Este framework sencillo, ligero y eficiente, permite comenzar rápidamente y personalizar el diseño según sea necesario, sin sobrecargar el proyecto con clases o reglas [CSS] innecesarias. Para los íconos y otros elementos visuales, [Font Awesome][awesome], [svg-loaders] y [favicon.io] ofrecen una experiencia de desarrollo similar.

### Renderizado del lado del Servidor

Para mantener la mayor simplicidad posible en el frontend, y de acuerdo con el paradigma [Hypermedia-Driven Applications][hda] que propone [htmx], se utilizó un motor de plantillas para renderizar el contenido del lado del servidor, aprovechando al máximo las capacidades del backend. En ese sentido, [Thymeleaf] facilita la generación de contenido dinámico en el servidor, facilitando la integración con el backend y promoviendo la separación de la lógica de presentación.

Por otro lado, [WebJars] simplifica la gestión de dependencias del frontend (como [CSS] y [JavaScript]) al integrarlas directamente en el proyecto como una dependencia más del backend. De esta forma, las bibliotecas se proveen automáticamente desde el servidor, lo que facilita la implementación y el mantenimiento de la aplicación.

### Detrás de Cámaras

Se eligió [Spring Boot][spring-boot] debido a su simplicidad y robustez, y especialmente gracias a su sistema de starters. Estos proporcionan una gran facilidad de integración, configuración automática y simplificada de tecnologías clave para el desarrollo, permitiendo centrarse en la lógica de la aplicación en lugar de en la configuración de tareas comunes y repetitivas.

Para el desarrollo se utilizaron los siguientes starters:

- [spring-boot-starter-web]: Provee de un servidor web embebido y facilita la creación de servicios web con poco esfuerzo de configuración, tanto RESTful como (en este proyecto) [MVC].

- [spring-boot-starter-security]: Proporciona una configuración de seguridad sencilla pero robusta, incluyendo autenticación, autorización y protección contra los ataques más comunes.

- [spring-boot-starter-validation]: Permite la validación automática de los datos de entrada en formularios o solicitudes HTTP, simplificando el manejo de errores y mejorando la calidad de los datos.

- [spring-boot-starter-thymeleaf]: Proporciona una integración natural con [Thymeleaf], permitiendo fácilmente renderizar las vistas del lado del servidor.

- [spring-boot-starter-data-jpa]: Proporciona una capa de abstracción sobre la base de datos, permitiendo realizar operaciones [CRUD] sin necesidad de escribir código [SQL] o manejar los conexiones de forma manual.

- [spring-boot-starter-test]: Incluye herramientas para realizar pruebas unitarias y de integración de forma automática, asegurando la calidad del código y facilitando la evolución de la aplicación.

El uso de estos starters permitió una integración fluida y la configuración (en la mayoría de los casos) automática de ciertas tecnologías esenciales para la aplicación: seguridad, validación, persistencia de datos y pruebas.

Por otro lado, el proyecto [htmx-spring-boot] simplifica la integración de [htmx] con aplicaciones [Spring MVC][mvc] y [Thymeleaf]. Proporciona un conjunto de vistas, anotaciones y proveedores de argumentos para que los controladores puedan gestionar fácilmente los encabezados de las peticiones y respuestas [htmx]. Esto garantiza una interacción fluida entre el frontend y el backend, impulsada por [htmx], tal como actualizaciones parciales basadas en [AJAX].

Finalmente, el uso de herramientas como [Lombok] y [MapStruct], basadas en un enfoque de _convention over configuration_ para la generación automática de código en tiempo de compilación, permitió reducir el código repetitivo y más propenso a errores, mejorando su legibilidad. Junto con [Maven] para gestionar las dependencias y el proceso de compilación, fue posible incrementar la productividad, brindando una experiencia de desarrollo más simplificada y eficiente.

### Motor de Base de Datos

Se utilizó [H2] como base de datos debido a su ligereza, facilidad de configuración y rapidez. Esta es una base de datos en memoria que permite ejecutar aplicaciones sin la necesidad de instalar y configurar un servidor de base de datos completo, lo que la convierte en una herramienta ideal para prototipado y demostraciones rápidas (como este proyecto). Además, su integración con frameworks como [Spring Boot][spring-boot] es sencilla y ofrece una interfaz web para gestionar la base de datos de forma visual, agilizando considerablemente el desarrollo.


## Estructura del Proyecto

El proyecto sigue la estructura de directorios estándar de un proyecto típico de [Maven], con los paquetes organizados de acuerdo al dominio; permitiendo reducir la visibilidad de la mayoría de las clases al paquete que las contiene.

```
├── README.md
├── pom.xml
└── src
    ├── main
    │   ├── java
    │   │   └── io.github.lcnicolau.cs50.todolist
    │   │       ├── Application.java
    │   │       ├── ErrorController.java
    │   │       ├── MainController.java
    │   │       ├── config/**
    │   │       ├── tasks/**
    │   │       └── users/**
    │   └── resources
    │       ├── application.yml
    │       ├── application-dev.yml
    │       ├── static
    │       │   ├── favicon.ico
    │       │   ├── css/**
    │       │   └── images/**
    │       └── templates
    │           ├── error.html
    │           ├── layout.html
    │           ├── nav.html
    │           ├── pages/**
    │           ├── tasks/**
    │           └── users/**
    └── test
        └── java
            └── io.github.lcnicolau.cs50.todolist
                ├── ApplicationTest.java
                ├── HtmxUtils.java
                └── MainControllerTest.java
```

A continuación se describen las principales clases y paquetes del proyecto:

#### `src/main/java/io/github/lcnicolau/cs50/todolist/Application.java`

Representa el punto de entrada de la aplicación, contiene el método `main` de `java` encargado de inicializar [Spring Boot][spring-boot].

#### `src/main/java/io/github/lcnicolau/cs50/todolist/ErrorController.java`

[Spring][spring-boot] `@Controller` que maneja el renderizado de errores en la vista, hereda de la clase `BasicErrorController` y personaliza los errores asociados a peticiones específicas de [htmx]. Además, se encuentra anotado como `@ControllerAdvice` lo cual le permite mapear adecuadamente ciertas excepciones que se produzcan en la aplicación para ser mostrados al usuario.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/MainController.java`

[Spring][spring-boot] `@Controller` que maneja el renderizado de las diferentes páginas de la aplicación. El proyecto consta de 5 pagínas principales (`/home`, `/tasks`, `/users`, `/signup`, `/login`) a las que se puede acceder directamente escribiendo su URL en el navegador, o a través del menú de navegación provisto. Este controlador comprueba si se trata de una solicitud [htmx] ([AJAX]) o no, para devolver solo un fragmento con el contenido relevante de la página, o la página completa en caso de que el usuario haya accedido a través de la URL en el navegador.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/config/**`

Este paquete contiene diferentes clases `@Component` y `@Configuration`, las cuales proveen funcionalidades adicionales, o permiten personalizar ciertos aspectos del framework, como la [seguridad][spring-boot-starter-security], [validación][spring-boot-starter-validation], [acceso a datos][spring-boot-starter-data-jpa] o el [renderizado de las vistas][spring-boot-starter-thymeleaf].

Por ejemplo, la clase `DataConfig.java`, anotada como `@EnableJpaAuditing`, define un bean de tipo `AuditorAware` a partir del usuario autenticado. Esta configuración mínima posibilita el uso de `@CreatedDate` y `@CreatedBy` en las clases de tipo `@Entity` para establecer la fecha y el autor de cada tarea de manera automática.

La anotación `@Password` utiliza una expresión regular para validar la fortaleza de las contraseñas, quedando disponible para su uso en la `@Entity User`, similar a la anotación `@Email` provista por [Spring Validation][spring-boot-starter-validation].

Adicionalmente, la clase `SecurityAwareErrorAttributes` extiende la funcionalidad de `DefaultErrorAttributes` para buscar errores de seguridad comunes asociados al request actual o almacenados en sesión, útil por ejemplo, luego de una redirección a la página de `/login`.

Las configuraciones de seguridad se discutirán en detalle en la siguiente sección.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/tasks/**`

Contiene las clases necesarias para el manejo por capas de las solicitudes HTTP relacionadas con las tareas. La capa de presentación (`@Controller`) define las rutas y métodos para la creación, visualización, edición y eliminación de tareas. La capa de aplicación (`@Service`, `@Mapper`) se ocupa de validaciones de negocio, manejo de transacciones y mapeo de los datos entre las capas de presentación y dominio. La capa de dominio (`@Repository`, `@Entity`) maneja las interacciones con la base de datos, utilizando [Spring Data JPA][spring-boot-starter-data-jpa] para la persistencia de las tareas de forma sencilla y eficiente.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/users/**`

Similar al paquete anterior, contiene las clases necesarias para el manejo por capas de las solicitudes HTTP relacionadas con los usuarios. Adicionalmente, implementa las interfaces `UserDetails`, `CredentialsContainer` y `UserDetailsService` para el manejo de usuarios en base de datos con [Spring Security][spring-boot-starter-security].

#### `src/main/resources/application.yml`

Contiene las configuraciones generales de la aplicación, incluyendo los parámetros de conexión a base de datos, logging, etc.

#### `src/main/resources/application-dev.yml`

Similar al archivo anterior, contiene configuraciones específicas para el perfil de desarrollo. Si el perfil `dev` está activo, estas configuraciones predominan sobre las configuraciones generales.

#### `src/main/resources/static/**`

Contiene los archivos estáticos de la aplicación: favicon, imágenes y estilos personalizados (no existen archivos JavaScript).

#### `src/main/resources/templates/**`

Contiene el layout general y cada una de las páginas de la aplicación. Se utiliza [Thymeleaf] para renderizar dinámicamente las páginas, al tiempo que se ofrece una interfaz de usuario limpia e interactiva gracias a [htmx] y [Alpine.js].


## Consideraciones de Seguridad

La clase `src/main/java/io/github/lcnicolau/cs50/todolist/config/SecurityConfig.java` define un conjunto de beans que colaboran entre sí para proveer un marco de seguridad a la aplicación, con la ayuda de [Spring Security][spring-boot-starter-security].

El bean `SecurityFilterChain` define las configuraciones más importantes; por ejemplo, qué hacer en caso de un intento de login fallido o satisfactorio, y en caso de logout. Qué hacer si un usuario anónimo intenta acceder a un recurso protegido, o si un usuario autenticado intenta acceder a un recurso restringido.

Además, se define qué recursos estarán disponibles públicamente (por ejemplo, los recursos estáticos y las páginas de `/home`, `/signup`, `/login` y `/error`), cuáles requieren autenticación y cuáles estarán restringidos a un rol en específico (por ejemplo, la propia gestión de usuarios y roles).

El bean `WebSecurityCustomizer` solo se activa para el perfil de desarrollo `@Profile("dev")` y se encarga de deshabilitar la seguridad para la consola web de [H2], la cual ya dispone de su propia seguridad y solo está disponible en desarrollo.

El bean `PasswordEncoder` define el algoritmo para codificar las contraseñas, y colabora con las implementaciones de `UserDetails` y `UserDetailsService` (disponibles en el paquete `src/main/java/io/github/lcnicolau/cs50/todolist/users/**`) para registrar y autenticar a los usuarios en base de datos.

Finalmente, [Spring Security][spring-boot-starter-security] nos provee de una protección integrada contra algunos de los ataques más comunes, como [XSS] y [CSRF], y una gestión adecuada de las políticas de [CORS], sin necesidad de hacer absolutamente nada.

### Patrón de Redirección

El comportamiento por defecto de [Spring Security][spring-boot-starter-security] ante un acceso no autorizado, es enviar un código de redirección [302] al cliente, apuntando a la página de `/login`; o en caso de autenticación satisfactoria, a la página solicitada originalmente.

Aunque este enfoque puede ser el comportamiento deseado en la mayoría de los casos, cuando se usa en conjunto con [htmx] para cargar fragmentos de la página utilizando peticiones [AJAX], puede dejar el sistema en un estado inconsistente y algunas veces hasta gracioso (como una página de login dentro de un botón :upside_down_face:). El problema es que el navegador intercepta la redirección internamente y devuelve las cabeceras y respuesta de la URL redirigida, luego de lo cual [htmx] carga la respuesta justo donde debía ir el fragmento solicitado originalmente.

Para resolver este problema, [htmx] provee de una forma especial de enviar una instrucción de redirección al cliente, manteniendo un código satisfactorio [200] y enviando una cabecera HTTP personalizada ([HX-Location], [HX-Redirect]) desde el servidor, lo cual [htmx] interpreta correctamente y sigue la redirección, reemplazando su contenido en el body de la página.

Por esta razón, fue necesario crear implementaciones personalizadas de `AuthenticationFailureHandler`, `AuthenticationSuccessHandler`, `LogoutSuccessHandler`, `AuthenticationEntryPoint` y `AccessDeniedHandler`; las cuales se pueden encontrar en el paquete `src/main/java/io/github/lcnicolau/cs50/todolist/config/security/**` y se activan en el bean `SecurityFilterChain`.


## Pruebas Unitarias

Aunque no exhaustivo, el proyecto cuenta con un conjunto de pruebas unitarias automatizadas sobre los principales puntos de acceso a la aplicación, comprobando que la respuesta sea consistente tanto si se acceda desde la url almacenada en el navegador o a través de una petición [AJAX] con [htmx].

También existen pruebas de seguridad tanto a recursos protegidos como restringidos; y en el caso de las peticiones [htmx], se verifica que la respuesta contenga el _Patrón de Redirección_ explicado en la sección anterior. Estas pruebas se encuentran en la clase `src/test/java/io/github/lcnicolau/cs50/todolist/MainControllerTestjava`.


## Siguientes Pasos

### Mejoras

- Eliminar dependencia `Task -> User` y limitar la visibilidad de la clase `User`.
- Crear interfaces de servicio, exponer [DTO] y hacer la implementación privada.
- Mostrar indicador de _loading_ en la primera carga de `tasks` y `users` (`hx-trigger="load"`).
- Redireccionar a la página solicitada luego del login (`successHandler`).
- Mostrar detalle de errores solo para el perfil `dev` (`include-message`).
- Editar `tasks` completamente en el frontend con [Alpine.js] (`input`).
- Manejar envío de value `false` en checkbox con [Alpine.js] (`hx:vals`).
- Cambiar `ViewUtils` a algún objeto nativo de [Thymeleaf] (`getElapsedTimeSince`).

### Nuevas Funcionalidades

- Mostrar contador de tareas pendientes y totales.
- Utilizar [Flyway] para la creación e inicialización de base de datos.
- Agregar logs a las principales funcionalidades.
- Aumentar cobertura de pruebas unitarias.
- Agregar autenticación vía [Github].
- Evaluar [Java Template Engine][jte].





[CS50]: https://cs50.harvard.edu/x/ "CS50’s Introduction to Computer Science"

[htmx]: https://htmx.org/ "High power tools for HTML"

[JavaScript]: https://developer.mozilla.org/en-US/docs/Web/JavaScript "A programming language and core technology of the web"

[HTML]: https://developer.mozilla.org/en-US/docs/Web/HTML "HyperText Markup Language"

[AJAX]: https://developer.mozilla.org/en-US/docs/Glossary/AJAX "Asynchronous JavaScript and XML"

[Alpine.js]: https://alpinejs.dev/ "Simple. Lightweight. Powerful as hell"

[SPA]: https://developer.mozilla.org/en-US/docs/Glossary/SPA "Single-page application"

[no-class.css]: https://davidpaulsson.github.io/no-class/ "A CSS framework without class"

[CSS]: https://developer.mozilla.org/en-US/docs/Web/CSS "Cascading Style Sheets"

[awesome]: https://fontawesome.com/ "The iconic SVG, font, and CSS toolkit"

[svg-loaders]: https://samherbert.net/svg-loaders/ "Loading icons and small animations built with pure SVG"

[favicon.io]: https://favicon.io/ "Quickly generate your favicon from text, image, or choose from hundreds of emojis"

[hda]: https://htmx.org/essays/hypermedia-driven-applications/ "Hypermedia-Driven Applications"

[Thymeleaf]: https://www.thymeleaf.org/ "A modern server-side Java template engine"

[WebJars]: https://www.webjars.org/ "Managed client-side dependencies in JVM-based web apps"

[spring-boot]: https://spring.io/projects/spring-boot "Spring makes Java simple, modern, productive, reactive, cloud-ready"

[spring-boot-starter-web]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web "Starter for building web, including RESTful, applications using Spring MVC. Uses Tomcat as the default embedded container"

[mvc]: https://docs.spring.io/spring-framework/reference/web/webmvc.html "Makes it easy to build web applications using the Model-View-Controller (MVC) design pattern"

[spring-boot-starter-security]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security "Starter for using Spring Security"

[spring-boot-starter-validation]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation "Starter for using Java Bean Validation with Hibernate Validator"

[spring-boot-starter-thymeleaf]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf "Starter for building MVC web applications using Thymeleaf views"

[spring-boot-starter-data-jpa]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa "Starter for using Spring Data JPA with Hibernate"

[CRUD]: https://developer.mozilla.org/en-US/docs/Glossary/CRUD "Create, Read, Update, Delete"

[SQL]: https://developer.mozilla.org/en-US/docs/Glossary/SQL "Structured Query Language"

[spring-boot-starter-test]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test "Starter for testing Spring Boot applications with libraries including JUnit Jupiter, Hamcrest and Mockito"

[htmx-spring-boot]: https://github.com/wimdeblauwe/htmx-spring-boot "Spring Boot and Thymeleaf helpers for working with htmx"

[Lombok]: https://projectlombok.org/ "Spicing up your java"

[MapStruct]: https://mapstruct.org/ "Java bean mappings, the easy way!"

[maven]: https://maven.apache.org/ "A build automation and project management tool for Java applications"

[h2]: https://h2database.com/ "The Java SQL database"

[XSS]: https://owasp.org/www-community/attacks/xss/ "Cross-site scripting"

[CSRF]: https://owasp.org/www-community/attacks/csrf "Cross Site Request Forgery"

[CORS]: https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS "Cross-Origin Resource Sharing"

[302]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/302 "Found"

[200]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/200 "OK"

[HX-Location]: https://htmx.org/headers/hx-location/ "HX-Location Response Header"

[HX-Redirect]: https://htmx.org/headers/hx-redirect/ "HX-Redirect Response Header"

[DTO]: https://stackoverflow.com/questions/1051182/what-is-a-data-transfer-object-dto "Data Transfer Object"

[Flyway]: https://github.com/flyway/flyway "Database Migrations Made Easy"

[GitHub]: https://docs.github.com/en/apps/oauth-apps/building-oauth-apps "Building OAuth apps"

[jte]: https://jte.gg/ "Java Template Engine"