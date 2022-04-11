package ca.sheridancollege.project.game;


import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;
import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.players.ComputerPlayer;
import ca.sheridancollege.project.players.HumanPlayer;
import ca.sheridancollege.project.players.Player;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * This class controls game flow and operations related to the game.
 * The most relevant method is play() which sets up the game and
 * calls other methods when needed.
 *
 * @author Jayson Evans
 * @author Justin Beaulne
 */
public final class CrazyEights extends Game
{
    public static final String NAME = "Crazy Eights";
    public static final int NUMBER_OF_STARTING_CARDS = 8;
    
    /**
     * Constructor isn't supposed to be used as
     * a typical object, more as a controller/coordinator
     * of other objects.
     * 
     * Since the name won't change, it's used here as a 
     * class variable to call the constructor.
     */
    public CrazyEights()
    {
        super(NAME);
    }
    
    /**
     * Display the rules of the game
     */
    public void displayRules()
    {
        System.out.println("Here are the rules: ");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                "1. Get rid of the cards in your hand.",
                "2. You can place any card with a matching suit or value.",
                "3. Eight cards change the suit of the top card.",
                "4. You can play down multiple cards of the same value on the same turn.",
                "5. Queen of Spades on top of the discard pile makes the next player pick up 5 cards.",
                "6. Two makes the next player pick up two.",
                "7. Twos stack",
                "8. Enter a Q  to terminate the game\n");
    }
    
    @Override
    public Player checkForWinner(ArrayList<Player> players)
    {

        for (Player ply: players)
        {
            if (ply.getHandSize() <= 0)
            {
                return ply;
            }
        }

        return null;
    }

    /**
     *  @param 
     */
    public void createComputerPlayers(int numberOfOpponents, ArrayList<Player> players)
    {
        for (int i = 1; i <= numberOfOpponents; i++)
        {
            players.add(new ComputerPlayer("COM" + i));
        }
    }
    
    /**
     * Does the heavy lifting in coordinating 
     * objects can method calls.
     * 
     * It would probably be better as a main method
     * but I needed to override the abstract method
     * in Game to inherit from it, and so, this is 
     * the result.
     */
    @Override
    public void play()
    {
        // Create a Scanner and ArrayList
        Scanner input = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>(); 
        
        // Prompt for the name of the player
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        
        // Instantiate the player
        HumanPlayer humanPlayer = new HumanPlayer(name);
        players.add(humanPlayer);
        
        // Display a welcome message to the player and print the rules
        System.out.println("\nGreetings, " + humanPlayer.getName() + ".\nWelcome to " + getName() + ".");
        displayRules();
        
        // Create the opponent
        //ComputerPlayer com = new ComputerPlayer("COM");
        //players.add(com);
        
        // Ask the user how many opponents they would like
        System.out.print("Enter the number of opponents you would like to play against: ");
        int numberOfOpponents = input.nextInt();

        // Create the opponents
        createComputerPlayers(numberOfOpponents, players);
        
        // Generate a random card
        Random random = new Random();
        
        Value randomValue = Value.values()[random.nextInt(Value.values().length)];
        Suit randomSuit = Suit.values()[random.nextInt(Suit.values().length)];
        
        CrazyEightsCard startingCard = new CrazyEightsCard(randomSuit, randomValue);
        
        // Instantiate the stock pile and fill it with all but the random card
        StockPile stockPile = new StockPile(startingCard);
        
        // Create the discard pile and give one card to it
        DiscardPile discardPile = new DiscardPile(startingCard);
        
        // Deal the cards
        
        // To the player
        for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
        {
            CrazyEightsCard card = stockPile.pickUp();
            humanPlayer.addToHand(card);
        }
  
/*      
        // To the opponent
        for (int i = 1; i <= NUMBER_OF_STARTING_CARDS; i++)
        {
            CrazyEightsCard card = stockPile.pickUp();
            com.addToHand(card);
        }
*/
 
/*       
        // Start the main game loop
        //boolean noWinner = true;
        //playing 10 rounds of player vs com
        for(int i = 0; i < 10; i++)
        {
            Player winner = checkForWinner(players);
            if (winner != null)
            {
                System.out.println(winner.getName() + " has won the game!");
                break;
            }
            humanPlayer.play(discardPile, stockPile);
            com.play(discardPile, stockPile);
            // End the game loop for testing
            //noWinner = false;
        }
*/
    }
    
    /**
     * Needed to execute the play method in the
     * CrazyEights class. It's put here for 
     * simplicity but could be in it's own
     * class.
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        new CrazyEights().play();
    }
}
