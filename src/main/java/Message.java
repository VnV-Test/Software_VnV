import java.io.*;
import java.net.*;
import java.util.*;

class Mail extends Thread{
    public int src_id;
    public int dst_id;
    private int type;
    private String description;

    public Mail(int src_id, int dst_id, int type, String description){
        super("mail");
        this.src_id = src_id;
        this.dst_id = dst_id;
        this.type = type;
        this.description = description;
    }


    @Override
    public void run(){
        BufferedWriter out = null;
        ServerSocket listener = null;
        Socket socket = null;

        try {
            socket = new Socket("localhost", dst_id);
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String outputMessage= String.valueOf(src_id)+','+String.valueOf(dst_id)+','+String.valueOf(type)+','+description;

            out.write(outputMessage + "\n");
            out.flush();

        }catch(IOException e){
            System.out.println(e.getMessage());
        } finally {
            try{
                if(socket != null) socket.close();
            } catch (IOException e){
                System.out.println("Error occured.");
            }
        }
    }
}

public class Message {
    public int src_id;
    public int dst_id;
    private int type;
    private String description;

    public Message(int src_id, int dst_id, int type, String description){
        this.src_id = src_id;
        this.dst_id = dst_id;
        this.type = type;
        this.description = description;
    }

    public void Send() {
        //TODO
        Thread t = new Mail(this.src_id, this.dst_id, this.type, this.description);
        t.start();
    }

    public int getMsgtype(){
        return this.type;
    }

    public String getMsgField(){
        return this.description;
    }
}