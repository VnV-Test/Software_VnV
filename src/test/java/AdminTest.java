import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTest {
    private Admin ad,admin;

    @BeforeEach
    public void setUp() throws Exception {
        ad = new Admin();
        admin = new Admin("2721ckd","875421","");
    }

    @Test
    void AdminLogin() {
        // 로그인이 성공하는 경우
        Assertions.assertEquals(ad.checkIDPW("admin", "admin"), true);
        Assertions.assertEquals(admin.checkIDPW("2721ckd", "875421"), true);
    }
    @Test
    void AdminLogin2() {
        // 일치하는 ID가 없는 경우
        Assertions.assertEquals(ad.checkIDPW("metallica", "dreamtheater"), false);
        Assertions.assertEquals(ad.checkIDPW("überraschung", "남승협잘생겼다."), false);
        Assertions.assertEquals(admin.checkIDPW("loginID", "password"), false);
    }
    @Test
    void AdminLogin3() {
        // PW가 일치하지 않는 경우
        Assertions.assertEquals(ad.checkIDPW("admin", "875421"), false);
        Assertions.assertEquals(ad.checkIDPW("admin", ""), false);
        Assertions.assertEquals(ad.checkIDPW("2721ckd", ""), false);
        Assertions.assertEquals(ad.checkIDPW("2721ckd", "admin"), false);
    }
    @Test
    void editContactTest(){
        Assertions.assertEquals(ad.editContact("010-4017-4928"), "010-4017-4928");
        Assertions.assertEquals(ad.getContact(), "010-4017-4928");
        Assertions.assertEquals(ad.editContact("010-4017-1234"), "010-4017-1234");
        Assertions.assertEquals(admin.editContact("010-4000-3000"), "010-4000-3000");
    }
}
