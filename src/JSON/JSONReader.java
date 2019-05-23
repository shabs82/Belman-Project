/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import belmanfinalsemester.dal.DatabaseWriter;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

    DatabaseWriter dbWriter;

    public void readJsonFile() throws SQLServerException, Exception {
        dbWriter = new DatabaseWriter();
        FileReader reader = null;//file reader has no file
        try {
           
            String fileName = "src/JSON/JSON.txt";//path of the file
            JSONParser parser = new JSONParser();//parser object created
            reader = new FileReader(fileName);//refers to file reader line 36
            Object obj = parser.parse(reader);//created a parsed object
            JSONObject jsonObj = (JSONObject) obj;//this obj is a json object
            JSONArray AvailableWorkers = (JSONArray) jsonObj.get("AvailableWorkers");//jsob object is jsonarray.
            for (Object AvailableWorker : AvailableWorkers) {
                analyseWorker(AvailableWorker);
            }
            JSONArray AvailableOrders = (JSONArray) jsonObj.get("ProductionOrders");
            for (Object AvailableOrder : AvailableOrders) {
                analyseOrders(AvailableOrder);
            }

        } catch (IOException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void analyseWorker(Object AvailableWorker) throws FileNotFoundException, Exception {
        JSONObject jObj = (JSONObject) AvailableWorker;//the object send through is typecast into jsonobject
        String initials = jObj.get("Initials").toString();
        String name = jObj.get("Name").toString();
        long sal = (long) jObj.get("SalaryNumber");
        dbWriter.addAvailableWorkers(initials, name, sal);
    }

    private void analyseOrders(Object AvailableOrder) throws Exception {
        JSONObject jObj = (JSONObject) AvailableOrder;
        JSONObject orderIDobj = (JSONObject) jObj.get("Order");
        String orderID = orderIDobj.get("OrderNumber").toString();
        JSONObject custIDobj = (JSONObject) jObj.get("Customer");
        String custID = custIDobj.get("Name").toString();
        JSONObject deliveryIDobj = (JSONObject) jObj.get("Delivery");
        String deliveryTime = reformatDate(deliveryIDobj.get("DeliveryTime").toString());
        Date deliveryFormattedDates = new Date(Long.parseLong(deliveryTime));
        dbWriter.addCustomer(custID);
        dbWriter.addProductionOrder(orderID, deliveryFormattedDates, false, custID);
        
        JSONArray depOrder = (JSONArray) jObj.get("DepartmentTasks");
        for (Object depTask : depOrder) {
            JSONObject jjObj = (JSONObject) depTask;
            String endDate = reformatDate(jjObj.get("EndDate").toString());
            Date endFormattedDates = new Date(Long.parseLong(endDate));
            String startDate = reformatDate(jjObj.get("StartDate").toString());
            Date startFormattedDates = new Date(Long.parseLong(startDate));
            Boolean FinishedOrder = (Boolean) jjObj.get("FinishedOrder");
            JSONObject jjjObj = (JSONObject) jjObj.get("Department");
            String deptName = jjjObj.get("Name").toString();
            dbWriter.addDepartment(deptName);

            try {
                dbWriter.addDepartmentOrder(deptName, startFormattedDates, endFormattedDates, FinishedOrder, orderID);
            } catch (Exception ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("StartDate " + startFormattedDates + " EndDate " + endFormattedDates + " FinishedOrder " + FinishedOrder + " deptName " + deptName + " DeliveryTime " + deliveryFormattedDates);
        }

    }

    private String reformatDate(String dateString) {

        String replaceString = dateString.replace("/", "");
        replaceString = replaceString.replace("Date(", "");
        replaceString = replaceString.replace("+0200)", "");
        return replaceString;
    }

}
