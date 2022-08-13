package mongo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeGui {
    private JFrame frame;
    private JPanel panel;

    private JLabel welcome;
    private JLabel hms;

    private JButton student;
    private JButton admin;
    private JButton employee;

    public WelcomeGui() {

        panel = new JPanel(null);

        frame = new JFrame("Welcome");
        panel.setBackground(Color.orange);

        welcome = new JLabel("Welcome");
        hms = new JLabel("HMS");


        welcome.setFont(new Font("Sanserif", Font.BOLD, 24));
        hms.setFont(new Font("Sanserif", Font.BOLD, 32));

        welcome.setForeground(Color.BLACK);//Text color
        hms.setForeground(Color.DARK_GRAY);//Text color

        hms.setBounds(252, 20, 300, 60);
        welcome.setBounds(232, 80, 300, 60);

        admin = new JButton("Admin Console");

        employee = new JButton("Employee Console");

        student = new JButton("Student Console");

        admin.setFont(new Font("Sanserif", Font.BOLD, 14));
        employee.setFont(new Font("Sanserif", Font.BOLD, 14));
        student.setFont(new Font("Sanserif", Font.BOLD, 14));

        admin.setForeground(Color.orange);
        employee.setForeground(Color.orange);
        student.setForeground(Color.orange);
        admin.setBackground(Color.black);
        student.setBackground(Color.black);
        employee.setBackground(Color.black);

        admin.setFocusable(false);
        employee.setFocusable(false);
        student.setFocusable(false);

        admin.setBounds(170, 180, 230, 70);
        employee.setBounds(170, 290, 230, 70);
        student.setBounds(170, 400, 230, 70);

        panel.add(welcome);
        panel.add(hms);
        panel.add(student);
        panel.add(employee);
        panel.add(admin);

        frame.add(panel);
        frame.setTitle("Add Employee");
        frame.setVisible(true);
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        MyActionListener z=new MyActionListener ();
        admin.addActionListener (  z);
        student.addActionListener (  z);
        employee.addActionListener (  z);

    }
    public class MyActionListener implements ActionListener {
        public void actionPerformed( ActionEvent ae) {

            if("Employee Console".equals(ae.getActionCommand())) {
                frame.dispose();
            }
            else if("Student Console".equals(ae.getActionCommand())) {
                frame.dispose();
                StudentLogiGui aa = new StudentLogiGui ();
            }
            else if("Admin Console".equals(ae.getActionCommand())) {
                frame.dispose();
                AdminLogiGui aa = new AdminLogiGui ();
            }

        }
    }
}
