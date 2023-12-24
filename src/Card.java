public class Card {
    private String gameDeckColor;
    private int gameDeck;
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

    public String getSpecial(){
        return special;
    }

    public void setGameDeck (int gameDeck){

        this.gameDeck = gameDeck;
    }
    public int getDouble(){   // for double
        return gameDeck*sign*2;
    }
    public int getFlip(){   // for flip
        return gameDeck*sign*-1;
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
