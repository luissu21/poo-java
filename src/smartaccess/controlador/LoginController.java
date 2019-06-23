package smartaccess.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.util.Duration;
import smartaccess.modelo.Conexion;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Aleman
 */
public class LoginController implements Initializable {
    
    @FXML
    private JFXTextField usu;
    @FXML
    private JFXPasswordField passw;
    @FXML
    private JFXButton btnExit;
    @FXML
    private Label nodata;
    
    Icon Info;
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    
    
    void acceder (String usuario, String pass) throws IOException {
     
        if (usu.getText().equals("natalia") && passw.getText().equals("perez27")) {
            System.out.println("Eres administrador");
            nodata.setText("");
            Image img = new Image("/smartaccess/resources/user-4.png");
                String title = "Inicio de Sesión Exitoso";
                String message = "Bienvenido(a): natalia";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(4));
                tray.setRectangleFill(Paint.valueOf("#5b9395"));
                tray.showAndWait();
            usu.setText("");
            passw.setText("");
            MenuController ingresomenu = new MenuController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
                
        } else if (usu.getText().equals("sa") && passw.getText().equals("9616")) {
            System.out.println("Eres gerente");
            nodata.setText("");
            Image img = new Image("/smartaccess/resources/user-4.png");
                String title = "Inicio de Sesión Exitoso";
                String message = "Bienvenido(a): sa";
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(4));
                tray.setRectangleFill(Paint.valueOf("#5b9395"));
                tray.showAndWait();
            usu.setText("");
            passw.setText("");
            MenuSAController ingresomenu = new MenuSAController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnExit.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Datos Invalidos");
            nodata.setText("* Usuario y/o Contraseña Incorrectos");
            usu.setText("");
            passw.setText("");
        }
            
    } 
    
    

    
    @FXML
    private void btnInicioAction(ActionEvent event) throws IOException {
        
        String usuario = usu.getText();
        String password = passw.getText();
        acceder(usuario, password);
    }
    
    @FXML
    public void btnSalir(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    
    
}
