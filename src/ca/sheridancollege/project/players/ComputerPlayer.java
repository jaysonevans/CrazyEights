package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents an AI player.
 *
 * @author Jayson Evans
 * @author Justin Beaulne 
 */
public final class ComputerPlayer extends Player
{
    
    private ArrayList<CrazyEightsCard> hand = new ArrayList<>();
    
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
     */
    @Override
    public void play(DiscardPile discardPile, StockPile stockPile)
    {
        System.out.println("The top card is " + discardPile.getTopCard());
        
        boolean entering = true;
        
        
        
        if (discardPile.hasMatch(hand))
        {

            do
            {
                int cardNumber = cardPicker(discardPile);
                if (isInRange(cardNumber))
                {
                
                    discardPile.add(hand.get(cardNumber),getName());
                    
                    if (discardPile.isAddSuccessful())
                    {
                        CrazyEightsCard card = hand.get(cardNumber);
                        cardsPlaced++;
                        removeFromHand(cardNumber);
                        
                        System.out.println( getName() + " used " + card + "!");

                        if (discardPile.hasValueMatch(hand)) 
                        {
                            // checking for another card to play computer always anwsers yes  
                            String answer = "y";

                            if (!answer.equalsIgnoreCase("y")) 
                            {
                                entering = false;
                            }
                        } 
                        
                        else 
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
     * @param card to be added to the hand
     */
    public void addToHand(CrazyEightsCard card)
    {
        hand.add(card);
    }
     
    /**
     * @return the size of the player's hand
     */
    public int getHandSize()
    {
        return hand.size();
    }
    
    /**
     * Display the hand of this player
     */
    public void printHand()
    {
        System.out.println(super.getName() + "'s hand contains:");
        Collections.sort(hand);
        for (CrazyEightsCard card: hand)
        {
            System.out.printf("%-6sof %s\n", card.getValue(), card.getSuit());
        }
    }
    
    public boolean isInRange(int cardNumber)
    {
        return 0 <= cardNumber && cardNumber < hand.size();
    }
    
     public void removeFromHand(int index)
    {
        hand.remove(index);
    }
    
    public int cardPicker(DiscardPile discardPile)
    {
        int cardPick = 0;
        for(int i = 0; i < hand.size(); i++)
        {
             
            discardPile.hasMatch(hand);
            if(discardPile.topCardMatches(hand.get(i)))
            {
               cardPick = i;
            }
        }
        return cardPick;
    }
}
