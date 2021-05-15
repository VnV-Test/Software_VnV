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
        Assertions.assertEquals(i.editStock(123),true);
        Assertions.assertEquals(i.getStock(), 123);
        Assertions.assertEquals(i.editPrice(12345),true);
        Assertions.assertEquals(i.getPrice(), 12345);
    }
}
