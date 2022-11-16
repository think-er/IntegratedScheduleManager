package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import oracle.jdbc.OracleTypes;

public class DB_Conn_Query {
   Connection con = null;
   String url = "jdbc:oracle:thin:@localhost:1521:XE";
   String id = "hmart";      String password = "1234";
   public DB_Conn_Query() {
     try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("드라이버 적재 성공");
     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
   }
   private void DB_Connect() {
     try {
         con = DriverManager.getConnection(url, id, password);
         System.out.println("DB 연결 성공");
     } catch (SQLException e) {         System.out.println("Connection Fail");      }
   }
   private void sqlRun() throws SQLException{   // 단순 검색
    String query = "select 고객아이디, 고객이름, 적립금 from 고객";

    try { DB_Connect();
    	  Statement stmt = con.createStatement();
          ResultSet rs = stmt.executeQuery(query);
          while (rs.next()) {
              System.out.print("\t" + rs.getString("고객아이디"));
              System.out.print("\t" + rs.getString("고객이름"));
              System.out.print("\t" + rs.getInt(3) + "\n");         
           }
          stmt.close();    rs.close();
    } catch (SQLException e) { e.printStackTrace(); 
    } finally {   con.close(); }
    
   }
}
