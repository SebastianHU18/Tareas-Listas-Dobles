package ListasDobles;

public class ListaDoble<V> {
    private NodoDoble<V> cabeza;
    private NodoDoble<V> cola;
    private int tamano;
    public ListaDoble() {
        this.cabeza = null;
        this.cola = null;
        this.tamano = 0;
    }
    //todo insertar inicio, insertar final, insertar anywhere
    public void insertarInicio(V dato){
        NodoDoble<V> nuevo = new NodoDoble<>(dato);
        if(cabeza==null){
            cabeza=cola=nuevo;
        }
        else{
            nuevo.next=cabeza;
            cabeza.prev=nuevo;
            cabeza=nuevo;
        }
        tamano++;
    }
    public void insertarFin(V dato){
        NodoDoble<V> nuevo = new NodoDoble<>(dato);
        if(cabeza==null){
            cabeza=cola=nuevo;
        }else{
            cola.next=nuevo;
            nuevo.prev=cola;
            cola=nuevo;
        }
        tamano++;
    }
    public void insertarAnywhere(V dato, int posicion){
        NodoDoble<V> nuevo = new NodoDoble<>(dato);
        if(posicion<0 || posicion>tamano){
            System.out.println("Torpe no es posible");
            return;
        }
        if(posicion==0){
            insertarInicio(dato);
            return;
        }
        if(posicion==tamano){
            insertarFin(dato);
            return;
        }
        NodoDoble<V> actual = cabeza;
        for(int i=0;i<posicion;i++){
            actual=actual.next;
        }
        nuevo.prev=actual.prev;
        nuevo.next=actual;
        actual.prev.next=nuevo;
        actual.prev=nuevo;
        tamano++;
    }
    public void clear(){
        cabeza=null;
        cola=null;
        tamano=0;
    }
    public void recorrerAdelante(){
        NodoDoble<V> actual = cabeza;
        while(actual.next!=null){
            System.out.print(actual.datos + "<-> ");
            actual=actual.next;
        }
        System.out.println("null");
    }
    public void recorrerAtras(){
        NodoDoble<V> actual = cola;
        while(actual.prev!=null){
            System.out.print(actual.datos + "<-> ");
            actual=actual.prev;
        }
        System.out.println("null");
    }
    //Hacer buscar por dato
    public int getNodo(V dato){
        NodoDoble<V> actual = cabeza;
        int i=0;
        while(actual.next!=null){
            if(actual.datos.equals(dato)){
                return i;
            }
            i++;
            actual=actual.next;
        }
        return -1;
    }
    //todo eliminar con el dato o por posicion
    public boolean eliminarPorDato(V dato) {
        if (cabeza == null) return false;

        NodoDoble<V> actual = cabeza;

        while (actual != null) {
            if (actual.datos.equals(dato)) {

                if (actual == cabeza && actual == cola) {  // único nodo
                    cabeza = null;
                    cola = null;

                } else if (actual == cabeza) {             // es la cabeza
                    cabeza = cabeza.next;
                    cabeza.prev = null;

                } else if (actual == cola) {               // es la cola
                    cola = cola.prev;
                    cola.next = null;

                } else {                                   // nodo intermedio
                    actual.prev.next = actual.next;
                    actual.next.prev = actual.prev;
                }

                tamano--;
                return true;
            }
            actual = actual.next;
        }

        System.out.println("Dato no encontrado");
        return false;
    }

    public boolean eliminarPorPosicion(int pos) {
        if (pos < 0 || pos >= tamano) {
            System.out.println("Posición inválida");
            return false;
        }

        NodoDoble<V> actual = cabeza;
        for (int i = 0; i < pos; i++) {
            actual = actual.next;
        }

        if (actual == cabeza && actual == cola) {  // único nodo
            cabeza = null;
            cola = null;

        } else if (actual == cabeza) {             // es la cabeza
            cabeza = cabeza.next;
            cabeza.prev = null;

        } else if (actual == cola) {               // es la cola
            cola = cola.prev;
            cola.next = null;

        } else {                                   // nodo intermedio
            actual.prev.next = actual.next;
            actual.next.prev = actual.prev;
        }

        tamano--;
        return true;
    }
}