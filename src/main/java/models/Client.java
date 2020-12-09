package models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String project_name;
    private String Description_project ;
    private String Timeline_project;
    private String price;

    public Client( String name, String email, String phone_number, String project_name,String Description_project ,String Timeline_project,String price){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.project_name = project_name;
        this.Description_project = Description_project;
        this.Timeline_project = Timeline_project;
        this.price = price;
        this.id =id;

    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhone_number() { return phone_number; }
    public int getId() { return id; }



    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public void setId(int id) { this.id = id; }

    public String getDescription_project() {
        return Description_project;
    }

    public void setDescription_project(String description_project) {
        Description_project = description_project;
    }
}
