
import java.io.IOException;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * instantiates the first frame
 * @author student
 */
public class Start {
    
    /**
     *
     * @param args
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static void main(String args[])throws IOException, 
	SQLException, ClassNotFoundException {
        
        if (args.length ==0){
            String errorMsg = "Usage: BudgeteerDbApp properitesFile";
            System.out.println(errorMsg);
            return;
        }
        else {
            SimpleDataSource.init(args[0]);
        }
        
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
    }
    
}
