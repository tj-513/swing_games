/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import dbops.ScoreUpdator;
import entities.Player;
import java.util.ArrayList;

/**
 *
 * @author TJR
 */
public class GameStateInfo {
    
    public GameStateInfo(int difficulty , String userName){
        this.difficulty = difficulty;
        this.userName = userName;
    }
    
    private int size = 20;

    /**
     * @return the SIZE
     */
    public  int getSize() {
        return size;
    }
    private Direction curDir = Direction.RIGHT;
    private int curRow;
    private int curCol;
    private int curLength = 3;
    private int score = 0;
    private boolean gameStarted = false;
    private int fruitCount = 4;
    private int fruitIndex = 2;
    private  int difficulty;
    private Direction dir = Direction.RIGHT;
    
    private Player []  hiScores = new Player [10];
   
    private String userName;
    
    
    
    /**
     * @return the curDir
     */
    public Direction getCurDir() {
        return curDir;
    }

    /**
     * @param curDir the curDir to set
     */
    public void setCurDir(Direction curDir) {
        this.curDir = curDir;
    }

    /**
     * @return the curRow
     */
    public int getCurRow() {
        return curRow;
    }

    /**
     * @param curRow the curRow to set
     */
    public void setCurRow(int curRow) {
        this.curRow = curRow;
    }

    /**
     * @return the curCol
     */
    public int getCurCol() {
        return curCol;
    }

    /**
     * @param curCol the curCol to set
     */
    public void setCurCol(int curCol) {
        this.curCol = curCol;
    }

    /**
     * @return the curLength
     */
    public int getCurLength() {
        return curLength;
    }

    /**
     * @param curLength the curLength to set
     */
    public void setCurLength(int curLength) {
        this.curLength = curLength;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the gameStarted
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * @param gameStarted the gameStarted to set
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * @return the fruitCount
     */
    public int getFruitCount() {
        return fruitCount;
    }

    /**
     * @param fruitCount the fruitCount to set
     */
    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }

    /**
     * @return the fruitIndex
     */
    public int getFruitIndex() {
        return fruitIndex;
    }

    /**
     * @param fruitIndex the fruitIndex to set
     */
    public void setFruitIndex(int fruitIndex) {
        this.fruitIndex = fruitIndex;
    }

    /**
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @return the dir
     */
    public Direction getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(Direction dir) {
        this.dir = dir;
    }

    /**
     * @return the hiScores
     */
    public synchronized Player[] getHiScores() {
        return hiScores;
    }

    /**
     * @param hiScores the hiScores to set
     */
    public void setHiScores(Player[] hiScores) {
        this.hiScores = hiScores;
    }

 
    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the hiScoresList
     */
   
}
