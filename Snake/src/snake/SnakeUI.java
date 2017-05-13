/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import dbops.ScoreUpdator;
import exceptions.HitItselfException;
import exceptions.HitWallException;
import screens.GameOver;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import screens.Loader;

/**
 *
 * @author TJR
 */
public class SnakeUI extends JFrame {

    private Timer timer;

    private int timerInterval = 75;

    private String userName;

    private JLabel lblScore;
    private JLabel[][] boxes;
    private ImageIcon[] fruits;
    private ImageIcon[] snakeBody = new ImageIcon[14]; // i got 14 snake body parts.

    private BackEnd back;
    private GameStateInfo info;

    private final int SIZE;

    public static void main(String[] args) {
        //  new SnakeUI(2).setVisible(true);

    }

    public SnakeUI(int difficulty, String userName) {
        super("snake");
        this.userName = userName;
        back = new BackEnd(this, difficulty, userName);

        info = back.getInfo();

        SIZE = info.getSize();

        boxes = new JLabel[SIZE][SIZE];
        fruits = new ImageIcon[info.getFruitCount()];
        this.setBounds(100, 25, 565, 670);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addLabels();

        setTimerInterval(difficulty);

        addListener();
        loadImages();
        startScoresUpdateThread();
        updateUI();
    }

    private void startScoresUpdateThread() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    ScoreUpdator su = new ScoreUpdator(info);
                    su.updateScore();
                    System.out.println("score updated");
                    su.initiateTimer();
                } catch (RuntimeException e) {
                    System.out.println(e);
                }
            }

        });
        thread.setDaemon(true);
        thread.start();
    }

    public void updateUI() {
        //long l = System.currentTimeMillis();
        // for( int r = 0 ; r < SIZE ; r++)

        // update play area.
        int[][] grid = back.getGrid();

        for (int i = 0; i < SIZE * SIZE; i++) {
            int row = i / SIZE;
            int col = i % SIZE;
            JLabel current = boxes[ row][col];
            int gridNum = grid[ row][ col];
            if (gridNum > 0) {
                // snake is here

                current.setBackground(Color.BLACK);
                current.setIcon(snakeBody[back.getImgId(row, col, grid[row][col])]);

            } else if (gridNum == 0) {
                // an empty box

                current.setIcon(null);
                current.setBackground(Color.BLACK);
            } else if (gridNum < 0) {
                boxes[row][ col].setBorder(null);
                //boxes[ row][ col].setBackground(Color.RED);
                if (current.getIcon() == null) {
                    current.setIcon(fruits[info.getFruitIndex()]);
                }
            }

        }

    }

    private void addLabels() {
        int boxSize = 25;
        int startX = 80;
        this.setLayout(null);

        for (int i = 0; i < SIZE * SIZE; i++) {
            JLabel box = new JLabel();
            box.setOpaque(true);
            box.setSize(boxSize, boxSize);
            box.setLocation(boxSize + (i % SIZE) * boxSize, boxSize + startX + (i / SIZE) * boxSize);
            box.setBackground(Color.DARK_GRAY);
            box.setVisible(true);
            this.add(box);
            boxes[ i / SIZE][ i % SIZE] = box;
        }
        //add border
        for (int i = 0; i < SIZE + 2; i++) {
            // box is a brick here
            JLabel box = new JLabel();
            box.setOpaque(true);
            box.setSize(boxSize, boxSize);
            box.setLocation(i * boxSize, startX);
            box.setBackground(Color.RED);

            box.setBorder(new LineBorder(Color.YELLOW, 1));
            box.setVisible(true);
            this.add(box);

            // bottom row
            box = new JLabel();
            box.setOpaque(true);
            box.setSize(boxSize, boxSize);
            box.setLocation(i * boxSize, startX + boxSize * (SIZE + 1));
            box.setBorder(new LineBorder(Color.YELLOW, 1));

            box.setBackground(Color.RED);
            box.setVisible(true);
            this.add(box);

            // left column
            box = new JLabel();
            box.setOpaque(true);
            box.setSize(boxSize, boxSize);
            box.setLocation(0, startX + i * boxSize);
            box.setBackground(Color.RED);
            box.setBorder(new LineBorder(Color.YELLOW, 1));

            box.setVisible(true);
            this.add(box);

            // right col
            box = new JLabel();
            box.setOpaque(true);
            box.setBorder(new LineBorder(Color.YELLOW, 1));

            box.setSize(boxSize, boxSize);
            box.setLocation(boxSize * (SIZE + 1), startX + i * boxSize);
            box.setBackground(Color.RED);
            box.setVisible(true);
            this.add(box);

        }

        // add score label
        lblScore = new JLabel("0");
        lblScore.setOpaque(true);
        lblScore.setForeground(Color.GREEN);
        lblScore.setBackground(Color.BLACK);
        lblScore.setSize(175, startX);
        lblScore.setLocation(375, 0);
        lblScore.setBorder(new LineBorder(Color.GREEN, 3));
        lblScore.setFont(new Font("Arial", Font.BOLD, 45));
        lblScore.setVisible(true);
        lblScore.setHorizontalAlignment(SwingConstants.CENTER);
        // lblScore.setBackground(Color.DARK_GRAY);
        this.add(lblScore);

        JLabel yourScore = new JLabel("Your Score    ");
        yourScore.setOpaque(true);
        yourScore.setFont(new Font("Arial", Font.BOLD, 25));
        yourScore.setForeground(Color.GREEN);
        yourScore.setBackground(Color.BLACK);
        yourScore.setBounds(0, 0, 380, startX);
        yourScore.setVerticalTextPosition(SwingConstants.CENTER);
        yourScore.setHorizontalAlignment(SwingConstants.RIGHT);
        yourScore.setVisible(true);
        this.add(yourScore);
        this.repaint();
    }

    private void addListener() {
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!info.isGameStarted()) {
                    initTimer();
                    info.setGameStarted(true);

                }

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (info.getCurDir() != Direction.DOWN) {
                            info.setCurDir(Direction.UP);
                            back.getMoves().add(Direction.UP);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (info.getCurDir() != Direction.UP) {
                            info.setCurDir(Direction.DOWN);
                            back.getMoves().add(Direction.DOWN);

                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (info.getCurDir() != Direction.RIGHT) {
                            info.setCurDir(Direction.LEFT);
                            back.getMoves().add(Direction.LEFT);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (info.getCurDir() != Direction.LEFT) {
                            info.setCurDir(Direction.RIGHT);
                            back.getMoves().add(Direction.RIGHT);
                        }
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });
    }
    JFrame cur = this;

    private void initTimer() {
        timer = new Timer(timerInterval, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                PriorityQueue<Direction> moves = back.getMoves();
                try {
                    if (!moves.isEmpty()) {

                        back.nextMove(moves.poll());
                    } else {
                        back.nextMove(info.getCurDir());
                    }
                } catch (HitItselfException ex) {

                    timer.stop();

                    JOptionPane.showMessageDialog(null, "Oops .. \n Your snake hit itself ", "Crash", JOptionPane.INFORMATION_MESSAGE);
                    cur.dispose();
                    Loader ld = new Loader(3); // gamer over screen.
                    ld.setVisible(true);
                    ld.submitScore(info.getScore(), userName);
                } catch (HitWallException ex) {
                    timer.stop();
                    JOptionPane.showMessageDialog(null, "Oops .. \n Your snake hit a wall ", "Crash", JOptionPane.INFORMATION_MESSAGE);
                    cur.dispose();
                    gameOver(info.getScore(), userName);

                    //new GameOver(info.getScore(), userName).setVisible(true);
                }

                updateUI();
            }

        });

        timer.start();
    }

    private void gameOver(int score, final String userName) {
        final Loader ld = new Loader(3);;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                 // gamer over screen.
                ld.setVisible(true);
               
                ld.repaint();
                 
            }
        });
       
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                ld.repaint();
                 ld.submitScore(info.getScore(), userName);
            }
        });

    }

    private void setTimerInterval(int difficulty) {
        switch (difficulty) {
            case 1:
                //easy
                timerInterval = 115;
                break;
            case 2:
                //normal
                timerInterval = 100;
                break;
            case 3:
                // hard
                timerInterval = 75;
                break;
            case 4:
                // insane
                timerInterval = 50;
                break;
        }
    }

    private void loadImages() {
        for (int i = 0; i < fruits.length; i++) {
            //String.format("src/res/fruit(%d).png", i+1)
            //getClass().getClassLoader().getResource("res/vehicle1.png")
            fruits[i] = new ImageIcon(getClass().getClassLoader().getResource(String.format("res/fruit (%d).png", i + 1)));

        }
        for (int i = 0; i < snakeBody.length; i++) {
            snakeBody[ i] = new ImageIcon(getClass().getClassLoader().getResource(String.format("res/snake/img%d.png", i)));
        }

    }

    public void updateScore() {
        lblScore.setText(String.valueOf(info.getScore()));
    }

}
