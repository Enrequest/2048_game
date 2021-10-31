package j2048backend;

import j2048frontend.Observador;

import java.util.*;
public class Tablero {
    //Atributes:
    private int DIMENSION;
    private int matrix[][];
    ////List of Observers:
    private List<Observador> observadores;
    private boolean terminado;
    private boolean reiniciado;
    private int partidasGanadas;
    ///Constructors:
    private Tablero() {
        DIMENSION = 4;
        observadores = new ArrayList<Observador>();
        matrix = new int[DIMENSION][DIMENSION];
        terminado = false;
        reiniciado = false;
        partidasGanadas = 0;
    }
    //Methods:
    public static Tablero crear2048(){
        Tablero tablero = new Tablero();
        tablero.insertarNumeroDos();
        return tablero;
    }

    private boolean isFull(){
        for(int i = 0; i< DIMENSION; i++)
            for(int j = 0; j< DIMENSION; j++)
                if(matrix[i][j]==0) return false;
        return true;
    }
    private boolean insertarNumeroDos(){
        Random rand = new Random();
        if(isFull()) return false;
        while(true){
            int i = rand.nextInt(DIMENSION);
            int j = rand.nextInt(DIMENSION);
            if (matrix[i][j] == 0) {
                matrix[i][j] = 2;
                return true;
            }
        }
    }
    //Rotate the matrix 90 degree clockwise:
    private void rotateRight(){
        int M[][] = new int[DIMENSION][DIMENSION];
        int x =0;
        for(int j = 0; j < DIMENSION; j++){
            int y = 0;
            for(int i = DIMENSION - 1; i >= 0; i--) {
                M[x][y] = matrix[i][j];
                ++y;
            }
            ++x;
        }
        matrix = M;
    }
    private void rotateLeft(){
        int M[][] = new int[DIMENSION][DIMENSION];
        int x =0;
        for(int j = DIMENSION -1; j>= 0; j--) {
            int y = 0;
            for (int i = 0; i < DIMENSION; i++) {
                M[x][y] = matrix[i][j];
                ++y;
            }
            ++x;
        }
        matrix = M;
    }
    private void mover(){
        for(int i = 0; i< DIMENSION; i++){
            int last = matrix[i][0];
            int idx_last = 0;
            for(int j = 1; j< DIMENSION; j++){
                int curr = matrix[i][j];
                if(curr!=0){
                    //First Case only come here once.
                    if(last == 0){
                        matrix[i][idx_last] = curr;
                        matrix[i][j] = 0;
                        last = matrix[i][idx_last];
                    }
                    //The case when we add numbers
                    else if(last == curr) {
                        matrix[i][idx_last] = last + curr;
                        matrix[i][j] = 0;
                        last = matrix[i][idx_last];
                    }
                    //The case when only move the number
                    else{
                        matrix[i][idx_last+1] = curr;
                        if(idx_last+1 != j) matrix[i][j] = 0;
                        last = matrix[i][idx_last+1];
                        idx_last = idx_last+1;
                    }
                }
            }
        }
    }
    public void moverIzquierda(){
        String str_original = toString();
        mover();
        if(!str_original.equals(toString())) insertarNumeroDos();
        notificar();
    }
    public void moverArriba(){
        String str_original = toString();
        rotateLeft();
        mover();
        rotateRight();
        if(!str_original.equals(toString())) insertarNumeroDos();
        notificar();
    }
    public void moverDerecha(){
        String str_original = toString();
        rotateLeft();
        rotateLeft();
        mover();
        rotateRight();
        rotateRight();
        if(!str_original.equals(toString())) insertarNumeroDos();
        notificar();
    }
    public void moverAbajo(){
        String str_original = toString();
        rotateRight();
        mover();
        rotateLeft();
        if(!str_original.equals(toString())) insertarNumeroDos();
        notificar();
    }

    public String toString(){
        String ans = "";
        for(int i = 0; i< DIMENSION; i++){
            for(int j = 0; j< DIMENSION; j++) {
                if(i==0&&j==0) ans+=matrix[i][j];
                else if(j==0) ans+= "|" + matrix[i][j];
                else ans+= " " + matrix[i][j];
            }
        }
        return ans;
    }
    /*
    private boolean sePuedeMover(){
        //Verificamos si ya no se pueden hacer movimientos:
        Tablero dupTab = duplicate(this);
        String str = toString();
        dupTab.moverArriba();
        String strm = dupTab.toString();
        if(!str.equals(strm)) return true;
        dupTab.moverAbajo();
        strm = dupTab.toString();
        if(!str.equals(strm)) return true;
        dupTab.moverDerecha();
        strm = dupTab.toString();
        if(!str.equals(strm)) return true;
        dupTab.moverIzquierda();
        strm = dupTab.toString();
        if(!str.equals(strm)) return true;
        return false;
    }
    */
    //////////////////////////////////
    private boolean sePuedeMover(){
        for(int i = 0; i < DIMENSION; i++){
            for(int j = 0; j < DIMENSION; j++){
                if(matrix[i][j]==0) return true;
                if((j+1< DIMENSION && matrix[i][j]==matrix[i][j+1]) || (i+1< DIMENSION && matrix[i][j]==matrix[i+1][j])){
                    return true;
                }
            }
        }
        return false;
    }
    private String[][] tablerotoMatrix(){
        String columns[] = toString().split("\\|");
        String tableroMatrix[][] = new String[DIMENSION][DIMENSION];
        for(int i = 0; i<columns.length; i++){
            tableroMatrix[i] = columns[i].split(" ");
        }
        return tableroMatrix;
    }

    //private int LIMITE = 2048;
    private int LIMITE = 32;//16;
    private boolean alcanceLimite(){
        // una de las casillas contiene el valor LIMITE.
        for(int i = 0; i< DIMENSION; i++)
            for(int j = 0; j< DIMENSION; j++)
                if(matrix[i][j]==LIMITE) return true;
        return false;
    }

    private Estado estado(){
        if(terminado){
            return Estado.TERMINADO;
        }
        if(reiniciado) return Estado.REINICIADO;
        if(alcanceLimite()){
            partidasGanadas++;
            return Estado.GANADO;
        }
        if(!sePuedeMover()){
            return Estado.PERDIDO;
        }
        return Estado.EN_JUEGO;
    }

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    private void notificar(){
        Estado estadoA = estado();
        for(Observador observador : observadores){
            observador.actualizar(estadoA);
        }
    }
    public void terminar2048(){
        terminado = true;
        notificar();
    }
    public void reiniciar2048(){
        matrix = new int[DIMENSION][DIMENSION];
        reiniciado = true;
        terminado = false;
        insertarNumeroDos();
        notificar();
        reiniciado = false;
    }
    public int obtenerPuntaje(){
        return partidasGanadas;
    }
}

