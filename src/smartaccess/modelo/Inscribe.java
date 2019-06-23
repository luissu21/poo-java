
package smartaccess.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author alema
 */
public class Inscribe {
    
	private IntegerProperty id_Usu;
	private Actividad id_Acti;

	public Inscribe(int id_Usu, Actividad id_Acti) { 
		this.id_Usu = new SimpleIntegerProperty(id_Usu);
		this.id_Acti = id_Acti;
	}

	//Metodos atributo: id_Usu
	public int getId_Usu() {
		return id_Usu.get();
	}
	public void setId_Usu(int id_Usu) {
		this.id_Usu = new SimpleIntegerProperty(id_Usu);
	}
	public IntegerProperty Id_UsuProperty() {
		return id_Usu;
	}
	//Metodos atributo: id_Acti
	public Actividad getId_Acti() {
		return id_Acti;
	}
	public void setId_Acti(Actividad id_Acti) {
		this.id_Acti = id_Acti;
	}
        
        public int guardarActividad (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Inscribe (id_Usu, id_Acti)"
                    +"values(?, ?)");
            instruccion.setInt(1, id_Usu.get());
            instruccion.setInt(2, id_Acti.getId());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
