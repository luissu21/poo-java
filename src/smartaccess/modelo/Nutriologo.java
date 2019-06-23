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

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;


/**
 *
 * @author Elyziita
 */
public class Nutriologo {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty telefono;
    private StringProperty correo;
    private StringProperty rfc;
    private StringProperty especialidad;
    private Time horaE;
    private Time horaS;
    
    public Nutriologo(int id, String nombre, String telefono, String correo, String rfc,
            String especialidad, Time horaE, Time horaS) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.correo = new SimpleStringProperty(correo);
        this.rfc = new SimpleStringProperty(rfc);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.horaE = horaE;
        this.horaS = horaS;
    }

    Nutriologo() {
         
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
	//Metodos atributo: correo
	public String getCorreo() {
		return correo.get();
	}
	public void setCorreo(String correo) {
		this.correo = new SimpleStringProperty(correo);
	}
	public StringProperty CorreoProperty() {
		return correo;
	}
	//Metodos atributo: rfc
	public String getRfc() {
		return rfc.get();
	}
	public void setRfc(String rfc) {
		this.rfc = new SimpleStringProperty(rfc);
	}
	public StringProperty RfcProperty() {
		return rfc;
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
    
    
    
    public static void llenartabla (Connection Conexion, ObservableList<Nutriologo> lista) {
        try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Nutriologos");
            
            while(res.next()){
                lista.add(new Nutriologo (res.getInt("id_Nutri"),
                                        res.getString("Nom_Nutri"),
                                        res.getString("Tel_Nutri"),
                                        res.getString("Correo_Nutri"),
                                        res.getString("RFC_Nutri"),
                                        res.getString("Esp_Nutri"),
                                        res.getTime("horaE_Nutri"),
                                        res.getTime("horaS_Nutri")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Nutriologos (id_Nutri, Nom_Nutri, Tel_Nutri,"
                    + "Correo_Nutri, RFC_Nutri, Esp_Nutri, horaE_Nutri, horaS_Nutri) values(?, ?, ?, ?, ?, ?, ?, ?)");
                    
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setString(3, telefono.get());
            instruccion.setString(4, correo.get());
            instruccion.setString(5, rfc.get());
            instruccion.setString(6, especialidad.get());
            instruccion.setTime(7, horaE);
            instruccion.setTime(8, horaS);
            
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Nutriologos where id_Nutri = ?");
            instruccion.setInt(1, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    


public int modificarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Nutriologos set Nom_Nutri= ?, Tel_Nutri= ?,"
                    + "Correo_Nutri= ?, RFC_Nutri = ?, Esp_Nutri = ?, horaE_Nutri = ?, horaS_Nutri = ? where id_Nutri = ?");
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, telefono.get());
            instruccion.setString(3, correo.get());
            instruccion.setString(4, rfc.get());
            instruccion.setString(5, especialidad.get());
            instruccion.setTime(6, horaE);
            instruccion.setTime(7, horaS);
            instruccion.setInt(8, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
     public String toString() {
         return nombre.get()+" ("+especialidad.get()+")" +" ("+String.valueOf(horaE)+")";
     }

}