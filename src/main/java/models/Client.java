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

    public void save(){
        if(this.name.equals("")||this.email.equals("")||this.phone_number.equals("")||this.project_name.equals("")||this.description_project.equals("")||this.name.equals(null)||this.email.equals(null)||this.phone_number.equals(null)||this.project_name.equals(null)||this.description_project.equals(null)){
            throw new IllegalArgumentException("Fill all the fields");
        }
        try (Connection con= sql2o.open()){
            String sql ="INSERT INTO clients (name,email,phone_number,project_name,description_project,timeline_project,price,designer_id) VALUES (:name,:email,:phone_number,:project_name,:description_project,:timeline_project,:price,designer_id)";
            String joindesigner="INSERT INTO designers_clients (designer_id,client_id) VALUES (:designer_id,:client_id)";
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("email",this.email)
                    .addParameter("phone_number",this.phone_number)
                    .addParameter("project_name",this.project_name)
                    .addParameter("description_project",this.description_project)
                    .addParameter("timeline_project",this.timeline_project)
                    .addParameter("price",this.price)
                    .addParameter("designer_id",this.designer_id)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
            con.createQuery(joindesigner).addParameter("designer_id",this.getDesigner_id()).addParameter("client_id",
                    this.getId()).executeUpdate();
        }
    }
    public static List<Client> all(){
        try (Connection con= sql2o.open()){
            String sql="SELECT * FROM clients";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Client.class);
        }
    }
    public Client findById(int id) {
        try (Connection con= sql2o.open()){
            String sql="SELECT * FROM clients WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Client.class);
        }
    }
    public void update(int id,String name, String email, String phone_number, String project_name,String description_project ,String timeline_project,String price){
        try (Connection con= sql2o.open()){
            String sql="UPDATE clients SET name=:name,email=:email,phone_number=:phone_number,project_name=:project_name,description_project=:description_project,timeline_project=:timeline_project,price=:price,designer_id=:designer_id  WHERE id=:id";
            if(name.equals("")||phone_number.equals("")){
                throw new IllegalArgumentException("fill all the fields");
            }
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .addParameter("name",this.name)
                    .addParameter("email",this.phone_number)
                    .addParameter("phone_number",this.phone_number)
                    .addParameter("project_name",this.project_name)
                    .addParameter("description_project",this.description_project)
                    .addParameter("timeline_project",this.timeline_project)
                    .addParameter("price",this.price)
                    .addParameter("designer_id",this.designer_id)
                    .executeUpdate();
        }
    }
    public List<Client> getAllEmployeeByDepartment(int designer_id) {
        List<Client> departments=new ArrayList<>();
        try (Connection con=sql2o.open()) {
            String sql = "SELECT client_id FROM designers_clients WHERE designer_id=:designer_id";
            List<Integer> client_ids = con.createQuery(sql)
                    .addParameter("designer_id", designer_id)
                    .executeAndFetch(Integer.class);
            for (Integer id : client_ids) {
                String clientResults = "SELECT * FROM clients WHERE id=:id";
                departments.add(con.createQuery(clientResults)
                        .addParameter("id", id)
                        .executeAndFetchFirst(Client.class));
            }
            return departments;
        }
    }
    @Override
    public void delete() {
        try (Connection con= sql2o.open()){
            String sql="DELETE FROM clients WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }
    public static void deleteAll(){
        try (Connection con= sql2o.open()){
            String sql="DELETE FROM clients";
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
