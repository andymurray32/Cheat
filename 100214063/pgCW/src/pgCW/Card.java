package pgCW;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


////////////////LAMBDAS
public class Card implements Serializable, Comparable<Card> {
    static final long serialVersionUID = 12345;
    private static final String fName = "serialization.txt";
    public final Rank rank;
    public final Suit suit;

    //creates card with with rank and suit parameters
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    //calculate rank difference between 2 cards based on index in the rank enum
    public static int difference(Card card1, Card card2) {
        return Math.abs(card1.rank.ordinal() - card2.rank.ordinal());
    }

    //actual difference in value of the card
    public static int differenceValue(Card card1, Card card2) {
        return Math.abs(card1.rank.getValue() - card2.rank.getValue());
    }

    private static Object readIn() throws IOException, ClassNotFoundException {
        System.out.println("Reading ////////////////////");
        ObjectInputStream a = new ObjectInputStream(new FileInputStream(new File(fName)));
        return a.readObject();
    }

    private static void writeOut(Serializable obj) throws IOException {
        ObjectOutputStream a = new ObjectOutputStream(new FileOutputStream(new File(fName)));
        a.writeObject(obj);
        a.close();
    }

    private static void selectTest(Card c) {
        Card c1 = new Card(Rank.ACE, Suit.DIAMONDS);
        Card c2 = new Card(Rank.TWO, Suit.CLUBS);
        Card c3 = new Card(Rank.THREE, Suit.CLUBS);
        Card c4 = new Card(Rank.THREE, Suit.HEARTS);
        Card c5 = new Card(Rank.THREE, Suit.CLUBS);
        Card c6 = new Card(Rank.TWO, Suit.HEARTS);
        Card c7 = new Card(Rank.TWO, Suit.CLUBS);

        ArrayList a = new ArrayList();
        a.add(c1);
        a.add(c2);
        a.add(c3);
        a.add(c4);
        a.add(c5);
        a.add(c6);
        a.add(c7);
        a.add(c);
        System.out.println("\nOriginal list:");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
        //for compareTO list descending
        System.out.println("\nComparison 1(compareTo):");
        for (int i = 0; i < a.size(); i++) {
            Comparator<Card> comp = Card::compareTo;
            Collections.sort(a, comp);
            System.out.println("changed version: " + a.get(i));
        }
        System.out.print("CompareAscending\n");
        for (int i = 0; i < a.size(); i++) {
            Comparator<Card> dsc = new compareAscending();
            Collections.sort(a, dsc);
            System.out.println("changed version: " + a.get(i));
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Card c8 = new Card(Rank.EIGHT, Suit.HEARTS);
        Card c9 = new Card(Rank.ACE, Suit.SPADES);
        System.out.println("Card Created:" + c8 + " and " + c9 + " (to show to string method)");
        System.out.println("Card difference value= " + Card.differenceValue(c8, c9));
        System.out.println("Card difference = " + Card.difference(c8, c9));
        selectTest(c8);
        System.out.print("Other Methods test on: " + c9);
        System.out.print("\ngetPrevious () =" + c8.getRank().getPrevious());
        System.out.print("\ngetValue() =" + c8.getRank().getValue());
        System.out.print("\ngetRank () =" + c8.getRank());
        System.out.print("\ngetSuit() =" + c8.getSuit() + "\n");
        writeOut(c8);
        System.out.println("Trying to serialize: " + c8);
        //De-serialization
        System.out.println("deserialized: " + readIn());
    }

    //get rank - Accessor
    public Rank getRank() {
        return this.rank;
    }

    //get suit - Accessor
    public Suit getSuit() {
        return this.suit;
    }

    public int compareTo(Card card) {
        int cardComp = card.rank.compareTo(this.rank);
        if (cardComp == 0) {
            cardComp = this.suit.compareTo(card.suit);
        }
        return cardComp;
    }

    public String toString() {

        return rank + " of " + suit;
    }

    //for rank - constructor
    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(11);
        final int value;

        Rank(int x) {
            value = x;
        }

        //get value of card (rank)
        public int getValue() {
            return value;
        }

        //returns the rank value before it
        public Rank getPrevious() {
            if (this.equals(Rank.TWO))
                return Rank.ACE;
            else
                return Rank.values()[this.ordinal() - 1];
        }
    }

    //for suit- constructor
    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES;

        //random suit method
        public Suit randSuit() {

            return Suit.values()[new Random().nextInt(Suit.values().length)];
        }
    }

    //compares rank and put it in ascending order
    public static class compareAscending implements Comparator<Card> {
        public int compare(Card o1, Card o2) {
            return (o1.rank.ordinal() - o2.rank.ordinal());
        }
    }

    //compares suit based on order in the enum
    public static class compareSuit implements Comparator<Card> {
        public int compare(Card o1, Card o2) {
            return o2.compareTo(o1);
        }
    }
}

