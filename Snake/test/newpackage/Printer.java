/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

import screens.Loader;

/**
 *
 * @author TJR
 */
public class Printer {
   public static void main ( String [] args){
       Loader ld = new Loader(3); // gamer over screen.
                    ld.setVisible(true);
                    ld.submitScore(780, "TestUser");
   } 
}
