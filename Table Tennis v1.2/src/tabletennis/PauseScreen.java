
package tabletennis;
import java.awt.*;
import java.awt.event.*;


import javax.swing.border.*;

import javax.swing.*;

public class PauseScreen extends JPanel{
   
    
    TableTennis gameInstance;
    Font font = new Font("Arial", Font.BOLD, 16);
    public PauseScreen(TableTennis gameInstance) {
         
        this.gameInstance = gameInstance;
            this.setBounds(290, 0, 250, 500);
            this.setLayout(new GridLayout(6, 0, 15, 10));
            initComponents();
            this.setBackground(Color.DARK_GRAY);
            Border border = new LineBorder(Color.CYAN, 25);

            this.setBorder(border);
            this.setVisible(false);
        
    }
    
    void initComponents(){
        addGamePausedLabel();
        addResumeButton();
        addSetDifficultyLevelsButton();
        addStatsButtton();
        addAboutButton();
        addExitButton();
    }
    
    void addGamePausedLabel(){
       JLabel pauseLabel = new JLabel("Game Paused");
        
        pauseLabel.setOpaque(true);
        pauseLabel.setBackground(Color.GREEN);
        pauseLabel.setVisible(true);
        pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pauseLabel.setFont(font);
        this.add(pauseLabel);
        
    }
    
    void addAboutButton(){
        JButton btnAbout = new JButton("About");
        btnAbout.setFont(font);
        btnAbout.addActionListener(new ActionListener(){
     
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Table Tennis v1.2 \n Dedicated to Janaka sir,Shiran sir and the OCJP crew \n Created by Tharindu");
            }
            
        });
        this.add(btnAbout);
    }
    void addSetDifficultyLevelsButton(){
        JButton btnSetDifficultyLevels = new JButton("Set Difficulty level");
        btnSetDifficultyLevels.setFont(font);
        btnSetDifficultyLevels.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                
                new SetDifficultyBox(gameInstance);
            }
        });
        
        this.add(btnSetDifficultyLevels);
    }
    void addResumeButton(){
        JButton btnResume = new JButton("Resume Game");
        btnResume.setFont(font);
        btnResume.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                              
                gameInstance.resumeGame();
                setVisible(false);

            }
            
        });
        this.add(btnResume);
        
    }
    void addStatsButtton(){
        JButton btnStats = new JButton("Stats");
        btnStats.setFont(font);
        
        btnStats.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent evt){
                JOptionPane.showMessageDialog(null, gameInstance.gameStats);
            }
        });
        this.add(btnStats);
    }
    void addExitButton(){
        JButton btnExit = new JButton("Exit Game");
        btnExit.setFont(font);
        
        btnExit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure want to exit?",
                "Exit?", JOptionPane.YES_NO_OPTION);
                if(reply == JOptionPane.YES_OPTION) System.exit(0);
            
        
            }
        
        });
        
        this.add(btnExit);
    }
}


