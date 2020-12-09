package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class DesignerTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();
    @Test
    public void Designer_instantiatesCorrectly_true() {
        Designer testDesigner = new Designer ("Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
        assertEquals(true, testDesigner instanceof Designer);
    }

  @Test
    public void getName() {
      Designer testDesigner = setupDesigner();
        assertEquals("Benitha", testDesigner.getName());
    }

    @Test
    public void setName() {
        Designer testDesigner = setupDesigner();
        testDesigner.setName("Odile");
        assertNotEquals("Benitha", testDesigner.getName());
    }
    @Test
    public void getEmail() {
        Designer testDesigner = setupDesigner();
        assertEquals("b@gmail.com", testDesigner.getEmail());
    }

    @Test
    public void setEmail() {
        Designer testDesigner = setupDesigner();
        testDesigner.setEmail("b@gmail.com");
        assertNotEquals("b2@gmail.com", testDesigner.getEmail());
    }
    @Test
    public void getPhone_number() {
        Designer testDesigner = setupDesigner();
        assertEquals("438695879657", testDesigner.getPhone_number());
    }

    @Test
    public void setPhone_number() {
        Designer testDesigner = setupDesigner();
        testDesigner.setPhone_number("438695879657");
        assertNotEquals("4381695879657", testDesigner.getPhone_number());
    }


    @Test
    public void getDesigning_fields() {
        Designer testDesigner = setupDesigner();
        assertEquals("interior house design", testDesigner.getDesigning_fields());

    }
    @Test
    public void setDesigning_fields() {
        Designer testDesigner = setupDesigner();
        testDesigner.setDesigning_fields("lobby design");
        assertNotEquals("interrior design", testDesigner.getDesigning_fields());
    }

    @Test
    public void getExperience() {
        Designer testDesigner = setupDesigner();
        assertEquals("2 years", testDesigner.getExperience());
    }

    @Test
    public void setExperience() {
        Designer testDesigner = setupDesigner();
        testDesigner.setExperience("2 years");
        assertNotEquals("1 years", testDesigner.getExperience());
    }


    @Test
    public void setId() {
        Designer testDesigner = setupDesigner();
        testDesigner.setId(5);
        assertEquals(5, testDesigner.getId());
    }
    @Test
    public void getWebsite() {
        Designer testDesigner = setupDesigner();

        assertEquals("htpp:bjk", testDesigner.getWebsite());
    }
    @Test
    public void setWebsite() {
        Designer testDesigner = setupDesigner();
        testDesigner.setWebsite("htpp:bjk.com");
        assertNotEquals("htpp:bjkstone.com", testDesigner.getWebsite());
    }
    @Test
    public void getCreatedat() {
    }
    @Test
    public void setCreatedat() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void Designer_throwsExceptionIfFieldsEmpty(){
        Designer testDesigner = new Designer("","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");

        if(testDesigner.getName().equals("")||testDesigner.getEmail().equals("")||testDesigner.getPhone_number().equals("")||testDesigner.getDesigning_fields().equals("")||testDesigner.getExperience().equals("")){
            testDesigner.save();
        }
    }
    /*
    @Test
    public void save_insertsObjectIntoDatabase_designersAndNamecanNotBeEmpty() {
        Designer testDesigner = setupDesigner();
        testDesigner.save();
        assertTrue(Designer.all().get(0).equals(testDesigner));
    }*/
    @Test
    public void addedDesignersAreReturnedFromAll() throws Exception {
        Designer testDesigner = setupDesigner();
        testDesigner.save();
        assertEquals(1, Designer.all().size());
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Designer testDesigner = setupDesigner();
        int originalDesignerId = testDesigner.getId();
        testDesigner.save();
        assertNotEquals(originalDesignerId, testDesigner.getId());
    }










    //HELPER

    public Designer setupDesigner(){
        return new Designer("Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
    }
}