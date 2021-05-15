import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkTest {
    Drink d;
    @BeforeEach
    public void setUp() throws Exception {
        d = new Drink();
    }

    @Test
    public void editgetTest() {
        d.setName("str");
        Assertions.assertEquals(d.getName(), "str");
        d.setPrice(12345);
        Assertions.assertEquals(d.getPrice(), 12345);
    }
}
