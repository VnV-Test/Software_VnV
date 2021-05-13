public class Admin {
    private String adminID;
    private String adminPW;
    private String contact;

    public boolean checkIDPW(String id,String pw){
        return (adminID.equals(id) && adminPW.equals(pw));
    }
    public void editContact(String c){
        contact=c;
    }
    public Admin(){
        adminPW="admin";
        adminID="admin";
        contact="010-0000-0000";
    }
    public Admin(String i, String p, String c){
        adminPW=p;
        adminID=i;
        contact=c;
    }
    public String getContact(){
        return contact;
    }
}
