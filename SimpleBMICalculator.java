package Prac;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleBMICalculator extends JFrame {
    private JTextField heightField, weightField, bmiField;
    private JLabel resultLabel;
    private JButton calculateButton;

    public SimpleBMICalculator() {
        setTitle("BMI Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        panel.add(new JLabel("Height (cm):"));
        heightField = new JTextField();
        panel.add(heightField);

        panel.add(new JLabel("Weight (kg):"));
        weightField = new JTextField();
        panel.add(weightField);

        panel.add(new JLabel("BMI:"));
        bmiField = new JTextField();
        bmiField.setEditable(false);
        panel.add(bmiField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        resultLabel = new JLabel();
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double height = Double.parseDouble(heightField.getText());
                double weight = Double.parseDouble(weightField.getText());

                double bmi = calculateBMI(height, weight);
                bmiField.setText(String.format("%.2f", bmi));

                displayResult(bmi);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for height and weight.");
            }
        }
    }

    private double calculateBMI(double height, double weight) {
        return weight / ((height / 100.0) * (height / 100.0));
    }

    private void displayResult(double bmi) {
        String result;
        if (bmi < 18.5) {
            result = "Underweight";
            resultLabel.setForeground(Color.BLUE);
            
        } else if (bmi < 25) {
            result = "Normal weight";
            resultLabel.setForeground(Color.GREEN);
        } else if (bmi < 30) {
            result = "Overweight";
            resultLabel.setForeground(Color.ORANGE);
        } else {
            result = "Obese";
            resultLabel.setForeground(Color.RED);
        }
        resultLabel.setText(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleBMICalculator::new);
    }
}

