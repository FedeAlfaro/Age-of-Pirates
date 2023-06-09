/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;


import General.IConstants;
import Islas.Grafo;
import Jugador.Jugador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
/**
 *
 * @author USUARIO
 */
public class EspacioDeJuego extends javax.swing.JFrame implements IConstants{
    private JButton[][] btnsMapa1;
    private JButton[][] btnsMapa2;
    private JButton[][] btnsMapa3;
    private JButton[][] btnsMapa4;
    private JButton[][] btnsMapa5;
    private final int tamano = 30;
    ArrayList<Jugador> jugadores;
    //Grafo g = new Grafo();
    
    /**
     * Creates new form EspacioDeJuego
     */
    public EspacioDeJuego() {
        jugadores = new ArrayList<Jugador>();
        
        Jugador j0 = new Jugador(0);
        Jugador j1 = new Jugador(1);
        Jugador j2 = new Jugador(2);
        Jugador j3 = new Jugador(3);
        Jugador j4 = new Jugador(4);
        
        j1.getMapa().agregarFuente(0,15,15);
        j1.getMapa().agregarFabrica(1,14,13,0,0);
        j1.getMapa().agregarFabrica(2,13,12,0,0);
        j1.getMapa().agregarFabrica(3,12,11,0,1);
        j1.getMapa().agregarFabrica(3,11,10,0,1);
        j1.getMapa().agregarFabrica(4,10,9,0,1);
        j1.getMapa().agregarFabrica(5,9,8,0,2);
        j1.getMapa().agregarFabrica(6,8,7,0,2);
        j1.getMapa().agregarFabrica(7,7,6,0,0);   
        
        jugadores.add(j0);
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        jugadores.add(j4);
        
        generarRemolinos();
        
        btnsMapa1 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa2 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa3 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa4 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        btnsMapa5 = new JButton[TAMANO_MATRIZ][TAMANO_MATRIZ];
        initComponents();
        initMapa();
        initMapaEnemigo1();
        initMapaEnemigo2();
        initMapaEnemigo3();
        initMapaEnemigo4();
        
        int fontSize = 16; // Tamaño de la fuente
        String fontName = "Open Sans"; // Nombre de la fuente

        // Crea el objeto Font con el tamaño y la fuente recomendados
        Font font = new Font(fontName, Font.PLAIN, fontSize);

        // Asigna la fuente al JLabel lbl_Sucesos
        lbl_Sucesos.setFont(font);
        
        lbl_Sucesos.setText("Se agregó la fuente en la posición x:"+"15"+" y: "+"15 \n");
        lbl_Sucesos.setText(lbl_Sucesos.getText()+"Se agregó la fuente en la posición x:"+"15"+" y: "+"15");
        
    }
    public void colorearMapa(Grafo g, JButton[][] btns){
        Point puntoInicial = new Point(50,0);
        
        for (int i = 0; i < TAMANO_MATRIZ; i++) {
            for (int j = 0; j < TAMANO_MATRIZ; j++) {
                switch (g.matriz[j][i]) {
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
        colorearMapa(jugadores.get(1).getMapa(),btnsMapa2);
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
        colorearMapa(jugadores.get(2).getMapa(),btnsMapa3);
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
        colorearMapa(jugadores.get(3).getMapa(),btnsMapa4);
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
        colorearMapa(jugadores.get(4).getMapa(),btnsMapa5);
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
        jTextField1 = new javax.swing.JTextField();
        btn_Send = new javax.swing.JButton();
        lbl_Chat = new javax.swing.JLabel();
        panelSucesos = new javax.swing.JPanel();
        lbl_Sucesos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelMapaEnemigo1 = new javax.swing.JPanel();
        panelMapaEnemigo2 = new javax.swing.JPanel();
        panelMapaEnemigo3 = new javax.swing.JPanel();
        panelMapaEnemigo4 = new javax.swing.JPanel();

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

        jTextField1.setText("Escriba su mensaje");
        jTextField1.setName(""); // NOI18N

        btn_Send.setText("SEND");
        btn_Send.setActionCommand("");

        lbl_Chat.setText("chat");

        javax.swing.GroupLayout panelChatLayout = new javax.swing.GroupLayout(panelChat);
        panelChat.setLayout(panelChatLayout);
        panelChatLayout.setHorizontalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lbl_Chat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelChatLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Send)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelChatLayout.setVerticalGroup(
            panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelChatLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbl_Chat, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(btn_Send, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );

        panelSucesos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl_Sucesos.setText("Sucesos");
        lbl_Sucesos.setName(""); // NOI18N

        javax.swing.GroupLayout panelSucesosLayout = new javax.swing.GroupLayout(panelSucesos);
        panelSucesos.setLayout(panelSucesosLayout);
        panelSucesosLayout.setHorizontalGroup(
            panelSucesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSucesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Sucesos, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelSucesosLayout.setVerticalGroup(
            panelSucesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSucesosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Sucesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("My Sea");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel2.setText("Enemy");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelMapaEnemigo1Layout = new javax.swing.GroupLayout(panelMapaEnemigo1);
        panelMapaEnemigo1.setLayout(panelMapaEnemigo1Layout);
        panelMapaEnemigo1Layout.setHorizontalGroup(
            panelMapaEnemigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 703, Short.MAX_VALUE)
        );
        panelMapaEnemigo1Layout.setVerticalGroup(
            panelMapaEnemigo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
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
                                .addComponent(jTabbedPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panelChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(panelSucesos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(313, 313, 313)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(664, 664, 664)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(365, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSucesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
                new EspacioDeJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Send;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbl_Chat;
    private javax.swing.JLabel lbl_Sucesos;
    private javax.swing.JPanel panelChat;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JPanel panelMapaEnemigo1;
    private javax.swing.JPanel panelMapaEnemigo2;
    private javax.swing.JPanel panelMapaEnemigo3;
    private javax.swing.JPanel panelMapaEnemigo4;
    private javax.swing.JPanel panelSucesos;
    // End of variables declaration//GEN-END:variables
}
