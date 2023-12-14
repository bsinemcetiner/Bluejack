public class Card {

    private String gameDeckColor;
    private int gameDeck;
    private int remainingCount;

    public Card (String gameDeckColor, int gameDeck){
        this.gameDeckColor = gameDeckColor;
        this.gameDeck = gameDeck;
        this.remainingCount = 1;
    }

    public int getRemainingCount(){
        return remainingCount;
    }

    public void decreaseRemainingCount(){
        this.remainingCount--;
    }

    @Override
    public String toString(){

        return gameDeckColor + " " + gameDeck;
    }
}
