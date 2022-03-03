package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * This class represents the discard pile in a game of Crazy Eights.
 *
 * @author Jayson Evans
 * @author Ryan Stewart
 */
public final class DiscardPile extends GroupOfCards
{

    private Scanner input = new Scanner(System.in);
    private boolean addSuccessful = false; // Used for when prompting for another card
    
    public DiscardPile()
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
    }
    
    public DiscardPile(CrazyEightsCard startingCard)
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS); // GroupOfCards constructor, placeholder value
        add(startingCard);
    }
    
    public boolean isAddSuccessful()
    {
        return addSuccessful;
    }
    
    // When adding a card to the pile
    public void add(CrazyEightsCard card)
    {
        // For the first card added to the pile when starting the game
        if (cards.isEmpty())
        {
            cards.add(card);
            addSuccessful = true;
        }
        else 
        {   
            if (cardIsEight(card))
            {
                addEight(card);
                addSuccessful = true;
            }
            else if (topCardMatches(card))
            {
                cards.add(card);
                addSuccessful = true;
            }
            else
            {
                printCardDoesNotMatch(card);
                addSuccessful = false;
            }
        }
    }
    
    // Gets the top card in the pile
    public CrazyEightsCard getTopCard()
    {
        return cards.get(cards.size() - 1);
    }
    
    // Print an error for a nonmatching suit in the card
    public void printCardDoesNotMatch(CrazyEightsCard card)
    {
        String output = "Error: " + card.toString() + " does not match " +
                getTopCard().toString() + "'s suit.\nNo card was added.";
        
        System.out.println(output);
    }
    
    // Returns true if a top card matches
    public boolean topCardMatches(CrazyEightsCard card)
    {
        CrazyEightsCard topCard = getTopCard();
        
        return ( card.getSuit() == topCard.getSuit() || card.getValue() == topCard.getValue() );
    }
    
    // Checks if the card is an eight
    public boolean cardIsEight(CrazyEightsCard card)
    {
        return card.getValue() == Value.EIGHT;
    }
    
    // For adding an eight to the pile
    public void addEight(CrazyEightsCard card)
    {
        boolean go = true; // sentinel value to ensure a correct suit number was entered
        
        // Get the suit from the user and validate it
        while (go)
        {
            promptForSuit();
            
            int newSuit = input.nextInt(); // Get the inputted suit number
            
            // Find the corresponding suit
            // If no suit matches, report an error
            switch (newSuit)
            {
                case 0:
                    card.setSuit(Suit.HEARTS);
                    go = false;
                    break;
                case 1:
                    card.setSuit(Suit.CLUBS);
                    go = false;
                    break;
                case 2:
                    card.setSuit(Suit.SPADES);
                    go = false;
                    break;
                case 3:
                    card.setSuit(Suit.DIAMONDS);
                    go = false;
                    break;
                default:
                    System.out.println("Error: must enter the correct number of the corresponding suit");
                    break;
            }
        }
        
        cards.add(card); // Add the modified card to the pile
    }
    
    // Text prompt for the user to enter a suit number
    // Used exclusively for cards with a value of eight
    public void promptForSuit()
    {
            System.out.println("What would you like the top suit to be:");
        
            for (Suit suit: Suit.values())
            {
                System.out.printf("%d:%s\n", suit.ordinal(), suit.name());
            }
    }
    
    
    // Print the cards in the pile
    // Mainly for testing
    public void printPile()
    {
        System.out.println("The discard pile contains:");
        for (CrazyEightsCard card: cards)
        {
            System.out.println(card);
        }
    }
    
    // Clear the discard pile for reuse in the stock pile up until the last card
    public void restock()
    {
        CrazyEightsCard card = getTopCard();
        
        cards.clear();
        
        cards.add(card);
    }
}
