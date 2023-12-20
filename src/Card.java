public class Card {
    private String gameDeckColor;
    private int gameDeck;
    private int remainingCard;

    private int sign;
    private String special;

    public Card (String gameDeckColor, int gameDeck, int sign){
        this.gameDeckColor = gameDeckColor;
        this.gameDeck = gameDeck;
        this.sign = sign;
        this.special = "";
    }

    public Card(String s) {
        special = s;
    }

    public void setGameDeck (int gameDeck){

        this.gameDeck = gameDeck;
    }
    public int getGameDeck(){
        return gameDeck;
    }
    public int getValue(){
        return sign*gameDeck;
    }

    @Override
    public String toString() {
        if(!special.equals("")){
            return special;
        }
        return gameDeckColor + " " + (gameDeck * sign);
    }
}
