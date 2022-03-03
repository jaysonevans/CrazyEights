package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a human player.
 *
 * @author Jayson Evans
 */
public final class HumanPlayer extends Player
{
    private ArrayList<CrazyEightsCard> hand = new ArrayList<>();
    
    /**
     * @param name to pass to the Player class
     * The player can have any name.
     */
    public HumanPlayer(String name)
    {
        super(name);
    }
    
    /**
     * To be called by the game to initiate the
     * player's turn. Requires user input.
     */
    public void play()
    {
        
    }
    
    /**
     * @param card to be added to the hand
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
        System.out.println("Your hand is:");
        Collections.sort(hand);
        for (CrazyEightsCard card: hand)
        {
            System.out.printf("%-6sof %s\n", card.getValue(), card.getSuit());
        }
    }
}
