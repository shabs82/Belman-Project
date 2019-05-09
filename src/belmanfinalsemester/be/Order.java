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
    private int timeLeft;
    
    public Order (String orderNumber, String startDate, String endDate, int timeLeft){
        this.orderNumber = orderNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeLeft = timeLeft;
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

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
    
    
}
