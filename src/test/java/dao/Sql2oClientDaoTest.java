package dao;
import models.Client;
import models.Designer;
import models.Review;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static org.junit.Assert.*;
public class Sql2oClientDaoTest {
    private static Connection conn;
    private static Sql2oDesignerDao designerDao;
    private static Sql2oClientDao clientDao;
    private static Sql2oReviewDao reviewDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/wagerdesign_test";
        Sql2o sql2o = new Sql2o(connectionString, "benitha", "123");
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

    @AfterClass     //changed to @AfterClass (run once after all tests in this file completed)
    public static void shutDown() throws Exception { //changed to static
        conn.close(); // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingClientsSetsId() throws Exception {
        Client testClient = setupClient();
        int originalClientId = testClient.getId();
        clientDao.add(testClient);
        assertNotEquals(originalClientId, testClient.getId());
    }


@Test
public void noClientsReturnsEmptyList() throws Exception {
    assertEquals(0, clientDao.getAll().size());
}
public Client setupClient(){
    return new Client("Ange","b@gmail.com","4385879657","interior house design","house designing ","2 months","");
}

}