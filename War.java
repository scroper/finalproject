/**
 * Created by Sean Roper on 5/7/14.
 */
import java.util.Scanner;
import java.util.Arrays;

public class War {
    static Card[] deck = new Card[52];

    public static void main(String[] args) {
        //make a normal 52 card playing deck 2-A, 4 suits
        makeDeck(deck);
        //randomize the deck
        sortDeck(deck);
        //Assign 26 cards to each player
        initAssign(deck);
        //display the number of cards each player has
        cardCount(deck);
        //Play the game
        draw(deck);
    }

    public static void makeDeck(Card[] a){
        for (int i = 0; i < 13; i++) {
            //clubs
            a[i] = new Card(0, i+1, 0, Math.random());
            //diamonds
            a[i+13] = new Card(1, i+1, 0, Math.random());
            //hearts
            a[i+26] = new Card(2, i+1, 0, Math.random());
            //spades
            a[i+39] = new Card(3, i+1, 0, Math.random());
        }
    }
    public static void sortDeck(Card[] a){
        Arrays.sort(a);
    }

    public static void initAssign(Card[] a){
        for (int i = 26; i < a.length; i++) {
            a[i].setOwner(1);
        }
    }

    public static void shuffle(Card[] a){
        for (int i = 0; i < a.length; i++) {
            a[i].setValue(Math.random());
        }
        sortDeck(a);
    }

    public static void cardCount(Card[] a){
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i].owner == 0){
                p1++;
            }else {
                p2++;
            }
        }
        if(p1 == 0){
            System.out.println("Player 2 wins!");
        } else if(p2 == 0) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 1 has " + p1 + " cards, Player 2 has " + p2 + " cards.");
        }
    }

    public static void draw(Card[] a){
        Card p1 = new Card();
        Card p2 = new Card();
        int p1pos = 0;
        int p2pos = 0;
        int count = 0;
        int count2 = 0;

        for (int i = 0; i < a.length; i++) {
            if(a[i].owner == 0 && count < 1){
                p1 = a[i];
                p1pos = i;
                count++;
            } else if (a[i].owner == 1 && count2 < 1){
                p2 = a[i];
                p2pos = i;
                count2++;
            }
        }
        System.out.println("Player 1 drew " + p1 + " Player 2 drew " + p2);
        if (p1.rank == p2.rank){
            System.out.println("You guys tied. Get ready for a WAR!!");
            war(p1pos ,p2pos , a);
        } else if (p1.rank > p2.rank){
            System.out.println("Player 1 wins this hand.");
            p2.setOwner(0);
        } else {
            System.out.println("Player 2 wins this hand.");
            p1.setOwner(1);
        }
        cardCount(a);
        shuffle(a);
        cont(a);
    }

    public static void cont(Card[] a){
        System.out.println("Are you ready for the next hand? (q to quit anything else to continue)");
        Scanner s = new Scanner(System.in);
        String ready = s.nextLine();
        if(ready.equalsIgnoreCase("q")){
            System.exit(0);
        } else{
            draw(a);
        }
    }

    public static void war(int a, int b,Card[] c){
        Card play1 = new Card();
        Card play2 = new Card();
        int[] one = {0, 0, 0};
        int[] two = {0, 0, 0};
        int j = 0;
        int k = 0;
        int z = 0;
        int y = 0;
        int count = 0;
        int count2 = 0;

        for (int i = a+1; i < c.length; i++) {
            if(one[2] == 0){
                if(c[i].owner == 0 && c[i] != play1){
                    one[j] = i;
                    j++;
                }
            }else if (c[i].owner == 0 && count < 1){
                play1 = c[i];
                z = i;
                count++;
            }
        }
        for (int l = b+1; l < c.length; l++) {
            if(two[2] == 0){
                if(c[l].owner == 1 && c[l] != play2){
                    two[k] = l;
                    k++;
                }
            }else if (c[l].owner == 1 && count2 < 1){
                play2 = c[l];
                y = l;
                count2++;
            }
        }
        System.out.println("In the war, Player 1 drew " + play1 + " Player 2 drew " + play2);
        if (play1.rank == play2.rank){

            System.out.println("Player 1's hidden cards were:");
            for (int m = 0; m < one.length; m++) {
                System.out.println(c[one[m]]);
            }
            System.out.println("Player 2's were:");
            for (int m = 0; m < two.length; m++) {
                System.out.println(c[two[m]]);
            }
            System.out.println("You can keep the cards but prepare for the next war!");

            war(z, y, c);
        } else if (play1.rank > play2.rank){
            System.out.println("Player 1 won the war. The cards you lost were:" );
            for (int m = 0; m < two.length; m++) {
                c[two[m]].setOwner(0);
                System.out.println(c[two[m]]);
            }
            play2.setOwner(0);

        } else {
            System.out.println("Player 2 won the war. The cards you lost were:");
            for (int m = 0; m < one.length; m++) {
                c[one[m]].setOwner(1);
                System.out.println(c[one[m]]);
            }
            play1.setOwner(1);

        }
    }

    public static void printAll(Card[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
