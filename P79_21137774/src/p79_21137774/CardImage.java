/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author nghia
 */
public class CardImage extends JComponent {
       
    public Card c;

    @Override
    public void paintComponent (Graphics g)
    {
        Image cardImage = Toolkit.getDefaultToolkit().getImage("deck of cards/" + c.toString() + ".png");
        g.drawImage(cardImage, 10, 10, null);
    }
}
