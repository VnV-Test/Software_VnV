import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeTest {
    Code c;
    String s;
    int co;
    @BeforeEach
    public void setUp() throws Exception {
        co = 123;
        s = "abc";
        c = new Code(co,s);
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals(c.getName(), s);
    }
    @Test
    public void getCodeTest() {
        Assertions.assertEquals(c.getCode(), co);
    }
    @Test
    public void confirmCOdeTest(){
        Assertions.assertEquals(c.confirmCOde(co), true);
        Assertions.assertEquals(c.confirmCOde(234), false);
    }
}
