/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.be;


/**
 *
 * @author Test
 */
public class Order {
    
    private String orderNumber;
    private String startDate;
    private String endDate;
    private String timeLeft;
    private String customerName;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public Order (String orderNumber, String startDate, String endDate, String timeLeft, String customerName){
        this.orderNumber = orderNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeLeft = timeLeft;
        this.customerName = customerName;
    }
    
    public Order(){
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    
}
