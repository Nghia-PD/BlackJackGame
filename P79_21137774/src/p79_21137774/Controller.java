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

                        this.view.game(betAmount);
                    }
                    
                }
                catch (InputMismatchException ex)
                {
                    this.view.message.setText("Please type a number!");
                }
                
                break;
                
            case "Draw":
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
