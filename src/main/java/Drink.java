public class Drink {
    private int Price;
    private String Name;


    public Drink(){
        this.Price = 0;
        this.Name = null;
    }

    public Drink(int price, String Name){

        this.Price = price;
        this.Name = Name;
    }

    public int getDrinkInfo(){
        return this.Price;
    }

    public boolean requestEditPrice(int price){
        if(price>10000){
            return false;
        }else{
            this.Price = price;
            return true;
        }
    }

    public String getName(){
        return this.Name;
    }

}
