/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package listasdoblementeligadas;

/**
 *
 * @author ligoh
 */
public class ListasDoblementeLigadas {

    // Muestra la lista en ambos sentidos y el tamaño
    static void ver(ListaDoble l) {
        System.out.print("Adelante: ");
        l.mostrar();
        System.out.print("Reversa:  ");
        l.mostrarReversa();
        System.out.println("Tamaño: " + l.getTamanio() + "\n");
    }
    
    public static void main(String[] args) {
       ListaDoble lista = new ListaDoble();

        System.out.println("1. insertarOrdenado: 50, 20, 80, 10, 35");
        lista.insertarOrdenado(50);
        lista.insertarOrdenado(20);
        lista.insertarOrdenado(80);
        lista.insertarOrdenado(10);
        lista.insertarOrdenado(35);
        ver(lista);

        System.out.println("2. insertarEnPosicion(1, 5)  -> inicio");
        lista.insertarEnPosicion(1, 5);
        ver(lista);

        System.out.println("3. insertarEnPosicion(4, 99) -> medio");
        lista.insertarEnPosicion(4, 99);
        ver(lista);

        System.out.println("4. insertarEnPosicion(8, 100) -> final");
        lista.insertarEnPosicion(8, 100);
        ver(lista);

        System.out.println("5. eliminarPorValor(99) -> " + lista.eliminarPorValor(99));
        ver(lista);

        System.out.println("6. eliminarPorValor(777) -> " + lista.eliminarPorValor(777));
        ver(lista);

        System.out.println("7. eliminarEnPosicion(1) -> dato " + lista.eliminarEnPosicion(1));
        ver(lista);

        System.out.println("8. eliminarEnPosicion(3) -> dato " + lista.eliminarEnPosicion(3));
        ver(lista);

        System.out.println("9. eliminarUltimo() -> " + lista.eliminarUltimo());
        ver(lista);

        System.out.println("10. Posicion fuera de rango:");
        try {
            lista.eliminarEnPosicion(50);
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }

        System.out.println("11. Vaciar la lista con eliminarUltimo()");
        while (lista.eliminarUltimo()) { }
        ver(lista);

        System.out.println("12. insertarOrdenado en lista vacia: 30, 10, 20");
        lista.insertarOrdenado(30);
        lista.insertarOrdenado(10);
        lista.insertarOrdenado(20);
        ver(lista);
    } 
}
