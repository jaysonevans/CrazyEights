package ca.sheridancollege.project.cards;

import ca.sheridancollege.project.players.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents the discard pile in a game of Crazy Eights.
 *
 * @author Jayson Evans
 * @author Ryan Stewart
 * @author Justin Beaulne March 2022
 */
public final class DiscardPile extends GroupOfCards
{

    private boolean addSuccessful = false; // Used for when prompting for another card
    public static DiscardPile instance = null;

    /**
     * Constructs a discard pile with a size equal to that of the deck.
     *
     * @param startingCard
     * @return
     */
    public static DiscardPile getInstance(CrazyEightsCard startingCard)
    {
        if (instance == null)
        {
            instance = new DiscardPile(startingCard);
        }
        return instance;
    }

    /**
     * Constructs a discard pile with a size equal to that of the deck
     *
     * @param startingCard the card to be at the top of the discard pile.
     */
    private DiscardPile(CrazyEightsCard startingCard)
    {
        super(NUMBER_OF_VALUES * NUMBER_OF_SUITS);
        add(startingCard);
    }

    /**
     * @return whether the addition to the discard pile was successful
     */
    public boolean isAddSuccessful()
    {
        return addSuccessful;
    }

    /**
     * There are three cases when adding a card: 1. The pile is empty, which
     * would mean that this is the first card to be added to the pile. Since
     * this is the first card, it will be added regardless of the suit and
     * value. 2. The card is an eight, which means we would have to request what
     * the player wants the first suit in the pile to be. Elaborated more in the
     * addEight() method. 3. The card has a matching suit or value. In which
     * case just add it to the deck. 4. The card does not have a matching suit
     * or value. In which case, do not add it and print and error message to the
     * console.
     *
     * @param card to add to the discard pile
     */
    public void add(CrazyEightsCard card)
    {
        // For the first card added to the pile when starting the game
        if (cards.isEmpty())
        {
            cards.add(card);
            addSuccessful = true;
        } else
        {
            if (cardIsEight(card))
            {
                addEight(card);
                addSuccessful = true;
            } else if (topCardMatches(card))
            {
                cards.add(card);
                addSuccessful = true;
            } else
            {
                printCardDoesNotMatch(card);
                addSuccessful = false;
            }
        }
    }

    /**
     * Used for when the computer player puts down a card.
     *
     * @param card
     * @param name
     */
    public void add(CrazyEightsCard card, String name)
    {
        // For the first card added to the pile when starting the game
        if (cards.isEmpty())
        {
            cards.add(card);
            addSuccessful = true;

        } else
        {
            if (cardIsEight(card))
            {
                addEight(card, name);
                addSuccessful = true;
            } else if (topCardMatches(card))
            {
                cards.add(card);
                addSuccessful = true;
            } else
            {
                printCardDoesNotMatch(card);
                addSuccessful = false;
            }
        }
    }

    /**
     * @return the card at the top of the discard pile
     */
    public CrazyEightsCard getTopCard()
    {
        return cards.get(cards.size() - 1);
    }

    /**
     * Prints a simple error message to the screen telling the user their card
     * was not added.
     *
     * @param card the card that was the user tried to put
     */
    public void printCardDoesNotMatch(CrazyEightsCard card)
    {
        String output = "Error: " + card.toString() + " does not match "
                + getTopCard().toString() + "'s suit or value.\nNo card was added.";

        view.display(output);
    }

    /**
     * Doesn't look for an identical match but a match between suit and suit or
     * value and value.
     *
     * @param card to be compared with the top card
     * @return true if the card can be placed on the top card
     */
    public boolean topCardMatches(CrazyEightsCard card)
    {
        CrazyEightsCard topCard = getTopCard();

        return (card.getSuit() == topCard.getSuit() || card.getValue() == topCard.getValue());
    }

    /**
     * Looks for a match in the value of the card. To be Used with the AI for
     * selection purposes after their initial card has been placed.
     *
     * @param card
     * @return
     */
    public boolean topCardValueMatches(CrazyEightsCard card)
    {
        CrazyEightsCard topCard = getTopCard();

        return card.getValue() == topCard.getValue();
    }

    /**
     * @param card to be tested
     * @return true if the card is an eight
     */
    public boolean cardIsEight(CrazyEightsCard card)
    {
        return card.getValue() == Value.EIGHT;
    }

    /**
     * Checks if the card put down was a two.
     *
     * @param card
     * @return
     */
    public boolean cardIsTwo(CrazyEightsCard card)
    {
        return card.getValue() == Value.TWO;
    }

    /**
     * Checks if the card is a Queen of Spades.
     *
     * @param card
     * @return
     */
    public boolean cardIsQueenOfSpades(CrazyEightsCard card)
    {
        return card.getValue() == Value.QUEEN && card.getSuit() == Suit.SPADES;
    }

    /**
     * Search for special cards, which are all cards of value two and the Queen
     * of Spades. Also, keeps track of how much the next player will have to
     * pick up.
     *
     * @param cards
     * @param cardsPlaced
     * @return
     */
    public int searchDiscardPile(ArrayList<CrazyEightsCard> cards, int cardsPlaced)
    {
        int pickUpAmount = 0;
        int pileSize = cards.size() - cardsPlaced; // start the search at the spot where the players last placed card is 
        for (int i = 0; i < cardsPlaced; i++)
        {

            if (cardIsTwo(cards.get(pileSize)))
            {

                pickUpAmount += 2;
            } else if (cardIsQueenOfSpades(cards.get(pileSize)))
            {
                pickUpAmount += 5;
            }
            pileSize++;

        }
        return pickUpAmount;
    }

    /**
     * Asks the user what they'd like to change the suit of the top card to and
     * then changes the suit value of the card.
     *
     * These suit values do not have an reappear when restocking the deck
     * because the method restocks the deck with newly created cards.
     *
     * If an eight is at the top of the discard pile when reshuffling, the
     * restock just assumes it's what it says it is and creates eights of
     * different suits. Extremely minor and improbable but may be something to
     * address later.
     *
     * @param card which has the value EIGHT
     */
    public void addEight(CrazyEightsCard card)
    {
        boolean go = true; // sentinel value to ensure a correct suit number was entered

        // Get the suit from the user and validate it
        while (go)
        {

            int newSuit = view.promptForSuit();; // Get the inputted suit number

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
                    view.display("Error: must enter the correct number of the corresponding suit");
                    break;
            }
        }

        cards.add(card); // Add the modified card to the pile
    }

    /**
     * Similar to the addEight() above but is made to be used by the an instance
     * of the ComputerPlayer class to select a suit for the eight they put down.
     *
     * @param card
     * @param name
     */
    public void addEight(CrazyEightsCard card, String name)
    {
        boolean go = true; // sentinel value to ensure a correct suit number was entered

        // Get the suit from the user and validate it
        while (go)
        {
            Random random = new Random();
            int newSuit = random.nextInt(NUMBER_OF_SUITS - 1);
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
                    view.display("Error: must enter the correct number of the corresponding suit");
                    break;
            }
        }
        view.display(name + " Changed the suit to " + card.getSuit() + "\n");
        cards.add(card); // Add the modified card to the pile
    }

    /**
     * Acts as a controller method to cause the player to pick up some amount of
     * cards.
     *
     * @param player the player putting down the special cards (twos and the
     * Queen of Spades)
     * @param stockPile
     * @param discardPile
     * @param opponent the next player who will pick up
     */
    public void addingCards(Player player, StockPile stockPile, DiscardPile discardPile, Player opponent)
    {

        int pickUpAmount = discardPile.searchDiscardPile(discardPile.getCards(), player.getCardsPlaced());
        stockPile.giveCards(stockPile.getCards(), opponent, pickUpAmount, stockPile);

    }

    /**
     * Clear the discard pile for reuse in the stock pile except for the top
     * card.
     */
    public void restock()
    {
        CrazyEightsCard card = getTopCard();

        cards.clear();

        cards.add(card);
    }

    /**
     * @param hand the user's cards
     * @return
     */
    public boolean hasMatch(ArrayList<CrazyEightsCard> hand)
    {
        for (CrazyEightsCard card : hand)
        {
            if (topCardMatches(card))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * To check whether the user can enter a second card.
     *
     * @param hand the user's cards
     * @return true if the value of the top card and one of the user's cards
     * match
     */
    public boolean hasValueMatch(ArrayList<CrazyEightsCard> hand)
    {
        boolean hasValueMatch = false;

        CrazyEightsCard topCard = getTopCard();

        for (CrazyEightsCard card : hand)
        {
            if (card.getValue().ordinal() == topCard.getValue().ordinal())
            {
                hasValueMatch = true;
            }
        }

        return hasValueMatch;
    }
}
