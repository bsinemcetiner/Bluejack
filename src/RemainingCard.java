public class RemainingCard {
    private int remainingCard;
    private String gameDeckColor;
    private int sign;

    private String specialFlip;
    private int specialDouble;


    public RemainingCard(String gameDeckColor, int remainingCard, int sign) {
        this.gameDeckColor = gameDeckColor;
        this.remainingCard = remainingCard;
        this.sign = sign;
    }

    public int getRemainingCard() {
        return remainingCard;
    }

    public void decreaseRemainingCard() {
        this.remainingCard--;
    }

    @Override
    public String toString() {

        return gameDeckColor + " " + (remainingCard * sign);
    }
    public void specials (String specialFlip, int specialDouble){
        //if ()
    }
}
