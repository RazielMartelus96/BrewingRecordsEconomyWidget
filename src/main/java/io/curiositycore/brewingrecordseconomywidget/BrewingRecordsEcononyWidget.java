package io.curiositycore.brewingrecordseconomywidget;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//TODO logic for closing the main window (and exiting the application entire) should be added.
/**
 * Main class which initializes the user interface of the BREW executable.
 */
public class BrewingRecordsEcononyWidget extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BrewingRecordsEcononyWidget.class.getResource("main-window.fxml"));
        double minWidth = 1280;
        double minHeight = 720;

        Scene scene = new Scene(fxmlLoader.load(), minWidth, minHeight);
        prepareStage(stage,minWidth,minHeight,scene);
        stage.show();
    }

    /**
     * Main class that launches the application, as per the JavaFX library.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Prepares the stage for application initialisation. This includes the geometry, starting scene and close event of
     * the main application window.
     * @param stageToPrepare The stage to prepare (the main window stage).
     * @param minWidth The minimum width of the application window on startup.
     * @param minHeight The minimum height of the application window on startup.
     * @param scene The scene to set the stage to (the main application window) upon startup.
     * @return The prepared scene.
     */
    private Stage prepareStage(Stage stageToPrepare,double minWidth, double minHeight, Scene scene){
        stageToPrepare.setTitle("B.R.E.W");
        stageToPrepare.setScene(scene);
        stageToPrepare.setMinHeight(minHeight);
        stageToPrepare.setMinWidth(minWidth);
        stageToPrepare.setOnCloseRequest(closeEvent-> System.exit(0));
        return stageToPrepare;
    }
}