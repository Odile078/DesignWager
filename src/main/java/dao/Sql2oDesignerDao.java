package dao;

import models.Designer;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDesignerDao implements DesignerDao{
    private final Sql2o sql2o;
    public Sql2oDesignerDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Designer designer) {
        String sql ="INSERT INTO designers (name,email,phone_number,designing_fields,experience,website,createdat) VALUES (:name,:email,:phone_number,:designing_fields,:experience,:website,:createdat)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(designer)
                    .executeUpdate()
                    .getKey();
            designer.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Designer> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM designers")
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Designer.class);
        }
    }

    @Override
    public Designer findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM designers WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Designer.class);
        }
    }

    @Override
    public void update(int id, String name, String email, String phone_number, String designing_fields, String experience, String website) {
        String sql="UPDATE designers SET name=:name,email=:email,phone_number=:phone_number,designing_fields=:designing_fields,experience=:experience,website=:website  WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)

                    .addParameter("id",id)
                    .addParameter("name",name)
                    .addParameter("email",phone_number)
                    .addParameter("phone_number",phone_number)
                    .addParameter("designing_fields",designing_fields)
                    .addParameter("experience",experience)
                    .addParameter("website",website)

                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from designers WHERE id = :id"; //raw sql
       // String deleteJoin = "DELETE from designers_clients WHERE designer_id = :designer_d";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            //con.createQuery(deleteJoin)
                   // .addParameter("restaurantId", id)
                   // .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from designers";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
