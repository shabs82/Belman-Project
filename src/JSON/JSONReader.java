/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import belmanfinalsemester.dal.AvailableWorkers;
import belmanfinalsemester.dal.ProductionOrder;
import belmanfinalsemester.dal.DBConnector;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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
 AvailableWorkers aWorker ;
    public void readJsonFile() throws SQLServerException, Exception {
    aWorker = new AvailableWorkers();
    FileReader reader = null;
        try {
            //ClassLoader classLoader = new JSONReader().getClass().getClassLoader();
            String fileName = "src/JSON/JSON.txt";
            //File file = new File(classLoader.getResource(fileName).getFile());
            JSONParser parser = new JSONParser();
            reader = new FileReader(fileName);
            Object obj = parser.parse(reader);
            JSONObject jsonObj = (JSONObject) obj;
            JSONArray AvailableWorkers = (JSONArray) jsonObj.get("AvailableWorkers");
            for (Object AvailableWorker : AvailableWorkers) {
                analiseWorker(AvailableWorker);
            }
            JSONArray AvailableOrders = (JSONArray) jsonObj.get("ProductionOrders");
            for (Object AvailableOrder : AvailableOrders) {
                analiseOrders(AvailableOrder);
            }

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
public void analiseWorker(Object AvailableWorker) throws FileNotFoundException, Exception{
                    JSONObject jObj = (JSONObject) AvailableWorker;
                String initials = jObj.get("Initials").toString();
                String name = jObj.get("Name").toString();
                long sal = (long) jObj.get("SalaryNumber");
                //aWorker.addAvailableWorkers(initials, name, sal);
}
    public void analiseOrders(Object AvailableOrder) {                 
            JSONObject jObj = (JSONObject) AvailableOrder;
            JSONObject orderIDobj = (JSONObject) jObj.get("Order");
            String orderNum = orderIDobj.get("OrderNumber").toString();
            System.out.println(orderNum);
            JSONArray depOrder = (JSONArray) jObj.get("DepartmentTasks");
            for (Object depTask : depOrder) {
                 JSONObject jjObj = (JSONObject) depTask;
            //JSONObject endDate = (JSONObject) jjObj.get("EndDate");
            String endDate = jjObj.get("EndDate").toString();
                System.out.println(endDate);
                
        }
    }

}
