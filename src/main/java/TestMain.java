import java.io.*;
import java.net.*;
import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[] location = { 37.54164, 127.07880 };
        VM vm = new VM(9999, location);

        Thread t1 = new RecieveMail(vm);
        Thread t2 = new ImListening(vm);

        t1.start();
        t2.start();

        MainFrame main = new MainFrame(vm);
    }
}

class ImListening extends Thread {
    public VM vm

    public ImListening(VM v){
        super("Im Listening");
        this.vm = v;
    }

    @Override
    public void run(){
        while(true){
            if(vm.mailBox[1].size() > 0 || vm.mailBox[4].size() > 0){
                vm.NotifyVMsInfo();
            }
            if(vm.mailBox[3].size() > 0){
                vm.confirmPrepay();
            }
            if(vm.mailBox[6].size() > 0){
                vm.RespondSell();
            }
            if(vm.mailBox[7].size() > 0){
                vm.ConfirmSell_2();
            }
            if(vm.mailBox[8].size() > 0){
                vm.requestPrepay_2();
            }
        }
    }
}




class RecieveMail extends Thread{
    public VM vm;

    public RecieveMail(VM v){
        super("rm");
        this.vm = v;
    }

    @Override
    public void run(){
        BufferedReader in = null;
        ServerSocket listener = null;
        Socket socket = null;

        while(true){

            try{
                listener = new ServerSocket(vm.getID());
            } catch(IOException e) {
                System.out.println(e.getMessage() + "Here??");
            }

            try{
                socket = listener.accept();
                System.out.println("Connected.");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String inputMessage = in.readLine();

                String[] tempS = inputMessage.split(",");

                Message mg = new Message(Integer.valueOf(tempS[0]), Integer.valueOf(tempS[1]), Integer.valueOf(tempS[2]), tempS[3]);
                this.vm.MailRecieve(mg);

            } catch(IOException e){
                System.out.println(e.getMessage()+"Here??");
            } finally {
                try{
                    socket.close();
                    listener.close();
                } catch (IOException e){
                    System.out.println("Error occured while chatting with client.");
                }
            }
        }
    }
}