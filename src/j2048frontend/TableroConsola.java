package j2048frontend;

import java.util.*;
import j2048backend.Tablero;
import j2048backend.Estado;

public class TableroConsola implements IObservador {
    private Tablero tablero;
    private boolean continuar = true;
    //private Observador observable;
    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        tablero.addObserver(this);
    }
    public void correr() {
        Scanner sc = new Scanner(System.in);
        tablero.insertarNumeroDos();
        //printTablero(tablero);
        //boolean continuar = true;
        continuar = true;
        printMessage();
        printTablero(tablero);
        while (continuar) {
            // pedir comando (mover izquierda, derecha, arriba)
            // w = arriba, a = izquierda, s = abajo, d = derecha.
            // ejecutar movimiento.
            // mostrar tablero.

            String inputCase = sc.next();
            if (inputCase.equals("w")) tablero.moverArriba();
            else if (inputCase.equals("a")) tablero.moverIzquierda();
            else if (inputCase.equals("s")) tablero.moverAbajo();
            else if (inputCase.equals("d")) tablero.moverDerecha();

            //printMessage();
            //printTablero(tablero);
            Estado estadoActual = tablero.estado();
            switch(estadoActual){
                case PERDIDO:
                    System.out.println("Perdio la partida!");
                    continuar = false;
                    break;
                case GANADO:
                    System.out.println("Gano la partida!");
                    continuar = false;
                    break;
                default: break;
            }
        }
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

    @Override
    public void update(Estado estado) {
        //printTablero(tablero);
        //printMessage();
        switch(estado){
            case PERDIDO:
                System.out.println("Perdio la partida!");
                continuar = false;
                break;
            case GANADO:
                System.out.println("Gano la partida!");
                continuar = false;
                break;
            default:
                printTablero(tablero);
                printMessage();
                break;
        }
        //printMessage();
    }
    // cuando termina el ciclo infinito?
    // 1. cuando lleguemos a un limite en alguna casilla (2048)
    // 2. cuando ya no hay casillas libres (inserto la ultima libre y ya no se puede mover).

}
