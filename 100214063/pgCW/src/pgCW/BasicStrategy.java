package pgCW;



/*
 * You should implement a new class BasicStrategy that implements the Strategy interface provided.  Basic strategy should
1.	Never cheat
2.	Always play all the cards of the lowest value possible
*/

public interface Strategy {

    /**
     *
     * @param b   the bid the player has to follow.
     * @param h      The players current hand
     * @param cheat true if the Strategy has decided to cheat (by call to cheat())
     *
     * @return a Bid with the cards to pass to the game and the Rank. This will be
     * different to the rank of thecards if the player is cheating!
     *
     */ {
        //Checking whether the player has cards that is equal to the current
        //bid rank or one above
        //If so false is returned if not the player will have to cheat
        /////////////////////not working
        Bid b = new Bid();
        Hand h = new Hand();
        h.countRank;
        return !h.countRank[b.getRank().getValue() > 0 || h.countRank(b.getRank().getPrevious().getValue()) > 0; return true;
    }

    {
        return true;
    }

getValue()

    /**
     * Decides on whether to cheat or not
     *
     * @param b the bid this player has to follow (i.e the
     *          bid prior to this players turn.
     * @param h The players current hand
     * @return true if the player will cheat, false if not
     */
    boolean cheat(Bid b, Hand h);
    //if the count of that value across all suits is more than 4 because you have some in your hand to check
    if(b.getCount()>4-h.countRank(b.getRank().

        Bid chooseBid(Bid b, Hand h, boolean cheat);))

    /**
     * @param b the current bid
     * @return true if this player is going to call cheat  on the last play b
     */

    boolean callCheat(Hand h, Bid b);
    return false
}
