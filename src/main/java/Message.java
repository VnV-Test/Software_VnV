import java.io.*;
import java.net.*;
import java.util.*;

class Mail extends Thread{
    public int src_id;
    public int dst_id;
    private int type;
    private String description;

    public Mail(int src_id, int dst_id, int type, String description){
        super("mail"+String.valueOf(src_id)+String.valueOf(dst_id));
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
    private int src_id;
    private int dst_id;
    private int type;
    private String description;
    private int[] portArr = {9999, 9998,9997,9996,9995,9994,9993,9992,9991,9990};

    public Message(int src_id, int dst_id, int type, String description){
        this.src_id = src_id;
        this.dst_id = dst_id;
        this.type = type;
        this.description = description;
    }

    public void Send() {
        if(dst_id == 0){
            for(int dest : this.portArr) {
                if(dest == this.src_id){ continue; }
                Thread t1 = new Mail(this.src_id, dest, this.type, this.description);
                t1.start();
            }
        }
        else{
            Thread t2 = new Mail(this.src_id, this.dst_id, this.type, this.description);
            t2.start();
        }

    }

    public int getSrc_id(){
        return this.src_id;
    }
    public int getDst_id(){
        return this.dst_id;
    }
    public int getMsgtype(){
        return this.type;
    }
    public String getMsgField(){
        return this.description;
    }
}