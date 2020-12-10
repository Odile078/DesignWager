package dao;

import models.Designer;
import models.Review;

import java.util.List;

public interface ReviewDao {
    //create
    void add(Review review);
    //void addDesignerToClient(Designer Designer, Client Client);

    //read
    List<Review> getAll();
    Review findById(int id);


    //update
    //omit for now

    void delete();
    void deleteById(int id);
    void clearAll();
}
