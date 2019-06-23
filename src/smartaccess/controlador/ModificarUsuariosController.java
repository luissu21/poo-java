
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.sun.nio.sctp.Notification;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Instructor;
import smartaccess.modelo.Nutriologo;
import smartaccess.modelo.Usuario;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Elyziita
 */
public class ModificarUsuariosController implements Initializable {

    @FXML
    private JFXButton btnBuscarID;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnEliminar;
    @FXML
    private JFXButton btnBuscarI;
    @FXML
    private JFXButton btnBuscarN;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnHuella;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnpago;
    @FXML
    private JFXButton btnact;
    
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtBUsu;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtTel;
    @FXML
    private JFXTextField txtPeso;
    @FXML
    private JFXTextField txtAltura;
    @FXML
    private JFXTextField txtEnfermedad;
    @FXML
    private JFXTextField txtAlergias;
    
    @FXML
    private JFXComboBox<String> cbTP;
    @FXML
    private JFXComboBox<Instructor> cbInst;
    @FXML
    private JFXComboBox<Nutriologo> cbNutri;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private ImageView ImaFoto;
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    Usuario em = new Usuario();
    private ObservableList<Usuario> listaUsuario;
    private ObservableList<Instructor> listaInstructor;
    private ObservableList<Nutriologo> listaNutriologo;
    private ObservableList<String> listatipopago;
    
    
    Stage stage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    public void iniciarventana () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/ModificarUsuarios.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        
        
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = stage.getX() - event.getScreenX();
                dragDelta.y = stage.getY() - event.getScreenY();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() + dragDelta.x);
                stage.setY(event.getScreenY() + dragDelta.y);
                stage.getWidth();
                stage.getHeight();
                stage.getX();
                stage.getY();
                inDrag.set(true);
            }
        });
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Ver Usuarios");
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        stage.show();
        stage.setResizable(false);
    }
    
    public void Salir () {
    Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void AsignarInstructor(ActionEvent event) throws IOException {
        VistaInstructorController asignar = new VistaInstructorController();
        asignar.iniciarventana();
    }
    
    public void AsignarNutriologo(ActionEvent event) throws IOException {
        VistaNutriologoController asignar = new VistaNutriologoController();
        asignar.iniciarventana();
    }
    
    public void eliminarUsuario () {
        
        String foto = "asfnllcklvwrigakldfnblñsndfih4ndfw55473";
        String huella = "a97h2ckligakld24624243246fnblñsdfw55473";
        Usuario usu = new Usuario(Integer.valueOf(txtId.getText()), txtNom.getText(), txtTel.getText(), 
                                  Date.valueOf(dpFecha.getValue()), cbTP.getValue(),Float.valueOf(txtPeso.getText()), 
                                  Float.valueOf(txtAltura.getText()), txtEnfermedad.getText(), txtAlergias.getText(),
                                  foto, huella, cbInst.getSelectionModel().getSelectedItem(), 
                                  cbNutri.getSelectionModel().getSelectedItem());
        int res = usu.eliminarregistro(cnx);
        
        if (res == 1){
            
            txtBUsu.setText("");
            txtId.setText("");
            txtNom.setText("");
            txtTel.setText("");
            dpFecha.setValue(null);
            cbTP.setValue(null);
            txtPeso.setText(null);
            txtAltura.setText(null);
            txtEnfermedad.setText(null);
            txtAlergias.setText(null);
            cbInst.setValue(null);
            cbNutri.setValue(null);
            
            Image img = new Image("/smartaccess/resources/garbage-2.png");
                String title = "Usuario Eliminado";
                String message = "El Usuario se elimino exitosamente.";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(4));
                tray.setRectangleFill(Paint.valueOf("#f3cd3e"));
                tray.showAndWait();
        }
    }
    
    public void Regresar () throws IOException {
        MenuController ingresomenu = new MenuController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
    }
    
    public void ConfActiSalas (ActionEvent event) throws IOException {
        ConfigurarActividadesSalasController config = new ConfigurarActividadesSalasController();
        config.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void BuscarUsu (ActionEvent event) {
        Conexion cc = new Conexion();
        Connection cnx = cc.getConexion();
        
        try {
            Statement instrccion = cnx.createStatement();
            ResultSet res = instrccion.executeQuery("SELECT A.id_Usu, A.Nom_Usu, A.Tel_Usu, A.FeNa_Usu, A.TP_Usu, A.Peso_Usu, A.Alt_Usu, A.Enf_Usu,"
                    +" A.Alerg_Usu, A.instructor, A.nutriologo, A.Foto_Usu, A.HuellaDac_Usu, B.Nom_Instr, C.Nom_Nutri, "
                    +"B.Tel_Instr, B.TiempEP, B.Esp_Instr, B.horaE_instr, B.horaS_instr, " 
                    +"C.Tel_Nutri, C.Correo_Nutri, C.RFC_Nutri, C.Esp_Nutri, C.horaE_Nutri, C.horaS_Nutri "
                    +"FROM Usuarios AS A INNER JOIN " 
                    +"Instructores AS B ON A.instructor = B.id_Instr INNER JOIN " 
                    +"Nutriologos AS C ON A.nutriologo = C.id_Nutri " 
                    +"WHERE A.id_Usu = '"+txtBUsu.getText()+"'");
            
           
            
            
            int count = 0;
            
            listaInstructor = FXCollections.observableArrayList();
            listaNutriologo = FXCollections.observableArrayList();
            
            
            
            Instructor.llenartabla(cnx, listaInstructor);
            cbInst.setItems(listaInstructor);
            Nutriologo.llenartabla(cnx, listaNutriologo);
            cbNutri.setItems(listaNutriologo);
            cbTP.setItems(listatipopago);
            
            while(res.next()){
                
                    txtId.setText(String.valueOf(res.getInt("id_Usu")));
                    txtNom.setText(res.getString("Nom_Usu"));
                    txtTel.setText(res.getString("Tel_Usu"));
                    dpFecha.setValue(res.getDate("FeNa_Usu").toLocalDate());
                    cbTP.setValue(res.getString("TP_Usu"));
                    txtPeso.setText(String.valueOf(res.getFloat("Peso_Usu")));
                    txtAltura.setText(String.valueOf(res.getFloat("Alt_Usu")));
                    txtEnfermedad.setText(res.getString("Enf_Usu"));
                    txtAlergias.setText(res.getString("Alerg_Usu"));
                    cbInst.setValue(new Instructor (res.getInt("instructor"),
                                        res.getString("Nom_Instr"),
                                        res.getString("Tel_Instr"),
                                        res.getInt("TiempEP"),
                                        res.getString("Esp_Instr"),
                                        res.getTime("horaE_instr"),
                                        res.getTime("horaS_instr")));
                    cbNutri.setValue(new Nutriologo (res.getInt("nutriologo"),
                                        res.getString("Nom_Nutri"),
                                        res.getString("Tel_Nutri"),
                                        res.getString("Correo_Nutri"),
                                        res.getString("RFC_Nutri"),
                                        res.getString("Esp_Nutri"),
                                        res.getTime("horaE_Nutri"),
                                        res.getTime("horaS_Nutri")));
                  
                    txtBUsu.setText("");
                    count++;
            }
            if (count < 1){
                Image img = new Image("/smartaccess/resources/error.png");
                String title = "Usuario Invalido";
                String message = "No existe el usuario con el ID ingresado.";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(2));
                tray.setRectangleFill(Paint.valueOf("#bf2c1d"));
                tray.showAndWait();
                    
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarUsuario () {
        String foto = "asfnllcklvwrigakldfnblñsndfihndfw55473";
        String huella = "a97h2ckligakld24624243246fnblñsdfw55473";
        Usuario usu = new Usuario(Integer.valueOf(txtId.getText()), txtNom.getText(), txtTel.getText(), 
                                  Date.valueOf(dpFecha.getValue()), cbTP.getValue(),Float.valueOf(txtPeso.getText()), 
                                  Float.valueOf(txtAltura.getText()), txtEnfermedad.getText(), txtAlergias.getText(),
                                  foto, huella, cbInst.getSelectionModel().getSelectedItem(), 
                                  cbNutri.getSelectionModel().getSelectedItem());
        int res = usu.modificarregistro(cnx);
        
        if (res == 1){
            Image img = new Image("/smartaccess/resources/success.png");
                String title = "Usuario Modificado";
                String message = "Los nuevos datos del usuario seleccionado se guardaron satisfactoriamente.";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(4));
                tray.setRectangleFill(Paint.valueOf("#27ae61"));
                tray.showAndWait();
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listatipopago = FXCollections.observableArrayList();
        Statement instrccion1;
        try {
            instrccion1 = cnx.createStatement();
            ResultSet res1 = instrccion1.executeQuery("SELECT DISTINCT TP_Usu "
                    +"FROM Usuarios");
            while (res1.next()) {
                listatipopago.add(res1.getString("TP_Usu"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
