public class Item {
    private String Name;
    private int Price;
    private int Stock;
    private int index;//일단 Item이랑 Drink동시에 수정되야되서 추가함.

    public Item(){
        this.Name = null;
        this.Price = 0;
        this.Stock = 0;
    }

    public Item(String name, int price, int stock){
        this.Name = name;
        this.Price = price;
        this.Stock = stock;
    }

    public boolean editName(String name){
        if(name.length() >10){
            return false;
        }else{
            this.Name = name;
            return true;
        }
    }

    public boolean editPrice(int price){
        if(price>10000){
            return false;
        }else{
            this.Price = price;
            return true;
        }
    }

    public boolean editStock(int stock){
        if(stock <0 || stock>50){
            return false;
        }
        else{
            this.Stock = stock;
            return true;
        }
    }

    public boolean editStock(){
        if(this.Stock <=0){
            return false;
        }
        else{
            this.Stock--;
            return true;
        }
    }

    public static int getStock(){
        return Stock;
    }
//아래부터는 VM product info와 give product를 위해 만듦.
    public static String getName(){
        return Name;
    }

    public void setPrice(int price){
        Price=price;
    }

    public void setName(String name){
        Name=name;
    }
    public void setStock(int stock){
        Stock=stock;
    }
}
