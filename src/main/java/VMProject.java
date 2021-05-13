import java.util.*;

public class VMProject {

    public static void main(String[] args) {
        double[] location2 = { 47.54164, 137.07880 };
        VM vm2= new VM(9998, location2,1);
        MainFrame main2 = new MainFrame(vm2);
        Thread t3 = new RecieveMail(vm2);
        Thread t4 = new ImListening(vm2);

        t3.start();
        t4.start();
    }
    private void store(){
        int n1=0,n2=1,n3,i,count=10;
        System.out.print(n1+" "+n2);//printing 0 and 1

        for(i=2;i<count;++i)//loop starts from 2 because 0 and 1 are already printed
        {
            n3=n1+n2;
            System.out.print(" "+n3);
            n1=n2;
            n2=n3;
        }
        int rows = 5;
        for (int k = 1; k <= rows; ++k) {
            for (int j = 1; j <= k; ++j) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
