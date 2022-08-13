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

public class password extends JFrame{
    JLabel changelabel, l3,a1,a2,a3;
    JButton l1 ,forget;
     JTextField newp;
     JTextField old;

    JPasswordField conf;
    Border border1;
    JFrame frame;
    MongoClient mongo;
    String z;

    public password (String a) {
        z=a;
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
        label.setBounds ( 550,10,300,300);
        frame=new JFrame (  );
        l1=new JButton ( "Change" );
        l1.setBounds ( 600,600,200,30);
        l1.setForeground ( new Color ( 255,255,255) );
        l1.setFont ( new Font("My boli",Font.BOLD,20) );
        l1.setHorizontalTextPosition ( JLabel.CENTER);
        l1.setVerticalTextPosition ( JLabel.BOTTOM );
        l1.setHorizontalAlignment ( JLabel.CENTER );
        l1.setBackground ( new Color(0,0,0) );
        l1.setOpaque ( true );
        l1.setFocusable(false);



        changelabel =new JLabel ( "Change Password " );
        changelabel.setBounds(600, 330, 250, 40);
        changelabel.setFont(new Font("My Bali", Font.BOLD, 25));
        changelabel.setForeground(new Color(2));

       
        
        

        newp=new JTextField (  );
        newp.setBounds (  500,450,400,35);
        newp.setFont ( new Font ( "My Bali",Font.BOLD,20 ) );
        a2 =new JLabel ( "New Password " );
        a2.setBounds(250, 450, 250, 40);
        a2.setFont(new Font("My Bali", Font.BOLD, 25));
        a2.setForeground(new Color(2));

        old=new JTextField (  );
        old.setBounds (  500,400,400,35);
        old.setFont ( new Font ( "My Bali",Font.BOLD,20 ) );
        a1 =new JLabel ( "Prev Password " );
        a1.setBounds(250, 400, 250, 40);
        a1.setFont(new Font("My Bali", Font.BOLD, 25));
        a1.setForeground(new Color(2));

        conf=new JPasswordField (  );
        conf.setBounds (  500,500,400,35);
        conf.setFont ( new Font ( "My Bali",Font.BOLD,20 ) );
        a3 =new JLabel ( "Confirm Password " );
        a3.setBounds(250, 500, 250, 40);
        a3.setFont(new Font("My Bali", Font.BOLD, 25));
        a3.setForeground(new Color(2));
        
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
        frame.add (newp  );
        frame.add (old );
        //frame.add (changelabel  );
        frame.add(conf);
        frame.add(a1);
        frame.add(a2);
        frame.add(a3);
        frame.add(label);


        l1.addActionListener ( new LoginHandler ());
    }
    public boolean userExists(String email, String password,MongoClient k) {
        DBObject query = new BasicDBObject("username", email).append("password", password);
        return k.getDB("myDatabase").getCollection("students").findOne(query) != null;
    }
    public class LoginHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==l1){

                try{
                    //Creating a MongoDB client
                    mongo = new MongoClient( "localhost" , 27017 );



                }
                catch(Exception ee){
                    System.out.println(ee.toString());
                }
                if(userExists(z,old.getText().trim(),mongo)){
                    System.out.println("find");
                    if(newp.getText().trim().equals(conf.getText().trim())){

                        //Connecting to the database
                        MongoDatabase database = mongo.getDatabase("myDatabase");
                        MongoCollection mycol = database.getCollection("students");
                        Document query = new Document();
                        query.append("password",old.getText().trim()).append("username",z);
                        Document setData = new Document();
                        setData.append("password",newp.getText().trim());
                        Document update = new Document();
                        update.append("$set", setData);
                        mycol.updateOne(query, update);
                       frame.dispose();
                       StudentLogiGui o=new StudentLogiGui();
                    }
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
//                        newp.setText("");
//                        conf.setText("");
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

