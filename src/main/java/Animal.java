import org.sql2o.Connection;

import java.util.List;

public class Animal implements DatabaseManagement {

    public String name;
    public int id;
    private String health;
    private String age;
    public String type;
    public static final String ANIMAL_TYPE="Not endangered";


    public Animal(String name,String type) {

//        this.id = id;
        this.name = name;
        this.age =age;
        this.health = health;
        this.type=ANIMAL_TYPE;
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
        if(this.name.equals("")){
            throw new IllegalArgumentException("Please input name");
        }
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
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeUpdate();
            String sql2 = "DELETE from sightings WHERE animal_id = :id";
            con.createQuery(sql2)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }




}
