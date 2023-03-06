package com.RepairMe.AppGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.apache.commons.io.FileUtils;

public class LicenseWindow extends JFrame {
   private JFrame frmLicenseAgreement;

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LicenseWindow window = new LicenseWindow(new File(""), (InitializerFrame)null);
               window.frmLicenseAgreement.setVisible(true);
            } catch (Exception var2) {
               var2.printStackTrace();
            }

         }
      });
   }

   public LicenseWindow(File f1, InitializerFrame frame) {
      this.initialize(f1, frame);
   }

   private void initialize(File f1, final InitializerFrame frame) {
      this.frmLicenseAgreement = new JFrame();
      this.frmLicenseAgreement.setIconImage(Toolkit.getDefaultToolkit().getImage(LicenseWindow.class.getResource("/com/resource/bigLogo.png")));
      this.frmLicenseAgreement.setTitle("License Agreement");
      this.frmLicenseAgreement.getContentPane().setBackground(new Color(255, 255, 255));
      this.frmLicenseAgreement.getContentPane().setLayout((LayoutManager)null);
      JPanel middlePanel = new JPanel();
      middlePanel.setBackground(new Color(255, 255, 255));
      middlePanel.setBounds(51, 75, 868, 468);
      this.frmLicenseAgreement.getContentPane().add(middlePanel);
      String path = System.getProperty("user.dir") + "\\private\\Agreement.txt";
      JTextArea display = new JTextArea(25, 80);
      display.setBounds(1, 1, 836, 529);
      display.setFont(new Font("Roboto Medium", 0, 15));
      display.setEditable(false);
      display.setLineWrap(true);
      display.setWrapStyleWord(true);
      display.setText("vijay");

      try {
         display.setText(FileUtils.readFileToString(new File(path)));
      } catch (IOException var11) {
         var11.printStackTrace();
      }

      middlePanel.setLayout((LayoutManager)null);
      middlePanel.add(display);
      JScrollPane scroll = new JScrollPane(display);
      scroll.setBounds(10, 10, 848, 444);
      scroll.setVerticalScrollBarPolicy(22);
      middlePanel.add(scroll);
      JLabel lblNewLabel = new JLabel("RepairMe -- END USER LICENSE AGREEMENT");
      lblNewLabel.setFont(new Font("Tahoma", 0, 22));
      lblNewLabel.setBounds(266, 27, 462, 38);
      this.frmLicenseAgreement.getContentPane().add(lblNewLabel);
      final JButton AgreedBtn = new JButton("Continue");
      AgreedBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               File f1 = new File(System.getProperty("user.dir") + "\\private\\PreCheck.ini");
               FileWriter fw = new FileWriter(f1, true);
               fw.append(",LCheck");
               InitializerFrame.callwindow(f1, frame);
               fw.close();
               LicenseWindow.this.frmLicenseAgreement.setVisible(false);
            } catch (Exception var4) {
               var4.printStackTrace();
            }

         }
      });
      AgreedBtn.setFont(new Font("Tahoma", 0, 12));
      AgreedBtn.setBounds(579, 562, 106, 30);
      AgreedBtn.setEnabled(false);
      final JCheckBox AgreeChkBox = new JCheckBox("I have read this agreement and agree to the License");
      AgreeChkBox.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            if (AgreeChkBox.isSelected()) {
               AgreedBtn.setEnabled(true);
            }

            if (!AgreeChkBox.isSelected()) {
               AgreedBtn.setEnabled(false);
            }

         }
      });
      AgreeChkBox.setBackground(new Color(255, 255, 255));
      AgreeChkBox.setFont(new Font("Roboto", 0, 14));
      AgreeChkBox.setBounds(49, 565, 501, 21);
      this.frmLicenseAgreement.getContentPane().add(AgreeChkBox);
      this.frmLicenseAgreement.getContentPane().add(AgreedBtn);
      JLabel lblNewLabel_1 = new JLabel(createImageIcon("/com/resource/smallestLogo.png", "Logo"));
      lblNewLabel_1.setBounds(216, 27, 40, 40);
      this.frmLicenseAgreement.getContentPane().add(lblNewLabel_1);
      this.frmLicenseAgreement.setBounds(100, 100, 988, 647);
      this.frmLicenseAgreement.setDefaultCloseOperation(3);
      this.frmLicenseAgreement.setLocationRelativeTo((Component)null);
      this.frmLicenseAgreement.setVisible(true);
      this.frmLicenseAgreement.setLocationRelativeTo((Component)null);
   }

   private static ImageIcon createImageIcon(String path, String description) {
      URL imgURL = MainFrame.class.getResource(path);
      return imgURL != null ? new ImageIcon(imgURL, description) : null;
   }
}
