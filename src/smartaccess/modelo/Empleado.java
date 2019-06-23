package smartaccess.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author Elyziita
 */
public class Empleado {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty contraseña;
    private StringProperty direccion;
    private StringProperty email;
    private StringProperty codigo;
    private StringProperty tipo;

    public Empleado(int id, String nombre, String apellido, String contraseña, String direccion, String email, String codigo, String tipo) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.contraseña = new SimpleStringProperty(contraseña);
        this.direccion = new SimpleStringProperty(direccion);
        this.email = new SimpleStringProperty(email);
        this.codigo = new SimpleStringProperty(codigo);
        this.tipo = new SimpleStringProperty(tipo);
    }
    
    public Empleado() {
    }
    
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getContraseña() {
        return contraseña.get();
    }

    public void setContraseña(String contraseña) {
        this.contraseña.set(contraseña);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public IntegerProperty IdProperty() {
        return id;
    }

    public StringProperty NombreProperty() {
        return nombre;
    }

    public StringProperty ApellidoProperty() {
        return apellido;
    }

    public StringProperty ContraseñaProperty() {
        return contraseña;
    }

    public StringProperty DireccionProperty() {
        return direccion;
    }

    public StringProperty EmailProperty() {
        return email;
    }
    
    public StringProperty CodigoProperty() {
        return codigo;
    }

    public StringProperty TipoProperty() {
        return tipo;
    }
    
    public void llenartabla (Connection Conexion, ObservableList<Empleado> lista) {
        try {
            Statement instrccion = Conexion.createStatement();
            ResultSet res = instrccion.executeQuery("select * from Empleado");
            
            while(res.next()){
                lista.add(new Empleado (res.getInt("id_empleado"),
                                        res.getString("nombre_empledo"),
                                        res.getString("apellido_empleado"),
                                        res.getString("contraseña_empleado"),
                                        res.getString("direccion_empleado"),
                                        res.getString("email_empleado"),
                                        res.getString("codigo_empleado"),
                                        res.getString("tipo_usuario")
                ));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Pago.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int guardarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("insert into Empleado (id_empleado, nombre_empledo, apellido_empleado,"
                    + "contraseña_empleado, direccion_empleado, email_empleado, codigo_empleado, tipo_usuario) values(?, ?, "
                    + "?, ?, ?, ?, ?, ?)");
            instruccion.setInt(1, id.get());
            instruccion.setString(2, nombre.get());
            instruccion.setString(3, apellido.get());
            instruccion.setString(4, contraseña.get());
            instruccion.setString(5, direccion.get());
            instruccion.setString(6, email.get());
            instruccion.setString(7, codigo.get());
            instruccion.setString(8, tipo.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int modificarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("update Empleado set nombre_empledo = ?, apellido_empleado = ?,"
                    + "contraseña_empleado = ?, direccion_empleado = ?, email_empleado = ?, codigo_empleado = ?, tipo_usuario = ? where id_empleado = ?");
            instruccion.setString(1, nombre.get());
            instruccion.setString(2, apellido.get());
            instruccion.setString(3, contraseña.get());
            instruccion.setString(4, direccion.get());
            instruccion.setString(5, email.get());
            instruccion.setString(6, codigo.get());
            instruccion.setString(7, tipo.get());
            instruccion.setInt(8, id.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int eliminarregistro (Connection Conexion) {
        try {
            PreparedStatement instruccion = Conexion.prepareStatement("delete from Empleado where nombre_empledo = ?");
            instruccion.setString(1, nombre.get());
            return instruccion.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
}
