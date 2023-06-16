/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;

import Armas.*;
import Fabricas.Mina;
import General.IConstants;
import Islas.Grafo;
import Jugador.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
/**
 *
 * @author USUARIO
 */
public class EspacioDeJuego extends javax.swing.JFrame implements IConstants,Runnable{
    private JButton[][] btnsMapa1;
    private JButton[][] btnsMapa2;
    private JButton[][] btnsMapa3;
    private JButton[][] btnsMapa4;
    private JButton[][] btnsMapa5;
    private JButton[][] btnsMapaActual;
    private final int tamano = 30;
    ArrayList<Jugador> jugadores;
    private Thread miHilo;
    Jugador jugadorActual;
    int contadorJugador = 0;
    /**
     * Creates new form EspacioDeJuego
     * @param cantidadJugadores
     */
    public EspacioDeJuego(int cantidadJugadores) {
        
        jugadores = new ArrayList<Jugador>();
        
        for(int i=0;i<cantidadJugadores;i++){
            Jugador j0 = new Jugador(i);
            jugadores.add(j0);
        }
        
        jugadores.get(0).getMapa().agregarFuente(15,15);
        jugadores.get(0).getMapa().agregarFabrica(1,14,13,0,0);
        jugadores.get(0).getMapa().agregarFabrica(2,13,12,0,0);
        jugadores.get(0).getMapa().agregarFabrica(3,12,11,0,1);
        jugadores.get(0).getMapa().agregarFabrica(3,11,10,0,1);
        jugadores.get(0).getMapa().agregarFabrica(4,10,9,0,1);
        jugadores.get(0).getMapa().agregarFabrica(5,9,8,0,2);
        jugadores.get(0).getMapa().agregarFabrica(6,8,7,0,2);
        jugadores.get(0).getMapa().agregarFabrica(7,7,6,0,0);  
        jugadores.get(0).getMapa().agregarFabrica(8,3,1,0,3);
        jugadores.get(0).getMapa().agregarConector(1, 1);
        jugadores.get(0).getMapa().visibilizar();
        
        jugadores.get(1).getMapa().agregarFuente(15,15);
        jugadores.get(1).getMapa().agregarFabrica(1,14,13,0,0);
        jugadores.get(1).getMapa().agregarFabrica(2,13,12,0,0);
        jugadores.get(1).getMapa().agregarFabrica(3,12,11,0,1);
        jugadores.get(1).getMapa().agregarFabrica(3,11,10,0,1);
        jugadores.get(1).getMapa().agregarFabrica(4,10,9,0,1);
        jugadores.get(1).getMapa().agregarFabrica(5,9,8,0,2);
        jugadores.get(1).getMapa().agregarFabrica(6,8,7,0,2);
        jugadores.get(1).getMapa().agregarFabrica(7,7,6,0,0);
        jugadores.get(1).getMapa().agregarFabrica(8,15,0,0,2);
        jugadores.get(1).getMapa().agregarConector(15, 14);
        jugadores.get(1).getMapa().agregarConector(14, 12);
        
        jugadores.get(2).getMapa().agregarFuente(15,15);
        jugadores.get(2).getMapa().agregarFabrica(1,14,13,0,0);
        jugadores.get(2).getMapa().agregarFabrica(2,13,12,0,0);
        jugadores.get(2).getMapa().agregarFabrica(3,12,11,0,1);
        jugadores.get(2).getMapa().agregarFabrica(3,11,10,0,1);
        jugadores.get(2).getMapa().agregarFabrica(4,10,9,0,1);
        jugadores.get(2).getMapa().agregarFabrica(5,9,8,0,2);
        jugadores.get(2).getMapa().agregarFabrica(6,8,7,0,2);
        jugadores.get(2).getMapa().agregarFabrica(7,7,6,0,0); 
        jugadores.get(2).getMapa().agregarFabrica(8,18,18,0,1);
        jugadores.get(2).getMapa().agregarConector(15, 14);
        jugadores.get(2).getMapa().agregarConector(14, 12);
        jugadores.get(2).getMapa().agregarConector(13, 11);
        
        generarRemolinos();
        
        btnsMapa1 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa2 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa3 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa4 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa5 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        initComponents();
        switch(cantidadJugadores){
            case 1: 
                initMapa();
                break;
            case 2:  
                initMapa();
                initMapaEnemigo1();
                break;
            case 3: 
                initMapa();
                initMapaEnemigo1();
                initMapaEnemigo2();
                break;
            case 4: 
                initMapa();
                initMapaEnemigo1();
                initMapaEnemigo2();
                initMapaEnemigo3();
                break;
            case 5:
                initMapa();
                initMapaEnemigo1();
                initMapaEnemigo2();
                initMapaEnemigo3();
                initMapaEnemigo4();
                break;
            default:
                break;
        }
        
        
        int fontSize = 16; // Tamaño de la fuente
        String fontName = "Open Sans"; // Nombre de la fuente

        // Crea el objeto Font con el tamaño y la fuente recomendados
        Font font = new Font(fontName, Font.PLAIN, fontSize);

        // Asigna la fuente al JLabel lbl_Sucesos
        btnsMapaActual = btnsMapa1;
        
        miHilo = new Thread(this);
            miHilo.start();
            
        jTextArea_sucesos.setText("Bienvenido");
        jugadorActual = jugadores.get(0);
    }

    
    
    public boolean agregar(Jugador j, int x, int y, int orientacion, int tipoFabrica){
        boolean resultado;
        switch(tipoFabrica){ //Mina(0), Armeria(1), TemploBrujas(2)
            case 0: //Fuente de poder
                if(j.getDinero()-PRECIO_FUENTE_ENERGIA>0){
                    if(j.getMapa().getVertices().get(0).getFabrica() != null){
                        j.setDinero(j.getDinero()-PRECIO_FUENTE_ENERGIA);
                        j.getMapa().agregarFuente(x, y);
                        resultado = true;
                        colorearMapa(j.getMapa(),btnsMapa1);
                        jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado una nueva fuente de poder al jugador "+jugadorActual.getId());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Ya hay una fuente de poder", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                        resultado = false;
                    }
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            case 1:  //Mina
                if(j.getDinero()-PRECIO_MINA>0){
                    resultado = j.getMapa().agregarFabrica(j.getNumeroComponente(), x, y, orientacion, 0); 
                    colorearMapa(j.getMapa(),btnsMapa1);
                    jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado una nueva mina al jugador "+jugadorActual.getId());
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            case 2:  //Armeria
                if(j.getDinero()-PRECIO_ARMERIA>0){
                    resultado = j.getMapa().agregarFabrica(j.getNumeroComponente(), x, y, orientacion, 1);
                    colorearMapa(j.getMapa(),btnsMapa1);
                    jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado una nueva armeria al jugador "+jugadorActual.getId());
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            case 3: //Fabrica de brujas
                if(j.getDinero()-PRECIO_TEMPLO_BRUJA>0){
                    resultado = j.getMapa().agregarFabrica(j.getNumeroComponente(), x, y, orientacion, 2);
                    colorearMapa(j.getMapa(),btnsMapa1);
                    jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado un nuevo templo de brujas al jugador "+jugadorActual.getId());
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            case 4:
                if(j.getDinero()-PRECIO_MERCADO>0){
                    resultado = j.getMapa().agregarFabrica(j.getNumeroComponente(), x, y, orientacion, 3);
                    colorearMapa(j.getMapa(),btnsMapa1);
                    jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado un nuevo mercado al jugador "+jugadorActual.getId());
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            case 5: //Conector
                if(j.getDinero()-PRECIO_CONECTORES>0){
                    resultado = j.getMapa().agregarConector(x, y);
                    colorearMapa(j.getMapa(),btnsMapa1);
                    jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nSe ha agregado un nuevo conector al jugador "+jugadorActual.getId());
                }else{
                    JOptionPane.showMessageDialog(null
                            , "No tiene dinero suficiente para realizar la transacción"
                            , "Error", JOptionPane.ERROR_MESSAGE);
                    resultado = false;
                }
                break;
            default:
                resultado = false;
                break;
        }
        System.out.println( j.getMapa().imprimirMatriz3());
        //JOptionPane.showMessageDialog(null, j.getMapa().imprimirMatriz3(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        return resultado;
    }
    
    public boolean agregarConector(Jugador j,int x, int y){
        return j.getMapa().agregarConector(x,y);
    }
    
    public void colorearMapa(Grafo g, JButton[][] btns){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                switch (g.matriz[j][i]) {
                    case 0:
                        btns[i][j].setBackground(Color.LIGHT_GRAY);
                        break;
                    case CODIGO_FUENTE:
                        //Amarillo Fuente //Azul Conector //Anaranjado mina //Magenta Bruja //Plateado armeria //Rojo bala
                        btns[i][j].setBackground(Color.YELLOW);
                        break;
                    case CODIGO_CONECTOR:
                        btns[i][j].setBackground(Color.CYAN);
                        break;
                    case CODIGO_ARMERIA:
                        btns[i][j].setBackground(Color.DARK_GRAY);
                        break;
                    case CODIGO_MINA:
                        btns[i][j].setBackground(Color.ORANGE);
                        break;
                    case CODIGO_MERCADO:  
                        btns[i][j].setBackground(Color.BLACK);
                        break;         
                    case CODIGO_TEMPLO_BRUJA:
                        btns[i][j].setBackground(Color.MAGENTA);
                        break;
                    case CODIGO_DISPARO:  
                        btns[i][j].setBackground(Color.RED);
                        break;
                    case CODIGO_REMOLINO:  
                        btns[i][j].setBackground(Color.BLUE);
                        break;  
                    default:
                        break;
                }
                
                
            }
        }
    }
    
    public void colorearMapa2(Grafo g, JButton[][] btns){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                switch (g.retornarMatrizVisible()[j][i]) {
                    case 0:
                        btns[i][j].setBackground(Color.LIGHT_GRAY);
                        break;
                    case CODIGO_FUENTE:
                        //Amarillo Fuente //Azul Conector //Anaranjado mina //Magenta Bruja //Plateado armeria //Rojo bala
                        btns[i][j].setBackground(Color.YELLOW);
                        break;
                    case CODIGO_CONECTOR:
                        btns[i][j].setBackground(Color.CYAN);
                        break;
                    case CODIGO_ARMERIA:
                        btns[i][j].setBackground(Color.DARK_GRAY);
                        break;
                    case CODIGO_MINA:
                        btns[i][j].setBackground(Color.ORANGE);
                        break;
                    case CODIGO_TEMPLO_BRUJA:
                        btns[i][j].setBackground(Color.MAGENTA);
                        break;
                    case CODIGO_DISPARO:  
                        btns[i][j].setBackground(Color.RED);
                        break;
                    case CODIGO_REMOLINO:  
                        btns[i][j].setBackground(Color.BLUE);
                        break;  
                    case CODIGO_MERCADO:  
                        btns[i][j].setBackground(Color.BLACK);
                        break;      
                    default:
                        break;
                }
                
                
            }
        }
    }
    
    public void generarRemolinos(){
        Random random = new Random();
        int x;
        int y;
        for(Jugador j: jugadores){
            int contador = 0;
            do{
                x = random.nextInt(20);
                y = random.nextInt(20);
                if(j.getMapa().verificarEspacioRemolino(x, y)){
                    j.getMapa().matriz[x][y] = CODIGO_REMOLINO;
                    contador++;
                }    
            }while(contador<2);
        }
    }
    
    public void initMapa(){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                Point referencia = new Point((int)puntoInicial.getX() + ((tamano+1)*j), (int)puntoInicial.getY() + ((tamano+1)*i));
                JButton btn = new JButton();
                panelMapa.add(btn);
                btn.setLocation(referencia);
                btn.setSize(tamano,tamano);
                btnsMapa1[i][j] = btn;
                //btn.addActionListener(new MapActionListener(this, j, i));
                //if (i < 2 || i > 22 || j < 2 || j > 22)
                    //btn.setEnabled(false);
                    
                    
            }
        }
        colorearMapa(jugadores.get(0).getMapa(),btnsMapa1);
    }
    
    public void initMapaEnemigo1(){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                Point referencia = new Point((int)puntoInicial.getX() + ((tamano+1)*j), (int)puntoInicial.getY() + ((tamano+1)*i));
                JButton btn = new JButton();
                panelMapaEnemigo1.add(btn);
                btn.setLocation(referencia);
                btn.setSize(tamano,tamano);
                btnsMapa2[i][j] = btn;
                //btn.addActionListener(new MapActionListener(this, j, i));
                //if (i < 2 || i > 22 || j < 2 || j > 22)
                    //btn.setEnabled(false);
            }
        }
        colorearMapa2(jugadores.get(1).getMapa(),btnsMapa2);
    }
    
    public void initMapaEnemigo2(){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                Point referencia = new Point((int)puntoInicial.getX() + ((tamano+1)*j), (int)puntoInicial.getY() + ((tamano+1)*i));
                JButton btn = new JButton();
                panelMapaEnemigo2.add(btn);
                btn.setLocation(referencia);
                btn.setSize(tamano,tamano);
                btn.setBackground(Color.red);
                btnsMapa3[i][j] = btn;
                //btn.addActionListener(new MapActionListener(this, j, i));
                //if (i < 2 || i > 22 || j < 2 || j > 22)
                    //btn.setEnabled(false);
            }
        }
        colorearMapa2(jugadores.get(2).getMapa(),btnsMapa3);
    }
    
    public void initMapaEnemigo3(){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                Point referencia = new Point((int)puntoInicial.getX() + ((tamano+1)*j), (int)puntoInicial.getY() + ((tamano+1)*i));
                JButton btn = new JButton();
                panelMapaEnemigo3.add(btn);
                btn.setLocation(referencia);
                btn.setSize(tamano,tamano);
                btn.setBackground(Color.BLUE);
                btnsMapa4[i][j] = btn;
                //btn.addActionListener(new MapActionListener(this, j, i));
                //if (i < 2 || i > 22 || j < 2 || j > 22)
                    //btn.setEnabled(false);
            }
        }
        colorearMapa2(jugadores.get(3).getMapa(),btnsMapa4);
    }
    
    public void initMapaEnemigo4(){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                Point referencia = new Point((int)puntoInicial.getX() + ((tamano+1)*j), (int)puntoInicial.getY() + ((tamano+1)*i));
                JButton btn = new JButton();
                panelMapaEnemigo4.add(btn);
                btn.setLocation(referencia);
                btn.setSize(tamano,tamano);
                btn.setBackground(Color.GREEN);
                btnsMapa5[i][j] = btn;
                //btn.addActionListener(new MapActionListener(this, j, i));
                //if (i < 2 || i > 22 || j < 2 || j > 22)
                    //btn.setEnabled(false);
            }
        }
        colorearMapa2(jugadores.get(4).getMapa(),btnsMapa5);
    }
    
    public void colorearTodos(){
        switch(jugadores.size()){
            case 1:
                colorearMapa(jugadorActual.getMapa(), btnsMapa1);
            case 2:
                colorearMapa(jugadorActual.getMapa(), btnsMapa1);
                colorearMapa2(jugadores.get((contadorJugador+1)% jugadores.size()).getMapa(), btnsMapa2);
                break;
            case 3:
                colorearMapa(jugadorActual.getMapa(), btnsMapa1);
                colorearMapa2(jugadores.get((contadorJugador+1)% jugadores.size()).getMapa(), btnsMapa2);
                colorearMapa2(jugadores.get((contadorJugador+2)% jugadores.size()).getMapa(), btnsMapa3);
                break;
            case 4:
                colorearMapa(jugadorActual.getMapa(), btnsMapa1);
                colorearMapa2(jugadores.get((contadorJugador+1)% jugadores.size()).getMapa(), btnsMapa2);
                colorearMapa2(jugadores.get((contadorJugador+2)% jugadores.size()).getMapa(), btnsMapa3);
                colorearMapa2(jugadores.get((contadorJugador+3)% jugadores.size()).getMapa(), btnsMapa4);
                break;
            case 5:
                colorearMapa(jugadorActual.getMapa(), btnsMapa1);
                colorearMapa2(jugadores.get((contadorJugador+1)% jugadores.size()).getMapa(), btnsMapa2);
                colorearMapa2(jugadores.get((contadorJugador+2)% jugadores.size()).getMapa(), btnsMapa3);
                colorearMapa2(jugadores.get((contadorJugador+3)% jugadores.size()).getMapa(), btnsMapa4);
                colorearMapa2(jugadores.get((contadorJugador+4)% jugadores.size()).getMapa(), btnsMapa5);
                break;
            default:
                break;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMapa = new javax.swing.JPanel();
        panelChat = new javax.swing.JPanel();
        jTextFieldMensaje = new javax.swing.JTextField();
        btn_Send = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_chat = new javax.swing.JTextArea();
        panelSucesos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_sucesos = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMapaEnemigo1 = new javax.swing.JPanel();
        cmbComprarFabrica = new javax.swing.JComboBox<>();
        panelMapaEnemigo2 = new javax.swing.JPanel();
        panelMapaEnemigo3 = new javax.swing.JPanel();
        panelMapaEnemigo4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbPosXAtaque = new javax.swing.JComboBox<>();
        cmbPosYAtaque = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbTipoAtaque = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnAtacar = new javax.swing.JButton();
        cmbOrientacionAtaque = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cmbComprarFabrica1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbPosXFabrica = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbPosYFabricas = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cmbOrientacionFabrica = new javax.swing.JComboBox<>();
        btnComprarAgregarFabrica = new javax.swing.JButton();
        btn_Turno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMapa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextFieldMensaje.setText("Escriba su mensaje");
        jTextFieldMensaje.setName(""); // NOI18N
        jTextFieldMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldMensajeActionPerformed(evt);
            }
        });

        btn_Send.setBackground(new java.awt.Color(102, 102, 255));
        btn_Send.setForeground(new java.awt.Color(255, 255, 255));
        btn_Send.setText("SEND");
        btn_Send.setActionCommand("");
        btn_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SendActionPerformed(evt);
            }
        });

        jTextArea_chat.setColumns(20);
        jTextArea_chat.setRows(5);
        jScrollPane1.setViewportView(jTextArea_chat);

        javax.swing.GroupLayout panelChatLayout = new javax.swing.GroupLayout(panelChat);
        panelChat.setLayout(panelChatLayout);
        panelChatLayout.setHorizontalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(panelChatLayout.createSequentialGroup()
                        .addComponent(jTextFieldMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Send)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelChatLayout.setVerticalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelSucesos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea_sucesos.setColumns(20);
        jTextArea_sucesos.setRows(5);
        jScrollPane2.setViewportView(jTextArea_sucesos);

        javax.swing.GroupLayout panelSucesosLayout = new javax.swing.GroupLayout(panelSucesos);
        panelSucesos.setLayout(panelSucesosLayout);
        panelSucesosLayout.setHorizontalGroup(
            panelSucesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSucesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSucesosLayout.setVerticalGroup(
            panelSucesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSucesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("My Sea");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel2.setText("Enemy");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbComprarFabrica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mina ($1000)", "Templo de la Bruja ($2500)", "Armeria ($1500)" }));
        cmbComprarFabrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbComprarFabricaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMapaEnemigo1Layout = new javax.swing.GroupLayout(panelMapaEnemigo1);
        panelMapaEnemigo1.setLayout(panelMapaEnemigo1Layout);
        panelMapaEnemigo1Layout.setHorizontalGroup(
            panelMapaEnemigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMapaEnemigo1Layout.createSequentialGroup()
                .addGap(701, 701, 701)
                .addComponent(cmbComprarFabrica, 0, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMapaEnemigo1Layout.setVerticalGroup(
            panelMapaEnemigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMapaEnemigo1Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(cmbComprarFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Enemy P1", panelMapaEnemigo1);

        javax.swing.GroupLayout panelMapaEnemigo2Layout = new javax.swing.GroupLayout(panelMapaEnemigo2);
        panelMapaEnemigo2.setLayout(panelMapaEnemigo2Layout);
        panelMapaEnemigo2Layout.setHorizontalGroup(
            panelMapaEnemigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelMapaEnemigo2Layout.setVerticalGroup(
            panelMapaEnemigo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Enemy P2", panelMapaEnemigo2);

        javax.swing.GroupLayout panelMapaEnemigo3Layout = new javax.swing.GroupLayout(panelMapaEnemigo3);
        panelMapaEnemigo3.setLayout(panelMapaEnemigo3Layout);
        panelMapaEnemigo3Layout.setHorizontalGroup(
            panelMapaEnemigo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelMapaEnemigo3Layout.setVerticalGroup(
            panelMapaEnemigo3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Enemy P3", panelMapaEnemigo3);

        javax.swing.GroupLayout panelMapaEnemigo4Layout = new javax.swing.GroupLayout(panelMapaEnemigo4);
        panelMapaEnemigo4.setLayout(panelMapaEnemigo4Layout);
        panelMapaEnemigo4Layout.setHorizontalGroup(
            panelMapaEnemigo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelMapaEnemigo4Layout.setVerticalGroup(
            panelMapaEnemigo4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Enemy P4", panelMapaEnemigo4);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Al ataque!");

        cmbPosXAtaque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));

        cmbPosYAtaque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seleccione las posiciones \"X\" y \"Y\", en las que desea atacar ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Y");

        cmbTipoAtaque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cañon", "Cañon Multiple", "Bomba", "Cañon Barba Roja" }));
        cmbTipoAtaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoAtaqueActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Seleccione el tipo de ataque");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("orientacion del ataque (si es bomba)");

        btnAtacar.setBackground(new java.awt.Color(255, 51, 51));
        btnAtacar.setText("ATACAR");
        btnAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtacarActionPerformed(evt);
            }
        });

        cmbOrientacionAtaque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vertical", "Horizontal" }));
        cmbOrientacionAtaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrientacionAtaqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPosXAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(161, 161, 161)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPosYAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(276, 276, 276)
                                .addComponent(cmbTipoAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 191, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbOrientacionAtaque, 0, 127, Short.MAX_VALUE)
                    .addComponent(btnAtacar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(277, 277, 277))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPosXAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPosYAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(32, 32, 32)
                .addComponent(jLabel7)
                .addGap(29, 29, 29)
                .addComponent(cmbTipoAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(cmbOrientacionAtaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Seleccione la fabrica que desea comprar y agregar al mapa");

        cmbComprarFabrica1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fuente de poder ($12000)", "Mina ($1000)", "Templo de la Bruja ($2500)", "Armeria ($1500)", "Mercado ($2000)", "Conector ($100)" }));
        cmbComprarFabrica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbComprarFabrica1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Seleccione la coordenadas \"X\" y \"Y\" donde desea ubicar la fabrica seleccionada");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("X");

        cmbPosXFabrica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Y");

        cmbPosYFabricas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19" }));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Seleccione la orientacion de la fabrica ");

        cmbOrientacionFabrica.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vertical", "Horizontal" }));

        btnComprarAgregarFabrica.setBackground(new java.awt.Color(51, 51, 255));
        btnComprarAgregarFabrica.setForeground(new java.awt.Color(255, 255, 255));
        btnComprarAgregarFabrica.setText("Comprar y agregar al mapa");
        btnComprarAgregarFabrica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnComprarAgregarFabricaMouseClicked(evt);
            }
        });
        btnComprarAgregarFabrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComprarAgregarFabricaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmbComprarFabrica1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(263, 263, 263))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbPosYFabricas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPosXFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(256, 256, 256))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbOrientacionFabrica, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnComprarAgregarFabrica, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbComprarFabrica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbPosXFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(cmbPosYFabricas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(cmbOrientacionFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnComprarAgregarFabrica, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btn_Turno.setBackground(new java.awt.Color(51, 51, 255));
        btn_Turno.setForeground(new java.awt.Color(255, 255, 255));
        btn_Turno.setText("SIGUIENTE");
        btn_Turno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TurnoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelMapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelSucesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(664, 664, 664)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btn_Turno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_Turno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTabbedPane1))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelSucesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int convertirOpcionTIpoAtaque(){
        String ataque = cmbTipoAtaque.getSelectedItem().toString();
        if (ataque == "Cañon"){
            return 0;
        }
        if (ataque == "Cañon Multiple"){
            return 1;
        }
        if (ataque == "Bomba"){
            return 2;
        }
        if (ataque == "Cañon Barba Roja"){
            return 3;
        }
        return -1;
    }
    
    private void btnAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtacarActionPerformed
        int tipoAtaque = convertirOpcionTIpoAtaque();
        Arma arma = FactoryArmas.generaArma(tipoAtaque);
        arma.atacar(jugadores.get(0), jugadores.get(1));
        colorearTodos();
    }//GEN-LAST:event_btnAtacarActionPerformed

    private void btn_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SendActionPerformed
        String mensaje = jTextFieldMensaje.getText();
        if(!mensaje.isEmpty()){
            jTextArea_chat.setText(jTextArea_chat.getText()+"\n"+"Jugador "+jugadorActual.getId()+": "+mensaje);
            jTextFieldMensaje.setText("");
        }
    }//GEN-LAST:event_btn_SendActionPerformed

    private void jTextFieldMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldMensajeActionPerformed
         
    }//GEN-LAST:event_jTextFieldMensajeActionPerformed

    private void cmbComprarFabricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComprarFabricaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbComprarFabricaActionPerformed

    private void cmbComprarFabrica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbComprarFabrica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbComprarFabrica1ActionPerformed

    private void btnComprarAgregarFabricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComprarAgregarFabricaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnComprarAgregarFabricaActionPerformed

    private void btnComprarAgregarFabricaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnComprarAgregarFabricaMouseClicked
        if(!agregar(jugadores.get(0),cmbPosXFabrica.getSelectedIndex(),
                cmbPosYFabricas.getSelectedIndex(),cmbOrientacionFabrica.getSelectedIndex(),
                cmbComprarFabrica1.getSelectedIndex() ) ){
            JOptionPane.showMessageDialog(null, "No se pudo agregar el componente solicitado, revise la ubicación y orientación elegida", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnComprarAgregarFabricaMouseClicked

    private void cmbTipoAtaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoAtaqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoAtaqueActionPerformed

    private void cmbOrientacionAtaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrientacionAtaqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOrientacionAtaqueActionPerformed

    private void btn_TurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TurnoActionPerformed
        // TODO add your handling code here:   
        jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nTerminó el turno del jugador "+jugadorActual.getId());
        contadorJugador++;
        contadorJugador = (contadorJugador) % jugadores.size();
        jugadorActual = jugadores.get(contadorJugador);
        jTextArea_sucesos.setText(jTextArea_sucesos.getText()+"\nComienza el turno del jugador "+jugadorActual.getId());
        colorearTodos();
       
    }//GEN-LAST:event_btn_TurnoActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EspacioDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EspacioDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EspacioDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EspacioDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EspacioDeJuego(3).setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtacar;
    private javax.swing.JButton btnComprarAgregarFabrica;
    private javax.swing.JButton btn_Send;
    private javax.swing.JButton btn_Turno;
    private javax.swing.JComboBox<String> cmbComprarFabrica;
    private javax.swing.JComboBox<String> cmbComprarFabrica1;
    private javax.swing.JComboBox<String> cmbOrientacionAtaque;
    private javax.swing.JComboBox<String> cmbOrientacionFabrica;
    private javax.swing.JComboBox<String> cmbPosXAtaque;
    private javax.swing.JComboBox<String> cmbPosXFabrica;
    private javax.swing.JComboBox<String> cmbPosYAtaque;
    private javax.swing.JComboBox<String> cmbPosYFabricas;
    private javax.swing.JComboBox<String> cmbTipoAtaque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea_chat;
    private javax.swing.JTextArea jTextArea_sucesos;
    private javax.swing.JTextField jTextFieldMensaje;
    private javax.swing.JPanel panelChat;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JPanel panelMapaEnemigo1;
    private javax.swing.JPanel panelMapaEnemigo2;
    private javax.swing.JPanel panelMapaEnemigo3;
    private javax.swing.JPanel panelMapaEnemigo4;
    private javax.swing.JPanel panelSucesos;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(true){
            int contadorMuertos = 0;
            for(Jugador j:jugadores){
                j.recoger();
                j.getMapa().verificarFabricaDisparo();
                j.getMapa().verificarConectorDisparo();
                j.getMapa().verificarFuenteDisparo();
                if(j.getMapa().finalizar()){
                    j.setVivo(false);
                }
                if(!j.getVivo()){
                    contadorMuertos++;
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mina.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(contadorMuertos==jugadores.size()-1){
                
                JOptionPane.showMessageDialog(null, "Felicidades jugador: "+jugadores.stream().filter(p->p.getVivo()==true).toList().get(0).getId()+" has ganado.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
