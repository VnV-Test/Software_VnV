import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    Item i;
    @BeforeEach
    public void setUp() throws Exception {
        i = new Item();
    }

    @Test
    public void editgetTest() {
        Assertions.assertEquals(i.editName("str"),true);
        Assertions.assertEquals(i.getName(), "str");
        Assertions.assertEquals(i.editStock(123),false);
        Assertions.assertEquals(i.editStock(-1),false);
        Assertions.assertEquals(i.editStock(23),true);
        Assertions.assertEquals(i.getStock(), 23);
        Assertions.assertEquals(i.editPrice(12345),false);
        Assertions.assertEquals(i.editPrice(1234),true);
        Assertions.assertEquals(i.getPrice(), 1234);
    }
}
