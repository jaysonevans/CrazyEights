package ca.sheridancollege.project.io;

import ca.sheridancollege.project.cards.DiscardPile;
import ca.sheridancollege.project.cards.StockPile;
import ca.sheridancollege.project.players.Player;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class handles everything related to writing to the file, most
 * importantly, saving a game.
 *
 * @author Jayson Evans
 */
public class Saver extends IOHandler
{

    public static Saver instance = null;

    /**
     * Private constructor for Singleton design pattern.
     */
    private Saver()
    {

    }

    /**
     * @return the instance of this class
     */
    public static Saver getInstance()
    {
        if (instance == null)
        {
            instance = new Saver();
        }

        return instance;
    }

    /**
     * Saves the objects necessary to resume the game to a file.
     *
     * @param discardPile
     * @param stockPile
     * @param players
     */
    public void save(DiscardPile discardPile, StockPile stockPile, ArrayList<Player> players)
    {
        try ( ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(SAVE_FILE_NAME)));)
        {
            output.writeObject(discardPile);
            output.writeObject(stockPile);
            output.writeObject(players);
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        /*
        try
        {
            testCorrectValuesSaved();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
*/
    }

    /*
    public void testCorrectValuesSaved() throws IOException, ClassNotFoundException
    {
        ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(SAVE_FILE_NAME)));
        DiscardPile discardPile = (DiscardPile) input.readObject();
        discardPile.printPile();
        input.close();
    }
*/

}
