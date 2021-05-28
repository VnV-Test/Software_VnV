import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DrinkTest {
    Drink drink,drink2;
    @BeforeEach
    public void setUp() throws Exception {
        drink = new Drink("coke",1000);
        drink2 = new Drink("mint coke",1200);
    }
    @Test
    public void getNameTest() {
        Assertions.assertEquals(drink.getName(), "coke");
        Assertions.assertEquals(drink2.getName(), "mint coke");
    }
    @Test
    public void getPriceTest() {
        Assertions.assertEquals(drink.getPrice(), 1000);
        Assertions.assertEquals(drink2.getPrice(), 1200);
    }
    @Test
    public void setNameTest() {
        drink.setName("sprite");
        Assertions.assertEquals(drink.getName(), "sprite");
        drink.setName("coke");
        Assertions.assertEquals(drink.getName(), "coke");
    }
    @Test
    public void setPriceTest() {
        drink.setPrice(800);
        Assertions.assertEquals(drink.getPrice(), 800);
        drink2.setPrice(1000);
        Assertions.assertEquals(drink2.getPrice(), 1000);
    }
}
