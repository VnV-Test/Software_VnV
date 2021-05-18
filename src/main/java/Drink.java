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
