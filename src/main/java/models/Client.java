package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static models.DB.sql2o;

public class Client implements Data{
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String project_name;
    private String description_project ;
    private String timeline_project;
    private String price;
    private int designer_id;

    public Client( String name, String email, String phone_number, String project_name,String description_project ,String timeline_project,String price){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.project_name = project_name;
        this.description_project = description_project;
        this.timeline_project = timeline_project;
        this.price = price;
        this.id =id;
        this.designer_id =designer_id;

    }

    public Client( String name, String email, String phone_number, String project_name,String description_project ,String timeline_project,String price, int designer_id){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.project_name = project_name;
        this.description_project = description_project;
        this.timeline_project = timeline_project;
        this.price = price;
        this.id =id;
        this.designer_id =designer_id;

    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhone_number() { return phone_number; }
    public int getId() { return id; }
    public String getTimeline_project() { return timeline_project; }
    public String getPrice() { return price; }
    public String getProject_name() { return project_name; }
    public int getDesigner_id() { return designer_id; }

    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public void setId(int id) { this.id = id; }

    public String getDescription_project() { return description_project; }

    public void setDescription_project(String description_project) { description_project = description_project; }

    public void setPrice(String price) { this.price = price; }
    public void setProject_name(String project_name) { this.project_name = project_name; }
    public void setTimeline_project(String timeline_project) { this.timeline_project = timeline_project; }


    public void setDesigner_id(int designer_id) {
        this.designer_id = designer_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                designer_id == client.designer_id &&
                Objects.equals(name, client.name) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone_number, client.phone_number) &&
                Objects.equals(project_name, client.project_name) &&
                Objects.equals(description_project, client.description_project) &&
                Objects.equals(timeline_project, client.timeline_project) &&
                Objects.equals(price, client.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone_number, project_name, description_project, timeline_project, price, designer_id);
    }


    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
