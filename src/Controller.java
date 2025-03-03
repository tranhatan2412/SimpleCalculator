package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controller implements ActionListener {
   private SimpleCalculation calculator;
   public Controller(SimpleCalculation calculator) {
      this.calculator = calculator;
   }
   @Override
   public void actionPerformed(ActionEvent event) {
      String command = event.getActionCommand();
      if ("AC".equals(command)) {
         this.calculator.textField.setText("");
         this.calculator.resultLabel.setForeground(Color.BLACK);
         this.calculator.resultLabel.setText("=");
         this.calculator.setExpression("");
      } else {
         int length = this.calculator.getExpression().length();
         if ("DEL".equals(command)) {
            if (length > 0) {
               if (this.calculator.getExpression().charAt(length - 1) == ' ') {
                  this.calculator.setExpression(this.calculator.getExpression().substring(0, length - 3));
               } else {
                  this.calculator.setExpression(this.calculator.getExpression().substring(0, length - 1));
               }
            }
         } else if (!"Ans".equals(command)) {
            if (length == 0 && (" * ".equals(command) || " / ".equals(command))) {
               this.calculator.setExpression("");
            } else {
               this.calculator.setExpression(this.calculator.textField.getText() + command);
            }
         }
         this.calculator.textField.setText(this.calculator.getExpression());
         String expression = this.calculator.textField.getText();
         int expressionLength = expression.length();
         if (expressionLength > 0 && expression.charAt(expressionLength - 1) == ' ') {
            expression = expression.substring(0, expressionLength - 3);
         }
         String resultString = this.calculator.result(expression);
         // this.calculator.resultLabel.setText("= " + this.calculator.calculate(this.calculator.textField.getText()));
         if ("MATH ERROR".equals(resultString)) {
            this.calculator.resultLabel.setForeground(Color.RED);
            this.calculator.resultLabel.setText("MATH ERROR");
         } else {
            this.calculator.resultLabel.setForeground(Color.BLACK);
            this.calculator.resultLabel.setText("= " + resultString);
         }
         if ("Ans".equals(command)) {
            this.calculator.textField.setText(resultString);
            this.calculator.setExpression(resultString);
         }
      }
   }
}
