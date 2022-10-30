/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.awt.BorderLayout;
import java.awt.Container;
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
    Container c;
    
    private JPanel user = new JPanel();
   // private JPanel game_button = new JPanel();
   //private JPanel game_ph = new JPanel();
   // private JPanel game_dh = new JPanel();
    //private JPanel afterGame = new JPanel();
    //private JPanel preGame = new JPanel();
    
    private JLabel username = new JLabel("Username");
    public JTextField usernameInput = new JTextField(12);
    private JLabel password = new JLabel("Password");
    public JTextField passwordInput = new JTextField(12);
    public JLabel message = new JLabel("Welcome", JLabel.CENTER);
    private JLabel bet = new JLabel("Bet Amount: ");
    public JTextField betInput = new JTextField(7);  
    private JLabel playerHand = new JLabel("Your hand:", JLabel.LEFT);
    private JLabel computerHand = new JLabel("Computer hand:", JLabel.LEFT);
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
   
    public int x = 150;
    public int y = 100;
    public int x_fordh = 150;
    public int y_frameSize = 801;
    
    public View ()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        
        this.username.setBounds(170, 130, 120, 30);
        this.usernameInput.setBounds(270, 130, 120, 30);
        this.password.setBounds(170, 180, 120,30);
        this.passwordInput.setBounds(270, 180, 120,30);
        this.logIn.setBounds(170, 300, 120,30);
        this.signUp.setBounds(320, 300, 120,30);
        
        this.add(username);
        this.add(usernameInput);
        this.add(password);
        this.add(passwordInput);
        this.add(logIn);
        this.add(signUp);
        this.add(this.message,BorderLayout.SOUTH);
        this.add(user);
        this.setVisible(true);       
    }
    
    public void tooLong()
    {
        this.message.setText("Your username and password must be less than 20 characters each!");
    }
    
    public void preGamePhase(int balance)
    {
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.getContentPane().removeAll();
        this.balance= new JLabel ("Balance: $" + balance);
        this.message.setText("Welcome!");
        this.bet.setBounds(170, 130, 120, 30);
        this.betInput.setBounds(270, 130, 120, 30);
        this.start.setBounds(170, 300, 120,30);
        this.restart.setBounds(320, 300, 120,30);
        this.message.setBounds(200, 170, 250, 30);
        this.balance.setBounds(0,0,200,30);
        this.revalidate();
        this.repaint();
        this.add(bet);
        this.add(betInput);      
        this.add(this.start);
        this.add(this.restart);
        this.add(this.balance);
        this.add(message); 
    }
    
    public void game(int betAmount, int balance) 
    {
        this.getContentPane().removeAll();
        this.setSize(1200,800);     
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JLabel betDisplay = new JLabel("Bet ammount: $" + betAmount);
        this.balance = new JLabel ("Current balance: $" + balance);
        this.balance.setBounds(200, 0, 150, 30);
        betDisplay.setBounds(0, 0, 120, 30);     
        this.message = new JLabel("your turn!");
        this.message.setBounds(500, 600, 400, 30);
        this.draw.setBounds(450, 700, 120, 30);
        this.stand.setBounds(600, 700, 120, 30);
        this.playerHand.setBounds(0, 100, 100, 30);
        this.computerHand.setBounds(0, 350, 120, 30);
        this.restart.setBounds(750, 700, 120, 30);
        this.add(restart);
        this.add(this.balance);
        this.add(betDisplay);
        this.add(this.playerHand);
        this.add(this.computerHand);
        this.add(message);
        this.add(draw);
        this.add(stand);
        
        this.revalidate();
        this.repaint();
    }
    
    public void resetSize()// this is for when player want a new game!
    {
        this.x = 150;
        this.y = 100;
        this.x_fordh = 150;
        this.y_frameSize = 800;
    }
    
    public void addCardImage(Card c)
    {       
        this.y_frameSize++;
        
        CardImage cardImage = new CardImage();
        cardImage.c = c;
        cardImage.setBounds(this.x, this.y, 150, 200);
        this.add(cardImage);
        this.setSize(1200, this.y_frameSize);//sometime the JComponent doesn't show up until I resize the frame...
        this.x = this.x + 200;   
        
    }
    
    public void dealerPhase(Card c)
    {
        this.y_frameSize++;
        CardImage cardImage = new CardImage();
        cardImage.c = c;
        cardImage.setBounds(this.x_fordh, 350, 150, 200);
        this.add(cardImage);
        this.setSize(1200, this.y_frameSize);//sometime the JComponent doesn't show up until I resize the frame...
        this.x_fordh += 200;  
    }
    
    public void nofifyResult(int r, int betAmount)
    {
        if(r == 0)
        {           
            this.message.setText("Draw! + 0$ to your account!");
        }

        else if(r == 1) 
        {
            this.message.setText("You win! + $" + betAmount + " to your account");
        }

        else
        {
            this.message.setText("You lose! - $" + betAmount + " to your account");
        }
    }
    
    public void afterGamePhase(int newBalance)
    {
        this.remove(draw);
        this.remove(stand);
        
        playAgain.setBounds(350, 700, 120, 30);
        quit.setBounds(500, 700, 120, 30);
        restart.setBounds(650, 700, 120, 30);
        this.add(playAgain);
        this.add(quit);
        this.add(restart);
        this.balance.setText("Balance: $" + newBalance);
        this.revalidate();
    }
    
    public void quit(int newBalance)
    {
        this.getContentPane().removeAll();
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.balance.setText("Your balance: $" + newBalance);
        balance.setBounds(247, 120, 150, 30);
        this.add(balance);
        this.message.setText("See you again!");
        message.setBounds(253, 300, 150, 30);
        this.add(message);
        this.revalidate();
        this.repaint();
    }
    
    public void restart()
    {
        this.getContentPane().removeAll();
        
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        
        this.username.setBounds(170, 130, 120, 30);
        this.usernameInput.setBounds(270, 130, 120, 30);
        this.password.setBounds(170, 180, 120,30);
        this.passwordInput.setBounds(270, 180, 120,30);
        this.logIn.setBounds(170, 300, 120,30);
        this.signUp.setBounds(320, 300, 120,30);
       
        this.add(username);
        this.add(usernameInput);
        this.add(password);
        this.add(passwordInput);
        this.add(logIn);
        this.add(signUp);
        this.add(message, BorderLayout.SOUTH);
        this.add(user);
        this.setVisible(true);
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
    
    //whenever a flag turn on do sth.
    @Override
    public void update(Observable o, Object arg) {
        PlayerData pd = (PlayerData) arg;
        if(pd.invalidInputFlag == true)
        {
            this.usernameInput.setText("");
            this.passwordInput.setText("");        
            this.message.setText("Both your username and password must be less than 12 characters!");
        }
        
        else if(pd.loginFlag == false)
        {
            this.usernameInput.setText("");
            this.passwordInput.setText("");
            
            
            if(pd.existFlagForLogin == true)
            { 
                this.message.setText("Wrong password!");
            }
   
            else 
            {
                this.message.setText("This username doesn't exist! Please use the sign up button!");
            }
            
            if(pd.existFlagForSignup == true)
            {
                this.message.setText("This username has been taken! Try a new one!");
            }

        } 
        
        if(pd.restartFlag == true)
        {
            this.usernameInput.setText("");
            this.passwordInput.setText("");
            this.message.setText("Welcome!");
        }
    }    
}
