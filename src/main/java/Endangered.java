import org.sql2o.Connection;

import java.util.List;

public class Endangered extends Animal implements DatabaseManagement  {


    private final String type;
    private String name;
    private String age;
    private String health;
    private static int id;

//HEALTH CONSTANTS
    public static final String WELLNESS="healthy";
    public static final String SICK="ill";
    public static final String FINE="okay";

//AGE CONSTANTS
    public static final String INFANT="newborn";
    public static final String YOUNG="young";
    public static final String ADULT="adult";
    public static final String ANIMAL_TYPE="Endangered";


    public Endangered(String name, String health,String age,String type) {
        super(name,type);
        this.name = name;
        this.health = health;
        this.age = age;
        this.type=type;

        if(this.name.equals("")){
            throw new UnsupportedOperationException("Please input name of animal");
        }
    }

    public int getId() {
        return id;
    }
    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }


    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql ="INSERT INTO animals (name,health,age,type) VALUES (:name,:health,:age,:type)";
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
    }


}
