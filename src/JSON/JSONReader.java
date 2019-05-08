/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author kulsoom-Abbas
 */
public class JSONReader {
    
    public void readJsonFile (){
    
        FileReader reader = null;
        try {
            //ClassLoader classLoader = new JSONReader().getClass().getClassLoader();
            String fileName ="src/JSON/JSON.txt";
            //File file = new File(classLoader.getResource(fileName).getFile());
            JSONParser parser = new JSONParser();
            reader = new FileReader(fileName);
            Object obj = parser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray AvailableWorkers = (JSONArray)jsonObj.get("AvailableWorkers");
            System.out.println("AvailableWorkers:"+ AvailableWorkers);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
    
    public void readJsonTxt(){
    
        FileReader reader = null;
        try {
            //ClassLoader classLoader = new JSONReader().getClass().getClassLoader();
            String fileName ="src/JSON/JSON.txt";
            //File file = new File(classLoader.getResource(fileName).getFile());
            JSONParser parser = new JSONParser();
            reader = new FileReader(fileName);
            Object obj = parser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray ProductionOrders = (JSONArray)jsonObj.get("ProductionOrders");
            System.out.println("ProductionOrders:"+ ProductionOrders);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

}