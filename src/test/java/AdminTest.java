import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {
    private Admin ad;

    @BeforeEach
    public void setUp() throws Exception {
        ad = new Admin();
    }

    @Test
    void AdminLogin() {
        Assertions.assertEquals(ad.checkIDPW("admin", "admin"), true);
        Assertions.assertEquals(ad.checkIDPW("admin", "oulala"), false);
        Assertions.assertEquals(ad.checkIDPW("metallica", "dreamtheater"), false);
        Assertions.assertEquals(ad.checkIDPW("überraschung", "남승협잘생겼다."), false);
    }

    @Test
    void editContactTest(){
        Assertions.assertEquals(ad.editContact("010-4017-4928"), "010-4017-4928");
        Assertions.assertEquals(ad.editContact("010-4017-1234"), "010-4017-1234");
    }
}
