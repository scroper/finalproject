/**
 * Created by Sean Roper on 5/7/14.
 */
public class Card implements Comparable<Card> {

    protected int suit;
    protected int rank;
    protected int owner;
    protected double value;


    private static final String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };
    private static final String[] ranks = { "narf", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    private static final int[] owners = {1, 2};

    /**
     * No-argument constructor.
     */
    public Card() {
        this(0, 0, 0, 0);
    }

    /**
     * Constructor with arguments.
     */
    public Card(int suit, int rank, int owner, double value) {
        this.suit = suit;
        this.rank = rank;
        this.owner = owner;
        this.value = value;
    }

    /**
     * Prints a card in human-readable form.
     */
    @Override
    public String toString() {
        return ranks[rank] + " of " + suits[suit];
    }

    /**
     * Return true if the cards are equivalent.
     */
    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj instanceof Card) {
            Card that = (Card) obj;
            b = this.compareTo(that) == 0;
        }
        return b;
    }

    public static void printCard(Card card) {
        System.out.println(card);
    }

    /**
     * Compares two cards: returns 1 if the first card is greater
     * -1 if the seconds card is greater, and 0 if they are the equivalent.
     */
    public int compareTo(Card that) {
        // first compare the suits
        if (this.value > that.value) return 1;
        if (this.value < that.value) return -1;

        // compare the ranks
        if (this.rank > that.rank) return 1;
        if (this.rank < that.rank) return -1;
        return 0;
    }

    public void setOwner(int owner){
        this.owner = owner;
    }
    public void setRank(int rank) {this.rank = rank; }
    public void setValue(Double value){
        this.value = value;
    }
}