package testclasses;

import game.DiscardPile;
import cards.Value;
import cards.Suit;
import cards.CrazyEightsCard;

/**
 * This class tests the discard pile class.
 *
 * @author Jayson Evans
 * @author Justin Beaulne 
 */
public class TestDiscardPile
{

    public static void main(String[] args)
    {
        // Instantiate a discard pile
        DiscardPile discardPile = new DiscardPile();
        
        // Add the first card to the pile, should not produce error
        CrazyEightsCard card = new CrazyEightsCard(Suit.HEARTS, Value.FIVE);
        
        discardPile.add(card);
        
        discardPile.printPile();
        
        System.out.println();
        
        // Add another card with the same suit as the last card placed, should not produce an error
        card = new CrazyEightsCard(Suit.HEARTS, Value.SEVEN);
        
        discardPile.add(card);
        
        discardPile.printPile();
        
        System.out.println();
        
        // Add another card with a different suit, should produce an error
        card = new CrazyEightsCard(Suit.SPADES, Value.KING);
        
        discardPile.add(card);
        
        discardPile.printPile();
        
        System.out.println();
        
        // Add an eight of a random suit, should not produce an error
        card = new CrazyEightsCard(Suit.CLUBS, Value.EIGHT);
        
        discardPile.add(card);
        
        discardPile.printPile();
        
        // The stock pile is empty so we'll have to restock it with cards from the discard pile
        discardPile.restock();
        
        discardPile.printPile();
    }
}
