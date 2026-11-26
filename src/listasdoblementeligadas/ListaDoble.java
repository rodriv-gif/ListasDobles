/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listasdoblementeligadas;

/**
 *
 * @author ligoh
 */

/**
 * Lista doblemente ligada de enteros.
 * Usa los apuntadores cabeza (primer nodo) y cola (último nodo),
 * y cada nodo conoce a su anterior y a su siguiente.
 */
public class ListaDoble {
    private nodoD cabeza; //el primer nodo de la lista
    private nodoD cola;  //el ultimo nodo de la lista
    private int tamanio; //cantidad de nodos almacenados
    
    //crea la lista vacia
    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamanio = 0;
    }
    
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    /**
     * Inserta al inicio
     * Casos: lista vacía, el nodo también es la cola o lista con datos
     * el nuevo apunta a la cabeza actual y esta apunta atrás al nuevo.
     * En ambos casos el nuevo nodo pasa a ser la cabeza.
     */
    public void insertarInicio(int dato){
        nodoD nuevo = new nodoD(dato);
        //en caso de que la lista este vacia
        if (estaVacia()){
            cola = nuevo;
        }else{
            nuevo.setSiguiente(cabeza); //el nuevo apunta a la cabeza
            cabeza.setAnterior(nuevo);  //la cabeza apunta atras al nuevo
        }
        cabeza = nuevo; //en ambois casos el nuevo es la cabeza
        tamanio ++;
    }
    /**
     * Inserta al final.
     * Casos: lista vacía, el nodo también es la cabeza o lista con datos
     * el nuevo apunta atrás a la cola actual y esta apunta al nuevo.
     * En ambos casos el nuevo nodo pasa a ser la cola.
     */
    public void insertarFinal(int dato) {
        nodoD nuevo = new nodoD(dato);
        if (estaVacia()) {
            cabeza = nuevo;               // solo si está vacía cambia la cabeza
        } else {
            nuevo.setAnterior(cola);      // el nuevo apunta atrás a la cola actual
            cola.setSiguiente(nuevo);     // la cola actual apunta al nuevo
        }
        cola = nuevo;                     // en ambos casos el nuevo es la cola
        tamanio++;
    }

    /**
     * Elimina el primer nodo y devuelve su dato.
     * Casos: lista vacía (excepción), un solo nodo (cabeza y cola quedan
     * en null) o varios nodos (la cabeza pasa al segundo).
     */
    public int eliminarAlInicio() {
        if (estaVacia()) {
            throw new IllegalStateException("La lista está vacía");
        }
        int res = cabeza.getDato();
        if (cabeza == cola) {                 // solo había un nodo
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.getSiguiente();   // la cabeza pasa al segundo
            cabeza.setAnterior(null);         // la nueva cabeza ya no tiene anterior
        }
        tamanio--;
        return res;
    }

    /**
     * Busca un valor recorriendo desde la cabeza. 
     * true si el valor existe, false si no.
     */
    public boolean buscar(int valor) {
        nodoD aux = cabeza;
        while (aux != null) {   //mientras no se salga de lafila hacer lo que esta adentro
            if (aux.getDato() == valor) {  //si coinciden
                return true;               //returna true ya que se encontro
            }
            aux = aux.getSiguiente();  //se pasa al siguiente
        }
        return false;  //returna flaso si reviso en toda la fila y no encontro nada
    }

    /**
     * Devuelve el nodo en la posición pos.
     * Si la posición está en la primera mitad se recorre desde la cabeza;
     * si está en la segunda, desde la cola hacia atrás.
     * Lanza excepción si pos es menor a 1 o mayor al tamaño.
     */
    public nodoD obtenerNodo(int pos) {
        if (pos <= 0 || pos > tamanio) {
            throw new IllegalStateException(
                "Posición no válida: " + pos + ", rango válido del 1 al " + tamanio);
        }
        nodoD aux;
        if (pos <= tamanio / 2) {   //pregunta si esta en la primera mitad
            aux = cabeza;
            for (int i = 1; i < pos; i++) {  //recorre hacia adelante
                aux = aux.getSiguiente();
            }
        } else {              // si no esta en la primera mitad pasa a preguntar la segunda mitad
            aux = cola;
            for (int i = tamanio; i > pos; i--) {  //recorre hacia atrás
                aux = aux.getAnterior();
            }
        }
        return aux;
    }

    // Cambia el dato de la posición pos (no mueve nodos).
    public void actualizar(int pos, int dato) {
        obtenerNodo(pos).setDato(dato);
    }
    
    /**
     * Inserta un dato en la posición pos 1 a tamanio + 1.
     * Casos:
     * - Posición inválida: lanza excepción.
     * - pos == 1: insertarInicio y (actualiza cabeza).
     * - pos == tamanio + 1: insertarFinal y (actualiza cola).
     * - Caso general: se ubica el nodo que quedará después del nuevo y
     *   se enlazan los cuatro apuntadores (anterior.siguiente,
     *   nuevo.anterior, nuevo.siguiente y siguiente.anterior).
     */
    public void insertarEnPosicion(int pos, int dato) {
        if (pos <= 0 || pos > tamanio + 1) {
            throw new IllegalStateException(
                "Posición no válida: " + pos + ", rango válido del 1 al " + (tamanio + 1));
        }
        if (pos == 1) {   //caso borde, al frente
            insertarInicio(dato);
            return;
        }
        if (pos == tamanio + 1) { //caso borde al forndo
            insertarFinal(dato);
            return;
        }
        nodoD sig = obtenerNodo(pos);        // nodo que quedará después del nuevo
        nodoD ant = sig.getAnterior();       // nodo que quedará antes del nuevo
        nodoD nuevo = new nodoD(dato);

        nuevo.setAnterior(ant);   // el nuevo agarra al de atras
        nuevo.setSiguiente(sig);  //el nuevo agarra al del adelante
        ant.setSiguiente(nuevo);  //el de atras agarra al nuevo
        sig.setAnterior(nuevo);   //el del adelante agarra al nuevo
        tamanio++;
    }
    
    /**
     * Elimina la primera ocurrencia del valor x.
     * Casos:
     * - Lista vacía o valor inexistente retorna false.
     * - Está en la cabeza eliminarAlInicio.
     * - Está en la cola eliminarUltimo.
     * - Está en medio el anterior y el siguiente se enlazan entre sí.
     * true si lo eliminó, false si no lo encontró.
     */
    public boolean eliminarPorValor(int x) {
        if (estaVacia()) {
            return false;
        }
        nodoD aux = cabeza;
        while (aux != null && aux.getDato() != x) {
            aux = aux.getSiguiente();
        }
        if (aux == null) {
            return false;                    // no se encontró
        }
        if (aux == cabeza) {
            eliminarAlInicio();
        } else if (aux == cola) {
            eliminarUltimo();
        } else {   
            aux.getAnterior().setSiguiente(aux.getSiguiente()); //el de atras agarra al de adelante
            aux.getSiguiente().setAnterior(aux.getAnterior());  //el de adelante agarra al de atrás
            tamanio--;
        }
        return true;
    }
    
    /**
     * Elimina el nodo de la posición pos (1 a tamanio) y devuelve su dato.
     * Casos:
     * - Posición inválida: lanza excepción.
     * - pos == 1: eliminarAlInicio.
     * - pos == tamanio: eliminarUltimo (se guarda el dato antes).
     * - Caso general: el anterior y el siguiente se enlazan entre sí.
     */
    public int eliminarEnPosicion(int pos) {
        if (pos <= 0 || pos > tamanio) {
            throw new IllegalStateException(
                "Posición no válida: " + pos + ", rango válido del 1 al " + tamanio);
        }
        if (pos == 1) {
            return eliminarAlInicio();
        }
        if (pos == tamanio) {
            int res = cola.getDato();
            eliminarUltimo();
            return res;
        }
        nodoD objetivo = obtenerNodo(pos);
        int res = objetivo.getDato();
        objetivo.getAnterior().setSiguiente(objetivo.getSiguiente()); //el de atrás agarra al del adelante
        objetivo.getSiguiente().setAnterior(objetivo.getAnterior()); //el del adelante agarra al de atras
        tamanio--;
        return res;
    }

    /**
     * Elimina el último nodo usando el apuntador cola. 
     * Casos:
     * - Lista vacía retorna false.
     * - Un solo nodo cabeza y cola quedan en null.
     * - Varios nodos la cola retrocede al anterior y su siguiente se
     *   pone en null.
     * true si eliminó, false si la lista estaba vacía.
     */
    public boolean eliminarUltimo() {
        if (estaVacia()) {
            return false;
        }
        if (cabeza == cola) {                // solo había un nodo
            cabeza = null;
            cola = null;
        } else {
            cola = cola.getAnterior();       // la cola retrocede
            cola.setSiguiente(null);         // la nueva cola ya no tiene siguiente
        }
        tamanio--;
        return true;
    }

    /**
     * Inserta un dato manteniendo el orden de menor a mayor.
     * Precondición: la lista ya está ordenada. 
     * Casos:
     * - Lista vacía o dato <= cabeza, insertarInicio (actualiza cabeza).
     * - dato >= cola: insertarFinal (actualiza cola).
     * - Caso general: un auxiliar avanza hasta el primer nodo con dato
     *   mayor o igual y el nuevo se coloca justo antes de ese nodo,
     *   enlazando anterior y siguiente en ambos sentidos.
     */
    public void insertarOrdenado(int dato) {
        if (estaVacia() || dato <= cabeza.getDato()) {
            insertarInicio(dato);
            return;
        }
        if (dato >= cola.getDato()) {
            insertarFinal(dato);
            return;
        }
        nodoD aux = cabeza.getSiguiente();
        while (aux.getDato() < dato) {       // siempre se detiene antes de pasar la cola
            aux = aux.getSiguiente();
        }
        nodoD ant = aux.getAnterior();
        nodoD nuevo = new nodoD(dato);

        nuevo.setAnterior(ant);
        nuevo.setSiguiente(aux);
        ant.setSiguiente(nuevo);
        aux.setAnterior(nuevo);
        tamanio++;
    }
    /** Muestra la lista de cabeza a cola. */
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("(Lista vacía)");
            return;
        }
        nodoD aux = cabeza;
        System.out.print("NULL <-> ");
        while (aux != null) {
            System.out.print("[" + aux.getDato() + "]");
            System.out.print(aux.getSiguiente() != null ? " <-> " : " <-> NULL");
            aux = aux.getSiguiente();
        }
        System.out.println();
    }

    /** Muestra la lista de cola a cabeza (comprueba los enlaces anterior). */
    public void mostrarReversa() {
        if (estaVacia()) {
            System.out.println("(Lista vacía)");
            return;
        }
        nodoD aux = cola;
        System.out.print("NULL <-> ");
        while (aux != null) {
            System.out.print("[" + aux.getDato() + "]");
            System.out.print(aux.getAnterior() != null ? " <-> " : " <-> NULL");
            aux = aux.getAnterior();
        }
        System.out.println();
    }
}
