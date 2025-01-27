import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

 class Student {
    private String id;
    private String name;
    private List<Course> courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    // Getters and setters for id, name, and courses
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

class Course {
    private String name;
    private int credits;
    private char grade;

    public Course(String name, int credits, char grade) {
        this.name = name;
        this.credits = credits;
        this.grade = grade;
    }

    // Getters and setters for name, credits, and grade
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
}

public class CGPACalculator extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JButton addCourseButton;
    private JButton calculateCGPAButton;
    private JTable courseTable;
    private JScrollPane courseScrollPane;
    private DefaultTableModel tableModel;

    public CGPACalculator() {
        // Set up the GUI components
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 2));
        topPanel.add(new JLabel("ID:"));
        idField = new JTextField(10);
        topPanel.add(idField);
        topPanel.add(new JLabel("Name:"));
        nameField = new JTextField(20);
        topPanel.add(nameField);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        addCourseButton = new JButton("Add Course");
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });
        centerPanel.add(addCourseButton, BorderLayout.NORTH);

        String[] columnNames = {"Course Name", "Credits", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        courseTable = new JTable(tableModel);
        courseScrollPane = new JScrollPane(courseTable);
        centerPanel.add(courseScrollPane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        calculateCGPAButton = new JButton("Calculate CGPA");
        calculateCGPAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateCGPA();
            }
        });
        add(calculateCGPAButton, BorderLayout.SOUTH);

        setTitle("CGPA Calculator");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addCourse() {
        String courseName = JOptionPane.showInputDialog("Enter Course Name:");
        int credits = Integer.parseInt(JOptionPane.showInputDialog("Enter Credits:"));
        char grade = JOptionPane.showInputDialog("Enter Grade (A, B, C, etc.):").charAt(0);

        // Add course to the table
        Object[] rowData = {courseName, credits, grade};
        tableModel.addRow(rowData);
    }

    private void calculateCGPA() {
        double totalCredits = 0;
        double totalGradePoints = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int credits = (int) tableModel.getValueAt(i, 1);
            char grade = (char) tableModel.getValueAt(i, 2);

            totalCredits += credits;
            totalGradePoints += getGradePoint(grade) * credits;
        }

        double cgpa = totalGradePoints / totalCredits;
        JOptionPane.showMessageDialog(null, "CGPA: " + cgpa);
    }

    // Helper method to get grade points based on grades
    private double getGradePoint(char grade) {
        switch (grade) {
            case 'A':
                return 4.0;
            case 'B':
                return 3.0;
            case 'C':
                return 2.0;
            // Add more cases for other grades as needed
            default:
                return 0.0;
        }
    }

    public static void main(String[] args) {
        new CGPACalculator();
    }
}
