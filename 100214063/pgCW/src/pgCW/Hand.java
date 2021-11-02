package pgCW;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class Hand implements Serializable, Iterable {
    static final long serialVersionUID = 12365;
    Collection<Card> hand;
    private int[] countRank;
    private int[] suitArray;

    //creates new instance of card
    public Hand() {
        this.suitArray = new int[4]; // to tally how much of each suit
        this.countRank = new int[13]; //to tally how much of each rank
        this.hand = new ArrayList();
    }

    //card array to hand
    public Hand(Card[] cards) {
        for (Card a : cards) {
            hand.add(a);
        }
    }

    //new instance of Hand
    public Hand(Hand newH) {
        for (Object card : newH) {
            hand.add((Card) card);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Hand a = new Hand();
        Hand b = new Hand();
        Card c1 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.TEN, Card.Suit.SPADES);
        Card c3 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card c4 = new Card(Card.Rank.SIX, Card.Suit.HEARTS);
        Card c5 = new Card(Card.Rank.THREE, Card.Suit.SPADES);
        Card c6 = new Card(Card.Rank.FIVE, Card.Suit.CLUBS);
        Card c7 = new Card(Card.Rank.FIVE, Card.Suit.HEARTS);
        a.add(c1);
        a.add(c2);
        a.add(c3);
        a.add(c4);
        b.add(c6);
        b.add(c7);
        System.out.println("Original Hand:" + a);
        a.add(c5);
        System.out.println("\nAdding card to hand:\n" + a);
        System.out.println("\nhand size:" + a.handSize());
        System.out.println("\nRemoving 4th card:" + a.remove(4) + "\n Hand:\n" + a);
        a.add(b);
        System.out.println("\nAdding another hand to first hand\n" + a);
        a.remove(b);
        System.out.println("\nRemoving second hand\n" + a);
        a.add(b);

        System.out.println("\nCount Rank\n" + a.countRank);
        System.out.println("\nSuit Array\n" + a.suitArray);

    }

    //get Rank array
    public int[] getRankArray() {
        return countRank;
    }

    //get Suit array
    public int[] getSuitArray() {
        return suitArray;
    }

    //adding a single card
    public void add(Card a) {
        countRank[a.getRank().getValue()]++; //adding to tally of value
        suitArray[a.getSuit().ordinal()]++; // adding to tally of suit
        hand.add(a);
    }

    //adding a collection
    public void add(Collection<Card> cardCollection) {
        for (Card card : cardCollection) {
            countRank[card.getRank().getValue()]++; //add to tally
            suitArray[card.getSuit().ordinal()]++; //add to tally
            hand.add(card);
        }
    }

    //adding a hand
    public void add(Hand hand2) {
        for (Object card : hand2) {
            Card a = (Card) card;
            countRank[((Card) card).getRank().getValue()]++; //add to tally
            suitArray[((Card) card).getSuit().ordinal()]++;// add to tally
            hand.add(a);
        }
    }

    //remove certain card
    public boolean remove(Card certainCard) {
        for (Card card : hand) {
            if (certainCard == card) {
                countRank[card.getRank().getValue()]--; // remove card from tally
                suitArray[card.getSuit().ordinal()]--; //remove card from tally
                hand.remove(card);
                return true;
            }
        }
        return false;
    }

    public int handSize() {
        return hand.size();
    }

    //remove a certain hand
    public boolean remove(Hand certainHand) {
        int removedCards = 0;
        for (Object card : certainHand) { //given certain hand remove those cards
            Card certainCard = (Card) card;
            if (hand.contains(certainCard)) {
                countRank[((Card) card).getRank().getValue()]--;//decrease tally
                suitArray[((Card) card).getSuit().ordinal()]--;//decrease tally
                hand.remove(certainCard);
                removedCards++;
            }
        }
        //Only return true if all cards given were removed.
        return removedCards == certainHand.handSize();
    }

    //remove card from  a location in hand
    public Card remove(int rem) {
        int place = 0; // place of card
        for (Card card : hand) {
            if (place == rem) //loop through for when the position matches
            {
                countRank[card.getRank().getValue()]--;//decrease tally
                suitArray[card.getSuit().ordinal()]--;//decrease tally
                hand.remove(card); //remove the card in that location
                return card;
            }
            place++; //look at next postition
        }
        return null;
    }

    public boolean isFlush() {
        if (hand.isEmpty()) {
            return false;
        } else {
            //get the first suit and compare the rest to its suit
            Card card = (Card) hand.toArray()[0];
            Card.Suit suit = card.getSuit(); //Get first suit
            for (int i = 1; i < hand.size(); i++) {
                Card a = (Card) hand.toArray()[i];
                Card.Suit newSuit = a.getSuit();
                if (suit != newSuit) //Compare first suit with other suit.
                    return false; //false if they dont match
            }
            return true;
        }
    }

    public boolean isStraight() {
        Collections.sort((ArrayList<Card>) hand);
        //loops through all cards to check
        for (int i = 0; i < hand.size(); i++) {
            //check if the cards are in consecutive order
            Card card = (Card) hand.toArray()[i];
            if (card.getRank().getValue() != card.getRank().getValue())
                return false;
        }
        return true;
    }

    public Iterator<Card> iterator() {
        Iterator<Card> b = new Iterator<Card>() {
            private int index = 0;

            //check if there is a next card
            public boolean hasNext() {
                return index < hand.size();
            }

            public Card next() {
                if (hasNext()) {
                    return (Card) hand.toArray()[index++];
                }
                return null;
            }
        };
        return b;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Card card : hand) {
            //appends card and newline
            s.append(card).append("\n");
        }
        return s.toString();
    }

    //compares rank and put it in descending order
    public static class compareDescending implements Comparator<Card> {
        public int compare(Card o1, Card o2) {
            return (o2.rank.ordinal() - o1.rank.ordinal());
        }
    }
}
