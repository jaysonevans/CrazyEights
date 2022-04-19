package ca.sheridancollege.project.game;

import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.io.IOHandler;
import ca.sheridancollege.project.io.Retriever;
import ca.sheridancollege.project.players.ComputerPlayer;
import ca.sheridancollege.project.players.HumanPlayer;
import ca.sheridancollege.project.players.Player;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class controls game flow and operations related to the game. The most
 * relevant method is play() which sets up the game and calls other methods when
 * needed.
 *
 * @author Jayson Evans
 * @author Justin Beaulne
 * @author Ryan Stewart
 */
public final class CrazyEights extends Game
{

    public static final String NAME = "Crazy Eights";
    public static final int NUMBER_OF_STARTING_CARDS = 8;
    private final CrazyEightsUI view = CrazyEightsUI.getInstance(); // create the view

    /**
     * Constructor isn't supposed to be used as a typical object, more as a
     * controller/coordinator of other objects.
     *
     * Since the name won't change, it's used here as a class variable to call
     * the constructor.
     */
    public CrazyEights()
    {
        super(NAME);
    }

    /**
     * @param player
     * @return true if the size of the player's hand is 0
     */
    @Override
    public boolean checkForWinner(Player player)
    {
        return player.getHandSize() <= 0;
    }

    /**
     * @param player
     * @param players Prints a message of congratulation or consolation whether
     * the user won or lost.
     */
    public void declareWinner(Player player, ArrayList<Player> players)
    {
        if (players.get(0) == player)
        {
            view.displayPlayerWin(player.getName());
        } else
        {
            view.displayPlayerLoss(player.getName());
        }

    }

    /**
     * @param numberOfOpponents the number of computer AI to be created
     * @param players the list of players
     */
    public void createComputerPlayers(int numberOfOpponents, ArrayList<Player> players)
    {
        for (int i = 1; i <= numberOfOpponents; i++)
        {
            players.add(new ComputerPlayer("COM " + i));
        }
    }

    /**
     * @param players
     * @param stockPile Deal a hand to the players at the start of the game
     */
    public void deal(ArrayList<Player> players, StockPile stockPile)
    {
        for (Player player : players)
        {
            for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
            {
                CrazyEightsCard card = stockPile.pickUp();
                player.addToHand(card);
            }
        }
    }

    /**
     * @param players For diagnostic purposes only
     */
    public void printPlayers(ArrayList<Player> players)
    {
        for (Player player : players)
        {
            System.out.println(player);
        }
    }

    public Player getPlayerToLeft(ArrayList<Player> players, int count)
    {
        if (count > players.size() - 1)
        {

            return players.get(0);

        } else
        {

            return players.get(count);

        }
    }

    /**
     * Does the heavy lifting in coordinating objects can method calls.
     *
     * It would probably be better as a main method but I needed to override the
     * abstract method in Game to inherit from it, and so, this is the result.
     */
    @Override
    public void play()
    {

        // Load the save file
        File saveFile = new File(IOHandler.getSaveFileName());
        
        // Create the retriever 
        Retriever retriever = Retriever.getInstance();

        // Create an ArrayList
        ArrayList<Player> players = new ArrayList<>();

        // Prompt for the name of the player
        String name = view.getPlayerName();

        // Instantiate the player
        HumanPlayer humanPlayer = new HumanPlayer(name);
        players.add(humanPlayer);

        // Display a welcome message to the player and print the rules
        view.displayWelcome(humanPlayer.getName(), getName());
        view.displayRules();

        // Ask the user how many opponents they would like
        int numberOfOpponents = view.getNumberOfOpponents();

        // Create the opponents
        createComputerPlayers(numberOfOpponents, players);

        // Generate a random card
        Random random = new Random();

        Value randomValue = Value.values()[random.nextInt(Value.values().length)];
        Suit randomSuit = Suit.values()[random.nextInt(Suit.values().length)];

        CrazyEightsCard startingCard = new CrazyEightsCard(randomSuit, randomValue);

        // Instantiate the stock pile and fill it with all but the random card
        StockPile stockPile = StockPile.getInstance(startingCard);

        // Create the discard pile and give one card to it
        DiscardPile discardPile = DiscardPile.getInstance(startingCard);

        // Deal the cards
        deal(players, stockPile);

        if (saveFile.exists())
        {
            char answer = view.promptRestoreFromSave();

            if (answer == 'y')
            {
                //retriever.restore(discardPile, stockPile, players);
                discardPile = retriever.restoreDiscardPile();
                stockPile = retriever.restoreStockPile();
                players = retriever.restorePlayers();
            }
        }

        // Main game loop
        boolean winner = false;

        while (!winner)
        {

            int count = 0;
            for (Player player : players)
            {

                count++;
                player.play(discardPile, stockPile, players);
                discardPile.addingCards(player, stockPile, discardPile, getPlayerToLeft(players, count));

                System.out.println();

                if (checkForWinner(player))
                {
                    declareWinner(player, players);
                    winner = true;
                    break;
                }
            }
        }
    }

    /**
     * Needed to execute the play method in the CrazyEights class. It's put here
     * for simplicity but could be in it's own class.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new CrazyEights().play();

        // check two count platers array 
        // if two add to player at counts hand number of cards 
    }
}
