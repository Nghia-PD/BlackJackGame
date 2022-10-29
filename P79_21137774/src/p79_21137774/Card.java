/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p79_21137774;

/**
 *
 * @author nghia
 */
public class Card {
    
    public Suit suit;
    public Rank rank;
    
    Card(Suit s, Rank r)
    {
        this.suit = s;
        this.rank = r;
    }
   
    //getter and setter
    public Rank get_Rank()
    {
        return this.rank;
    }
    
    @Override
    public String toString()
    {
        return this.suit.name() + " of " + this.rank.name();
    }
}
