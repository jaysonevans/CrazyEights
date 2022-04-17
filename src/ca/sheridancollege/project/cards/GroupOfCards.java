/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.cards;

import ca.sheridancollege.project.game.CrazyEightsUI;
import java.util.ArrayList;
import java.util.Collections;
import java.io.Serializable;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you
 * might want to subclass this more than once. The group of cards has a maximum
 * size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author Jayson Evans Feb 2022
 * @author Ryan Stewart
 * @author Justin Beaulne March 2022
 */
public class GroupOfCards implements Serializable
{

    // Constants add modularity to the number of cards in the game
    public final static int NUMBER_OF_VALUES = Value.values().length;
    public final static int NUMBER_OF_SUITS = Suit.values().length;

    //The group of cards, stored in an ArrayList
    protected ArrayList<CrazyEightsCard> cards = new ArrayList<>();
    protected transient CrazyEightsUI view = CrazyEightsUI.getInstance();
    private int size;//the size of the grouping

    public GroupOfCards(int size)
    {
        this.size = size;
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<CrazyEightsCard> getCards()
    {
        return cards;
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize()
    {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size)
    {
        this.size = size;
    }

    /**
     * Print the cards in the pile. Mainly for testing.
     */
    public void printPile()
    {
        view.display("The discard pile contains:");
        for (CrazyEightsCard card : cards)
        {
            view.printCard(card);
        }
    }
}//end class
