import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    @Test
    void insertarNumeroDos() {
        Tablero tablero = new Tablero();
        boolean resultado = tablero.insertarNumeroDos();
        assertTrue(resultado);
    }
    @Test
    void mostrar() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        boolean resultado = tablero.insertarNumeroDos();
        //tablero.print();
        System.out.println(tablero.toString());
        tablero.moverIzquierda();
        //tablero.print();
        System.out.println(tablero.toString());
        tablero.moverDerecha();
        //tablero.print();
        System.out.println(tablero.toString());
        //assertTrue(resultado);
    }
}