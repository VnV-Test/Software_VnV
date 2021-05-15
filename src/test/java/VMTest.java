import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VMTest {
    private Message msg;
    private VM vm;
    Thread t3;
    private Admin a;
    private MainFrame m,m2;
    private double[] loc;
    int d;
    Thread t4;
    private VM vm2;
    Thread t5;
    Thread t6;
    int k;

    @BeforeEach
    void setUp() throws Exception {
        d=0;
        msg = new Message(0, 9999, 0, "Hello World");

        loc = new double[]{0f, 0f};
        vm = new VM(9999, loc, d);
        vm2=new VM(9998, loc, d);
        t3 = new RecieveMail(vm);
        t4 = new ImListening(vm);
        t5 = new RecieveMail(vm2);
        t6 = new ImListening(vm2);
        a = new Admin("PW", "ID", "cont");
        m = new MainFrame(vm);
        m2 = new MainFrame(vm2);
        switch (d){
            case 1:
                k=7;
                break;
            case 2:
                k=13;
                break;
            default:
                k=0;
        }
    }

    @Test
    void getsetTest() {
        Assertions.assertEquals(vm.setAdmin(a), a);
        Assertions.assertEquals(vm.setUI(m), m);
        Assertions.assertEquals(vm.getLocation(), loc);
        //mailbox사이즈는 다른곳에서 하긴했음.
        vm.editVMAddress("이쪽으로 가세요");
        Assertions.assertEquals(vm.getAddress(), "이쪽으로 가세요");
        Assertions.assertEquals(vm.getMailBoxSize(), 0);
        vm.editVMID(9998);
        Assertions.assertEquals(vm.getID(), 9998);
        vm.editVMID(9999);
        Assertions.assertEquals(vm.getID(), 9999);
        Assertions.assertEquals(vm.getIDtS(), "9999");
    }
    @Test
    void findTest(){
        Drink[] da = vm.getDrinkArray();
        for(int i=0;i<20;i++){
            if(i>=k&&i<=k+6){
                Assertions.assertEquals(vm.findItem(da[i].getName()).getName(), da[i].getName());
                Assertions.assertEquals(vm.findItem(da[i].getName()).getPrice(), da[i].getPrice());
            }else
                Assertions.assertEquals(vm.findItem(da[i].getName()), null);
        }
        for(int i=0;i<20;i++) {
            Assertions.assertEquals(vm.findDrink(da[i].getName()), da[i]);
        }
        //카드리스트는 비교할 대상을 만들 수 없어서 구현할수없음.
    }


    @Test
    void checkgiveTest(){
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        //givecode는 gui라서 테스트x.
        //check Code는 메시지와 테스트.
        //drink리스트 가져오는것을 확인.하면서 CheckStock도 확인.
        Drink[] da = vm.getDrinkArray();

        for(int i=0;i<20;i++){
            if(i>=k&&i<=k+6){
                Assertions.assertEquals(vm.CheckStock(da[i].getName()), true);
           }else
               Assertions.assertEquals(vm.CheckStock(da[i].getName()), false);
        }

        //리스너는 자동으로 테스트되고, 결과가 제대로 나온다면, 중간과정의 함수들도 문제가 없는것임.
        //MailReceive도 자동으로 검사됨.-저게 작동하지 않으면 나머지도 안하니까.

        vm.requestPrepay(da[k+3].getName(),9999);
        //여기서 give code확인가능.(이 테스트 케이스만 별도로 돌려야함.)
        while(vm.codeempty());
        Assertions.assertEquals(vm.checkCode(99990000), da[k+3].getName());
        m2.setpreDrinkname_test(da[k+3].getName());
        vm2.getOtherVM(da[k+3].getName());
        while (true);
        //여기까지 왔으면 위 테스트는 다통과한것.결과창을 보여주기 위해서 그냥 둠.
    }
}
