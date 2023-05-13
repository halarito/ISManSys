package Modulo;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {

    public static Connection conector() {
        
        Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/ismansys";

        String user = "root";
        String pass = "";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, pass);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

            return null;
        }

    }
}
