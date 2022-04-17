package ca.sheridancollege.project.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import ca.sheridancollege.project.cards.CrazyEightsCard;
import ca.sheridancollege.project.cards.Suit;
import ca.sheridancollege.project.cards.Value;

/**
 * A class acting as the view for the program
 * 
 * @author Ryan Stewart
 */
public class CrazyEightsUI {

    private Scanner input = new Scanner(System.in);

    public void display(String message) {
        System.out.println(message);
    }

    public void displayWelcome(String plyName, String gameName) {
        System.out.println("\nGreetings, " + plyName + ".\nWelcome to " + gameName + ".");
    }

    /**
     * Display the rules of the game
     */
    public void displayRules()
    {
        System.out.println("Here are the rules: ");
        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "1. Get rid of the cards in your hand.",
                "2. You can place any card with a matching suit or value.",
                "3. Eight cards change the suit of the top card.",
                "4. You can play down multiple cards of the same value on the same turn.",
                "5. Queen of Spades on top of the discard pile makes the next player pick up 5 cards.",
                "6. Two makes the next player pick up two.",
                "7. Twos stack");
    }

    public void displayPlayerWin(String name) {
        System.out.println("Congratulations " + name + "!");
        System.out.println("You just won the game!");
    }

    public void displayPlayerLoss(String name) {
        System.out.println(name + " wins!");
        System.out.println("Sorry 'bout the loss; try again next time");
    }

    public String getPlayerName() {
        System.out.print("Enter your name: ");
        return input.nextLine();
    }

    public int getNumberOfOpponents() {
        int numberOfOpponents = 1;
        try
        {
            System.out.print("Enter the number of opponents you would like to play against between 1 and 3: ");
            numberOfOpponents = input.nextInt();
        } catch (InputMismatchException ex)
        {
            System.out.println("That's not an integer!");
            System.out.println("Default of 1 is used.");
        }

        if (numberOfOpponents < 1 || numberOfOpponents > 3)
        {
            System.out.println("Number out of range.");
            System.out.println("Default of one is used.");
            numberOfOpponents = 1;
        }
        return numberOfOpponents;
    }

    public char promptRestoreFromSave() {
        System.out.println("Would you like to restore from save?");
        return input.next().charAt(0);
    }

    public void printCard(Value value, Suit suit, int index) {
        System.out.printf("%d: %-6sof %s\n", index, value, suit);
    }

    public void printCard(CrazyEightsCard card) {
        System.out.printf("%-6sof %s\n", card.getValue(), card.getSuit());
    }

    /**
     * Text prompt for the user to enter a suit number
     * when placing an eight.
     */
    public int promptForSuit()
    {
        System.out.println("What would you like the top suit to be:");
    
        for (Suit suit: Suit.values())
        {
            System.out.printf("%d:%s\n", suit.ordinal(), suit.name());
        }

        return input.nextInt();
    }
    
    public String promptForCard() {
        System.out.print("Enter a card: ");

        return input.next();
    }

    public String promptConfirmQuit() {
        System.out.println("Are you sure you wanna end the game? (y/n):");
        return input.next();
    }

    public char promptForSave() {
        System.out.println("Do you want to save the game before you go?");
        return input.next().charAt(0);
    }

    public String promptAnotherCard() {
        System.out.println("Would you like to enter another card (y/n [default]):");

        return input.next();
    }
    
}
