import java.util.Collections;
import java.util.Vector;

public class VM {
    private  int ID;
    private  double []Location;
    private  String Address;
    private double[][] vmLocArray;
    private  String[] vmAddArray;
    private Vector<VM> dvmList = new Vector<>();
    private Vector<Code> codeList = new Vector<Code>();
    private Vector<String> prepayList = new Vector<>();
    private Item[] itemArray = new Item[7]; //새로 추가함.- setProductinfo와 give Product연관/
    private Drink[] drinkArray = new Drink[20]; //새로 추가함. - setProduct연관.
    public Vector<Message>[] mailBox;
    private Vector<Card> cardList = new Vector<>();

    public VM(int ID, double[] Locaiton){
        this.ID = ID;
        this.Location = Locaiton;

        Vector[] temp = new Vector[9];
        this.mailBox = (Vector<Message>[]) temp;
        for(int i = 0; i < this.mailBox.length; i++)
            mailBox[i] = new Vector<Message>();

        basicSettinng();
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
    // 남승협
    synchronized void MailRecieve(Message msg){
        Thread.yield();
        this.mailBox[msg.getMsgtype()].add(msg);
    }

    public int getID(){
        return this.ID;
    }

    public Vector<VM> getOtherVM(String itemName){
        // Use Case 4, 9, 15
        Vector<Integer> ids = null;
        Vector<VM> vms = null;
        new Message(this.ID, 0, 1, itemName).Send(); // stockMsg:Message

        // Check Mail Box and filter which has our requirement (for Requested Stock)
        for(int i = mailBox[2].size()-1; i >= 0; i--){
            if(mailBox[2].get(i).getMsgField() != null)
                ids.add(mailBox[2].get(i).src_id);

            mailBox[2].remove(i);
        }

        // Require address from other DVMs
        for(int des : ids){
            new Message(this.ID, des, 4, "").Send(); // addressMsg:Message
        }

        // Check Mail Box and filter which has our requirement (for Request Address)
        for(int i = mailBox[5].size()-1; i >= 0; i--){
            if(mailBox[5].get(i).getMsgField() != null) {
                double[] tempD = new double[2];
                String[] tempS = mailBox[5].get(i).getMsgField().split(",");
                tempD[0] = Double.parseDouble(tempS[0]);
                tempD[1] = Double.parseDouble(tempS[1]);

                vms.add(new VM(mailBox[5].get(i).src_id, tempD));
            }
            mailBox[5].remove(i);
        }

        return vms;
    }
    public void giveCode(String code){
        System.out.println(code); // UI생성 필요.
    }
    public boolean editDVMLocation(){
        double []Location = new double[2];
        Location[0] = 37.54164;  //scanLongitude
        Location[1] = 127.07880; //scanAltitude
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
    //추가
    public void basicSettinng(){
        // drink
        drinkArray[0] = new Drink("Sprite",900);
        drinkArray[1] = new Drink("Mint Sprite",1000);
        drinkArray[2] = new Drink("Coke",900);
        drinkArray[3] = new Drink("Mint Coke",1000);
        drinkArray[4] = new Drink("Water",500);
        drinkArray[5] = new Drink("Sparkling water",700);
        drinkArray[6] = new Drink("Coffee",600);
        drinkArray[7] = new Drink("Mint Coffee",700);
        drinkArray[8] = new Drink("Milk Coffee",700);
        drinkArray[9] = new Drink("Demisoda",900);
        drinkArray[10] = new Drink("SunnyTen",900);
        drinkArray[11] = new Drink("Sikhye",700);
        drinkArray[12] = new Drink("IDH",800);
        drinkArray[13] = new Drink("Milkis",800);
        drinkArray[14] = new Drink("McCall",600);
        drinkArray[15] = new Drink("2%",600);
        drinkArray[16] = new Drink("Gatorade",700);
        drinkArray[17] = new Drink("Hot Six",700);
        drinkArray[18] = new Drink("CoCo palm",1000);
        drinkArray[19] = new Drink("Minute Made",500);

        // item
        for(int i = 0; i < 7; i++) {
            itemArray[i] = new Item(drinkArray[i].getName(),drinkArray[i].getPrice(),10);
        }

        // code
        codeList.add(new Code(123456,"콜라"));

        // card
        cardList.add(new Card("1234123412341234", 820, 578, 25, 900));
    }
    public String checkCode(int code){
        for(int i=0; i<codeList.size(); i++){
            if(codeList.elementAt(i).getCode() == code){
                String str = codeList.elementAt(i).getName();
                codeList.remove(i);
                return str;
            }
        }
        return null;
    }
    public Drink[] getDrinkArray(){
        return drinkArray;
    }
    public Card findCard(String cardNum,int cvc,int pw, int validity){
        for(int i=0; i<cardList.size(); i++){
            if(cardList.elementAt(i).isThisCard(cardNum,cvc,pw,validity)){
                return cardList.elementAt(i);
            }
        }
        return null;
    }
    public void editVMAddress(String Address){
        this.Address = Address;
    }
    public void editVMID(int id){
        this.ID = id;
    }
    public Item findItem(String name){
        for(int i=0; i<7;i++) {
            if(name.equals(itemArray[i].getName())){
                return itemArray[i];
            }
        }
        return null;
    }
    //강현수
    public void requestPrepay(String name, int dst_id){
        //코드 생성
        int code;
        //자기의ID+n번째음료수 자기의 ID+음료수 식별id+n번째로 발급했다.
        new Message(this.ID, dst_id, 3, "name"+"?"+String.valueof(code)).Send();
        while(true){
            if(mailBox[8]!=null)
                break;
        }
        if(mailBox[5].get(0).getMsgField() == null)
            println("대상 자판기에 재고가 없습니다."); //UI로 만들어야함.
        else {
            String str[] = mailBox[8].get(0).getMsgField().split("?");
            giveCode(str[1]);
            mailBox[8].remove(0);
        }
    }
}