import java.util.Collections;
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
    private Vector<Message> mailBox;

    public VM(int ID, double[] Locaiton){
        this.ID = ID;
        this.Location = Locaiton;
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
        // Use Case 4, 9, 15
        Vector<Integer> ids = null;
        Vector<VM> vms = null;
        new Message(this.ID, 0, 1, itemName).Send(); // stockMsg:Message

        // Check Mail Box and filter which has our requirement (for Requested Stock)
        for(int i = mailBox.size()-1; i >= 0; i--){
            if(mailBox.get(i).getMsgtype() == 2 && mailBox.get(i).getMsgField() != null)
                ids.add(mailBox.get(i).src_id);

            mailBox.remove(i);
        }

        // Require address from other DVMs
        for(int des : ids){
            new Message(this.ID, des, 4, "").Send(); // addressMsg:Message
        }

        // Check Mail Box and filter which has our requirement (for Request Address)
        for(int i = mailBox.size()-1; i >= 0; i--){
            if(mailBox.get(i).getMsgtype() == 5 && mailBox.get(i).getMsgField() != null) {
                double[] tempD = new double[2];
                String[] tempS = mailBox.get(i).getMsgField().split(" ");
                tempD[0] = Double.parseDouble(tempS[0]);
                tempD[1] = Double.parseDouble(tempS[1]);

                vms.add(new VM(mailBox.get(i).src_id, tempD));
            }
            mailBox.remove(i);
        }

        return vms;
    }

    public Code giveCode(){
        return codeList.get(codeList.size() - 1);
    }

    public boolean editDVMLocation(){
        double []Location = new double[2];
        Location[0] = 37.54164;  //scanLongitude
        Location[0] = 127.07880; //scanAltitude
        this.Location = Location;

        if(this.Location != Location || this.Location == null)
            return false;

        return true;
    }

    //송주한
    public void sendVMList(){

    }

    public boolean CheckStoke(String itemName){
        for(int i=0; i<7;i++) {
            if(itemName.equals(itemArray[i].getName())){
                if(itemArray[i].getStock()>0)
                    return true;
            }
        }
        return false;
    } // boolean 값으로 수정, item에 stock이 존재하면 true 아니면 false

    public boolean editDVMActivated(VM v ){
        if(dvmList.size()<10 && dvmList.size()>=0){
            dvmList.add(v);
            return true;
        }
        return false;
    }

}