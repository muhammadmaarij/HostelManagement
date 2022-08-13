package mongo;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import com.mongodb.MongoClient;
import org.bson.conversions.Bson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateStudent2 {

    int sCnic = 0;

    private JFrame frame;
    private JPanel panel;

    private JLabel addStd;
    private JLabel stdAge;
    private JLabel stdName;
    private JLabel stdDob;
    private JLabel stdUsername;
    private JLabel stdPassword;
    private JLabel stdContact;
    private JLabel stdUniversity;
    private JLabel stdRoom;

    private JTextField t_stdAge;
    private JTextField t_stdName;
    private JTextField t_stdDob;
    private JTextField t_stdUsername;
    private JTextField t_stdPassword;
    private JTextField t_stdContact;
    private JTextField t_stdUniversity;
    private JTextField t_stdRoom;
    private JComboBox jComboBox;

    ArrayList<String> cnic1 = new ArrayList<>();

    String dAge = null;
    String dName = null;
    String dUsername = null;
    String dPassword = null;
    String dContact = null;
    String dUni = null;
    String dRoom = null;


    private JButton clear;
    private JButton add;
    private JButton back;

    public UpdateStudent2(int selectedCnic) {

        panel = new JPanel(null);

        frame = new JFrame("Add Student");
        panel.setBackground(Color.orange);

        addStd = new JLabel("Update Student");
        stdAge = new JLabel("Age:");
        stdName = new JLabel("Name:");
        stdDob = new JLabel("Date of Birth:");
        stdUsername = new JLabel("Username:");
        stdPassword = new JLabel("Password:");
        stdContact = new JLabel("Contact:");
        stdUniversity = new JLabel("University:");
        stdRoom = new JLabel("Room No:");

        t_stdAge = new JTextField();
        t_stdName = new JTextField();
        t_stdDob = new JTextField();
        t_stdUsername = new JTextField();
        t_stdPassword = new JTextField();
        t_stdContact = new JTextField();
        t_stdUniversity = new JTextField();
        t_stdRoom = new JTextField();

        addStd.setFont(new Font("Sanserif", Font.BOLD, 30));
        stdAge.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdName.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdDob.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdUsername.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdPassword.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdContact.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdUniversity.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdRoom.setFont(new Font("Sanserif", Font.BOLD, 20));

        addStd.setForeground(Color.BLACK);//Text color
        stdAge.setForeground(Color.BLACK);
        stdName.setForeground(Color.BLACK);
        stdDob.setForeground(Color.BLACK);
        stdUsername.setForeground(Color.BLACK);
        stdPassword.setForeground(Color.BLACK);
        stdContact.setForeground(Color.BLACK);
        stdUniversity.setForeground(Color.BLACK);
        stdRoom.setForeground(Color.BLACK);

        addStd.setBounds(190, 40, 200, 60);
        stdAge.setBounds(120, 150, 250, 30);
        stdName.setBounds(120, 190, 250, 30);
        stdDob.setBounds(120, 230, 250, 30);
        stdUsername.setBounds(120, 270, 250, 30);
        stdPassword.setBounds(120, 310, 250, 30);
        stdContact.setBounds(120, 350, 250, 30);
        stdUniversity.setBounds(120, 390, 250, 30);
        stdRoom.setBounds(120, 430, 250, 30);

        t_stdAge.setBounds(330, 150, 130, 30);
        t_stdName.setBounds(330, 190, 130, 30);
        t_stdDob.setBounds(330, 230, 130, 30);
        t_stdUsername.setBounds(330, 270, 130, 30);
        t_stdPassword.setBounds(330, 310, 130, 30);
        t_stdContact.setBounds(330, 350, 130, 30);
        t_stdUniversity.setBounds(330, 390, 130, 30);
        t_stdRoom.setBounds(330, 430, 130, 30);

        t_stdAge.setBackground(Color.white);
        t_stdName.setBackground(Color.white);

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



        panel.add(addStd);
        panel.add(stdAge);
        panel.add(stdName);
        panel.add(stdUsername);
        panel.add(stdPassword);
        panel.add(stdContact);
        panel.add(stdUniversity);
        panel.add(stdRoom);
        panel.add(clear);
        panel.add(back);
        panel.add(t_stdName);
        panel.add(t_stdAge);
        panel.add(t_stdUsername);
        panel.add(t_stdPassword);
        panel.add(t_stdContact);
        panel.add(t_stdUniversity);
        panel.add(t_stdRoom);
        panel.add(add);

        frame.add(panel);
        frame.setTitle("Add Student");
        frame.setVisible(true);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        sCnic = selectedCnic;


        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //Connecting to the database
        MongoDatabase database = mongo.getDatabase("myDatabase");
        //Creating a collection object
        MongoCollection <Document> collection = database.getCollection("students");

        Document query = new Document("CNIC", sCnic);

        MongoIterable<Document> docs = collection.find(query);

        MongoCursor<Document> it = docs.iterator();


        while (it.hasNext()) {

            Document doc = it.next();

            int r = Integer.valueOf(doc.getInteger("room").toString());
            t_stdRoom.setText(Integer.toString(r));

            int a = Integer.valueOf(doc.getInteger("age").toString());
            t_stdAge.setText(Integer.toString(a));

            int c = Integer.valueOf(doc.getInteger("contact").toString());
            t_stdContact.setText(Integer.toString(c));

            t_stdName.setText(doc.getString("name"));
            t_stdUsername.setText(doc.getString("username"));
            t_stdPassword.setText(doc.getString("password"));
            t_stdUniversity.setText(doc.getString("university"));
        }


        add.addActionListener(new updateEmployeeHandler());
        clear.addActionListener(new updateEmployeeHandler());
        back.addActionListener(new updateEmployeeHandler());

    }

    boolean temp = false;
    //    String usernameText=text1.getText();
    class updateEmployeeHandler implements ActionListener {
        public void actionPerformed(ActionEvent i) {
            boolean flag = true;
            if (i.getSource() == add) {
                try {
                    MongoClient mongo = new MongoClient( "localhost" , 27017 );
                    //Connecting to the database
                    MongoDatabase database = mongo.getDatabase("myDatabase");
                    //Creating a collection object
                    MongoCollection <Document> collection = database.getCollection("students");

                    Document  co=new Document();
                    co.append("CNIC",sCnic);
                    Document upd=new Document();
                    upd.append("CNIC",sCnic).append("room",t_stdRoom.getText()).append("name",t_stdName.getText()).append("university",t_stdUniversity.getText()).append("age",t_stdAge.getText()).append("username",t_stdUsername.getText()).append("password",t_stdPassword.getText()).append("contact",t_stdContact.getText());

                    UpdateOptions options = new UpdateOptions().upsert(true);
                    collection.replaceOne(co, upd,options);
                }
                catch (Exception e){
                    UpdateStudent2 updateStudent = new UpdateStudent2(sCnic);
                    frame.dispose();
                    System.out.println(e.toString());
                }
            }
            if (i.getSource() == clear) {
                UpdateStudent2 updateStudent = new UpdateStudent2(sCnic);
                frame.dispose();
            }
            if (i.getSource() == back) {
                StudentManagementGui studentManagementGui = new StudentManagementGui();
                frame.dispose();
            }
        }

    }
}
