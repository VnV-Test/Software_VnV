import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {
    private Message msg;
    private VM vm;
    Thread t3;

    @BeforeEach
    void setUp() throws Exception {
        msg = new Message(0, 9999, 0, "Hello World");
        vm = new VM(9999, new double[]{0f, 0f}, 1);
        t3 = new RecieveMail(vm);

    }

    @Test
    void get_idTest() {
        Assertions.assertEquals(msg.getSrc_id(), 0);
        Assertions.assertEquals(msg.getDst_id(), 9999);
        Assertions.assertEquals(msg.getMsgtype(), 0);
        Assertions.assertEquals(msg.getMsgField(), "Hello World");
    }

    @Test
    void msgTest() {
        t3.start();
        msg.send();
        while(vm.getMailBoxSize()==0);
        Assertions.assertEquals(vm.getMailBoxSize(), 1);
        Assertions.assertEquals(vm.mailBox.get(0).getMsgField(), "Hello World");
    }
}
