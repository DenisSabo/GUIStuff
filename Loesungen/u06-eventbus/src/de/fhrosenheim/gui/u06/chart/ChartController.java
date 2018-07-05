
package de.fhrosenheim.gui.u06.chart;

import de.fhrosenheim.gui.u06.header.HeaderController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Presenter für den Diagramm-View.
 *
 * @author dominik.haas
 */
public class ChartController implements Initializable {
    
    @FXML
    private HeaderController headerController;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML 
    private Button cancelButton;
    @FXML
    private Label progressLabel;
    @FXML
    private ProgressBar progressBar;
    
    private ChartModel chartModel;
    
    GenerateDataService generateDataService = new GenerateDataService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeGenerateDataServiceBindings();
        
        chartModel = new ChartModel(headerController.getSettingsModel());
        headerController.setActionListener(() -> {
            generateDataService.restart();
        });
        
        cancelButton.setOnAction((event) -> { 
            generateDataService.cancel();
        });
        
        chart.dataProperty().bind(chartModel.getChartData());
        
        generateDataService.setOnSucceeded((event) -> {
            chartModel.updateChartData(generateDataService.getValue());
        });
    }    
   
    
    public void initializeGenerateDataServiceBindings(){
        generateDataService.seriesNameProperty()
                .bind(headerController.getSettingsModel().seriesProperty());
        progressBar.progressProperty().bind(generateDataService.progressProperty());
        progressLabel.textProperty().bind(generateDataService.messageProperty());
    }
}
