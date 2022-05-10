import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal implements DatabaseManagement {

    public String name;
    public int id;
    public  String health;
    public  String age;
    public String type;
    public static String species="Not endangered";

    //HEALTH CONSTANTS
    public static final String WELLNESS="healthy";
    public static final String SICK="ill";
    public static final String FINE="okay";

    //AGE CONSTANTS
    public static final String INFANT="newborn";
    public static final String YOUNG="young";
    public static final String ADULT="adult";



    public Animal(String name,String type) {

        this.id = id;
        this.name = name;
        this.age =age;
        this.health = health;
        this.type= type;

        if(name.equals("")){
            throw new IllegalArgumentException("Please input name of animal");
        }
    }


    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getAge() {
        return age;
    }
    public String getHealth() {
        return health;
    }
    public String getType() {
        return type;
    }


    public static List<Animal> all() {
        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public void save() {

        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Animal find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animal animal = con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Animal.class);
            return animal;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getId() == animal.getId() && Objects.equals(getName(), animal.getName()) && Objects.equals(getHealth(), animal.getHealth()) && Objects.equals(getAge(), animal.getAge()) && Objects.equals(getType(), animal.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getHealth(), getAge(), getType());
    }
}
