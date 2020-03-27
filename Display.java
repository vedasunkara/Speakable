import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame implements ActionListener {
  private static JFrame frame;
  private static JTextField textField;

  Calculator calculator;

  public Display() {
    this.calculator = new Calculator();
    this.frame = new JFrame("Calculator");
    this.textField = new JTextField(16);

    textField.setEditable(false);
  }

  public void showDisplay() {
    frame.show();
  }

  public void actionPerformed(ActionEvent e) {}


}
