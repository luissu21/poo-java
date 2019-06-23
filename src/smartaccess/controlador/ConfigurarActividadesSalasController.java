
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smartaccess.modelo.Accesa;
import smartaccess.modelo.Actividad;
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Inscribe;
import smartaccess.modelo.Pago;
import smartaccess.modelo.Sala;
import smartaccess.modelo.Usuario;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


public class ConfigurarActividadesSalasController implements Initializable {

    @FXML
    private JFXButton btnBuscarID;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnActualizar;
    
    
    @FXML
    private JFXTextField txtBUsu;
    @FXML
    private JFXTextField txtNom;
   
    
    @FXML
    private JFXComboBox<Actividad> cbActi;
    @FXML
    private JFXComboBox<Sala> cbSal;
    
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    private ObservableList<Actividad> listaActividades;
    private ObservableList<Sala> listaSalas;
    
    
    Stage stage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    
    public void iniciarventana () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/ConfigurarActividadesSalas.fxml"));
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
        stage.setTitle("Configurar Activiades/Salas");
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        stage.show();
        stage.setResizable(false);
    }
    
    
    public void Salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    public void Regresar () throws IOException {
        MenuController ingresomenu = new MenuController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
    }
    
    @FXML
    public void BuscarUsu (ActionEvent event) {
        Conexion cc = new Conexion();
        Connection cnx = cc.getConexion();
        
        try {
            Statement instrccion = cnx.createStatement();
            ResultSet res = instrccion.executeQuery("SELECT Nom_Usu " +
            "FROM Usuarios " +
            "WHERE id_Usu = '"+txtBUsu.getText()+"'");
            
            int count = 0;
            
            while(res.next()){
                
                    txtNom.setText(res.getString("Nom_Usu"));
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
    
    @FXML
    public void guardarActividad() {
        
        Inscribe inscribe = new Inscribe(Integer.valueOf(txtBUsu.getText()), cbActi.getSelectionModel().getSelectedItem());
        int res = inscribe.guardarActividad(cnx);
        
        if(res == 1){
            
            Image img = new Image("/smartaccess/resources/activity.png");
                String title = "Actividad Registrada";
                String message = "Se registró la actividad: "+cbActi.getSelectionModel().getSelectedItem()+ " a: "+txtBUsu.getText();
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(5));
                tray.setRectangleFill(Paint.valueOf("#903fc3"));
                tray.showAndWait();
        }
    }
    
    @FXML
    public void guardarSalas() {
        
        Accesa accesa = new Accesa(Integer.valueOf(txtBUsu.getText()), cbSal.getSelectionModel().getSelectedItem());
        int res = accesa.guardarSala(cnx);
        
        if(res == 1){
            
            Image img = new Image("/smartaccess/resources/gym.png");
                String title = "Sala Registrada";
                String message = "Se registró la sala: "+cbSal.getSelectionModel().getSelectedItem()+ " a: "+txtBUsu.getText();
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(5));
                tray.setRectangleFill(Paint.valueOf("#3f8dc3"));
                tray.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        listaActividades = FXCollections.observableArrayList();
        listaSalas = FXCollections.observableArrayList();
        
        Actividad.llenarCombo(cnx, listaActividades);
        cbActi.setItems(listaActividades);
        
        Sala.llenarCombo(cnx, listaSalas);
        cbSal.setItems(listaSalas);
    }    
    
}
