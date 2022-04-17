package ca.sheridancollege.project.cards;


import ca.sheridancollege.project.game.CrazyEightsUI;
import ca.sheridancollege.project.players.Player;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the stock pile in a game of Crazy Eights.
 *
 * @author Jayson Evans
 * @author Ryan Stewart
 * @author Justin Beaulne 
 */
public final class StockPile extends GroupOfCards
{
    
    public static StockPile instance = null;
    private CrazyEightsUI view = CrazyEightsUI.getInstance();

    /**
     * Constructs a stock pile with a size equal
     * to that of the deck.
     */
    // private StockPile()
    // {
    //     super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
    // }

    /**
     * Singleton getInstance method that returns the existing instance of StockPile if it already exists
     * 
     * @param startingCard
     * @return
     */
    public static StockPile getInstance(CrazyEightsCard startingCard) {
        if (instance == null) {
            instance = new StockPile(startingCard);
        }
        return instance;
    }
    
    /**
     * Constructs a stock pile with a size equal
     * to that of the deck. And fill it with cards
     * but for the starting card at the top of the 
     * discard pile.
     * 
     * @param startingCard 
     */
    private StockPile(CrazyEightsCard startingCard)
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
        restock(startingCard);
    }
    
    /**
     * Takes a card from the pile. 
     * Controller classes should check if the pile is 
     * empty so as not to return null.
     * 
     * @return the first card in the pile or null if empty
     */
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
    
    /**
     * To be called by the controller class before
     * picking up a card so as not to return null.
     * If classes that call pick up don't want to 
     * check before, then they will have to implement
     * a check for null.
     * 
     * @return true if the stock pile is empty
     */
    public boolean isEmpty()
    {
        return cards.isEmpty();
    }
    
    /**
     * Restock the stock pile with all cards except the one
     * at the top of the discard pile. And shuffle after
     * all the cards have been added.
     * 
     * @param excludedCard card at the top of the discard pile
     */
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
        
        super.shuffle();
    }
    
    /**
     * Display the contents of the stock pile.
     * Mainly used for testing purposes.
     */
    public void printPile()
    {
        Collections.sort(cards);
        //Collections.shuffle(cards);
        
        view.display("The stock pile contains:");
        for (CrazyEightsCard card: cards)
        {
            view.printCard(card);
        }
    }
    
    public void giveCards(ArrayList<CrazyEightsCard> cards, Player opponent, int cardPickUp, StockPile stockPile)
    {
        for(int i = 0; i <= cardPickUp; i++)
        {
           opponent.addToHand(stockPile.pickUp());
            
           
        }
    }
}
