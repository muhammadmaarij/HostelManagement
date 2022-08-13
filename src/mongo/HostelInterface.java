package mongo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostelInterface {
    private JFrame frame;
    private JPanel panel;
    private JLabel adminMenu;

    private JButton mess;
    private JButton expense;
    private JButton furniture;
    private JButton rooms;
    private JButton fees;
    private JButton salaries;
    private JButton back;

    public HostelInterface() {

        panel = new JPanel(null);

        frame = new JFrame("Admin Menu");
        panel.setBackground(new Color(0,153,153));

        adminMenu = new JLabel("Hostel Menu");
        mess = new JButton("Mess Management");
        rooms = new JButton("Rooms Management");
        expense = new JButton("Expense Management");
        fees = new JButton("Fees Management");
       salaries = new JButton("Salaries Management");
        furniture = new JButton("Furniture");
        back = new JButton("<--");


        adminMenu.setFont(new Font("Sanserif", Font.BOLD, 30));
        mess.setFont(new Font("Sanserif", Font.BOLD, 20));
        rooms.setFont(new Font("Sanserif", Font.BOLD, 20));
        expense.setFont(new Font("Sanserif", Font.BOLD, 20));
        fees.setFont(new Font("Sanserif", Font.BOLD, 20));
        salaries.setFont(new Font("Sanserif", Font.BOLD, 20));
        furniture.setFont(new Font("Sanserif", Font.BOLD, 20));
       back.setFont(new Font("Sanserif", Font.BOLD, 20));


        adminMenu.setForeground(Color.BLACK);
        mess.setForeground(Color.orange);
        rooms.setForeground(Color.orange);
        expense.setForeground(Color.orange);
        fees.setForeground(Color.orange);
        salaries.setForeground(Color.orange);
        furniture.setForeground(Color.orange);
        back.setForeground(Color.orange);


        adminMenu.setBackground(Color.green);
        mess.setBackground(Color.black);
        rooms.setBackground(Color.black);
        expense.setBackground(Color.black);
        fees.setBackground(Color.black);
        furniture.setBackground(Color.black);
        salaries.setBackground(Color.black);
       back.setBackground(Color.black);

        adminMenu.setBounds(200, 50, 350, 50);
        mess.setBounds(125, 150, 350, 50);
        rooms.setBounds(125, 230, 350, 50);
        expense.setBounds(125, 310, 350, 50);
        fees.setBounds(125, 390, 350, 50);
        salaries.setBounds(125, 460, 350, 50);
        furniture.setBounds(175, 540, 250, 50);
        back.setBounds(50, 50, 100, 50);

        mess.setFocusable(false);
        rooms.setFocusable(false);
        expense.setFocusable(false);
        fees.setFocusable(false);
        furniture.setFocusable(false);
        salaries.setFocusable(false);
        back.setFocusable(false);


        panel.add(adminMenu);
        panel.add(rooms);
        panel.add(expense);
        panel.add(furniture);
        panel.add(fees);
        panel.add(mess);
        panel.add(salaries);
        panel.add(back);

        frame.add(panel);
        frame.setTitle("Main Menu");
        frame.setVisible(true);
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        mess.addActionListener(new AdminguiHandler());
        expense.addActionListener(new AdminguiHandler());
        rooms.addActionListener(new AdminguiHandler());
        furniture.addActionListener(new AdminguiHandler());
        fees.addActionListener(new AdminguiHandler());
       salaries.addActionListener(new AdminguiHandler());
       back.addActionListener(new AdminguiHandler());
    }

    class AdminguiHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {


             if (e.getSource() == expense) {
                frame.dispose ();
              ExpenseGui exp=new ExpenseGui ();
            }



            else if  (e.getSource() == back) {
                frame.dispose();
                AdminMenuGui adminMenuGui = new AdminMenuGui();
            }
        }
    }
}
