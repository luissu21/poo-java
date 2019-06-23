package smartaccess.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class Pago {
    
    private IntegerProperty id;
    private IntegerProperty idUsu;
    private Date fecha;
    private IntegerProperty numero;
    private DoubleProperty monto;
    private DoubleProperty pago;
    
    public Pago(int id, int idUsu, Date fecha, int numero, Double monto, Double pago) { 
		this.id = new SimpleIntegerProperty(id);
		this.idUsu = new SimpleIntegerProperty(idUsu);
		this.fecha = fecha;
		this.monto = new SimpleDoubleProperty(monto);
		this.numero = new SimpleIntegerProperty(numero);
                this.pago = new SimpleDoubleProperty(pago);
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
	//Metodos atributo: idUsu
	public int getIdUsu() {
		return idUsu.get();
	}
	public void setIdUsu(int idUsu) {
		this.id = new SimpleIntegerProperty(idUsu);
	}
        public IntegerProperty IdUsuProperty() {
		return idUsu;
	}
	
	//Metodos atributo: fecha
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	//Metodos atributo: monto
	public Double getMonto() {
		return monto.get();
	}
	public void setMonto(Double monto) {
		this.monto = new SimpleDoubleProperty(monto);
	}
	public DoubleProperty MontoProperty() {
		return monto;
	}
	//Metodos atributo: numero
	public int getNumero() {
		return numero.get();
	}
	public void setNumero(int numero) {
		this.numero = new SimpleIntegerProperty(numero);
	}
	public IntegerProperty NumeroProperty() {
		return numero;
	}
        //Metodos atributo: pago
	public Double getPago() {
		return pago.get();
	}
	public void setPago(Double pago) {
		this.pago= new SimpleDoubleProperty(pago);
	}
	public DoubleProperty PagoProperty() {
		return pago;
        }
    
    
public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Pagos (id_pago, id_usu, Fecha_pago, "+
                    "numero_pago, monto_pago, pago) values(?, ?, ?, ?, ?, ?)");
                    
            instruccion.setInt(1, id.get());
            instruccion.setInt(2, idUsu.get());
            instruccion.setDate(3, fecha);
            instruccion.setInt(4, numero.get());
            instruccion.setDouble(5, monto.get());
            instruccion.setDouble(6, pago.get());
            
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pago.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Pagos where id_pago = ?");
            instruccion.setInt(1, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Pago.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}