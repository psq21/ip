package uwu.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getUwuDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.startTypingAnimation(text);
        return db;
    }

    /**
     * Reveals Uwu's reply one character at a time to create a typewriter effect.
     *
     * @param text the complete reply to reveal
     */
    private void startTypingAnimation(String text) {
        dialog.setText("");

        Timeline typingAnimation = new Timeline();
        for (int characterCount = 1; characterCount <= text.length(); characterCount++) {
            int count = characterCount;
            typingAnimation.getKeyFrames().add(new KeyFrame(
                    Duration.millis(count * 35),
                    new KeyValue(dialog.textProperty(), text.substring(0, count))));
        }
        typingAnimation.play();
    }
}
