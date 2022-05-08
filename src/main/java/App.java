import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {

        get("/",(request, response) -> {
            Map<String,Object> model=new HashMap<String, Object>();
            return new ModelAndView(model,"index.hbs");
        },new HandlebarsTemplateEngine());

        //form animals
        get( "/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
//            model.put("animals", animals);
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());

    }
}