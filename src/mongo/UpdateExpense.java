package mongo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class UpdateExpense {
    private JFrame frame;
    private JPanel panel;

    private JLabel addMess;
    private JLabel day;
    private JLabel breakfast;
    private JLabel lunch;
    private JLabel Dinner;

    private JTextField t_day;
    private JTextField t_breakfast;
    private JTextField t_lunch;
    private JTextField t_dinner;
    int Breakfast;
    String Lunch;
    String dinner;
    String typedText;

    private JButton update;
    private JButton add;
    private JButton back;

    public UpdateExpense() {

        panel = new JPanel(null);

        frame = new JFrame("Add Student");
        panel.setBackground(Color.orange);

        addMess = new JLabel("Enter Your ID");

        breakfast = new JLabel("ID");
        lunch = new JLabel("Amount");
        Dinner = new JLabel("Purpose");


        t_breakfast = new JTextField();
        t_lunch = new JTextField();
        t_dinner = new JTextField();

        addMess.setFont(new Font("Sanserif", Font.BOLD, 18));
        breakfast.setFont(new Font("Sanserif", Font.BOLD, 20));
        lunch.setFont(new Font("Sanserif", Font.BOLD, 20));
        Dinner.setFont(new Font("Sanserif", Font.BOLD, 20));

        addMess.setForeground(Color.BLACK);//Text color
        breakfast.setForeground(Color.BLACK);
        lunch.setForeground(Color.BLACK);
        Dinner.setForeground(Color.BLACK);

        addMess.setBounds(190, 40, 2900, 60);
        breakfast.setBounds(120, 150, 250, 30);
        lunch.setBounds(120, 190, 250, 30);
        Dinner.setBounds(120, 230, 250, 30);


        t_breakfast.setBounds(330, 150, 130, 30);
        t_lunch.setBounds(330, 190, 130, 30);
        t_dinner.setBounds(330, 230, 130, 30);


        t_breakfast.setBackground(Color.white);
        t_lunch.setBackground(Color.white);

        add = new JButton("Select");

        back = new JButton("Back");

        update = new JButton("Update");

        add.setFont(new Font("Sanserif", Font.BOLD, 20));
        back.setFont(new Font("Sanserif", Font.BOLD, 20));
        update.setFont(new Font("Sanserif", Font.BOLD, 20));

        add.setForeground(Color.orange);
        back.setForeground(Color.orange);
        update.setForeground(Color.orange);
        add.setBackground(Color.black);
        update.setBackground(Color.black);
        back.setBackground(Color.black);

        add.setBounds(50, 500, 150, 50);
        back.setBounds(215, 500, 150, 50);
        update.setBounds(380, 500, 150, 50);

        panel.add(addMess);

        panel.add(breakfast);
        panel.add(lunch);
        panel.add(Dinner);
        panel.add(update);
        panel.add(back);
        panel.add(t_lunch);
        panel.add(t_breakfast);
        panel.add(t_dinner);
        panel.add(add);

        frame.add(panel);
        frame.setTitle("Add Student");
        frame.setVisible(true);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        add.addActionListener ( new StudentRegistrationGuiHandler ());
        update.addActionListener ( new StudentRegistrationGuiHandler () );
        back.addActionListener ( new StudentRegistrationGuiHandler () );
    }
    class StudentRegistrationGuiHandler implements ActionListener {

        @Override
        public void actionPerformed( ActionEvent e) {
            if (e.getSource() == add) {
//                if(t_day.getText().trim().isEmpty()&& t_lunch.getText ().trim ().isEmpty ()&& t_breakfast.getText().trim().isEmpty()&& t_dinner.getText().trim().isEmpty()){
//
//                    AddMess p=new AddMess();
//                    frame.dispose ();
//                }
                try{
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","project","project");
                    Statement st = con.createStatement();
                    typedText = t_breakfast.getText ().trim ();
                    System.out.println (typedText );
                    String sqlStudent="Select * from expenses where expenseid='"+typedText+"'";
                    System.out.println(sqlStudent);


                    ResultSet resultStudent = st.executeQuery(sqlStudent);
                    while (resultStudent.next ()){
                        Breakfast=resultStudent.getInt ( 2 );
                        Lunch=resultStudent.getString ( 3 );

                    }
                    String str = String.valueOf(Breakfast);

                    t_lunch.setText (str );
                    t_dinner.setText ( Lunch );
                }
                catch (Exception e1){
                    JOptionPane.showMessageDialog ( null,"THIS day is already present" );
                    System.out.println(e1.toString());
                }

            }
            else if (e.getSource ()==update){
                if(t_breakfast.getText ().trim ().toLowerCase( Locale.ROOT ).equals ( Breakfast )&&t_lunch.getText ().trim ().toLowerCase( Locale.ROOT ).equals ( Lunch )&&t_dinner.getText ().trim ().toLowerCase( Locale.ROOT ).equals ( dinner )){
                }
                else{
                    try{
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","project","project");
                        Statement st = con.createStatement();
                        int number = Integer.parseInt(t_lunch.getText ().trim ());
                        String SQL="update expenses set expenseamount="+number+",expensepurpose='"+t_dinner.getText ()+"' where expenseid='"+t_breakfast.getText ()+"'";
                        System.out.println (SQL );
                        ResultSet resulttt = st.executeQuery(SQL);
                        JOptionPane.showMessageDialog ( null,"updated" );
                        frame.dispose ();
                        ExpenseGui n=new ExpenseGui ();




                    }
                    catch (Exception e1){
                        System.out.println(e1.toString());
                    }
                }
            }
            else if(e.getSource ()==back){
                frame.dispose ();
                ExpenseGui n=new ExpenseGui ();
            }
        }
    }
}

