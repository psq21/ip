import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uwu.Uwu;
import uwu.gui.MainWindow;

import java.io.IOException;

/**
 * A GUI for Uwu using FXML.
 */
public class Main extends Application {

    private Uwu uwu = new Uwu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setUwu(uwu);  // inject the uwu instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
