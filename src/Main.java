public class Main {
    public static void main(){
        Tablero tablero = new Tablero();
        TableroConsola consola = new TableroConsola(tablero);
        consola.correr();
    }
}