import java.util.*;

public class TableroConsola {
    private Tablero tablero;

    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
    }

    public void correr() {
        Scanner sc = new Scanner(System.in);
        tablero.insertarNumeroDos();
        while (true) {
            // pedir comando (mover izquierda, derecha, arriba)
            // w = arriba, a = izquierda, s = abajo, d = derecha.
            // ejecutar movimiento.
            // mostrar tablero.
            System.out.print("\033[H\033[2J");
            System.out.flush();
            printMessage();
            String inputCase = sc.next();
            if (inputCase == "w") tablero.moverArriba();
            else if (inputCase == "a") tablero.moverIzquierda();
            else if (inputCase == "s") tablero.moverAbajo();
            else if (inputCase == "d") tablero.moverDerecha();
            printTablero(tablero);
        }
    }

    private void printMessage() {
        System.out.println("Ingrese comando a ejecutar:");
        System.out.println("w = arriba; a = izquierda; s = abajo; d = derecha.");
    }

    private void printTablero(Tablero tablero) {
        String strTab = tablero.toString();
        String parts[] = strTab.split("|");
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
        }
    }
    // cuando termina el ciclo infinito?
    // 1. cuando lleguemos a un limite en alguna casilla (2048)
    // 2. cuando ya no hay casillas libres (inserto la ultima libre y ya no se puede mover).


}
