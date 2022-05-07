import org.sql2o.Connection;

import java.util.List;

public class Endangered {

    private String name;
    private String age;
    private String health;
    private int id;

//HEALTH CONSTANTS
    public static final String HEALTHY="healthy";
    public static final String ILL="ill";
    public static final String OKAY="okay";

//AGE CONSTANTS
    public static final String NEWBORN="newborn";
    public static final String YOUNG="young";
    public static final String ADULT="adult";


    public Endangered(String name, String health,String age) {
        this.name = name;
        this.health = health;
        this.age = age;
    }

    public String getName() {
        return name;
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



    public static List<Endangered> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Endangered.class);
        }
    }



    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql ="INSERT INTO animals (name,health,age) VALUES (:name,:health,:age)";
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }
    }


}
