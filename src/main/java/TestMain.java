import java.io.*;
import java.net.*;
import java.util.*;

public class TestMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double[] location = { 37.54164, 127.07880 };
        VM vm = new VM(9999, location);

        Thread t = new RecieveMail(vm);
        t.start();

        MainFrame main = new MainFrame(vm);
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