package pgCW;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class Deck implements Serializable, Iterable {
    static final long serialVersionUID = 12355;
    private static final String fName = "deck.txt";
    private final ArrayList<Card> deck = new ArrayList<>();

    public Deck() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) { //for every suit in enum for every rank,deck created
                deck.add(new Card(rank, suit)); //new card created
            }
        }
    }

    private static Object readIn() throws IOException, ClassNotFoundException {
        System.out.println("serialising ////////////////////");
        ObjectInputStream b = new ObjectInputStream(new FileInputStream(new File(fName)));
        return b.readObject();
    }

    private static void writeOut(Serializable obj) throws IOException {
        ObjectOutputStream b = new ObjectOutputStream(new FileOutputStream(new File(fName)));
        b.writeObject(obj);
        b.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Deck a = new Deck();
        System.out.println("Original Deck:" + a);
        a.shuffle();
        System.out.println("\nShuffled :\n" + a);
        System.out.println("Size:" + a.size());
        System.out.println("\nDeal: (top of shuffled deck)\n" + a.deal());
        System.out.println("\nNew Deck: " + a.newDeck());
        writeOut(a);
        System.out.println("/////////////////////");
        //De-serialization
        System.out.println("\ndeserialized: (missing first card of shuffled deck as it was dealt)" + readIn());
        a.deck.clear();
        Card c1 = new Card(Card.Rank.TEN, Card.Suit.DIAMONDS);
        Card c2 = new Card(Card.Rank.TEN, Card.Suit.SPADES);
        Card c3 = new Card(Card.Rank.TWO, Card.Suit.CLUBS);
        Card c4 = new Card(Card.Rank.SIX, Card.Suit.HEARTS);
        a.deck.add(c1);
        a.deck.add(c2);
        a.deck.add(c3);
        a.deck.add(c4);
        System.out.println("Deck created : \n" + a);
        //System.out.println("\nIterated"+a.iterator()+"\n");
///////////////////////////////////NOT WORKING
        writeOut(a);
        //System.out.println("Trying to serialize: " + a);
        //De-serialization
        System.out.println("deserialized: " + readIn());


    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    //find size
    public int size() {
        return deck.size();
    }

    //create new deck
    public Deck newDeck() {
        return new Deck();
    }

    //iterator
    public Iterator<Card> iterator() {
        return new OddEvenIterator(deck);
    }

    public void shuffle() {
        Random random = new Random();
        random.nextInt();

        for (int i = 0; i < deck.size(); i++) {
            int change = i + random.nextInt(deck.size() - i);
            //Card changing index
            Card c = deck.get(i);
            deck.set(i, deck.get(change)); //changing deck
            deck.set(change, c);
        }
    }

    public Card deal() {
        return deck.remove(0);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (Card card : deck) {
            //str is appending card and newline
            s.append(card).append("\n");
        }
        return s.toString();
    }

    public static class OddEvenIterator implements Iterator<Card> {
        int nextCard;
        int oddEven;
        ArrayList<Card> deck;

        public OddEvenIterator(ArrayList<Card> deck) {
            //next card at -2 so that when 2 is added it starts at index 0
            this.deck = deck;
            this.oddEven = 0;
            this.nextCard = -2;
        }

        public boolean hasNext() {
            if (nextCard < deck.size() - 2)
                return true;
                //for even number
            else if (oddEven == 0) {
                //Change Values for odd numbers to be travesed
                nextCard = -1;
                oddEven = 1;
                return true;
            }
            return false;
        }

        //nextcard
        public Card next() {
            if (hasNext())
                return deck.get(nextCard += 2);
            return null;
        }
    }
}