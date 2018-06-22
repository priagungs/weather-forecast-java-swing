/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.MainMenu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.json.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import weather.forecast.model.WeatherData;

/**
 *
 * @author Priagung Satyagama
 */
public class MainMenuFrame extends JFrame {
    private JsonArray dataCurrWeather;
    public JsonArray getDataCurrWeather(){
        return dataCurrWeather;
    }
    public void setDataCurrWeather(JsonArray dataCurrWeather){
        this.dataCurrWeather = dataCurrWeather;
    }
    
    private JsonArray dataForecastWeather;
    public JsonArray getDataForecastWeather(){
        return dataForecastWeather;
    }
    public void setDataForecastWeather(JsonArray dataForecastWeather){
        this.dataForecastWeather = dataForecastWeather;
    }
    
    private SearchPanel searchPanel;
    public SearchPanel getSearchPanel(){
        return searchPanel;
    }
    
    private CityResultDialog cityResultDialog;
    public CityResultDialog getCityResultDialog(){
        return cityResultDialog;
    }
    
    public MainMenuFrame(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,300);
        
        // set layout
        setLayout(new BorderLayout());
        
        SearchClickListener searchClickListener = new SearchClickListener(this);
        searchPanel = new SearchPanel(searchClickListener);
        
        Container container = getContentPane();
        container.add(searchPanel, BorderLayout.CENTER);
//        container.add(cityResultPanel, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    public void initializeCityResultDialog(){
        cityResultDialog = new CityResultDialog(this, true);
    }
    
    public static void main(String[] args){
        MainMenuFrame frame = new MainMenuFrame("Weather Forecast");
    }
}
