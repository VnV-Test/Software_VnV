import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VMfunctionTest {

    private VM vm1, vm2,vm3,vm4;
    MainFrame frame1,frame2,frame3,frame4;


    @BeforeEach
    void setUp() throws Exception {

        double[] location2 = {100,100};
        vm2 = new VM(9998,location2,1);
        frame2 = new MainFrame(vm2);

        double[] location = {0,0};
        vm1 = new VM(9999,location,0);
        frame1 = new MainFrame(vm1);

        vm3 = new VM(9997,location,2);
        frame3 = new MainFrame(vm3);

        vm4 = new VM(9996,location,10);
        frame4 = new MainFrame(vm4);
    }

    @Test
    void editVMID() {
        Assertions.assertEquals(vm1.editVMID(100),1009);
        Assertions.assertEquals(vm1.editVMID(50),509);
        Assertions.assertEquals(vm2.editVMID(100),1008);
        Assertions.assertEquals(vm2.editVMID(50),508);
    }
    @Test
    void editVMID2() {
        Assertions.assertEquals(vm1.editVMID(1000),-1);
        Assertions.assertEquals(vm1.editVMID(-500),-1);
        Assertions.assertEquals(vm2.editVMID(1500),-1);
        Assertions.assertEquals(vm1.editVMID(1000),-1);
    }
    @Test
    void findDrink() {
        // 있는 Drink 검색하는 경우
        Assertions.assertEquals(vm1.findDrink("Coke").getName(),"Coke");
        Assertions.assertEquals(vm2.findDrink("Coke").getName(),"Coke");
        Assertions.assertEquals(vm1.findDrink("Sprite").getName(),"Sprite");
        Assertions.assertEquals(vm2.findDrink("Sprite").getName(),"Sprite");
    }
    @Test
    void findDrink2() {
        // 없는 Drink 검색하는 경우
        Assertions.assertEquals(vm1.findDrink("Drink"),null);
        Assertions.assertEquals(vm2.findDrink("Drink"),null);
        Assertions.assertEquals(vm1.findDrink(""),null);
        Assertions.assertEquals(vm2.findDrink(""),null);
    }
    @Test
    void findItem() {
        // 있는 Item을 검색하는 경우
        Assertions.assertEquals(vm1.findItem("Coke").getName(),"Coke");
        Assertions.assertEquals(vm2.findItem("Milk Coffee").getName(),"Milk Coffee");
    }
    @Test
    void findItem2() {
        // Drink 에는 있지만 Item은 없는 것을 검색하는 경우
        Assertions.assertEquals(vm1.findDrink("Milk Coffee").getName(),"Milk Coffee");
        Assertions.assertEquals(vm1.findItem("Milk Coffee"),null);
        Assertions.assertEquals(vm2.findDrink("Coke").getName(),"Coke");
        Assertions.assertEquals(vm2.findItem("Coke"),null);
    }
    @Test
    void findItem3() {
        // Drink 에없고 Item에도 없는 것을 검색하는 경우
        Assertions.assertEquals(vm1.findItem("Drink"),null);
        Assertions.assertEquals(vm2.findItem("Drink2"),null);
        Assertions.assertEquals(vm1.findItem(""),null);
        Assertions.assertEquals(vm2.findItem("1234"),null);
    }
    @Test
    void findCard() {
        Assertions.assertEquals(vm1.findCard("1234123412341234",578,25,821).getCardNum(),"1234123412341234");
        Assertions.assertEquals(vm2.findCard("1111111111111111",489,27,1222).getCardNum(),"1111111111111111");
    }
    @Test
    void findCard2() {
        Assertions.assertEquals(vm1.findCard("",578,25,821),null);
        Assertions.assertEquals(vm2.findCard("",489,27,1222),null);
    }
    @Test
    void checkStock() {
        //재고가 있는 경우
        Assertions.assertEquals(vm1.checkStock("Sprite"),true);
        Assertions.assertEquals(vm1.checkStock("Mint Sprite"),true);
        Assertions.assertEquals(vm1.checkStock("Coke"),true);
        Assertions.assertEquals(vm1.checkStock("Mint Coke"),true);
        Assertions.assertEquals(vm1.checkStock("Water"),true);
        Assertions.assertEquals(vm1.checkStock("Sparkling water"),true);
        Assertions.assertEquals(vm1.checkStock("Coffee"),true);
        Assertions.assertEquals(vm2.checkStock("Mint Coffee"),true);
        Assertions.assertEquals(vm2.checkStock("Milk Coffee"),true);
        Assertions.assertEquals(vm2.checkStock("Demisoda"),true);
        Assertions.assertEquals(vm2.checkStock("SunnyTen"),true);
        Assertions.assertEquals(vm2.checkStock("Sikhye"),true);
        Assertions.assertEquals(vm2.checkStock("IDH"),true);
        Assertions.assertEquals(vm2.checkStock("Milkis"),true);
    }
    @Test
    void checkStock2() {
        //재고가 없는 경우(item 자체가 존재하지 않는 경우)
        Assertions.assertEquals(vm2.checkStock("Sprite"),false);
        Assertions.assertEquals(vm2.checkStock("Mint Sprite"),false);
        Assertions.assertEquals(vm2.checkStock("Coke"),false);
        Assertions.assertEquals(vm2.checkStock("Mint Coke"),false);
        Assertions.assertEquals(vm2.checkStock("Water"),false);
        Assertions.assertEquals(vm2.checkStock("Sparkling Water"),false);
        Assertions.assertEquals(vm2.checkStock("Coffee"),false);
        Assertions.assertEquals(vm1.checkStock("Mint Coffee"),false);
        Assertions.assertEquals(vm1.checkStock("Milk Coffee"),false);
        Assertions.assertEquals(vm1.checkStock("Demisoda"),false);
        Assertions.assertEquals(vm1.checkStock("SunnyTen"),false);
        Assertions.assertEquals(vm1.checkStock("Sikhye"),false);
        Assertions.assertEquals(vm1.checkStock("IDH"),false);
        Assertions.assertEquals(vm1.checkStock("Milkis"),false);

    }
    @Test
    void checkStock3() {
        //재고가 없는 경우(item 존재하지만 재고가 모두 소진된 경우)
        vm1.findItem("Sprite").editStock(0);
        Assertions.assertEquals(vm1.checkStock("Sprite"),false);
        vm1.findItem("Sprite").editStock(10);

        vm2.findItem("Demisoda").editStock(0);
        Assertions.assertEquals(vm2.checkStock("Demisoda"),false);
        vm2.findItem("Demisoda").editStock(10);
    }
    @Test
    void checkCode() {
        // 맞는 코드를 입력한 경우
        vm1.addCode(new Code(99990000,"Coke"));
        vm2.addCode(new Code(99980000,"Milk Coffee"));

        Assertions.assertEquals(vm1.checkCode(99990000),"Coke");
        Assertions.assertEquals(vm2.checkCode(99980000),"Milk Coffee");
    }
    @Test
    void checkCode2() {
        // 틀린 코드를 입력한 경우
        vm1.addCode(new Code(99990000,"Coke"));
        vm2.addCode(new Code(99980000,"Milk Coffee"));

        Assertions.assertEquals(vm1.checkCode(99991111),null);
        Assertions.assertEquals(vm2.checkCode(99981111),null);
    }
    @Test
    void checkGiveCode() {
        Assertions.assertEquals(vm1.giveCode("99990000"),"99990000");
        Assertions.assertEquals(vm2.giveCode("99980000"),"99980000");
    }
    @Test
    void checkReceiveMail() {

        vm1.mailBox.add(new Message(9998,9999,1,"Coke"));
        Assertions.assertEquals(vm1.receiveRequest(),1);

        vm1.mailBox.add(new Message(9998,9999,1,"Sikhye"));
        Assertions.assertEquals(vm1.receiveRequest(),1);

        vm1.mailBox.add(new Message(9998,9999,2,"Coke"));
        Assertions.assertEquals(vm1.receiveRequest(),2);

        vm1.mailBox.add(new Message(9998,9999,2,"trash"));
        Assertions.assertEquals(vm1.receiveRequest(),2);

        vm1.mailBox.add(new Message(9998,9999,3,"Coke-99980000"));
        Assertions.assertEquals(vm1.receiveRequest(),3);

        vm1.mailBox.add(new Message(9998,9999,4,""));
        Assertions.assertEquals(vm1.receiveRequest(),4);

        vm1.mailBox.add(new Message(9998,9999,7,""));
        Assertions.assertEquals(vm1.receiveRequest(),7);

        vm1.mailBox.add(new Message(9998,9999,8,"Coke-99981111"));
        Assertions.assertEquals(vm1.receiveRequest(),8);

        vm1.mailBox.add(new Message(9998,9999,9,"1:Sprite2"));
        Assertions.assertEquals(vm1.receiveRequest(),9);

        vm1.mailBox.add(new Message(9998,9999,10,"1:800"));
        Assertions.assertEquals(vm1.receiveRequest(),10);

        vm1.mailBox.add(new Message(9998,9999,11,"1234123412341234:800"));
        Assertions.assertEquals(vm1.receiveRequest(),11);


        vm2.mailBox.add(new Message(9999,9998,1,"Milk Coffee"));
        Assertions.assertEquals(vm2.receiveRequest(),1);

        vm2.mailBox.add(new Message(9999,9998,2,"Milk Coffee"));
        Assertions.assertEquals(vm2.receiveRequest(),2);

        vm2.mailBox.add(new Message(9999,9998,3,"Milk Coffee-99990000"));
        Assertions.assertEquals(vm2.receiveRequest(),3);

        vm2.mailBox.add(new Message(9999,9998,4,""));
        Assertions.assertEquals(vm2.receiveRequest(),4);

        vm2.mailBox.add(new Message(9999,9998,8,"Milk Coffee-99991111"));
        Assertions.assertEquals(vm2.receiveRequest(),8);

        vm2.mailBox.add(new Message(9999,9998,8,null));
        Assertions.assertEquals(vm2.receiveRequest(),8);

        vm2.mailBox.add(new Message(9999,9998,9,"2:Mint Sprite2"));
        Assertions.assertEquals(vm2.receiveRequest(),9);

        vm2.mailBox.add(new Message(9999,9998,10,"2:800"));
        Assertions.assertEquals(vm2.receiveRequest(),10);

        vm2.mailBox.add(new Message(9999,9998,11,"1111111111111111:800"));
        Assertions.assertEquals(vm2.receiveRequest(),11);
    }
    @Test
    void checkRequestChangeName(){
        Assertions.assertEquals(vm1.requestChangeName(1,"sprite2"),"1:sprite2");
        Assertions.assertEquals(vm1.requestChangePrice(1,800),"1:800");
    }
    @Test
    void checkMessage2(){
        vm1.mailBox.add(new Message(9998,9999,6,""));
        Assertions.assertEquals(vm1.receiveRequest(),6);

        vm1.setPredrinkname("Coke");
        vm1.mailBox.add(new Message(9998,9999,5,"10-10-8708"));
        Assertions.assertEquals(vm1.receiveRequest(),5);
    }
    @Test
    void checkCodeEmpty(){
        Assertions.assertEquals(vm1.codeempty(),true);
    }
    @Test
    void checkGetCardList(){
        Assertions.assertEquals(vm1.getCardList().elementAt(0).getCardNum(),"1234123412341234");
    }
    @Test
    void checkGetMailSize(){
        Assertions.assertEquals(vm1.getMailBoxSize(),0);
    }
    @Test
    void checkAddress(){
        Assertions.assertEquals(vm1.editVMAddress("주소"),"주소");
        Assertions.assertEquals(vm1.getAddress(),"주소");
    }
    @Test
    void checkGetIDts(){
        Assertions.assertEquals(vm1.getIDtS(),"9999");
    }
    @Test
    void checkFindCard(){
        Assertions.assertEquals(vm1.findCard2(""),null);
    }
    @Test
    void checkMailReceive(){
        Assertions.assertEquals(vm1.mailRecieve(new Message(9998,9999,5,"10-10-8708")),"mail receive");
        vm1.getOtherVM("Coke");
    }
    @Test
    void checkRequestPrepay(){
        Assertions.assertEquals(vm3.requestPrepay("Hot Six",9999),"99970000");
    }
    @Test
    void checkGetLocation(){
        Assertions.assertEquals(vm3.getLocation()[0],0);
    }
}