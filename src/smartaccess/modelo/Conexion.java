package smartaccess.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {

    Connection conect = null;

    public Connection getConexion(String usuario, String password)  {
        
    try { 
         //jdbc:sqlserver://LUISSU-LAP:1433;databaseName=smartaccess
        conect = DriverManager.getConnection("jdbc:sqlserver://LUISSU-LAP:1433;databaseName=smartaccess",usuario,password);
        System.out.println("CONECTADO!"); 
    }  catch (SQLException ex)  { 
        System.out.println("ERROR!!" +ex); 
    }

    return conect;
             
    }
    
}
