import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    Item item, item2;
    @BeforeEach
    public void setUp() throws Exception {
        item = new Item("coke",1000,20);
        item2 = new Item("mint coke",800,5);
    }
    @Test
    public void getNameTest() {
        Assertions.assertEquals(item.getName(), "coke");
        Assertions.assertEquals(item2.getName(), "mint coke");
    }
    @Test
    public void getPriceTest() {
        Assertions.assertEquals(item.getPrice(), 1000);
        Assertions.assertEquals(item2.getPrice(), 800);
    }
    @Test
    public void editPriceTest() {
        Assertions.assertEquals(item.editPrice(1500),true);
        Assertions.assertEquals(item2.editPrice(1000),true);
        Assertions.assertEquals(item.getPrice(), 1500);
        Assertions.assertEquals(item2.getPrice(), 1000);
    }
    @Test
    public void editPriceTest2() {
        Assertions.assertEquals(item.editPrice(20000),false);
        Assertions.assertEquals(item2.editPrice(30000),false);
    }
    @Test
    public void editNameTest() {
        Assertions.assertEquals(item.editName("sprite"),true);
        Assertions.assertEquals(item2.editName("mint sprite"),true);
        Assertions.assertEquals(item.getName(), "sprite");
        Assertions.assertEquals(item2.getName(), "mint sprite");
        Assertions.assertEquals(item.editName("str"),true);
        Assertions.assertEquals(item2.editName("mint str"),true);
        Assertions.assertEquals(item.getName(), "str");
        Assertions.assertEquals(item2.getName(), "mint str");
    }
    @Test
    public void editStockTest() {
        Assertions.assertEquals(item.editStock(50),true);
        Assertions.assertEquals(item.editStock(10),true);
        Assertions.assertEquals(item.editStock(0),true);
        Assertions.assertEquals(item2.editStock(50),true);
        Assertions.assertEquals(item2.editStock(1),true);
        Assertions.assertEquals(item2.editStock(0),true);
    }
    @Test
    public void editStockTest2() {
        Assertions.assertEquals(item.editStock(100),false);
        Assertions.assertEquals(item.editStock(-5),false);
        Assertions.assertEquals(item.editStock(10000),false);
        Assertions.assertEquals(item2.editStock(100),false);
        Assertions.assertEquals(item2.editStock(-150),false);
        Assertions.assertEquals(item2.editStock(150),false);
    }
}
