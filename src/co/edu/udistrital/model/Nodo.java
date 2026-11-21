package co.edu.udistrital.model;

public class Nodo {
    int dato;
    int altura;

    // Hijos
    Nodo izquierdo;
    Nodo derecho;

    // Constructor.
    public Nodo(int dato) {
        this.dato = dato;
        this.altura = 1;
        this.izquierdo = null;
        this.derecho = null;
    }

    // Getters y setters: nodos.
    public Nodo getIzquierdo(){
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo){
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho (){
        return derecho;
    }

    public void setDerecho(Nodo derecho){
        this.derecho = derecho;
    }

    // Getters y setters: datos y altura.
    public int getDato(){
        return dato;
    }

    public void setDato(int dato){
        this.dato = dato;
    }

    public int getAltura(){
        return altura;
    }

    public void setAltura (int altura){
        this.altura = altura;
    }
}