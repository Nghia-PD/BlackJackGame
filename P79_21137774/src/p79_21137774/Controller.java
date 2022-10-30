/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

/**
 *
 * @author nghia
 */
public class Controller implements ActionListener{
    
    public View view;
    public Model model;
    
    public Controller (View view, Model model)
    {
        this.model = model;
        this.view = view;
        this.view.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String username;
        String password;
        boolean dealerDone = false;
        
        switch (command)
        {
            case "Log in":
                username = this.view.usernameInput.getText();
                password = this.view.passwordInput.getText();
                this.model.checkInfo(username, password);
                if(this.model.pd.loginFlag == true)
                {
                    this.view.preGamePhase(this.model.pd.balance);
                }
                
                break;
            case "Sign up":
                username = this.view.usernameInput.getText();
                password = this.view.passwordInput.getText();
                
                this.model.newPlayer(username, password);
                if(this.model.pd.loginFlag == true)
                {
                    this.view.preGamePhase(this.model.pd.balance);
                }
                break;
            case "Start!":               
                try
                {
                    int betAmount;
                    betAmount = Integer.parseInt(this.view.betInput.getText());
                    boolean bettingFlag = this.model.betting(betAmount); 
                    if(bettingFlag == false)
                    {
                        this.view.message.setText("Your betting should be bigger than 0 and not more than your balance!");
                    }

                    else
                    {
                        
                        this.view.game(betAmount, this.model.pd.balance);
                        
                        Card c1 = this.model.getCardFromDeck();
                        this.model.playerDraw(c1);
                        this.view.addCardImage(c1);
                        
                        Card c2 = this.model.getCardFromDeck();
                        this.model.playerDraw(c2);
                        this.view.addCardImage(c2);
                                 
                        break;
                    }                   
                }
                catch (NumberFormatException ex)
                {
                    this.view.message.setText("Please type a number!");
                }
                                
                break;
                
            case "Draw":
                if(this.model.ph.hand.size() == 5)
                {
                    System.out.println("Maximum number of card reached! Cant Draw!");
                    this.view.message.setText("You have 5 cards already! Please press stand. ");
                }
                else if(this.model.ph.handValue() >= 21)
                {
                    System.out.println("Player hand value >= 21! Cant Draw!");
                    this.view.message.setText("Your hand value is >= 21! Please press stand.");
                }
                else
                {
                    Card c = this.model.getCardFromDeck();
                    this.model.playerDraw(c);
                    this.view.addCardImage(c);
                }
                
                break;
            case "Stand":
                System.out.println("Dealer hand: ");
                Card c3;
                while(dealerDone == false)
                {
                    c3 = this.model.getCardFromDeck();
                    dealerDone = this.model.dealerDraw(c3);
                    this.view.dealerPhase(c3);
                }
                
                this.model.compareHand();
                this.model.updateBalance();      
                this.view.nofifyResult(this.model.result, this.model.betAmount);
                this.view.afterGamePhase(this.model.pd.balance);
                
                break;
            case "Play Again":
                this.view.preGamePhase(this.model.pd.balance);
                this.view.betInput.setText("");
                break;
                
            case "Restart":
                
                break;
            case "Quit":
                //show
                break;
            default:
                break;      
        }
    }   
}
