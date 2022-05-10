import org.sql2o.Connection;

public class Endangered extends Animal implements DatabaseManagement {
    private static int id;

    public static final String species ="Endangered";


    public Endangered(String name, String health,String age,String type) {
        super(name,type);
        this.name = name;
        this.health = health;
        this.age = age;
        this.type=type;

//        if(this.name.equals("")){
//            throw new UnsupportedOperationException("Please input name of animal");
//        }
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
