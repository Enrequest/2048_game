package j2048frontend.gui;
import j2048backend.Tablero;
import j2048backend.Estado;
import j2048frontend.Observador;
import j2048frontend.TableroUI;

import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;

public class TableroGUI implements ActionListener, Observador, TableroUI {
    private Tablero tablero;
    private JFrame frame;

    private final int dimension = 4;
    private JLabel celdas[][] = new JLabel[dimension][dimension];

    private final int numDir = 4;
    private JButton dirButtons[] = new JButton[numDir];
    private JButton terminar;
    private JButton reiniciar;

    private JPanel panel;

    private JPanel panelEstado;
    private JLabel partidasGanadas;
    private JLabel estadoPartida;

    public TableroGUI(Tablero tablero){
        this.tablero = tablero;
        tablero.agregarObservador(this);
    }

    @Override
    public void correr(){
        initGameFrame();
    }
    private void createFrame(){
        //Creamos el frame principal
        frame = new JFrame("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLayout(null);

        //Creamos las etiquetas:
        ImageIcon up = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/gui/img/up-arrow2.png");
        dirButtons[0] = new JButton(up);
        ImageIcon right = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/gui/img/right-arrow2.png");
        dirButtons[1] = new JButton(right);
        ImageIcon down = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/gui/img/down-arrow2.png");
        dirButtons[2] = new JButton(down);
        ImageIcon left = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/gui/img/left-arrow2.png");
        dirButtons[3] = new JButton(left);

        dirButtons[0].setBounds(250, 5, 100, 40);
        dirButtons[1].setBounds(470, 180, 100, 40);
        dirButtons[2].setBounds(250, 355, 100, 40);
        dirButtons[3].setBounds(20, 180, 105, 40);

        //Agregamos Listeners
        dirButtons[0].addActionListener(this);
        dirButtons[1].addActionListener(this);
        dirButtons[2].addActionListener(this);
        dirButtons[3].addActionListener(this);

        for (int i = 0; i < numDir; i++) frame.add(dirButtons[i]);

        //Agregamos la ventana de mensajes.
        panelEstado = new JPanel();
        panelEstado.setBounds(150, 425, 300, 50);
        JLabel labelGanados = new JLabel("Ganados: ");
        panelEstado.add(labelGanados);
        partidasGanadas = new JLabel("0");
        panelEstado.add(partidasGanadas);
        estadoPartida = new JLabel("");
        panelEstado.add(estadoPartida);
        //panelEstado.setBackground(new Color(0xCDC1B4));
        frame.add(panelEstado);

        //Creamos el boton de Terminar
        terminar = new JButton("Terminar");
        //terminar.setBounds(225, 500, 150, 50);
        terminar.setBounds(150, 500, 150, 50);
        terminar.addActionListener(this);
        frame.add(terminar);
        //Creamos el boton de Reiniciar
        reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(310, 500, 150, 50);
        reiniciar.addActionListener(this);
        frame.add(reiniciar);
        /*
        for(int i=0; i<numDir; i++){
            dirButtons[i].setFocusable(false);
        }
         */
        createPanel();
        frame.setVisible(true);
    }
    private void createPanel(){
        panel = new JPanel();
        panel.setBounds(150, 50, 300, 300);
        panel.setLayout(new GridLayout(dimension, dimension, 0, 0));
        panel.setBackground(new Color(0xCDC1B4));
        //Creamos las celdas
        for(int i=0; i<dimension; i++)
            for(int j=0; j<dimension; j++){
                celdas[i][j] = new JLabel("", SwingConstants.CENTER);
                celdas[i][j].setOpaque(true);
                celdas[i][j].setBorder(BorderFactory.createMatteBorder(3,3, 3, 3, new Color(0xBBADA0)));
                celdas[i][j].setFont(new Font("Dialog", 1, 42));
                panel.add(celdas[i][j]);
            }
        frame.add(panel);
    }

    private void initGameFrame(){
        createFrame();
        showFrame();
    }

    private void showFrame(){
        String matrixCeldas[][] = toMatrix();
        updateCeldas(matrixCeldas);
    }

    private String[][] toMatrix(){
        String columns[] = tablero.toString().split("\\|");
        String matrixCeldas[][] = new String[dimension][dimension];
        for(int i = 0; i<columns.length; i++){
            matrixCeldas[i] = columns[i].split(" ");
        }
        return matrixCeldas;
    }

    private void updateCeldas(String matrixCeldas[][]){
        for(int i = 0; i<dimension; i++){
            for(int j = 0; j<dimension; j++){
                String s = matrixCeldas[i][j];
                switch (s){
                    case "0":
                        celdas[i][j].setText("");
                        celdas[i][j].setBackground(new Color(0xCDC1B4));
                        break;
                    case "2":
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xeee4da));
                        break;
                    case "4":
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xede0c8));
                        break;
                    case "8":
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xf2b179));
                        break;
                    case "16":
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xf59563));
                        break;
                    case "32":
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xf67c5f));
                        break;
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == dirButtons[0]){
            tablero.moverArriba();
        }else if(actionEvent.getSource() == dirButtons[1]){
            tablero.moverDerecha();
        }else if(actionEvent.getSource() == dirButtons[2]){
            tablero.moverAbajo();
        }else if(actionEvent.getSource() == dirButtons[3]){
            tablero.moverIzquierda();
        }else if(actionEvent.getSource() == terminar){
            tablero.terminar2048();
        }else if(actionEvent.getSource() == reiniciar){
            tablero.reiniciar2048();
        }
    }
    @Override
    public void actualizar(Estado estado) {
        switch(estado){
            case PERDIDO:
                bloquear();
                mostrarMensajePerdido();
                showFrame();
                break;
            case GANADO:
                bloquear();
                mostrarMensajeGanado();
                mostrarGanados();
                showFrame();
                break;
            case TERMINADO:
                acabar();
                break;
            case REINICIADO:
                desbloquear();
                mostrarMensajeVacio();
                showFrame();
                break;
            default:
                mostrarMensajeVacio();
                showFrame();
                break;
        }
    }
    private void mostrarGanados(){
        partidasGanadas.setText(Integer.toString(tablero.obtenerPuntaje()));
    }
    private void mostrarMensajeGanado(){
        estadoPartida.setText("GANO LA PARTIDA");
    }
    private void mostrarMensajePerdido(){
        estadoPartida.setText("PERDIO LA PARTIDA");
    }
    private void mostrarMensajeVacio(){
        estadoPartida.setText("");
    }
    private void acabar(){
        frame.setVisible(false);
        frame.dispose();
    }
    private void desbloquear(){
        for(JButton buton : dirButtons){
            buton.setEnabled(true);
        }
    }
    private void bloquear(){
        for(JButton buton : dirButtons){
            buton.setEnabled(false);
        }
    }
}
