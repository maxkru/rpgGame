package kriuchkov.maksim.game;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainWindowController {

    public Button secondaryButton;

    @FXML
    private void exit() throws IOException {
        System.exit(0);
    }
}
