package Helper;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
 

public class DbHelper {
	
	private static Connection c=null;
	protected DbHelper() {}
	public static Connection connDb() {
		if(c==null) {
			try {
				c=DriverManager.getConnection("jdbc:mysql://localhost:3306/gts?user=root&password=******");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return c;
	}
}
	
