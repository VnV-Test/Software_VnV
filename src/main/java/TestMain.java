public class TestMain {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[] location = { 37.54164, 127.07880 };
        VM vm = new VM(9999, location,0);
        MainFrame main = new MainFrame(vm);
        Thread t1 = new RecieveMail(vm);
        Thread t2 = new ImListening(vm);

        t1.start();
        t2.start();
    }
}

