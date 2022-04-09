/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Jayson Evans Feb 2022
 * @author Justin Beaulne March 2022 
 */
public abstract class Player implements Cloneable
{

    private String name; //the unique name for this player
    public int cardsPlaced = 0;
    protected ArrayList<CrazyEightsCard> hand = new ArrayList<>();

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
     */
    public abstract void play(DiscardPile discardPile, StockPile stockPile);
    
      public void addToHand(CrazyEightsCard card)
    {
        hand.add(card);
    }

    /**
     * @return the size of the player's hand
     */
    public int getHandSize()
    {
        return hand.size();
    }

}