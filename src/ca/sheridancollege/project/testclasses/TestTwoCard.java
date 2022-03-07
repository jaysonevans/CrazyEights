

package ca.sheridancollege.project.testclasses;

import static ca.sheridancollege.project.games.CrazyEights.NUMBER_OF_STARTING_CARDS;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;
import ca.sheridancollege.project.games.DiscardPile;
import ca.sheridancollege.project.games.StockPile;
import java.util.Random;
import ca.sheridancollege.project.players.HumanPlayer;


/**
 *
 *Testing 2 card  +++
 * @author Justin Beaulne
 */
public class TestTwoCard 
{
    //some notes for things i need to add 
    // need to add logic of how many cards player puts down for the method to check that many cards in discard pile 
    // need to add the method to trigger everytime card is played?
    //add variable to player to check cards put down 

    public static void main (String[]args)
    {
        
      // counter for 2s in the discard pile 
        int twoCounter = 0;
        boolean queenSpadesPresent = false; 
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
        
        CrazyEightsCard startingCard = new CrazyEightsCard(randomSuit, randomValue);
        
        // 7. Instantiate the stock pile and fill it with all but the random card
        StockPile stockPile = new StockPile(startingCard);
        
        // 8. Create the discard pile and give one card to it
        DiscardPile discardPile = new DiscardPile(queenSpades);
        //adding test cards to discard pile 
        discardPile.add(twoClubs);
        discardPile.add(twoSpades );
        discardPile.add(twoDiamonds);
        discardPile.add(twoHearts);
        
        
        // 9. Deal the cards
        
        // To the player
        for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
        {
            CrazyEightsCard card = stockPile.pickUp();
            humanPlayer.addToHand(card);
        }
        
        humanPlayer.printHand();
       
        System.out.println();
        discardPile.printPile();
        
        humanPlayer.play(discardPile, stockPile);
        discardPile.printPile();
        
        
        // checking discard pile for 2s assigning that to  twoCounter to be used when a player plays a card
        // checking for queen of spades in discard pile  
        for (CrazyEightsCard card : discardPile.getCards())
        {
            CrazyEightsCard twoCardHolder = new CrazyEightsCard(randomSuit, two);
            CrazyEightsCard QueenCardHolder = new CrazyEightsCard(randomSuit, queen);
            if(card.getValue().equals(twoCardHolder.getValue()))
            {
            twoCounter++;
            }
            if (card.getValue().equals(QueenCardHolder.getValue()) && card.getSuit().equals(QueenCardHolder.getSuit()))
            {
            queenSpadesPresent = true;
            }
            
            
        }
        
        // make into method to check for 2s and queen of spades 
        //checking the 2 count to see homw many cards should be picked up 
        switch(twoCounter)
        {
            case 1:
                 for(int i = 0; i < 2; i++)
                {
                    humanPlayer.addToHand(stockPile.pickUp());
                }
                break;
            case 2:
                for(int i = 0; i < 4; i++)
                {
                    humanPlayer.addToHand(stockPile.pickUp());
                }
                break;
            case 3:
                for(int i = 0; i < 6; i++)
                {
                    humanPlayer.addToHand(stockPile.pickUp());
                }
                break;
            case 4:
                for(int i = 0; i < 8; i++)
                {
                    humanPlayer.addToHand(stockPile.pickUp());
                }
                break;
        }
        
        if (queenSpadesPresent)
        {
            for(int i = 0; i < 5; i++)
                {
                    humanPlayer.addToHand(stockPile.pickUp());
                }
        }
       
        
        
        humanPlayer.printHand();
        discardPile.printPile();
       
        System.out.println();
        System.out.println(twoCounter);
        
    }
}
