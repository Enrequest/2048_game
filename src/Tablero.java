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
    public void moverArriba(){

    }
    public void moverAbajo(){

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

    //private int LIMITE = 2048;
    private int LIMITE = 28;

    public boolean alcanceLimite(){
        // una de las casillas contiene el valo LIMITE.
        return false;
    }
}

