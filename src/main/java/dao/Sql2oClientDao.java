package dao;

import models.Client;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oClientDao implements ClientDao {
    private final Sql2o sql2o;
    public Sql2oClientDao(Sql2o sql2o) { this.sql2o = sql2o; }


    @Override
    public void add(Client client) {

    }

    @Override
    public List<Client> getAll() {
        return null;
    }

    @Override
    public Client findById(int id) {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
