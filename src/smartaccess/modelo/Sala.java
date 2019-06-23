
package smartaccess.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author alema
 */
public class Sala{
	private IntegerProperty id;
	private StringProperty nombre;
	private StringProperty nequipo;
	private StringProperty hora;
	private StringProperty disponible;
	private StringProperty ubicacion;

	public Sala(int id, String nombre, String nequipo, String hora, String disponible, String ubicacion) { 
		this.id = new SimpleIntegerProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.nequipo = new SimpleStringProperty(nequipo);
		this.hora = new SimpleStringProperty(hora);
		this.disponible = new SimpleStringProperty(disponible);
		this.ubicacion = new SimpleStringProperty(ubicacion);
	}

	//Metodos atributo: id
	public int getId() {
		return id.get();
	}
	public void setId(int id) {
		this.id = new SimpleIntegerProperty(id);
	}
	public IntegerProperty IdProperty() {
		return id;
	}
	//Metodos atributo: nombre
	public String getNombre() {
		return nombre.get();
	}
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	public StringProperty NombreProperty() {
		return nombre;
	}
	//Metodos atributo: nequipo
	public String getNequipo() {
		return nequipo.get();
	}
	public void setNequipo(String nequipo) {
		this.nequipo = new SimpleStringProperty(nequipo);
	}
	public StringProperty NequipoProperty() {
		return nequipo;
	}
	//Metodos atributo: hora
	public String getHora() {
		return hora.get();
	}
	public void setHora(String hora) {
		this.hora = new SimpleStringProperty(hora);
	}
        public StringProperty HoraProperty() {
            return hora;
        }
        
	//Metodos atributo: disponible
	public String getDisponible() {
		return disponible.get();
	}
	public void setDisponible(String disponible) {
		this.disponible = new SimpleStringProperty(disponible);
	}
	public StringProperty DisponibleProperty() {
		return disponible;
	}
	//Metodos atributo: ubicacion
	public String getUbicacion() {
		return ubicacion.get();
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = new SimpleStringProperty(ubicacion);
	}
	public StringProperty UbicacionProperty() {
		return ubicacion;
	}
        
        public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Salas (id_Sala, Nom_Sala, NumEq_Sala,"
                    + "Hora_Sala, Disp_Sala, Ubi_Sala)"
                    +"values(?, ?, ?, ?, ?, ?)");
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setString(3, nequipo.get());
            instruccion.setString(4, hora.get());
            instruccion.setString(5, disponible.get());
            instruccion.setString(6, ubicacion.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int modificarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Actividades set Nom_Sala = ?, NumEq_Sala = ?,"
                    + "Hora_Sala = ?, Disp_Sala = ?, Ubi_Sala = ? where  id_Sala = ?");
 
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, nequipo.get());
            instruccion.setString(3, hora.get());
            instruccion.setString(4, disponible.get());
            instruccion.setString(5, ubicacion.get());
            instruccion.setInt(6, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Salas where Nom_Sala = ?");
            instruccion.setString(1, nombre.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static void llenarCombo (Connection Conexion, ObservableList<Sala> lista) {
         try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Salas");
            
            while(res.next()){
                lista.add(new Sala (res.getInt("id_Sala"),
                                        res.getString("Nom_Sala"),
                                        res.getString("NumEq_Sala"),
                                        res.getString("Hora_Sala"),
                                        res.getString("Disp_Sala"),
                                        res.getString("Ubi_Sala")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
     public String toString() {
         return nombre.get() + " ("+ubicacion.get()+")";
     }
}
