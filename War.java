/**
 * Created by Sean Roper on 5/7/14.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;


public class War extends Application{

    public Image playerCard = new Image("/img/2.png");
    public static Image compCard;
    ImageView ivTest = new ImageView(playerCard);
    GridPane box = new GridPane();
    TextArea results = new TextArea();
    TextArea playRemain = new TextArea();


    @Override
    public void start(final Stage primaryStage) {
        Group canvas = new Group();
        final Scene scene = new Scene(canvas, 600, 400);



        primaryStage.setTitle("War");
        primaryStage.setScene(scene);
        primaryStage.show();
        final Button quitBtn = new Button("Quit.");
        quitBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });

        final Button contBtn = new Button("Continue?");
        contBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                draw();
            }
        });
        Text play1 = new Text("Player 1");
        Text comp1 = new Text("Computer");
        Image image1 = new Image("/img/1.png");
        ImageView iv1 = new ImageView(image1);
        Image image2 = new Image("/img/2.png");
        ImageView iv2 = new ImageView(image2);
        playRemain.setMaxSize(300, 30);
        playRemain.setText("Player 1 has " + playerHand.size() + " cards. The Computer has " + compHand.size() + " cards.");
        results.setMaxSize(300,120);
        results.setWrapText(true);

        GridPane.setConstraints(iv1, 0, 1);
        GridPane.setConstraints(iv2, 1, 1);
        GridPane.setConstraints(contBtn, 0, 2);
        GridPane.setConstraints(quitBtn, 1, 2);
        GridPane.setConstraints(play1, 0, 0);
        GridPane.setConstraints(comp1, 1, 0);
        GridPane.setConstraints(playRemain,3,1);
        GridPane.setConstraints(results,3,2);


        box.setHgap(10);
        box.setVgap(10);
        box.getChildren().addAll(play1, comp1, iv1, iv2, contBtn, quitBtn, playRemain, results);
        canvas.getChildren().add(box);


    }
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
        Application.launch(args);


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
            compHand.add(a[i + 26]);
        }
    }

    public static void shuffle(ArrayList<Card> a){

        for (int i = 0; i < a.size(); i++) {
            a.get(i).setValue(Math.random());
        }
        sortHand(a);
    }

    public void draw(){
        if(playerHand.size()==0){results.setText("YOU LOST THE GAME");}
        if(compHand.size()==0){results.setText("YOU WIN");}
        Card p1 = playerHand.get((int) (Math.random()*playerHand.size()));
        Card p2 = compHand.get((int) (Math.random()*compHand.size()));
        String pl = p1.suit + "-" + p1.rank;
        String co = p2.suit + "-" + p2.rank;
        playerCard = new Image("/img/" + pl + ".png");
        ivTest = new ImageView(playerCard);
        GridPane.setConstraints(ivTest, 0, 1);

        compCard = new Image("/img/" + co + ".png");
        ImageView iv3 = new ImageView(compCard);
        GridPane.setConstraints(iv3, 1, 1);



        box.getChildren().addAll(ivTest, iv3);




        if (p1.rank == p2.rank){
            results.setText("You tied, prepare for WAR!");
            war(0);
        } else if (p1.rank > p2.rank){
            results.setText("You won this hand.");
            playerHand.add(compHand.get(0));
            compHand.remove(0);
        } else {
            results.setText("You lost this hand");
            compHand.add(playerHand.get(0));
            playerHand.remove(0);
        }
        playRemain.setText("Player 1 has " + playerHand.size() + " cards. The Computer has " + compHand.size() + " cards.");

        shuffle(playerHand);
        shuffle(compHand);
        shuffle(playerHand);
        shuffle(compHand);

    }

    public void war(int a){
        if (playerHand.size()<=a) a=playerHand.size()-1;
        if (compHand.size()<=a) a=compHand.size()-1;
        Card play1 = playerHand.get(a);
        Card play2 = compHand.get(a);

        results.setText("In the war, Player 1 drew " + play1 + " Player 2 drew " + play2);
        String pl = play1.suit + "-" + play1.rank;
        String co = play2.suit + "-" + play2.rank;
        playerCard = new Image("/img/" + pl + ".png");
        ivTest = new ImageView(playerCard);
        GridPane.setConstraints(ivTest, 0, 1);
        compCard = new Image("/img/" + co + ".png");
        ImageView iv3 = new ImageView(compCard);
        GridPane.setConstraints(iv3, 1, 1);
        box.getChildren().addAll(ivTest, iv3);

        if (play1.rank == play2.rank){
            results.appendText(". You tied, prepare to battle again.");
            a+=4;
            war(a);

        } else if (play1.rank > play2.rank){
            results.appendText(". Player 1 won the war. The cards you won were:");
            for (int m = 0; m < a; m++) {
                results.appendText(compHand.get(0).toString());
                playerHand.add(compHand.get(0));
                compHand.remove(0);
            }
        } else {
            results.appendText(". Player 2 won the war. The cards you lost were:");
            for (int m = 0; m < a; m++) {
                results.appendText(playerHand.get(0).toString());
                compHand.add(playerHand.get(0));
                playerHand.remove(0);
            }

        }
    }
}