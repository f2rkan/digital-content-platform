package baglanti;
import java.sql.*;
public class Baglanti {
	
	Connection connection = null;
	public static Connection c0nnection() {
    	try {
    		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/platform", "root", "mysql");
    		return connection;
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
		}
    }
}
