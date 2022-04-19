package ca.sheridancollege.project.io;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.players.Player;

import java.io.IOException;
import java.io.EOFException;
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
     * Sets objects in the game according to what they are in the save file.
     *
     * @param discardPile
     * @param stockPile
     * @param players
     */
    public void restore(DiscardPile discardPile, StockPile stockPile, ArrayList<Player> players)
    {
        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            discardPile = (DiscardPile) input.readObject();
            discardPile.printPile();
            stockPile = (StockPile) input.readObject();
            players = (ArrayList<Player>) input.readObject();
        } catch (ClassNotFoundException ex)
        {
        } catch (EOFException ex)
        {
            System.out.println("File " + SAVE_FILE_NAME + " unexpectedly ended");
        } catch (IOException ex)
        {
        }
    }

    public DiscardPile restoreDiscardPile()
    {

        DiscardPile discardPile = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            discardPile = (DiscardPile) input.readObject();
        } catch (ClassNotFoundException ex)
        {
        } catch (EOFException ex)
        {
            System.out.println("File " + SAVE_FILE_NAME + " unexpectedly ended");
        } catch (IOException ex)
        {
        }

        return discardPile;
    }

    public StockPile restoreStockPile()
    {

        StockPile stockPile = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            input.readObject(); // To read the DiscardPile which comes before the StockPile
            stockPile = (StockPile) input.readObject();
        } catch (ClassNotFoundException ex)
        {
        } catch (EOFException ex)
        {
            System.out.println("File " + SAVE_FILE_NAME + " unexpectedly ended");
        } catch (IOException ex)
        {
        }

        return stockPile;
    }

    public ArrayList<Player> restorePlayers()
    {

        ArrayList<Player> players = null;

        try ( ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));)
        {
            input.readObject(); // Read the DiscardPile
            input.readObject(); // Read the StockPile
            players = (ArrayList<Player>) input.readObject();
        } catch (ClassNotFoundException ex)
        {
        } catch (EOFException ex)
        {
            System.out.println("File " + SAVE_FILE_NAME + " unexpectedly ended");
        } catch (IOException ex)
        {
        }

        return players;
    }

}
