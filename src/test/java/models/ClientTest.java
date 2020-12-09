package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();
    @Test
    public void Designer_instantiatesCorrectly_true() {
        Client testClient = new Client ("Aisha","b@gmail.com","43875879657","house design","2 years","htpp:bjk","100,000Rwf");
        assertEquals(true, testClient instanceof Client);
    }


}