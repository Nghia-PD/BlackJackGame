/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.util.Observable;

/**
 *
 * @author nghia
 */
public class Model extends Observable{
    
    public Database db;
    public PlayerData pd;
    public String username;
    public PlayerHand ph;
    public DealerHand dh;
    public Deck deck;
    public int result;
    public int betAmount;
    
    public Model()
    {
        this.db = new Database();      
        db.setup();
        this.deck = new Deck();
        deck.createDeck();
        this.ph = new PlayerHand();
        this.dh = new DealerHand();
    }
    
    public void checkInfo(String username, String password)
    {
        this.username = username;
        this.pd = this.db.checkInfo(username, password);
        this.setChanged();
        this.notifyObservers(this.pd);
    }
    
    public void newPlayer(String username, String password)
    {
        this.username = username;
        this.pd = this.db.addNewPlayer(username, password);
        this.setChanged();
        this.notifyObservers(this.pd);
    }
    
    
    public void newGame()
    {
        this.ph = new PlayerHand();
        this.dh = new DealerHand();
    }
    
    public boolean betting(int betAmount)
    {
        boolean bettingFlag = false;
        if(0 < betAmount && betAmount <= this.pd.balance)
        {                
            bettingFlag = true;
            this.betAmount = betAmount;
        }        

        return bettingFlag;    
    }
    
    public void gameStart()
    {
        for(int i = 0; i < 2; i++)
        {
            ph.draw(getCardFromDeck());
        }
        
        //Show player hand
        ph.showHand();
        System.out.println("Player current hand value: " + ph.handValue());       
    }
    
    public void playerDraw(Card c) // for the draw button
    {
        ph.draw(c);
        ph.showHand();
        System.out.println("Player current hand value: " + ph.handValue());    
    }
    
    
    public Card getCardFromDeck()
    {
        Card card = this.deck.newCardFromDeck();
        this.deck.updateDeck(card);
        
        return card;
    }
    
    public boolean dealerDraw(Card c)
    {
        boolean dealerFlag = false;
        
        dh.draw(c);
        System.out.println(c);
        if (dh.handValue() > 16 || dh.hand.size() == 5)
        {
            dealerFlag = true;
            System.out.println("Dealer hand value: " + dh.handValue());
        }
        return dealerFlag;
    }
    
    public void compareHand()
    {
        //1 : player win
        //-1: dealer win
        //0 : draw
        if(dh.handValue() <= 21 && ph.handValue() <= 21)
        {
            if(dh.handValue() > ph.handValue())
            {
                this.result = -1;
            }
            
            else if(dh.handValue() < ph.handValue())
            {
                this.result = 1;
            }
        }
        
        else if(dh.handValue() <= 21 && ph.handValue() > 21)
        {
            this.result = -1;
        }
        
        else if(dh.handValue() > 21 && ph.handValue() <= 21)
        {
            this.result = 1;
        }
        
        else
        {
            this.result = 0;
        }
       
    }
    
    public void updateBalance()
    {
        switch (result) {
            case 1:
                System.out.println("Player win!");
                this.pd.balance = this.pd.balance + this.betAmount;
                break;
            case -1:
                System.out.println("Player lose!");
                this.pd.balance = this.pd.balance - this.betAmount;
                break;
            default:
                System.out.println("Draw!");                
                break;
        }    
        
        this.setChanged();
    }
   
    public void restart()//restart the game
    {
        this.pd.restartFlag = true;
        this.setChanged();
        this.notifyObservers(this.pd);
    }
    
    public void quitGame()//quit the game
    {
        this.db.quitGame(this.pd.balance, username);
        this.pd.quitFlag = true;
        this.setChanged();
        this.notifyObservers(this.pd);
    }
}
