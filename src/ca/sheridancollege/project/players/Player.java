/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.game.CrazyEightsUI;

import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;
import java.util.Scanner;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Jayson Evans Feb 2022
 * @author Justin Beaulne March 2022 
 * @author Ryan Stewart April 2022
 */
public abstract class Player implements Cloneable, Serializable
{

    private String name; //the unique name for this player
    public int cardsPlaced = 0;
    protected ArrayList<CrazyEightsCard> hand = new ArrayList<>();
    protected transient CrazyEightsUI view = CrazyEightsUI.getInstance();

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name)
    {
        this.name = name;
    }

    /**
     * @return the player name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    public int getCardsPlaced() 
    {
        return cardsPlaced;
    }

    public void setCardsPlaced(int cardsPlaced) 
    {
        this.cardsPlaced = cardsPlaced;
    }
    
    
    
    /**
     * @return a copy of the player object
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }

    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     * @param discardPile
     * @param stockPile
     */
    public abstract void play(DiscardPile discardPile, StockPile stockPile, ArrayList<Player> players);
    
    /**
     * @param card to be added to the player's hand
     */
    public void addToHand(CrazyEightsCard card)
    {
        hand.add(card);
    }
    
    /**
     * Display the hand of this player
     */
    public void printHand()
    {
        view.display("Your hand is:");
        Collections.sort(hand);
        for (int i = 0; i < hand.size(); i++)
        {
            // System.out.printf("%d: %-6sof %s\n", i, hand.get(i).getValue(), hand.get(i).getSuit());
            view.printCard(hand.get(i).getValue(), hand.get(i).getSuit(), i);
        }
    }
    
    /**
     * The dialog displayed to the user when it's their turn
     */
    public void printUserDialog()
    {
        printHand();
        System.out.println("Or enter q to quit");
    }
    
    /**
     * @param index of the card in the hand to remove
     */
    public void removeFromHand(int index)
    {
        hand.remove(index);
    }

    /**
     * @return the size of the player's hand
     */
    public int getHandSize()
    {
        return hand.size();
    }
        
    /**
     * @param cardNumber the index number of the card in hand
     * @return true if the card number is within the limits of the hand
     */
    public boolean isInRange(int cardNumber)
    {
        return 0 <= cardNumber && cardNumber < hand.size();
    }
    
    @Override
    public String toString()
    {
        String output = getName() + ":\n";
        
        for (CrazyEightsCard card: hand)
        {
            output += card.getValue() + " of " + card.getSuit() + "\n";
        }
        
        return output;
    }
    

}