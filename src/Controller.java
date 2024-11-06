package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

   private SimpleCalculation sc;

   public Controller(SimpleCalculation sc) {
      this.sc = sc;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String getCmd = e.getActionCommand();
      if ("AC".equals(getCmd)) {
         this.sc.tf.setText("");
         this.sc.l.setForeground(Color.BLACK);
         this.sc.l.setText("=");
         this.sc.setS("");
      } else {
         int len = this.sc.getS().length();
         if ("DEL".equals(getCmd)) {
            if (len > 0) {
               if (this.sc.getS().charAt(len - 1) == ' ') {
                  this.sc.setS(this.sc.getS().substring(0, len - 3));
               } else {
                  this.sc.setS(this.sc.getS().substring(0, len - 1));
               }
            }
         } else if (!"Ans".equals(getCmd)) {
            if (len == 0 && (" * ".equals(getCmd) || " / ".equals(getCmd))) {
               this.sc.setS("");
            } else {
               this.sc.setS(this.sc.tf.getText() + getCmd);
            }
         }
         this.sc.tf.setText(this.sc.getS());
         String s = this.sc.tf.getText();
         int lens = s.length();
         if (lens > 0 && s.charAt(lens - 1) == ' ') {
            s = s.substring(0, lens - 3);
         }
         String str = this.sc.result(s);
         // this.sc.l.setText("= " + this.sc.tinh(this.sc.tf.getText()));
         if ("MATH ERROR".equals(str)) {
            this.sc.l.setForeground(Color.RED);
            this.sc.l.setText("MATH ERROR");
         } else {
            this.sc.l.setForeground(Color.BLACK);
            this.sc.l.setText("= " + str);
         }

         if ("Ans".equals(getCmd)) {
            this.sc.tf.setText(str);
            this.sc.setS(str);
         }
      }

   }
}
