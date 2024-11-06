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

   private String s = "";
   private JPanel ptf, pbtn;
   protected JTextField tf;
   protected JLabel l;
   private JButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_Ans, btn_DEL, btn_AC,
         btn_dot, btn_addition, btn_subtraction, btn_multiplication, btn_division, btn_moNgoac, btn_dongNgoac;

   public void setS(String s) {
      this.s = s;
   }

   public String getS() {
      return this.s;
   }

   public SimpleCalculation() {
      this.setTitle("Máy tính đơn giản");
      this.setSize(600, 550);
      this.setLocationRelativeTo(null);

      Color c = new Color(252, 115, 3);
      Font font = new Font("Arial", ABORT, 40);

      ptf = new JPanel();
      tf = new JTextField();
      tf.setFont(font);
      tf.setSize(600, 50);
      l = new JLabel("= ");
      l.setFont(font);
      l.setSize(600, 50);
      l.setBackground(Color.WHITE);
      ptf.setLayout(new BorderLayout());
      ptf.add(tf, BorderLayout.NORTH);
      ptf.add(l, BorderLayout.SOUTH);

      pbtn = new JPanel();
      pbtn.setLayout(new GridLayout(4, 5));
      btn_0 = new JButton("0");
      btn_0.setFont(font);
      btn_1 = new JButton("1");
      btn_1.setFont(font);
      btn_2 = new JButton("2");
      btn_2.setFont(font);
      btn_3 = new JButton("3");
      btn_3.setFont(font);
      btn_4 = new JButton("4");
      btn_4.setFont(font);
      btn_5 = new JButton("5");
      btn_5.setFont(font);
      btn_6 = new JButton("6");
      btn_6.setFont(font);
      btn_7 = new JButton("7");
      btn_7.setFont(font);
      btn_8 = new JButton("8");
      btn_8.setFont(font);
      btn_9 = new JButton("9");
      btn_9.setFont(font);
      btn_AC = new JButton("AC");
      btn_AC.setFont(font);
      btn_AC.setBackground(c);
      btn_dot = new JButton(".");
      btn_dot.setFont(font);
      btn_DEL = new JButton("DEL");
      btn_DEL.setFont(font);
      btn_DEL.setBackground(c);
      btn_Ans = new JButton("Ans");
      btn_Ans.setFont(font);
      btn_moNgoac = new JButton("(");
      btn_moNgoac.setFont(font);
      btn_dongNgoac = new JButton(")");
      btn_dongNgoac.setFont(font);
      btn_division = new JButton(" / ");
      btn_division.setFont(font);
      btn_addition = new JButton(" + ");
      btn_addition.setFont(font);
      btn_subtraction = new JButton(" - ");
      btn_subtraction.setFont(font);
      btn_multiplication = new JButton(" * ");
      btn_multiplication.setFont(font);

      Controller al = new Controller(this);
      btn_0.addActionListener(al);
      btn_1.addActionListener(al);
      btn_2.addActionListener(al);
      btn_3.addActionListener(al);
      btn_4.addActionListener(al);
      btn_5.addActionListener(al);
      btn_6.addActionListener(al);
      btn_7.addActionListener(al);
      btn_8.addActionListener(al);
      btn_9.addActionListener(al);
      btn_dot.addActionListener(al);
      btn_division.addActionListener(al);
      btn_multiplication.addActionListener(al);
      btn_addition.addActionListener(al);
      btn_subtraction.addActionListener(al);
      btn_moNgoac.addActionListener(al);
      btn_dongNgoac.addActionListener(al);
      btn_AC.addActionListener(al);
      btn_DEL.addActionListener(al);
      btn_Ans.addActionListener(al);

      pbtn.add(btn_7);
      pbtn.add(btn_8);
      pbtn.add(btn_9);
      pbtn.add(btn_DEL);
      pbtn.add(btn_AC);
      pbtn.add(btn_4);
      pbtn.add(btn_5);
      pbtn.add(btn_6);
      pbtn.add(btn_multiplication);
      pbtn.add(btn_division);
      pbtn.add(btn_1);
      pbtn.add(btn_2);
      pbtn.add(btn_3);
      pbtn.add(btn_addition);
      pbtn.add(btn_subtraction);
      pbtn.add(btn_0);
      pbtn.add(btn_dot);
      pbtn.add(btn_Ans);
      pbtn.add(btn_moNgoac);
      pbtn.add(btn_dongNgoac);

      this.setLayout(new BorderLayout());
      this.add(ptf, BorderLayout.NORTH);
      this.add(pbtn, BorderLayout.CENTER);

      this.setVisible(true);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   }

   public String result(String s) {
      if (s.isEmpty()) {
         return "";
      }
      StringBuilder sb = new StringBuilder(s);
      if (s.contains("(") || s.contains(")")) {
         ArrayList<Integer> moNgoac = new ArrayList<>();
         ArrayList<Integer> dongNgoac = new ArrayList<>();
         char[] c = s.toCharArray();
         int len = c.length;
         for (int i = 0; i < len; ++i) {
            if (c[i] == '(') {
               moNgoac.add(i);
            } else if (c[i] == ')') {
               dongNgoac.add(i);
            }
         }
         if (moNgoac.size() != dongNgoac.size()) {
            return "";
         }
         String s1, s2;
         int mo, dong, j = 1, k, before, after;
         moNgoac.add(Integer.MAX_VALUE);
         while (moNgoac.size() > 1) {
            dong = dongNgoac.get(0);
            mo = moNgoac.get(j - 1);
            if (dong < moNgoac.get(j)) {
               s1 = sb.substring(mo + 1, dong);
               s2 = this.tinh(s1);
               before = s1.length();
               after = s2.length();
               k = before - after + 2;
               for (int i = k; i > 0; --i) {
                  s2 += " ";
               }
               sb.replace(mo, dong + 1, s2);
               moNgoac.remove(j - 1);
               dongNgoac.remove(0);
               j = 1;
            } else {
               ++j;
            }
         }
      }
      s = sb.toString();
      s = this.tinh(s);
      try {
         if (Float.parseFloat(s.substring(s.indexOf(".") + 1)) == 0) {
            return s.substring(0, s.indexOf("."));
         }
      } catch (NumberFormatException e) {
      }
      return s;
   }

   public String tinhCong(ArrayList<String> arrstr) {
      float result = 0;
      for (String i : arrstr)
         try {
            result += Float.parseFloat(i);
         } catch (NumberFormatException e) {
         }
      return result + "";
   }

   public String tinhNhanChia(String s) {
      String[] str = s.trim().split(" ");
      String i;
      int len = str.length;
      float result = 0;
      try {
         result = Float.parseFloat(str[0]);
      } catch (NumberFormatException e) {
      }
      int j = 1;
      while (j < len) {
         if (j == len - 1) {
            return result + "";
         }
         i = str[j];
         switch (i) {
            case "*" -> {
               result *= Float.parseFloat(str[++j]);
            }
            case "/" -> {
               float end = Float.parseFloat(str[++j]);
               if (end == 0) {
                  return "MATH ERROR";
               } else {
                  result /= end;
               }
            }
         }
         ++j;
      }
      return result + "";
   }

   public String tinh(String s) {
      ArrayList<String> arrstr = new ArrayList<>();
      char[] c = null;
      String[] str = null;
      if (s.contains(" - ")) {
         c = s.toCharArray();
         s = "";
         int len = c.length;
         for (int i = 0; i < len; ++i) {
            if (c[i] == '-' && c[i + 1] == ' ') {
               c[i] = '+';
               c[i + 1] = '-';
               ++i;
            }
         }
         for (char i : c) {
            s += i;
         }
      }
      if (s.contains("+")) {
         str = s.split("[+]");
         for (String i : str) {
            arrstr.add(i);
         }
      } else {
         arrstr.add(s);
      }

      int len = arrstr.size();
      String j;

      for (int i = 0; i < len; ++i) {
         j = arrstr.get(i);
         if (j.contains("*") || j.contains("/")) {
            arrstr.set(i, this.tinhNhanChia(j));
         }
         if ("MATH ERROR".equals(arrstr.get(i))) {
            return "MATH ERROR";
         }
      }
      return this.tinhCong(arrstr);
   }

   public static void main(String[] args) {
      SimpleCalculation simpleCalculation;
      simpleCalculation = new SimpleCalculation();

   }
}
