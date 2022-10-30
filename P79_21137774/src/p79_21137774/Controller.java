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
        switch (command)
        {
            case "Log in":
                username = this.view.usernameInput.getText();
                password = this.view.usernameInput.getText();
                this.model.checkInfo(username, password);
                if(this.model.pd.loginFlag == true)
                {
                    this.view.preGamePhase(this.model.pd.balance);
                }
                
                break;
            case "Sign up":
                username = this.view.usernameInput.getText();
                password = this.view.usernameInput.getText();
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
                        //this.model.gameStart();
                        this.view.game(betAmount, this.model.pd.balance);
                        
                        Card c1 = this.model.getCardFromDeck();
                        this.model.draw(c1);
                        this.view.addCardImage(c1);
                        
                        Card c2 = this.model.getCardFromDeck();
                        this.model.draw(c2);
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
                Card c = this.model.getCardFromDeck();
                this.model.draw(c);
                this.view.addCardImage(c);
                break;
            case "Stand":
                break;
            case "Play Again":
                break;
            case "Restart":
                break;
            case "Quit":
                break;
            default:
                break;      
        }
    }   
}
