/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSON;

import belmanfinalsemester.dal.AvailableWorkers;
import belmanfinalsemester.dal.DepartmentTasks;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    AvailableWorkers aWorker;
    DepartmentTasks depTasks;
    public void readJsonFile() throws SQLServerException, Exception {
        aWorker = new AvailableWorkers();
        depTasks = new DepartmentTasks();
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
                analyseWorker(AvailableWorker);
            }
            JSONArray AvailableOrders = (JSONArray) jsonObj.get("ProductionOrders");
            for (Object AvailableOrder : AvailableOrders) {
                analyseOrders(AvailableOrder);
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

    private void analyseWorker(Object AvailableWorker) throws FileNotFoundException, Exception {
        JSONObject jObj = (JSONObject) AvailableWorker;
        String initials = jObj.get("Initials").toString();
        String name = jObj.get("Name").toString();
        long sal = (long) jObj.get("SalaryNumber");
        aWorker.addAvailableWorkers(initials, name, sal);
    }

    private void analyseOrders(Object AvailableOrder) {
        JSONObject jObj = (JSONObject) AvailableOrder;
        JSONObject orderIDobj = (JSONObject) jObj.get("Order");
        String orderNum = orderIDobj.get("OrderNumber").toString();
        JSONObject deliveryIDobj = (JSONObject) jObj.get("Delivery");
        String deliveryTime = reformatDate(deliveryIDobj.get("DeliveryTime").toString());
        Date deliveryFormattedDates = new Date(Long.parseLong(deliveryTime));

        JSONArray depOrder = (JSONArray) jObj.get("DepartmentTasks");
        for (Object depTask : depOrder) {
            JSONObject jjObj = (JSONObject) depTask;
            //JSONObject endDate = (JSONObject) jjObj.get("EndDate");
            String endDate = reformatDate(jjObj.get("EndDate").toString());
            Date endFormattedDates = new Date(Long.parseLong(endDate));
            String startDate = reformatDate(jjObj.get("StartDate").toString());
            Date startFormattedDates = new Date(Long.parseLong(startDate));
            Boolean FinishedOrder = (Boolean) jjObj.get("FinishedOrder");
            JSONObject jjjObj = (JSONObject) jjObj.get("Department");
            String deptName = jjjObj.get("Name").toString();
            try {
                depTasks.addDepartmentTasks(deptName, startFormattedDates, endFormattedDates, FinishedOrder, orderNum);
            } catch (Exception ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("EndDate " + endFormattedDates + " StartDate " + startFormattedDates + " FinishedOrder " + FinishedOrder + " deptName " + deptName + " DeliveryTime "+ deliveryFormattedDates);
        }

    }

    private String reformatDate(String dateString) {
        String replaceString = dateString.replace("/", "");
        replaceString = replaceString.replace("Date(", "");
        replaceString = replaceString.replace("+0200)", "");
        return replaceString;
    }

}
