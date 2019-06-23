package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smartaccess.modelo.Actividad;
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Usuario;
import smartaccess.modelo.Instructor;
import smartaccess.modelo.Nutriologo;
import smartaccess.modelo.Sala;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Aleman
 */
public class AñadirUsuarioController implements Initializable {
    
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnLimpiar;
    @FXML
    private JFXButton btnBuscarI;
    @FXML
    private JFXButton btnBuscarN;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnFoto;
    @FXML
    private JFXButton btnHuella;
    @FXML
    private JFXButton btnRegresar;
    
    @FXML
    private ImageView fotoUsu;
    
    @FXML
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtNombre;
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
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    Usuario em = new Usuario();
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
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/AñadirUsuario.fxml"));
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
        stage.setTitle("Añadir Usuario");
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        stage.show();
        stage.setResizable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listatipopago = FXCollections.observableArrayList("EFECTIVO", "TARJETA CRÉDITO", "TARJETA DÉBITO");
        listaInstructor = FXCollections.observableArrayList();
        listaNutriologo = FXCollections.observableArrayList();
        
        cbTP.setItems(listatipopago);
        Instructor.llenartabla(cnx, listaInstructor);
        cbInst.setItems(listaInstructor);
        Nutriologo.llenartabla(cnx, listaNutriologo);
        cbNutri.setItems(listaNutriologo);
        
        
    }
    
    
    @FXML
    public void guardarUsuario () {
        
        String foto = "asfnllcklvwri4gakldfnblñsndfihndfw55473";
        String huella = "a97h2ckligakld24624243246fnblñsdfw55473";
        Usuario e = new Usuario(Integer.valueOf(txtId.getText()), txtNombre.getText(), txtTel.getText(), 
                                  Date.valueOf(dpFecha.getValue()), cbTP.getSelectionModel().getSelectedItem(),Float.valueOf(txtPeso.getText()), 
                                  Float.valueOf(txtAltura.getText()), txtEnfermedad.getText(), txtAlergias.getText(),
                                  foto, huella, cbInst.getSelectionModel().getSelectedItem(), 
                                  cbNutri.getSelectionModel().getSelectedItem());
        int res = e.guardarregistro(cnx);
        
        if(res == 1){
            Image img = new Image("/smartaccess/resources/save.png");
                String title = "Usuario Agregado";
                String message = "El usuario se agrego satisfactoriamente";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(4));
                tray.setRectangleFill(Paint.valueOf("#5b9395"));
                tray.showAndWait();
        }
    }
    
    
    
    @FXML
    public void Limpiar () {
      limpiarregistro();
    }
    
    public void Regresar () throws IOException {
        MenuController ingresomenu = new MenuController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
    }
    
    public void Salir () {
    Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void limpiarregistro(){
        txtId.setText(null);
        txtNombre.setText(null);
        txtTel.setText(null);
        dpFecha.setValue(null);
        cbTP.setValue(null);
        txtPeso.setText(null);
        txtAltura.setText(null);
        txtEnfermedad.setText(null);
        txtAlergias.setText(null);
        cbInst.setValue(null);
        cbNutri.setValue(null);
        
        btnGuardar.setDisable(false);
    }
    
    public void AsignarInstructor(ActionEvent event) throws IOException {
        VistaInstructorController asignar = new VistaInstructorController();
        asignar.iniciarventana();
    }
    
    public void AsignarNutriologo(ActionEvent event) throws IOException {
        VistaNutriologoController asignar = new VistaNutriologoController();
        asignar.iniciarventana();
    }
    
}
    

