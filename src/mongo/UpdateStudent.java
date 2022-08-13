package mongo;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdateStudent {

    int sCnic = 78;
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

    public UpdateStudent(int selectedCnic) {

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
//        panel.add(stdDob);
        panel.add(stdUsername);
        panel.add(stdPassword);
        panel.add(stdContact);
        panel.add(stdUniversity);
        panel.add(stdRoom);
        panel.add(clear);
        panel.add(back);
        panel.add(t_stdName);
        panel.add(t_stdAge);
//        panel.add(t_stdDob);
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

        try {
            Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "project", "project");
            Statement st = con1.createStatement();
            ResultSet result = st.executeQuery("select cnic from student");
            while (result.next()) {
                cnic1.add(result.getString(1));
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }



//        Connection con;
//        try {
//            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "project", "project");
//            java.sql.Statement st = con.createStatement();
//            String SQL = "SELECT * from student where cnic = '" + selectedCnic + "'";
//            ResultSet result = st.executeQuery(SQL);
//            while (result.next()) {
//                dUni = result.getString("university");
//
//            }
//
//            String sqlPerson = "SELECT * from person where cnic = '" + selectedCnic + "'";
//            System.out.println (sqlPerson );
//            ResultSet resultP = st.executeQuery(sqlPerson);
//            while (resultP.next()) {
//                dAge = resultP.getString("age");
//                dName = resultP.getString("name");
//                dUsername = resultP.getString("username");
//                dPassword = resultP.getString("password");
//                dContact = resultP.getString("contactNo");
//
//            }
//
//            String sqlOccupied = "SELECT * from occupied where student_cnic = '" + selectedCnic + "'";
//            ResultSet resultOccupied = st.executeQuery(sqlOccupied);
//            while (resultOccupied.next()) {
//                dRoom = resultOccupied.getString("rooms_roomNo");
//
//            }

//            String sqlStudent = "SELECT * from student where cnic = '" + selectedCnic + "'";
//            ResultSet resultStudent = st.executeQuery(sqlStudent);
//            while (resultStudent.next()) {
//                dUni = resultStudent.getString("university");
//
//            }

//            String sqlRoom = "SELECT * from room where roomNo = '" + dRoom + "'";
//            ResultSet resultRoom = st.executeQuery(sqlRoom);
//            while (result.next()) {
//                dUni = result.getString("room");
//
//            }
//            t_stdAge.setText(dAge);
//            t_stdName.setText(dName);
//            t_stdUsername.setText(dUsername);
//            t_stdContact.setText(dContact);
//            t_stdPassword.setText(dPassword);
//            t_stdUniversity.setText(dUni);
//            t_stdRoom.setText(dRoom);
//        } catch (Exception e) {
//            System.err.println(e.toString());
//        }


        sCnic = selectedCnic;

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

//                try {
//                    Connection con1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "prooject", "prooject");
//                    Statement st = con1.createStatement();
//                    ResultSet resultSetStudent = st.executeQuery("update student set university='"+ t_stdUniversity.getText()+"'where cnic='"+sCnic+"'");
//                    ResultSet resultSetPerson = st.executeQuery("update person set age='" + t_stdAge.getText() + "', Username='"+t_stdUsername.getText()+"', password='"+t_stdPassword.getText()+"', name='"+t_stdName.getText()+"', contactNo='"+t_stdContact.getText() + "'where cnic='" + sCnic + "'");
//
//                    String dAvaliablility = "";
//                    String sqlRoom = "SELECT * from rooms where roomNo = '" + t_stdRoom.getText() + "'";
//                    ResultSet result = st.executeQuery(sqlRoom);
//                    while (result.next()) {
//                        dAvaliablility = result.getString("avaliability");
//                    }
//                    if(dAvaliablility.equals("yes")){
//                        if (dRoom.equals(t_stdRoom.getText())){
//                        }
//                        else{
//                            ResultSet resultSetRoomsUpdate = st.executeQuery("update rooms set avaliability= 'yes' where roomno='"+dRoom+"'");
//                            ResultSet resultSetRoomsSame = st.executeQuery("update rooms set avaliability= 'not' where roomno='"+t_stdRoom.getText()+"'");
//                            ResultSet resultSetOccupied = st.executeQuery("update occupied set rooms_roomno='"+ t_stdRoom.getText()+"'where student_cnic='"+sCnic+"'");
//                            ResultSet resultSetStay = st.executeQuery("update stay set rooms_roomno='"+ t_stdRoom.getText()+"'where student_cnic='"+sCnic+"'");
//
//                            JOptionPane.showMessageDialog(null,"Room Updated");
//                        }
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null,"Room Already Booked");
//
//                    }
//
//                    JOptionPane.showMessageDialog(null,"Other Information Updated");
//
//                } catch (Exception e) {
//                    UpdateStudent updateStudent = new UpdateStudent(sCnic);
//                    frame.dispose();
//                    System.out.println(e.toString());
//                }
                try {
                    MongoClient mongo = new MongoClient("localhost", 27017);
                    MongoDatabase database = mongo.getDatabase("myDatabase");
                    MongoCollection<Document> collection = database.getCollection("students");

                    Document query = new Document();
                    query.append("_id",sCnic);
                    Document studentdoc = new Document("age",Integer.parseInt(t_stdAge.getText())).append("username",t_stdUsername.getText()).append("password",t_stdPassword.getText()).append("contact",Integer.parseInt(t_stdContact.getText())).append("university",t_stdUniversity.getText()).append("room",Integer.parseInt(t_stdRoom.getText())).append("name",t_stdName.getText());

                    Document update = new Document();
                    update.append("$set", studentdoc);
                    collection.updateOne(query, update);
                }
                catch (Exception e){
                    UpdateStudent updateStudent = new UpdateStudent(sCnic);
                    frame.dispose();
                    System.out.println(e.toString());
                }
            }
            if (i.getSource() == clear) {
                UpdateStudent updateStudent = new UpdateStudent(sCnic);
                frame.dispose();
            }
            if (i.getSource() == back) {
                StudentManagementGui studentManagementGui = new StudentManagementGui();
                frame.dispose();
            }
        }

    }
}
