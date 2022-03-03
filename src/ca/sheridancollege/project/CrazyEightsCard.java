package ca.sheridancollege.project;

/**
 * This class models the cards used in the Crazy Eights game.
 *
 * @author Jayson Evans Feb 2022
 */
public final class CrazyEightsCard extends Card implements Comparable<CrazyEightsCard>
{
    private Suit suit;
    private Value value;
    
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
    
    @Override
    public int compareTo(CrazyEightsCard otherCard)
    {
        if (value.ordinal() > otherCard.getValue().ordinal())
        {
            return 1;
        }
        else if (value.ordinal() < otherCard.getValue().ordinal())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
