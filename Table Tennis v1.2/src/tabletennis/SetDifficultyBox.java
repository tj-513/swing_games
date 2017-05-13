package tabletennis;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class SetDifficultyBox extends JFrame {

    TableTennis target;
    static final int NORMAL = 160;
    static final int HARD = 130;
    static final int INSANE = 120;

    SetDifficultyBox(TableTennis target) {
        this.target = target;
        this.setBounds(150, 150, 250, 100);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        addButtons();
    }

    void addButtons() {
        JButton normal = new JButton("Normal");
        normal.addActionListener(new DifficultyAction(NORMAL));
        this.add(normal, BorderLayout.WEST);

        JButton hard = new JButton("Hard");
        hard.addActionListener(new DifficultyAction(HARD));
        this.add(hard, BorderLayout.CENTER);

        JButton insane = new JButton("Insane");
        insane.addActionListener(new DifficultyAction(INSANE));
        this.add(insane, BorderLayout.EAST);

    }

    class DifficultyAction implements ActionListener {

        int difficulty;

        public DifficultyAction(int d) {
            difficulty = d;

        }

        public void actionPerformed(ActionEvent e) {
            target.pad.setSize(target.pad.getWidth(), difficulty);
            switch (difficulty) {
                case NORMAL:
                    target.currentHorizontalSpeed = 18;
                    break;
                case HARD:
                    target.currentHorizontalSpeed = 22;
                    break;
                case INSANE:
                    target.currentHorizontalSpeed = 26;
                    break;
            }
            //target.reset();
            setVisible(false);
        }
    }

}
