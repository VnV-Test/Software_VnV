import java.lang.invoke.SwitchPoint;
import java.util.Vector;

public class VM {
    private int ID;
    private double[] Location;
    private String Address;
    private double[][] vmLocArray;
    private String[] vmAddArray;
    private Vector<VM> dvmList = new Vector<>();
    private Vector<Code> codeList = new Vector<Code>();
    private Vector<String> prepayList = new Vector<>();
    private Item[] itemArray; //새로 추가함.- setProductinfo와 give Product연관/
    private Drink[] drinkArray = new Drink[20]; //새로 추가함. - setProduct연관.
    public Vector<Message>[] mailBox;
    private Vector<Card> cardList = new Vector<>();
    private int count;
    private MainFrame controller = null;
    private Admin admin = null;

    public VM(int ID, double[] Locaiton) {
        this.ID = ID;
        this.Location = Locaiton;
        Vector<Message>[] temp = new Vector[9];
        this.mailBox = (Vector<Message>[]) temp;
        for (int i = 0; i < this.mailBox.length; i++)
            mailBox[i] = new Vector<Message>();
        itemArray = new Item[7];
        basicSettinng(0);
    }
    public VM(int ID, double[] Locaiton,int division) {
        this.ID = ID;
        this.Location = Locaiton;
        Vector<Message>[] temp = new Vector[9];
        this.mailBox = (Vector<Message>[]) temp;
        for (int i = 0; i < this.mailBox.length; i++)
            mailBox[i] = new Vector<Message>();
        itemArray = new Item[7];
        basicSettinng(division);
    }
    public Vector<VM> getDvmList() {
        return dvmList;
    }
    //강현수
    public double[] getVMInfo(int index) {
        double[] arr = new double[2];
        arr[0] = vmLocArray[index - 1][0]; //index번째는 index-1이니까.
        arr[1] = vmLocArray[index - 1][1];
        return arr;
    }//double[]로 반환값 수정., 인덱스 받아서 넘겨줌.
    public void setUI(MainFrame m){
        this.controller =  m;
    }
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
    public String giveProduct(int index) {
        return itemArray[index - 1].getName();
        //드링크 이름 반환 인덱스 들어옴.
    }
    public double[] getLocation(){
        return this.Location;
    }
    public void editProductInfo(int index, String name, int price, int stock) {
        itemArray[index - 1].setName(name);
        itemArray[index - 1].setPrice(price);
        itemArray[index - 1].setStock(stock);
        drinkArray[index - 1].setName(name);
        drinkArray[index - 1].setPrice(price);
    }
    // 남승협
    public void NotifyVMsInfo() {
        // msgType == 1
        System.out.println("VM("+this.getID()+"): Receive Message type: 1");
        boolean isItem = false;
        for (int i = 0; i < mailBox[1].size(); i++) {
            for (int j = 0; j < itemArray.length; j++) {
                if (itemArray[j].getName() == mailBox[i].get(2).getMsgField()) {
                    String name = itemArray[j].getName();
                    if (itemArray[j].getStock() > 0) {
                        System.out.println("VM("+this.getID()+"): I have a stock");
                        Message stockMsg = new Message(this.ID, mailBox[1].get(i).getSrc_id(), 2, name);
                        mailBox[1].remove(i);
                        stockMsg.Send();
                        isItem = true;
                    }
                    System.out.println("VM("+this.getID()+"): I dont't have a stock");
                }
            }
            if(!isItem){
                Message stockMsg = new Message(this.ID, mailBox[1].get(i).getSrc_id(), 2, null);
                mailBox[1].remove(i);
                stockMsg.Send();
            }
        }

        // msgType == 4
        for (int i = 0; i < mailBox[4].size(); i++) {
            String loc = this.Location[0] + "?" + this.Location[1];
            Message addressMsg = new Message(this.ID, mailBox[4].get(i).getSrc_id(), 5, loc);
            mailBox[3].remove(i);
            addressMsg.Send();
        }
    }
    public int getVmNum(){
        return this.dvmList.size();
    }
    synchronized public int getMailBoxSize(int num){
        Thread.yield();
        return mailBox[num].size();
    }
    synchronized void MailRecieve(Message msg) {
        Thread.yield();
        this.mailBox[msg.getMsgtype()].add(msg);
    }
    public int getID() {
        return this.ID;
    }
    //////
    public void ConfirmSell() {
        //6번 보냄 내용은 선결재로 코드넘겨준게 지급됬는지 질문.-사용하지않으므로 주석만.
        //보내는 메시지는 new Message(this.ID, 0, 6, itemName).Send();
        return;
    }
    public void ConfirmSell_2() {
        //7번오면 처리. 선결재로 코드넘겨준게 지급됬는지 질문에 대한 응답을 확인.-사용하지않으므로주석만.
        return;
    }
    public void RespondSell() {
        //6번오면 7번보냄 선결재로 코드넘겨준게 지급됬는지 확인 후 전송.
        for (int i=0;i<mailBox[6].size(); i++){
            new Message(this.ID, mailBox[6].get(i).getSrc_id(), 7, mailBox[6].get(i).getMsgField()).Send();
        }

        return;
    }
    public void getOtherVM(String itemName){
        // Use Case 4, 9, 15

        new Message(this.ID, 0, 1, itemName).Send(); // stockMsg:Message
    }
    public void getOtherVM_2() {
        Vector<Integer> ids = new Vector<Integer>();
        for (int i = mailBox[2].size() - 1; i >= 0; i--) {
            if (mailBox[2].get(i).getMsgField() != null)
                ids.add(mailBox[2].get(i).getSrc_id());

            mailBox[2].remove(i);
        }

        if (ids.size() == 0) {
            //TODO
            controller.showMessage("Error","Please contact us at the following contact information \n" + admin.getContact());// Swing으로 구현 필요.
        }
        // Require address from other DVMs
        for (int des : ids) {
            new Message(this.ID, des, 4, "").Send(); // addressMsg:Message
        }
    }
    public void getOtherVM_3(){
        Vector<VM> vms = new Vector<VM>();

        // Check Mail Box and filter which has our requirement (for Request Address)

        for (int i = mailBox[5].size() - 1; i >= 0; i--) {
            if (mailBox[5].get(i).getMsgField() != null) {
                double[] tempD = new double[2];
                String[] tempS = mailBox[5].get(i).getMsgField().split("\\?");
                tempD[0] = Double.parseDouble(tempS[0]);
                tempD[1] = Double.parseDouble(tempS[1]);
                vms.add(new VM(mailBox[5].get(i).getSrc_id(), tempD));
            }
            mailBox[5].remove(i);
        }

        //return vms; -> UI쪽으로 패스
    }
    public void giveCode(String code) {
        controller.showMessage("Guidance","This is your authentication code + \n" + code);
    }
    public boolean editDVMLocation() {
        double[] Location = new double[2];
        Location[0] = 37.54164;  //scanLongitude
        Location[1] = 127.07880; //scanAltitude
        this.Location = Location;

        if (this.Location != Location || this.Location == null)
            return false;

        return true;
    }
    //송주한
    public boolean CheckStoke(String itemName) {
        for (int i = 0; i < 7; i++) {
            if (itemName.equals(itemArray[i].getName())) {
                if (itemArray[i].getStock() > 0)
                    return true;
            }
        }
        return false;
    } // boolean 값으로 수정, item에 stock이 존재하면 true 아니면 false
    public boolean editDVMActivated(VM v) {
        if (dvmList.size() < 10 && dvmList.size() >= 0) {
            dvmList.add(v);
            return true;
        }
        return false;
    }
    //추가
    public void basicSettinng(int division) {
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
                for (int i = 0; i < 7; i++) {
                    itemArray[i] = new Item(drinkArray[i].getName(), drinkArray[i].getPrice(), 10);
                }
                break;
            case 1:
                for (int i = 0; i < 7; i++) {
                    itemArray[i] = new Item(drinkArray[i+3].getName(), drinkArray[i+3].getPrice(), 10);
                }
                break;
        }
        // card
        cardList.add(new Card("1234123412341234", 820, 578, 25, 900));
        //count
        count = 0;
    }
    public String checkCode(int code) {
        for (int i = 0; i < codeList.size(); i++) {
            if (codeList.elementAt(i).getCode() == code) {
                String str = codeList.elementAt(i).getName();
                codeList.remove(i);
                return str;
            }
        }
        return null;
    }
    public Drink[] getDrinkArray() {
        return drinkArray;
    }
    public Card findCard(String cardNum, int cvc, int pw, int validity) {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.elementAt(i).isThisCard(cardNum, cvc, pw, validity)) {
                return cardList.elementAt(i);
            }
        }
        return null;
    }
    public void editVMAddress(String Address) {
        this.Address = Address;
    }
    public void editVMID(int id) {
        this.ID = id;
    }
    public Item findItem(String name) {
        for (int i = 0; i < 7; i++) {
            if (name.equals(itemArray[i].getName())) {
                return itemArray[i];
            }
        }
        return null;
    }
    //강현수
    public void requestPrepay(String name, int dst_id) {
        //코드 생성
        String code;
        String zeros;
        //자기의ID+n번째음료수 자기의 ID+음료수 식별id+n번째로 발급했다.
        if (count < 10)
            zeros = "000";
        else if (count < 100)
            zeros = "00";
        else if (count < 1000)
            zeros = "0";
        else
            zeros = "";
        code = String.valueOf(this.ID) + zeros + count;
        new Message(this.ID, dst_id, 3, "name" + "\\?" + code).Send();
    }
    public void requestPrepay_2(){
        if (mailBox[8].get(0).getMsgField() == null)
            controller.showMessage("Guidance","Out of Stock");
        else {
            String str[] = mailBox[8].get(0).getMsgField().split("\\?");
            giveCode(str[1]);
            mailBox[8].remove(0);
        }
    }
    public void confirmPrepay() {
        int stock;
        for (int i=0;i<mailBox[3].size(); i++){
            for (int j = 0; j < itemArray.length; j++) {
                if (itemArray[j].getName() == mailBox[3].get(i).getMsgField()) {
                    String name = itemArray[j].getName();
                    stock = itemArray[j].getStock();
                    if (stock < 1)
                        new Message(this.ID, mailBox[3].get(i).getSrc_id(), 8, null).Send();
                    else {
                        String[] str = mailBox[3].get(i).getMsgField().split("\\?"); //이거맞는지 확인좀
                        codeList.add(new Code(Integer.parseInt(str[1]), str[0]));
                        new Message(this.ID, mailBox[3].get(i).getSrc_id(), 8, mailBox[3].get(i).getMsgField()).Send();
                        mailBox[3].remove(0);
                    }
                }
            }
        }

    }
}