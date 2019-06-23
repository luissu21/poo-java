
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
public class Accesa {
    
    private IntegerProperty id_Usu;
	private Sala id_Sala;

	public Accesa (int id_Usu, Sala id_Acti) { 
		this.id_Usu = new SimpleIntegerProperty(id_Usu);
		this.id_Sala = id_Acti;
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
	public Sala getId_Sala() {
		return id_Sala;
	}
	public void setId_Sala(Sala id_Sala) {
		this.id_Sala = id_Sala;
	}
        
        public int guardarSala (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Accesa (id_Usu, id_Sala)"
                    +"values(?, ?)");
            instruccion.setInt(1, id_Usu.get());
            instruccion.setInt(2, id_Sala.getId());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
