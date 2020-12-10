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

    public Client setupClient(){
        return new Client("Aisha","b@gmail.com","43875879657","house design","htpp:bjk","2 years","100,000Rwf");
    }

    @Test
    public void getEmail() {
        Client testClient = setupClient();
        assertEquals("b@gmail.com", testClient.getEmail());
    }

    @Test
    public void getName() {
        Client testClient = setupClient();
        assertEquals("Aisha", testClient.getName());
    }

    @Test
    public void getPhone_number() {
        Client testClient = setupClient();
        assertEquals("43875879657", testClient.getPhone_number());
    }

    @Test
    public void getTimeline_project() {
        Client testClient = setupClient();
        assertEquals("2 years", testClient.getTimeline_project());
    }

    @Test
    public void getPrice() {
        Client testClient = setupClient();
        assertEquals("100,000Rwf", testClient.getPrice());
    }

    @Test
    public void getProject_name() {
        Client testClient = setupClient();
        assertEquals("house design", testClient.getProject_name());
    }

    @Test
    public void setEmail() {
        Client testClient = setupClient();
        testClient.setEmail("b@gmail.com");
        assertNotEquals("b21@gmail.com", testClient.getEmail());
    }

    @Test
    public void setName() {
        Client testClient = setupClient();
        testClient.setName("Aisha");
        assertNotEquals("Odile", testClient.getName());
    }

    @Test
    public void setPhone_number() {
        Client testClient = setupClient();
        testClient.setPhone_number("43875879657");
        assertNotEquals("43875879324", testClient.getPhone_number());
    }

    @Test
    public void setId() {
        Client testClient = setupClient();
        testClient.setId(5);
        assertEquals(5, testClient.getId());
    }

    @Test
    public void getDescription_project() {
        Client testClient = setupClient();
        assertEquals("htpp:bjk", testClient.getDescription_project());
    }

    @Test
    public void setDescription_project() {
        Client testClient = setupClient();
        testClient.setDescription_project("htpp:bjk");
        assertNotEquals("htpp:bjke", testClient.getDescription_project());
    }

    @Test
    public void setPrice() {
        Client testClient = setupClient();
        testClient.setPrice("1OO,OOORwf");
        assertNotEquals("120,000Rwf", testClient.getPrice());
    }

    @Test
    public void setProject_name() {
        Client testClient = setupClient();
        testClient.setProject_name("house design");
        assertNotEquals("mansion design", testClient.getProject_name());
    }

    @Test
    public void setTimeline_project() {
        Client testClient = setupClient();
        testClient.setTimeline_project("2 years");
        assertNotEquals("1 years", testClient.getTimeline_project());
    }

    @Test
    public void setDesigner_id() {
        Client testClient = setupClient();
        testClient.setDesigner_id(2);
        assertNotEquals(3, testClient.getDesigner_id());
    }
}