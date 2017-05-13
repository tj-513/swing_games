/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screens;

import dbops.HDDOps;
import dbops.ScoreUpdator;
import entities.Player;
import javax.swing.JOptionPane;

/**
 *
 * @author TJR
 */
public class Loader extends javax.swing.JFrame {

    /**
     *
     * Creates new form Loading
     *
     * @param situation 1 = as splash screen. 2 = loading highScores screen 3 =
     * submitting score 4 = refreshing hi-score screen.
     */
    public Loader(int situation) {
        initComponents();

        switch (situation) {
            case 1:
                lblMessage.setText(" Starting game . . . ");

                break;
            case 2:
                lblMessage.setText(" Loading Hi-Scores . . .");
                break;
            case 3:
                lblMessage.setText(" Submitting your score . . . ");
                break;
            case 4:
                lblMessage.setText(" Loading Hi-Scores . .");
                break;
        }
        jLabel1.repaint();
       
        this.repaint();
        this.setVisible(true);
    }

    public void submitScore(final int score, final String userName) {
        final Loader ld = this;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(2);
                    ScoreUpdator updator = new ScoreUpdator();
                    updator.addScore(new Player(score, userName));

                } catch (RuntimeException ex) {
                    ld.dispose();
                    JOptionPane.showMessageDialog(ld, " An error occured while connecting to database "
                            + "\n Make sure you are connected to internet", " Error ", JOptionPane.ERROR_MESSAGE);
                }
                ld.dispose();
                new GameOver(score, userName).setVisible(true);
            }

        });
        t.start();

    }

    public void gameStart() {
        final Loader ld = this;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println(1);
                    ScoreUpdator updator = new ScoreUpdator();
                    updator.updateScore();
                    updator.initiateTimer();
                } catch (RuntimeException ex) {
                    ld.dispose();
                    JOptionPane.showMessageDialog(ld, " An error occured while connecting to database "
                            + "\n Make sure you are connected to internet", " Error ", JOptionPane.ERROR_MESSAGE);
                }
                ld.dispose();
                new StartScreen().setVisible(true);
            }

        });
        t.start();

    }

    public void loadHiScores() {
        final Loader ld = this;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ScoreUpdator updator = new ScoreUpdator();
                    updator.updateScore();

                } catch (RuntimeException ex) {
                    ld.dispose();
                    JOptionPane.showMessageDialog(ld, " An error occured while connecting to database "
                            + "\n Make sure you are connected to internet", " Error ", JOptionPane.ERROR_MESSAGE);
                    //new StartScreen().setVisible(true);
                }
                ld.dispose();
                if (new HDDOps().cacheExists()) {
                    new HighScores().setVisible(true);
                }else{
                    new StartScreen().setVisible(true);
                }
            }

        });
        t.start();
    }

    public void hiScoreSplash(){
        final Loader ld = this;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ScoreUpdator updator = new ScoreUpdator();
                    updator.updateScore();

                } catch (RuntimeException ex) {
                    ld.dispose();
                    JOptionPane.showMessageDialog(ld, " An error occured while connecting to database "
                            + "\n Make sure you are connected to internet", " Error ", JOptionPane.ERROR_MESSAGE);
                    //new StartScreen().setVisible(true);
                }
                ld.dispose();
                
            }

        });
        t.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblMessage = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setBounds(new java.awt.Rectangle(250, 200, 0, 0));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 255, 0), 1, true));
        jPanel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/loader.gif"))); // NOI18N
        jLabel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);

        lblMessage.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(102, 255, 0));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("Fetching hi-scores ...");

        jLabel3.setFont(new java.awt.Font("Vijaya", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 255, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Please Wait . . . ");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 111, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loader(0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}