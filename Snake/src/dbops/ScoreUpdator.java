/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbops;
import entities.Player;
import java.util.Timer;
import java.util.TimerTask;
import snake.GameStateInfo;
/**
 * takes care of updating the hi scores list.
 * 
 * @author TJR
 */
public class ScoreUpdator {
    private GameStateInfo info;
    private DBOps db;
    private Timer updateTimer;
    public ScoreUpdator( GameStateInfo info){
        
        this.info = info;
        db = new DBOps();
        
    }
    
    public ScoreUpdator(){
        db = new DBOps();
    }

    public void initiateTimer() {
        updateTimer = new Timer ( "updator", true);
        updateTimer.schedule(new TimerTask(){

            @Override
            public void run() {
                updateScore();
            }
            
        }, 1*10*1000 , 2 *60* 1000); // once every two min. // first update starts in ten sec
        
    }
    
    public boolean updateScore( ){
        HDDOps hd = new HDDOps();
        
            hd.writeCacheFile( db.getTopTenScores());
      
        return true;
    }
    
    public boolean addScore( Player p){
        return db.addScore(p);
    }
    
}
