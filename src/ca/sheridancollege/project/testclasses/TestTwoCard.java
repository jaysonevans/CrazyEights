

package ca.sheridancollege.project.testclasses;

import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;
import ca.sheridancollege.project.game.CrazyEights;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.players.ComputerPlayer;
import java.util.Random;
import ca.sheridancollege.project.players.HumanPlayer;


/**
 *
 *Testing 2 card  +++
 * @author Justin Beaulne
 */
public class TestTwoCard 
{
    public static final int NUMBER_OF_STARTING_CARDS = 8;

    public static void main (String[]args)
    {
       
        Random random = new Random();
        
        Value randomValue = Value.values()[random.nextInt(Value.values().length)];
        Suit randomSuit = Suit.values()[random.nextInt(Suit.values().length)];
        
       
        // making a test 2 card and queen of spades 
        Value two = Value.values()[1];
        Value queen = Value.values()[11];
        Suit heartsSuit   = Suit.values()[0];
        Suit clubsSuit    = Suit.values()[1];
        Suit spadesSuit   = Suit.values()[2];
        Suit diamondsSuit = Suit.values()[3];
        
        // two test cards 
        CrazyEightsCard twoHearts   = new CrazyEightsCard(heartsSuit, two);
        CrazyEightsCard twoClubs    = new CrazyEightsCard(clubsSuit, two);
        CrazyEightsCard twoSpades   = new CrazyEightsCard(spadesSuit, two);
        CrazyEightsCard twoDiamonds = new CrazyEightsCard(diamondsSuit, two);
        
        //queen of spades test card 
        CrazyEightsCard queenSpades = new CrazyEightsCard(spadesSuit, queen);
       
        //test player 
        HumanPlayer humanPlayer = new HumanPlayer("Jim");
        
        // test computer
        ComputerPlayer com = new ComputerPlayer("COM");
        
        CrazyEightsCard startingCard = new CrazyEightsCard(spadesSuit, randomValue);
        
        // 7. Instantiate the stock pile and fill it with all but the random card
        StockPile stockPile = new StockPile(startingCard);
        
        // 8. Create the discard pile and give one card to it
        DiscardPile discardPile = new DiscardPile(startingCard);
       
        // 9. Deal the cards
        
        // To the player
        for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
        {
            CrazyEightsCard card = stockPile.pickUp();
            humanPlayer.addToHand(card);
        }
        
          // To the opponent
        for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
        {
            CrazyEightsCard card = stockPile.pickUp();
            com.addToHand(card);
        }
        com.printHand();
        humanPlayer.printHand();
        
        // giving human player 2 cards and queen
        humanPlayer.addToHand(twoClubs);
        humanPlayer.addToHand(twoHearts);
        humanPlayer.addToHand(twoSpades );
        humanPlayer.addToHand(twoDiamonds);
        humanPlayer.addToHand(queenSpades);
        
        System.out.println();
        discardPile.printPile();
        
        humanPlayer.play(discardPile, stockPile);
        
        
       
        // checks the number of cards the players played then check if there was any 2s or queens
        int cardPickUps = new CrazyEights().checkAdditionCards(humanPlayer, discardPile, stockPile);
        //if there is any cards to be picked up this makes the player or computer pick them up 
        new CrazyEights().PickUpCards(cardPickUps, com, stockPile);
        System.out.println(cardPickUps);
        System.out.println();
        com.printHand();
        discardPile.printPile();
      
    }
}
