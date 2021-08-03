/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class NewClass {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://192.168.77.24:1433;database=gestion_paquetes", "jhernandez", "Junior2000");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM lugar");
            while (rs.next()) {
                System.out.println(rs.getString("nombre_lugar"));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String connectionURL = "jbdc:sqlserver://192.168.77.24\\SQLEXPRESS:1433;datebaseName=gestion_paquetes;user=jhernandez;password=junior2000;";

    }

}
