package players;

import game.DiscardPile;
import game.StockPile;
import cards.CrazyEightsCard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This class represents a human player.
 *
 * @author Jayson Evans
 * @author Justin Beaulne 
 */
public final class HumanPlayer extends Player
{
    private ArrayList<CrazyEightsCard> hand = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    
    /**
     * @param name to pass to the Player class
     * The player can have any name.
     */
    public HumanPlayer(String name)
    {
        super(name);
    }
    
    /**
     * To be called by the game to initiate the
     * player's turn. Requires user input.
     */
    public void play(DiscardPile discardPile, StockPile stockPile)
    {
        boolean entering = true;
        
        System.out.println("The top card is " + discardPile.getTopCard() +"\n");
        
        if (discardPile.hasMatch(hand))
        {

            do
            {
                printHand();
                
                System.out.print("Enter a card: ");
        
                int cardNumber = input.nextInt();
                
                discardPile.add(hand.get(cardNumber));
                
                removeFromHand(cardNumber);
                
                if (discardPile.hasValueMatch(hand))
                {
                    System.out.println("Would you like to enter another card (y/n [default]):");
            
                    String answer = input.next();
                    
                    if (!answer.equalsIgnoreCase("y"))
                    {
                        entering = false;
                    }
                }
                else
                {
                    entering = false;
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
    
    public void removeFromHand(int index)
    {
        hand.remove(index);
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
        System.out.println("Your hand is:");
        Collections.sort(hand);
        for (int i = 0; i < hand.size(); i++)
        {
            System.out.printf("%d: %-6sof %s\n", i, hand.get(i).getValue(), hand.get(i).getSuit());
        }
    }
    
    /**
     * @param cardNumber the index number of the card in hand
     * @return true if the card number is within the limits of the hand
     */
    public boolean inRange(int cardNumber)
    {
        return 0 <= cardNumber && cardNumber < hand.size();
    }
}