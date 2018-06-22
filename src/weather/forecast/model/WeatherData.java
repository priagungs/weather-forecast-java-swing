/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import weather.forecast.main.SearchClickListener;

/**
 *
 * @author Priagung Satyagama
 */
public class WeatherData {
    private JsonArray currData;
    public JsonArray getCurrData(){
        return currData;
    }
    
    private JsonArray forecastData;
    public JsonArray getForecastData(){
        return forecastData;
    }
    
    public WeatherData(String query) throws IOException{
        LinkedList<Integer> idCityList = searchIdCity(query);
        JsonArrayBuilder currDataBuilder = Json.createArrayBuilder();
        JsonArrayBuilder forecastDataBuilder = Json.createArrayBuilder();
        System.out.println(idCityList);
        if (!idCityList.isEmpty()){
            for (Integer id : idCityList){
                // read current data
                String currStr = getData("http://api.openweathermap.org/data/2.5/weather?id=" + id + "&APPID=3506dfa8bbebf7709e6fba904a68559a");
                JsonReader reader = Json.createReader(new StringReader(currStr));
                JsonObject data = reader.readObject();
                
                if(data.getInt("cod") == 200){
                    currDataBuilder.add(data);
                }
                reader.close();
                
                // read forecast data
                String forecastStr = getData("http://api.openweathermap.org/data/2.5/forecast?id=" + id + "&APPID=3506dfa8bbebf7709e6fba904a68559a");
                reader = Json.createReader(new StringReader(forecastStr));
                data = reader.readObject();
                if(data.getString("cod").equals("200")){
                    forecastDataBuilder.add(data);
                }
                reader.close();
            }
            currData = currDataBuilder.build();
            forecastData = forecastDataBuilder.build();
        }
        System.out.println(currData);
    }
    
    private LinkedList<Integer> searchIdCity(String cityname) throws FileNotFoundException, IOException{
        JsonArray idCity;
        try(BufferedReader br = new BufferedReader(new FileReader("city.list.json"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String content = sb.toString();
            JsonReader reader = Json.createReader(new StringReader(content));
            idCity = reader.readArray();
            reader.close();
        }
        LinkedList<Integer> idList = new LinkedList<>();
        for (JsonValue el : idCity){
            if (el.asJsonObject().getString("name").equalsIgnoreCase(cityname)){
                idList.add(el.asJsonObject().getInt("id"));
            }
        }
        return idList;
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
    
//        
//        // get forecast weather data 3 hours/5 days
//        // url = api.openweathermap.org/data/2.5/forecast?q={city_name}&APPID=3506dfa8bbebf7709e6fba904a68559a
//        String forecastStr = null;
//        try {
//            forecastStr = getData("http://api.openweathermap.org/data/2.5/forecast?q=" + query + "&APPID=3506dfa8bbebf7709e6fba904a68559a");
//        } catch (IOException ex) {
//            Logger.getLogger(SearchClickListener.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        reader = Json.createReader(new StringReader(forecastStr));
//        JsonObject forecastData = reader.readObject();
//        reader.close();
    
}
