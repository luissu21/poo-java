
package smartaccess.modelo;

import java.sql.Connection;
import java.sql.Date;
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
import static smartaccess.modelo.Instructor.getCurrentJavaSqlTime;

/**
 *
 * @author alema
 */
public class Actividad{
	private IntegerProperty id;
	private StringProperty nombre;
	private IntegerProperty caper;
	private Time hora;
	private StringProperty disp;
	private StringProperty ubicacion;
	private IntegerProperty instructor;

	public Actividad(int id, String nombre, int caper, Time hora, String disp, String ubicacion, 
                int instructor) { 
		this.id = new SimpleIntegerProperty(id);
		this.nombre = new SimpleStringProperty(nombre);
		this.caper = new SimpleIntegerProperty(caper);
		this.hora = hora;
		this.disp = new SimpleStringProperty(disp);
		this.ubicacion = new SimpleStringProperty(ubicacion);
		this.instructor = new SimpleIntegerProperty(instructor);
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
	//Metodos atributo: caper
	public int getCaper() {
		return caper.get();
	}
	public void setCaper(int caper) {
		this.caper = new SimpleIntegerProperty(caper);
	}
	public IntegerProperty CaperProperty() {
		return caper;
	}
	//Metodos atributo: hora
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	//Metodos atributo: disp
	public String getDisp() {
		return disp.get();
	}
	public void setDisp(String disp) {
		this.disp = new SimpleStringProperty(disp);
	}
	public StringProperty DispProperty() {
		return disp;
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
	//Metodos atributo: instructor
	public int getInstructor() {
		return instructor.get();
	}
	public void setInstructor(int instructor) {
		this.instructor = new SimpleIntegerProperty(instructor);
	}
	public IntegerProperty InstructorProperty() {
		return instructor;
	}
        
        public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Actividades (id_Acti, Nom_Acti, CapPer_Acti,"
                    + "Hora_Acti, Disp_Acti, Ubi_Acti, ints_Acti)"
                    +"values(?, ?, ?, ?, ?, ?, ?)");
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setInt(3, caper.get());
            instruccion.setTime(4, hora);
            instruccion.setString(5, disp.get());
            instruccion.setString(6, ubicacion.get());
            instruccion.setInt(7, instructor.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int modificarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Actividades set Nom_Acti = ?, CapPer_Acti = ?,"
                    + "Hora_Acti = ?, Disp_Acti = ?, Ubi_Acti = ?, ints_Acti = ? where  id_Acti = ?");
 
            instruccion.setString(1, nombre.get());
            instruccion.setInt(2, caper.get());
            instruccion.setTime(3, hora);
            instruccion.setString(4, disp.get());
            instruccion.setString(5, ubicacion.get());
            instruccion.setInt(6, instructor.get());
            instruccion.setInt(7, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Actividades where Nom_Acti = ?");
            instruccion.setString(1, nombre.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public static void llenarCombo (Connection Conexion, ObservableList<Actividad> lista) {
         try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Actividades");
            
            while(res.next()){
                lista.add(new Actividad (res.getInt("id_Acti"),
                                        res.getString("Nom_Acti"),
                                        res.getInt("CapPer_Acti"),
                                        res.getTime("Hora_Acti"),
                                        res.getString("Disp_Acti"),
                                        res.getString("Ubi_Acti"),
                                        res.getInt("ints_acti")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
     public String toString() {
         return nombre.get()+" ("+hora+")";
     }
        
}
