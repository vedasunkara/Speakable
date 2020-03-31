public class Calculator {
  private String arg1;
  private String arg2;
  private String op;

  private Functions functions;

  public Calculator() {
    arg1 = "";
    arg2 = "";
    op = "";

    functions = new Functions();
  }

  // input: (char) update for arg1 or arg2
  // update arg2 if an operation has been defined, otherwise update arg1
  // output: None
  public void updateArgumentValues(char update) {
    if(op.equals("")) {
      arg1 += update;
    } else {
      arg2 += update;
    }
  }

  // clear instance values
  public void clear() {
    arg1 = "";
    arg2 = "";
    op = "";
  }

  // input: None
  // calculate result
  // output: (Double) the result of the stored operation
  //         0.0 if operation undefined
  public double calculateResult() {
    try {
      Double arg1_double = Double.parseDouble(arg1);
      Double arg2_double = Double.parseDouble(arg2);

      if(op.equals("")) {
        return arg1_double;
      } else if(op.equals("+")) {
        return this.functions.add(arg1_double, arg2_double);
      } else if(op.equals("-")) {
        return this.functions.subtract(arg1_double, arg2_double);
      } else if(op.equals("*")) {
        return this.functions.multiply(arg1_double, arg2_double);
      } else if(op.equals("/")) {
        return this.functions.divide(arg1_double, arg2_double);
      } else if(op.equals("%")) {
        return this.functions.percentage(arg1_double, arg2_double);
      }
    } catch (java.lang.NumberFormatException e) {
      System.out.println("NUMBER FORMAT EXCEPTION");
    }

    return 0.0;
  }

  // input: (String) raw input from calculator button
  // output: (String) output to calculator text display
  public String handleInput(String raw_input) {
    char input = raw_input.charAt(0);

    // handle numeric input (digits and decimal point)
    if(input >= '0' && input <= '9' || input == '.') {
      updateArgumentValues(input);

      return arg1 + op + arg2;
    }

    // handle 'C' (clear) command
    else if(input == 'C') {
      clear();

      return "";
    }

    // handle '=' command
    else if(input == '=') {
      double total = calculateResult();

      arg1 = Double.toString(total);
      arg2 = "";
      op = "";

      return arg1;
    }

    // handle operands
    else {
      // always apply the most recent operand
      if(op.equals("") || arg2.equals("")) {
        op = raw_input;
      } else {
        // aggregate expressions
        double result = calculateResult();
        arg1 = Double.toString(result);
        op = raw_input;
        arg2 = "";
      }

      return arg1;
    }
  }

}
