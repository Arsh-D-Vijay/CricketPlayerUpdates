package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoint {

    private static Connection conn;
    private static ConnectionPoint connectionPoint;
    private  ConnectionPoint (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/CRICKETDATA";
            String user = "root";
            String password = "root@SQL";
            conn = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        if (connectionPoint == null){
            connectionPoint = new ConnectionPoint();
        }
        return conn;
    }

}
