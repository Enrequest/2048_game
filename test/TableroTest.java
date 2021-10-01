import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableroTest {

    @Test
    void insertarNumeroDosTableroVacio() {
        Tablero tablero = new Tablero();
        boolean resultado = tablero.insertarNumeroDos();
        assertTrue(resultado);
    }
    @Test
    void insertarNumeroDosTableroLLeno() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        boolean resultado = tablero.insertarNumeroDos();
        assertFalse(resultado);
    }

    @Test
    void toStringCantidadCaracteres(){
        Tablero tablero = new Tablero();
        int longitud = 31;
        assertTrue(tablero.toString().length()==longitud);
    }
    @Test
    void toStringTableroVacio(){
        Tablero tablero = new Tablero();
        String str = "0 0 0 0|0 0 0 0|0 0 0 0|0 0 0 0";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void toStringTableroLleno(){
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        String str = "2 2 2 2|2 2 2 2|2 2 2 2|2 2 2 2";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverIzquierdaTableroLleno() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        String str = "4 4 0 0|4 4 0 0|4 4 0 0|4 4 0 0";
        tablero.moverIzquierda();
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverDerechaTableroLleno() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        tablero.insertarNumeroDos();
        String str = "0 0 4 4|0 0 4 4|0 0 4 4|0 0 4 4";
        tablero.moverDerecha();
        assertTrue(tablero.toString().compareTo(str)==0);
    }
}