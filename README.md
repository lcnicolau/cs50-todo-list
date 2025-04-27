# This is [CS50] Final Project

> Just another **to-do list** app... but having fun with [htmx].

Video Demo: [Watch on YouTube][demo].

Leer en [Español].


## Description

This project consists of a simple task management application that allows users to sign up, create, view, edit, mark as completed and delete tasks. In addition, users with an administrator profile can manage other users.

The main goal is not simply to solve the problem of task management, as there are already excellent applications for that. The idea is to do it in accordance with software development best practices, with the support of modern technologies that provide a smooth user experience, without the need for complex frontend or backend configurations, and that can serve as a reference for other developers who want to integrate such technologies into a more complex project.


## Technologies and Design

### User Interface

The choice of technologies for the user interface was mainly based on the desire to create a simple, yet modern and attractive application; avoiding writing all the [JavaScript] code from scratch or using more complex frameworks to maintain the application _global state_.

[HTMX] allows you to add dynamic interactivity to a web page using a declarative style with standard [HTML] and [AJAX] requests. This reduces project complexity and allows you to maintain a cleaner, more accessible approach. Each task can be created, edited, completed or deleted without reloading the page, providing an [SPA]-like experience.

[Alpine.js], on the other hand, allows you to add reactivity to the interface in a minimalist format and with a syntax similar to [HTML], ideal for tasks such as hiding/showing elements, handling forms or basic interactions, without overloading the project with a heavy architecture and contributing to a light and fluid user experience.

Together, [htmx] and [Alpine.js] make it possible to build applications quickly, with less code and without the need for a complex build or packaging process, improving both development and execution speed, especially for small to medium-sized projects.

In addition, [no-class.css] was used to provide the essential styles of the application, ensuring a modern and responsive _look and feel_. This simple, lightweight and efficient framework allows you to get up and running quickly and customize the design as needed, without overloading the project with unnecessary [CSS] classes or rules. For icons and other visual elements, [Font Awesome][awesome], [svg-loaders] and [favicon.io] provide a similar development experience.

### Server-Side Rendering

To keep the frontend as simple as possible, and in accordance with the [Hypermedia-Driven Applications][hda] paradigm proposed by [htmx], a template engine was used to render the content on the server side, taking full advantage of the backend's capabilities. In this sense, [Thymeleaf] facilitates the generation of dynamic content on the server, simplifying the integration with the backend and promoting the separation of the presentation logic.

On the other hand, [WebJars] simplifies the management of frontend dependencies (such as [CSS] and [JavaScript]) by integrating them directly into the project as another backend dependency. This way, libraries are automatically provisioned from the server, making it easier to deploy and maintain the application.

### Behind the Scenes

[Spring Boot][spring-boot] was chosen due to its simplicity and robustness, and especially because of its starters. These provide easy integration, automatic and simplified configuration of key technologies for development, allowing you to focus on the application logic rather than on configuring common and repetitive tasks.

The following starters were used for the development:

- [spring-boot-starter-web]: Provides an embedded web server and facilitates the creation of web services with little configuration effort, both [RESTful] and (in this project) [MVC].

- [spring-boot-starter-security]: Provides a simple but robust security configuration, including authentication, authorization, and protection against the most common attacks.

- [spring-boot-starter-validation]: Enables automatic validation of input data in forms or HTTP requests, simplifying error handling and improving data quality.

- [spring-boot-starter-thymeleaf]: Provides natural integration with [Thymeleaf], allowing easy server-side rendering of views.

- [spring-boot-starter-data-jpa]: Provides an abstraction layer over the database, allowing [CRUD] operations to be performed without writing [SQL] code or manually managing connections.

- [spring-boot-starter-test]: Includes tools for automated unit and integration testing, ensuring code quality and facilitating the application evolution.

Using these starters allowed for smooth integration and (in most cases) automatic configuration of certain essential technologies for the application: security, validation, data persistence and testing.

On the other hand, the [htmx-spring-boot] project simplifies the integration of [htmx] with [Spring MVC][mvc] and [Thymeleaf] applications. It provides a set of views, annotations, and argument resolvers for controllers to easily handle [htmx]-related request and response headers. This ensures seamless [htmx]-driven interaction between the frontend and backend, such as [AJAX]-based partial page updates.

Finally, the use of tools such as [Lombok] and [MapStruct], based on a _convention-over-configuration_ approach to automatic code generation at compile time, reduced repetitive and error-prone code, improving its readability. Together with [Maven] to manage dependencies and the compilation process, it was possible to increase productivity, making development easier and more efficient.

### Database Engine

[H2] was used as the database due to its light weight, ease of configuration and speed. It is an in-memory database that allows applications to run without the need to install and configure a full database server, making it an ideal tool for rapid prototyping and demonstrations (such as this project). It is also easy to integrate with frameworks such as [Spring Boot][spring-boot] and provides a web interface for visual management of the database, significantly speeding up development.


## Project Structure

The project follows the standard directory layout of a typical [Maven] project, with packages organized by domain; this restricts the visibility of most classes to their own package.

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

The most important classes and packages in the project are described below:

#### `src/main/java/io/github/lcnicolau/cs50/todolist/Application.java`

Represents the application entry point, contains the `main` method of `java` responsible for initializing [Spring Boot][spring-boot].

#### `src/main/java/io/github/lcnicolau/cs50/todolist/ErrorController.java`

[Spring][spring-boot] `@Controller` that handles error rendering in the view, extends the `BasicErrorController` class and customizes errors associated with specific [htmx] requests. In addition, it is annotated as `@ControllerAdvice`, which allows it to properly map certain exceptions that occur in the application to be displayed to the user.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/MainController.java`

[Spring][spring-boot] `@Controller` that handles the rendering of the application pages. The project consists of five main pages (`/home`, `/tasks`, `/users`, `/signup`, `/login`) that can be accessed directly by typing their URL in the browser, or through the provided navigation menu. This controller checks if it is a [htmx] ([AJAX]) request or not, to return only a fragment with the relevant content of the page, or the full page if the user has accessed it through the URL in the browser.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/config/**`

This package contains several `@Component` and `@Configuration` classes that provide additional functionality or allow you to customize certain framework aspects, such as [security][spring-boot-starter-security], [validation][spring-boot-starter-validation], [data access][spring-boot-starter-data-jpa], or [view rendering][spring-boot-starter-thymeleaf].

For example, the [DataConfig.java] class, annotated as `@EnableJpaAuditing`, defines an `AuditorAware` bean based on the authenticated user. This minimal configuration enables the use of `@CreatedDate` and `@CreatedBy` in `@Entity` classes to automatically set the date and author of each task.

The [@Password] annotation uses a regular expression to validate the strength of passwords, and it is available for use on the `@Entity User`, like the `@Email` annotation provided by [Spring Validation][spring-boot-starter-validation].

In addition, the [SecurityAwareErrorAttributes.java] class extends the functionality of `DefaultErrorAttributes` to check for common security errors associated with the current request or stored in session; useful, for example, after a redirect to the `/login` page.

Security configurations are discussed in detail in the next section.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/tasks/**`

Contains the classes required for the layered handling of task-related HTTP requests. The presentation layer (`@Controller`) defines the routes and methods for creating, viewing, editing, and deleting tasks. The application layer (`@Service`, `@Mapper`) is responsible for business validation, transaction handling, and data mapping between the presentation and domain layers. The domain layer (`@Repository`, `@Entity`) handles interactions with the database, using [Spring Data JPA][spring-boot-starter-data-jpa] for simple and efficient task persistence.

#### `src/main/java/io/github/lcnicolau/cs50/todolist/users/**`

Similar to the previous package, it contains the classes needed for the layered handling of user-related HTTP requests. Additionally, it implements the `UserDetails`, `CredentialsContainer` and `UserDetailsService` interfaces for database user management with [Spring Security][spring-boot-starter-security].

#### `src/main/resources/application.yml`

Contains general application settings, including database connection parameters, logging, etc.

#### `src/main/resources/application-dev.yml`

Similar to the previous file, it contains specific settings for the development profile. When the `dev` profile is active, these settings override the general settings.

#### `src/main/resources/static/**`

Contains the application's static files: favicon, images, and custom styles (there are no JavaScript files).

#### `src/main/resources/templates/**`

Contains the general layout and each application page. [Thymeleaf] is used to dynamically render the pages, while providing a clean and interactive user interface thanks to [htmx] and [Alpine.js].


## Security Considerations

The [SecurityConfig.java] class defines a set of beans that work together to provide a security framework for the application, with the help of [Spring Security][spring-boot-starter-security].

The `SecurityFilterChain` bean defines the most important settings; for example, what to do on a failed or successful login attempt, and on logout. What to do when an anonymous user tries to access a protected resource, or when an authenticated user tries to access a restricted resource.

It also defines which resources are public (for example, static resources and the `/home`, `/signup`, `/login` and `/error` pages), which require authentication, and which are restricted to a specific role (for example, the user and role management itself).

The `WebSecurityCustomizer` bean is only enabled for the development profile `@Profile("dev")` and is responsible for disabling security for the [H2] web console, which already has its own security and is only available in development.

The `PasswordEncoder` bean defines the algorithm for encoding passwords, and collaborates with the `UserDetails` and `UserDetailsService` implementations (available in the [users] package) to register and authenticate users in the database.

Finally, [Spring Security][spring-boot-starter-security] provides built-in protection against some of the most common attacks, such as [XSS] and [CSRF], and proper management of [CORS] policies, without us having to do absolutely anything.

### HTMX Redirect Pattern

The default behavior of [Spring Security][spring-boot-starter-security] in the event of unauthorized access is to send a [302] redirect code to the client, pointing to the `/login` page; or in case of successful authentication, to the originally requested page.

While this approach may be the desired behavior in most cases, when used in conjunction with [htmx] to load page fragments using [AJAX] requests, it can leave the system in an inconsistent and sometimes even funny state (such as a login page inside a button :upside_down_face:). The problem is that the browser intercepts the redirect internally and returns the headers and response from the new URL, then [htmx] loads the response right where the original requested fragment should go.

The [htmx-authentication-error-handling] blog post proposes a workaround _"to have htmx force a full page browser refresh in case there is an authentication failure"_. While it works, this solution doesn't cover all scenarios where [Spring Security][spring-boot-starter-security] might trigger a redirect, and it misses a feature of [htmx] specifically designed to solve this problem.

In that sense, [htmx] provides a special way to send a redirect instruction to the client, keeping a [200] success code and sending a custom HTTP header ([HX-Location], [HX-Redirect]) from the server, which [htmx] correctly interprets and follows the redirect, replacing its content in the page body.

As a result, and inspired by the idea behind [htmx-authentication-error-handling], custom implementations of `AuthenticationFailureHandler`, `AuthenticationSuccessHandler`, `LogoutSuccessHandler`, `AuthenticationEntryPoint` and `AccessDeniedHandler` were created. These implementations are located in the [config/security] package and can be seen in action in the `SecurityFilterChain` bean definition, in the [SecurityConfig.java] class.


## Unit Testing

Although not exhaustive, the project has a set of automated unit tests for the main access points of the application, checking the consistency of the response whether accessed via the URL stored in the browser or via an [AJAX] request with [htmx].

There are also security tests for both protected and restricted resources; and in the case of [htmx] requests, it is verified that the response contains the _HTMX Redirect Pattern_ explained in the previous section. These tests are located in the [MainControllerTest.java] class.


## Next Steps

### Improvements

- Remove the `Task -> User` dependency and limit the `User` class visibility.
- Create service interfaces, expose [DTO] and make the implementation private.
- Show _loading_ indicator on `tasks` and `users` first load (`hx-trigger="load"`).
- Redirect to the requested page after login (`successHandler`).
- Show detailed errors only on the `dev` profile (`include-message`).
- Edit `tasks` completely on the frontend with [Alpine.js] (`input`).
- Handle `false` submission in checkbox with [Alpine.js] (`hx:vals`).
- Change `ViewUtils` to some native [Thymeleaf] object (`getElapsedTimeSince`).

### New Features

- Display counter of pending and total tasks.
- Use [Flyway] to create and initialize the database.
- Add logs to major functionality.
- Increase unit test coverage.
- Add authentication via [GitHub].
- Evaluate [Java Template Engine][jte].





[Español]: LEEME.md "Read in Spanish"

[DataConfig.java]: src/main/java/io/github/lcnicolau/cs50/todolist/config/DataConfig.java

[@Password]: src/main/java/io/github/lcnicolau/cs50/todolist/config/validation/Password.java

[SecurityAwareErrorAttributes.java]: src/main/java/io/github/lcnicolau/cs50/todolist/config/error/SecurityAwareErrorAttributes.java

[SecurityConfig.java]: src/main/java/io/github/lcnicolau/cs50/todolist/config/SecurityConfig.java

[config/security]: src/main/java/io/github/lcnicolau/cs50/todolist/config/security

[users]: src/main/java/io/github/lcnicolau/cs50/todolist/users

[MainControllerTest.java]: src/test/java/io/github/lcnicolau/cs50/todolist/MainControllerTest.java


[demo]: https://youtu.be/jI2FjRabNhU "Video Demo"

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

[RESTful]: https://developer.mozilla.org/en-US/docs/Glossary/REST "Representational State Transfer"

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

[htmx-authentication-error-handling]: https://www.wimdeblauwe.com/blog/2022/10/04/htmx-authentication-error-handling/ "Htmx authentication error handling"

[200]: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status/200 "OK"

[HX-Location]: https://htmx.org/headers/hx-location/ "HX-Location Response Header"

[HX-Redirect]: https://htmx.org/headers/hx-redirect/ "HX-Redirect Response Header"

[DTO]: https://stackoverflow.com/questions/1051182/what-is-a-data-transfer-object-dto "Data Transfer Object"

[Flyway]: https://github.com/flyway/flyway "Database Migrations Made Easy"

[GitHub]: https://docs.github.com/en/apps/oauth-apps/building-oauth-apps "Building OAuth apps"

[jte]: https://jte.gg/ "Java Template Engine"