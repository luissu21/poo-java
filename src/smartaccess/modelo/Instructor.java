
package smartaccess.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author alema
 */
public class Instructor {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty telefono;
    private IntegerProperty tiempoep;
    private StringProperty especialidad;
    private Time horaE;
    private Time horaS;

    public Instructor(int id, String nombre, String telefono, int tiempoep, String especialidad, Time horaE, Time horaS) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.tiempoep = new SimpleIntegerProperty(tiempoep);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.horaE = horaE;
        this.horaS = horaS;
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
	//Metodos atributo: telefono
	public String getTelefono() {
		return telefono.get();
	}
	public void setTelefono(String telefono) {
		this.telefono = new SimpleStringProperty(telefono);
	}
	public StringProperty TelefonoProperty() {
		return telefono;
	}
	//Metodos atributo: tiempoep
	public int getTiempoep() {
		return tiempoep.get();
	}
	public void setTiempoep(int tiempoep) {
		this.tiempoep = new SimpleIntegerProperty(tiempoep);
	}
	public IntegerProperty TiempoepProperty() {
		return tiempoep;
	}
	//Metodos atributo: especialidad
	public String getEspecialidad() {
		return especialidad.get();
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = new SimpleStringProperty(especialidad);
	}
	public StringProperty EspecialidadProperty() {
		return especialidad;
	}

    public Time getHoraE() {
        return horaE;
    }
    
    public void setHoraE(Time horaE) {
        this.horaE = horaE;
    }

    public Time getHoraS() {
        return horaS;
    }

    public void setHoraS(Time horaS) {
        this.horaS = horaS;
    }
    
    public static java.sql.Time getCurrentJavaSqlTime () {
        java.util.Date date = new java.util.Date();
        return new java.sql.Time(date.getTime());
    }
    
    
    public static void llenartabla (Connection Conexion, ObservableList<Instructor> lista) {
        try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Instructores");
            
            while(res.next()){
                lista.add(new Instructor (res.getInt("id_Instr"),
                                        res.getString("Nom_Instr"),
                                        res.getString("Tel_Instr"),
                                        res.getInt("TiempEP"),
                                        res.getString("Esp_Instr"),
                                        res.getTime("horaE_instr"),
                                        res.getTime("horaS_instr")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Instructores (id_Instr, Nom_Instr, Tel_Instr,"
                    + "TiempoEP, Esp_Instr, horaE_instr, horaS_instr)"
                    +"values(?, ?, ?, ?, ?, ?, ?)");
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setString(3, telefono.get());
            instruccion.setInt(4, tiempoep.get());
            instruccion.setString(5, especialidad.get());
            instruccion.setTime(6, horaE);
            instruccion.setTime(7, horaS);
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int modificarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Instructores set Nom_Instr = ?, Tel_Instr = ?,"
                    + "TiempoEP = ?, Esp_Instr = ?, horaE_instr = ?, horaS_instr = ? where  id_Instr = ?");
 
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, telefono.get());
            instruccion.setInt(3, tiempoep.get());
            instruccion.setString(4, especialidad.get());
            instruccion.setTime(5, horaE);
            instruccion.setTime(6, horaS);
            instruccion.setInt(7, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Instructores where Nom_Instr = ?");
            instruccion.setString(1, nombre.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    @Override
     public String toString() {
         return nombre.get()+" ("+especialidad.get()+")" +" ("+String.valueOf(horaE)+")";
     }
    
    
}
