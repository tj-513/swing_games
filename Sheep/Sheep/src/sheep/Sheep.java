package sheep;
import java.awt.Color;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Random;


public class Sheep extends JFrame{

    final static int WIDTH = 310;
    final static int HEIGHT = 600;
    
    Timer timer;
    int timerDelay = 10;
    
    JButton [] vehicles = new JButton[5];
    JButton animal = new JButton();
    JLabel lblTimeElapsed;
    JPanel options;
    int row =0, column =2;
    
    Random rnd = new Random();
    
    BestTime [] times = new BestTime[3];
    int timeElapsed =0;
    File stats = new File("Stats.ser");
      
    public Sheep(){
        super("Sheep game v2.1");
        setLayout( null );
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT );
        this.setBounds(250,75,WIDTH,HEIGHT);
        this.setVisible(true);
        this.setResizable(false);
        this.setBackground(Color.red);
        loadBestTimes();
        
        addTimeLabels();
        addOptionButtons();
        
          
        addAnimal();
        animal.requestFocus();
     
        addVehicles();
        addGrass();
        initTimer();
        this.repaint();
        
      
     
        
    }
    
    void addVehicles(){
        
        for(int i=0; i< 5; i++){
            vehicles [i] = new JButton(""+i);
            vehicles [i].setVisible(true);
            vehicles [i].setLocation(  rnd.nextInt(WIDTH), 100 + i*60);
            vehicles [i].setSize( 80,50);
            ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("res/vehicle1.png"));
            vehicles [i].setIcon(img);
            vehicles[i].setHorizontalAlignment(SwingConstants.CENTER);
            vehicles [i].setFocusable(false);
            vehicles [i].setOpaque(false);
           // vehicles [i].setBackground(new Color(rnd.nextInt(100)+150,rnd.nextInt(100)+150, rnd.nextInt(100)+150));
            this.add(vehicles[i]);
        }
        
        vehicles[0].setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/vehicle0.png")));
        vehicles[1].setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/vehicle1.png")));
        vehicles[2].setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/vehicle2.png")));
        vehicles[3].setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/vehicle3.png")));
       vehicles[4].setVisible(false);
       
    }
    
  
    
    void addGrass(){
        JLabel grass = new JLabel();
     
        grass.setToolTipText("Bring sheep here");
        grass.setBackground(Color.GREEN);
        grass.setOpaque(true);
        grass.setLocation(0,50);
        grass.setVisible(true);
        grass.setSize(WIDTH, 50);
        grass.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/grass.png")));
        grass.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(grass);
        
        JLabel road = new JLabel();
        road.setText("road");
        road.setBackground(Color.LIGHT_GRAY);
        road.setOpaque(true);
        road.setLocation(0,100);
        road.setVisible(true);
        road.setSize(WIDTH, 240);
        road.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/road.png")));
        road.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(road);
        
       
        JLabel pavement = new JLabel();
        pavement.setOpaque(true);
        pavement.setLocation(0,340);
        pavement.setVisible(true);
        pavement.setSize(WIDTH, 50);
        pavement.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/eaten_grass.png")));
        pavement.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(pavement);
        
           JLabel forest = new JLabel();
        forest.setOpaque(true);
        forest.setLocation(0,390);
        forest.setVisible(true);
        forest.setSize(WIDTH, 160);
        forest.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/forest.png")));
        forest.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(forest);
    }
    
    void addOptionButtonPanel(){
        
    }
    
    void addAnimal(){
        
      animal.setVisible(true);
       animal.setSize(50, 50);
       animal.setLocation(WIDTH/2-40, 345);
    animal.setBackground(Color.LIGHT_GRAY);
       animal.setOpaque(false);
       animal.repaint();
       animal.setIcon(new ImageIcon(getClass().getClassLoader().getResource("res/sheep.png")));   
       animal.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                moveAnimal(evt);
            }
        });
       animal.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(animal);
        animal.repaint();
    }
    
    void addTimeLabels(){
        JLabel lblTime = new JLabel("Time Elapsed");
        lblTime.setLocation(0, 0);
        lblTime.setSize(100,50);
        lblTime.setBackground(Color.yellow);
        lblTime.setOpaque(true);
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblTime);
                
        lblTimeElapsed = new JLabel();
        lblTimeElapsed.setLocation(100, 0);
        lblTimeElapsed.setOpaque(true);
        lblTimeElapsed.setSize(75, 50);
        lblTimeElapsed.setBackground(Color.WHITE);
        lblTimeElapsed.setHorizontalAlignment(SwingConstants.CENTER);
        
        this.add(lblTimeElapsed);
    }
    
    void addOptionButtons(){
        JButton btnBestTimes = new JButton ("Best Times");
        btnBestTimes.setSize(100,30);
        btnBestTimes.setLocation(10,520);
        btnBestTimes.setFocusable(false);
        btnBestTimes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                displayBestTimesMessage();
            }
        });
        this.add(btnBestTimes);
        
        JButton btnAbout = new JButton( "About");
        btnAbout.setSize(85,30);
        btnAbout.setLocation(115,520);
        btnAbout.setFocusable(false);
        btnAbout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                displayAboutMessage();
            }
        });
        this.add(btnAbout);
        
        JButton btnHelp = new JButton("Help");
        btnHelp.setSize(85,30);
        btnHelp.setLocation(205, 520);
        btnHelp.setFocusable(false);
        btnHelp.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                displayHelpMessage();
            }
        });
        this.add(btnHelp);
        
        JButton btnPause = new JButton("Pause");
        btnPause.setSize(85,30);
        btnPause.setLocation(10, 450);
        btnPause.setFocusable(false);
        btnPause.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                displayPauseMessage();
            }
        });
        this.add(btnPause);
        
        JButton btnReset = new JButton("Reset");
        btnReset.setSize(85,30);
        btnReset.setLocation(100, 450);
        btnReset.setFocusable(false);
        btnReset.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
                resetGameAction();
                
            }
        });
        this.add(btnReset);
        
        JButton btnResetTimes = new JButton("Reset Best Times");
        btnResetTimes.setSize(140,30);
        btnResetTimes.setLocation(160, 485);
        btnResetTimes.setFocusable(false);
        btnResetTimes.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                resetBestTimes();
            }
        });
        this.add(btnResetTimes);
        
        JButton btnDifficulty = new JButton("Set Difficulty");
        btnDifficulty.setBounds(10 , 485, 140, 30);
        btnDifficulty.setFocusable(false);
        btnDifficulty.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                DisplaySetDifficulty();
            }

        });
        this.add(btnDifficulty);
        
    }
    
    void initTimer(){
          timer =  new Timer(timerDelay, new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                moveVehicles();
                checkAnimalPosition();
                timeElapsed += timerDelay;
               
               
            }
        });
      timer.start(); 
      
      Timer updateTime = new Timer(100, new ActionListener(){
        public void actionPerformed(ActionEvent evt){
             lblTimeElapsed.setText(String.format("%1$4.1f", timeElapsed / 1000.0));
           
        }
      });
      updateTime.start();
    }
    
    void moveVehicles(){
        for(int i=0; i< 4; i++){
            vehicles[i].setLocation( vehicles[i].getLocation().x + (5-i), vehicles[i].getLocation().y );
		if(vehicles[i].getLocation().x > 360){
			vehicles[i].setLocation(-60,vehicles[i].getLocation().y);
                        changeVehicleColors(vehicles[i]);
		}
        }
    }
    
    private void moveAnimal(java.awt.event.KeyEvent evt){
        switch(evt.getKeyCode()){
            case KeyEvent.VK_UP:
                if(row < 5){
                    row++;
                    animal.setLocation(animal.getLocation().x , animal.getLocation().y-60);
                }  
                break;
            case KeyEvent.VK_DOWN:
                if(row >0){
                    row--;
                    animal.setLocation(animal.getLocation().x , animal.getLocation().y+60);
                }  
                break;
           
            case KeyEvent.VK_RIGHT:
                if (column < 4){
                    column ++;
                    animal.setLocation(animal.getLocation().x+60 , animal.getLocation().y);
                }    
                break;    
            
            case KeyEvent.VK_LEFT:
                if (column > 0){
                    column--;
                    animal.setLocation(animal.getLocation().x-60 , animal.getLocation().y);
                }   
                break;    
           
        }         
    }
    
    void checkAnimalPosition(){
        switch(row){
            case 1:
                int space = animal.getLocation().x - vehicles[3].getLocation().x;
                if (-50 < space && space < 70) displayMessage(true);
                break;
            case 2:
                space = animal.getLocation().x - vehicles[2].getLocation().x;
                if (-50 < space && space < 70) displayMessage(true);
                break;
             case 3:
                space = animal.getLocation().x - vehicles[1].getLocation().x;
                if (-50 < space && space < 70) displayMessage(true);
                break;
              case 4:
                space = animal.getLocation().x - vehicles[0].getLocation().x;
                if (-50 < space && space < 70) displayMessage(true);
                break;
            case 5:
                 displayMessage(false);
               
                
        }
    
    }
    
    void changeVehicleColors(JButton b){       
        b.setBackground(new Color(rnd.nextInt(100)+150,rnd.nextInt(100)+150, rnd.nextInt(10)+150));
       
    }     
   
    
    void loadBestTimes(){
        if (stats.exists()){
            deserializeTimes();
        }else{
            times = new BestTime []{new BestTime(200*1000),new BestTime(200*1000),new BestTime(200*1000) };
            serializeTimes();
        }
    }
    
    void saveBestTimes(){
        deserializeTimes();
    }
    
    void deserializeTimes(){
        try {
            FileInputStream fin = new FileInputStream(stats);
            ObjectInputStream ois = new ObjectInputStream(fin);
            times = (BestTime[]) ois.readObject();
            ois.close();

        } catch (Exception e) {} 

    }
    
    void serializeTimes(){
        try {
            Arrays.sort(times);
            FileOutputStream fos = new FileOutputStream(stats);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(times);
            oos.flush();
            oos.close();

        } catch (Exception e){}

    }
    
    void resetGameAction(){
       timer.stop();
       int o = JOptionPane.showConfirmDialog(null,"Reset Game?", "Reset", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       if(o == JOptionPane.YES_OPTION){
          resetGame(); 
       }
       timer.start();
    }
    
    void resetGame(){
         int i = 0;
         for(JButton v: vehicles){
           v.setLocation(  rnd.nextInt(WIDTH), 100 + i++*60);
         }
         row =0;
         column = 2;
         animal.setLocation(WIDTH/2-40,345);
        
         timeElapsed =0;
    }
    
    void resetBestTimes(){
        timer.stop();
        int o = JOptionPane.showConfirmDialog(null,"Are you sure? Best Times data will be lost.","Reset Best Times", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE); 
        if(o == JOptionPane.YES_OPTION){
            times = new BestTime []{new BestTime(200*1000),new BestTime(200*1000),new BestTime(200*1000) };
            serializeTimes();
        }
        timer.start();
    }
   
   void displayMessage (boolean dead){
        JOptionPane.showMessageDialog(null,dead? "Unfortunately, \n your sheep has been hit by a vehicle" :
            "Congratulations! \n you safely brought the sheep across the road!");
        Arrays.sort(times);
        if(!dead && times[2].getTime()> timeElapsed){
            times[2] = new BestTime(timeElapsed, JOptionPane.showInputDialog("Congratulations! \n you have a best time.\n Please Enter your name"));
        }
        Arrays.sort(times);
        
        serializeTimes();
        resetGame();
    }
   
    void displayAboutMessage(){
        timer.stop();
        JOptionPane.showMessageDialog(null,"Sheep Game v2.0 \nDedicated to Shiran sir and the OCJP crew. \n Created by Tharindu ");
        timer.start();
    }
    
    void displayHelpMessage(){
         timer.stop();
        JOptionPane.showMessageDialog(null,"Help the sheep to get across the road. \n Use Arrow keys to control the sheep");
        timer.start();
    }
    
    void displayBestTimesMessage(){
          timer.stop();
          Arrays.sort(times);
        JOptionPane.showMessageDialog(null,"Best Times \n" +times[0]+ "" +times[1]+times[2]);
        timer.start();
    }
  
    void displayPauseMessage(){
        timer.stop();
        JOptionPane.showMessageDialog(null,"Game paused, OK to continue ");
        timer.start();
    }
    
    void DisplaySetDifficulty(){
        timer.stop();
        new SetDifficultyBox(this);
        
    }
    
    public static void main(String[] args) {
        new Sheep();
    }

}

class BestTime implements Comparable,Serializable{
    private int time; // in miliseconds
    private String playerName;
    
    BestTime(int time, String playerName){
        this.time = time;
        this.playerName = playerName;
    }
    BestTime(int time){
        this(time, "Anonymous");
    }
    
    public int compareTo(Object o){
        BestTime bt = (BestTime)o;
        return(this.time -bt.getTime());
    }
    
    @Override
    public String toString(){
        return (time/1000.0 + " Seconds by " + playerName + " \n");
    } 
    
    public int getTime(){
        return time;
    }
    public String getPlayerName(){
        return playerName;
    }
}

class SetDifficultyBox extends JFrame {
    final static int NORMAL = 10;
    final static int HARD = 8;
    final static int INSANE = 6;
    
    private int currentDifficulty = NORMAL;
    Sheep target;
    
    SetDifficultyBox(Sheep target){
        this.target = target;
        this.setBounds(150, 150, 250, 100);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        addButtons();
    }
    
    void addButtons(){
        JButton normal = new JButton("Normal");
        normal.addActionListener(new DifficultyAction(NORMAL));
        this.add(normal,BorderLayout.WEST);
        
        JButton hard = new JButton("Hard");
        hard.addActionListener(new DifficultyAction(HARD));
        this.add(hard,BorderLayout.CENTER);
        
        JButton insane = new JButton("Insane");
        insane.addActionListener(new DifficultyAction(INSANE));
        this.add(insane,BorderLayout.EAST);
       
    }
    
    class DifficultyAction implements ActionListener{
        int difficulty ;
        
        public DifficultyAction(int d) {
            difficulty = d;
        }
        
        
        public void actionPerformed(ActionEvent e) {
            target.timerDelay = difficulty;
            target.timer.setDelay(difficulty);
            setVisible(false);
            target.timer.start();
        }
    
    }
    
    
}
    
    

