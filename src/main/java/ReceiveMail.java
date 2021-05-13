import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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