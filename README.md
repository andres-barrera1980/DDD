# Proyecto DDD - Gestión de Estados Académicos

Este proyecto es una implementación robusta de los principios de **Domain-Driven Design (DDD)** para el dominio de **Estados Académicos** en una institución educativa. Se enfoca en la gestión de estudiantes, su historia académica y las transiciones de estado basadas en el rendimiento académico.

## 🎯 Objetivo del Proyecto

Modelar y desarrollar un sistema que gestione el ciclo de vida académico de un estudiante, aplicando patrones tácticos de DDD para garantizar que las reglas de negocio (invariantes) se mantengan consistentes y desacopladas de la infraestructura tecnológica.

## 🛠️ Metodologías Utilizadas

- **Domain-Driven Design (DDD)**: Centrado en el lenguaje ubicuo y el modelado del dominio.
- **Test-Driven Development (TDD)**: El desarrollo fue guiado por pruebas unitarias y de integración para asegurar la calidad y el cumplimiento de los requisitos.
- **Slice Architecture (Vertical Slices)**: El proyecto se organiza por "rebanadas" de funcionalidad de negocio en lugar de capas técnicas globales, lo que reduce el acoplamiento y facilita la escalabilidad.
- **Clean Architecture**: Dentro de cada slice, se aplican las capas de la arquitectura limpia para mantener el núcleo del negocio independiente de frameworks y bases de datos.

## 🧱 Estructura de Capas (Inside each Slice)

Cada módulo o *slice* sigue una estructura de capas interna:

1.  **Domain**: El corazón del negocio.
    - **Model**: Agregados, Entidades y Root Aggregates.
    - **Value Objects**: Objetos inmutables que definen atributos del dominio (ej. `Calificacion`, `Email`).
    - **Events**: Eventos de dominio que notifican cambios de estado importantes.
    - **Exceptions**: Excepciones de negocio específicas para evitar estados inválidos.
    - **Repository**: Interfaces que definen el contrato para la persistencia.
2.  **Application**: Orquestación de la lógica de negocio.
    - **Use Cases**: Procesos específicos que el sistema puede realizar.
    - **Commands**: Objetos de transferencia para ejecutar acciones.
    - **Facades**: Puntos de entrada simplificados para la capa de aplicación.
3.  **Infrastructure**: Implementación de detalles técnicos y adaptadores.
    - **Persistence**: Capa encargada de la persistencia de datos.
        - **Entities**: Objetos que representan la estructura de la base de datos (desacoplados del dominio).
        - **Repositories**: Implementaciones concretas de las interfaces de dominio (ej. `JpaEstudianteRepository`).
        - **Mappers**: Lógica para convertir entre Objetos de Dominio y Entidades de Persistencia.
    - **Interfaces**: Puntos de interacción con el usuario o sistemas externos.
        - **UI/Menu**: Implementaciones de interfaces de consola para la navegación (ej. `MainMenu`, `EstudianteMenu`).

## 🍕 Slices del Dominio

El dominio se ha dividido en los siguientes contextos/slices:

- **Estudiantes**: Gestión del perfil del estudiante y su situación académica (NORMAL, PRUEBA, EXCLUIDO).
- **Asignaturas**: Catálogo de materias, créditos y requisitos.
- **Shared**: Elementos comunes y transversales (`AggregateRoot`, `DomainEvent`, `ValueObjects` compartidos).

## 📂 Estructura de Directorios

```text
DDD/
├── dominio-estudiantes/           # Módulo principal de implementación
│   ├── src/
│   │   ├── main/java/co/edu/javeriana/ddd/
│   │   │   ├── estudiantes/       # Slice de Estudiantes
│   │   │   │   ├── domain/        # Modelos, Agregados, VOs y Eventos
│   │   │   │   ├── application/   # Casos de Uso y Comandos
│   │   │   │   └── infrastructure/
│   │   │   │       ├── persistence/ # Implementación de DB, Mappers y Entidades
│   │   │   │       └── interfaces/  # UI de Consola (Menús)
│   │   │   ├── asignaturas/       # Slice de Asignaturas
│   │   │   │   └── ...
│   │   │   └── shared/            # Slice de Objetos Compartidos
│   │   │       └── ...
│   │   └── test/java/...          # Pruebas unitarias guiadas por TDD
│   └── pom.xml                    # Configuración de Maven
├── .gitignore                     # Archivos ignorados
└── README.md                      # Este archivo
```

## ⚙️ Detalle de Implementación Técnica

### Persistencia (Persistence Layer)
Se utiliza una estrategia de desacoplamiento total basada en el patrón **Data Mapper**:
- **Agregados de Dominio**: Son POJOs puros sin anotaciones de JPA, lo que garantiza que el negocio sea independiente de la tecnología.
- **Entidades de Infraestructura**: Clases en el paquete `persistence.entities` (como `EstudianteEntity`) que contienen las anotaciones de Hibernate/JPA para SQLite.
- **Data Mappers**: La clase `EstudianteMapper` realiza la traducción bidireccional, permitiendo que el repositorio trabaje con objetos de dominio mientras persiste entidades de base de datos.
- **Repositorios**: Se utiliza JPA/Hibernate sobre SQLite para la gestión de datos.

### Interfaz de Usuario (Interfaces Layer)
El sistema utiliza una interfaz de línea de comandos organizada de forma jerárquica:
- **MainMenu**: Orquesta el acceso a los diferentes dominios del sistema.
- **EstudianteMenu**: Implementa la interacción para el registro y consulta de estudiantes, comunicándose exclusivamente con la capa de aplicación a través de la `EstudianteFacade`.

---
**Desarrollado como parte de los talleres de Ingeniería de Software - Pontificia Universidad Javeriana.**
