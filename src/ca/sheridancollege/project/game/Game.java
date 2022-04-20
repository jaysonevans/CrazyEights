/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.game;

import ca.sheridancollege.project.players.Player;
import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific child of
 * this class and instantiate the methods given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Jayson Evans Feb 2022
 * @author Justin Beaulne March 2022
 */
public abstract class Game implements Cloneable
{

    private final String name;//the title of the game
    protected ArrayList<Player> players;// the players of the game

    public Game(String name)
    {
        this.name = name;
        players = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers()
    {
        return (ArrayList<Player>) players.clone();
    }

    /**
     * @return the deeply copied ArrayList of players
     */
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        ArrayList<Player> list = new ArrayList<>();

        for (Player player : players)
        {
            list.add((Player) player.clone());
        }

        return list;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players)
    {
        this.players = players;
    }

    /**
     * Play the game. This might be one method or many method calls depending on
     * your game.
     */
    public abstract void play();

    /**
     * @param players When the game is over, use this method to declare and
     * display a winning player.
     */
    //public abstract Player checkForWinner(ArrayList<Player> players);
    public abstract boolean checkForWinner(Player player);

}//end class
