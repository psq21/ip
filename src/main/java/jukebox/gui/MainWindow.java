package jukebox.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import jukebox.Jukebox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jukebox jukebox;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image jukeboxImage = new Image(this.getClass().getResourceAsStream("/images/uwu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Jukebox instance */
    public void setJukebox(Jukebox j) {
        jukebox = j;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jukebox's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = Jukebox.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJukeboxDialog(response, jukeboxImage)
        );
        userInput.clear();
    }
}
