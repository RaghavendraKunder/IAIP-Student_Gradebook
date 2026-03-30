import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        if (subjects.size() == 0) return 0;
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
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }

        sb.append("Average: ").append(getAverage()).append("\n");
        return sb.toString();
    }
}

public class GradebookSystem extends JFrame {

    ArrayList<Student> students = new ArrayList<>();

    JTextField idField, nameField, subjectField, marksField;
    JTextArea outputArea;

    public GradebookSystem() {
        setTitle("Student Gradebook");
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        idField = new JTextField(10);
        nameField = new JTextField(10);
        subjectField = new JTextField(10);
        marksField = new JTextField(5);

        JButton addStudentBtn = new JButton("Add Student");
        JButton addGradeBtn = new JButton("Add Grade");
        JButton viewBtn = new JButton("View Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton viewAllBtn = new JButton("View All");

        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);

        add(new JLabel("ID:")); add(idField);
        add(new JLabel("Name:")); add(nameField);
        add(addStudentBtn);

        add(new JLabel("Subject:")); add(subjectField);
        add(new JLabel("Marks:")); add(marksField);
        add(addGradeBtn);

        add(viewBtn);
        add(deleteBtn);
        add(viewAllBtn);

        add(new JScrollPane(outputArea));

        // BUTTON ACTIONS

        addStudentBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();

                students.add(new Student(id, name));
                outputArea.setText("Student added successfully!");
            } catch (Exception ex) {
                outputArea.setText("Invalid input!");
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
                    outputArea.setText("Grade added!");
                } else {
                    outputArea.setText("Student not found!");
                }
            } catch (Exception ex) {
                outputArea.setText("Invalid input!");
            }
        });

        viewBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Student s = findStudent(id);

            if (s != null) {
                outputArea.setText(s.getReport());
            } else {
                outputArea.setText("Student not found!");
            }
        });

        deleteBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            Student s = findStudent(id);

            if (s != null) {
                students.remove(s);
                outputArea.setText("Student deleted!");
            } else {
                outputArea.setText("Student not found!");
            }
        });

        viewAllBtn.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();

            for (Student s : students) {
                sb.append(s.getReport()).append("\n----------------\n");
            }

            outputArea.setText(sb.toString());
        });

        setVisible(true);
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