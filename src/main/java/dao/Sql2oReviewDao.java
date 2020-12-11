package dao;
import models.Client;
import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;
public class Sql2oReviewDao implements ReviewDao{
    private final Sql2o sql2o;
    public Sql2oReviewDao(Sql2o sql2o) { this.sql2o = sql2o; }
    @Override
    public void add(Review review) {
        String sql ="INSERT INTO reviews (user_name,content,designer_id,time) VALUES (:user_name,:content,:designer_id,:time)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(review)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Review> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews")
                    .executeAndFetch(Review.class);
        }
    }
    @Override
    public List<Review> getAllReviewsByDesigner(int designer_id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE designer_id = :designer_id")
                    .addParameter("designer_id", designer_id)
                    .executeAndFetch(Review.class);
        }
    }
    @Override
    public Review findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM reviews WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Review.class);
        }
    }
    @Override
    public void delete() {
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE from reviews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}