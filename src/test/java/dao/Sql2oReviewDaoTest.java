package dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {
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

}