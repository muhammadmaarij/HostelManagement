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

public class ViewUni {
    JFrame frame2;
    JButton logout;
    DefaultTableModel defaultTableModel;
    JTable table;
    String uni1 = "";
    String n = "";

    public void viewUniGui(String s){
        uni1 = s;
        frame2 = new JFrame("Database Results");
        frame2.setLayout(new FlowLayout());
        frame2.setSize(400, 400);

        defaultTableModel = new DefaultTableModel();
        table = new JTable(defaultTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(300, 200));
        table.setFillsViewportHeight(true);
        frame2.add(new JScrollPane(table));

        defaultTableModel.addColumn("name");
        defaultTableModel.addColumn("username");
        defaultTableModel.addColumn("password");
        defaultTableModel.addColumn("university");

        try {
            MongoClient mongo = new MongoClient( "localhost" , 27017 );
            MongoDatabase database = mongo.getDatabase("myDatabase");
            MongoCollection<Document> collection = database.getCollection("students");
//            MongoIterable<Document> docs = collection.find();
//            MongoCursor<Document> it = docs.iterator();

            Document query = new Document("university", uni1);

            MongoIterable<Document> docs = collection.find(query);

            MongoCursor<Document> it = docs.iterator();


            while (it.hasNext()) {
                Document doc = it.next();
                String name = doc.getString("name");
                String username = doc.getString("username");
                String pass = doc.getString("password");
                String uni = doc.getString("university");

                defaultTableModel.addRow(new Object[]{name, username, pass, uni});//Adding row in Table
                frame2.setVisible(true);//Setting the visibility of second Frame
                frame2.validate();



            }
            final MongoClient mongoClient1 = new MongoClient("localhost" , 27017 );
            final DB db1 = mongoClient1.getDB("myDatabase");
            final DBCollection collection1 = db1.getCollection("hostel");






            DBObject  match = new BasicDBObject("$match", query);

            DBObject  project = new BasicDBObject("$project", new BasicDBObject());
            final AggregationOutput aggregate = collection1.aggregate(match, project);
            for (DBObject result : aggregate.results()) {
                n= (String) result.get("university");

            }
//                int r = Integer.parseInt(doc.getInteger("room").toString());
//                String room = Integer.toString(r);
//                int a = Integer.parseInt(doc.getInteger("age").toString());
//                String age = Integer.toString(a);
//                int c = Integer.parseInt(doc.getInteger("contact").toString());
//                String contact = Integer.toString(c);
//                defaultTableModel.addRow(new Object[]{name, username, pass, uni, room, age, contact});//Adding row in Table

//            }
//            while (resultSet.next()) {
//
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        logout = new JButton("Back");
        logout.setFont(new Font("Sanserif", Font.BOLD, 20));
        logout.setForeground(Color.orange);
        logout.setFocusable(false);
        logout.setBounds(175, 370, 50, 15);
        logout.setBackground(Color.black);
        frame2.add(logout);
        logout.addActionListener (new StudentRegistrationGuiHandler());
    }
    class StudentRegistrationGuiHandler implements ActionListener {

        @Override
        public void actionPerformed( ActionEvent e) {
            if (e.getSource() == logout) {
                StudentManagementGui f = new StudentManagementGui();
                frame2.dispose();
            }
        }
    }
}
