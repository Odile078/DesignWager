package dao;

import models.Designer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDesignerDaoTest {
    private static Connection conn;
    private static Sql2oDesignerDao designerDao;
    private static Sql2oClientDao clientDao;
    private static Sql2oReviewDao reviewDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/wagerdesign_test";
        Sql2o sql2o = new Sql2o(connectionString, "odile", "123");
        designerDao = new Sql2oDesignerDao(sql2o);
        clientDao = new Sql2oClientDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        designerDao.clearAll();
        clientDao.clearAll();
        reviewDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void addingDesignerSetsId() throws Exception {
        Designer testDesigner = setupDesigner();
        int originalDesignerId = testDesigner.getId();
        designerDao.add(testDesigner);
        assertNotEquals(originalDesignerId, testDesigner.getId());
    }
    @Test
    public void addedDesignerAreReturnedFromGetAll() throws Exception {
        Designer testDesigner = setupDesigner();
        assertEquals(1, designerDao.getAll().size());
    }

    @Test
    public void noDesignersReturnsEmptyList() throws Exception {
        assertEquals(0, designerDao.getAll().size());
    }
/*
    @Test
    public void find_returnsPersonWithSameId_secondRanger() {

        Designer testDesigner = new Designer("Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
        designerDao.add(testDesigner);

        Designer otherDesigner = new Designer("Benita","b@gmail.co","438695879657","interior house design","2 years","htpp:bjk");
        designerDao.add(otherDesigner);

        assertEquals(designerDao.findById(otherDesigner.getId()), otherDesigner);
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Designer testDesigner = setupDesigner();
        designerDao.update(testDesigner.getId(), "Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
       Designer foundDesigner = designerDao.findById(testDesigner.getId());
        assertEquals("Benith", foundDesigner.getName());
        assertEquals("438695879657", foundDesigner.getPhone_number());
        assertEquals("interior house design", foundDesigner.getDesigning_fields());
        assertEquals("2 years", foundDesigner.getExperience());
        assertEquals("htpp:bjk", foundDesigner.getWebsite());
        assertEquals("b@gmail.com", foundDesigner.getEmail());
    }*/
    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Designer testDesigner = setupDesigner();
        designerDao.deleteById(testDesigner.getId());
        assertEquals(0, designerDao.getAll().size());
    }


//HELPER

    public Designer setupDesigner(){
        return new Designer("Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
    }

}