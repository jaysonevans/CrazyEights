package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class controls game flow and operations related to the game.
 * The most relevant method is play() which controls sets up the game
 * and calls other methods when needed.
 *
 * @author Jayson Evans
 */
public class CrazyEights extends Game
{
    public static final String NAME = "Crazy Eights";
    public CrazyEights()
    {
        super(NAME);
    }
    
    public void declareWinner()
    {
    }
    
    @Override
    public void play()
    {
        // 1. Create a Scanner and ArrayList
        Scanner input = new Scanner(System.in);
        ArrayList<Player> tempPlayers = new ArrayList<>(); 
        
        // 2. Prompt for the name of the player
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        
        // 3. Instantiate the player
        HumanPlayer humanPlayer = new HumanPlayer(name);
        tempPlayers.add(humanPlayer);
        
        // 4. Display a welcome message to the player and print the rules
        System.out.println("\nGreetings, " + humanPlayer.getName() + ".\nWelcome to " + getName() + ".");
        System.out.println("Here are the rules: ");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n",
                "1. Get rid of the cards in your hand.",
                "2. You can place any card with a matching suit or value.",
                "3. You can play down multiple cards of the same value on the same turn.",
                "4. Queen of Spades on top of the discard pile makes the next player pick up 5 cards.",
                "5. Two makes the next player pick up two.",
                "6. Twos stack");
        
        // 5. Ask for and create the opponents
        System.out.print("Enter the number of opponents between 1 and 3, anything else defaults to 3: ");
        int numberOfOpponents = input.nextInt();
        
        if (numberOfOpponents == 1)
        {
            // Create player
            ComputerPlayer comOne = new ComputerPlayer("COM1");
            // Add to the ArrayList
            tempPlayers.add(comOne);
        }
        else if (numberOfOpponents == 2)
        {
            // Create players
            ComputerPlayer comOne = new ComputerPlayer("COM1");
            ComputerPlayer comTwo = new ComputerPlayer("COM2");
            // Add to the ArrayList
            tempPlayers.add(comOne);
            tempPlayers.add(comTwo);
        }
        else
        {
            // Create players
            ComputerPlayer comOne = new ComputerPlayer("COM1");
            ComputerPlayer comTwo = new ComputerPlayer("COM2");
            ComputerPlayer comThree = new ComputerPlayer("COM3");
            // Add to the ArrayList
            tempPlayers.add(comOne);
            tempPlayers.add(comTwo);
            tempPlayers.add(comThree);
        }
    }
    
    public static void main(String[] args)
    {
        new CrazyEights().play();
    }
}