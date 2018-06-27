/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Priagung Satyagama
 */
public class ForecastWeatherClickListener implements ActionListener{
    private MainMenuFrame frame;
    
    public ForecastWeatherClickListener(MainMenuFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        frame.initializeForecastWeatherDialog();
        frame.getForecastWeatherDialog().setVisible(true);
    }
    
}
