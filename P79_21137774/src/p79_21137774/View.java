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
    private JPanel game_button = new JPanel();
    private JPanel game_ph = new JPanel();
    private JPanel game_dh = new JPanel();
    private JPanel afterGame = new JPanel();
    private JPanel preGame = new JPanel();
    
    private JLabel username = new JLabel("Username");
    public JTextField usernameInput = new JTextField(20);
    private JLabel password = new JLabel("Password");
    public JTextField passwordInput = new JTextField(20);

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
    public int y_frameSize = 800;
    
    public View ()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200,800);
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
    
    public void tooLong()
    {
        this.message.setText("Your username and password must be less than 20 characters each!");
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
    
    public void game(int betAmount, int balance) 
    {
        this.getContentPane();
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
        
        this.getContentPane().removeAll();
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
    
    public void addCardImage(Card c)
    {       
        this.y_frameSize += 1;
        CardImage cardImage = new CardImage();
        cardImage.c = c;
        cardImage.setBounds(this.x, this.y, 150, 200);
        this.add(cardImage);
        this.setSize(1200, this.y_frameSize);//sometime the JComponent doesn't show up until I resize the frame...
        this.x = this.x + 200;   
    }
    
    public void dealerPhase(Card c)
    {
        this.y_frameSize += 1;
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
  
        else if(!this.betFinish)
        {
            this.started = true;
            this.preGamePhase(pd.balance);           
        }
        
        else if(!this.started)
        {
            this.game(pd.betAmount, pd.balance);
        }
        
        else if(pd.quitFlag)
        {
            this.afterGamePhase(pd.balance);
        }
        
    }
      
}
