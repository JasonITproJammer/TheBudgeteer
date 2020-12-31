
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This object inherits the budgeteer properties and
 * holds all the SQL methods for returning data
 * from the SQL tables using the budgeteer properties
 * @author student
 */
public class BudgeteerSQL extends Budgeteer {
    
    /**
     * empty constructor
     */
    public BudgeteerSQL(){
        
    }
    
    /**
     * Instantiates the budgeteer object with a uid
     * @param uid 
     */
    public BudgeteerSQL(int uid){
        super(uid);
    }
    
    /**
     * Gets the full name from the SQL tables
     * @return 
     */
    public String returnFullName(){
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            String name = "user";
            String sql1 = "SELECT fullname FROM users "
                    + "WHERE userid = "+this.getUid()+"";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            while (rs1.next()){
                name = rs1.getString("FULLNAME");
            }
            conn.close();
            return name;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return "ERROR: "+e.getMessage();
        }
    }
    
    /**
     * Gets the AS Of Date from the SQL tables
     * @return 
     */
    public String returnAsOfDate(){
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Date balDate = new Date();
            String sql1 = "SELECT asofdate FROM accounts "
                    + "WHERE userid = "+this.getUid()+"";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            while (rs1.next()){
                balDate = rs1.getDate("ASOFDATE");
            }
            
            // Set the object's as of date
            this.setAsOfDate(balDate);
            
            // Format date to string formatted (MM/dd/ccyy)
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String asOfDate = formatter.format(balDate);
            
            // Close SQL connection and return data
            conn.close();
            return asOfDate;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return "ERROR: "+e.getMessage();
        }
    }
    
    /**
     * Gets the balance from the SQL tables
     * @return 
     */
    public String returnBalance(){
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Double bal=0.00;
            String sql1 = "SELECT balance FROM accounts "
                    + "WHERE userid = "+this.getUid()+"";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            while (rs1.next()){
                bal = rs1.getDouble("BALANCE");
            }
            
            // Set the object's balance variable
            this.setBalance(bal);
            
            // Format double to string formatted (#,###.##)
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String balance = decimalFormat.format(bal);
            
            // Close SQL connection and return data
            conn.close();
            return balance;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return "ERROR: "+e.getMessage();
        }
    }
    
    /**
     * Gets the income items sum from the SQL tables
     * @return 
     */
    public String returnIncomeItemsSum(){
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Double incomeItems=0.00;
            String sql1 = "SELECT amount FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+" "
                    + "AND itemtype='income'";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            while (rs1.next()){
                incomeItems += rs1.getDouble("AMOUNT");
            }
            
            // Set the object's sum income variable
            this.setSumIncomeItems(incomeItems);
            
            // Format double to string formatted (#,###.##)
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String sum = decimalFormat.format(incomeItems);
            
            // Close SQL connection and return data
            conn.close();
            return sum;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return "ERROR: "+e.getMessage();
        }
    }
    
    /**
     * Gets the income items details from the SQL tables
     * @return 
     */
    public String[][] returnIncomeItemsDetails(){
        String[][] arrResult;
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Double incomeItems=0.00;
            String sql1 = "SELECT shortname, amount FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+" "
                    + "AND itemtype='income'";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            int temp = 0;
            while (rs1.next()){
                    temp++;
            }
            arrResult = new String [temp][2];
            rs1 = stat1.executeQuery();
            temp = 0;
            while (rs1.next()){
                incomeItems += rs1.getDouble("AMOUNT");
                // Format double to string formatted (#,###.##) before adding to String array
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                String item = decimalFormat.format(incomeItems);
                // add income dblItem to string array
                arrResult[temp][0] = rs1.getString("SHORTNAME");
                arrResult[temp][1] = item;
                temp++;
            }
            
            // Set the object's sum income variable
            this.setSumIncomeItems(incomeItems);

            // Close SQL connection and return data
            conn.close();
            return arrResult;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            arrResult = new String[1][1];
            arrResult[0][0] = "ERROR: "+e.getMessage();
            return arrResult;
        }
    }
    
    /**
     * Gets the expense items sum from the SQL tables
     * @return 
     */
    public String returnExpenseItemsSum(){
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Double expenseItems=0.00;
            String sql1 = "SELECT amount FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+" "
                    + "AND itemtype='expense'";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            while (rs1.next()){
                expenseItems += rs1.getDouble("AMOUNT");
            }
            
            // Set the object's sum expense variable
            this.setSumExpenseItems(expenseItems);
            
            // Format double to string formatted (#,###.##)
            DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
            String sum = decimalFormat.format(expenseItems);
            
            // Close SQL connection and return data
            conn.close();
            return sum;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
            return "ERROR: "+e.getMessage();
        }
    }
    
    /**
     * Gets the expense items details from the SQL tables
     * @return 
     */
    public String[][] returnExpenseItemsDetails(){
        String[][] arrResult;
        try {
            // Declare variables and query Accounts table
            ResultSet rs1;
            Double expenseItems=0.00;
            String sql1 = "SELECT amount, shortname FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+" "
                    + "AND itemtype='expense'";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            int temp = 0;
            while (rs1.next()){
                temp++;
            }
            arrResult = new String [temp][2];
            rs1 = stat1.executeQuery();
            temp = 0;
            while (rs1.next()){
                expenseItems += rs1.getDouble("AMOUNT");
                // Format double to string formatted (#,###.##)
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                String item = decimalFormat.format(expenseItems);
                // add income dblItem to string array
		arrResult[temp][0] = rs1.getString("SHORTNAME");
		arrResult[temp][1] = item;
		temp++;
            }
            
            // Set the object's sum expense variable
            this.setSumExpenseItems(expenseItems);

            // Close SQL connection and return data
            conn.close();
            return arrResult;
        } catch(Exception e){
            System.out.println("ERROR: "+e.getMessage());
		arrResult = new String[1][1];
		arrResult[0][0] = "ERROR: "+e.getMessage();
		return arrResult;
        }
    }
    
    /**
     * Gets the ending balance based on the properties of the budgeteer object
     * @return 
     */
    public String returnEndingBalance(){
        Double endBal = this.getBalance()+this.getSumIncomeItems()-this.getSumExpenseItems();
        // Format double to string formatted (#,###.##)
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String sum = decimalFormat.format(endBal);
        return sum;
    }
    
    /**
     * Gets the item typs and short names from the budget items SQL table
     * @return 
     */
    public String[][] returnItemsTypeName(){
        String[][] arrResult;
        try{
            ResultSet rs1;
            String sql1 = "SELECT itemtype, shortname "
                    + "FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+"";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            int temp = 0;
            while (rs1.next()){
                    temp++;
            }
            arrResult = new String [temp][2];
            rs1 = stat1.executeQuery();
            temp = 0;
            while (rs1.next()){
                arrResult[temp][0] = rs1.getString("ITEMTYPE");
                arrResult[temp][1] = rs1.getString("SHORTNAME");
                temp++;
            }
            
            conn.close();
            return arrResult;
        } catch (Exception e){
            System.out.println("ERROR: "+e.getMessage());
            arrResult = new String[1][1];
            arrResult[0][0] = "ERROR: "+e.getMessage();
            return arrResult;
        }
    }
    
    /**
     * Gets the amounts and descriptions from the budget items SQL table
     * @return 
     */
    public String[][] returnItemsAmtDesc(){
        String[][] arrResult;
        try{
            ResultSet rs1;
            String sql1 = "SELECT amount, description "
                    + "FROM budgetitems "
                    + "WHERE userid = "+this.getUid()+"";
            Connection conn = SimpleDataSource.getConnection();
            PreparedStatement stat1 = conn.prepareStatement(sql1);
            rs1 = stat1.executeQuery();
            int temp = 0;
            while (rs1.next()){
                    temp++;
            }
            arrResult = new String [temp][2];
            rs1 = stat1.executeQuery();
            temp = 0;
            while (rs1.next()){
                DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
                String item = decimalFormat.format(rs1.getDouble("AMOUNT"));
                arrResult[temp][0] = item;
                arrResult[temp][1] = rs1.getString("DESCRIPTION");
                temp++;
            }
            
            conn.close();
            return arrResult;
        } catch (Exception e){
            System.out.println("ERROR: "+e.getMessage());
            arrResult = new String[1][1];
            arrResult[0][0] = "ERROR: "+e.getMessage();
            return arrResult;
        }
    }
}
