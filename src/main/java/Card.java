public class Card {
    private int cardNum;
    private int validity;
    private int cvc;
    private int pw;
    private int balance;

    public Card(int cardNum, int validity, int cvc, int pw, int balance){
        this.cardNum = cardNum;
        this.validity = validity;
        this.cvc = cvc;
        this.pw = pw;
        this.balance = balance;
    }

    public boolean payment(int cardNum, int cvc, int pw, int price){
        if(cardNum != this.cardNum || cvc != this.cvc, pw != this.pw || this.balance < price){
            return false;
        }

        this.balance -= price;
        return true;
    }
}
