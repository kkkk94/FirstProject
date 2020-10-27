package Pharmacy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {
	public static Connection DBConnect() { //Connection :참조형 데이터타입 → 리턴 con 
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "JISOO";
			String password = "1111";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB접속 성공!");
		} catch(ClassNotFoundException cne) { //try 확인
			cne.printStackTrace();
			//printStackTrace() : 에러 발생 시 경로를 찾아준다.
			System.out.println("DB접속 실패 : 드라이버 로딩 실패");
		} catch(SQLException se) { //접속 확인
			se.printStackTrace();
			System.out.println("DB접속 실패 : url, user, password 확인");
		}
		return con;
	}
}
