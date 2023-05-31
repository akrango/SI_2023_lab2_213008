import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SILab2Test {
    //allusers={krango, angela@gmail.com}{krango, krango@gmail.com}{angela, krango@gmail.com}
    ArrayList<User> users=new ArrayList<User>();
    @Test
    public void everyBranchTest(){
        //user=null allusers.size=0
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.function(null, users));
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //username=null, email=angela@gmail.com pass=pass, allusers.size=0
        assertFalse(SILab2.function(new User(null, "pass",  "angela@gmail.com"),
                new ArrayList<User>(){}));

        users.add(new User("krango", "password", "angela@gmail.com"));
        users.add(new User("krango", "password", "krango@gmail.com"));
        users.add(new User("angela", "password", "krango@gmail.com"));

        //username=krango, email=angelagmail.com pass=p ssword, allusers.size=3
        assertFalse(SILab2.function(new User("krango", "p ssword",  "angelagmail.com"), users));

        //username=krango, email=angela@gmail.com pass=p@ssword, allusers.size=3
        assertFalse(SILab2.function(new User("krango", "p@ssword",  "angela@gmail.com"), users));

        //username=krango, email=angela@gmail.com pass=password, allusers.size=3
        assertFalse(SILab2.function(new User("krango", "password",  "angela@gmail.com"), users));

    }

    @Test
    public void multipleConditionTest(){
        //user=null
        RuntimeException ex= assertThrows(RuntimeException.class,()->SILab2.function(null, users) );
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //user={username="angela", password="", email="angela@gmail.com"}
        ex= assertThrows(RuntimeException.class,()->SILab2.function(new User("angela",null, "angela@gmail.com"), users) );
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //user={username="angela", password="p@ssword", email=""}
        ex= assertThrows(RuntimeException.class,()->SILab2.function(new User("angela","p@ssword", null), users) );
        assertTrue(ex.getMessage().contains("Mandatory information missing!"));

        //user={username="angela", password="p@ssword", email="angela@gmail.com"}
        assertTrue(SILab2.function(new User("angela","p@ssword", "angela@gmail.com"), users) );
    }
}
