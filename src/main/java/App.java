
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

/*        get("/designers",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"designers.hbs");
        },new HandlebarsTemplateEngine());*/

        get("/gallery",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"gallery.hbs");
        },new HandlebarsTemplateEngine());

        //department
        //interface
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

        //retrieving the department
        get("/designers",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("designers",sql2oDesignerDao.getAll());
            return new ModelAndView(model,"designers.hbs");
        },new HandlebarsTemplateEngine());

    }
}
