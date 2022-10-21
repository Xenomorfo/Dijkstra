package JavaFX;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author José Canelas
 */
public class FrontEnd extends Application {

    private FrontEndModel model;
    private FrontEndView view;
    private FrontEndController controller;
    
     /**
     *
     * Classe FrontEnd
     */

    @Override
    public void start(Stage primaryStage) {

        model = new FrontEndModel();
        view = new FrontEndView();
        controller = new FrontEndController(model, view);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Parque Temático Bellwaerde");
        Scene scene = view.getScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
