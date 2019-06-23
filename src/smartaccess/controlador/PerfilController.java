
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Usuario;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author alema
 */
public class PerfilController implements Initializable {

    @FXML
    private JFXButton btnRegresar;
    @FXML
    private ImageView Foto;
    @FXML
    private Label Nombre;
    @FXML
    private Label Id;
    @FXML
    private JFXButton btnEditar;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private Label FechaNa;
    @FXML
    private Label Tel;
    @FXML
    private Label Instructor;
    @FXML
    private Label Nutriologo;
    @FXML
    private Label FechaPago;
    @FXML
    private Label Actividades;
    @FXML
    private Label Salas;
    @FXML
    private JFXTextField txtBUsu;
    @FXML
    private JFXButton btnBuscar;

    Stage stage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    
    
    public void iniciarventana () throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/Perfil.fxml"));
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
        stage.setTitle("Perfil del Usuario");
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        stage.show();
        stage.setResizable(false);
    }
    
    public void Salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void Regresar (ActionEvent event) throws IOException {
        MenuController ingresomenu = new MenuController();
            ingresomenu.iniciarventana();
            Stage stage = (Stage) btnSalir.getScene().getWindow();
            stage.close();
    }
    
    public void ModificarUsuarios(ActionEvent event) throws IOException {
        ModificarUsuariosController ver = new ModificarUsuariosController();
        ver.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void BuscarUsu (ActionEvent event) {
        Conexion cc = new Conexion();
        Connection cnx = cc.getConexion();
        
        try {
            Statement instrccion = cnx.createStatement();
            ResultSet res = instrccion.executeQuery("SELECT A.id_Usu, A.Nom_Usu, A.Tel_Usu, A.FeNa_Usu, "
                    +"E.Nom_Instr, F.Nom_Nutri, B.Fecha_pago, C.id_Acti, D.id_Sala " 
                    +"FROM Usuarios AS A INNER JOIN " 
                    +"Pagos AS B ON A.id_Usu = B.id_usu INNER JOIN " 
                    +"Inscribe AS C ON A.id_Usu = C.id_Usu INNER JOIN " 
                    +"Accesa AS D ON A.id_Usu = D.id_Usu INNER JOIN " 
                    +"Instructores AS E ON A.instructor = E.id_Instr INNER JOIN " 
                    +"Nutriologos AS F ON A.nutriologo = F.id_Nutri " 
                    +"WHERE A.id_Usu = '"+txtBUsu.getText()+"'");
            
            int count = 0;
            
            while(res.next()){
                
                    Nombre.setText(res.getString("Nom_Usu"));
                    Id.setText(String.valueOf(res.getInt("id_Usu")));
                    FechaNa.setText(res.getDate("FeNa_Usu").toString());
                    Tel.setText(res.getString("Tel_Usu"));
                    Instructor.setText(res.getString("Nom_Instr"));
                    Nutriologo.setText(res.getString("Nom_Nutri"));
                    FechaPago.setText(res.getDate("Fecha_pago").toString());
                    Actividades.setText(String.valueOf(res.getInt("id_Acti")));
                    Salas.setText(String.valueOf(res.getInt("id_Sala")));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
