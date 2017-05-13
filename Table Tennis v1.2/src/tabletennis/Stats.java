
package tabletennis;
import java.io.Serializable;
public class Stats implements Serializable {

    private int played;
    private int won;
    private int lost;
  
    public Stats(int played, int won , int lost) {
        this.played = played;
        this.won = won;
        this.lost = lost;
    
        
    }

    public

    int getPlayed() {
        return played;
    }
    public int getWon() {
        return won;
    }
    public int getLost() {
        return lost;
    }
  
    
   public void incrementPlayed(boolean victory){
       played++; 
       if(victory) won++;
        else lost++;
   }
   
   public String toString(){
        return String.format("Games Played: %1$d \nGames Won: %2$d\nGames lost: %3$d  ", played, won , lost);
   }
}
