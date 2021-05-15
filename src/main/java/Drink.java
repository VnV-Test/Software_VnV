public class Drink {
    private int Price;
    private String Name;

    public Drink(){
        this.Price = 0;
        this.Name = null;
    }
    public Drink(String Name,int price){

        this.Price = price;
        this.Name = Name;
    }
/*
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
*/
    public String getName(){
        return Name;
    }
    //아래부터는 VM product info와 give product를 위해 만듦.
    public int getPrice(){
        return this.Price;
    }
    public void setPrice(int price){
        Price=price;
    }

    public void setName(String name){
        Name=name;
    }
}
