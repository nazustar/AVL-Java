package co.edu.udistrital.controller;

import co.edu.udistrital.model.ArbolAVL;
import co.edu.udistrital.view.View;

public class Controller {
    private ArbolAVL arbol;
    private View view;

    // Constructor.
    public Controller() {
        arbol = new ArbolAVL();
        view = new View();
    }

    // Ciclo principal del programa.
    public void iniciar() {
        int opcion;
        do {
            view.mostrarMenu();
            opcion = view.leerEntero("Opcion: ");

            switch (opcion) {
                case 1:
                    insertar();
                    break;
                case 2:
                    eliminar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    view.mostrarRecorrido("Inorden", arbol.inorden());
                    break;
                case 0:
                    view.mostrarMensaje("Hasta luego.");
                    break;
                default:
                    view.mostrarMensaje("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    // Pide un dato a la vista y lo inserta en el modelo.
    private void insertar() {
        int dato = view.leerEntero("Dato a insertar: ");
        arbol.insertar(dato);
        view.mostrarMensaje("Dato " + dato + " insertado.");
    }

    // Verifica que exista antes de eliminar, para dar un mensaje correcto.
    private void eliminar() {
        int dato = view.leerEntero("Dato a eliminar: ");
        if (arbol.buscar(dato)) {
            arbol.eliminar(dato);
            view.mostrarMensaje("Dato " + dato + " eliminado.");
        } else {
            view.mostrarMensaje("El dato " + dato + " no existe en el arbol.");
        }
    }

    // Busca un dato y le dice a la vista el resultado.
    private void buscar() {
        int dato = view.leerEntero("Dato a buscar: ");
        if (arbol.buscar(dato)) {
            view.mostrarMensaje("El dato " + dato + " SI esta en el arbol.");
        } else {
            view.mostrarMensaje("El dato " + dato + " NO esta en el arbol.");
        }
    }
}