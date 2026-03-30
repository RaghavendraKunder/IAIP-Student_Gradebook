import javax.swing.*;
import java.awt.*;
import java.util.*;

class Student {
    int id;
    String name;
    HashMap<String, Integer> subjects;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
        subjects = new HashMap<>();
    }

    void addSubject(String subject, int marks) {
        subjects.put(subject, marks);
    }

    double getAverage() {
        if (subjects.isEmpty()) return 0;
        int sum = 0;
        for (int m : subjects.values()) sum += m;
        return (double) sum / subjects.size();
    }

    String getReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Name: ").append(name).append("\n");
        sb.append("Subjects:\n");

        for (Map.Entry<String, Integer> e : subjects.entrySet()) {
            sb.append("  ").append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }

        sb.append("Average: ").append(String.format("%.2f", getAverage())).append("\n");
        return sb.toString();
    }
}

public class GradebookSystem extends JFrame {

    ArrayList<Student> students = new ArrayList<>();

    JTextField idField, nameField, subjectField, marksField;
    JTextArea outputArea;

    public GradebookSystem() {
        setTitle("📘 Student Gradebook");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // ===== COLORS =====
        Color bgColor = new Color(240, 248, 255); // light blue
        Color panelColor = new Color(220, 235, 250);
        Color buttonColor = new Color(70, 130, 180); // steel blue
        Color textColor = Color.WHITE;

        getContentPane().setBackground(bgColor);

        // ===== TITLE =====
        JLabel title = new JLabel("Student Gradebook System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setOpaque(true);
        title.setBackground(new Color(100, 149, 237));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        centerPanel.setBackground(bgColor);

        // INPUT PANEL
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(panelColor);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Details"));

        idField = new JTextField();
        nameField = new JTextField();
        subjectField = new JTextField();
        marksField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(subjectField);
        inputPanel.add(new JLabel("Marks:"));
        inputPanel.add(marksField);

        // BUTTON PANEL
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBackground(panelColor);
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addStudentBtn = createStyledButton("Add Student", buttonColor, textColor);
        JButton addGradeBtn = createStyledButton("Add Grade", buttonColor, textColor);
        JButton viewBtn = createStyledButton("View Student", buttonColor, textColor);
        JButton deleteBtn = createStyledButton("Delete Student", buttonColor, textColor);
        JButton viewAllBtn = createStyledButton("View All", buttonColor, textColor);

        buttonPanel.add(addStudentBtn);
        buttonPanel.add(addGradeBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(viewAllBtn);

        centerPanel.add(inputPanel);
        centerPanel.add(buttonPanel);
        add(centerPanel, BorderLayout.CENTER);

        // ===== OUTPUT =====
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        outputArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        add(scrollPane, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        addStudentBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();

                students.add(new Student(id, name));
                outputArea.setText("✅ Student added successfully!");
            } catch (Exception ex) {
                outputArea.setText("❌ Invalid input!");
            }
        });

        addGradeBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String subject = subjectField.getText();
                int marks = Integer.parseInt(marksField.getText());

                Student s = findStudent(id);

                if (s != null) {
                    s.addSubject(subject, marks);
                    outputArea.setText("✅ Grade added!");
                } else {
                    outputArea.setText("❌ Student not found!");
                }
            } catch (Exception ex) {
                outputArea.setText("❌ Invalid input!");
            }
        });

        viewBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Student s = findStudent(id);

                if (s != null) {
                    outputArea.setText(s.getReport());
                } else {
                    outputArea.setText("❌ Student not found!");
                }
            } catch (Exception ex) {
                outputArea.setText("❌ Enter valid ID!");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Student s = findStudent(id);

                if (s != null) {
                    students.remove(s);
                    outputArea.setText("🗑️ Student deleted!");
                } else {
                    outputArea.setText("❌ Student not found!");
                }
            } catch (Exception ex) {
                outputArea.setText("❌ Enter valid ID!");
            }
        });

        viewAllBtn.addActionListener(e -> {
            if (students.isEmpty()) {
                outputArea.setText("⚠️ No students available!");
                return;
            }

            StringBuilder sb = new StringBuilder();
            for (Student s : students) {
                sb.append(s.getReport()).append("\n-----------------\n");
            }

            outputArea.setText(sb.toString());
        });

        setVisible(true);
    }

    JButton createStyledButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        return btn;
    }

    Student findStudent(int id) {
        for (Student s : students) {
            if (s.id == id) return s;
        }
        return null;
    }

    public static void main(String[] args) {
        new GradebookSystem();
    }
}