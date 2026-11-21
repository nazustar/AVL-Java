package co.edu.udistrital.model;

public class ArbolAVL {

    // Raiz del arbol, de aqui parten los otros nodos.
    Nodo raiz;

    // Utilidades.

    // Altura de nodos.
    private int altura(Nodo nodo) {
        if (nodo == null) return 0;
        return nodo.getAltura();
    }

    // Calcular el equilibrio.
    private int obtenerEquilibrio(Nodo nodo) {
        // Si es vacio.
        if (nodo == null){
            return 0;
        }
        return altura(nodo.getIzquierdo()) - altura(nodo.getDerecho());
    }

    // Rotaciones.

    // Rotación a la derecha: Caso LL.
    private Nodo rotacionDerecha(Nodo y) {
        // En esta caso el nodo y esta desbalanceado.

        // Guardar referencias.
        Nodo x = y.getIzquierdo();
        Nodo T2 = x.getDerecho();

        // Realizar rotación.
        x.setDerecho(y);
        y.setIzquierdo(T2);

        // Actualizar alturas.
        y.setAltura(Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);
        x.setAltura(Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);

        // Nuevo nodo raíz del subárbol.
        return x;
    }

    // Rotación a la izquierda (Caso RR)
    private Nodo rotacionIzquierda(Nodo x) {
        // En este caso el nodo x esta desbalanceado.

        Nodo y = x.getDerecho();
        Nodo T2 = y.getIzquierdo();

        // Realizar rotación
        y.setIzquierdo(x);
        x.setDerecho(T2);

        // Actualizar alturas
        x.setAltura(Math.max(altura(x.getIzquierdo()), altura(x.getDerecho())) + 1);
        y.setAltura(Math.max(altura(y.getIzquierdo()), altura(y.getDerecho())) + 1);

        return y;
    }

    // Insercion: Caso base, sin problemas.
    public void insertar(int dato) {
        raiz = insertarAVL(raiz, dato);
    }

    // Insercion: Casos LL, RR, LR, RL.
    private Nodo insertarAVL(Nodo nodo, int dato){

        // Si el nodo esta vacio se inserta normal.
        if (nodo == null) {
            return new Nodo(dato);
        }
        // Si el dato es menor que el nodo: izquierda.
        if (dato < nodo.getDato()){
            nodo.setIzquierdo(insertarAVL(nodo.getIzquierdo(), dato));
        } // Si el dato es mayor : derecha.
        else if (dato > nodo.getDato()){
            nodo.setDerecho(insertarAVL(nodo.getDerecho(), dato));
        } else {
            return nodo;
        }

        // Actualizar altura.
        nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);

        int equilibrio = obtenerEquilibrio(nodo);

        // Rotaciones al desbalancearse.

        // Izquierda-Izquierda (LL)
        if (equilibrio > 1 && dato < nodo.getIzquierdo().getDato()) {
            return rotacionDerecha(nodo);
        }
        // Derecha-Derecha (RR)
        if (equilibrio < -1 && dato > nodo.getDerecho().getDato()) {
            return rotacionIzquierda(nodo);
        }
        // Izquierda-Derecha (LR)
        if (equilibrio > 1 && dato > nodo.getIzquierdo().getDato()) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }
        // Derecha-Izquierda (RL)
        if (equilibrio < -1 && dato < nodo.getDerecho().getDato()) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Recorrido en orden.
    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inordenRec(raiz, sb);
        return sb.toString().trim();
    }

    // Método recursivo privado.
    private void inordenRec(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            inordenRec(nodo.getIzquierdo(), sb);
            sb.append(nodo.getDato()).append(" ");
            inordenRec(nodo.getDerecho(), sb);
        }
    }

    // Buscar.

    public boolean buscar(int dato) {
        return buscarNodo(raiz, dato);
    }

    private boolean buscarNodo(Nodo nodo, int dato) {
        // Si no encuentra nada, el dato no esta.
        if (nodo == null) {
            return false;
        }
        // Si lo encontro.
        if (dato == nodo.getDato()) {
            return true;
        }
        // Si es menor: izquierda, si es mayor: derecha.
        if (dato < nodo.getDato()) {
            return buscarNodo(nodo.getIzquierdo(), dato);
        }
        return buscarNodo(nodo.getDerecho(), dato);
    }

    // Eliminar.

    public void eliminar(int dato) {
        raiz = eliminarAVL(raiz, dato);
    }

    // Nodo con el menor dato de un subarbol (el mas a la izquierda).
    private Nodo minimo(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    private Nodo eliminarAVL(Nodo nodo, int dato) {

        // Si el nodo esta vacio, el dato no existe.
        if (nodo == null) {
            return null;
        }

        // Baja haste encntrar el nodo.
        if (dato < nodo.getDato()) {
            nodo.setIzquierdo(eliminarAVL(nodo.getIzquierdo(), dato));
        } else if (dato > nodo.getDato()) {
            nodo.setDerecho(eliminarAVL(nodo.getDerecho(), dato));
        } else {
            // Si lo encuentra 3 casos:

            // 1. Sin hijo izquierdo.
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            }
            // 2. Sin hijo derecho.
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            }
            // 3. Dos hijos: reemplazar por el sucesor inorden.
            Nodo sucesor = minimo(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(eliminarAVL(nodo.getDerecho(), sucesor.getDato()));
        }

        // Actualizar altura.
        nodo.setAltura(Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho())) + 1);

        int equilibrio = obtenerEquilibrio(nodo);

        // Rebalancear (aqui se mira el equilibrio).

        // Izquierda-Izquierda (LL)
        if (equilibrio > 1 && obtenerEquilibrio(nodo.getIzquierdo()) >= 0) {
            return rotacionDerecha(nodo);
        }
        // Izquierda-Derecha (LR)
        if (equilibrio > 1 && obtenerEquilibrio(nodo.getIzquierdo()) < 0) {
            nodo.setIzquierdo(rotacionIzquierda(nodo.getIzquierdo()));
            return rotacionDerecha(nodo);
        }
        // Derecha-Derecha (RR)
        if (equilibrio < -1 && obtenerEquilibrio(nodo.getDerecho()) <= 0) {
            return rotacionIzquierda(nodo);
        }
        // Derecha-Izquierda (RL)
        if (equilibrio < -1 && obtenerEquilibrio(nodo.getDerecho()) > 0) {
            nodo.setDerecho(rotacionDerecha(nodo.getDerecho()));
            return rotacionIzquierda(nodo);
        }

        return nodo;
    }
}