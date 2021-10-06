package j2048backend;

import java.util.*;
public class Tablero {
    //Atributes:
    private final int dimension = 4;
    private int matrix[][] = new int [dimension][dimension];
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
    public void rotateRight(){
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
    public void rotateLeft(){
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
    public boolean moverIzquierda(){
        mover();
        return insertarNumeroDos();
    }
    public boolean moverArriba(){
        rotateLeft();
        mover();
        rotateRight();
        return insertarNumeroDos();
    }
    public boolean moverDerecha(){
        rotateLeft();
        rotateLeft();
        mover();
        rotateRight();
        rotateRight();
        return insertarNumeroDos();
    }
    public boolean moverAbajo(){
        rotateRight();
        mover();
        rotateLeft();
        return insertarNumeroDos();
    }
    /*
    public void moverIzquierda(){
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
    */
    /*
    public void moverDerecha(){
        for(int i=0; i<dimension; i++){
            int last = matrix[i][dimension-1];
            int idx_last = dimension - 1;
            for(int j=dimension-2; j>=0; j--){
                int curr = matrix[i][j];
                if(curr!=0) {
                    //First Case only come here once.
                    if (last == 0) {
                        matrix[i][idx_last] = curr;
                        matrix[i][j] = 0;
                        last = matrix[i][idx_last];
                    }
                    //The case when we add numbers
                    else if (last == curr) {
                        matrix[i][idx_last] = last + curr;
                        matrix[i][j] = 0;
                        last = matrix[i][idx_last];
                    }
                    //The case when only move the number
                    else {
                        matrix[i][idx_last - 1] = curr;
                        if (idx_last - 1 != j) matrix[i][j] = 0;
                        last = matrix[i][idx_last - 1];
                        idx_last = idx_last - 1;
                    }
                }
            }
        }
    }
    */
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

    //private int LIMITE = 2048;
    private int LIMITE = 16;

    public boolean alcanceLimite(){
        // una de las casillas contiene el valor LIMITE.
        for(int i=0; i<dimension; i++)
            for(int j=0; j<dimension; j++)
                if(matrix[i][j]==LIMITE) return true;

        //Verificamos si ya no se pueden hacer movimientos:
        Tablero dupTab = duplicate(this);
        String str = toString();
        dupTab.moverArriba();
        String strm = dupTab.toString();
        if(!str.equals(strm)) return false;
        dupTab.moverAbajo();
        strm = dupTab.toString();
        if(!str.equals(strm)) return false;
        dupTab.moverDerecha();
        strm = dupTab.toString();
        if(!str.equals(strm)) return false;
        dupTab.moverIzquierda();
        strm = dupTab.toString();
        if(!str.equals(strm)) return false;
        return true;
    }
    private Tablero duplicate(Tablero tablero){
        Tablero tab = new Tablero();
        int M[][] = new int[dimension][dimension];
        for(int i = 0; i<tablero.dimension; i++){
            M[i] = tablero.matrix[i].clone();
        }
        return tab;
    }
}

