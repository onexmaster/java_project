package javaapplication33;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.JOptionPane;
class insert1 extends Frame implements ActionListener
{
TextField t1,t2;
Label l1,l2,l3;
Button b1,b2,b3,b4;
Connection con=null;
PreparedStatement stat,stmt1;
Statement st;
ResultSet rt;
insert1()
{
  l1=new Label("Enter Pin");
  l2=new Label("Enter Amount To withdraw/Deposit");
  l3=new Label("  ");
  t1=new TextField(10);
  t2=new TextField(10);
  b1=new Button("Deposit");
  b2=new Button("Withdraw");
  b3=new Button("Display balance");
  b4=new Button("Exit");
  setLayout(new FlowLayout());
  add(l1);
  add(t1);
  add(l3);
  add(t2);
  add(b1);
  add(b2);
  add(b3);
  add(b4);
  b1.addActionListener(this);
  b2.addActionListener(this);
  b3.addActionListener(this);
  b4.addActionListener(this);
  setSize(400,400);
  setVisible(true);
  
}
public void actionPerformed(ActionEvent a)
{
    if (a.getSource()==b1)
{
  String url = "jdbc:sqlserver://localhost:1433;databaseName=NERUL;integratedSecurity=true"; 
  try 
  {
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
         con = DriverManager.getConnection(url);
         stat = con.prepareStatement("select curr_amount from bank where pin = ?");
               String pin = t1.getText();
               stat.setInt(1, Integer.parseInt(pin));
               String amount=t2.getText();
               int dep_amount=Integer.parseInt(amount);
                 // st = con.createStatement();  
                 int curr_amount;
        rt = stat.executeQuery();              
             while (rt.next())      
             {
                 curr_amount=rt.getInt(1);
                 int final_amount= curr_amount + dep_amount;
                 System.out.println(final_amount);
                 con = DriverManager.getConnection(url);
                stmt1 = con.prepareStatement("update bank set curr_amount=?  where pin = ?");
                stmt1.setInt(1,final_amount);
                stmt1.setInt(2, Integer.parseInt(pin));
                stmt1.executeUpdate();
             }
               con.close();
 }
 catch(Exception m)
 {
     System.out.println(m.getMessage());
}
}
    if(a.getSource()==b2)
    {
          String url = "jdbc:sqlserver://localhost:1433;databaseName=NERUL;integratedSecurity=true"; 
  try 
  {
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
         con = DriverManager.getConnection(url);
         stat = con.prepareStatement("select curr_amount from bank where pin = ?");
               String pin = t1.getText();
               stat.setInt(1, Integer.parseInt(pin));
               String amount=t2.getText();
               int wit_amount=Integer.parseInt(amount);
                 // st = con.createStatement();  
                 int curr_amount;
                 
        rt = stat.executeQuery();              
             while (rt.next())      
             {
                 int final_amount;
                 curr_amount=rt.getInt(1);
                 if(wit_amount<curr_amount) 
                 {
                  final_amount= curr_amount - wit_amount;  
                  con = DriverManager.getConnection(url);
                  stmt1 = con.prepareStatement("update bank set curr_amount=?  where pin =?");
                  stmt1.setInt(1,final_amount);
                  stmt1.setInt(2, Integer.parseInt(pin));
                  stmt1.executeUpdate();
                 }
                 else
                 {
                    JOptionPane.showMessageDialog(null, 
                              "Not Enough Balance", 
                              "TITLE", 
                              JOptionPane.WARNING_MESSAGE); 
                 }
                 
             }
               con.close();
 }
 catch(Exception m)
 {
     System.out.println(m.getMessage());
}
        
    }
 if(a.getSource()==b3){
     String url = "jdbc:sqlserver://localhost:1433;databaseName=NERUL;integratedSecurity=true"; 
  try 
  {
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
         con = DriverManager.getConnection(url);
         stat = con.prepareStatement("select curr_amount from bank where pin = ?");
               String pin = t1.getText();
               stat.setInt(1, Integer.parseInt(pin));
               rt = stat.executeQuery();              
             while (rt.next()){
                 l3.setText(rt.getString(1));
             }
     
     
 }
  catch(Exception m)
 {
     System.out.println(m.getMessage());
}
  
}
 if(a.getSource()==b4){
     System.exit(0);
 }
}
}

/**
 *
 * @author TJain
 */
public class JavaApplication33 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        insert1 obj=new insert1();
    }
    
}

