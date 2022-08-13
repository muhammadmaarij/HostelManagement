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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchStudent {
    private JFrame frame;
    private JPanel panel;

    private JLabel addEmp;
    private JLabel empCnic;

    private JButton clear;
    private JButton search;
    private JButton back;

    int icnic = 0;
    private JComboBox jComboBox;
    ArrayList<Integer> cnic1 = new ArrayList<>();

    public SearchStudent(){
        panel = new JPanel(null);

        frame = new JFrame("Add Student");
        panel.setBackground(Color.orange);

        addEmp = new JLabel("Search Student");
        empCnic = new JLabel("Cnic:");


        addEmp.setFont(new Font("Sanserif", Font.BOLD, 30));
        empCnic.setFont(new Font("Sanserif", Font.BOLD, 20));


        addEmp.setForeground(Color.BLACK);//Text color
        empCnic.setForeground(Color.BLACK);

        addEmp.setBounds(190, 40, 200, 60);
        empCnic.setBounds(120, 110, 250, 30);


        search = new JButton("Add");

        back = new JButton("Back");

        clear = new JButton("Clear");

        search.setFont(new Font("Sanserif", Font.BOLD, 20));
        back.setFont(new Font("Sanserif", Font.BOLD, 20));
        clear.setFont(new Font("Sanserif", Font.BOLD, 20));

        search.setForeground(Color.orange);
        back.setForeground(Color.orange);
        clear.setForeground(Color.orange);
        search.setBackground(Color.black);
        clear.setBackground(Color.black);
        back.setBackground(Color.black);

        search.setBounds(50, 500, 150, 50);
        back.setBounds(215, 500, 150, 50);
        clear.setBounds(380, 500, 150, 50);
//        try {
//            Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
//            Statement st = con1.createStatement();
//            ResultSet result = st.executeQuery("select cnic from student");
//            while (result.next()) {
//                cnic1.add(result.getString(1));
//            }
//
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
        try {
            //Creating a MongoDB client
            MongoClient mongo = new MongoClient("localhost", 27017);
            //Connecting to the database
            MongoDatabase database = mongo.getDatabase("myDatabase");
            MongoCollection mycol = database.getCollection("students");

//            List docs1 = (List) mycol.find(queryy).into(cnic1);


            MongoIterable<Document> docs1 = mycol.find();
            MongoCursor<Document> it2 = docs1.iterator();
            while (it2.hasNext()){

                Document document = it2.next();
                icnic = document.getInteger("CNIC");

                System.out.println(icnic);
                cnic1.add(icnic);
            }


        } catch (Exception ee) {
            System.out.println(ee.toString());
        }

        Integer unArray[] = cnic1.toArray(new Integer[cnic1.size()]);
        jComboBox = new JComboBox(unArray);
        jComboBox.setBounds(330, 110, 130, 30);
        panel.add(jComboBox);


        panel.add(addEmp);
        panel.add(empCnic);
        panel.add(clear);
        panel.add(back);
        panel.add(search);

        frame.add(panel);
        frame.setTitle("Add Student");
        frame.setVisible(true);
        frame.setSize(600, 650);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);



        search.addActionListener(new SearchStudentHandler());
        clear.addActionListener(new SearchStudentHandler());
        back.addActionListener(new SearchStudentHandler());
    }
    class SearchStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent i) {

            if (i.getSource() == search) {
                UpdateStudent2 updateEmployee11 = new UpdateStudent2(Integer.parseInt(String.valueOf(jComboBox.getSelectedItem())));
                frame.dispose();
            }
            if (i.getSource() == clear) {
                SearchStudent searchStudent = new SearchStudent();
                frame.dispose();

            }
            if (i.getSource() == back) {
                StudentManagementGui studentManagementGui = new StudentManagementGui();
                frame.dispose();
            }
        }
    }
}