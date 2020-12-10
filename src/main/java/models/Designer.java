package models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Designer {
    private int id;
    private String name;
    private String email;
    private String phone_number;
    private String designing_fields;
    private String experience ;
    private String website;
    private Date date= new Date();
    private Timestamp createdat;

    public Designer( String name, String email, String phone_number, String designing_fields,String experience ,String website){
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.designing_fields =designing_fields;
        this.experience = experience;
        this.website = website;
        this.createdat = new Timestamp(date.getTime());
    }

    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getPhone_number() { return phone_number; }
    public String getDesigning_fields() { return designing_fields; }
    public String getExperience() { return experience; }


    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    public String getWebsite() { return website; }
    public int getId() { return id; }
    public Timestamp getCreatedat() { return createdat; }



    public void setDesigning_fields(String designing_fields) { this.designing_fields = designing_fields; }
    public void setExperience(String experience) { this.experience = experience; }
    public void setWebsite(String website) { this.website = website; }
    public void setId(int id) { this.id = id; }
    public void setCreatedat(Timestamp createdat) { this.createdat = createdat; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Designer designer = (Designer) o;
        return id == designer.id &&
                Objects.equals(name, designer.name) &&
                Objects.equals(email, designer.email) &&
                Objects.equals(phone_number, designer.phone_number) &&
                Objects.equals(designing_fields, designer.designing_fields) &&
                Objects.equals(experience, designer.experience) &&
                Objects.equals(website, designer.website) &&
                Objects.equals(date, designer.date) &&
                Objects.equals(createdat, designer.createdat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phone_number, designing_fields, experience, website, date, createdat);
    }
}
