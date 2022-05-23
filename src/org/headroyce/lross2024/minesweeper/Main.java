package org.headroyce.lross2024.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class starts and runs program
 */
public class Main extends Application {
    static Stage stage;

    Scene SettingsScene = new Scene(new SettingsGraphics(), 500, 500);

    /**
     * Makes new JavaFX stage, then defines stage variable
     * @param primaryStage new stage object which program runs on
     */
    @Override
    public void start(Stage primaryStage){

        stage = primaryStage;
        stage.setScene(SettingsScene);
        stage.setTitle("Minesweeper");
        stage.show();

    }

    /**
     * Switches the scene to the other scene (there are only 2 classes so its basically a swap)
     * @param scene new scene to switch to
     */
    public static void switchScenes(Scene scene){
        stage.setScene(scene);
    }


    /**
     * Launches the program
     * @param args list of arguments to run
     */
    public static void main(String[] args) {
        launch(args);
    }
}
