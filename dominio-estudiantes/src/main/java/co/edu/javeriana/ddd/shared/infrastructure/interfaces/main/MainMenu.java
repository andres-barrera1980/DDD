package co.edu.javeriana.ddd.shared.infrastructure.interfaces.main;

import co.edu.javeriana.ddd.estudiantes.application.facades.EstudianteFacade;
import co.edu.javeriana.ddd.estudiantes.application.usecases.RegistrarEstudiante;
import co.edu.javeriana.ddd.estudiantes.infrastructure.interfaces.main.EstudianteMenu;
import co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.repositories.JpaEstudianteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final EstudianteMenu estudianteMenu;

    public MainMenu() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudiantes-pu");
        EntityManager em = emf.createEntityManager();

        EstudianteFacade estudianteFacade = new EstudianteFacade(new RegistrarEstudiante(new JpaEstudianteRepository(em)));
        this.estudianteMenu = new EstudianteMenu(estudianteFacade, scanner);
    }

    public void start() {
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Estudiantes");
            System.out.println("2. Salir");
            System.out.print("Selección: ");

            String choice = scanner.nextLine();
            if (choice.equals("1")) estudianteMenu.show();
            else if (choice.equals("2")) break;
        }
    }

    public static void main(String[] args) {
        new MainMenu().start();
    }
}
