package edu.ssau.gasstation.GUI;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.ssau.gasstation.DB.DBHelper;
import edu.ssau.gasstation.XMLHelper.XMLParser;
import edu.ssau.gasstation.topology.Topology;
import edu.ssau.gasstation.topology.TopologyItem;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andrey on 05.12.16.
 */
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/db_window.fxml"));
        primaryStage.setTitle("Управление справочниками");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*DBHelper db = new DBHelper();
        try {
            //TopologyItem ti = XMLParser.getTopologyFromFile("result.xml").getTopologyItem(1, 1);
            //ResultSet rs = db.executeQuery("INSERT INTO fuel (fuel_name) VALUES(АИ-76);");
            int id = db.getFuelID("АИ-96");
            db.executeQuery("SELECT * FROM fuel;");
        }catch (MySQLIntegrityConstraintViolationException e){
            //todo отобразить окно с сообщение о дублировании записи
        }
        catch (SQLException e) {
            e.printStackTrace();
        } /*catch (XMLStreamException e) {
            e.printStackTrace();
        }*/
        launch(args);
    }
}
