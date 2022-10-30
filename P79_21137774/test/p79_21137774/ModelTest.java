/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p79_21137774;

import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nghia
 */
public class ModelTest {
    public Deck deck = new Deck();
    public ModelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    

    //Test the testBetting method of class Model.
    @Test
    public void testBetting() {
        System.out.println("betting");
        int betAmount = -10;
        Model instance = new Model();
        boolean expResult = false;
        boolean result = instance.betting(betAmount);
        assertEquals(expResult, result);
        
    }
    
    // Test the updateBalance method of class Model.
    @Test
    public void updateBalance()
    {
        Model instanceModel = new Model();
        instanceModel.betAmount = 100;
        instanceModel.pd.balance = 120;
        instanceModel.result = 1;
        instanceModel.updateBalance();
        int balance_expected = 220;
        assertEquals(balance_expected, instanceModel.pd.balance);
    }
    
    // Test the gameStart method of Model class
    @Test 
    public void testgameStart()
    {
        Model instance = new Model();
        instance.gameStart();
        int noOfCard_expected = 2;
        assertEquals(noOfCard_expected, instance.ph.hand.size());
   
    }
    
    //Test the updateDeck and newCardFromDeck of the Deck method
    @Test
    public void testDeckOfCard()
    { 
        int size_expected = deck.deckOfCard.size() -1 ;
        deck.updateDeck(deck.newCardFromDeck()); 
        
        assertEquals(deck.deckOfCard.size(), size_expected);
    }
    
    //Test playerDraw method of the deck class
    @Test
    public void testPlayerDraw()
    {
        Model model = new Model();
        int expected_size = model.ph.hand.size() + 1;
        model.playerDraw(deck.newCardFromDeck());
        
        
        assertEquals(expected_size, model.ph.hand.size());
    }
}
