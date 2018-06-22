/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.MainMenu;

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
        String currStr = null;
        try {
            currStr = getData("http://api.openweathermap.org/data/2.5/weather?q=" + query + "&APPID=3506dfa8bbebf7709e6fba904a68559a");
        } catch (IOException ex) {
            Logger.getLogger(SearchClickListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        JsonReader reader = Json.createReader(new StringReader(currStr));
        JsonObject currData = reader.readObject();
        reader.close();
        
        // get forecast weather data 3 hours/5 days
        // url = api.openweathermap.org/data/2.5/forecast?q={city_name}&APPID=3506dfa8bbebf7709e6fba904a68559a
        String forecastStr = null;
        try {
            forecastStr = getData("http://api.openweathermap.org/data/2.5/forecast?q=" + query + "&APPID=3506dfa8bbebf7709e6fba904a68559a");
        } catch (IOException ex) {
            Logger.getLogger(SearchClickListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader = Json.createReader(new StringReader(forecastStr));
        JsonObject forecastData = reader.readObject();
        reader.close();
        
        frame.setDataCurrWeather(currData);
        frame.setDataForecastWeather(forecastData);
        frame.getCityResultDialog().setCurrData(currData);
        frame.getCityResultDialog().setForecastData(forecastData);
        System.out.println(currData);
        System.out.println(frame.getCityResultDialog().getCurrData());
        
        frame.getCityResultDialog().setVisible(true);
        
        
    }
    
    public String getData(String urlstr) throws MalformedURLException, IOException{
            URL url = new URL(urlstr);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;
            String data = "";
            while ((inputLine = in.readLine()) != null) 
                data += inputLine;
            in.close();
            return data;
    }
}
