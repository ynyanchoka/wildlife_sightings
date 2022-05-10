import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {

    static int getHerokuAssignedPort() {

        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        staticFileLocation("/public");

        //home

        get("/",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());



        //form impervious animals
        get( "/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());


        // get form endangered animals
        get( "/endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "endangered-form.hbs");
        }, new HandlebarsTemplateEngine());

        //posting  animals form details

        post("/animals",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            String type=request.queryParams("type");
            System.out.println(type);
            String health=request.queryParams("health");
            System.out.println(health);
            String age=request.queryParams("age");
            System.out.println(age);
            String name=request.queryParams("name");
            System.out.println(name);
            if(type.equals(Endangered.species)){
                Endangered endangered=new Endangered(name,health,age,Endangered.species);
                endangered.save();
            }
            else  {
                Animal animal=new Animal(name,Animal.species);
                animal.save();
            }
            return new ModelAndView(model,"animal-success.hbs");
        },new HandlebarsTemplateEngine());




        get("/animals/new/endangered",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            List<String> health= new ArrayList<String>();
            health.add(Endangered.WELLNESS);
            health.add(Endangered.SICK);
            health.add(Endangered.FINE);
            List<String> age= new ArrayList<String>();
            age.add(Endangered.ADULT);
            age.add(Endangered.INFANT);
            age.add(Endangered.YOUNG);
            model.put("health",health);
            model.put("age",age);
            String typeChosen="impervious";
            model.put("endangered",typeChosen);
            return new ModelAndView(model,"animal-form.hbs");
        },new HandlebarsTemplateEngine());

       

//        view animals list

        get("/animals",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("animals",Animal.all());
            return new ModelAndView(model,"animal-detail.hbs");
        },new HandlebarsTemplateEngine());


//        /get sightings form
        get("/sightings/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("animals",Animal.all());
            return new ModelAndView(model,"sightings-form.hbs");
        },new HandlebarsTemplateEngine());

//        post a sighting form
        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animalId = Integer.parseInt((request.queryParams("animal")));
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            System.out.println(Sightings.all());

            try {
                Sightings sightings = new Sightings(animalId, location, rangerName);
                sightings.save();
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter Ranger name.");
            }
            return new ModelAndView(model, "/sightings-success.hbs");
        },new HandlebarsTemplateEngine());



        get("/sightings",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("sightings",Sightings.all());
            System.out.println(Sightings.all());
            return new ModelAndView(model,"sightings-detail.hbs");
        },new HandlebarsTemplateEngine());








    }
}