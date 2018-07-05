/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhrosenheim.gui.u06.chart;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

/**
 *
 * @author denis
 */
public class GenerateDataTask extends Task<ObservableList<XYChart.Series<Number, Number>>>{
    
    private final SimpleObjectProperty<ObservableList<XYChart.Series<Number, Number>>> chartData = new SimpleObjectProperty<>();
    private final ObservableList<XYChart.Series<Number, Number>> seriesList = FXCollections.observableArrayList();
    String seriesName;
    private final int SLEEPING_ITERATIONS = 10;
    
    public GenerateDataTask(String seriesName){
        this.seriesName = seriesName;
    }
    
    
    @Override
    public ObservableList<XYChart.Series<Number, Number>> call(){
        if(simulateLongRunningTask()){
            return generateData(seriesName);
        }
        else return null;
    }
    
    private ObservableList<XYChart.Series<Number, Number>> generateData(String seriesName){
        // will run in own thread
        seriesList.clear();
        final XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(seriesName);
        for(int i = 0; i < 100; i++) {
            series.getData().add(new XYChart.Data<>(i, Math.random() * i));
        }
        seriesList.addAll(series);
        return seriesList;
    }
    
    private boolean simulateLongRunningTask(){
        for(int i = 1; i <= SLEEPING_ITERATIONS; i++){
            if(isCancelled()) return false;
            try{
                Thread.sleep(1000);
                super.updateProgress(i, SLEEPING_ITERATIONS);
                super.updateMessage("Step " + i + "/"+ SLEEPING_ITERATIONS);
            }
            catch(InterruptedException ex){
                ex.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
