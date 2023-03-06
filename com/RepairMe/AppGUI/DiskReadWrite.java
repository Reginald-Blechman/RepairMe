package com.RepairMe.AppGUI;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.UtillityFiles.DiskUtilitty;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

public class DiskReadWrite extends JFrame {
   private JPanel contentPane;
   Logger logger = Logger.getLogger(MyLogger.class);
   public static String ss = "";

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               DiskReadWrite frame = new DiskReadWrite();
               frame.setVisible(true);
               frame.setResizable(false);
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      });
   }

   public DiskReadWrite() {
      this.setDefaultCloseOperation(3);
      this.setBounds(100, 100, 885, 557);
      this.contentPane = new JPanel();
      this.contentPane.setBackground(new Color(255, 255, 255));
      this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setContentPane(this.contentPane);
      this.contentPane.setLayout((LayoutManager)null);
      JButton closeBtn = new JButton("New button");
      closeBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            DiskReadWrite ff = new DiskReadWrite();
            ff.setDefaultCloseOperation(3);
         }
      });
      closeBtn.setBounds(391, 404, 85, 21);
      this.contentPane.add(closeBtn);
      DiskUtilitty ui = new DiskUtilitty();
      ss = ui.printData();
      JLabel diskReadWriteLabel = new JLabel("New label");
      diskReadWriteLabel.setBounds(110, 141, 658, 245);
      diskReadWriteLabel.setText(ss);
      this.contentPane.add(diskReadWriteLabel);
   }
}
