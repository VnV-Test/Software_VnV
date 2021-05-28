import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeTest {
    Code code,code2;
    String drinkname,drinkname2;
    int codenum,codenum2;

    @BeforeEach
    public void setUp() throws Exception {
        codenum = 99990000;
        drinkname = "coke";
        code = new Code(codenum,drinkname);

        codenum2 = 99980001;
        drinkname2 = "sprite";
        code2 = new Code(codenum2,drinkname2);
    }
    @Test
    public void getNameTest() {
        Assertions.assertEquals(code.getName(), "coke");
    }
    @Test
    public void getNameTest2() {
        Assertions.assertEquals(code2.getName(), "sprite");
    }
    @Test
    public void getNameTest3() {
        Assertions.assertEquals(code.getName(), drinkname);
    }
    @Test
    public void getNameTest4() {
        Assertions.assertEquals(code2.getName(), drinkname2);
    }
    @Test
    public void getCodeTest() {
        Assertions.assertEquals(code.getCode(), 99990000);
    }
    @Test
    public void getCodeTest2() {
        Assertions.assertEquals(code2.getCode(), 99980001);
    }
    @Test
    public void getCodeTest3() {
        Assertions.assertEquals(code.getCode(),codenum);
    }
    @Test
    public void getCodeTest4() {
        Assertions.assertEquals(code2.getCode(),codenum2);
    }
    @Test
    public void confirmCOdeTest(){
        // 코드가 맞는 경우
        Assertions.assertEquals(code.confirmCOde(99990000), true);
        Assertions.assertEquals(code2.confirmCOde(99980001), true);
    }
    @Test
    public void confirmCOdeTest2(){
        // 코드가 맞는 경우
        Assertions.assertEquals(code.confirmCOde(codenum), true);
        Assertions.assertEquals(code2.confirmCOde(codenum2), true);
    }
    @Test
    public void confirmCOdeTest3(){
        // 코드가 틀린 경우
        Assertions.assertEquals(code.confirmCOde(99990001), false);
        Assertions.assertEquals(code2.confirmCOde(99990000), false);
    }
    @Test
    public void confirmCOdeTest4(){
        // 코드가 틀린 경우
        Assertions.assertEquals(code.confirmCOde(codenum2), false);
        Assertions.assertEquals(code2.confirmCOde(codenum), false);
    }
}
