import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VMTest {
    private Message msg;
    private VM vm;
    Thread t3;
    private Admin a;
    private MainFrame m,m2, m3;
    private double[] loc;
    int d1, d2, d3;
    Thread t4;
    private VM vm2, vm3;
    Thread t5;
    Thread t6, t7, t8;
    int k1,k2,k3;
    Drink[] da, da2, da3;

    @BeforeEach
    void setUp() throws Exception {
        d1=0;
        d2=1;
        d3=2;
        msg = new Message(0, 9999, 0, "Hello World");

        loc = new double[]{0f, 0f};
        vm = new VM(9999, loc);
        vm2=new VM(9998, loc, d2);
        vm3=new VM(9997, loc, d3);
        t3 = new RecieveMail(vm);
        t4 = new ImListening(vm);
        t5 = new RecieveMail(vm2);
        t6 = new ImListening(vm2);
        t7 = new RecieveMail(vm3);
        t8 = new ImListening(vm3);
        a = new Admin("PW", "ID", "cont");
        m = new MainFrame(vm);
        m2 = new MainFrame(vm2);
        m3 = new MainFrame(vm3);
        switch (d1){
            case 1:
                k1=7;
                break;
            case 2:
                k1=13;
                break;
            default:
                k1=0;
        }
        switch (d2){
            case 1:
                k2=7;
                break;
            case 2:
                k2=13;
                break;
            default:
                k2=0;
        }
        switch (d3){
            case 1:
                k3=7;
                break;
            case 2:
                k3=13;
                break;
            default:
                k3=0;
        }
        da = vm.getDrinkArray();
        da2 = vm2.getDrinkArray();
        da3 = vm3.getDrinkArray();
    }

    @Test
    void findTest(){

        for(int i=0;i<20;i++){
            if(i>=k1&&i<=k1+6){
                Assertions.assertEquals(vm.findItem(da[i].getName()).getName(), da[i].getName());
                Assertions.assertEquals(vm.findItem(da[i].getName()).getPrice(), da[i].getPrice());
            }else
                Assertions.assertEquals(vm.findItem(da[i].getName()), null);
        }
        for(int i=0;i<20;i++) {
            Assertions.assertEquals(vm.findDrink(da[i].getName()), da[i]);
        }
        for(int i=0;i<20;i++){
            if(i>=k2&&i<=k2+6){
                Assertions.assertEquals(vm2.findItem(da2[i].getName()).getName(), da2[i].getName());
                Assertions.assertEquals(vm2.findItem(da2[i].getName()).getPrice(), da2[i].getPrice());
            }else
                Assertions.assertEquals(vm2.findItem(da2[i].getName()), null);
        }
        for(int i=0;i<20;i++) {
            Assertions.assertEquals(vm2.findDrink(da2[i].getName()), da2[i]);
        }
        for(int i=0;i<20;i++){
            if(i>=k3&&i<=k3+6){
                Assertions.assertEquals(vm3.findItem(da3[i].getName()).getName(), da3[i].getName());
                Assertions.assertEquals(vm3.findItem(da3[i].getName()).getPrice(), da3[i].getPrice());
            }else
                Assertions.assertEquals(vm3.findItem(da3[i].getName()), null);
        }
        for(int i=0;i<20;i++) {
            Assertions.assertEquals(vm3.findDrink(da3[i].getName()), da3[i]);
        }
        //카드리스트는 비교할 대상을 만들 수 없어서 구현할수없음.
    }
    @Test
    void checkgiveTest(){
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        //givecode는 gui라서 테스트x.
        //check Code는 메시지와 테스트.
        //drink리스트 가져오는것을 확인.하면서 CheckStock도 확인.
        for(int i=0;i<20;i++){
            if(i>=k1&&i<=k1+6){
                Assertions.assertEquals(vm.checkStock(da[i].getName()), true);
            }else
                Assertions.assertEquals(vm.checkStock(da[i].getName()), false);
        }
        for(int i=0;i<20;i++){
            if(i>=k2&&i<=k2+6){
                Assertions.assertEquals(vm2.checkStock(da2[i].getName()), true);
            }else
                Assertions.assertEquals(vm2.checkStock(da2[i].getName()), false);
        }
        for(int i=0;i<20;i++){
            if(i>=k3&&i<=k3+6){
                Assertions.assertEquals(vm3.checkStock(da3[i].getName()), true);
            }else
                Assertions.assertEquals(vm3.checkStock(da3[i].getName()), false);
        }

        //리스너는 자동으로 테스트되고, 결과가 제대로 나온다면, 중간과정의 함수들도 문제가 없는 것
        //MailReceive도 자동으로 검사됨.-저게 작동하지 않으면 나머지도 안하니까.
        for(int i=0;i<7;i++) {
            vm.requestPrepay(da[k2 + i].getName(), 9998);
            //여기서 코드가 왔는지 확인가능.(이 테스트 케이스만 별도로 돌려야함.)
            //givecode를 테스트하기 위해서는, while(true)도 필요하고, Assertion부분을 빼줘야함( 저번에 이부분에서 오류가 있어서 코드입력이 안됬음)
            while (vm2.codeempty()) ;
            Assertions.assertEquals(vm2.checkCode(99990000 + i * 2), da[k2 + i].getName());
            m.setpreDrinkname_test(da[k2 + i].getName());
            //vm.getOtherVM(da[k2+i].getName());
            vm2.requestPrepay(da[k1 + i].getName(), 9999);
            //여기서 코드가 왔는지 확인가능.(이 테스트 케이스만 별도로 돌려야함.)
            //givecode를 테스트하기 위해서는, while(true)도 필요하고, Assertion부분을 빼줘야함( 저번에 이부분에서 오류가 있어서 코드입력이 안됬음)
            while (vm.codeempty()) ;
            Assertions.assertEquals(vm.checkCode(99980000 + i), da[k1 + i].getName());
            m2.setpreDrinkname_test(da[k1 + i].getName());
            //vm.getOtherVM(da[k2+i].getName());


            vm.requestPrepay(da[k3 + i].getName(), 9997);
            //여기서 give code확인가능.(이 테스트 케이스만 별도로 돌려야함.)
            while (vm3.codeempty()) ;
            Assertions.assertEquals(vm3.checkCode(99990000 + i * 2 + 1), da[k3 + i].getName());
            m.setpreDrinkname_test(da[k3 + i].getName());
            //vm.getOtherVM(da[k3+i].getName());
        }
    }
    @Test
    void getOtherVM_3Test() {
        for (int i = 0; i < 10; i++) {
            if (i == 3 || i == 5 || i == 8) {
                new Message(i, 9999, 5, "trash");
                while (vm.getMailBoxSize() != 0) ;
                Assertions.assertEquals(vm.gov3_flag, false);
            } else {
                new Message(i, 9999, 5, "0-0-" + String.valueOf(i));
                while (vm.getMailBoxSize() != 0) ;
                Assertions.assertEquals(vm.gov3_flag, true);
            }
        }
//        vm
    }
    @Test
    void getsetTest() {
        Assertions.assertEquals(vm.setAdmin(a), a);
        Assertions.assertEquals(vm.setUI(m), m);
        Assertions.assertEquals(vm.getLocation(), loc);
        //mailbox사이즈는 다른곳에서 하긴했음.
        vm.editVMAddress("이쪽으로 가세요");
        Assertions.assertEquals(vm.getAddress(), "이쪽으로 가세요");
    }

}
