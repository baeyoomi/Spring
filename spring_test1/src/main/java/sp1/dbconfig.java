package sp1;

import java.sql.Connection;
import java.sql.DriverManager;

//Database 환경설정
public class dbconfig {
	public static Connection info() throws Exception {   //실무에선 getConnection 과 같은 이름을 사용한다
		String db_driver = "com.mysql.jdbc.Driver";
		String db_url = "jdbc:mysql://localhost:3306/web";
		String db_user = "hong";
		String db_pass = "a123456";
		Class.forName(db_driver);
		Connection con = DriverManager.getConnection(db_url,db_user,db_pass);
		return con;
	}
}
