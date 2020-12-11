package dao;

import models.Client;
import models.Designer;

import java.util.List;

public interface ClientDao {
    //create
    void add(Client client);
    //void addClientToDesigner(Client Client, Designer Designer);
    List<Client> getAllClientsByDesigner(int designer_id);
    //read
    List<Client> getAll();
    Client findById(int id);
    //update
//omit for now
    void delete();
    void deleteById(int id);
    void clearAll();
}
