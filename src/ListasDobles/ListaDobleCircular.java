package ListasDobles;

public class ListaDobleCircular<P> {
    private NodoDoble<P> cabeza;
    private int tam;
    public ListaDobleCircular() {
        this.tam = 0;
        this.cabeza = null;
    }
    //Metodo insertar al principio y al final
    public void insertar(P elemento) {
        NodoDoble<P> nuevo = new NodoDoble<>(elemento);
        if(cabeza == null) {
            cabeza = nuevo;
            cabeza.next = cabeza;
            cabeza.prev = cabeza;
        }
        else{
            NodoDoble<P> ultimo = cabeza.prev;
            nuevo.next = cabeza;
            nuevo.prev = ultimo;
            ultimo.next=nuevo;
            cabeza.prev = nuevo;
        }
        tam++;
    }

    //todo buscar, recorrr derecho y reves, eliminar, insertar cualquier pos
    public void insertarEnPos(P elemento, int pos) {
        if (pos < 0 || pos > tam) {
            System.out.println("Posición inválida");
            return;
        }
        if (pos == 0) {
            insertar(elemento);
            cabeza = cabeza.prev;
            return;
        }
        if (pos == tam) {
            insertar(elemento);
            return;
        }
        NodoDoble<P> nuevo = new NodoDoble<>(elemento);
        NodoDoble<P> actual = cabeza;
        for (int i = 0; i < pos; i++) {
            actual = actual.next;
        }
        NodoDoble<P> anterior = actual.prev;
        nuevo.next = actual;
        nuevo.prev = anterior;
        anterior.next = nuevo;
        actual.prev = nuevo;
        tam++;
    }

    public int buscar(P dato) {
        if (cabeza == null) return -1;
        NodoDoble<P> actual = cabeza;
        for (int i = 0; i < tam; i++) {
            if (actual.datos.equals(dato)) return i;
            actual = actual.next;
        }
        return -1;
    }

    public void recorrerAdelante() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }
        NodoDoble<P> actual = cabeza;
        do {
            System.out.print(actual.datos + " <-> ");
            actual = actual.next;
        } while (actual != cabeza);
        System.out.println("(cabeza)");
    }

    public void recorrerAtras() {
        if (cabeza == null) {
            System.out.println("Lista vacía");
            return;
        }
        NodoDoble<P> actual = cabeza.prev;   // empieza en el último nodo
        do {
            System.out.print(actual.datos + " <-> ");
            actual = actual.prev;
        } while (actual != cabeza.prev);
        System.out.println("(último)");
    }

    public boolean eliminar(P dato) {
        if (cabeza == null) return false;

        NodoDoble<P> actual = cabeza;
        for (int i = 0; i < tam; i++) {
            if (actual.datos.equals(dato)) {

                if (tam == 1) {
                    cabeza = null;
                } else {
                    actual.prev.next = actual.next;
                    actual.next.prev = actual.prev;
                    if (actual == cabeza) {
                        cabeza = actual.next;
                    }
                }
                tam--;
                return true;
            }
            actual = actual.next;
        }
        System.out.println("Dato no encontrado");
        return false;
    }

}
