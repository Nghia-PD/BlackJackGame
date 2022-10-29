/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author nghia
 */
public class View extends JFrame implements Observer{
    
    private JPanel user = new JPanel();
    private JPanel game = new JPanel();
    private JPanel afterGame = new JPanel();
    private JPanel preGame = new JPanel();
    
    private JLabel username = new JLabel("Username");
    public JTextField usernameInput = new JTextField(18);
    private JLabel password = new JLabel("Password");
    public JTextField passwordInput = new JTextField(18);

    public JLabel message = new JLabel("Welcome", JLabel.CENTER);
    
    private JLabel bet = new JLabel("Bet Amount: ");
    public JTextField betInput = new JTextField(7);  
    
    private JLabel playerHand = new JLabel("Your hand value:", JLabel.LEFT);
    private JLabel opponentHand = new JLabel("Opponent hand value:", JLabel.LEFT);
    
    private JLabel balance;
    
    private JButton logIn = new JButton("Log in");
    private JButton signUp = new JButton("Sign up");
    private JButton start = new JButton("Start!");       
    private JButton draw = new JButton("Draw");
    private JButton stand = new JButton("Stand");
    private JButton playAgain = new JButton("Play Again"); 
    private JButton quit = new JButton("Quit");
    private JButton restart = new JButton("Restart");
        
    public boolean started = false;
    public boolean betFinish = false;
    
    public View ()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        
        //add component to user panel
        this.user.add(username);
        this.user.add(usernameInput);
        this.user.add(password);
        this.user.add(passwordInput);
        this.user.add(logIn);
        this.user.add(signUp);
        this.add(this.message,BorderLayout.SOUTH);
        
        this.add(user); //add panel to frame
        this.setVisible(true);       
    }
    
    
    public void preGamePhase(int balance)
    {
        this.balance= new JLabel ("Balance: $" + balance);
        this.message.setText("Welcome");
        this.preGame.add(bet);
        this.preGame.add(betInput); 
        this.preGame.add(this.start);
        this.preGame.add(this.restart);
        
        this.getContentPane().removeAll();
        this.add(this.balance, BorderLayout.PAGE_START);
        this.add(preGame, BorderLayout.CENTER);
        this.add(message,BorderLayout.PAGE_END);
        this.revalidate();
        this.preGame.setVisible(true);
        this.repaint();
    }
    
    public void game(int betAmount) 
    {
        
        JLabel betDisplay = new JLabel("Bet ammount: $" + betAmount);
        this.game.add(betDisplay);
        this.game.add(playerHand);
        //card pic
        this.game.add(opponentHand);
        //card pic
        this.game.add(draw);
        this.game.add(stand);
        this.game.add(message, BorderLayout.SOUTH);
    }
    
    
    
    public void afterGamePhase(int newBalance)
    {
        balance = new JLabel("Current Balance: $" + newBalance);
        this.afterGame.add(balance);
        this.afterGame.add(playAgain);
        this.afterGame.add(quit);
        this.afterGame.add(restart);
        this.getContentPane().removeAll();
        afterGame.setVisible(true);
        this.revalidate();
        this.add(afterGame);
        this.repaint(); 
    }
    
    public void addActionListener(ActionListener listener)
    {
        this.signUp.addActionListener(listener);
        this.logIn.addActionListener(listener);
        this.start.addActionListener(listener);
        this.draw.addActionListener(listener);
        this.stand.addActionListener(listener);
        this.playAgain.addActionListener(listener);
        this.quit.addActionListener(listener);
        this.restart.addActionListener(listener);
    }

    @Override
    public void update(Observable o, Object arg) {
        PlayerData pd = (PlayerData) arg;
        if(pd.loginFlag == false)
        {
            this.usernameInput.setText("");
            this.passwordInput.setText("");
            if(pd.existFlag == true)
            {
                this.message.setText("Wrong password!");
            }
            
            else
            {
                this.message.setText("This username doesn't exist! Please use the sign up button!");
            }
        }
        
        
        else if(!this.betFinish)
        {
            this.started = true;
            this.preGamePhase(pd.balance);           
        }
        
        else if(!this.started)
        {
            this.game(pd.betAmount);
        }
        
        else if(pd.quitFlag)
        {
            this.afterGamePhase(pd.balance);
        }
        
    }
      
}
