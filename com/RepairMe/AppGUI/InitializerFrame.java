package com.RepairMe.AppGUI;

import com.RepairMe.Process.MyLogger;
import com.RepairMe.StartupTest.StartupTests;
import com.RepairMe.common.ProcessMe;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

public class InitializerFrame extends JFrame {
   private static final long serialVersionUID = 1L;
   private JPanel contentPane;
   Logger logger = Logger.getLogger(MyLogger.class);

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 50);
            InitializerFrame frame = new InitializerFrame();
            frame.setVisible(false);
            frame.setResizable(false);

            try {
               StartupTests tr = new StartupTests();
               tr.startProcess();
               File f1 = new File(System.getProperty("user.dir") + "\\private\\PreCheck.ini");
               (new InitializerFrame()).startupTests(f1, frame);
            } catch (Exception var4) {
               var4.printStackTrace();
            }

         }
      });
   }

   private void startupTests(File f1, InitializerFrame frame) {
      try {
         if (f1.exists()) {
            BufferedReader br1 = new BufferedReader(new FileReader(f1));
            String line = "";
            if ((line = br1.readLine()) != null) {
               if (line.contains("LCheck")) {
                  frame.setVisible(true);
                  callwindow(f1, frame);
               } else {
                  new LicenseWindow(f1, frame);
                  frame.setVisible(false);
               }
            }

            br1.close();
         } else {
            Thread.sleep(20L);
            this.startupTests(f1, frame);
         }
      } catch (Exception var5) {
         this.logger.info(var5.getMessage());
      }

   }

   public static void callwindow(File f1, InitializerFrame frame) {
      try {
         if (!f1.exists() && f1.length() == 0L) {
            try {
               Thread.sleep(100L);
            } catch (Exception var5) {
               var5.printStackTrace();
            }

            callwindow(f1, frame);
         } else if (f1.exists() && f1.length() == 0L) {
            try {
               Thread.sleep(100L);
            } catch (Exception var4) {
               var4.printStackTrace();
            }

            callwindow(f1, frame);
         } else if (f1.length() > 0L) {
            BufferedReader br1 = new BufferedReader(new FileReader(f1));
            if (br1.readLine().contains("OK")) {
               MainFrame mf = new MainFrame("2", frame, checklicence());
               mf.setSize(1233, 940);
               mf.setVisible(true);
            } else {
               JOptionPane.showMessageDialog(frame, "OS Not Compatible. Only Win 10 and Win 11 is compatible", "Software Distribution Service", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
               System.exit(128);
            }

            br1.close();
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private static boolean checklicence() {
      try {
         String[] chckCommand = new String[]{"powershell.exe", "Get-ItemProperty -Path 'HKLM:\\Software\\RepairMe' | Get-Member -Name 'ActivationCode'"};
         Process process = Runtime.getRuntime().exec(chckCommand);
         process.waitFor();
         String finaLine = "";
         BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
         int j = 0;
         String s = "";

         while((finaLine = in.readLine()) != null) {
            if (!finaLine.isEmpty()) {
               if (j > 2) {
                  s = finaLine.substring(finaLine.indexOf("=") + 1, finaLine.length());
               }

               ++j;
            }
         }

         ProcessMe m = new ProcessMe();
         s = m.process(s);
         in.close();
         if (s.equals("S")) {
            return true;
         }

         if (s.equals("N")) {
            return false;
         }
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      return true;
   }

   public InitializerFrame() {
      this.setTitle("initializer");
      this.setDefaultCloseOperation(3);
      this.setBounds(100, 100, 425, 316);
      this.getContentPane().setLayout((LayoutManager)null);
      this.contentPane = new JPanel();
      this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
   }
}
