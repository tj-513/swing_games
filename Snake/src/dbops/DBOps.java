/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbops;

import entities.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TJR
 */
public class DBOps {

    String url = "jdbc:mysql://sql5.freemysqlhosting.net:3306/sql542743";
    String userName = "sql542743";
    String password = "mC3*wM1*";

    Connection con;
    PreparedStatement pst;

    public DBOps() {
        
    }
    /*
     * get an array of players , fill it.
     */

    public ArrayList<Player> getTopTenScores() {
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            //SELECT * 
            con = DriverManager.getConnection(url, userName, password);
            String query = "SELECT * FROM leaderboard ORDER BY  `Score` DESC LIMIT 10";
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            int i = 0;
            while (rs.next()) {
                players.add(new Player(rs.getInt(2), rs.getString(1)));
                //players [ i ] = new Player (  rs.getInt(2) , rs.getString(1) );
            }
            System.out.println("Score successfully updated ");
            return players;
        } catch (SQLException ex) {
            throw new RuntimeException("no connection");
           
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean addScore(Player player) {
        try {
            con = DriverManager.getConnection(url, userName, password);
            String query = "INSERT INTO leaderboard VALUES(?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, player.getName());
            pst.setInt(2, player.getScore());

            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBOps.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
