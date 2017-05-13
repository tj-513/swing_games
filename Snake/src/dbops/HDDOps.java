/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbops;
import entities.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TJR
 */
public class HDDOps {
    // make file using the given name.
    public boolean makeFile( String userName){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream( new File("userdata.data"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(userName);
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public String readUserName(){
        try {
            FileInputStream fin = new FileInputStream(new File("userdata.data"));
            ObjectInputStream ois = new ObjectInputStream(fin);
            String ret = (String) ois.readObject();
            return ret;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return "player";
        } catch (IOException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return "player";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return "player";
        }
    }
    
    public boolean fileExists(){
        return new File("userdata.data").exists();
    }
    
    public boolean cacheExists(){
        return new File("scorecache.data").exists();
    }
    
    public boolean writeCacheFile( ArrayList<Player> scores ){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream( new File("scorecache.data"));
            oos = new ObjectOutputStream(fos);
            oos.writeObject(scores);
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return false;
        } catch (IOException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public ArrayList<Player> readScoresCache(){
        ArrayList<Player> ret = new ArrayList<Player>();
        try {
            FileInputStream fin = new FileInputStream(new File("scorecache.data"));
            ObjectInputStream ois = new ObjectInputStream(fin);
            ret = (ArrayList<Player>) ois.readObject();
            return ret;
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return ret;
        } catch (IOException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return ret;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HDDOps.class.getName()).log(Level.SEVERE, null, ex);
            return ret;
        }
    }
}
