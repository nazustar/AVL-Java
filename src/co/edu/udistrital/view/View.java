package co.edu.udistrital.view;

import java.util.Scanner;

public class View {

    // Scanner unico para toda la vista (leer del teclado).
    private Scanner scanner = new Scanner(System.in);

    // Menu.
    
    public void mostrarMenu() {
        System.out.println("\n===== ARBOL AVL =====");
        System.out.println("1. Insertar");
        System.out.println("2. Eliminar");
        System.out.println("3. Buscar");
        System.out.println("4. Mostrar recorrido inorden");
        System.out.println("0. Salir");
    }

    // Entrada con validacion.
    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Dato invalido, escribe un numero entero.");
            }
        }
    }

    // Salida.

    // Imprime mensajes.
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    // Recorrido como texto.
    public void mostrarRecorrido(String nombre, String recorrido) {
        if (recorrido.isEmpty()) {
            System.out.println("El arbol esta vacio.");
        } else {
            System.out.println(nombre + ": " + recorrido);
        }
    }
}