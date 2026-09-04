import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jukebox.Jukebox;
import jukebox.gui.MainWindow;

import java.io.IOException;

/**
 * A GUI for Jukebox using FXML.
 */
public class Main extends Application {

    private Jukebox jukebox = new Jukebox();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJukebox(jukebox);  // inject the jukebox instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
