import org.sql2o.Sql2o;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", null, null);

    static String connectionString = "jdbc:postgresql://ec2-54-164-40-66.compute-1.amazonaws.com:5432/dcoe5b888ocno2";
    public static Sql2o sql2o  = new Sql2o(connectionString, "ozxzztjrdpxsrq", "621c774fd25dc132f0ddb5ec9230485a16178d1dc10aa41b29a74230fda904ff");

}

