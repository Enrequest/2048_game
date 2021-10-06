package j2048frontend;

import java.util.*;
import j2048backend.Tablero;

public class TableroConsola {
    private Tablero tablero;

    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
    }
    public void correr() {
        Scanner sc = new Scanner(System.in);
        tablero.insertarNumeroDos();
        printTablero(tablero);
        while (!tablero.alcanceLimite()) {
            // pedir comando (mover izquierda, derecha, arriba)
            // w = arriba, a = izquierda, s = abajo, d = derecha.
            // ejecutar movimiento.
            // mostrar tablero.
            printMessage();
            String inputCase = sc.next();
            if (inputCase.compareTo("w")==0) tablero.moverArriba();
            else if (inputCase.compareTo("a")==0) tablero.moverIzquierda();
            else if (inputCase.compareTo("s")==0) tablero.moverAbajo();
            else if (inputCase.compareTo("d")==0) tablero.moverDerecha();
            printTablero(tablero);
        }
        System.out.println("Juego Terminado!");
    }
    private void printMessage() {
        System.out.println("Ingrese comando a ejecutar:");
        System.out.println("w = arriba; a = izquierda; s = abajo; d = derecha.");
    }
    private void printTablero(Tablero tablero) {
        String strTab = tablero.toString();
        String[] parts = strTab.split("\\|");
        for (int i = 0; i < parts.length; i++) {
            System.out.print("[");
            System.out.print(parts[i]);
            System.out.println("]");
        }
    }
    // cuando termina el ciclo infinito?
    // 1. cuando lleguemos a un limite en alguna casilla (2048)
    // 2. cuando ya no hay casillas libres (inserto la ultima libre y ya no se puede mover).
}
