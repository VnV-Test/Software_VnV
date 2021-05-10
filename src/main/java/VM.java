import java.util.Vector;

public class VM {
    private  int ID;
    private  double []Location;
    private  String Address;
    private double[][] vmLocArray;
    private  String[] vmAddArray;
    private Vector<VM> dvmList;
    private Vector<Code> codeList;
    private Vector<String> prepayList;
    private Item[] itemArray; //새로 추가함.- setProductinfo와 give Product연관/
    private Drink[] drinkArray; //새로 추가함. - setProduct연관.

    public Drink sendVMList(){
        //TODO
        return Drink;
    }

    public Vector<VM> getDvmList() {
        return dvmList;
    }
    //강현수
    public double[] getVMInfo(int index){
        double[] arr= new double[2];
        arr[0]=vmLocArray[index-1][0]; //index번째는 index-1이니까.
        arr[1]=vmLocArray[index-1][1];
        return arr;
    }//double[]로 반환값 수정., 인덱스 받아서 넘겨줌.
    public String giveProduct(int index){
        return itemArray[index-1].getName();
        //드링크 이름 반환 인덱스 들어옴.
    }
    public void editProductInfo(int index, String name, int price, int stock){
        itemArray[index-1].setName(name);
        itemArray[index-1].setPrice(price);
        itemArray[index-1].setStock(stock);
        drinkArray[index-1].setName(name);
        drinkArray[index-1].setPrice(price);
    }

    // Sünghjöp
    public Vector<VM> getOtherVM(String itemName){
        //TODO
    }

    public void giveCode(){
        //TODO
    }

    public boolean editDVMLocation(){
        double []Location = double[2];
        Location[0] = 37.54164;  //scanLongitude
        Location[0] = 127.07880; //scanAltitude
        this.Location = Location;

        if(this.Location != Location || this.Location == null)
            return false;

        return true;
    }
}
