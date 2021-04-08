import java.util.*;

public class VMProject {

    public static void main(String[] args) {
        /*System.out.println("Hello world");
        System.out.println("Jira 이슈 연동 테스트");*/
        Addnumber addnumber = new Addnumber();

        System.out.println(addnumber.add(2,6));
        System.out.println(addnumber.add(2,3,4));
    }

    public Member join(){
        Member member = new Member();
        member.setName("VNV");
        member.setId((long) 1.0);

        return member;
    }
}
