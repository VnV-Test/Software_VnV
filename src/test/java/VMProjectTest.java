import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VMProjectTest {

    @Test
    void multiply(){
        Addnumber addnumber = new Addnumber();
        assertEquals(addnumber.add(8,9),16);
    }

    @Test
    void 빼기(){
        Addnumber addnumber = new Addnumber();
        assertEquals(addnumber.sub(10,9),1);
    }
    
    @Test
    void 빼기2(){
        Addnumber addnumber = new Addnumber();
        assertEquals(addnumber.sub(10,3),5);
    }

