package jframe;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBC {
    
    static Connection con = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root","");
        } catch (Exception e){
            e.printStackTrace();
        }       
        return con;
    }
}
