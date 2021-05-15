public class Card {
    private String cardNum;
    private int validity;
    private int cvc;
    private int pw;
    private int balance;

    public Card(String cardNum, int validity, int cvc, int pw, int balance){
        this.cardNum = cardNum;
        this.validity = validity;
        this.cvc = cvc;
        this.pw = pw;
        this.balance = balance;
    }
    public boolean payment(int price){
        if(this.balance < price ){
            return false;
        }
        this.balance -= price;
        return true;
    }
    public boolean isThisCard(String cardNum, int cvc, int pw, int validity){
        if(cardNum.equals(this.cardNum)&& cvc == this.cvc && pw == this.pw &&  this.validity == validity){
            return true;
        }
        return false;
    }
}
