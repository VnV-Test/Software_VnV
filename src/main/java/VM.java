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
    public Vector<Message> mailBox;
    private Vector<Card> cardList = new Vector<>();

    public VM(int ID, double[] Locaiton){
        this.ID = ID;
        this.Location = Locaiton;
        this.mailBox = new Vector<Message>();
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
        this.mailBox.add(msg);
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
                String[] tempS = mailBox.get(i).getMsgField().split(",");
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
        drinkArray[0] = new Drink("사이다",900);
        drinkArray[1] = new Drink("민트맛 사이다",1000);
        drinkArray[2] = new Drink("콜라",900);
        drinkArray[3] = new Drink("민트 콜라",1000);
        drinkArray[4] = new Drink("물",500);
        drinkArray[5] = new Drink("탄산수",700);
        drinkArray[6] = new Drink("커피",600);
        drinkArray[7] = new Drink("민트 커피",700);
        drinkArray[8] = new Drink("밀크 커피",700);
        drinkArray[9] = new Drink("데미소다",900);
        drinkArray[10] = new Drink("써니텐",900);
        drinkArray[11] = new Drink("식혜",700);
        drinkArray[12] = new Drink("갈아먹는 배",800);
        drinkArray[13] = new Drink("밀키스",800);
        drinkArray[14] = new Drink("맥콜",600);
        drinkArray[15] = new Drink("2%",600);
        drinkArray[16] = new Drink("게토레이",700);
        drinkArray[17] = new Drink("핫식스",700);
        drinkArray[18] = new Drink("코코팜",1000);
        drinkArray[19] = new Drink("미닛메이드",500);

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
                return codeList.elementAt(i).getName();
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
}