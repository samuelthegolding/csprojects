import javax.swing.*;
import java.awt.*;

public class CalcGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalcGui::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calc");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,800);

        JTextField num1Field = new JTextField(10);
        JTextField num2Field = new JTextField(10);

        String[] operations = {"+", "-", "*", "/", "^", "log"};
        JComboBox<String> operationBox = new JComboBox<>(operations);

        JLabel resultLabel = new JLabel("Result: ");
        JButton calcButton = new JButton("Calculate");

        BasicCalculator calc = new BasicCalculator();
        calcButton.addActionListener(e -> {
            try{
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());
                String op = (String) operationBox.getSelectedItem();

                assert op != null;
                calc.driver(num1,num2,op);
                resultLabel.setText("Result: " + calc.result);
            }
            catch(NumberFormatException ex){
                resultLabel.setText("Error: " + ex.getMessage());
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("First Number:"));
        panel.add(num1Field);
        panel.add(new JLabel("Second Number:"));
        panel.add(num2Field);
        panel.add(new JLabel("Operation:"));
        panel.add(operationBox);
        panel.add(calcButton);
        panel.add(resultLabel);

        frame.add(panel);
        frame.setVisible(true);
    }
}
