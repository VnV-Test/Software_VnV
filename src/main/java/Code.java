public class Code {
    private int code_num;
    private String name;

    public String getName() {
        return name;
    }

    public int getCode() {
        return code_num;
    }
    public boolean confirmCOde(int c){
        return c==code_num;
    }

    public Code(int c, String n){
        code_num = c;
        name = n;
    }
}
