public class Code {
    private int code;
    private String name;

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }
    public boolean confirmCOde(int c){
        return c==code;
    }

    public Code(int c, String n){
        code = c;
        name = n;
    }
}
