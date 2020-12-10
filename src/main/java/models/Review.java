package models;

import java.sql.Timestamp;
import java.util.Date;

public class Review {
    public int id;
    public String user_name;
    public String content;
    public int designer_id;
    private Date date= new Date();
    private Timestamp time;
    public Review(String content,String user_name,  int designer_id ) {
        this.content = content;
        this.user_name = user_name;
        this.content = content;
        this.designer_id =designer_id;
        this.time = new Timestamp(date.getTime());


    }

    public String getUser_name() { return user_name; }
    public String getContent() { return content; }
    public int getDesigner_id() { return designer_id; }
    public Timestamp getTime() { return time; }
    //public int getClient_id() { return client_id; }
    public int getId() { return id; }

    public void setUser_name(String user_name) { this.user_name = user_name; }
    public void setContent(String content) { this.content = content; }
    public void setDesigner_id(int designer_id) { this.designer_id = designer_id; }
    public void setTime(Timestamp time) { this.time = time; }
    //public void setClient_id(int client_id) { this.client_id = client_id; }
    public void setId(int id) { this.id = id; }
}
