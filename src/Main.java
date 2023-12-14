import java.util.Random;

public class Main {
    public static void main(String[] args) {

        System.out.println();
        System.out.println("Welcome to the Bluejack Game.");
        System.out.println();

        String[] gameDeckColor = {"blue", "yellow", "red", "green"};
        int[] gameDeck = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Card[] Deck = new Card[40];

        Card[] computerDeck = new Card[10];
        Card[] userDeck = new Card[10];


        int l = 0;
        for (int i = 0; i < gameDeckColor.length; i++) {
            for (int j = 0; j < gameDeck.length; j++) {
                Deck[l++] = new Card(gameDeckColor[i], gameDeck[j]);
            }
        }
        System.out.println("**********  The deck is shuffling... **********");
        System.out.println();
        Shuffle(Deck);
        for (int c = 0; c < Deck.length; c++) {
            System.out.println(Deck[c]);
        }

        System.out.println("********************");


        Dealing(Deck, computerDeck, userDeck);


        int[] remainingCard = {1, 2, 3, 4, 5, 6};
        int [] sign = {-1, +1};
        RemainingCard[] remainingDeck = new RemainingCard[48];  // 4 different colors, numbers 1 to 6 and 2 different signs

        int m = 0;
        for (int i = 0; i < gameDeckColor.length; i++) {
            for (int j = 0; j < remainingCard.length; j++) {
                for (int k = 0; k < sign.length; k++) {
                    remainingDeck[m++] = new RemainingCard(gameDeckColor[i], remainingCard[j], sign[k]);
                }
            }
        }

        System.out.println();
        System.out.println("**********  Remaining cards are shuffling...  **********");
        ShuffleRemain(remainingDeck);
        for (int r = 0; r < remainingDeck.length; r++) {
            System.out.println(remainingDeck[r]);
        }

        System.out.println("********************");

        // Dealing the remaining 3 cards to the computer and user
        RemainingCard [] computerRemainingDeck = new RemainingCard[3];
        RemainingCard [] userRemainingDeck = new RemainingCard[3];

        RemainingCard [] computerSpecial = new RemainingCard[1];
        RemainingCard [] userSpecial = new RemainingCard[1];

        ShuffleRemain(remainingDeck);
        for (int i=0; i<3; i++){
            userRemainingDeck[i] = remainingDeck[i];
            userRemainingDeck[i].decreaseRemainingCard();  // kart çekildiğinde sayacı azaltması için
            computerRemainingDeck[i] =remainingDeck[i+3];
            computerRemainingDeck[i].decreaseRemainingCard(); // kart çekildiğinde sayacı azaltması için
        }

        System.out.println();
        System.out.println("Player's Remaining Cards: ");
        for (int i=0; i<userRemainingDeck.length; i++){
            System.out.println(userRemainingDeck[i]);
            userRemainingDeck[i].decreaseRemainingCard();   // her seferinde sayacı azaltıyor
        }

        System.out.println();

        System.out.println("Computer's Remaining Cards: ");
        for (int i=0; i<userRemainingDeck.length; i++){
            System.out.println(computerRemainingDeck[i]);
            computerRemainingDeck[i].decreaseRemainingCard();   // her seferinde sayacı azaltıyor
        }

        
        System.out.println("********************");

        String flip= "+/-";
        int db= 1*2;
        Random r = new Random ();
        for (int i=0; i<2; i++){
           int a  = r.nextInt(10);
           System.out.println("Randomly selected card is " + a);

           if (a==0){
               System.out.println("It is flip card! " + flip);
           } else if (a==1){
               System.out.println("It is double card! " + db);
           } else {
               Special(remainingDeck);
               for (int s=0; s<1; s++){
                   userSpecial[s] = remainingDeck[s];
                   userSpecial[s].decreaseRemainingCard();  // kart çekildiğinde sayacı azaltması için???????????
                   computerSpecial[s] =remainingDeck[s+3];
                   computerSpecial[s].decreaseRemainingCard(); // kart çekildiğinde sayacı azaltması için
               }

               System.out.println();
               System.out.println("Player's Remaining Cards: ");
               for (int j=0; j<userSpecial.length; j++){
                   System.out.println(userSpecial[j]);
                   userSpecial[j].decreaseRemainingCard();
               }

               System.out.println();

               System.out.println("Computer's Remaining Cards: ");
               for (int k=0; k<computerSpecial.length; k++){
                   System.out.println(computerSpecial[k]);
                   computerSpecial[k].decreaseRemainingCard();
               }
           }
        }
    }
    public static void Shuffle(Card[] Deck) {
        Random r = new Random();
        for (int i = 0; i < Deck.length; i++) {
            int a = r.nextInt(Deck.length);
            int b = r.nextInt(Deck.length);
            Swap(Deck, a, b);
            /*
            Card temp = Deck[a];
            Deck[a] = Deck[b];
            Deck[b] = temp;
             */
        }
    }

    public static void Swap(Card[] Deck, int i, int j) {
        Card temp = Deck[i];
        Deck[i] = Deck[j];
        Deck[j] = temp;
    }

    public static void Dealing(Card [] Deck, Card [] computerDeck, Card [] userDeck){
        System.out.println();
        System.out.println("**********  Cards are dealing...  **********");
        System.out.println();

        for (int j=0; j<5; j++){
            // The card at the top is given to the computer.
            computerDeck[j] = Deck[j];
            computerDeck[j].decreaseRemainingCount();   // kart çekildikten sonra sayacı azzaltıyor
            System.out.println("Computer Deck is = " + computerDeck[j]);
        }
        System.out.println("********************");
        for (int k=Deck.length-1; k>=Deck.length-5; k--){
            // The card at the bottom is given to the user.
            userDeck[39-k] = Deck [k];
            userDeck[39-k].decreaseRemainingCount();   // kart çekildikten sonra sayacı azzaltıyor
            System.out.println("User Deck is = " + userDeck[39-k]);
        }
    }

    public static void ShuffleRemain(RemainingCard[] remainingDeck){
        Random r = new Random();
        for (int s = 0; s < remainingDeck.length; s++) {
            int a = r.nextInt(remainingDeck.length);
            int b = r.nextInt(remainingDeck.length);
            int c = r.nextInt(remainingDeck.length);

            RemainingCard temp = remainingDeck[a];
            remainingDeck[a] = remainingDeck[b];
            remainingDeck[b] = remainingDeck[c];
            remainingDeck[c] = temp;

        }
    }
    public static void Special (RemainingCard[] remainingDeck){
        Random r = new Random();
        for (int i = 0; i < remainingDeck.length; i++) {
            int a = r.nextInt(remainingDeck.length);

            RemainingCard temp = remainingDeck[a];
            remainingDeck[a] = temp;
        }
    }

  /*  public static void fourCards (Card [] Deck, RemainingCard[] remainingDeck, Card [] computerDeck, Card [] userDeck, RemainingCard [] computerRemainingDeck, RemainingCard [] userRemainingDeck){

    }

   */
}



