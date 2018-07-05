/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhrosenheim.gui.u06.chart;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

/**
 *
 * @author denis
 */
public class GenerateDataService extends Service<ObservableList<XYChart.Series<Number, Number>>>{
    // must be binded because Service will only be instantiated once
    // but Task will be instantiated often
    StringProperty seriesName = new SimpleStringProperty();

    @Override
    protected Task createTask() {
        return new GenerateDataTask(seriesName.get());
    }
    
    public String getSeriesName(){
        return seriesName.get();
    }
    
    public void setSeriesName(String seriesName){
        this.seriesName.set(seriesName);
    }
    
    public StringProperty seriesNameProperty(){
        return seriesName;
    }
}
