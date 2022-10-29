/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p79_21137774;

import java.util.Random;

/**
 *
 * @author nghia
 */
public enum Suit {
    SPADES(1), CLUBS(2), DIAMONDS(3), HEART(4);
    private int suitRank;
    
    //constructor
    private Suit(int suitRank)
    {
        this.suitRank = suitRank;
    }
    
    // get and set method
    public int get_suitRank()
    {
        return this.suitRank;
    }
    
    public void set_suitRank(int suitRank)
    {
        this.suitRank = suitRank;
    }
    
    public static Suit getRandomSuit()
    {
        return Suit.values()[new Random().nextInt(Suit.values().length)];
    }
    //return the String representation of the enum name

    /**
     *
     * @return
     */
    @Override
    public String toString()
    {
        return this.name();
    }
}
