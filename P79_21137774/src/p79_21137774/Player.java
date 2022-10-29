/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

/**
 *
 * @author nghia
 */
public class Player {
    
    private String name;
    private int balance;

    public Player(String name, int balance) {
        this.balance = balance;
        this.name = name;
    }

    //getter and seter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int score) {
        this.balance = score;
    }
}
