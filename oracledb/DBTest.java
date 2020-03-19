package oracledb;
import java.sql.*;
import oracle.jdbc.pool.*;

public class DBTest {
    public static void main(String[] args) {
        String jdbcConnectionURL = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "javauser";
        String userPASS = "javapass";
        
        Statement stmt;
        ResultSet rset;
        String sqlQuery;
        
        try
        {
            Connection conn = getDBConnection(jdbcConnectionURL,userID,userPASS);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY);
            sqlQuery = "SELECT * FROM TESTTABLE";
            //sqlQuery = "INSERT INTO TESTTABLE (ID,TESTNAME) VALUES (78,'HELLO THERE')";
            rset = stmt.executeQuery(sqlQuery);
            
            while (rset.next())
            {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.toString());
        }
    }
    public static Connection getDBConnection(String url, 
            String user, 
            String pass) throws SQLException
    {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(url);
        return ds.getConnection(user, pass);
    }
}
