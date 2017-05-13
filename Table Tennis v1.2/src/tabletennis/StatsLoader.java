package tabletennis;
import java.io.*;
public class StatsLoader  {
    TableTennis gameInstance;
    File statsFile;
    Stats statsObject;
    public StatsLoader(TableTennis t) {
        gameInstance = t;
        statsFile = new File("stats.ser");
        deserialize();
    }
    
    public Stats getStats(){
        return statsObject;
    }
    public void saveStats(){
        serialize();
    }
    private void deserialize(){
       if(statsFile.exists()){
        try{
           FileInputStream fis = new FileInputStream(statsFile);
           ObjectInputStream ois = new ObjectInputStream(fis);
           statsObject = (Stats) ois.readObject();
           ois.close();
         }catch(Exception e){}
       }else{
            statsObject = new Stats(0, 0, 0);
       }
    }
    
    private void serialize(){
        try{
            FileOutputStream fos = new FileOutputStream(statsFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(statsObject);
            oos.flush();
            oos.close();
        }catch(Exception e){}
    }
   
}
