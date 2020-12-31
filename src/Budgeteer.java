
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Holds all the object properties for a budgeteer
 * as well as the gets and sets for it
 * @author student
 */
public class Budgeteer {
    private int uid;
    private Double balance;
    private Date asOfDate;
    private Double sumIncomeItems;
    private Double sumExpenseItems;
    
    /**
     * empty constructor
     */
    public Budgeteer(){
        
    }
    
    /**
     * Instantiates the budgeteer object with a UID
     * @param UserID 
     */
    public Budgeteer(int UserID){
        this.uid = UserID;
    }

    /**
     * @return the uid
     */
    public int getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(int uid) {
        this.uid = uid;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return the asOfDate
     */
    public Date getAsOfDate() {
        return asOfDate;
    }

    /**
     * @param asOfDate the asOfDate to set
     */
    public void setAsOfDate(Date asOfDate) {
        this.asOfDate = asOfDate;
    }

    /**
     * @return the sumIncomeItems
     */
    public Double getSumIncomeItems() {
        return sumIncomeItems;
    }

    /**
     * @param sumIncomeItems the sumIncomeItems to set
     */
    public void setSumIncomeItems(Double sumIncomeItems) {
        this.sumIncomeItems = sumIncomeItems;
    }

    /**
     * @return the sumExpenseItems
     */
    public Double getSumExpenseItems() {
        return sumExpenseItems;
    }

    /**
     * @param sumExpenseItems the sumExpenseItems to set
     */
    public void setSumExpenseItems(Double sumExpenseItems) {
        this.sumExpenseItems = sumExpenseItems;
    }
    
}
