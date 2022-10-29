/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p79_21137774;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author nghia
 */
public class Deck{
    
    public ArrayList<Card> deckOfCard ;
    private static int limit;
    Deck()
    {
        this.deckOfCard = new ArrayList<>();
        this.limit = 52;
    }
    
    public void createDeck()
    {
        for(Suit s : Suit.values())
        {
            for(Rank r : Rank.values())
            {
                this.deckOfCard.add(new Card (s, r));
            }
        }
    }
    
    public Card newCardFromDeck()
    {    
        Random random = new Random();
        int index = random.nextInt(limit);  
        
        limit--;
        return this.deckOfCard.get(index);      
    }
    
    public void updateDeck(Card c)
    {
        this.deckOfCard.remove(c);
    }
}
