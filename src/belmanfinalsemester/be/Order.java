/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belmanfinalsemester.be;

import java.time.LocalDate;


/**
 *
 * @author Test
 */
public class Order {
    
    private String orderNumber;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private int timeLeft;
    private double progress;
    

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public Order (String orderNumber, LocalDate startDate, LocalDate endDate, String customerName){
        this.orderNumber = orderNumber;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
    
    
}