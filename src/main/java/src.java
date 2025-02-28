import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentManagementSystem {
    private static final Logger logger = LoggerFactory.getLogger(StudentManagementSystem.class);
    
    private JFrame frame;
    private JTextField nameField, rollField, dobField, addressField, phoneField;
    private Connection conn;

    public StudentManagementSystem() {
        logger.info("Initializing Student Management System...");
        connectToDatabase();
        initializeUI();
    }

    private void connectToDatabase() {
        try {
            logger.info("Connecting to database...");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db", "root", "password");
            logger.info("Database connection successful.");
        } catch (SQLException e) {
            logger.error("Database Connection Failed", e);
            JOptionPane.showMessageDialog(null, "Database Connection Failed");
        }
    }

    private void initializeUI() {
        logger.info("Setting up UI...");
        
        frame = new JFrame("Student Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        frame.add(rollField);

        frame.add(new JLabel("DOB (YYYY-MM-DD):"));
        dobField = new JTextField();
        frame.add(dobField);

        frame.add(new JLabel("Address:"));
        addressField = new JTextField();
        frame.add(addressField);

        frame.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        frame.add(phoneField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> addStudent());
        frame.add(addButton);

        frame.setVisible(true);
        logger.info("UI setup complete.");
    }

    private void addStudent() {
        try {
            String sql = "INSERT INTO students (name, roll_no, dob, address, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            String name = nameField.getText();
            String rollNo = rollField.getText();
            String dob = dobField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();

            logger.info("Adding student: Name={}, Roll No={}, DOB={}, Address={}, Phone={}", name, rollNo, dob, address, phone);
            
            pstmt.setString(1, name);
            pstmt.setString(2, rollNo);
            pstmt.setString(3, dob);
            pstmt.setString(4, address);
            pstmt.setString(5, phone);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(frame, "Student Added Successfully");
            logger.info("Student added successfully.");
        } catch (SQLException e) {
            logger.error("Error adding student", e);
            JOptionPane.showMessageDialog(frame, "Error Adding Student");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentManagementSystem::new);
    }
}
