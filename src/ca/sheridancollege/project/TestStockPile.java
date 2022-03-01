package ca.sheridancollege.project;

/**
 * This class +++ Insert class description here +++
 *
 * @author Jayson Evans
 */
public class TestStockPile 
{
    public static void main(String[] args)
    {
        StockPile stockPile = new StockPile(); // Create a stock pile
        
        // Create a card assumed to be the top card in the discard pile
        CrazyEightsCard card = new CrazyEightsCard(Suit.CLUBS, Value.JACK);
        
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
