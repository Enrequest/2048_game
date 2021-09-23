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
            if (matrix[i][j] != 2) {
                matrix[i][j] = 2;
                return true;
            }
        }
    }
    /*
    public void print(){
            for(int i=0; i<dimension; i++) {
                for (int j = 0; j < dimension; j++) {
                    System.out.print(matrix[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("==========================");
    }
    */
    public void moverIzquierda(){
        for(int i=0; i<dimension; i++){
            int k;
            for(k=0; k<dimension; k++){
                if(matrix[i][k]==2) break;
            }
            for(int j = k; j<dimension; j++){
                matrix[i][j-k] = matrix[i][j];
                if((j-k)!=j) matrix[i][j] = 0;
            }
        }
    }
    public void moverDerecha(){
        for(int i=0; i<dimension; i++){
            int k;
            for(k=dimension-1; k>=0; k--){
                if(matrix[i][k]==2) break;
            }
            int dif = (dimension - 1) - k;
            for(int j = k; j>=0; j--){
                matrix[i][j + dif] = matrix[i][j];
                if((j+dif)!=j) matrix[i][j] = 0;
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
}

