import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Display extends JFrame implements ActionListener {
  Calculator calculator;
  JFrame frame;
  JPanel panel;
  JTextField textField;

  JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bDec, bC, bAdd, bSub, bMul, bDiv, bPer, bEq;

  private int displayWidth;
  private int displayHeight;

  public Display(int textFieldSize, int displayWidth, int displayHeight) {
    this.displayWidth = displayWidth;
    this.displayHeight = displayHeight;

    calculator = new Calculator();
    frame = new JFrame("Calculator");
    panel = new JPanel();
    textField = new JTextField(textFieldSize);

    textField.setEditable(false);

    b0 = new JButton("0");
    b1 = new JButton("1");
    b2 = new JButton("2");
    b3 = new JButton("3");
    b4 = new JButton("4");
    b5 = new JButton("5");
    b6 = new JButton("6");
    b7 = new JButton("7");
    b8 = new JButton("8");
    b9 = new JButton("9");
    bDec = new JButton(".");

    bC = new JButton("C");

    bAdd = new JButton("+");
    bSub = new JButton("-");
    bMul = new JButton("*");
    bDiv = new JButton("/");
    bPer = new JButton("%");

    bEq = new JButton("=");
  }

  public void setListeners() {
    b0.addActionListener(this);
    b1.addActionListener(this);
    b2.addActionListener(this);
    b3.addActionListener(this);
    b4.addActionListener(this);
    b5.addActionListener(this);
    b6.addActionListener(this);
    b7.addActionListener(this);
    b8.addActionListener(this);
    b9.addActionListener(this);
    bDec.addActionListener(this);

    bC.addActionListener(this);

    bAdd.addActionListener(this);
    bSub.addActionListener(this);
    bMul.addActionListener(this);
    bDiv.addActionListener(this);
    bPer.addActionListener(this);

    bEq.addActionListener(this);
  }

  public void setScreen() {
    panel.add(textField);
    panel.add(b0);
    panel.add(b1);
    panel.add(b2);
    panel.add(b3);
    panel.add(b4);
    panel.add(b5);
    panel.add(b6);
    panel.add(b7);
    panel.add(b8);
    panel.add(b9);

    panel.add(bC);

    panel.add(bAdd);
    panel.add(bSub);
    panel.add(bMul);
    panel.add(bDec);
    panel.add(bPer);

    panel.add(bEq);

    panel.setBackground(Color.PINK);
    frame.add(panel);
    frame.setSize(displayWidth, displayHeight);
  }

  public void show() {
    frame.show();
  }

  public void actionPerformed(ActionEvent e) {
    String s = e.getActionCommand();
    String output = calculator.handleInput(s);

    textField.setText(output);
  }

  public static void main(String[] args) {
    Display display = new Display(20, 300, 300);

    display.setListeners();
    display.setScreen();

    display.show();
  }
}
