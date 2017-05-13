/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import dbops.HDDOps;
import java.util.Scanner;
import screens.Loader;
import screens.Login;
import screens.StartScreen;

/**
 *
 * @author TJR
 */
public class Snake {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
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
            System.out.println(ex);
        } catch (InstantiationException ex) {
             System.out.println(ex);
        } catch (IllegalAccessException ex) {
            System.out.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            
        }
        //</editor-fold>
        
        // TODO code application logic here
        HDDOps hdd = new HDDOps();
        if(hdd.fileExists()){
            Loader ld = new Loader(1); // startScr
           ld.setVisible(true);
           ld.gameStart();
           
        }else{
            new Login().setVisible(true);
        }
    }
    
    
    
}
