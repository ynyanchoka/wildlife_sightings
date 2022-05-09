import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Sightings {
    private int id;
    private int animalId;
    private String location;
    private String rangerName;
    private Timestamp time;
    private Date date= new Date();


    public Sightings(int animalId, String location, String rangerName) {
        this.rangerName=rangerName;
        this.location = location;
        this.animalId=animalId;
        this.time = new Timestamp(date.getTime());
        this.save();
    }




    public String getRangerName() {
        return rangerName;
    }

    public String getLocation() {
        return location;
    }

    public int getAnimalId() {
        return animalId;
    }

    public Timestamp getTime() {
        return time;
    }
    public int getId() {
        return id;
    }


    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }
    //Listing sightings by animal id
    public static List<Sightings> allByAnimal(int animalId) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animalId = :animalId ";
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .executeAndFetch(Sightings.class);
        }
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId,location,rangerName,time) VALUES (:animalId,:location,:rangerName,:now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("location", this.location)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("time", this.time)
                    .executeUpdate()
                    .getKey();
        }
    }



    public static Sightings find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE id=:id;";
            Sightings sightings = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return sightings;
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM sightings WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }

    }


}
