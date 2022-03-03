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
    
    public HumanPlayer(String name)
    {
        super(name);
    }
    
    public void play()
    {
        
    }
    
    // Add to the player's hand
    public void addToHand(CrazyEightsCard card)
    {
        hand.add(card);
    }
    
    // Display the player's hand
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
