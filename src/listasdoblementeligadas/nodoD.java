/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listasdoblementeligadas;

/**
 *
 * @author ligoh
 */
public class nodoD {
    private int dato;           // valor almacenado en el nodo
    private nodoD anterior;     // referencia al nodo anterior (null si es la cabeza)
    private nodoD siguiente;    // referencia al nodo siguiente (null si es la cola)

    public nodoD(int dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public nodoD getAnterior() {
        return anterior;
    }

    public void setAnterior(nodoD anterior) {
        this.anterior = anterior;
    }

    public nodoD getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(nodoD siguiente) {
        this.siguiente = siguiente;
    }
}
