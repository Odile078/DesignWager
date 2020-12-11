package dao;
import models.Designer;
import models.Review;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
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
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    @Test
    public void addingReviewSetsId() throws Exception {
        Designer testDesigner = setupDesigner();
        designerDao.add(testDesigner);
        Review testReview = new Review("Captain Kirk", "",testDesigner.getId());
        int originalReviewId = testReview.getId();
        reviewDao.add(testReview);
        assertNotEquals(originalReviewId,testReview.getId());
    }
    @Test
    public void getAll() throws Exception {
        Review review1 = setupReview();
        Review review2 = setupReview();
        assertEquals(2, reviewDao.getAll().size());
    }
    /*
        @Test
        public void getAllReviewsByRestaurant() throws Exception {
            Designer testDesigner = setupDesigner();
            Designer otherDesigner = setupDesigner();
            Review review1 = setupReviewForDesigner(testDesigner);
            Review review2 = setupReviewForDesigner(testDesigner);
            Review reviewForOtherDesigner = setupReviewForDesigner(otherDesigner);
            assertEquals(2, reviewDao.getAllReviewsByDesigner(testDesigner.getId()).size());
        }*/
    @Test
    public void clearAll() throws Exception {
        Review testReview = setupReview();
        Review otherReview = setupReview();
        reviewDao.clearAll();
        assertEquals(0, reviewDao.getAll().size());
    }
    @Test
    public void deleteById() throws Exception {
        Review testReview = setupReview();
        Review otherReview = setupReview();
        assertEquals(2, reviewDao.getAll().size());
        reviewDao.deleteById(testReview.getId());
        assertEquals(1, reviewDao.getAll().size());
    }
    public Review setupReview() {
        Review review = new Review("great", "He does a great job in a short time", 1);
        reviewDao.add(review);
        return review;
    }
    public Designer setupDesigner(){
        return new Designer("Benitha","b@gmail.com","438695879657","interior house design","2 years","htpp:bjk");
    }
    public Review setupReviewForDesigner(Designer designer) {
        Review review = new Review("great", "He does a great job in a short time", designer.getId());
        reviewDao.add(review);
        return review;
    }
}