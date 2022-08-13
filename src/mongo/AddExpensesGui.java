package mongo;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddExpensesGui {
    private JFrame frame;
    private JPanel panel;

    private JLabel addMess;
    private JLabel day;
    private JLabel amount;
    private JLabel purpose;
    private JLabel Dinner;

    private JTextField t_day;
    private JTextField t_amount;
    private JTextField t_purpose;
    private JTextField t_dinner;
    JComboBox days;
    int id;
    private JButton clear;
    private JButton add;
    private JButton back;

    public AddExpensesGui (){
        panel = new JPanel (null);

        frame = new JFrame("Expenses");
        panel.setBackground( new Color ( 0,153,153 ));

        addMess = new JLabel("Expenses");
        day = new JLabel("Day:");
        amount = new JLabel("Amount");
        purpose = new JLabel("Purpose");
        Dinner = new JLabel("Dinner:");
        String []dayss={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        days=new JComboBox ( dayss );

        t_day = new JTextField();
        t_amount = new JTextField();
        t_purpose = new JTextField();
        t_dinner = new JTextField();

        addMess.setFont(new Font("Sanserif", Font.BOLD, 30));
        day.setFont(new Font("Sanserif", Font.BOLD, 20));
        amount.setFont(new Font("Sanserif", Font.BOLD, 20));
        purpose.setFont(new Font("Sanserif", Font.BOLD, 20));
        Dinner.setFont(new Font("Sanserif", Font.BOLD, 20));

        addMess.setForeground(Color.BLACK);//Text color
        day.setForeground(Color.BLACK);
        amount.setForeground(Color.BLACK);
        purpose.setForeground(Color.BLACK);
        Dinner.setForeground(Color.BLACK);

        addMess.setBounds(190, 40, 200, 60);
        day.setBounds(120, 110, 250, 30);
        amount.setBounds(120, 150, 250, 30);
        purpose.setBounds(120, 190, 250, 30);
        Dinner.setBounds(120, 230, 250, 30);

        days.setBounds(330, 110, 130, 30);
        t_amount.setBounds(330, 150, 130, 30);
        t_purpose.setBounds(330, 190, 130, 30);
        t_dinner.setBounds(330, 230, 130, 30);

        days.setBackground(Color.white);
        t_amount.setBackground(Color.white);
        t_purpose.setBackground(Color.white);

        add = new JButton("Add");

        back = new JButton("Back");

        clear = new JButton("Clear");

        add.setFont(new Font("Sanserif", Font.BOLD, 20));
        back.setFont(new Font("Sanserif", Font.BOLD, 20));
        clear.setFont(new Font("Sanserif", Font.BOLD, 20));

        add.setForeground(Color.orange);
        back.setForeground(Color.orange);
        clear.setForeground(Color.orange);
        add.setBackground(Color.black);
        clear.setBackground(Color.black);
        back.setBackground(Color.black);

        add.setBounds(50, 500, 150, 50);
        back.setBounds(215, 500, 150, 50);
        clear.setBounds(380, 500, 150, 50);

        panel.add(addMess);
        //panel.add(day);
        panel.add(amount);
        panel.add(purpose);
        //panel.add(Dinner);
        panel.add(clear);
        panel.add(back);
        //panel.add(days);
        panel.add(t_purpose);
        panel.add(t_amount);

        panel.add(add);

        frame.add(panel);
        frame.setTitle("Add Student");
        frame.setVisible(true);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        add.addActionListener ( new ExpensesGuihandler ());
        back.addActionListener ( new ExpensesGuihandler () );

    }
    class ExpensesGuihandler implements ActionListener{
        public void actionPerformed( ActionEvent e) {

            if ( e.getSource ( ) == add ){
                int x=0;
                if(t_amount.getText ().trim ().equals ( "" )|| t_purpose.getText ().trim ().equals ( "" )){
                    frame.dispose ();
                    AddExpensesGui vv=new AddExpensesGui ();
                }
                else{
                    try{
                        MongoClient mongo = new MongoClient( "localhost" , 27017 );
                        //Connecting to the database
                        MongoDatabase database = mongo.getDatabase("myDatabase");
                        MongoCollection mycol = database.getCollection("hostel");
                        //Creating a collection
                        //Preparing a document
                        MongoIterable<Document> docs = mycol.find();
                        MongoCursor<Document> it = docs.iterator();
                        while(it.hasNext()){

                            List<Document> edu = it.next().getList("expenses", Document.class);
                            for(int i = 0;i<edu.size();i++){
                                System.out.println(edu.get(i).getInteger("billno"));
                                x=edu.get(i).getInteger("billno");
                            }
                        }
                        x++;
                        Document con=new Document("username","admin");
                        int z=Integer.parseInt(t_amount.getText());
                        Document exp=  new Document("billno",x).append("amount",z).append("purpose",t_purpose.getText());
                        Document setData = new Document();
                        setData.append("expenses",exp);
                        Document update = new Document();
                        update.append("$push", setData);
                        mycol.updateOne(con,update);
                        JOptionPane.showMessageDialog(null,"Added");
                        frame.dispose();
                        AddExpensesGui k=new AddExpensesGui();

                    }
                    catch (Exception e1){
                        JOptionPane.showMessageDialog(null,"Enter valid data");
                        AddExpensesGui k=new AddExpensesGui();
                        System.out.println(e1.toString());
                    }
                }
            }
            else if(e.getSource ()==back){
                frame.dispose();
                ExpenseGui c =new ExpenseGui ();
            }
        }
    }
}
