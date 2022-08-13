package mongo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseDate {
    JComboBox days;
    JComboBox months ;
    JComboBox years;
    JComboBox days1;
    JComboBox months1 ;
    JComboBox years1;
    JFrame frame;
    JPanel panel;
    JButton l1;
    JLabel label;
    JLabel label1;
    public ExpenseDate (){
    label =new JLabel ( "From Date" );
    label1 =new JLabel ( "To Date" );


        l1=new JButton ( "Submit" );
        l1.setBounds ( 100,50,100,30);
        l1.setForeground ( new Color ( 255,255,255) );
        l1.setFont ( new Font("My boli",Font.BOLD,20) );
        l1.setHorizontalTextPosition ( JLabel.CENTER);
        l1.setVerticalTextPosition ( JLabel.BOTTOM );
        l1.setHorizontalAlignment ( JLabel.CENTER );
        l1.setBackground ( new Color(0,0,0) );
        l1.setOpaque ( true );
        l1.setFocusable(false);
        String[]day ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String []month={"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
        String []year={"2022"};
        days=new JComboBox ( day );
        months=new JComboBox ( month );
        years=new JComboBox ( year );
        days1=new JComboBox ( day );
        months1=new JComboBox ( month );
        years1=new JComboBox ( year );
        panel=new JPanel (  );
        frame=new JFrame (  );
        panel.setBackground (new Color ( 0,153,153 ) );
        panel.add ( label );
        panel.add ( days );
        panel.add ( months );
        panel.add ( years);
        panel.add ( label1 );
        panel.add ( days1 );
        panel.add ( months1 );
        panel.add ( years1);
        frame.add ( l1 );
        frame.add ( panel );
        frame.setTitle ( "hostel" );
        frame.setSize ( 500,200 );
        frame.setVisible ( true );
        frame.setLayout ( null );
        frame.setDefaultCloseOperation ( WindowConstants.EXIT_ON_CLOSE );
        frame.getContentPane ().setBackground ( new Color (0,0,0) );
        frame.getContentPane ().setBackground ( new Color (2) );
        l1.addActionListener( new StudentRegistrationGuiHandler());

    }
    class StudentRegistrationGuiHandler implements ActionListener {

        @Override
        public void actionPerformed( ActionEvent e) {
            if (e.getSource() == l1) {
                if(((JTextField)days.getEditor().getEditorComponent()).getText().isEmpty ()||((JTextField)months.getEditor().getEditorComponent()).getText().isEmpty ()||((JTextField)years.getEditor().getEditorComponent()).getText().isEmpty ()||((JTextField)days1.getEditor().getEditorComponent()).getText().isEmpty ()||((JTextField)months1.getEditor().getEditorComponent()).getText().isEmpty ()||((JTextField)years1.getEditor().getEditorComponent()).getText().isEmpty ()){
                    JOptionPane.showMessageDialog ( null,"select all values" );
                    frame.dispose ();

                    ExpenseDate u=new ExpenseDate ();
                }
                else{
                    String datee= ((JTextField)days.getEditor().getEditorComponent()).getText()+"-"+((JTextField)months.getEditor().getEditorComponent()).getText()+"-"+((JTextField)years.getEditor().getEditorComponent()).getText();
                    String dateee= ((JTextField)days1.getEditor().getEditorComponent()).getText()+"-"+((JTextField)months1.getEditor().getEditorComponent()).getText()+"-"+((JTextField)years1.getEditor().getEditorComponent()).getText();
                    frame.dispose ();
                    ViewExpense h=new ViewExpense ();
                    h.viewExpensegui ();

                }

            }

            }
        }
    }

