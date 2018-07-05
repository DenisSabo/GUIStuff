package de.fhrosenheim.gui.u07;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class fuer Uebung 07
 * 
 * @author dominik.haas
 */
public class U07Parallel extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("chart/Chart.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        
        Task<String> task = new Task<String>() {
            @Override
            protected String call() throws Exception{
                System.out.println(Thread.currentThread());
                return "Hugo";
            }
            
            @Override 
            protected void succeeded() {
                System.out.println(Thread.currentThread() + " : " + super.getValue());
            }
        };
        
        new Thread(task).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
