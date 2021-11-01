package j2048frontend.consola;

import java.io.BufferedReader;
import java.util.*;
import j2048backend.Tablero;
import j2048backend.Estado;
import j2048frontend.Observador;
import j2048frontend.TableroUI;
import java.io.*;

public class TableroConsola implements Observador, TableroUI {
    private Tablero tablero;
    private boolean continuar;
    private BufferedReader bf ;
    private boolean bloqueado;
    //private Observador observable;
    public TableroConsola(Tablero tablero) {
        this.tablero = tablero;
        tablero.agregarObservador(this);
        bf = new BufferedReader(new InputStreamReader(System.in));
        continuar = true;
        bloqueado = false;
    }
    @Override
    public void correr() {
        printTablero(tablero);
        printMessage();
        while (continuar) {
            try {
                if(bf.ready()) {
                    String inputCase = bf.readLine();
                    if(!bloqueado || inputCase.equals("r") || inputCase.equals("q")) {
                        if (inputCase.equals("w")) tablero.moverArriba();
                        else if (inputCase.equals("a")) tablero.moverIzquierda();
                        else if (inputCase.equals("s")) tablero.moverAbajo();
                        else if (inputCase.equals("d")) tablero.moverDerecha();
                        else if (inputCase.equals("q")) tablero.terminar2048();
                        else if (inputCase.equals("r")) tablero.reiniciar2048();
                        else System.out.println("Ingrese un comando valido!!!");
                    }
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    private void printMessage() {
        System.out.println("Ingrese comando a ejecutar:");
        System.out.println("w = arriba; a = izquierda; s = abajo; d = derecha; r = reiniciar; q = terminar");
    }
    private void mostrarGanados(){
        System.out.println("GANADOS: " + tablero.obtenerPuntaje());
    }
    private void printTablero(Tablero tablero) {
        String strTab = tablero.toString();
        String[] parts = strTab.split("\\|");
        String partsFinal[][] = new String[parts.length][];
        for(int i = 0; i < parts.length; i++){
            partsFinal[i] = parts[i].split(" ");
        }
        int dimension = partsFinal.length;
        for (int i = 0; i < dimension; i++) {
            System.out.print("[");
            for(int j = 0; j < dimension; j++) {
                int tamCelda = 5;
                int diff = tamCelda - partsFinal[i][j].length();
                for(int k = 0; k < tamCelda; k++){
                    if(k<diff)
                        System.out.print(" ");
                    else {
                        System.out.print(partsFinal[i][j]);
                        break;
                    }
                }
            }
            System.out.println("]");
        }
    }
    @Override
    public void actualizar(Estado estado) {
        switch(estado){
            case PERDIDO:
                bloquear();
                printTablero(tablero);
                mostrarGanados();
                //printMessage();
                System.out.println("PERDIO LA PARTIDA!");
                break;
            case GANADO:
                bloquear();
                printTablero(tablero);
                mostrarGanados();
                //printMessage();
                System.out.println("GANO LA PARTIDA!");
                break;
            case TERMINADO:
                acabar();
                break;
            case REINICIADO:
                desbloquear();
                printTablero(tablero);
                mostrarGanados();
                printMessage();
                break;
            default:
                printTablero(tablero);
                mostrarGanados();
                printMessage();
                break;
        }
    }
    private void bloquear(){
        bloqueado = true;
    }
    private void desbloquear(){
        bloqueado = false;
    }
    private void acabar(){
         continuar = false;
    }

}
