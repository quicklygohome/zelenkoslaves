package edu.ssau.gasstation.DB;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Properties;

/**
 * Created by andrey on 06.12.16.
 */
public class DBHelper {
    private static Connection connect;
    private static Statement st;

    public DBHelper() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "2");
            properties.setProperty("useUnicode","true");
            properties.setProperty("characterEncoding","UTF-8");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/gasstation", properties);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        return st.executeQuery(query);
    }

    public boolean insertFuel(String fuel_name, double fuel_cost) throws SQLException {
        String query = "INSERT INTO fuel(fuel_name, fuel_cost) VALUES(?, ?);";
        PreparedStatement ps = connect.prepareStatement(query);
        ps.setString(1, new String(fuel_name.getBytes(), Charset.forName("utf8")));
        ps.setDouble(2, fuel_cost);
        return ps.execute();
    }
}
