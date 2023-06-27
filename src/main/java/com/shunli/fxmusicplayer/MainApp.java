package com.shunli.fxmusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 907, 589);
        stage.setTitle("FxMusicPlayer");
        stage.setScene(scene);
        stage.show();
        stage.setMinWidth(907);
        stage.setMinHeight(589);
    }

    public static void main(String[] args) {
        launch();
    }
}