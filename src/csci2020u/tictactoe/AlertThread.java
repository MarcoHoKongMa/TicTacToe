package csci2020u.tictactoe;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertThread extends Thread {
    private Alert alert;

    public AlertThread(Alert alert) {
        this.alert = alert;
    }

    public void run() {
        alert.showAndWait();
    }

    Platform platform;
}
