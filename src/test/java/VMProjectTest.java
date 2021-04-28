import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VMProjectTest {

    @Test
    void multiply(){
        Addnumber addnumber = new Addnumber();
        assertEquals(addnumber.add(8,9),17);
    }

    @Test
    void 곱하기(){
        Addnumber addnumber = new Addnumber();
        assertEquals(addnumber.sub(10,9),1);
    }



//    @Test
//    void join() {
//        Member member = new Member();
//        member.setName("VNV");
//        member.setId((long) 1.0);
//
//        //when
//        Long savedId = (long)1.0;
//
//        //then
//        //Assertions.assertThat(member.getName()).isEqaulTo("VNV");
//    }
}
