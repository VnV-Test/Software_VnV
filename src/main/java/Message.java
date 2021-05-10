import java.io.*;
import java.net.*;
import java.util.*;

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
    }

    public int getMsgtype(){
        return this.type;
    }

    public String getMsgField(){
        return this.description;
    }
}