package ca.sheridancollege.project.drivers;

import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.Value;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.CrazyEightsCard;

/**
 * This class tests the StockPile class.
 *
 * @author Jayson Evans
 * @author Justin Beaulne
 */
public class TestStockPile
{

    public static void main(String[] args)
    {

        // Create a card assumed to be the top card in the discard pile
        CrazyEightsCard card = new CrazyEightsCard(Suit.CLUBS, Value.JACK);

        StockPile stockPile = StockPile.getInstance(card); // Create a stock pile

        stockPile.restock(card); // Restock the cards

        stockPile.printPile(); // Print the cards in the pile

        System.out.println();

        // Check if the stock pile is empty
        if (!stockPile.isEmpty())
        {
            CrazyEightsCard newCard = stockPile.pickUp(); // Pick up a card
        }

        stockPile.printPile();
    }
}
