package ca.sheridancollege.project.players;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.game.CrazyEightsUI;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.io.Saver;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a human player.
 *
 * @author Jayson Evans
 * @author Justin Beaulne
 * @author Ryan Stewart
 */
public final class HumanPlayer extends Player
{

    private transient Saver saver = Saver.getInstance();
    private CrazyEightsUI view = new CrazyEightsUI(); // TODO singleton

    /**
     * @param name to pass to the Player class The player can have any name.
     */
    public HumanPlayer(String name)
    {
        super(name);
    }

    /**
     * To be called by the game to initiate the player's turn. Requires user
     * input.
     *
     * @param discardPile the pile where cards are discarded
     * @param stockPile the stock of cards for pick ups
     */
    @Override
    public void play(DiscardPile discardPile, StockPile stockPile, ArrayList<Player> players)
    {
        boolean quitCheck = true;
        boolean entering = true;
        int cardNumber = 0;
        cardsPlaced = 0;

        view.display("\nThe top card is " + discardPile.getTopCard() + "\n");

        if (discardPile.hasMatch(hand))
        {
            do
            {
                do
                {
                    printUserDialog();

                    String userInput = view.promptForCard();

                    char numValue = userInput.charAt(0);
                    if (!Character.isDigit(numValue))
                    {
                        if (numValue == 'q' || numValue == 'Q')
                        {
                            String confirm = view.promptConfirmQuit();
                            if (confirm.equalsIgnoreCase("y"))
                            {
                                numValue = view.promptForSave();

                                if (numValue == 'y' || numValue == 'Q')
                                {
                                    saver.save(discardPile, stockPile, players);
                                }

                                System.exit(0);
                            } else
                            {
                                view.display("The top card is " + discardPile.getTopCard() + "\n");
                                quitCheck = true;

                            }
                        } else
                        {
                            view.display("Please enter a card number or a Q to quit the game");
                        }

                    } else
                    {
                        try
                        {
                            cardNumber = Integer.parseInt(userInput);

                            if ((isInRange(cardNumber)))
                            {
                                quitCheck = false;
                            } else
                            {
                                view.display("\"Must enter the correct number corresponding to a card in your hand\"");

                            }
                        } catch (NumberFormatException nef)
                        {
                            view.display("Error: Must enter the correct number corresponding to a card in your hand");

                        }

                    }
                } while (quitCheck);

                if (isInRange(cardNumber))
                {
                    discardPile.add(hand.get(cardNumber));

                    if (discardPile.isAddSuccessful())
                    {
                        CrazyEightsCard card = hand.get(cardNumber);
                        cardsPlaced++;
                        removeFromHand(cardNumber);

                        view.display("You used " + card + "!");

                        if (discardPile.hasValueMatch(hand))
                        {
                            String answer = view.promptAnotherCard();

                            if (!answer.equalsIgnoreCase("y"))
                            {
                                entering = false;
                            }
                        } else
                        {
                            entering = false;
                        }
                    } else
                    {
                        view.display("Please try again.");
                    }
                } else
                {
                    view.display("Must enter the correct number corresponding to a card in your hand");
                }
            } while (entering);
        } else
        {
            view.display(getName() + " has no moves.");
            view.display("Pick up one and skip turn.");

            if (stockPile.isEmpty())
            {
                discardPile.restock();
                stockPile.restock(discardPile.getTopCard());
            }

            addToHand(stockPile.pickUp());
        }

    }
}
