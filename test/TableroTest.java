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
    //Test funcion toString()
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

    //Test funcion moverIzquierda()
    @Test
    void moverIzquierdaVacio() {
        Tablero tablero = new Tablero();
        tablero.moverIzquierda();
        String str = tablero.toString();
        assertTrue(str.contains("2"));
    }
    @Test
    void moverIzquierdaTableroLleno() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverIzquierda();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "4 4 2 2|4 4 2 2|4 4 2 2|4 4 2 2";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverIzquierdaTableroLlenoDosVeces() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverIzquierda();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        tablero.moverIzquierda();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "8 4 2 2|8 4 2 2|8 4 2 2|8 4 2 2";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    /*
    @Test
    void moverIzquierdaRotado() {
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        tablero.moverIzquierda();
        String str = tablero.toString();
        for(int i=0; i<)
    }
    */
    //Test funcion moverDerecha()
    @Test
    void moverDerechaVacio() {
        Tablero tablero = new Tablero();
        tablero.moverDerecha();
        String str = tablero.toString();
        assertTrue(str.contains("2"));
    }
    @Test
    void moverDerechaTableroLleno() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverDerecha();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "2 2 4 4|2 2 4 4|2 2 4 4|2 2 4 4";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverDerechaTableroLlenoDosVeces() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverDerecha();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        tablero.moverDerecha();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "2 2 4 8|2 2 4 8|2 2 4 8|2 2 4 8";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    //Test funcion moverArriba()
    @Test
    void moverArribaVacio() {
        Tablero tablero = new Tablero();
        tablero.moverArriba();
        String str = tablero.toString();
        assertTrue(str.contains("2"));
    }
    @Test
    void moverArribaTableroLleno() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverArriba();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "4 4 4 4|4 4 4 4|2 2 2 2|2 2 2 2";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverArribaTableroLlenoDosVeces() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverArriba();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        tablero.moverArriba();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "8 8 8 8|4 4 4 4|2 2 2 2|2 2 2 2";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    //Test funcion moverAbajo()
    @Test
    void moverAbajoVacio() {
        Tablero tablero = new Tablero();
        tablero.moverAbajo();
        String str = tablero.toString();
        assertTrue(str.contains("2"));
    }
    @Test
    void moverAbajoTableroLleno() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverAbajo();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "2 2 2 2|2 2 2 2|4 4 4 4|4 4 4 4";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
    @Test
    void moverAbajoTableroLlenoDosVeces() {
        Tablero tablero = new Tablero();
        for(int i = 0; i<16; i++) tablero.insertarNumeroDos();
        tablero.moverAbajo();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        tablero.moverAbajo();
        for(int i = 0; i<7; i++) tablero.insertarNumeroDos();
        String str = "2 2 2 2|2 2 2 2|4 4 4 4|8 8 8 8";
        assertTrue(tablero.toString().compareTo(str)==0);
    }
}