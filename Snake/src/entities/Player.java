/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;

/**
 *
 * @author TJR
 */
public class Player implements Comparable<Player> , Serializable{
    private final int score;
    private final String name;

    public Player(int score, String name) {
        this.score = score;
        this.name = name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player o) {
        return o.score - score;
    }
    
    
}
