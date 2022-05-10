import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Sightings {
    private int id;
    private int animalId;
    private String location;
    private String rangerName;

    private Date date= new Date();
    private LocalDateTime time;


    public Sightings(int animalId, String location, String rangerName) {


        this.rangerName=rangerName;
        this.location = location;
        this.animalId=animalId;
        this.time = LocalDateTime.now();

        if (rangerName.equals("")) {
            throw new IllegalArgumentException("Please enter Ranger name.");
        }
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

    public LocalDateTime getTime() {
        return time;
    }
    public int getId() {
        return id;
    }


    public static List<Sightings> all() {

        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings";
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }


    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (animalId,location,rangerName,time) VALUES (:animalId,:location,:rangerName,:time)";
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
