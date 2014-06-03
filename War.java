/**
 * Created by Sean Roper on 5/7/14.
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


public class War {

    static Card[] deck = new Card[52];
    public static ArrayList<Card> playerHand = new ArrayList<Card>();
    public static ArrayList<Card> compHand = new ArrayList<Card>();

    public static void main(String[] args) {

        //make a normal 52 card playing deck 2-A, 4 suits
        makeDeck(deck);
        //randomize the deck
        sortDeck(deck);
        //Assign 26 cards to each player
        initAssign(deck);
        //Play the game
        draw();

    }

    public static void makeDeck(Card[] a){
        for (int i = 0; i < 13; i++) {
            //clubs
            a[i] = new Card(0, i+1, Math.random());
            //diamonds
            a[i+13] = new Card(1, i+1, Math.random());
            //hearts
            a[i+26] = new Card(2, i+1, Math.random());
            //spades
            a[i+39] = new Card(3, i+1, Math.random());
        }
    }
    public static void sortDeck(Card[] a){
        Arrays.sort(a);
    }

    public static void sortHand(ArrayList<Card> a){
        Card[] b = new Card[a.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = a.get(i);
        }
        sortDeck(b);
    }

    public static void initAssign(Card[] a){
        for (int i = 0; i < a.length/2; i++) {
            playerHand.add(a[i]);
            compHand.add(a[i+26]);
        }
    }

    public static void shuffle(ArrayList<Card> a){

        for (int i = 0; i < a.size(); i++) {
            a.get(i).setValue(Math.random());
        }
        sortHand(a);
    }

    public static void cardCount(){
        int p1 = playerHand.size();
        int p2 = compHand.size();
        if(p1 == 0){
            System.out.println("Player 2 wins!");
        } else if(p2 == 0) {
            System.out.println("Player 1 wins!");
        } else {
            System.out.println("Player 1 has " + p1 + " cards, Player 2 has " + p2 + " cards.");
        }
    }

    public static void draw(){
        Card p1 = playerHand.get(0);
        Card p2 = compHand.get(0);

        showCard(p1, p2);

        if (p1.rank == p2.rank){
            System.out.println("You guys tied. Get ready for a WAR!!");
            war(0);
        } else if (p1.rank > p2.rank){
            System.out.println("Player 1 wins this hand.");
           playerHand.add(compHand.get(0));
            compHand.remove(0);
        } else {
            System.out.println("Player 2 wins this hand.");
            compHand.add(playerHand.get(0));
            playerHand.remove(0);
        }
        cardCount();
        shuffle(playerHand);
        shuffle(compHand);
        cont();
    }

    public static void cont(){
        System.out.println("Are you ready for the next hand? (q to quit anything else to continue)");
        Scanner s = new Scanner(System.in);
        String ready = s.nextLine();
        if(ready.equalsIgnoreCase("q")){
            System.exit(0);
        } else{
            draw();
        }
    }

    public static void war(int a){

        Card play1 = playerHand.get(a);
        Card play2 = compHand.get(a);

        System.out.println("In the war, Player 1 drew " + play1 + " Player 2 drew " + play2);
        if (play1.rank == play2.rank){
            System.out.println("You tied, prepare to battle again.");
            a+=4;
            war(a);

        } else if (play1.rank > play2.rank){
            System.out.println("Player 1 won the war. The cards you lost were:" );
            for (int m = 0; m < a; m++) {
                System.out.println(compHand.get(0));
                playerHand.add(compHand.get(0));
                compHand.remove(0);
            }
        } else {
            System.out.println("Player 2 won the war. The cards you lost were:");
            for (int m = 0; m < a; m++) {
                System.out.println(playerHand.get(0));
                compHand.add(playerHand.get(0));
                playerHand.remove(0);
            }

        }
    }

    public static void printAll(ArrayList<Card> a){
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }

    }
       public static void showCard(Card a, Card b){
           String player = "";
           String comp = "";
           String playRank = a.rank + "";
           String compRank = b.rank + "";
           if(a.suit==0) player = "♣";
           if(a.suit==1) player = "♦";
           if(a.suit==2) player = "♥";
           if(a.suit==3) player = "♠";
           if(b.suit==0) comp = "♣";
           if(b.suit==1) comp = "♦";
           if(b.suit==2) comp = "♥";
           if(b.suit==3) comp = "♠";
           if(a.rank==11) playRank = "J";
           if(a.rank==12) playRank = "Q";
           if(a.rank==13) playRank = "K";
           if(a.rank==1) playRank = "A";
           if(b.rank==11) compRank = "J";
           if(b.rank==12) compRank = "Q";
           if(b.rank==13) compRank = "K";
           if(b.rank==1) compRank = "A";
           System.out.println("Player 1           Player 2");
           System.out.println(" _______            _______");
        System.out.println("|" +playRank+player+"    |           |" +compRank+comp+"    |");
        System.out.println("|       |          |       |");
        System.out.println("|       |          |       |");
        System.out.println("|    " +playRank+player+"|           |    " +compRank+comp+"|");
        System.out.println(" -------            ------- ");
    }
    }
