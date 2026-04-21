# Dominio Estudiantes - DDD Workshop

Este proyecto implementa los principios de **Domain-Driven Design (DDD)** utilizando una arquitectura de **Vertical Slices (Sliced Architecture)** para el sistema de gestión académica de la Universidad Javeriana.

## Requisitos

- **Java 21**
- **Maven 3.9+**
- **SQLite** (Base de datos local)

## Arquitectura

El proyecto está organizado por "Slices" de dominio para mantener la cohesión y escalabilidad:

- `co.edu.javeriana.ddd.estudiantes`: Gestión de estudiantes, historia académica, periodos y materias.
- `co.edu.javeriana.ddd.asignaturas`: Catálogo de asignaturas con sus créditos y estados.
- `co.edu.javeriana.ddd.shared`: Componentes transversales como eventos de dominio base, excepciones y Value Objects comunes.

Cada slice sigue la estructura de capas:
- `domain`: Agregados, Entidades, Value Objects, Repositorios (interfaces) y Eventos.
- `application`: Casos de uso y servicios de aplicación.
- `infrastructure`: Implementaciones de persistencia (JPA), mappers y adaptadores externos.

## Conceptos de Dominio Implementados

### Estudiantes
- **Agregado Estudiante**: Raíz del agregado que gestiona la situación académica.
- **Historia Académica**: Estructura organizada por **Periodos Académicos** que contienen **Materias**.
- **Estados Académicos**: Transiciones estrictas (NORMAL, PRUEBA_ACADEMICA, EXCLUIDO, etc.) basadas en el promedio.
- **Value Objects**: `PromedioPonderado`, `Calificacion`, `Email`, `EstudianteId`.
- **Eventos de Dominio**: `EstudianteRegistrado`, `SituacionAcademicaCambiada`.

### Asignaturas
- **Agregado Asignatura**: Gestiona el catálogo de materias disponibles, sus créditos (1-10) y su estado (activa/inactiva).

## Tecnologías

- **Java 21**: Uso intensivo de **Records** para Value Objects y eventos, y patrones modernos.
- **JPA / Hibernate 6**: Persistencia desacoplada del dominio mediante Entidades de Infraestructura y Mappers.
- **SQLite**: Base de datos ligera embebida.
- **JUnit 5**: Pruebas unitarias y de integración.

## Construcción y Ejecución

Compilar el proyecto:
```bash
mvn compile
```

Ejecutar pruebas:
```bash
mvn test
```

La base de datos se genera automáticamente en `src/main/resources/db/estudiantes.db` mediante la configuración de `persistence.xml`.
