package smartaccess.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author Elyziita
 */
public class Usuario {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty telefono;
    private Date fechaNa;
    private StringProperty tp;
    private FloatProperty peso;
    private FloatProperty altura;
    private StringProperty enfermedades;
    private StringProperty alergias;
    private StringProperty foto;
    private StringProperty huella;
    private Instructor instructor;
    private Nutriologo nutriologo;
    
    

    public Usuario(int id, String nombre, String telefono, Date fecha, String tp, float peso, float altura, String enfermedad, 
            String alergia, String foto, String huella, Instructor instructor, Nutriologo nutriologo) {
         
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.telefono = new SimpleStringProperty(telefono);
        this.fechaNa = fecha;
        this.tp = new SimpleStringProperty(tp);
        this.peso = new SimpleFloatProperty(peso);
        this.altura= new SimpleFloatProperty(altura);
        this.enfermedades = new SimpleStringProperty(enfermedad);
        this.alergias = new SimpleStringProperty(alergia);
        this.foto = new SimpleStringProperty(foto);
        this.huella = new SimpleStringProperty(huella);
        this.instructor = instructor;
        this.nutriologo= nutriologo;
    }
    
    public Usuario() {
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
	//Metodos atributo: fechaNa
	public Date getFechaNa() {
		return fechaNa;
	}
	public void setFechaNa(Date fechaNa) {
		this.fechaNa = fechaNa;
	}
	//Metodos atributo: tp
	public String getTp() {
		return tp.get();
	}
	public void setTp(String tp) {
		this.tp = new SimpleStringProperty(tp);
	}
	public StringProperty TpProperty() {
		return tp;
	}
	//Metodos atributo: peso
	public float getPeso() {
		return peso.get();
	}
	public void setPeso(float peso) {
		this.peso = new SimpleFloatProperty(peso);
	}
	public FloatProperty PesoProperty() {
		return peso;
	}
	//Metodos atributo: altura
	public float getAltura() {
		return altura.get();
	}
	public void setAltura(float altura) {
		this.altura = new SimpleFloatProperty(altura);
	}
	public FloatProperty AlturaProperty() {
		return altura;
	}
	//Metodos atributo: enfermedades
	public String getEnfermedades() {
		return enfermedades.get();
	}
	public void setEnfermedades(String enfermedades) {
		this.enfermedades = new SimpleStringProperty(enfermedades);
	}
	public StringProperty EnfermedadesProperty() {
		return enfermedades;
	}
	//Metodos atributo: alergias
	public String getAlergias() {
		return alergias.get();
	}
	public void setAlergias(String alergias) {
		this.alergias = new SimpleStringProperty(alergias);
	}
	public StringProperty AlergiasProperty() {
		return alergias;
	}
	//Metodos atributo: foto
	public String getFoto() {
		return foto.get();
	}
	public void setFoto(String foto) {
		this.foto = new SimpleStringProperty(foto);
	}
        public StringProperty FotoProperty() {
		return foto;
	}
        
	
	//Metodos atributo: huella
	public String getHuella() {
		return huella.get();
	}
	public void setHuella(String huella) {
		this.huella = new SimpleStringProperty(huella);
	}
        public StringProperty HuellaProperty() {
		return huella;
	}
	
	//Metodos atributo: instructor
	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	//Metodos atributo: nutriologo
	public Nutriologo getNutriologo() {
		return nutriologo;
	}
	public void setNutriologo(Nutriologo nutriologo) {
		this.nutriologo = nutriologo;
        }
    
    
    
    public void llenartabla (Connection Conexion, ObservableList<Usuario> lista) {
        try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Usuarios");
            
            while(res.next()){
                lista.add(new Usuario (res.getInt("id_Usu"),
                                        res.getString("Nom_Usu"),
                                        res.getString("Tel_Usu"),
                                        res.getDate("FeNa_Usu"),
                                        res.getString("TP_Usu"),
                                        res.getFloat("Peso_Usu"),
                                        res.getFloat("Alt_Usu"),
                                        res.getString("Enf_Usu"),
                                        res.getString("Alerg_Usu"),
                                        res.getString("Foto_Usu"),
                                        res.getString("HuellaDac_Usu"),
                                    new Instructor (res.getInt("id_Instr"),
                                        res.getString("Nom_Instr"),
                                        res.getString("Tel_Instr"),
                                        res.getInt("TiempEP"),
                                        res.getString("Esp_Instr"),
                                        res.getTime("horaE_instr"),
                                        res.getTime("horaS_instr")),
                                    new Nutriologo (res.getInt("id_Nutri"),
                                        res.getString("Nom_Nutri"),
                                        res.getString("Tel_Nutri"),
                                        res.getString("Correo_Nutri"),
                                        res.getString("RFC_Nutri"),
                                        res.getString("Esp_Nutri"),
                                        res.getTime("horaE_Nutri"),
                                        res.getTime("horaS_Nutri"))
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Nutriologo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int guardarregistro (Connection Conexion) {
        
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Usuarios (id_Usu, Nom_Usu, Tel_Usu,"
                    + "FeNa_Usu, TP_Usu, Peso_Usu, Alt_Usu, Enf_Usu, Alerg_Usu, Foto_Usu, HuellaDac_Usu, instructor, nutriologo)"
                    +"values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)");
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setString(3, telefono.get());
            instruccion.setDate(4, fechaNa);
            instruccion.setString(5, tp.get());
            instruccion.setFloat(6, peso.get());
            instruccion.setFloat(7, altura.get());
            instruccion.setString(8, enfermedades.get());
            instruccion.setString(9, alergias.get());
            instruccion.setString(10, foto.get());
            instruccion.setString(11, huella.get());
            instruccion.setInt(12, instructor.getId());
            instruccion.setInt(13, nutriologo.getId());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int modificarregistro (Connection Conexion) {
        
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Usuarios set Nom_Usu = ?, Tel_Usu = ?,"
                    + "FeNa_Usu = ?, TP_Usu = ?, Peso_Usu = ?, Alt_Usu = ?, Enf_Usu = ?, Alerg_Usu = ?, Foto_Usu = ?, HuellaDac_Usu = ?,"
                    + "instructor = ?, nutriologo = ? where  id_Usu = ?");
 
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, telefono.get());
            instruccion.setDate(3, fechaNa);
            instruccion.setString(4, tp.get());
            instruccion.setFloat(5, peso.get());
            instruccion.setFloat(6, altura.get());
            instruccion.setString(7, enfermedades.get());
            instruccion.setString(8, alergias.get());
            instruccion.setString(9, foto.get());
            instruccion.setString(10, huella.get());
            instruccion.setInt(11, instructor.getId());
            instruccion.setInt(12, nutriologo.getId());
            instruccion.setInt(13, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Usuarios where id_Usu = ?");
            instruccion.setInt(1, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    
}
