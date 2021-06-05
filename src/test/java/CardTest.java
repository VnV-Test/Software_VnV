import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CardTest {
    private Card cd;
    private Card cd2;

    @BeforeEach
    public void setUp() throws Exception {
        cd = new Card("1234123412341234", 820, 578, 25, 5000);
        cd2 = new Card("1111111111111111", 222, 489, 12, 1500);
    }

    @Test
    public void isThisCardTest(){
        //모두 일치할 경우
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 578, 25, 820), true);
        Assertions.assertEquals(cd2.isThisCard("1111111111111111", 489, 12 , 222), true);

    }
    @Test
    public void isThisCardTest2(){
        //카드번호가 불일치할 경우
        Assertions.assertEquals(cd.isThisCard("1687558759637234", 820, 25, 820), false);
        Assertions.assertEquals(cd.isThisCard("1rmst857dn41dshx", 820, 25, 820), false);
        Assertions.assertEquals(cd2.isThisCard("1234123412341234", 489, 12, 222), false);
    }
    @Test
    public void isThisCardTest3(){
        //CVC가 불일치할 경우
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 821, 25, 820), false);
        Assertions.assertEquals(cd2.isThisCard("1111111111111111", 490, 12 , 222), false);
    }
    @Test
    public void isThisCardTest4(){
        //비밀번호가 불일치할 경우
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 820, 35, 820), false);
        Assertions.assertEquals(cd2.isThisCard("1111111111111111", 489, 25 , 222), false);
    }
    @Test
    public void isThisCardTest5(){
        //유효기간이 불일치할 경우
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 820, 25, 821), false);
        Assertions.assertEquals(cd2.isThisCard("1111111111111111", 489, 12 , 322), false);
    }
    @Test
    public void isThisCardTest6(){
        //전혀 일치하지 않는 경우
        Assertions.assertEquals(cd.isThisCard("", 0, 0, 0), false);
        Assertions.assertEquals(cd2.isThisCard("", 0, 0 , 0), false);
    }
    @Test
    public void paymentTest(){
        //결제 잔액이 충분한 경우
        Assertions.assertEquals(cd.payment(100), true);
        Assertions.assertEquals(cd2.payment(500), true);
    }
    @Test
    public void paymentTest2(){
        //결제 잔액이 충분하지 않은 경우
        Assertions.assertEquals(cd.payment(10000), false);
        Assertions.assertEquals(cd.payment(6000), false);
        Assertions.assertEquals(cd2.payment(10000), false);
        Assertions.assertEquals(cd2.payment(6000), false);
    }
    @Test
    public void paymentTest3(){
        //결제 잔액이 음수인 경우
        Assertions.assertEquals(cd.payment(-100), false);
        Assertions.assertEquals(cd2.payment(-100), false);
    }
}
