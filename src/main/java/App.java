
import dao.Sql2oDesignerDao;
import dao.Sql2oClientDao;
import dao.Sql2oReviewDao;


import org.sql2o.Connection;
import org.sql2o.Sql2o;
import static spark.Spark.*;

import models.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {


    public static void main(String[] args) {

        Sql2oDesignerDao sql2oDesignerDao;
        Sql2oClientDao sql2oClientDao;
        Sql2oReviewDao sql2oReviewDao;
        Connection conn;


        String connectionString = "jdbc:postgresql://localhost:5432/wagerdesign";
        Sql2o sql2o = new Sql2o(connectionString, "benitha", "123");

        sql2oDesignerDao = new Sql2oDesignerDao(sql2o);
        sql2oClientDao = new Sql2oClientDao(sql2o);
        sql2oReviewDao = new  Sql2oReviewDao(sql2o);

        conn = sql2o.open();


        staticFileLocation("/public");
        get("/",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        get("/about",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"about.hbs");
        },new HandlebarsTemplateEngine());

        get("/contact",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"contact.hbs");
        },new HandlebarsTemplateEngine());

        get("/gallery",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"gallery.hbs");
        },new HandlebarsTemplateEngine());

        //department
        get("/create/designer",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"designerForm.hbs");
        },new HandlebarsTemplateEngine());

        post("/create/designer/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name=request.queryParams("name");
            String email=request.queryParams("email");
            String phone_number=request.queryParams("phone_number");
            String designing_fields=request.queryParams("designing_fields");
            String experience =request.queryParams("experience");
            String website=request.queryParams("website");
            Designer designer=new Designer(name,email,phone_number,designing_fields,experience,website);
            sql2oDesignerDao.add( designer);
            request.session().attribute("item", name);
            model.put("item", request.session().attribute("item"));
            return new ModelAndView(model,"designerForm.hbs");
        },new HandlebarsTemplateEngine());

        get("/designers",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("designers",sql2oDesignerDao.getAll());
            return new ModelAndView(model,"designers.hbs");
        },new HandlebarsTemplateEngine());

        //Client

        get("/create/designer/clients/:id",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"clientForm.hbs");
        },new HandlebarsTemplateEngine());
        post("/create/designer/client/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String name=request.queryParams("name");
            String email=request.queryParams("email");
            String phone_number=request.queryParams("phone_number");
            String project_name=request.queryParams("project_name");
            String description_project =request.queryParams("description_project");
            String timeline_project=request.queryParams("timeline_project");
            String price=request.queryParams("price");
            //int designer_id = Integer.parseInt(request.params("designer"));
            // model.put("designers",sql2oDesignerDao.getAll());
            // String designer_id=request.queryParams("designer_id");
            // Client client=new Client(name,email,phone_number,project_name,description_project,timeline_project,price,designer_id);
            Client client=new Client(name,email,phone_number,project_name,description_project,timeline_project,price);
            sql2oClientDao.add(client);
            request.session().attribute("item", name);
            model.put("item", request.session().attribute("item"));
            return new ModelAndView(model,"clientsuccess.hbs");
            //return new ModelAndView(model,"departmentsuccess.hbs");
        },new HandlebarsTemplateEngine());
        //retrieving the department
        get("/view/clients",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("clients",sql2oClientDao.getAll());
            return new ModelAndView(model,"clients.hbs");
        },new HandlebarsTemplateEngine());

        //Review
        get("/create/designer/:id/review", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Designer> designers = sql2oDesignerDao.getAll(); //refresh list of links for navbar
            model.put("designers", designers);
            return new ModelAndView(model, "reviewForm.hbs"); //new layout
        }, new HandlebarsTemplateEngine());
        post("/review/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String user_name=request.queryParams("user_name");
            String content=request.queryParams("content");
            int designer_id= Integer.parseInt(request.queryParams("designer"));
            Review review=new Review(user_name,content,designer_id);
            sql2oReviewDao.add(review);
            request.session().attribute("item", user_name);
            model.put("item", request.session().attribute("item"));
            return new ModelAndView(model,"reviewsuccess.hbs");
            //return new ModelAndView(model,"departmentsuccess.hbs");
        },new HandlebarsTemplateEngine());
        get("/view/reviews/:id",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("reviews",sql2oReviewDao.getAll());
            return new ModelAndView(model,"reviews.hbs");
        },new HandlebarsTemplateEngine());

    }
}
