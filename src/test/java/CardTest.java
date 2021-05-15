import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CardTest {
    private Card cd;

    @BeforeEach
    public void setUp() throws Exception {
        cd = new Card("1234123412341234", 820, 578, 25, 5000);
    }

    @Test
    public void isThisCardTest(){
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 578, 25, 820), true);
        Assertions.assertEquals(cd.isThisCard("1687558759637234", 745, 16, 54), false);
        Assertions.assertEquals(cd.isThisCard("1rmst857dn41dshx", 123, 75, 4540), false);
        Assertions.assertEquals(cd.isThisCard("1234123412341234", 953, 546, 735), false);
    }

    @Test
    public void paymentTest(){
        Assertions.assertEquals(cd.payment(10000), false);
        Assertions.assertEquals(cd.payment(6000), false);
        Assertions.assertEquals(cd.payment(100), true);

    }
}
