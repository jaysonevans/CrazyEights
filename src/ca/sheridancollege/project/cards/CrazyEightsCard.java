package ca.sheridancollege.project.cards;

/**
 * This class models the cards used in the Crazy Eights game.
 *
 * @author Jayson Evans Feb 2022
 * @author Justin Beaulne March 2022
 */
public final class CrazyEightsCard extends Card implements Comparable<CrazyEightsCard>
{

    private Suit suit;
    private Value value;

    /**
     *
     * @param suit of the card (e.g. HEARTS, DIAMONDS)
     * @param value of the card (e.g. TWO, SEVEN, JACK)
     */
    public CrazyEightsCard(Suit suit, Value value)
    {
        setSuit(suit);
        setValue(value);
    }

    /**
     * @return the suit of the card
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * @param suit of the card
     */
    public void setSuit(Suit suit)
    {
        this.suit = suit;
    }

    /**
     * @return the value of the card
     */
    public Value getValue()
    {
        return value;
    }

    /**
     * @param value of the card
     */
    public void setValue(Value value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value.name() + " of " + suit.name();
    }

    /**
     *
     * @param otherCard the card to compare with
     * @return a number based of the suits and values of the cards in relation
     * to each other
     */
    @Override
    public int compareTo(CrazyEightsCard otherCard)
    {
        if (value.ordinal() > otherCard.getValue().ordinal())
        {
            return 1;
        } else if (value.ordinal() < otherCard.getValue().ordinal())
        {
            return -1;
        } else if (value.ordinal() == otherCard.getValue().ordinal())
        {
            if (suit.ordinal() > otherCard.getSuit().ordinal())
            {
                return 1;
            } else
            {
                return -1;
            }
        } else
        {
            return 0;
        }
    }
}
