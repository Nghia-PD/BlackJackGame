/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p79_21137774;

import java.util.HashSet;

/**
 *
 * @author nghia
 */
public abstract class Hand
{  
    public HashSet<Card> hand;
    public abstract int handValue();
    public abstract void showHand();
}
