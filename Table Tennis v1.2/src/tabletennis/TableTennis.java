package tabletennis;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class TableTennis extends JPanel {

    static {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Your Current version of java seems to be older than 1.7. Look and feel won't work");
        } catch (Exception e) {
        }
    }

    Image ballImage;

    static int WIDTH = 900;
    static int HEIGHT = 450;
    HashSet<JLabel> dots = new HashSet<JLabel>();
    JLabel ball;
    JButton pad;
    JButton opponentPad;
    JButton btnPause = new JButton("Pause");

    JLabel playerLabel;
    JLabel playerScore;
    JLabel comLabel;
    JLabel comScore;
    JLabel table;
    JLabel tableN;

    Timer timer;
    Timer comTimer;
    Timer bounceTimer;

    static final int OPP_PAD_LENGTH = 45;
    static final int PLAYER_PAD_LENGTH = 160;
    // initial 120

    static final int COM_SCORES = 0;
    static final int PLAYER_SCORES = 1;

    static final int COM_PLAYER_TIMER_DELAY = 17; // initial 15

    static final double g = 0.75;

    double currentVerticalSpeed = 0;
    double currentHorizontalSpeed = 20;

    PauseScreen pScreen = new PauseScreen(this);
    Stats gameStats;
    StatsLoader statLoader = new StatsLoader(this);
    private boolean isMultiplayer;
    private String playerOne;
    private String playerTwo;
    JFrame parent;
    
    public TableTennis(boolean isMultiplayer, String playerOne, String playerTwo , JFrame parent) {
        //super("Table Tennis v1.2");
        this.setBounds(0, 0, WIDTH, HEIGHT + 100);
        this.setLayout(null);
        this.setVisible(true);
        // this.setResizable(false);
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.isMultiplayer = isMultiplayer;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        gameStats = statLoader.getStats();
        initComponents(isMultiplayer, playerOne, playerTwo);
        //this.toFront();
        this.parent = parent;
        btnPause.setFocusable(false);
        opponentPad.requestFocus();

    }

    void initComponents(boolean isMultiplayer, String playerOne, String playerTwo) {

        this.add(pScreen);
        addball();
        addPad(isMultiplayer);
        addScoreLabels(playerOne, playerTwo);
        addTable();
        addNet();
        addPauseButton();

        addBackground();

        this.setOpaque(false);
        timer = new Timer(15, new MoveObjects());
        timer.setInitialDelay(1500);
        timer.start();
        timer.setInitialDelay(0);
        if (!isMultiplayer) {
            comTimer = new Timer(COM_PLAYER_TIMER_DELAY, new ComputerPlay());
            comTimer.start();
        }
        bounceTimer = new Timer(5, new Bouncer());
        bounceTimer.start();
    }

    void addball() {
        ball = new JLabel();

        ball.setEnabled(true);
        ball.setLocation(10, 140);
        ball.setSize(25, 25);
        ball.setBackground(Color.YELLOW);
        ImageIcon ballIcon = new ImageIcon(getClass().getClassLoader().getResource("res/ball.png"));
        ball.setIcon(ballIcon);
        //ball.setOpaque(false);
        // ball.setVisible(true);
        try {
            ballImage = ImageIO.read(new File("src/res/ball.png"));
        } catch (IOException ex) {
            Logger.getLogger(TableTennis.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.add(ball);
        ball.repaint();
        this.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {

                pad.setLocation(pad.getLocation().x, e.getPoint().y - 60);
            }

        });
    }

    void addPad(boolean isMultiplayer) {
        pad = new JButton();
        pad.setBounds(WIDTH - 35, HEIGHT / 2, 30, PLAYER_PAD_LENGTH);
        pad.setBackground(Color.GREEN);
        pad.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String s = JOptionPane.showInputDialog("Enter Your cheat code here");
                if (s == null) {
                    return;
                }
                if (s.equals("givemeabiggerracket")) {
                    pad.setSize(pad.getSize().width, 250);
                    JOptionPane.showMessageDialog(null, "Cheat Activated");
                } else if (s.equals("playwithgranny")) {

                    comTimer.setDelay(550);
                    JOptionPane.showMessageDialog(null, "Cheat Activated");
                } else if (s.equals("freezeopponent")) {
                    comTimer.setDelay(11000);
                    JOptionPane.showMessageDialog(null, "Cheat Activated");
                } else if (s.equals("someonekidnappedopponent")) {
                    opponentPad.setSize(opponentPad.getSize().width, 0);
                    JOptionPane.showMessageDialog(null, "Cheat Activated");
                } else if (s.equals("fairplay")) {
                    comTimer.restart();
                    opponentPad.setSize(opponentPad.getSize().width, OPP_PAD_LENGTH);
                    comTimer.setDelay(COM_PLAYER_TIMER_DELAY);
                    pad.setSize(pad.getSize().width, 160);
                    JOptionPane.showMessageDialog(null, "Cheats Deactivated");
                }
            }

        });
        pad.setVisible(true);
        pad.setFocusable(false);
        this.add(pad);

        opponentPad = new JButton();
        opponentPad.setBackground(Color.ORANGE);
        if (isMultiplayer) {
            opponentPad.setBounds(0, HEIGHT / 2, 30, PLAYER_PAD_LENGTH);
            //add action listener here
            opponentPad.addKeyListener(new KeyListener(){

                public void keyTyped(KeyEvent e) {
                    
                }

                public void keyPressed(KeyEvent e) {
                    switch(e.getKeyCode()){
                        case KeyEvent.VK_UP:
                            opponentPad.setLocation(opponentPad.getLocation().x,opponentPad.getLocation().y - 75);
                            break;
                        case KeyEvent.VK_DOWN:
                            opponentPad.setLocation(opponentPad.getLocation().x,opponentPad.getLocation().y + 75);
                            break;
                    }
                }

                public void keyReleased(KeyEvent e) {
                    
                }
            
            });
        } else {
            opponentPad.setBounds(35, HEIGHT / 2, 30, OPP_PAD_LENGTH);
        }
        opponentPad.setVisible(true);
        this.add(opponentPad);
    }

    void addScoreLabels(String playerOne, String playerTwo) {
        font = new Font("Arial", Font.BOLD, 15);
        Font scoresFont = new Font("Arial", Font.BOLD, 55);

        playerLabel = new JLabel(playerOne);
        playerLabel.setBounds(550, 10, 100, 50);
        playerLabel.setOpaque(true);
        playerLabel.setBackground(Color.GREEN);
        playerLabel.setVisible(true);
        playerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerLabel.setFont(font);
        this.add(playerLabel);

        playerScore = new JLabel("0");
        playerScore.setBounds(655, 10, 100, 70);
        playerScore.setOpaque(false);
        playerScore.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
        playerScore.setForeground(Color.GREEN);
        playerScore.setVisible(true);
        playerScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerScore.setFont(scoresFont);
        this.add(playerScore);

        comLabel = new JLabel(playerTwo);
        comLabel.setBounds(50, 10, 120, 50);
        comLabel.setOpaque(true);
        comLabel.setBackground(Color.ORANGE);
        comLabel.setVisible(true);
        comLabel.setHorizontalAlignment(SwingConstants.CENTER);
        comLabel.setFont(font);
        this.add(comLabel);

        comScore = new JLabel("0");
        comScore.setBounds(175, 10, 100, 70);
        comScore.setOpaque(false);
        comScore.setForeground(Color.ORANGE);
        comScore.setVisible(true);
        comScore.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));
        comScore.setFont(scoresFont);
        comScore.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(comScore);
    }

    void addTable() {
        table = new JLabel();
        table.setBounds(50, HEIGHT - 35, WIDTH - 100, 30);
        table.setOpaque(true);
        table.setBackground(new Color(27, 141, 18));

        table.setVisible(true);
        table.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(table);

        table = new JLabel();
        table.setBounds(100, HEIGHT - 35, 50, 150);
        table.setOpaque(true);
        table.setBackground(Color.ORANGE);
        table.setVisible(true);
        table.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(table);

        table = new JLabel();
        table.setBounds(WIDTH - 150, HEIGHT - 35, 50, 150);
        table.setOpaque(true);

        table.setBackground(Color.ORANGE);
        table.setVisible(true);
        table.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(table);
    }

    void addBackground() {
        JLabel back = new JLabel();
        back.setBounds(0, 0, WIDTH, HEIGHT);
        back.setSize(this.getSize());
        back.setOpaque(true);

        ImageIcon im = null;
        im = new ImageIcon(Toolkit.getDefaultToolkit().getImage(TableTennis.class.getClassLoader().getResource("res/TableTennis_Background.jpg")));

        back.repaint();
        back.setIcon(im);
        // back.setBackground(Color.ORANGE);
        back.setVisible(true);
        back.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(back);
        back.repaint();
        this.repaint();

    }

    void addNet() {

        tableN = new JLabel();
        tableN.setBounds(WIDTH / 2 - 10, HEIGHT - 70, 5, 65);
        tableN.setOpaque(true);
        tableN.setBackground(new Color(27, 141, 18));
        tableN.setVisible(true);
        tableN.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(tableN);
    }

    void addPauseButton() {

        btnPause.setBounds(350, 25, 100, 45);
        btnPause.setVisible(true);
        btnPause.setFont(font);
        this.add(btnPause);
        btnPause.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pauseGame();

                pScreen.setVisible(true);
            }

        });

    }

    class MoveObjects implements ActionListener {

        public void actionPerformed(ActionEvent evt) {

            dropBall();

        }
    }

    class Bouncer implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            bounce();
        }
    }

    class ComputerPlay implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (ball.getLocation().x < 220) {
                if (ball.getLocation().y < opponentPad.getLocation().y - 20) {
                    opponentPad.setLocation(5, ball.getLocation().y + 6);
                }
                if (ball.getLocation().y > opponentPad.getLocation().y - 20) {
                    opponentPad.setLocation(5, ball.getLocation().y - 6);
                }
            }
        }
    }

    void dropBall() {
        Graphics graphics = getGraphics();
        int newX = 0, newY = 0;
        currentVerticalSpeed += g;
        int oldY = ball.getLocation().y;
        newX = ball.getLocation().x + (int) currentHorizontalSpeed;
        newY = ball.getLocation().y + (int) currentVerticalSpeed;

        if (newY >= HEIGHT - 50) {
            //ball.repaint();
            newY = HEIGHT - 50;

            currentVerticalSpeed = -(int) Math.round(currentVerticalSpeed);
        }

        // paintDot(newX, newY);
        graphics.drawImage(ballImage, newX, newY, null);
        this.repaint();
        ball.setLocation(newX, newY);

    }

    void checkPosition(JButton pad, int y) {
        int difference = -pad.getLocation().y + y;
        if (difference > 0 && difference < pad.getSize().height) {

        } else {
            if (pad.equals(this.pad)) {
                hit(COM_SCORES);

            } else {
                hit(PLAYER_SCORES);
            }

        }
    }

    void hit(int x) {

        switch (x) {
            case PLAYER_SCORES:
                int score = Integer.parseInt(playerScore.getText()) + 1;
                playerScore.setText(Integer.toString(score));

                if (score > 9) {
                    timer.stop();
                    bounceTimer.stop();
                    if (!isMultiplayer) {
                        JOptionPane.showMessageDialog(null, "Congratulations! \n You Win!");
                    } else {
                        JOptionPane.showMessageDialog(null, playerOne +"  Wins !");
                        gameStats.incrementPlayed(true);
                    statLoader.saveStats();
                    }
                   
                    ball.setLocation(20, 140);
                     parent.dispose();
                    new StartScreen().setVisible(true);
                }

                break;
            case COM_SCORES:
                int opScore = Integer.parseInt(comScore.getText()) + 1;
                comScore.setText(Integer.toString(opScore));
                if (opScore > 9) {
                    timer.stop();
                    bounceTimer.stop();
                    if (!isMultiplayer) {
                        JOptionPane.showMessageDialog(null, "Sorry, you lose \n BetterLuckNext time");
                    } else {
                        JOptionPane.showMessageDialog(null, playerTwo+" Wins !");
                        statLoader.saveStats();
                        gameStats.incrementPlayed(false);
                    }
                    
                   
                    ball.setLocation(20, 140);
                    parent.dispose();
                    new StartScreen().setVisible(true);
                }

                break;
        }
    }

    void clearDots() {
        // for testing purposes only
        for (JLabel l : dots) {
            l.setVisible(false);
        }
        // dots.clear();
    }

    void bounce() {

        if (ball.getLocation().x >= WIDTH - 35 && currentHorizontalSpeed > 0) {
            currentHorizontalSpeed *= -(1);

            ball.setLocation(WIDTH - 35, ball.getLocation().y);
            checkPosition(pad, ball.getLocation().y);
            // clearDots();
        }
        if (ball.getLocation().x <= 10 && currentHorizontalSpeed < 0) {
            currentHorizontalSpeed *= -(1);

            ball.setLocation(10, ball.getLocation().y);
            checkPosition(opponentPad, ball.getLocation().y);
        }
    }

    void reset() {
        ball.repaint();
        ///currentHorizontalSpeed = 8;
        currentVerticalSpeed = 0;
        playerScore.setText("0");
        comScore.setText("0");

    }

    void pauseGame() {
        timer.stop();
        bounceTimer.stop();
        if (!isMultiplayer) {
            comTimer.stop();
        }
        ball.setVisible(false);
        btnPause.setVisible(false);
        pScreen.setVisible(true);

    }

    void resumeGame() {
        pScreen.setVisible(true);
        ball.setVisible(true);
        btnPause.setVisible(true);
        ball.repaint();
        timer.start();
        bounceTimer.start();
        if (!isMultiplayer) {
            comTimer.start();
        }

    }

    void paintDot(int x, int y) {
        JLabel dot = new JLabel();
        dot.setBounds(x, y, 5, 5);
        dot.setOpaque(true);
        dot.setBackground(Color.BLACK);
        dot.setVisible(true);
        this.add(dot);
        dots.add(dot);
    }

    Font font = Font.getFont("Arial");

    public static void main(String[] args) {
        /* JPanel gameFrame = new TableTennis();
         JFrame tt = new JFrame(" Table Tennis v 1.1");

         tt.setBounds(100, 100, WIDTH, HEIGHT + 100);
         tt.setLayout(null);
         tt.setVisible(true);
         tt.add(gameFrame);
         */
    }

}
