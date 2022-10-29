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
public enum Rank {
   ACE(1), _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9), _10(10) ,J(10), Q(10), K(10);
   private int cardRank;
   
    private Rank(int cardRank)
    {
        this.cardRank = cardRank;
    }

    //getter and setter
    public int get_cardRank()
    {
        return this.cardRank;
    }

    public void set_cardRank(int cardRank)
    {
        this.cardRank = cardRank;
    }
   
    public static Rank getRandomRank()
    {
        return Rank.values()[new Random().nextInt(Rank.values().length)];
    }
   
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
