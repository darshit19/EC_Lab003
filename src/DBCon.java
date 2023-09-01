

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBCon implements DBConfig{
    public static Connection getMeConnection(){
        Connection con=null;
        try{
             con= DriverManager.getConnection(
                    DBConfig.DB_URL,
                    DBConfig.DB_USER,
                    DBConfig.DB_PASS
            );
        }catch (SQLException e){
            System.out.println(e+" from Connection side");
        }
        return con;
    }
}
