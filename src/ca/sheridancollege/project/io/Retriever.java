package ca.sheridancollege.project.io;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.players.Player;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * This class handles everything related to retrieving the save file.
 *
 * @author Jayson Evans
 */
public class Retriever extends IOHandler
{

    private static Retriever instance = null;

    /**
     * Private constructor for Singleton design pattern.
     */
    private Retriever()
    {

    }

    /**
     * @return the instance of this class
     */
    public static Retriever getInstance()
    {
        if (instance == null)
        {
            instance = new Retriever();
        }

        return instance;
    }

    /**
     * @return the discard pile previously saved
     */
    public DiscardPile restoreDiscardPile()
    {

        DiscardPile discardPile = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            discardPile = (DiscardPile) input.readObject();
        } catch (ClassNotFoundException | IOException ex)
        {
        }

        return discardPile;
    }

    /**
     * @return the stock pile previously saved
     */
    public StockPile restoreStockPile()
    {

        StockPile stockPile = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            input.readObject(); // To read the DiscardPile which comes before the StockPile
            stockPile = (StockPile) input.readObject();
        } catch (ClassNotFoundException | IOException ex)
        {
        }

        return stockPile;
    }

    /**
     * @return the ArrayList of players previously saved
     */
    public ArrayList<Player> restorePlayers()
    {

        ArrayList<Player> players = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            input.readObject(); // Read the DiscardPile
            input.readObject(); // Read the StockPile
            players = (ArrayList<Player>) input.readObject();
        } catch (ClassNotFoundException | IOException ex)
        {
        }

        return players;
    }

}
