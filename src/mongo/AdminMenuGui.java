package mongo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminMenuGui {
    private JFrame frame;
    private JPanel panel;
    private JLabel adminMenu;

    private JButton admin;
    private JButton employee;
    private JButton logout;
    private JButton student;
    private JButton hostel;
    private JButton empC;
    private JButton stdC;

    public AdminMenuGui() {

        panel = new JPanel(null);

        frame = new JFrame("Admin Menu");
        panel.setBackground(Color.orange);

        adminMenu = new JLabel("Admin Menu");
        admin = new JButton("Notifications");
        student = new JButton("Student Management");
        employee = new JButton("Employee Management");
        hostel = new JButton("Hostel Management");
        logout = new JButton("Logout");
        empC = new JButton("Employee Complains");
        stdC = new JButton("Student Complains");


        adminMenu.setFont(new Font("Sanserif", Font.BOLD, 30));
        admin.setFont(new Font("Sanserif", Font.BOLD, 20));
        student.setFont(new Font("Sanserif", Font.BOLD, 20));
        employee.setFont(new Font("Sanserif", Font.BOLD, 20));
        hostel.setFont(new Font("Sanserif", Font.BOLD, 20));
        logout.setFont(new Font("Sanserif", Font.BOLD, 20));
        empC.setFont(new Font("Sanserif", Font.BOLD, 20));
        stdC.setFont(new Font("Sanserif", Font.BOLD, 20));


        adminMenu.setForeground(Color.BLACK);
        admin.setForeground(Color.orange);
        student.setForeground(Color.orange);
        employee.setForeground(Color.orange);
        hostel.setForeground(Color.orange);
        logout.setForeground(Color.orange);
        empC.setForeground(Color.orange);
        stdC.setForeground(Color.orange);


        adminMenu.setBackground(Color.green);
        admin.setBackground(Color.black);
        student.setBackground(Color.black);
        employee.setBackground(Color.black);
        hostel.setBackground(Color.black);
        logout.setBackground(Color.black);
        empC.setBackground(Color.black);
        stdC.setBackground(Color.black);

        adminMenu.setBounds(200, 50, 350, 50);
        admin.setBounds(125, 150, 350, 50);
        student.setBounds(125, 230, 350, 50);
        employee.setBounds(125, 310, 350, 50);
        hostel.setBounds(125, 390, 350, 50);
        empC.setBounds(125, 460, 350, 50);
        stdC.setBounds(125, 530, 350, 50);
        logout.setBounds(175, 600, 250, 50);

        admin.setFocusable(false);
        student.setFocusable(false);
        employee.setFocusable(false);
        hostel.setFocusable(false);
        empC.setFocusable(false);
        stdC.setFocusable(false);
        logout.setFocusable(false);


        panel.add(adminMenu);
        panel.add(student);
        panel.add(employee);
        panel.add(logout);
        panel.add(hostel);
        panel.add(empC);
        panel.add(stdC);
        panel.add(admin);

        frame.add(panel);
        frame.setTitle("Main Menu");
        frame.setVisible(true);
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        admin.addActionListener(new AdminguiHandler());
        employee.addActionListener(new AdminguiHandler());
        student.addActionListener(new AdminguiHandler());
        logout.addActionListener(new AdminguiHandler());
        hostel.addActionListener(new AdminguiHandler());
        empC.addActionListener(new AdminguiHandler());
        stdC.addActionListener(new AdminguiHandler());
    }

    class AdminguiHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            if (e.getSource() == admin) {
//                frame.dispose();
//Notification g=new Notification();
//            }
//
           if (e.getSource() == student) {
                frame.dispose();
            StudentManagementGui h=new StudentManagementGui ();

            }
//
//            else if (e.getSource() == employee) {
//
//            }
//
            else if (e.getSource() == hostel) {
                frame.dispose();
                HostelInterface hostelInterface = new HostelInterface();
            }
//
            else if  (e.getSource() == logout) {
                frame.dispose();
                WelcomeGui kk=new WelcomeGui();

            }
//            else if  (e.getSource() == empC) {
//                frame.dispose();
//                ViewComplaintsEmp complaintsEmp = new ViewComplaintsEmp();
//                complaintsEmp.viewComplaintsEmpGui();;
//
//            }
//            else if  (e.getSource() == stdC) {
//                frame.dispose();
//                ViewComplaintsStd viewComplaintsStd = new ViewComplaintsStd();
//                viewComplaintsStd.viewComplaintsStdGui();
//            }
        }
    }
}
