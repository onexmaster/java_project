/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication32;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class insert1 extends Frame implements ActionListener
{
TextField t1,t2,t3;
Label l1,l2,l3;
Button b1,b2;
Connection con=null;
PreparedStatement stat;
insert1()
{
t1=new TextField(10);
t2=new TextField(10);
t3=new TextField(10);
l1=new Label("Set Pin ");
l2=new Label("enter name please ");
l3=new Label("Enter Balance ");
b1=new Button("Create Account");
b2=new Button("Exit Application");
setLayout(new FlowLayout());
add(l1);
add(t1);
add(l2);
add(t2);
add(l3);
add(t3);
add(b1);
add(b2);
b1.addActionListener(this);
b2.addActionListener(this);
setSize(400,400);
setVisible(true);
//pack();
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
  stat = con.prepareStatement("insert into bank values (?, ?, ?)");
                String pin = t1.getText();
                String name1 = t2.getText();
                String bal=t3.getText();
               stat.setInt(1, Integer.parseInt(pin));
               stat.setInt(3, Integer.parseInt(bal));
  //stat.setString(1,id);
                stat.setString(2, name1);
                stat.executeUpdate();  
  //executeupdate -used for doing dml (insert,delete,update)
                con.close();
  }
  catch(Exception m)
  {
      System.out.println(m.getMessage());
}
}
else if(a.getSource()==b2)
System.exit(0);
}
}

/**
 *
 * @author TJain
 */
public class JavaApplication32 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        insert1 obj=new insert1();
    }
    
}





















































                                                                     
