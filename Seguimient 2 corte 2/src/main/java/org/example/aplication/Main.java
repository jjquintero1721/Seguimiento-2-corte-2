package org.example.aplication;

import org.example.aplication.service.PacienteService;
import org.example.aplication.service.PacienteServiceImpl;
import org.example.domain.Paciente;
import org.example.infraestructure.repositoryImpl.PacienteRepositoryImpl;
import org.example.interfaces.PacienteRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final PacienteService pacienteService;

    static {
        PacienteRepository pacienteRepository = new PacienteRepositoryImpl();
        pacienteService = new PacienteServiceImpl(pacienteRepository);
    }

    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Listar pacientes");
            System.out.println("2. Crear paciente");
            System.out.println("3. Actualizar paciente");
            System.out.println("4. Eliminar paciente");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarPacientes();
                    break;
                case 2:
                    crearPaciente();
                    break;
                case 3:
                    actualizarPaciente();
                    break;
                case 4:
                    eliminarPaciente();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void listarPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            System.out.println("Listado de pacientes:");
            for (Paciente paciente : pacientes) {
                System.out.println(paciente);
            }
        }
    }

    private static void crearPaciente() {
        System.out.print("Ingrese el còdigo del paciente: ");
        int cod  = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente();
        paciente.setId(cod);
        paciente.setNombre(nombre);

        try {
            pacienteService.save(paciente);
            System.out.println("Paciente creado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void actualizarPaciente() {
        System.out.print("Ingrese el ID del paciente a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        System.out.print("Ingrese el nuevo nombre del paciente (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) {
            paciente.setNombre(nombre);
        }

        try {
            pacienteService.update(paciente);
            System.out.println("Paciente actualizado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void eliminarPaciente() {
        System.out.print("Ingrese el ID del paciente a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            System.out.println("No se encontró el paciente con ID " + id);
            return;
        }

        pacienteService.delete(id);
        System.out.println("Paciente eliminado exitosamente.");
    }
}
