
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Aleman
 */
public class MenuController implements Initializable {
    
    @FXML
    private JFXButton btnPerfil;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXButton btnModificar;
    @FXML
    private JFXButton btnpago;
    @FXML
    private JFXButton btnajus;
    @FXML
    private JFXButton btnSalir;
    
    Stage stage = new Stage();
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    public void iniciarventana() throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/Menu.fxml"));
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
    
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        Scene scene = new Scene(root);
        stage.setTitle("Menú Principal");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene); 
        stage.show();
        stage.setResizable(false);
        
    }
    
    
    public void Salir(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void AñadirUsuario(ActionEvent event) throws IOException {
        AñadirUsuarioController añadir = new AñadirUsuarioController();
        añadir.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void ModificarUsuarios(ActionEvent event) throws IOException {
        ModificarUsuariosController ver = new ModificarUsuariosController();
        ver.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void ActPago (ActionEvent event) throws IOException {
        ActualizarPagoController pago = new ActualizarPagoController();
        pago.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void ConfigActiSal(ActionEvent event) throws IOException {
        ConfigurarActividadesSalasController config = new ConfigurarActividadesSalasController();
        config.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    public void PerfilUsu(ActionEvent event) throws IOException {
        PerfilController perfil = new PerfilController();
        perfil.iniciarventana();
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
    }    
    
}
