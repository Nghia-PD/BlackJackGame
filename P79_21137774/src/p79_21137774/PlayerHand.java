/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.HashSet;

/**
 *
 * @author nghia
 */
public class PlayerHand extends Hand implements Draw{
     
    public PlayerHand()
    {
        super.hand = new HashSet<>();
    }
    
    @Override
    public void showHand()
    {
        for(Card c : hand)
        {
            System.out.println(c);
        }
    }

    @Override
    public int handValue() {
        int value = 0;
        
        for (Card c : super.hand) {
            value += c.get_Rank().get_cardRank();
        }
        
        return value;
    }
  
    @Override
    public void draw(Card c) {
        if(handValue() >= 21 )
        {
            System.out.println("Your hand is currently >= 21, you cannot draw another card!");
        }
        
        else if (super.hand.size() == 5)
        {
            System.out.println("Maximum no of card reached!");
        }
        else
        {
            super.hand.add(c);
        }
    }
}
