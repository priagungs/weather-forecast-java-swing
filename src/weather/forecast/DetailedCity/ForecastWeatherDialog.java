/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.DetailedCity;

import java.util.ArrayList;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Priagung Satyagama
 */
public class ForecastWeatherDialog extends javax.swing.JDialog {

    private JsonObject forecastSelectedCity;

    /**
     * Creates new form DetailedWeather
     */
    public ForecastWeatherDialog(java.awt.Frame parent, boolean modal, JsonObject forecastSelectedCity) {
        super(parent, modal);
        this.forecastSelectedCity = forecastSelectedCity;
        initList();
        initPanel();
        
        dateList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                WeatherDetailPanel p = new WeatherDetailPanel(forecastSelectedCity.getJsonArray("list").getJsonObject(getIdxDate()), forecastSelectedCity.getJsonObject("city").getString("name"));
                layout.replace(weatherDetailPanel, p);
                weatherDetailPanel = p;
           }
            
        });
        
    }
    

    public int getIdxDate(){
        for(int i = 0; i < forecastSelectedCity.getJsonArray("list").size(); i++){
            if(forecastSelectedCity.getJsonArray("list").getJsonObject(i).getString("dt_txt").equals(dateList.getSelectedValue())){
                return i;
            }
        }        
        return 0;
    }

    private void initList(){
        jScrollPane1 = new javax.swing.JScrollPane();
        dateList = new javax.swing.JList<>();
        ArrayList<String> li = new ArrayList<>();
        if(forecastSelectedCity.getJsonArray("list") != null){
            for (JsonValue el : forecastSelectedCity.getJsonArray("list")){
                li.add(el.asJsonObject().getString("dt_txt"));
            }    
        }
        String[] strs = new String[li.size()];
        li.toArray(strs);
        
        dateList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = strs;
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
    }
    
    private void initPanel() {
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        weatherDetailPanel = new WeatherDetailPanel(forecastSelectedCity.getJsonArray("list").getJsonObject(getIdxDate()), forecastSelectedCity.getJsonObject("city").getString("name"));
       
        jScrollPane1.setViewportView(dateList);

        layout = new javax.swing.GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(weatherDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(weatherDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        pack();
    }

    // Variables declaration - do not modify                     
    private javax.swing.JList<String> dateList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.GroupLayout layout;
    private WeatherDetailPanel weatherDetailPanel;
    // End of variables declaration                   
}
