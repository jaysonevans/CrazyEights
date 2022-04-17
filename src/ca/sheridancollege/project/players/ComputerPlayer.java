package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import java.util.ArrayList;

/**
 * This class represents an AI player.
 *
 * @author Jayson Evans
 * @author Justin Beaulne 
 */
public final class ComputerPlayer extends Player
{
    /**
     * @param name to pass to the Player class
     * Name's for computer should generally follow
     * the format of COM#.
     */
    public ComputerPlayer(String name)
    {
        super(name);
    }
    
    /**
     * To be called by the game to initiate the
     * computer's turn.Does not require user input.
     * @param discardPile
     * @param stockPile
     * @param players
     */
    @Override
    public void play(DiscardPile discardPile, StockPile stockPile, ArrayList<Player> players)
    {
        System.out.println("\nThe top card is " + discardPile.getTopCard() + "\n");
        
        boolean entering = true;
        
        int cardNumber = 0;
        int cardsPlacedThisTurn = 0;
        cardsPlaced = 0;
        
        printHand();
           
        if (cardsPlacedThisTurn == 0 && discardPile.hasMatch(hand))
        {
            do
            {
                cardNumber = cardMatchSelector(discardPile);
                
                if (isInRange(cardNumber))
                {
                    discardPile.add(hand.get(cardNumber),getName());
                    
                    if (discardPile.isAddSuccessful())
                    {
                        CrazyEightsCard card = hand.get(cardNumber);
                        cardsPlaced++;
                        cardsPlacedThisTurn++;
                        removeFromHand(cardNumber);
                        
                        System.out.println( getName() + " used " + card + "!");

                        if (!discardPile.hasValueMatch(hand))
                        {
                            entering = false;
                        }
                    }
                    else
                    {
                        System.out.println("Please try again.");
                    }
                }
                else
                {
                    System.out.println("Must enter the correct number corresponding to a card in your hand");
                } 
            } while (entering);
        }
        else if (discardPile.hasValueMatch(hand))
        {
            do
            {
                cardNumber = cardValueMatchSelector(discardPile);
                
                if (isInRange(cardNumber))
                {
                    discardPile.add(hand.get(cardNumber),getName());
                    
                    if (discardPile.isAddSuccessful())
                    {
                        CrazyEightsCard card = hand.get(cardNumber);
                        cardsPlaced++;
                        cardsPlacedThisTurn++;
                        removeFromHand(cardNumber);
                        
                        System.out.println(getName() + " used " + card + "!");

                        if (!discardPile.hasValueMatch(hand))
                        {
                            entering = false;
                        }
                    }
                    else
                    {
                        System.out.println("Please try again.");
                    }
                }
                else
                {
                    System.out.println("Must enter the correct number corresponding to a card in your hand");
                } 
            } while (entering);
        }
        else
        {
            System.out.println(getName() + " has no moves.");
            System.out.println("Pick up one and skip turn.");
            
            if (stockPile.isEmpty())
            {
                discardPile.restock();
                stockPile.restock(discardPile.getTopCard());
            }
            
            addToHand(stockPile.pickUp());
        }
    }
    
    /**
     * @param discardPile
     * @return the index of the last matching card in the hand
     */
    public int cardMatchSelector(DiscardPile discardPile)
    {
        int cardPick = -1;
        for(int i = 0; i < hand.size(); i++)
        {
            if(discardPile.topCardMatches(hand.get(i)))
            {
               cardPick = i;
            }
        }
        return cardPick;
    }
    
    /**
     * @param discardPile
     * @return the index of the last matching card in the hand
     */
    public int cardValueMatchSelector(DiscardPile discardPile)
    {
        int cardPick = -1;
        for (int i = 0; i < hand.size(); i++)
        {
            if (discardPile.topCardValueMatches(hand.get(i)))
            {
                cardPick = i;
            }
        }
        
        return cardPick;
    }
}
