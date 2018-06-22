/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.main;

import weather.forecast.main.MainMenuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.*;
import weather.forecast.model.WeatherData;

/**
 *
 * @author Priagung Satyagama
 */
public class SearchClickListener implements ActionListener {
    
    private MainMenuFrame frame;
    public SearchClickListener(MainMenuFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String query = frame.getSearchPanel().getTextField().getText();
        if (query.isEmpty()){
            return;
        }
        
        
        // gather data from Open Weather Map API
        // get current weather data
        // url = api.openweathermap.org/data/2.5/weather?q={city_name}&APPID=3506dfa8bbebf7709e6fba904a68559a
        WeatherData weatherData = null;
        try {
            weatherData = new WeatherData(query);
        } catch (IOException ex) {
            Logger.getLogger(SearchClickListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JsonArray currData = weatherData.getCurrData();
        JsonArray forecastData = weatherData.getForecastData();
        
        frame.setDataCurrWeather(currData);
        frame.setDataForecastWeather(forecastData);
        frame.initializeCityResultDialog();     
        frame.getCityResultDialog().setVisible(true);
        
        
    }
}
