package ca.sheridancollege.project;

import java.util.Collections;

/**
 * This class represents the stock pile in a game of Crazy Eights.
 *
 * @author Jayson Evans
 * @author Ryan Stewart
 */
public final class StockPile extends GroupOfCards
{
    
    public StockPile()
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
    }
    
    public StockPile(CrazyEightsCard startingCard)
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
        restock(startingCard);
    }
    
    // Takes a card from the pile
    // Returns null if cards is empty
    public CrazyEightsCard pickUp()
    {
        if (cards.isEmpty())
        {
            return null;
        }
        else 
        {
            CrazyEightsCard card = cards.get(cards.size() - 1);
        
            cards.remove(cards.size() - 1);
     
            return card;
        }
    }
    
    // To be used before picking up a card
    // The pick up method naturally checks this 
    // but it's good for the user to have access to it so they don't get null
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
    
    // Restock the stock pile with all cards except the one at the top
    // of the discard pile.
    // Reshuffle the cards after all the cards are added.
    public void restock(CrazyEightsCard excludedCard)
    {
        for (Value value: Value.values())
        {
            for (Suit suit: Suit.values())
            {
                if (value == excludedCard.getValue() && suit == excludedCard.getSuit())
                {
                    continue;
                }
                else
                {
                    CrazyEightsCard card = new CrazyEightsCard(suit, value);
                    cards.add(card);
                }
            }
        }
        
        Collections.shuffle(cards);
    }
    
    public void printPile()
    {
        System.out.println("The stock pile contains:");
        for (CrazyEightsCard card: cards)
        {
            System.out.println(card);
        }
    }
}
