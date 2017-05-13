/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import exceptions.HitItselfException;
import exceptions.HitWallException;
import java.util.PriorityQueue;
import java.util.Random;
import javax.swing.JOptionPane;
import screens.GameOver;

/**
 *
 * @author TJR
 */
public class BackEnd {

    private PriorityQueue< Direction> moves = new PriorityQueue();

    private  GameStateInfo info;
    private  SnakeUI ui;
    public BackEnd(SnakeUI ui , int difficulty , String userName) {
        System.out.println(1);
        info = new GameStateInfo(difficulty, userName);
        System.out.println(2);
        size = info.getSize();
        this.ui = ui;
        initGrid();

    }
    
    
    int size;
    private int[][] grid;

    private void decrement() {

        for (int i = 0; i < size * size; i++) {
            if (getGrid()[ i / size][ i % size] > 0) {
                getGrid()[ i / size][ i % size]--;
            }
        }
    }

    private void putFood() {
        Random r = new Random();
        int boxNumber = r.nextInt(size * size);
        // while chosen box is not  empty
        while (getGrid()[ boxNumber / size][ boxNumber % size] != 0) {
            boxNumber = r.nextInt(size * size);
        }
        // now it is empty
        grid[ boxNumber / size][ boxNumber % size] = -5;
        // set a fruit index;
        getInfo().setFruitIndex(r.nextInt(getInfo().getFruitCount()));// set random fruit

    }

    public void initGrid() {

        grid = new int[size][size];
        grid[3][1] = 1;
        grid[3][2] = 2;
        grid[3][3] = 3;
        // grid[0][0] = -5;
        //grid[4][4] = -5;
        // grid[4][0] = -5;
        putFood();
        getInfo().setCurRow(3);
        getInfo().setCurCol(3);

    }

    /**
     *
     * @param dir
     */
    public void nextMove(Direction dir) {

        switch (dir) {
            case UP:
                getInfo().setCurRow(getInfo().getCurRow() - 1);
                break;
            case DOWN:
                getInfo().setCurRow(getInfo().getCurRow() + 1);
                break;
            case RIGHT:
                getInfo().setCurCol(getInfo().getCurCol() + 1);
                break;
            case LEFT:
                getInfo().setCurCol(getInfo().getCurCol() - 1);
                break;

        }
        try {
            int row = getInfo().getCurRow();
            int col = getInfo().getCurCol();
            if (getGrid()[ row][ col] < 0) { // means player scores
                // curLength++;
               info.setCurLength(getInfo().getCurLength() + 1);
                info.setScore(getInfo().getScore() + (getInfo().getDifficulty() + 2) * 10);
                //score += (125 - timerInterval);
                //lblScore.setText(String.valueOf(score));
                ui.updateScore();
                putFood();
            } else if (getGrid()[ row][  col] > 0) {

                throw new HitItselfException();
                /**/
            } else {
                decrement();
            }
            // System.out.println(gameInstance.curCol + " are you playin me " + gameInstance.curRow);
            grid[ row][ col] = info.getCurLength();
            //updateUI();
        } catch (ArrayIndexOutOfBoundsException exc) {
            throw new HitWallException();
            /*timer.stop();
             cur.dispose();
             new GameOver(score).setVisible(true);*/
        }
    }

    public int getImgId(int row, int col, int pos /* 1 - tail , length - head , */) {
        if (pos == getInfo().getCurLength()) { // i caught it's head !
            if (row + 1 < size && getGrid()[row + 1][ col] == pos - 1) { // head up
                return 0;
            } else if (col - 1 >= 0 && getGrid()[ row][ col - 1] == pos - 1) { // head right
                return 1;
            } else if (row - 1 >= 0 && getGrid()[ row - 1][ col] == pos - 1) { // head down
                return 2;
            } else if (col + 1 < size && getGrid()[ row][ col + 1] == pos - 1) { // head left
                return 3;
            }
            // now its the tail I caught !!
        } else if (pos == 1) {
            if (row + 1 < size && getGrid()[row + 1][ col] == 2) { // tail points up
                return 4;
            } else if (col - 1 >= 0 && getGrid()[ row][ col - 1] == 2) { // tail points right
                return 5;
            } else if (row - 1 >= 0 && getGrid()[ row - 1][ col] == 2) { // tail points down
                return 6;
            } else if (col + 1 < size && getGrid()[ row][ col + 1] == 2) { // tail points left
                return 7;
            }
        } else {
            // now I caught it by the trunk ! eeew!
            // horizontal movement
            if (col + 1 < size && col - 1 >= 0
                    && getGrid()[ row][ col - 1] * getGrid()[ row][ col + 1] != 0
                    && (getGrid()[ row][ col - 1] + getGrid()[ row][ col + 1]) / 2.0 == pos) { // horizontal movement
                return 9;
            } // vertical
            else if (row + 1 < size && row - 1 >= 0
                    && getGrid()[ row - 1][ col] * getGrid()[ row + 1][ col] != 0
                    && (getGrid()[ row - 1][ col] + getGrid()[ row + 1][ col]) / 2.0 == pos) {
                return 8;
            } // hanling the bends 
            else if (col + 1 < size && row + 1 < size
                    && (getGrid()[ row + 1][ col] + getGrid()[ row][ col + 1]) / 2.0 == pos) { // bend 10
                return 10;
            } else if (row + 1 < size && col - 1 >= 0
                    && (getGrid()[ row + 1][col] + getGrid()[ row][ col - 1]) / 2.0 == pos) {
                return 11;
            } else if (row - 1 >= 0 && col + 1 < size
                    && (getGrid()[ row - 1][col] + getGrid()[ row][ col + 1]) / 2.0 == pos) {
                return 12;
            } else if (row - 1 >= 0 && col - 1 >= 0
                    && (getGrid()[ row - 1][col] + getGrid()[ row][ col - 1]) / 2.0 == pos) {
                return 13;
            }

        }
        // to satisfy compiler
        return 8;
    }

    /**
     * @return the info
     */
    public GameStateInfo getInfo() {
        return info;
    }

    /**
     * @return the grid
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * @return the moves
     */
    public PriorityQueue< Direction> getMoves() {
        return moves;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(GameStateInfo info) {
        this.info = info;
    }

    /**
     * @param ui the ui to set
     */
    public void setUi(SnakeUI ui) {
        this.ui = ui;
    }
}
