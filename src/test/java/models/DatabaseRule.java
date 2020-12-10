package models;

import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {



    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/designwager_test", "murielle", "murielle12");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deletedesignersQuery = "DELETE FROM designers *;";
            String deleteclientsQuery = "DELETE FROM clients *;";

            con.createQuery(deletedesignersQuery).executeUpdate();
            con.createQuery(deleteclientsQuery).executeUpdate();

        }
    }

}