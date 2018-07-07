/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather.forecast.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
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
public class SearchPanelTest {
    
    public SearchPanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTextField method, of class SearchPanel.
     */
    @Test
    public void testGetTextField() {
        System.out.println("getTextField");
        SearchPanel instance = new SearchPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JTextField textField = new JTextField("test");
        instance.setTextField(textField);
        assertEquals(instance.getTextField(), textField);
    }
    
    @Test
    public void testSetTextField() {
        System.out.println("setTextField");
        SearchPanel instance = new SearchPanel(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        JTextField textField = new JTextField("test");
        instance.setTextField(textField);
        assertEquals(instance.getTextField(), textField);
        
    }
    
}
