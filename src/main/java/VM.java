import java.util.Random;
import java.util.Vector;

public class VM {
    private int ID;
    private int mark_ID;
    private double[] Location;
    private String Address;
    private Vector<Integer> dvmIdList;
    private Vector<Code> codeList = new Vector<Code>();
//    private Vector<String> prepayList = new Vector<>();
    private Item[] itemArray; //새로 추가함.- setProductinfo와 give Product연관/
    private Drink[] drinkArray = new Drink[20]; //새로 추가함. - setProduct연관.
    public Vector<Message> mailBox;
    private Vector<Card> cardList = new Vector<>();
    private int count;
    private MainFrame controller = null;
    private Admin admin = null;
    private Vector<Integer> ids = new Vector<Integer>();
    private int idStack;
//    private Vector<VM> locVM = new Vector<VM>();
    public boolean gov3_flag;

    public VM(int ID, double[] Locaiton){
        this.ID = ID;
        this.mark_ID = setMarkID(ID);
        this.Location = Locaiton;
        this.mailBox = new Vector<Message>();
        itemArray = new Item[7];
        basicSettinng(0);
    }
    public VM(int ID, int mark_ID, double[] Locaiton){
        this.ID = ID;
        this.mark_ID = mark_ID;
        this.Location = Locaiton;
        this.mailBox = new Vector<Message>();
        itemArray = new Item[7];
        basicSettinng(0);
    }
    public VM(int ID, double[] Locaiton,int division){
        this.ID = ID;
        this.mark_ID = setMarkID(ID);
        this.Location = Locaiton;
        this.mailBox = new Vector<Message>();
        this.itemArray = new Item[7];
        this.dvmIdList = new Vector<Integer>();
        this.dvmIdList.add(9999);
        this.dvmIdList.add(9998);
        basicSettinng(division);
    }
    public int setMarkID(int port){
        int lastNum = port % 10;

        Random rand = new Random();

        int firstNum = rand.nextInt(1000);
        return (firstNum*10) + lastNum;
    }
    public void basicSettinng(int division){
        // drink
        drinkArray[0] = new Drink("Sprite", 900);
        drinkArray[1] = new Drink("Mint Sprite", 1000);
        drinkArray[2] = new Drink("Coke", 900);
        drinkArray[3] = new Drink("Mint Coke", 1000);
        drinkArray[4] = new Drink("Water", 500);
        drinkArray[5] = new Drink("Sparkling water", 700);
        drinkArray[6] = new Drink("Coffee", 600);
        drinkArray[7] = new Drink("Mint Coffee", 700);
        drinkArray[8] = new Drink("Milk Coffee", 700);
        drinkArray[9] = new Drink("Demisoda", 900);
        drinkArray[10] = new Drink("SunnyTen", 900);
        drinkArray[11] = new Drink("Sikhye", 700);
        drinkArray[12] = new Drink("IDH", 800);
        drinkArray[13] = new Drink("Milkis", 800);
        drinkArray[14] = new Drink("McCall", 600);
        drinkArray[15] = new Drink("2%", 600);
        drinkArray[16] = new Drink("Gatorade", 700);
        drinkArray[17] = new Drink("Hot Six", 700);
        drinkArray[18] = new Drink("CoCo palm", 1000);
        drinkArray[19] = new Drink("Minute Made", 500);
        // item
        switch (division){
            case 0:
                for (int i = 0; i < 7; i++){
                    itemArray[i] = new Item(drinkArray[i].getName(), drinkArray[i].getPrice(), 10);
                }
                break;
            case 1:
                for (int i = 0; i < 7; i++){
                    itemArray[i] = new Item(drinkArray[i+7].getName(), drinkArray[i+7].getPrice(), 10);
                }
                break;
            case 2:
                for (int i = 0; i < 7; i++){
                    itemArray[i] = new Item(drinkArray[i+13].getName(), drinkArray[i+13].getPrice(), 10);
                }
                break;
            default: // Default is equal to case 0
                for (int i = 0; i < 7; i++){
                    itemArray[i] = new Item(drinkArray[i].getName(), drinkArray[i].getPrice(), 10);
                }
                break;
        }
        // card
        cardList.add(new Card("1234123412341234", 821, 578, 25, 2900));
        cardList.add(new Card("1111111111111111", 1222, 489, 27, 1500));
        cardList.add(new Card("2222222222222222", 322, 367, 12, 0));
        //count
        count = 0;
        idStack=0;
    }
    public Vector<Card> getCardList(){
        return this.cardList;
    }
    public boolean codeempty(){
        return codeList.isEmpty();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public int getVmNum(){
//        return this.dvmList.size();
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    synchronized public int getMailBoxSize(){
        Thread.yield();
        return mailBox.size();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    public boolean editDVMLocation() {
//        double[] Location = new double[2];
//        Location[0] = 37.54164;  //scanLongitude
//        Location[1] = 127.07880; //scanAltitude
//        this.Location = Location;
//
//        if (this.Location != Location || this.Location == null)
//            return false;
//
//        return true;
//    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    //getset
    public MainFrame setUI(MainFrame m){
        this.controller =  m;
        return this.controller;
    }
    public Admin setAdmin(Admin admin){
        this.admin = admin;
        return this.admin;
    }
    public double[] getLocation(){
        return this.Location;
    }
    public int getID(){
        return this.ID;
    }
    public String getIDtS(){
        return String.valueOf(this.ID);
    }
    public Drink[] getDrinkArray() {
        return drinkArray;
    }
    public String getAddress() {
        return this.Address;
    }
    public void editVMAddress(String Address){
        this.Address = Address;
    }
    public int editVMID(int id){
        if(id < 0 || id > 999)
            return -1;
        this.mark_ID = (id*10) + this.getMarkID() % 10;
        return this.mark_ID;
    }
    public int getMarkID(){
        return  this.mark_ID;
    }
    // Test Set
    public void addCode(Code code){
        this.codeList.add(code);
    }
    //find
    public Drink findDrink(String name){
        for (int i = 0; i < 20; i++) {
            if (name.equals(drinkArray[i].getName())) {
                return drinkArray[i];
            }
        }
        return null;
    }
    public Item findItem(String name){
        for (int i = 0; i < 7; i++) {
            if (name.equals(itemArray[i].getName())) {
                return itemArray[i];
            }
        }
        return null;
    }
    public Card findCard(String cardNum, int cvc, int pw, int validity){
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.elementAt(i).isThisCard(cardNum, cvc, pw, validity)) {
                return cardList.elementAt(i);
            }
        }
        return null;
    }
    public Card findCard2(String cardNum){
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardNum().equals(cardNum)) {
                return cardList.elementAt(i);
            }
        }
        return null;
    }
    //give
    public String giveCode(String code){
        controller.showMessage("Guidance","<html> authentication code :" + "<b> "+ code+ " </b></html>");
        return  code;
    }
    //check
    public boolean checkStock(String itemName){
        for (int i = 0; i < 7; i++) {
            if (itemName.equals(itemArray[i].getName())) {
                if (itemArray[i].getStock() > 0)
                    return true;
            }
        }
        return false;
    } // boolean 값으로 수정, item에 stock이 존재하면 true 아니면 false
    public String checkCode(int code){
        for (int i = 0; i < codeList.size(); i++) {
            if (codeList.elementAt(i).getCode() == code) {
                String str = codeList.elementAt(i).getName();
                codeList.remove(i);
                return str;
            }
        }
        return null;
    }
    //message
    synchronized int receiveRequest(){
        Thread.yield();

        int mt = mailBox.get(0).getMsgtype();

        if(mt == 1 || mt == 4){
            notifyVMsInfo();
        }
        if(mt == 2){
            System.out.println("receive 2");
            getOtherVM_2();
        }
        if(mt == 3){
            confirmPrepay();
        }
        if(mt == 5){
            getOtherVM_3();
        }
        if(mt == 6){
            respondSell();
        }
        if(mt == 7){
            ConfirmSell_2();
        }
        if(mt == 8){
            requestPrepay_2();
        }
        if(mt == 9){
            receiveChangeName();
        }
        if(mt == 10){
            receiveChangePrice();
        }
        if(mt==11){
            revceiveSyncCard();
        }
        return mt;
    }
    public void respondSell(){
        //6번오면 7번보냄 선결재로 코드넘겨준게 지급됬는지 확인 후 전송.
        new Message(this.ID, mailBox.get(0).getSrc_id(), 7, mailBox.get(0).getMsgField()).send();
        mailBox.remove(0);

        return;
    }
    public void ConfirmSell_2(){
        //7번오면 처리. 선결재로 코드넘겨준게 지급됬는지 질문에 대한 응답을 확인.-사용하지않으므로주석만.
        return;
    }
    synchronized void MailRecieve(Message msg){
        Thread.yield();
        System.out.println("DVM "+this.ID+" Message ricieved\n"+msg.toString());
        this.mailBox.add(msg);
    }
    public void getOtherVM(String itemName){
        // Use Case 4, 9, 15
        new Message(this.ID, 0, 1, itemName).send(); // stockMsg:Message
    }
    public void getOtherVM_2(){
        System.out.println("1");
        //196번에서 초기화됬을때, 혹은 아예 처음에 배열을 pop해줌 전부다.
        if(idStack==0)
            while(ids.size()>0)
                ids.remove(0);
        //이다음부분도 진행됨.
        if (mailBox.get(0).getMsgField().equals("trash"))
            System.out.println("getOtherVM_2:" + mailBox.get(0).getMsgField()  + "== null ");
        else
            ids.add(mailBox.get(0).getSrc_id());
        mailBox.remove(0);
        idStack++;
        if(idStack==dvmIdList.size()-1){
            for (int des : ids) {
                new Message(this.ID, des, 4, "trash").send(); // addressMsg:Message
            }
            idStack=0;
        }
    }
    public void getOtherVM_3(){
        // Check Mail Box and filter which has our requirement (for Request Address)
        gov3_flag = true;
        double[] tempD = new double[2];
        String[] tempS = mailBox.get(0).getMsgField().split("-");
        tempD[0] = Double.parseDouble(tempS[0]);
        tempD[1] = Double.parseDouble(tempS[1]);
        int markID = Integer.parseInt(tempS[2]);
        controller.showVMFrame(new VM(mailBox.get(0).getSrc_id(),markID, tempD));
        mailBox.remove(0);
        //return vms; -> UI쪽으로 패스
    }
    public void notifyVMsInfo(){
        // msgType == 1
        Message msg = mailBox.get(0);
        switch (msg.getMsgtype()){
            case 1:
                System.out.println("VM(" + this.getID() + "): Receive Message type: 1");
                boolean isItem = false;
                for (int j = 0; j < itemArray.length; j++) {
                    if ((itemArray[j].getName()).equals(msg.getMsgField())) {
                        if (itemArray[j].getStock() > 0) {
                            System.out.println("VM(" + this.getID() + "): I have a stock");
                            Message stockMsg = new Message(this.ID, msg.getSrc_id(), 2, msg.getMsgField());
                            stockMsg.send();
                            isItem = true;
                            mailBox.remove(0);
                            break;
                        }

                    }
                }
                if (!isItem){
                    System.out.println("**************************** VM(" + this.getID() + "): I don't have a stock");
                    Message stockMsg = new Message(this.ID, msg.getSrc_id(), 2, "trash");
                    mailBox.remove(0);
                    stockMsg.send();
                }
                break;
            case 4:
                // msgType == 4
                String loc = this.Location[0] + "-" + this.Location[1] + "-" + this.mark_ID;
                Message addressMsg = new Message(this.ID, mailBox.get(0).getSrc_id(), 5, loc);
                mailBox.remove(0);
                addressMsg.send();
                break;
            default:
                System.out.println("Notify가 실행되었는데 1,4가아님");
        }
    }
    public void requestPrepay(String name, int dst_id){
        //코드 생성
        String code;
        String zeros;
        //자기의ID + n번째음료수 자기의 ID+음료수 식별id + n번째로 발급했다.
        if (count < 10)
            zeros = "000";
        else if (count < 100)
            zeros = "00";
        else if (count < 1000)
            zeros = "0";
        else
            zeros = "";
        code = String.valueOf(this.ID) + zeros + count;
        count++;
        System.out.println("ID(" + this.ID + ") : requestPrepay");
        new Message(this.ID, dst_id, 3, name + "-" + code).send();
    }
    public void requestPrepay_2(){
        if (mailBox.get(0).getMsgField() == null)
            controller.showMessage("Guidance","Out of Stock");
        else {
            String str[] = mailBox.get(0).getMsgField().split("-");

            giveCode(str[1]); // 카드창 띄우기
            mailBox.remove(0);
        }
    }
    public void confirmPrepay(){
        int stock;
        String str[] = mailBox.get(0).getMsgField().split("-");
        for (int j = 0; j < itemArray.length; j++) {
            if (itemArray[j].getName().equals(str[0])) {
                System.out.println("2");
                String name = itemArray[j].getName();
                stock = itemArray[j].getStock();
                if (stock < 1){
                    new Message(this.ID, mailBox.get(0).getSrc_id(), 8, null).send();
                }
                else {
                    Code c = new Code(Integer.parseInt(str[1]), str[0]);
                    codeList.add(c);
                    itemArray[j].editStock(itemArray[j].getStock()-1);
                    new Message(this.ID, mailBox.get(0).getSrc_id(), 8, mailBox.get(0).getMsgField()).send();
                }
            }
        }
        mailBox.remove(0);
    }
    //신규 메시지들. n번째 항목인것(drink중에)의 이름/가격을 name/price로 바꾼다!
    public String requestChangeName(int n, String name){
        int flag=0;
        for (int i = 0; i < 7; i++) {
            if (drinkArray[n].getName().equals(itemArray[i].getName())) {
                if(itemArray[i].editName(name)){
                    drinkArray[n].setName(name);
                }flag=1;
            }
        }
        if(flag==0)
            drinkArray[n].setName(name);
        String msgfield = n +":"+name;
        new Message(this.ID, 0, 9, msgfield).send();
        return msgfield;
    }
    public void receiveChangeName(){
        String str[] = mailBox.get(0).getMsgField().split(":");
        int n = Integer.parseInt(str[0]);
        int flag=0;
        for (int i = 0; i < 7; i++) {
            if (drinkArray[n].getName().equals(itemArray[i].getName())) {
                if(itemArray[i].editName(str[1]))
                    drinkArray[n].setName(str[1]);
                flag=1;
            }
        }
        if(flag==0)
            drinkArray[n].setName(str[1]);
        mailBox.remove(0);
    }
    public String requestChangePrice(int n, int price){
        int flag=0;
        for (int i = 0; i < 7; i++) {
            if (drinkArray[n].getName().equals(itemArray[i].getName())) {
                if(itemArray[i].editPrice(price))
                    drinkArray[n].setPrice(price);
                flag=1;
            }
        }
        if(flag==0)
            drinkArray[n].setPrice(price);
        String msgfield = n +":"+price;
        new Message(this.ID, 0, 10, msgfield).send();
        return msgfield;
    }
    public void receiveChangePrice(){
        String str[] = mailBox.get(0).getMsgField().split(":");
        int n = Integer.parseInt(str[0]);
        int price = Integer.parseInt(str[1]);
        int flag=0;
        for (int i = 0; i < 7; i++) {
            if (drinkArray[n].getName().equals(itemArray[i].getName())) {
                if(itemArray[i].editPrice(price))
                    drinkArray[n].setPrice(price);
                else{
                    //TODO 가격이 잘못되었다고 출력.-안해도됨.
                }flag=1;
            }
        }
        if(flag==0)
            drinkArray[n].setPrice(price);
        controller.showDrink();
        mailBox.remove(0);
    }
    public void revceiveSyncCard(){
        String[] str = mailBox.get(0).getMsgField().split(":");
        int balance = Integer.parseInt(str[1]);
        String num = str[0];
        Card card = findCard2(num);
        if(card!=null)
            card.setBalance(balance);
        mailBox.remove(0);
    }
    public void setPredrinkname(String name){
        controller.setPredrinkname(name);
    }
}