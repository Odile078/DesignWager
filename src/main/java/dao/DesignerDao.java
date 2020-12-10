package dao;

import models.Client;
import models.Designer;

import java.util.List;

public interface DesignerDao {
    //create
    void add(Designer designer);
    //void addDesignerToClient(Designer Designer, Client Client);

    //read
    List<Designer> getAll();
    Designer findById(int id);


    //update
    //omit for now
    void update(int id,String name, String email, String phone_number, String designing_fields,String experience ,String website);
    void delete();
    void deleteById(int id);
    void clearAll();
}
