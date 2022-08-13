package mongo;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class ViewExpense {
    JButton back;
    JFrame frame2;//creating object of second JFrame
    DefaultTableModel defaultTableModel;//creating object of DefaultTableModel
    JTable table;//Creating object of JTable
    Connection connection;//Creating object of Connection class
    Statement statement;//Creating object of Statement class
    int total=0;
    public ViewExpense(){

    }

    public void viewExpensegui() {
        //setting the properties of second JFrame
        back = new JButton ( "OK" );
        back.setBounds ( 600 , 1000 , 200 , 30 );
        back.setForeground ( new Color ( 255 , 255 , 255 ) );
        back.setFont ( new Font ( "My boli" , Font.BOLD , 20 ) );
        back.setHorizontalTextPosition ( JLabel.CENTER );
        back.setVerticalTextPosition ( JLabel.BOTTOM );
        back.setHorizontalAlignment ( JLabel.CENTER );
        back.setBackground ( new Color ( 0 , 0 , 0 ) );
        back.setOpaque ( true );
        back.setFocusable ( false );
        back.addActionListener(new ActionListener () {
            public void actionPerformed( ActionEvent ae) {
                frame2.dispose ();
                ExpenseGui n=new ExpenseGui();

            }
        });
        frame2 = new JFrame ( "Database Results" );
        frame2.setLayout ( new FlowLayout ( ) );
        frame2.setSize ( 400 , 400 );



        //Setting the properties of JTable and DefaultTableModel
        defaultTableModel = new DefaultTableModel ( );
        table = new JTable ( defaultTableModel );
        table.setPreferredScrollableViewportSize ( new Dimension ( 300 , 200 ) );
        table.setFillsViewportHeight ( true );
        frame2.add ( new JScrollPane ( table ) );
        frame2.add ( back );

        defaultTableModel.addColumn ( "ID" );
        defaultTableModel.addColumn ( "Amount" );
        defaultTableModel.addColumn ( "Purpose" );

        try{
            int x;
            int n=0;
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
                    int id = edu.get(i).getInteger("billno");
                    int amount = edu.get(i).getInteger("amount");
                    String purpose = edu.get(i).getString("purpose");

                    defaultTableModel.addRow ( new Object[]{ id , amount , purpose  } );
                    // System.out.println(edu.get(i).getString("marksobtained"));


                    final MongoClient mongoClient = new MongoClient("localhost" , 27017 );
                    final DB db = mongoClient.getDB("myDatabase");
                    final DBCollection collection = db.getCollection("hostel");

//array as list
// array of stages

                    DBObject  unwind = new BasicDBObject("$unwind", "$expenses");

                    DBObject  group = new BasicDBObject("$group", new BasicDBObject("_id", "$username")
                            .append("total", new BasicDBObject("$sum", "$expenses.amount")));
                    final AggregationOutput aggregate = collection.aggregate( unwind,group);
                    for (DBObject result : aggregate.results()) {
                        n= (int) result.get("total");


                    }


                }
            }
            defaultTableModel.addRow ( new Object[]{ "" ,  "", ""  } );
            defaultTableModel.addRow ( new Object[]{ "TOTAL" , n , "" } );
            frame2.setVisible ( true );//Setting the visibility of second Frame
            frame2.validate ( );



        }
        catch (Exception e1){
            JOptionPane.showMessageDialog(null,"Enter valid data");
            AddExpensesGui k=new AddExpensesGui();
            System.out.println(e1.toString());
        }

    }
}
