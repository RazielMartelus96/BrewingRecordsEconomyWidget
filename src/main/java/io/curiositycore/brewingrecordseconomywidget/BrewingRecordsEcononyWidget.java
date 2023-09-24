package io.curiositycore.brewingrecordseconomywidget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BrewingRecordsEcononyWidget extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BrewingRecordsEcononyWidget.class.getResource("main-window.fxml"));
        double minWidth = 1280;
        double minHeight = 720;

        Scene scene = new Scene(fxmlLoader.load(), minWidth, minHeight);

        stage.setTitle("B.R.E.W");
        stage.setScene(scene);
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}