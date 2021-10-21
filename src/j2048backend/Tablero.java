package j2048backend;

import j2048frontend.ui.Observador;

import java.util.*;
public class Tablero {
    //Atributes:
    private final int dimension = 4;
    private int matrix[][] = new int [dimension][dimension];
    //List of Observers:
    private List<Observador> observadores = new ArrayList<Observador>();
    //Methods:
    private boolean isFull(){
        for(int i = 0; i<dimension; i++)
            for(int j = 0; j<dimension; j++)
                if(matrix[i][j]==0) return false;
        return true;
    }
    public boolean insertarNumeroDos(){
        Random rand = new Random();
        if(isFull()) return false;
        while(true){
            int i = rand.nextInt(dimension);
            int j = rand.nextInt(dimension);
            if (matrix[i][j] == 0) {
                matrix[i][j] = 2;
                return true;
            }
        }
    }
    //Rotate the matrix 90 degree clockwise:
    private void rotateRight(){
        int M[][] = new int[dimension][dimension];
        int x =0;
        for(int j = 0; j < dimension; j++){
            int y = 0;
            for(int i = dimension - 1; i >= 0; i--) {
                M[x][y] = matrix[i][j];
                ++y;
            }
            ++x;
        }
        matrix = M;
    }
    private void rotateLeft(){
        int M[][] = new int[dimension][dimension];
        int x =0;
        for(int j = dimension-1; j>= 0; j--) {
            int y = 0;
            for (int i = 0; i < dimension; i++) {
                M[x][y] = matrix[i][j];
                ++y;
            }
            ++x;
        }
        matrix = M;
    }
    private void mover(){
        for(int i=0; i<dimension; i++){
            int last = matrix[i][0];
            int idx_last = 0;
            for(int j=1; j<dimension; j++){
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
        for(int i=0; i<dimension; i++){
            for(int j=0;j<dimension; j++) {
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
    private boolean sePuedeMover(int [][] tab){
        for(int i = 0; i < dimension; i++){
            int[] filaTab = tab[i];
            for(int j = 0; j < dimension- 1; j ++){
                if (filaTab [j] == filaTab[j + 1]){
                    return false;
                }
            }
        }
        return true;
    }
    /*
    private boolean verificarAdyacenciaCol(){
        Tablero clonTablero = duplicate(this);
        for (int i = 0; i < dimension; i++) {
            clonTablero[i] = Arrays.copyOf(clonTablero[dimension - i - 1], dimension);
        }
        return verificarAdyacenciaFila(clonTablero);
    }
     */
    /////////////////////

    //private int LIMITE = 2048;
    private int LIMITE = 32;//16;

    private boolean alcanceLimite(){
        // una de las casillas contiene el valor LIMITE.
        for(int i=0; i<dimension; i++)
            for(int j=0; j<dimension; j++)
                if(matrix[i][j]==LIMITE) return true;
        return false;
    }

    public Estado estado(){
        if(alcanceLimite()){
            return Estado.GANADO;
        }
        //if(!sePuedeMover()){
        if(isFull()){
            return Estado.PERDIDO;
        }
        return Estado.CONTINUAR;
    }
    /*
    private int[][] duplicate(Tablero tablero){
        Tablero tab = new Tablero();
        int duplicateMatrix[][] = new int[dimension][dimension];
        for(int i = 0; i<tablero.dimension; i++){
            duplicateMatrix[i] = tablero.matrix[i].clone();
        }
        return tab;
    }
    */

    public void agregarObservador(Observador observador) {
        observadores.add(observador);
    }

    private void notificar(){
        Estado estadoA = estado();
        for(Observador observador : observadores){
            observador.actualizar(estadoA);
        }
    }

}

