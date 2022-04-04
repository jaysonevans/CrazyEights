package ca.sheridancollege.project.drivers;

import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;
import static ca.sheridancollege.project.game.CrazyEights.NUMBER_OF_STARTING_CARDS;
import ca.sheridancollege.project.players.ComputerPlayer;
import java.util.Random;

/**
 *
 * This class is used to test the AI of the computer opponent +++
 *
 * @author Justin Beaulne
 */
public class TestComputerAI 
{

    public static void main(String[] args) 
    {
        boolean go = true;
        
        
            // 5. Create the opponent
            ComputerPlayer com = new ComputerPlayer("COM");

            // 6. Generate a random card
            Random random = new Random();

            Value randomValue = Value.values()[random.nextInt(Value.values().length)];
            Suit randomSuit = Suit.values()[random.nextInt(Suit.values().length)];

            CrazyEightsCard startingCard = new CrazyEightsCard(randomSuit, randomValue);

            // 7. Instantiate the stock pile and fill it with all but the random card
            StockPile stockPile = new StockPile(startingCard);

            // 8. Create the discard pile and give one card to it
            DiscardPile discardPile = new DiscardPile(startingCard);

            // 9. Deal the cards
            // To the opponent
            for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++) 
            {
                CrazyEightsCard card = stockPile.pickUp();
                com.addToHand(card);
            }

            com.play(discardPile, stockPile);
           // 2 diffrent add eight methods for player -- the one we have return number(suit) given to discard pile to change the top 
           // one for the computer that just picks random 0 -3 num to return to discard pile 
           // discard pile method eightAdded take an int to change suit 
           for(int i = 0; i < 11; i++) // plays 10 rounds to test
           {
               com.printHand(); // for testing 
               com.play(discardPile, stockPile);
           if(com.getHandSize()== 0)
            {
                
                i = 11;
            }
           }
        
    }
}
