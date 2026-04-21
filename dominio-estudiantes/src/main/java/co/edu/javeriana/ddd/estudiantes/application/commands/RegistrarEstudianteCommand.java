package co.edu.javeriana.ddd.estudiantes.application.commands;

public record RegistrarEstudianteCommand(String id, String nombres, String apellidos, String email) {
}
