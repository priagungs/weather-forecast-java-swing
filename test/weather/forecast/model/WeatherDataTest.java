/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Priagung Satyagama
 */
public class WeatherDataTest {
    
    private WeatherData instance;
    private JsonArray currTest;
    private JsonArray forecastTest;
    
    
    public WeatherDataTest() throws IOException {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        instance = new WeatherData("jakarta");
        String currTestStr = "[" + instance.getData("https://api.openweathermap.org/data/2.5/weather?id=1642911&APPID=3506dfa8bbebf7709e6fba904a68559a") + "]";
        String forecastTestStr = "[" + instance.getData("https://api.openweathermap.org/data/2.5/forecast?id=1642911&APPID=3506dfa8bbebf7709e6fba904a68559a") + "]";
        currTest = stringToJsonArray(currTestStr);
        forecastTest = stringToJsonArray(forecastTestStr);
    }
    
    private JsonArray stringToJsonArray(String content){
        JsonReader reader = Json.createReader(new StringReader(content));
        JsonArray result = reader.readArray();
        reader.close();
        return result;
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of getCurrData method, of class WeatherData.
     */
    @Test
    public void testGetCurrData() {
        System.out.println("getCurrData");
        String expResult = currTest.toString();
        String result = instance.getCurrData().toString();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getForecastData method, of class WeatherData.
     */
    @Test
    public void testGetForecastData() {
        System.out.println("getForecastData");
        String expResult = forecastTest.toString();
        String result = instance.getForecastData().toString();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of getData method, of class WeatherData.
     */
    @Test
    public void testGetData() throws Exception {
        System.out.println("getData");
        String urlstr = "https://api.openweathermap.org/data/2.5/weather?id=1642911&APPID=3506dfa8bbebf7709e6fba904a68559a";
        String expResult = currTest.toString();
        String result = "[" + instance.getData(urlstr) + "]";
        System.out.println(expResult);
        System.out.println(result);
        assertEquals(expResult, result);
    }
}
