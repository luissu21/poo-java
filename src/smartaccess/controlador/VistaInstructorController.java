
package smartaccess.controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import smartaccess.modelo.Conexion;
import smartaccess.modelo.Instructor;

/**
 * FXML Controller class
 *
 * @author Elyziita
 */
public class VistaInstructorController implements Initializable {

    @FXML
    private TableView tablaInstructor;
    
    @FXML
    private TableColumn<Instructor, Integer> colId;
    @FXML
    private TableColumn<Instructor, String> colNom;
    @FXML
    private TableColumn<Instructor, Integer> colTel;
    @FXML
    private TableColumn<Instructor, Integer> colTiempo;
    @FXML
    private TableColumn<Instructor, String> colEsp;
    @FXML
    private TableColumn<Instructor, Time> colHE;
    @FXML
    private TableColumn<Instructor, Time> colHS;
    
    @FXML
    private JFXButton btnSalir;
    
    
    Conexion cc = new Conexion();
    Connection cnx = cc.getConexion();
    private ObservableList<Instructor> listaInstructor;
    
    Stage stage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    
    private static class Delta {
        double x, y;
    }
    
    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);
    
    
    public void iniciarventana() throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource("/smartaccess/VistaInstructor.fxml"));
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
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene); 
        Image icon = new Image(getClass().getResourceAsStream("/smartaccess/resources/user-4.png"));
        stage.getIcons().add(icon);
        stage.show();
        stage.setResizable(false);
    }
    
    public void Salir(ActionEvent event) {
        
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaInstructor = FXCollections.observableArrayList();
        Instructor.llenartabla(cnx, listaInstructor);
        tablaInstructor.setItems(listaInstructor);
        colId.setCellValueFactory(new PropertyValueFactory<Instructor, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Instructor, String>("nombre"));
        colTel.setCellValueFactory(new PropertyValueFactory<Instructor, Integer>("telefono"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<Instructor, Integer>("tiempoep"));
        colEsp.setCellValueFactory(new PropertyValueFactory<Instructor, String>("especialidad"));
        colHE.setCellValueFactory(new PropertyValueFactory<Instructor, Time>("horaE"));
        colHS.setCellValueFactory(new PropertyValueFactory<Instructor, Time>("horaS"));
    }    
    
}
