import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class RecieveMail extends Thread{
    public VM vm;

    public RecieveMail(VM v){
        super("rm"+v.getIDtS()+String.valueOf(v.getMailBoxSize()+1));
        this.vm = v;
    }
    @Override
    public void run(){
        BufferedReader in = null;
        ServerSocket listener = null;
        Socket socket_h = null;
        while(true){

            try{
                listener = new ServerSocket(vm.getID());
            } catch(IOException e) {
                System.out.println(e.getMessage() + "Here??");
            }
            try{
                while(true) {
                    socket_h = listener.accept();
                    System.out.println("Connected.");
                    ////////////////////////
//                Thread t3 = new RecieveMail(vm);
//                t3.start();
                    in = new BufferedReader(new InputStreamReader(socket_h.getInputStream()));

                    String inputMessage = in.readLine();

                    String[] tempS = inputMessage.split(",");
                    System.out.println("field" + inputMessage);
                    System.out.println("temp0 :" + tempS[0]);
                    System.out.println("temp0 :" + tempS[1]);
                    System.out.println("temp0 :" + tempS[2]);
                    Message mg = new Message(Integer.parseInt(tempS[0]), Integer.parseInt(tempS[1]), Integer.parseInt(tempS[2]), tempS[3]);
                    this.vm.mailRecieve(mg);
                }
            } catch(IOException e){
                System.out.println(e.getMessage()+"Here??");
            }
        }
    }
}