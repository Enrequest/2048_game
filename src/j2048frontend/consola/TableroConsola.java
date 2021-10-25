package j2048frontend.consola;

import java.util.*;
import j2048backend.Tablero;
import j2048backend.Estado;
import j2048frontend.Observador;
import j2048frontend.TableroUI;

public class TableroConsola implements Observador, TableroUI {
    private Tablero tablero;
    private boolean continuar = true;
    //private Observador observable;
    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        tablero.agregarObservador(this);
    }
    @Override
    public void correr() {
        Scanner sc = new Scanner(System.in);
        continuar = true;
        printTablero(tablero);
        printMessage();
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
    public void reset(){
        tablero = Tablero.crear2048();
    }
    @Override
    public void actualizar(Estado estado) {
        switch(estado){
            case PERDIDO:
                System.out.println("Perdio la partida!");
                reset();
                //continuar = false;
                break;
            case GANADO:
                System.out.println("Gano la partida!");
                reset();
                //tablero = Tablero.crear2048();
                //continuar = false;
                break;
            default:
                System.out.println("Continua la partida");
                break;
        }
        printTablero(tablero);
        printMessage();
        //printMessage();
    }
    // cuando termina el ciclo infinito?
    // 1. cuando lleguemos a un limite en alguna casilla (2048)
    // 2. cuando ya no hay casillas libres (inserto la ultima libre y ya no se puede mover).

}
