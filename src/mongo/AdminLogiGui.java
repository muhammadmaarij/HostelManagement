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

public class AdminLogiGui extends JFrame{
    JLabel l2, l3;
    JButton l1;
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

    public AdminLogiGui () {
        border1=BorderFactory.createLineBorder(Color.red,3);
        Border border=BorderFactory.createLineBorder ( new Color ( 0,0,0 ),10);
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


        l2 =new JLabel ( "Admin Login" );
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
        frame.add(label);


        l1.addActionListener ( new LoginHandler ());
    }
    public boolean userExists(String email, String password,MongoClient k) {
        DBObject query = new BasicDBObject("username", email).append("password", password);
        return k.getDB("myDatabase").getCollection("hostel").findOne(query) != null;
    }
    public class LoginHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==l1){
                String q="";
                String dName = "";
                String dPassword = "";
                String name = Studentuser.getText().trim ();;
                String password = t2.getText();;

                try{
                    mongo = new MongoClient( "localhost" , 27017 );
                    MongoDatabase database = mongo.getDatabase("myDatabase");
                    MongoCollection mycol = database.getCollection("hostel");
                    if(userExists(Studentuser.getText().trim(),t2.getText(),mongo)){
                        AdminMenuGui k=new AdminMenuGui();
                        frame.dispose();
                        //MongoCollection mycoll = database.getCollection("students");


                    }

                }
                catch(Exception ee){
                    System.out.println(ee.toString());
                }
//                try{
//                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","prooject","prooject");
//                    Statement st = con.createStatement();
//                    String SQL = "SELECT * FROM person,student,occupied where person.cnic=occupied.student_cnic and person.cnic=student.cnic and username='" + name + "' and password='" + password+ "'";
//                    ResultSet result = st.executeQuery(SQL);
//                    while (result.next()) {
//
//                        dName = result.getString("username").trim ();
//                        dPassword = result.getString("password").trim ();
//                        iuniversity= result.getString ( "university" ).toUpperCase( Locale.ROOT );
//                        iroom=result.getInt ( "Rooms_RoomNo" );
//                        iname=result.getString ( "name" );
//                        icnic=result.getInt ( "cnic" );
//
//
//                    }
//                    System.out.println (name+password );
//                    if (name.equals(dName) && password.equals(dPassword)) {
//                        String qw="Select * from receives,Notifications where receives.notifications_notificationid=notifications.notificationid and receives.student_cnic="+icnic+"";
//                        ResultSet resultt = st.executeQuery(qw);
//                        while(resultt.next ()){
//                           q=resultt.getString ( 3 ).trim ().toLowerCase( Locale.ROOT );
////                            System.out.println (resultt.getString ( 3 ).trim ().toLowerCase( Locale.ROOT ) );
//                            //System.out.println (resultt.getInt ( 1 )+"   "+resultt.getInt ( 2 )+resultt.getString ( 3 ) );
//                            if(q.equals ( b )){
//                                bor=true;
//                            }
//                        }
//                       //StudentInterface k=new StudentInterface (iuniversity,iroom,iname,icnic,bor);
//
//                    } else {
//                        JOptionPane.showMessageDialog(null,"Incorrect Username or Password\nEnter credentials again");
//                        Studentuser.setText("");
//                        t2.setText("");
//                    }
//
//                }
//                catch (Exception e1){
//                    System.out.println(e1.toString());
//                }
            }
        }
    }
}
