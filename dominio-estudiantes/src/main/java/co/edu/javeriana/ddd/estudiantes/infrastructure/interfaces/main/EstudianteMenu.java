package co.edu.javeriana.ddd.estudiantes.infrastructure.interfaces.main;

import co.edu.javeriana.ddd.estudiantes.application.commands.RegistrarEstudianteCommand;
import co.edu.javeriana.ddd.estudiantes.application.facades.EstudianteFacade;

import java.util.Scanner;
import java.util.UUID;

public class EstudianteMenu {
    private final EstudianteFacade facade;
    private final Scanner scanner;

    public EstudianteMenu(EstudianteFacade facade, Scanner scanner) {
        this.facade = facade;
        this.scanner = scanner;
    }

    public void show() {
        System.out.println("\n--- Menú Estudiantes ---");
        System.out.println("1. Registrar Estudiante");
        System.out.println("2. Volver");
        System.out.print("Selección: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> registrar();
            case "2" -> {}
            default -> System.out.println("Opción inválida");
        }
    }

    private void registrar() {

        System.out.print("Nombres: ");
        String nombres = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        facade.registrar(new RegistrarEstudianteCommand(UUID.randomUUID().toString(), nombres, apellidos, email));
        System.out.println("Estudiante registrado con éxito.");
    }
}
