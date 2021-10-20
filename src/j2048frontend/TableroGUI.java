package j2048frontend;
import j2048backend.Tablero;
import j2048backend.Estado;
import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;

public class TableroGUI implements ActionListener, IObservador {
    private Tablero tablero;
    private JFrame frame;

    private final int dimension = 4;
    private JLabel celdas[][] = new JLabel[dimension][dimension];

    private final int numDir = 4;
    private JButton dirButtons[] = new JButton[numDir];
    private JButton terminar;

    private JPanel panel;

    public TableroGUI(Tablero tablero){
        this.tablero = tablero;
        tablero.addObserver(this);
    }
    public void correr(){
        tablero.insertarNumeroDos();
        initGameFrame();
    }
    private void initGameFrame(){
        frame = new JFrame("2048 Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setResizable(false);
        frame.setLayout(null);
        //frame.getContentPane().setBackground(new Color(0xCDC1B4));

        //Etiquetamos
        //ImageIcon up = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/img/up-arrow2.png");
        ImageIcon up = new ImageIcon("src/j2048frontend/img/up-arrow2.png");
        dirButtons[0] = new JButton(up);
        //ImageIcon right = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/img");
        ImageIcon right = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/img/right-arrow2.png");
        dirButtons[1] = new JButton(right);
        ImageIcon down = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/img/down-arrow2.png");
        dirButtons[2] = new JButton(down);
        ImageIcon left = new ImageIcon("/home/korotkevich/IdeaProjects/EjercicioTDD/src/j2048frontend/img/left-arrow2.png");
        dirButtons[3] = new JButton(left);
        //Agregamos Listeners
        dirButtons[0].addActionListener(this);
        dirButtons[1].addActionListener(this);
        dirButtons[2].addActionListener(this);
        dirButtons[3].addActionListener(this);

        terminar = new JButton("Terminar");
        terminar.setBounds(225, 400, 150, 50);
        terminar.addActionListener(this);

        for(int i=0; i<numDir; i++){
            //dirButtons[i].setFont(myFont);
            dirButtons[i].setFocusable(false);
        }
        dirButtons[0].setBounds(250, 5, 100, 40);
        dirButtons[1].setBounds(470, 180, 100, 40);
        dirButtons[2].setBounds(250, 355, 100, 40);
        dirButtons[3].setBounds(20, 180, 105, 40);

        for (int i = 0; i < numDir; i++) frame.add(dirButtons[i]);

        //Creamos las celdas
        for(int i=0; i<dimension; i++)
            for(int j=0; j<dimension; j++){
                celdas[i][j] = new JLabel("", SwingConstants.CENTER);
            }
        frame.add(terminar);
        panel = new JPanel();
        panel.setBounds(150, 50, 300, 300);
        panel.setLayout(new GridLayout(dimension, dimension, 0, 0));
        panel.setBackground(new Color(0xCDC1B4));
        showFrame();
    }
    private void showFrame(){
        //Modificamos el tablero Despues de agregar elementos.
        //panel = new JPanel();

        //panel.setBounds(150, 50, 300, 300);
        //panel.setLayout(new GridLayout(dimension, dimension, 0, 0));
        //panel.setBackground(new Color(0xCDC1B4));

        String matrixCeldas[][] = toMatrix();
        updateCeldas(matrixCeldas);
        JLabel jl;
        for(int i=0; i<dimension; i++){
            for(int j=0; j<dimension; j++){
                jl = celdas[i][j];
                jl.setOpaque(true);
                jl.setBorder(BorderFactory.createMatteBorder(3,3, 3, 3, new Color(0xBBADA0)));
                //jl.setFont(new Font("Dialog", 1, 42));
                jl.setFont(new Font("Dialog", 1, 42));
                panel.add(jl);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
        Estado estadoActual = tablero.estado();
        switch(estadoActual){
            case PERDIDO:
                JOptionPane.showMessageDialog(null, "Perdiste!");
                restart();
                correr();
                break;
            case GANADO:
                JOptionPane.showMessageDialog(null, "Ganaste!");
                restart();
                correr();
                break;
            default: break;
        }
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
                        //celdas[i][j] = new JLabel(s, SwingConstants.CENTER);
                        celdas[i][j].setText(s);
                        celdas[i][j].setBackground(new Color(0xeee4da));
                        break;
                    case "4":
                        celdas[i][j].setText(s);
                        //celdas[i][j] = new JLabel(s, SwingConstants.CENTER);
                        celdas[i][j].setBackground(new Color(0xede0c8));
                        break;
                    case "8":
                        celdas[i][j].setText(s);
                        //celdas[i][j] = new JLabel(s, SwingConstants.CENTER);
                        celdas[i][j].setBackground(new Color(0xf2b179));
                        break;
                    case "16":
                        celdas[i][j].setText(s);
                        //celdas[i][j] = new JLabel(s, SwingConstants.CENTER);
                        celdas[i][j].setBackground(new Color(0xf59563));
                        break;
                    case "32":
                        celdas[i][j].setText(s);
                        //celdas[i][j] = new JLabel(s, SwingConstants.CENTER);
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
            //System.out.println("w");
            showFrame();
        }else if(actionEvent.getSource() == dirButtons[1]){
            tablero.moverDerecha();
            //System.out.println("d");
            showFrame();
        }else if(actionEvent.getSource() == dirButtons[2]){
            tablero.moverAbajo();
            //System.out.println("s");
            showFrame();
        }else if(actionEvent.getSource() == dirButtons[3]){
            tablero.moverIzquierda();
            //System.out.println("a");
            showFrame();
        }else if(actionEvent.getSource() == terminar){
            //System.out.println("Terminar");
            restart();
            correr();
        }
    }
    private void restart(){
        frame.setVisible(false);
        frame.dispose();
        celdas = new JLabel[dimension][dimension];
        tablero = new Tablero();
    }

    @Override
    public void update(Estado estado) {
        /*
        if(estado == Estado.CONTINUAR) {
            System.out.println("CONTINUAR GUI");
        }else if(estado == Estado.GANADO) {
            System.out.println("GANADO GUI");
        }else if(estado == Estado.PERDIDO) {
            System.out.println("PERDIDO GUI");
        }
         */
        switch(estado){
            case PERDIDO:
                JOptionPane.showMessageDialog(null, "Perdiste!");
                restart();
                correr();
                break;
            case GANADO:
                JOptionPane.showMessageDialog(null, "Ganaste!");
                restart();
                correr();
                break;
            default:
                showFrame();
                break;
        }
    }
}
