package src;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SimpleCalculation extends JFrame {

   private String expression = "";
   private JPanel textPanel, buttonPanel;
   protected JTextField textField;
   protected JLabel resultLabel;
   private JButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, 
         button_Ans, button_DEL, button_AC, button_dot, button_addition, button_subtraction, button_multiplication, 
         button_division, button_openParenthesis, button_closeParenthesis;

   public void setExpression(String expression) {
      this.expression = expression;
   }

   public String getExpression() {
      return this.expression;
   }

   public SimpleCalculation() {
      this.setTitle("Simple Calculator");
      this.setSize(600, 550);
      this.setLocationRelativeTo(null);

      Color accentColor = new Color(252, 115, 3);
      Font displayFont = new Font("Arial", ABORT, 40);

      textPanel = new JPanel();
      textField = new JTextField();
      textField.setFont(displayFont);
      textField.setSize(600, 50);
      resultLabel = new JLabel("= ");
      resultLabel.setFont(displayFont);
      resultLabel.setSize(600, 50);
      resultLabel.setBackground(Color.WHITE);
      textPanel.setLayout(new BorderLayout());
      textPanel.add(textField, BorderLayout.NORTH);
      textPanel.add(resultLabel, BorderLayout.SOUTH);

      buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(4, 5));
      button_0 = new JButton("0");
      button_0.setFont(displayFont);
      button_1 = new JButton("1");
      button_1.setFont(displayFont);
      button_2 = new JButton("2");
      button_2.setFont(displayFont);
      button_3 = new JButton("3");
      button_3.setFont(displayFont);
      button_4 = new JButton("4");
      button_4.setFont(displayFont);
      button_5 = new JButton("5");
      button_5.setFont(displayFont);
      button_6 = new JButton("6");
      button_6.setFont(displayFont);
      button_7 = new JButton("7");
      button_7.setFont(displayFont);
      button_8 = new JButton("8");
      button_8.setFont(displayFont);
      button_9 = new JButton("9");
      button_9.setFont(displayFont);
      button_AC = new JButton("AC");
      button_AC.setFont(displayFont);
      button_AC.setBackground(accentColor);
      button_dot = new JButton(".");
      button_dot.setFont(displayFont);
      button_DEL = new JButton("DEL");
      button_DEL.setFont(displayFont);
      button_DEL.setBackground(accentColor);
      button_Ans = new JButton("Ans");
      button_Ans.setFont(displayFont);
      button_openParenthesis = new JButton("(");
      button_openParenthesis.setFont(displayFont);
      button_closeParenthesis = new JButton(")");
      button_closeParenthesis.setFont(displayFont);
      button_division = new JButton(" / ");
      button_division.setFont(displayFont);
      button_addition = new JButton(" + ");
      button_addition.setFont(displayFont);
      button_subtraction = new JButton(" - ");
      button_subtraction.setFont(displayFont);
      button_multiplication = new JButton(" * ");
      button_multiplication.setFont(displayFont);

      Controller actionListener = new Controller(this);
      button_0.addActionListener(actionListener);
      button_1.addActionListener(actionListener);
      button_2.addActionListener(actionListener);
      button_3.addActionListener(actionListener);
      button_4.addActionListener(actionListener);
      button_5.addActionListener(actionListener);
      button_6.addActionListener(actionListener);
      button_7.addActionListener(actionListener);
      button_8.addActionListener(actionListener);
      button_9.addActionListener(actionListener);
      button_dot.addActionListener(actionListener);
      button_division.addActionListener(actionListener);
      button_multiplication.addActionListener(actionListener);
      button_addition.addActionListener(actionListener);
      button_subtraction.addActionListener(actionListener);
      button_openParenthesis.addActionListener(actionListener);
      button_closeParenthesis.addActionListener(actionListener);
      button_AC.addActionListener(actionListener);
      button_DEL.addActionListener(actionListener);
      button_Ans.addActionListener(actionListener);

      buttonPanel.add(button_7);
      buttonPanel.add(button_8);
      buttonPanel.add(button_9);
      buttonPanel.add(button_DEL);
      buttonPanel.add(button_AC);
      buttonPanel.add(button_4);
      buttonPanel.add(button_5);
      buttonPanel.add(button_6);
      buttonPanel.add(button_multiplication);
      buttonPanel.add(button_division);
      buttonPanel.add(button_1);
      buttonPanel.add(button_2);
      buttonPanel.add(button_3);
      buttonPanel.add(button_addition);
      buttonPanel.add(button_subtraction);
      buttonPanel.add(button_0);
      buttonPanel.add(button_dot);
      buttonPanel.add(button_Ans);
      buttonPanel.add(button_openParenthesis);
      buttonPanel.add(button_closeParenthesis);

      this.setLayout(new BorderLayout());
      this.add(textPanel, BorderLayout.NORTH);
      this.add(buttonPanel, BorderLayout.CENTER);

      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public String result(String expression) {
      if (expression.isEmpty()) {
         return "";
      }
      StringBuilder expressionBuilder = new StringBuilder(expression);
      if (expression.contains("(") || expression.contains(")")) {
         ArrayList<Integer> openParenPositions = new ArrayList<>();
         ArrayList<Integer> closeParenPositions = new ArrayList<>();
         char[] chars = expression.toCharArray();
         int length = chars.length;
         for (int i = 0; i < length; ++i) {
            if (chars[i] == '(') {
               openParenPositions.add(i);
            } else if (chars[i] == ')') {
               closeParenPositions.add(i);
            }
         }
         if (openParenPositions.size() != closeParenPositions.size()) {
            return "";
         }
         String innerExpression, calculatedResult;
         int openPos, closePos, posIndex = 1, spaceDiff, beforeLength, afterLength;
         openParenPositions.add(Integer.MAX_VALUE);
         while (openParenPositions.size() > 1) {
            closePos = closeParenPositions.get(0);
            openPos = openParenPositions.get(posIndex - 1);
            if (closePos < openParenPositions.get(posIndex)) {
               innerExpression = expressionBuilder.substring(openPos + 1, closePos);
               calculatedResult = this.calculate(innerExpression);
               beforeLength = innerExpression.length();
               afterLength = calculatedResult.length();
               spaceDiff = beforeLength - afterLength + 2;
               for (int i = spaceDiff; i > 0; --i) {
                  calculatedResult += " ";
               }
               expressionBuilder.replace(openPos, closePos + 1, calculatedResult);
               openParenPositions.remove(posIndex - 1);
               closeParenPositions.remove(0);
               posIndex = 1;
            } else {
               ++posIndex;
            }
         }
      }
      expression = expressionBuilder.toString();
      expression = this.calculate(expression);
      try {
         if (Float.parseFloat(expression.substring(expression.indexOf(".") + 1)) == 0) {
            return expression.substring(0, expression.indexOf("."));
         }
      } catch (NumberFormatException e) {
      }
      return expression;
   }

   public String calculateAddition(ArrayList<String> terms) {
      float result = 0;
      for (String term : terms)
         try {
            result += Float.parseFloat(term);
         } catch (NumberFormatException e) {
         }
      return result + "";
   }

   public String calculateMultiplicationDivision(String expression) {
      String[] tokens = expression.trim().split(" ");
      String operator;
      int tokenCount = tokens.length;
      float result = 0;
      try {
         result = Float.parseFloat(tokens[0]);
      } catch (NumberFormatException e) {
      }
      int index = 1;
      while (index < tokenCount) {
         if (index == tokenCount - 1) {
            return result + "";
         }
         operator = tokens[index];
         switch (operator) {
            case "*" -> {
               result *= Float.parseFloat(tokens[++index]);
            }
            case "/" -> {
               float divisor = Float.parseFloat(tokens[++index]);
               if (divisor == 0) {
                  return "MATH ERROR";
               } else {
                  result /= divisor;
               }
            }
         }
         ++index;
      }
      return result + "";
   }

   public String calculate(String expression) {
      ArrayList<String> terms = new ArrayList<>();
      char[] chars = null;
      String[] tokens = null;
      if (expression.contains(" - ")) {
         chars = expression.toCharArray();
         expression = "";
         int length = chars.length;
         for (int i = 0; i < length; ++i) {
            if (chars[i] == '-' && chars[i + 1] == ' ') {
               chars[i] = '+';
               chars[i + 1] = '-';
               ++i;
            }
         }
         for (char character : chars) {
            expression += character;
         }
      }
      if (expression.contains("+")) {
         tokens = expression.split("[+]");
         for (String token : tokens) {
            terms.add(token);
         }
      } else {
         terms.add(expression);
      }

      int termCount = terms.size();
      String currentTerm;

      for (int i = 0; i < termCount; ++i) {
         currentTerm = terms.get(i);
         if (currentTerm.contains("*") || currentTerm.contains("/")) {
            terms.set(i, this.calculateMultiplicationDivision(currentTerm));
         }
         if ("MATH ERROR".equals(terms.get(i))) {
            return "MATH ERROR";
         }
      }
      return this.calculateAddition(terms);
   }

   public static void main(String[] args) {
      new SimpleCalculation();
   }
}
