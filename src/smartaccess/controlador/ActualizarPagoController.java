
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
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
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Instructor;
import smartaccess.modelo.Nutriologo;
import smartaccess.modelo.Pago;
import smartaccess.modelo.Usuario;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class ActualizarPagoController implements Initializable {

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
    private JFXTextField txtId;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtTP;
    @FXML
    private JFXTextField txtUltimaFecha;
    @FXML
    private JFXTextField txtMonto;
    @FXML
    private JFXTextField txtNumero;
    
    @FXML
    private DatePicker dpFecha;
    
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    
    Stage stage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    
    public void iniciarventana () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/ActualizarPago.fxml"));
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
        stage.setTitle("Actualizar Pago");
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
            ResultSet res = instrccion.executeQuery("SELECT Nom_Usu, TP_Usu " +
            "FROM Usuarios " +
            "WHERE id_Usu = '"+txtBUsu.getText()+"'");
            
            int count = 0;
            
            while(res.next()){
                
                    txtNom.setText(res.getString("Nom_Usu"));
                    txtTP.setText(res.getString("TP_Usu"));
                    txtUltimaFecha.setText("NUNCA");
                    count++;
            }
            
            if(count >= 1) {
                ResultSet res1 = instrccion.executeQuery("SELECT Fecha_Pago " +
                "FROM Pagos " +
                "WHERE id_usu = '"+txtBUsu.getText()+"'");
                
                while(res1.next()){
                
                    txtUltimaFecha.setText(res1.getDate("Fecha_pago").toString());
                    
                }
            }
            
            if (count < 1){
                Image img = new Image("/smartaccess/resources/error.png");
                String title = "Usuario Invalido";
                String message = "No existe el usuario con el ID ingresado.";
                
                txtNom.setText("");
                txtTP.setText("");
                txtUltimaFecha.setText("");
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
    public void guardarPago () {
        double pago = Double.parseDouble(txtMonto.getText()) * Double.parseDouble(txtNumero.getText());
        Pago p = new Pago(Integer.valueOf(txtId.getText()), Integer.valueOf(txtBUsu.getText()),
                     Date.valueOf(dpFecha.getValue()), Integer.valueOf(txtNumero.getText()), Double.valueOf(txtMonto.getText()),
                     pago);
        int res = p.guardarregistro(cnx);
        
        if(res == 1){
            
            Image img = new Image("/smartaccess/resources/money.png");
                String title = "Pago Actualizado";
                String message = "Se registró el pago de: "+txtBUsu.getText()+ " por: $"+pago;
                
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle(title);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.CUSTOM);
                tray.setImage(img);
                tray.showAndDismiss(Duration.seconds(5));
                tray.setRectangleFill(Paint.valueOf("#3e664f"));
                tray.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
