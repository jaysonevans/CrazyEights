package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.CrazyEightsCard;
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
     * 
     * @param discardPile the pile where cards are discarded
     * @param stockPile the stock of cards for pick ups
     */
    @Override
    public void play(DiscardPile discardPile, StockPile stockPile)
    {
        boolean quitCheck = true;
        boolean entering = true;
        int cardNumber = 0;
        
        System.out.println("The top card is " + discardPile.getTopCard() +"\n");
        
        if (discardPile.hasMatch(hand))
        {

            do
            {
                do
                {
                    printHand();
                
                    System.out.print("Enter a card: ");
        
                    String userNumInput = input.next();
                    
                    char numValue = userNumInput.charAt(0);
                    if(!Character.isDigit(numValue))
                    {
                        if (numValue == 'q' || numValue == 'Q')
                        {
                            System.out.println("Are you sure you wanna end the game? (y/n):");
                            String confirm = input.next();
                            if(confirm.equalsIgnoreCase("y"))
                            {
                                System.exit(0);
                            }
                            else
                            {
                                System.out.println("The top card is " + discardPile.getTopCard() +"\n");
                                quitCheck = true;
                                
                            }
                        }
                        else
                        {
                        System.out.println("Please enter a card number or a Q to quit the game");
                        }
                  
                    }
                    else 
                    {
                        try
                        {
                        cardNumber = Integer.parseInt(userNumInput);
                        if((!isInRange(cardNumber)))
                        {
                            quitCheck = false;
                        }
                        
                        else
                        {
                            System.out.println("\"Must enter the correct number corresponding to a card in your hand\"");
                        }
                        }
                        catch(NumberFormatException nef)
                        {
                            System.out.println("Error: Must enter the correct number corresponding to a card in your hand");
                        }
                        
                     }
                }while(quitCheck);
                
                if (isInRange(cardNumber))
                {
                
                    discardPile.add(hand.get(cardNumber));
                    
                    if (discardPile.isAddSuccessful())
                    {
                        CrazyEightsCard card = hand.get(cardNumber);
                        cardsPlaced++;
                        removeFromHand(cardNumber);
                        
                        System.out.println("You used " + card + "!");

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
    public boolean isInRange(int cardNumber)
    {
        return 0 <= cardNumber && cardNumber < hand.size();
    }
   
}
