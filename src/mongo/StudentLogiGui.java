package mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

public class StudentLogiGui extends JFrame{
    JLabel l2, l3;
    JButton l1 ,forget;
    public JTextField Studentuser;
    JPasswordField t2;
    Border border1;
    JFrame frame;
    String iname;
    int iroom;
    String iuniversity;
    int icnic;
    String b="not";
    boolean bor=false;
    MongoClient mongo;

    public StudentLogiGui () {
        border1=BorderFactory.createLineBorder(Color.red,3);

        Border border=BorderFactory.createLineBorder ( new Color ( 0,0,0 ),10);
        Border border5=BorderFactory.createLineBorder ( new Color ( 255,255,255 ),10);
        ImageIcon image=new ImageIcon ( "download.jpg" );
        JLabel label=new JLabel ( );
        label.setText ( "HOSTEL COSTEL" );
        label.setIcon(image);
        label.setHorizontalTextPosition ( JLabel.CENTER);
        label.setVerticalTextPosition ( JLabel.BOTTOM );
        label.setForeground ( new Color ( 0,0,0) );
        label.setFont ( new Font ( "MV Boli",Font.BOLD,20 ) );
        label.setBackground ( new Color(255,255,255 ));
        label.setOpaque ( true );
        label.setBorder ( border );
        label.setHorizontalAlignment ( JLabel.CENTER );
        label.setBounds ( 550,100,300,300);
        frame=new JFrame (  );
        l1=new JButton ( "Log In" );
        l1.setBounds ( 600,600,200,30);
        l1.setForeground ( new Color ( 255,255,255) );
        l1.setFont ( new Font("My boli",Font.BOLD,20) );
        l1.setHorizontalTextPosition ( JLabel.CENTER);
        l1.setVerticalTextPosition ( JLabel.BOTTOM );
        l1.setHorizontalAlignment ( JLabel.CENTER );
        l1.setBackground ( new Color(0,0,0) );
        l1.setOpaque ( true );
        l1.setFocusable(false);

        forget=new JButton ( "Change password" );
        forget.setBounds ( 950,480,200,50);
        forget.setForeground ( new Color ( 0,0,0 ));
        forget.setFont ( new Font("My boli",Font.BOLD,20) );
        forget.setHorizontalTextPosition ( JLabel.CENTER);
        forget.setVerticalTextPosition ( JLabel.BOTTOM );
        forget.setHorizontalAlignment ( JLabel.CENTER );
        forget.setBackground ( new Color(255,255,255) );
        forget.setOpaque ( true );
        forget.setFocusable(false);
        forget.setBorder(border5);

        l2 =new JLabel ( "Student Login" );
        l2.setBounds(625, 400, 250, 40);
        l2.setFont(new Font("My Bali", Font.BOLD, 25));
        l2.setForeground(new Color(2));

        Studentuser=new JTextField (  );
        Studentuser.setBounds (  500,450,400,35);
        Studentuser.setText ( "Username" );
        Studentuser.addFocusListener ( new FocusListener( ) {
            public void focusGained( FocusEvent  event){
                if(Studentuser.getText ().equals ( "Username" )){
                    Studentuser.setText ( "" );
                    if(Studentuser.getBorder()==border1){
                        Studentuser.setBorder(null);
                    }
                }
            }
            public void focusLost(FocusEvent event){
                if(Studentuser.getText ().equals ( "" )){
                    Studentuser.setText ( "Username" );
                }
                if(Studentuser.getBorder()==border1){
                    Studentuser.setBorder(null);
                }
            }
        } );
        Studentuser.setFont ( new Font ( "My Bali",Font.BOLD,20 ) );

        t2=new JPasswordField (  );
        t2.setBounds (  500,500,400,35);
        t2.setText ( "password" );
        t2.addFocusListener ( new FocusListener( ) {
            public void focusGained( FocusEvent  event){
                if(t2.getText ().equals ( "password" )){
                    t2.setText ( "" );
                    if(t2.getBorder()==border1){
                        t2.setBorder(null);
                    }
                }
            }
            public void focusLost(FocusEvent event){
                if(t2.getText ().equals ( "" )){
                    t2.setText ( "password" );
                }
            }
        } );
        t2.setFont ( new Font ( "My Bali",Font.BOLD,20 ) );
        frame.setTitle ( "hostel" );
        frame.setSize ( 700,500 );
        //frame.setResizable ( false );
        frame.setVisible ( true );
        frame.setLayout ( null );
        frame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );
        frame.getContentPane ().setBackground ( new Color (0,0,0) );
        frame.setSize ( 1400,700 );
        frame.getContentPane ().setBackground ( new Color (255,255,255) );
        frame.add (l1  );
        frame.add (Studentuser  );
        frame.add (l2  );
        frame.add(t2);
        frame.add(forget);
        frame.add(label);


        l1.addActionListener ( new LoginHandler ());
        forget.addActionListener ( new LoginHandler ());
    }
    public boolean userExists(String email, String password,MongoClient k) {
        DBObject query = new BasicDBObject("username", email).append("password", password);
        return k.getDB("myDatabase").getCollection("students").findOne(query) != null;
    }
    public class LoginHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == l1) {
                try {
                    //Creating a MongoDB client
                    mongo = new MongoClient("localhost", 27017);
                    //Connecting to the database
                    MongoDatabase database = mongo.getDatabase("myDatabase");
                    MongoCollection mycol = database.getCollection("students");
                    if (userExists(Studentuser.getText().trim(), t2.getText().trim(), mongo)) {
                        MongoCollection mycoll = database.getCollection("students");
                        Document query = new Document("username", Studentuser.getText().trim());
                        MongoIterable<Document> docs = mycol.find(query);
                        MongoCursor<Document> it = docs.iterator();

                        while (it.hasNext()) {
                            Document doc = it.next();
                            iroom = doc.getInteger("room");
                            icnic = doc.getInteger("CNIC");
                            iname = doc.getString("name");
                            iuniversity = doc.getString("university");
                        }

                        StudentInterface k = new StudentInterface(iuniversity, iroom, iname, icnic);
                    }

                } catch (Exception ee) {
                    System.out.println(ee.toString());
                }
            } else if (e.getSource() == forget) {

                    try {

                        mongo = new MongoClient("localhost", 27017);
                        MongoDatabase database = mongo.getDatabase("myDatabase");
                        MongoCollection mycol = database.getCollection("students");
                        if (userExists(Studentuser.getText().trim(), t2.getText().trim(), mongo)) {
                            password k = new password(Studentuser.getText().trim());

                        }

                    } catch (Exception ee) {
                        System.out.println(ee.toString());
                    }

                }
            }
        }
    }
