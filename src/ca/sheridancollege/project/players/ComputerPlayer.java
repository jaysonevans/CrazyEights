package ca.sheridancollege.project.players;

import ca.sheridancollege.project.games.DiscardPile;
import ca.sheridancollege.project.games.StockPile;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents an AI player.
 *
 * @author Jayson Evans
 * @author Justin Beaulne 
 */
public final class ComputerPlayer extends Player
{
    
    private ArrayList<CrazyEightsCard> hand = new ArrayList<>();
    
    /**
     * @param name to pass to the Player class
     * Name's for computer should generally follow
     * the format of COM#.
     */
    public ComputerPlayer(String name)
    {
        super(name);
    }
    
    /**
     * To be called by the game to initiate the
     * computer's turn. Does not require user input.
     */
    public void play(DiscardPile discardPile, StockPile stockPile)
    {
        System.out.println("The top card is " + discardPile.getTopCard());
    }
    
    /**
     * @param card to be added to the hand
     */
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
    
    /**
     * Display the hand of this player
     */
    public void printHand()
    {
        System.out.println(super.getName() + "'s hand contains:");
        Collections.sort(hand);
        for (CrazyEightsCard card: hand)
        {
            System.out.printf("%-6sof %s\n", card.getValue(), card.getSuit());
        }
    }
}
