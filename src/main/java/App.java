import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class App {
    public static void main(String[] args) {

        //home

        get("/",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //delete animals
        get("/animals/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal.find(Integer.parseInt(request.params(":id"))).delete();
            return new ModelAndView(model, "animal-detail.hbs");
        } ,new HandlebarsTemplateEngine());

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
            if(type.equals(Endangered.ANIMAL_TYPE)){
                Endangered endangered=new Endangered(name,Endangered.ANIMAL_TYPE,health,age);
                endangered.save();
            }
            else {
                Animal animal=new Animal(name,Animal.ANIMAL_TYPE);
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


//        get("/posts/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            Post.clearAllPosts();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());

//        /get sightings form
        get("/sightings/new",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            model.put("animals",Animal.all());
            return new ModelAndView(model,"sightings-form.hbs");
        },new HandlebarsTemplateEngine());

        //posting a sighting form
        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animal_id = Integer.parseInt(request.queryParams("animal"));
            String location = request.queryParams("location");
            String ranger_name = request.queryParams("rangerName");


            try {
                Sightings sightings = new Sightings(animal_id, location, ranger_name);
            } catch (IllegalArgumentException exception) {
                System.out.println("Please enter Ranger name.");
            }
            return new ModelAndView(model, "/sightings-success.hbs");
        },new HandlebarsTemplateEngine());

//    

    }
}