package com.RepairMe.AppGUI;

import com.RepairMe.Process.BrowserProcessor;
import com.RepairMe.Process.DiskProcess;
import com.RepairMe.Process.ExtrasProcess;
import com.RepairMe.Process.FirstTimeProcess;
import com.RepairMe.Process.GameProcess;
import com.RepairMe.Process.MainProcessor;
import com.RepairMe.Process.MainUserSystemInfo;
import com.RepairMe.Process.MyLogger;
import com.RepairMe.Process.SystemPerformanceProcess;
import com.RepairMe.UtillityFiles.BrowserUtilityClass;
import com.RepairMe.UtillityFiles.SystemPerformanceUtillity;
import com.RepairMe.bckThreads.InstallWinGetPackagePage;
import com.RepairMe.common.ProcessMe;
import com.RepairMe.common.RepairMeCommon;
import com.RepairMe.common.SystemDriveSize;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import org.apache.log4j.Logger;

public class MainFrame extends JFrame {
   private JPanel contentPane;
   private JLabel StorageMainMessageLbl;
   Logger logger = Logger.getLogger(MyLogger.class);
   private JTextField ActCodeTxtField;
   private JButton GameModeBtn;
   private JTextField virtualRamTxtbox;

   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MainFrame frame = new MainFrame("", (InitializerFrame)null, true);
               Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
               int frameWidth = (int)(screenSize.getWidth() * 90.0D) / 100;
               int frameHeight = (int)(screenSize.getHeight() * 90.0D / 100.0D);
               frame.setSize(1228, 950);
               frame.setMaximumSize(frame.getSize());
               frame.setResizable(false);
               frame.setVisible(true);
               frame.setLocation((screenSize.width - frameWidth) / 2, (screenSize.height - frameHeight) / 2);
               frame.pack();
            } catch (Exception var5) {
            }

         }
      });
   }

   private static ImageIcon createImageIcon(String path, String description) {
      URL imgURL = MainFrame.class.getResource(path);
      return imgURL != null ? new ImageIcon(imgURL, description) : null;
   }

   private void colourCoding(JLabel b, JPanel p) {
      Component[] components = p.getComponents();
      Component[] var7 = components;
      int var6 = components.length;

      for(int var5 = 0; var5 < var6; ++var5) {
         Component component = var7[var5];
         if (component.getClass().equals(JLabel.class)) {
            if (((JLabel)component).equals(b)) {
               b.setBackground(new Color(120, 136, 164));
               b.setOpaque(true);
               p.setBackground(new Color(120, 136, 164));
            } else {
               ((JLabel)component).setBackground(new Color(55, 64, 82));
               ((JLabel)component).setOpaque(true);
               p.setBackground(new Color(55, 64, 82));
            }
         }
      }

   }

   private void changelbl(ArrayList<JLabel> lbl) {
      for(int i = 0; i < lbl.size(); ++i) {
         ((JLabel)lbl.get(i)).setText("Once you click on any activity please wait for some time to finish it.");
      }

   }

   public MainFrame(String s12, InitializerFrame frame, final boolean licenceCheck) {
      this.setTitle("RepairMe");
      final String webURL = "https://www.tubeandtech.com/single-post/repairme-best-system-maintenance-program";
      (new File(System.getProperty("user.dir") + "//StartJarNew.bat")).delete();
      frame.setVisible(false);
      this.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/com/resource/smallLogo.png")));
      this.setDefaultCloseOperation(3);
      this.setBounds(100, 100, 2227, 1170);
      this.contentPane = new JPanel();
      this.contentPane.setBackground(new Color(55, 65, 82));
      this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      this.setContentPane(this.contentPane);
      this.contentPane.setLayout((LayoutManager)null);
      String btnVal = "";
      SystemPerformanceProcess spp = new SystemPerformanceProcess();
      if (spp.checkWidgetStatus().equals("Enable")) {
         btnVal = "Disable";
      } else {
         btnVal = "Enable";
      }

      JPanel panel_1 = new JPanel();
      panel_1.setBackground(new Color(55, 65, 82));
      panel_1.setBounds(1782, 46, 270, 1024);
      this.contentPane.add(panel_1);
      final JPanel GamingPanel = new JPanel();
      GamingPanel.setBackground(new Color(255, 255, 255));
      GamingPanel.setBounds(1942, 858, 83, 71);
      this.contentPane.add(GamingPanel);
      GamingPanel.setLayout((LayoutManager)null);
      final JPanel IntroPanel = new JPanel();
      JLabel lblExtraFeatuersList_1_1 = new JLabel("Gaming Performance");
      lblExtraFeatuersList_1_1.setBounds(97, 26, 218, 22);
      lblExtraFeatuersList_1_1.setFont(new Font("Verdana", 1, 17));
      GamingPanel.add(lblExtraFeatuersList_1_1);
      final JLabel gamePerfLblMesg = new JLabel("Improve your gaming performance");
      gamePerfLblMesg.setBounds(97, 50, 355, 17);
      gamePerfLblMesg.setHorizontalAlignment(2);
      gamePerfLblMesg.setForeground(new Color(58, 129, 255));
      gamePerfLblMesg.setFont(new Font("Tahoma", 0, 14));
      GamingPanel.add(gamePerfLblMesg);
      JLabel lblNewLabel_19 = new JLabel(createImageIcon("/com/resource/Game36.png", "Game"));
      lblNewLabel_19.setBounds(45, 30, 40, 40);
      GamingPanel.add(lblNewLabel_19);
      JLabel lblNewLabel_34_1 = new JLabel("Disable Memory Integrity");
      lblNewLabel_34_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34_1.setBounds(97, 147, 205, 28);
      GamingPanel.add(lblNewLabel_34_1);
      JLabel lblNewLabel_33_1 = new JLabel("<html>Creates a restoration point of your system , So you can revert changes to this system state.</html>");
      lblNewLabel_33_1.setBackground(new Color(0, 0, 0));
      lblNewLabel_33_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_33_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1.setBounds(97, 171, 261, 38);
      GamingPanel.add(lblNewLabel_33_1);
      JLabel lblNewLabel_36_1_1_1_2_2 = new JLabel(createImageIcon("/com/resource/MSD.png", "Memory Integrity"));
      lblNewLabel_36_1_1_1_2_2.setBounds(57, 157, 30, 32);
      GamingPanel.add(lblNewLabel_36_1_1_1_2_2);
      final JButton memoryIntegirtyBtn = new JButton("Disable");
      memoryIntegirtyBtn.setFont(new Font("Tahoma", 0, 12));
      memoryIntegirtyBtn.setForeground(new Color(255, 255, 255));
      memoryIntegirtyBtn.setBackground(new Color(55, 65, 82));
      memoryIntegirtyBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (licenceCheck) {
               GameProcess gp = new GameProcess();
               if (memoryIntegirtyBtn.getText().equals("Enable")) {
                  memoryIntegirtyBtn.setText(gp.disableMemoryInteigrity("1"));
                  gamePerfLblMesg.setText("Memory Integrity Enabled");
                  JOptionPane.showMessageDialog(MainFrame.this, "Memory Integrity successfully enabled", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
               } else if (memoryIntegirtyBtn.getText().equals("Disable")) {
                  memoryIntegirtyBtn.setText(gp.disableMemoryInteigrity("0"));
                  gamePerfLblMesg.setText("Memory Integrity Disabled");
                  JOptionPane.showMessageDialog(MainFrame.this, "Memory Integrity successfully disabled", "Memory Integrity", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
               }
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            }

         }
      });
      memoryIntegirtyBtn.setBounds(805, 157, 120, 35);
      GamingPanel.add(memoryIntegirtyBtn);
      JLabel lblNewLabel_34_1_1 = new JLabel("Prioratize Gaming");
      lblNewLabel_34_1_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34_1_1.setBounds(97, 255, 205, 28);
      GamingPanel.add(lblNewLabel_34_1_1);
      JLabel lblNewLabel_33_1_1 = new JLabel("<html>This will make changes in registry to run your game with system priority</html>");
      lblNewLabel_33_1_1.setBackground(new Color(0, 0, 0));
      lblNewLabel_33_1_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_33_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1_1.setBounds(97, 279, 261, 38);
      GamingPanel.add(lblNewLabel_33_1_1);
      JLabel lblNewLabel_36_1_1_1_2_2_1 = new JLabel(createImageIcon("/com/resource/list.png", "Gaming Priority"));
      lblNewLabel_36_1_1_1_2_2_1.setBounds(57, 265, 30, 32);
      GamingPanel.add(lblNewLabel_36_1_1_1_2_2_1);
      JButton PriorityGamingBtn = new JButton("Enable");
      PriorityGamingBtn.setFont(new Font("Tahoma", 0, 12));
      PriorityGamingBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (licenceCheck) {
               GameProcess p = new GameProcess();
               p.setGamePriority();
               gamePerfLblMesg.setText("Gaming is set to top priority");
               JOptionPane.showMessageDialog(MainFrame.this, "Game is set to top priority", "Gaming Priority", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            }

         }
      });
      PriorityGamingBtn.setForeground(new Color(255, 255, 255));
      PriorityGamingBtn.setBackground(new Color(55, 65, 82));
      PriorityGamingBtn.setBounds(621, 282, 120, 35);
      GamingPanel.add(PriorityGamingBtn);
      JLabel lblNewLabel_34_1_1_1 = new JLabel("Set High Performance Power Plan");
      lblNewLabel_34_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34_1_1_1.setBounds(92, 380, 268, 28);
      GamingPanel.add(lblNewLabel_34_1_1_1);
      final JLabel PowerplanValuesLbl = new JLabel("New label");
      PowerplanValuesLbl.setBounds(57, 751, 878, 22);
      PowerplanValuesLbl.setVisible(false);
      GamingPanel.add(PowerplanValuesLbl);
      JLabel lblNewLabel_33_1_1_1 = new JLabel("<html>This will create if not present a high performance power plan and activate it.</html>");
      lblNewLabel_33_1_1_1.setBackground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1_1_1.setBounds(92, 404, 280, 38);
      GamingPanel.add(lblNewLabel_33_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_2_1_1 = new JLabel(createImageIcon("/com/resource/Plug.png", "Gaming Priority"));
      lblNewLabel_36_1_1_1_2_2_1_1.setBounds(52, 390, 30, 32);
      GamingPanel.add(lblNewLabel_36_1_1_1_2_2_1_1);
      final JComboBox<String> powerPlanCombo = new JComboBox();
      powerPlanCombo.setFont(new Font("Tahoma", 0, 13));
      powerPlanCombo.setModel(new DefaultComboBoxModel(new String[]{"Please wait.."}));
      powerPlanCombo.setBounds(594, 406, 147, 28);
      GamingPanel.add(powerPlanCombo);
      JButton PowerPanBtn = new JButton("Enable");
      PowerPanBtn.setFont(new Font("Tahoma", 0, 12));
      PowerPanBtn.setForeground(new Color(255, 255, 255));
      PowerPanBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String s = PowerplanValuesLbl.getText().split("@")[powerPlanCombo.getSelectedIndex() - 1].split("#")[0];
            GameProcess game = new GameProcess();
            if (s.indexOf("%") > 0) {
               game.setPowerPlan(s.substring(s.lastIndexOf("%") + 1));
            }

            game.setPowerPlan(s);
            gamePerfLblMesg.setText("Power Plan set to " + powerPlanCombo.getSelectedItem());
            JOptionPane.showMessageDialog(MainFrame.this, "Power Plan set to " + powerPlanCombo.getSelectedItem(), "System Power Plan", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      PowerPanBtn.setBackground(new Color(55, 65, 82));
      PowerPanBtn.setBounds(805, 404, 120, 35);
      GamingPanel.add(PowerPanBtn);
      JLabel lblNewLabel_34_1_1_1_1 = new JLabel("Enable/ Disable \"VMP\"");
      lblNewLabel_34_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34_1_1_1_1.setBounds(85, 506, 268, 28);
      GamingPanel.add(lblNewLabel_34_1_1_1_1);
      JLabel lblNewLabel_33_1_1_1_1 = new JLabel("<html>Disable Virtual Machine Platform to give little more boost and resources to your gaming needs</html>");
      lblNewLabel_33_1_1_1_1.setBackground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1_1_1_1.setBounds(85, 530, 280, 38);
      GamingPanel.add(lblNewLabel_33_1_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_2_1_1_1 = new JLabel(createImageIcon("/com/resource/VR.png", "VMP"));
      lblNewLabel_36_1_1_1_2_2_1_1_1.setBounds(45, 516, 30, 32);
      GamingPanel.add(lblNewLabel_36_1_1_1_2_2_1_1_1);
      final JButton VMPBtn = new JButton("Disable");
      VMPBtn.setFont(new Font("Tahoma", 0, 12));
      VMPBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            JOptionPane.showMessageDialog(MainFrame.this, "It may requires a restart", "System Message", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/info1.png")));
            GameProcess p = new GameProcess();
            VMPBtn.setText(p.vmpMode(VMPBtn.getText()));
            String sa = "";
            if (VMPBtn.getText().equals("Enable")) {
               sa = "VMP Disabled";
            } else {
               sa = "VMP Enabled";
            }

            gamePerfLblMesg.setText(sa);
            JOptionPane.showMessageDialog(MainFrame.this, sa + " Successfully", "System VMP Setup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      VMPBtn.setForeground(new Color(255, 255, 255));
      VMPBtn.setBackground(new Color(55, 65, 82));
      VMPBtn.setBounds(798, 512, 120, 35);
      GamingPanel.add(VMPBtn);
      JLabel lblNewLabel_34_1_1_1_1_1 = new JLabel("Switch to Game Mode");
      lblNewLabel_34_1_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34_1_1_1_1_1.setBounds(92, 622, 268, 28);
      GamingPanel.add(lblNewLabel_34_1_1_1_1_1);
      JLabel lblNewLabel_33_1_1_1_1_1 = new JLabel("<html>Stops many background process, change registry entries, Enable full power mode to give you boost in gaming.</html>");
      lblNewLabel_33_1_1_1_1_1.setBackground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1_1_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_33_1_1_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1_1_1_1_1.setBounds(92, 646, 318, 45);
      GamingPanel.add(lblNewLabel_33_1_1_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_2_1_1_1_1 = new JLabel(createImageIcon("/com/resource/game32.png", "VMP"));
      lblNewLabel_36_1_1_1_2_2_1_1_1_1.setBounds(52, 632, 30, 32);
      GamingPanel.add(lblNewLabel_36_1_1_1_2_2_1_1_1_1);
      this.GameModeBtn = new JButton("Enable");
      this.GameModeBtn.setForeground(new Color(255, 255, 255));
      this.GameModeBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (licenceCheck) {
               GameProcess p = new GameProcess();
               p.enableGameMode();
               String sa = "";
               if (VMPBtn.getText().equals("Enable")) {
                  sa = "Game Mode Disabled";
               } else {
                  sa = "Game Mode Enabled";
               }

               gamePerfLblMesg.setText(sa);
               JOptionPane.showMessageDialog(MainFrame.this, sa + "Successfully. Please do remember to revert it back.", "Game Mode", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            }

         }
      });
      this.GameModeBtn.setFont(new Font("Tahoma", 0, 12));
      this.GameModeBtn.setBackground(new Color(55, 65, 82));
      this.GameModeBtn.setBounds(621, 643, 120, 35);
      GamingPanel.add(this.GameModeBtn);
      JSeparator separator_1_1_1_1_4 = new JSeparator();
      separator_1_1_1_1_4.setOpaque(true);
      separator_1_1_1_1_4.setBackground(Color.WHITE);
      separator_1_1_1_1_4.setBounds(34, 594, 901, 3);
      GamingPanel.add(separator_1_1_1_1_4);
      JSeparator separator_1_1_1_1_4_1 = new JSeparator();
      separator_1_1_1_1_4_1.setOpaque(true);
      separator_1_1_1_1_4_1.setBackground(Color.WHITE);
      separator_1_1_1_1_4_1.setBounds(34, 483, 901, 3);
      GamingPanel.add(separator_1_1_1_1_4_1);
      JSeparator separator_1_1_1_1_4_1_1 = new JSeparator();
      separator_1_1_1_1_4_1_1.setOpaque(true);
      separator_1_1_1_1_4_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_4_1_1.setBounds(34, 349, 901, 3);
      GamingPanel.add(separator_1_1_1_1_4_1_1);
      JSeparator separator_1_1_1_1_4_1_1_1 = new JSeparator();
      separator_1_1_1_1_4_1_1_1.setOpaque(true);
      separator_1_1_1_1_4_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_4_1_1_1.setBounds(34, 233, 901, 3);
      GamingPanel.add(separator_1_1_1_1_4_1_1_1);
      JLabel lblNewLabel_33_1_1_1_2 = new JLabel("'*' Active Plan");
      lblNewLabel_33_1_1_1_2.setForeground(new Color(58, 129, 255));
      lblNewLabel_33_1_1_1_2.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1_1_2.setFont(new Font("Tahoma", 1, 12));
      lblNewLabel_33_1_1_1_2.setBounds(649, 444, 92, 28);
      GamingPanel.add(lblNewLabel_33_1_1_1_2);
      JLabel linktoSiteLbl_2_3 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_3.setHorizontalAlignment(0);
      linktoSiteLbl_2_3.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_3.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_3.setBounds(805, 43, 169, 36);
      GamingPanel.add(linktoSiteLbl_2_3);
      linktoSiteLbl_2_3.setCursor(Cursor.getPredefinedCursor(12));
      JButton btnRevert = new JButton("Revert");
      btnRevert.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (licenceCheck) {
               GameProcess p = new GameProcess();
               p.revertGamePriority();
               gamePerfLblMesg.setText("Regsitry is set to Normal Priority");
               JOptionPane.showMessageDialog(MainFrame.this, "Regsitry is set to Normal Priority", "Gaming Priority", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            }

         }
      });
      btnRevert.setForeground(Color.WHITE);
      btnRevert.setFont(new Font("Tahoma", 0, 12));
      btnRevert.setBackground(new Color(55, 65, 82));
      btnRevert.setBounds(805, 282, 120, 35);
      GamingPanel.add(btnRevert);
      JButton disablegameMode = new JButton("Disable");
      disablegameMode.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (licenceCheck) {
               GameProcess p = new GameProcess();
               p.disableGameMode();
               gamePerfLblMesg.setText("Game Mode Disabled");
               JOptionPane.showMessageDialog(MainFrame.this, "Game Mode Disabled Successfully", "Gaming Mode", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            }

         }
      });
      disablegameMode.setForeground(Color.WHITE);
      disablegameMode.setFont(new Font("Tahoma", 0, 12));
      disablegameMode.setBackground(new Color(55, 65, 82));
      disablegameMode.setBounds(805, 643, 120, 35);
      GamingPanel.add(disablegameMode);
      JLabel lblNewLabel_33_1_1_1_1_1_1 = new JLabel("<html>Game mode will pump full power to your components and generates lot of heat. Better to revert it after.</html>");
      lblNewLabel_33_1_1_1_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_1_1_1_1_1_1.setForeground(Color.BLACK);
      lblNewLabel_33_1_1_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_1_1_1_1_1_1.setBackground(Color.BLACK);
      lblNewLabel_33_1_1_1_1_1_1.setBounds(621, 696, 304, 45);
      GamingPanel.add(lblNewLabel_33_1_1_1_1_1_1);
      JLabel lblActivityMayTake_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1.setBounds(251, 104, 489, 20);
      GamingPanel.add(lblActivityMayTake_1_1);
      linktoSiteLbl_2_3.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      final JPanel perfPannel = new JPanel();
      perfPannel.setBorder((Border)null);
      perfPannel.setBackground(new Color(255, 255, 255));
      perfPannel.setBounds(1818, 270, 100, 89);
      this.contentPane.add(perfPannel);
      perfPannel.setLayout((LayoutManager)null);
      perfPannel.setVisible(false);
      final JPanel StorgaePanel = new JPanel();
      StorgaePanel.setBorder((Border)null);
      StorgaePanel.setBackground(new Color(255, 255, 255));
      StorgaePanel.setBounds(1952, 739, 90, 95);
      this.contentPane.add(StorgaePanel);
      StorgaePanel.setLayout((LayoutManager)null);
      final JCheckBox windowsSoftwareDistriChkBox = new JCheckBox("Windows Software Distribution");
      windowsSoftwareDistriChkBox.setBackground(new Color(255, 255, 255));
      windowsSoftwareDistriChkBox.setEnabled(false);
      windowsSoftwareDistriChkBox.setSelected(true);
      windowsSoftwareDistriChkBox.setFont(new Font("Tahoma", 1, 13));
      windowsSoftwareDistriChkBox.setBounds(600, 302, 240, 21);
      StorgaePanel.add(windowsSoftwareDistriChkBox);
      final JCheckBox delUsrTempFileChkBox = new JCheckBox("Delete User Temp Files");
      delUsrTempFileChkBox.setBackground(new Color(255, 255, 255));
      delUsrTempFileChkBox.setForeground(new Color(0, 0, 0));
      delUsrTempFileChkBox.setEnabled(false);
      delUsrTempFileChkBox.setSelected(true);
      delUsrTempFileChkBox.setFont(new Font("Tahoma", 1, 13));
      delUsrTempFileChkBox.setBounds(35, 190, 219, 23);
      StorgaePanel.add(delUsrTempFileChkBox);
      final JCheckBox delWinTempFileChkBox = new JCheckBox("Delete Windows Temp Files");
      delWinTempFileChkBox.setBackground(new Color(255, 255, 255));
      delWinTempFileChkBox.setSelected(true);
      delWinTempFileChkBox.setEnabled(false);
      delWinTempFileChkBox.setFont(new Font("Tahoma", 1, 13));
      delWinTempFileChkBox.setBounds(600, 190, 219, 23);
      StorgaePanel.add(delWinTempFileChkBox);
      final JCheckBox delPrefetchFileChkBox = new JCheckBox("Delete Prefetch Files");
      delPrefetchFileChkBox.setBackground(new Color(255, 255, 255));
      delPrefetchFileChkBox.setSelected(true);
      delPrefetchFileChkBox.setEnabled(false);
      delPrefetchFileChkBox.setFont(new Font("Tahoma", 1, 13));
      delPrefetchFileChkBox.setBounds(35, 218, 191, 23);
      StorgaePanel.add(delPrefetchFileChkBox);
      final JCheckBox DelWinErrLogChkBox = new JCheckBox("Delete Windows  Error Logs");
      DelWinErrLogChkBox.setBackground(new Color(255, 255, 255));
      DelWinErrLogChkBox.setEnabled(false);
      DelWinErrLogChkBox.setSelected(true);
      DelWinErrLogChkBox.setFont(new Font("Tahoma", 1, 13));
      DelWinErrLogChkBox.setBounds(600, 218, 219, 23);
      DelWinErrLogChkBox.setToolTipText("<html>It can delete operating system event logs, <br>which might cause application failures and unexpected system behavior.</html>");
      StorgaePanel.add(DelWinErrLogChkBox);
      final JCheckBox emptRecyclebinChkBox = new JCheckBox("Empty Recycle Bin");
      emptRecyclebinChkBox.setBackground(new Color(255, 255, 255));
      emptRecyclebinChkBox.setSelected(true);
      emptRecyclebinChkBox.setEnabled(false);
      emptRecyclebinChkBox.setFont(new Font("Tahoma", 1, 13));
      emptRecyclebinChkBox.setBounds(600, 274, 219, 21);
      StorgaePanel.add(emptRecyclebinChkBox);
      final JLabel DelUserTempFile = new JLabel("0 KB");
      DelUserTempFile.setForeground(new Color(58, 129, 255));
      DelUserTempFile.setFont(new Font("Tahoma", 0, 14));
      DelUserTempFile.setBounds(296, 190, 73, 25);
      StorgaePanel.add(DelUserTempFile);
      final JLabel DelWinTempFile = new JLabel("0 KB");
      DelWinTempFile.setForeground(new Color(58, 129, 255));
      DelWinTempFile.setFont(new Font("Tahoma", 0, 14));
      DelWinTempFile.setBounds(882, 190, 74, 25);
      StorgaePanel.add(DelWinTempFile);
      final JLabel DelPrefetchFile = new JLabel("0 KB");
      DelPrefetchFile.setForeground(new Color(58, 129, 255));
      DelPrefetchFile.setFont(new Font("Tahoma", 0, 14));
      DelPrefetchFile.setBounds(882, 218, 73, 25);
      StorgaePanel.add(DelPrefetchFile);
      final JLabel DelErrLogFile = new JLabel("0 KB");
      DelErrLogFile.setForeground(new Color(58, 129, 255));
      DelErrLogFile.setFont(new Font("Tahoma", 0, 14));
      DelErrLogFile.setBounds(296, 218, 74, 25);
      StorgaePanel.add(DelErrLogFile);
      final JLabel EmptyRecBinLabl = new JLabel("Yes");
      EmptyRecBinLabl.setForeground(new Color(58, 129, 255));
      EmptyRecBinLabl.setFont(new Font("Tahoma", 0, 14));
      EmptyRecBinLabl.setBounds(882, 272, 73, 25);
      StorgaePanel.add(EmptyRecBinLabl);
      final JCheckBox delMemoryDumpChkBox = new JCheckBox("Delete Memory Dump");
      delMemoryDumpChkBox.setBackground(new Color(255, 255, 255));
      delMemoryDumpChkBox.setEnabled(false);
      delMemoryDumpChkBox.setSelected(true);
      delMemoryDumpChkBox.setFont(new Font("Tahoma", 1, 13));
      delMemoryDumpChkBox.setBounds(35, 246, 184, 21);
      StorgaePanel.add(delMemoryDumpChkBox);
      final JLabel memoryDmpFileLbl = new JLabel("0 KB");
      memoryDmpFileLbl.setForeground(new Color(58, 129, 255));
      memoryDmpFileLbl.setFont(new Font("Tahoma", 0, 14));
      memoryDmpFileLbl.setBounds(296, 246, 73, 25);
      StorgaePanel.add(memoryDmpFileLbl);
      final JCheckBox msStoreChkBox = new JCheckBox("MS Store Cache & Cookies");
      msStoreChkBox.setBackground(new Color(255, 255, 255));
      msStoreChkBox.setSelected(true);
      msStoreChkBox.setEnabled(false);
      msStoreChkBox.setFont(new Font("Tahoma", 1, 13));
      msStoreChkBox.setBounds(600, 246, 204, 21);
      StorgaePanel.add(msStoreChkBox);
      final JLabel msStoreLbl = new JLabel("0 KB");
      msStoreLbl.setBackground(new Color(0, 0, 0));
      msStoreLbl.setForeground(new Color(58, 129, 255));
      msStoreLbl.setFont(new Font("Tahoma", 0, 14));
      msStoreLbl.setBounds(882, 246, 74, 25);
      StorgaePanel.add(msStoreLbl);
      final JLabel WinSoftDistTotalLbl = new JLabel("0 KB");
      WinSoftDistTotalLbl.setForeground(new Color(58, 129, 255));
      WinSoftDistTotalLbl.setFont(new Font("Tahoma", 0, 14));
      WinSoftDistTotalLbl.setBounds(882, 302, 73, 25);
      StorgaePanel.add(WinSoftDistTotalLbl);
      final JLabel thumbIconSize = new JLabel("0 KB");
      thumbIconSize.setForeground(new Color(58, 129, 255));
      thumbIconSize.setFont(new Font("Tahoma", 0, 14));
      thumbIconSize.setBounds(296, 274, 74, 21);
      emptRecyclebinChkBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (emptRecyclebinChkBox.isSelected()) {
               EmptyRecBinLabl.setText("Yes");
            } else {
               EmptyRecBinLabl.setText("No");
            }

         }
      });
      final JCheckBox thumbCacheChkBox = new JCheckBox("Delete Thumbnails Cache");
      thumbCacheChkBox.setBackground(new Color(255, 255, 255));
      thumbCacheChkBox.setEnabled(false);
      thumbCacheChkBox.setSelected(true);
      thumbCacheChkBox.setFont(new Font("Tahoma", 1, 13));
      thumbCacheChkBox.setBounds(35, 274, 219, 21);
      StorgaePanel.add(thumbCacheChkBox);
      final JCheckBox deliveryOptiChkBox = new JCheckBox("Delivery Optimization Cache");
      deliveryOptiChkBox.setBackground(new Color(255, 255, 255));
      deliveryOptiChkBox.setEnabled(false);
      deliveryOptiChkBox.setSelected(true);
      deliveryOptiChkBox.setFont(new Font("Tahoma", 1, 13));
      deliveryOptiChkBox.setBounds(36, 302, 219, 21);
      StorgaePanel.add(deliveryOptiChkBox);
      final JLabel delivOptimCacheLbl = new JLabel("0 KB");
      delivOptimCacheLbl.setForeground(new Color(58, 129, 255));
      delivOptimCacheLbl.setFont(new Font("Tahoma", 0, 14));
      delivOptimCacheLbl.setBounds(296, 300, 73, 24);
      StorgaePanel.add(delivOptimCacheLbl);
      final JButton TempFilesscanButton = new JButton("Full Scan");
      TempFilesscanButton.setBackground(new Color(55, 65, 82));
      TempFilesscanButton.setForeground(new Color(255, 255, 255));
      TempFilesscanButton.setBounds(332, 641, 112, 40);
      StorgaePanel.add(TempFilesscanButton);
      TempFilesscanButton.setFont(new Font("Tahoma", 1, 12));
      final JCheckBox BroserCacheChkBox_1 = new JCheckBox("Clear cache - All Browser");
      BroserCacheChkBox_1.setToolTipText("Currently only supporting Brave, Chrome, Edge browsers Rest all Coming soon..");
      BroserCacheChkBox_1.setSelected(true);
      BroserCacheChkBox_1.setFont(new Font("Tahoma", 1, 13));
      BroserCacheChkBox_1.setEnabled(false);
      BroserCacheChkBox_1.setBackground(Color.WHITE);
      BroserCacheChkBox_1.setBounds(36, 460, 219, 21);
      StorgaePanel.add(BroserCacheChkBox_1);
      JLabel lblNewLabel_16 = new JLabel("Browser Cache & Temp Files");
      lblNewLabel_16.setForeground(new Color(0, 0, 0));
      lblNewLabel_16.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_16.setBounds(74, 395, 219, 27);
      StorgaePanel.add(lblNewLabel_16);
      final JCheckBox CleanCookieChkBox_1 = new JCheckBox("Clear Cookies - All browser");
      CleanCookieChkBox_1.setToolTipText("Currently only supporting Brave, Chrome, Edge browsers Rest all Coming soon..");
      CleanCookieChkBox_1.setSelected(true);
      CleanCookieChkBox_1.setFont(new Font("Tahoma", 1, 13));
      CleanCookieChkBox_1.setEnabled(false);
      CleanCookieChkBox_1.setBackground(Color.WHITE);
      CleanCookieChkBox_1.setBounds(35, 524, 220, 21);
      StorgaePanel.add(CleanCookieChkBox_1);
      final JCheckBox historyChkBox_1 = new JCheckBox("Clear History - All Browser");
      historyChkBox_1.setSelected(true);
      historyChkBox_1.setFont(new Font("Tahoma", 1, 13));
      historyChkBox_1.setEnabled(false);
      historyChkBox_1.setBackground(Color.WHITE);
      historyChkBox_1.setBounds(35, 588, 220, 21);
      StorgaePanel.add(historyChkBox_1);
      final JLabel cacheCleanlbl_1 = new JLabel("0 KB");
      cacheCleanlbl_1.setForeground(new Color(58, 129, 255));
      cacheCleanlbl_1.setFont(new Font("Tahoma", 0, 14));
      cacheCleanlbl_1.setBounds(297, 457, 60, 25);
      StorgaePanel.add(cacheCleanlbl_1);
      final JLabel cookieCleanlbl_1 = new JLabel("0 KB");
      cookieCleanlbl_1.setForeground(new Color(58, 129, 255));
      cookieCleanlbl_1.setFont(new Font("Tahoma", 0, 14));
      cookieCleanlbl_1.setBounds(297, 521, 60, 25);
      StorgaePanel.add(cookieCleanlbl_1);
      final JLabel historyCleanlbl_1 = new JLabel("0 KB");
      historyCleanlbl_1.setForeground(new Color(58, 129, 255));
      historyCleanlbl_1.setFont(new Font("Tahoma", 0, 14));
      historyCleanlbl_1.setBounds(297, 585, 60, 25);
      StorgaePanel.add(historyCleanlbl_1);
      windowsSoftwareDistriChkBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (windowsSoftwareDistriChkBox.isSelected()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Before removing this folders content please check if Windows update is running or not. \n If its running please wait to finish installation of windows update \n then try to delete this folder content.", "Software Distribution Service", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
            }

         }
      });
      final JLabel hibernateSize = new JLabel("0 KB");
      hibernateSize.setForeground(new Color(58, 129, 255));
      hibernateSize.setFont(new Font("Tahoma", 0, 14));
      hibernateSize.setBounds(600, 722, 73, 25);
      StorgaePanel.add(hibernateSize);
      final JCheckBox select_DeselectChkBox = new JCheckBox("Select/ Deselect");
      select_DeselectChkBox.setBackground(new Color(255, 255, 255));
      select_DeselectChkBox.setForeground(new Color(58, 129, 255));
      select_DeselectChkBox.setFont(new Font("Tahoma", 0, 14));
      select_DeselectChkBox.setBounds(600, 151, 130, 21);
      select_DeselectChkBox.setEnabled(false);
      StorgaePanel.add(select_DeselectChkBox);
      select_DeselectChkBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            boolean status;
            if (select_DeselectChkBox.isSelected()) {
               status = true;
               delUsrTempFileChkBox.setSelected(status);
               delWinTempFileChkBox.setSelected(status);
               delPrefetchFileChkBox.setSelected(status);
               DelWinErrLogChkBox.setSelected(status);
               emptRecyclebinChkBox.setSelected(status);
               delMemoryDumpChkBox.setSelected(status);
               msStoreChkBox.setSelected(status);
               thumbCacheChkBox.setSelected(status);
               deliveryOptiChkBox.setSelected(status);
               windowsSoftwareDistriChkBox.setSelected(status);
               BroserCacheChkBox_1.setSelected(status);
               historyChkBox_1.setSelected(status);
               CleanCookieChkBox_1.setSelected(status);
            } else if (!select_DeselectChkBox.isSelected()) {
               status = false;
               delUsrTempFileChkBox.setSelected(status);
               delWinTempFileChkBox.setSelected(status);
               delPrefetchFileChkBox.setSelected(status);
               DelWinErrLogChkBox.setSelected(status);
               emptRecyclebinChkBox.setSelected(status);
               delMemoryDumpChkBox.setSelected(status);
               msStoreChkBox.setSelected(status);
               thumbCacheChkBox.setSelected(status);
               deliveryOptiChkBox.setSelected(status);
               windowsSoftwareDistriChkBox.setSelected(status);
               BroserCacheChkBox_1.setSelected(status);
               historyChkBox_1.setSelected(status);
               CleanCookieChkBox_1.setSelected(status);
            }

         }
      });
      TempFilesscanButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            delUsrTempFileChkBox.setEnabled(true);
            delWinTempFileChkBox.setEnabled(true);
            delPrefetchFileChkBox.setEnabled(true);
            DelWinErrLogChkBox.setEnabled(true);
            emptRecyclebinChkBox.setEnabled(true);
            delMemoryDumpChkBox.setEnabled(true);
            msStoreChkBox.setEnabled(true);
            thumbCacheChkBox.setEnabled(true);
            deliveryOptiChkBox.setEnabled(true);
            windowsSoftwareDistriChkBox.setEnabled(true);
            select_DeselectChkBox.setEnabled(true);
            select_DeselectChkBox.setEnabled(true);
            BroserCacheChkBox_1.setEnabled(true);
            historyChkBox_1.setEnabled(true);
            CleanCookieChkBox_1.setEnabled(true);
            MainProcessor mp = new MainProcessor();
            String fileSize = mp.tempDirSizeOnDisk();
            BrowserProcessor bp = new BrowserProcessor();
            String brFileSize = bp.tempDirSizeOnDisk();
            String[] ar1 = brFileSize.split("_");
            cacheCleanlbl_1.setText(ar1[1]);
            cookieCleanlbl_1.setText(ar1[2]);
            historyCleanlbl_1.setText(ar1[3]);
            String[] ar = fileSize.split("_");
            MainFrame.this.StorageMainMessageLbl.setText(mp.converter(Long.parseLong(ar[0]) + Long.parseLong(ar1[0].split(" ")[0])) + " to be removed. (Approximate Size)");
            DelUserTempFile.setText(ar[1]);
            DelWinTempFile.setText(ar[2]);
            DelPrefetchFile.setText(ar[4]);
            DelErrLogFile.setText(ar[3]);
            memoryDmpFileLbl.setText(ar[5]);
            msStoreLbl.setText(ar[6]);
            thumbIconSize.setText(ar[7]);
            delivOptimCacheLbl.setText(ar[8]);
            WinSoftDistTotalLbl.setText(ar[9]);
            hibernateSize.setText(ar[10]);
            delUsrTempFileChkBox.setSelected(false);
            delWinTempFileChkBox.setSelected(false);
            delPrefetchFileChkBox.setSelected(false);
            DelWinErrLogChkBox.setSelected(false);
            emptRecyclebinChkBox.setSelected(false);
            delMemoryDumpChkBox.setSelected(false);
            msStoreChkBox.setSelected(false);
            thumbCacheChkBox.setSelected(false);
            deliveryOptiChkBox.setSelected(false);
            windowsSoftwareDistriChkBox.setSelected(false);
            select_DeselectChkBox.setSelected(false);
            BroserCacheChkBox_1.setSelected(false);
            historyChkBox_1.setSelected(false);
            CleanCookieChkBox_1.setSelected(false);
            TempFilesscanButton.setEnabled(false);
            JOptionPane.showMessageDialog(MainFrame.this, "Select option/s and click on 'All Clean' to claim your storage space", "Conditional Delete", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      JLabel olderthanLbl = new JLabel("System Only delete files older than 24 Hrs.");
      olderthanLbl.setFont(new Font("Tahoma", 0, 14));
      olderthanLbl.setForeground(new Color(58, 129, 255));
      olderthanLbl.setVisible(true);
      olderthanLbl.setBounds(651, 337, 270, 31);
      StorgaePanel.add(olderthanLbl);
      JLabel lblNewLabel_1 = new JLabel("System Storage Maintanance");
      lblNewLabel_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_1.setBounds(97, 30, 318, 26);
      StorgaePanel.add(lblNewLabel_1);
      lblNewLabel_1.setHorizontalAlignment(2);
      lblNewLabel_1.setFont(new Font("Verdana", 1, 17));
      JButton StorageCleanBtn = new JButton("All Clean");
      StorageCleanBtn.setForeground(new Color(255, 255, 255));
      StorageCleanBtn.setBackground(new Color(55, 65, 82));
      StorageCleanBtn.setBounds(504, 641, 112, 40);
      StorgaePanel.add(StorageCleanBtn);
      StorageCleanBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent actEvnt) {
            MainFrame.this.StorageMainMessageLbl.setText("Processing! please wait..");
            MainProcessor mp = new MainProcessor();
            boolean a = delUsrTempFileChkBox.isSelected();
            boolean b = delWinTempFileChkBox.isSelected();
            boolean c = delPrefetchFileChkBox.isSelected();
            boolean d = DelWinErrLogChkBox.isSelected();
            boolean f = emptRecyclebinChkBox.isSelected();
            boolean e = msStoreChkBox.isSelected();
            boolean g = windowsSoftwareDistriChkBox.isSelected();
            boolean j = thumbCacheChkBox.isSelected();
            boolean k = delMemoryDumpChkBox.isSelected();
            boolean l = deliveryOptiChkBox.isSelected();
            boolean a1 = BroserCacheChkBox_1.isSelected();
            boolean b1 = historyChkBox_1.isSelected();
            boolean c1 = CleanCookieChkBox_1.isSelected();
            BrowserProcessor bp = new BrowserProcessor();
            boolean chkBoxSelectionStatus = a || b || c || d || e || f || g || j || k || l || a1 || b1 || c1;
            String output = "";
            int result = 0;
            if (chkBoxSelectionStatus) {
               output = mp.purgeUserTempDirs(a, b, c, d, e, g, f, j, k, l);
               result = bp.purgeBrowserData(a1, b1, c1);
               MainFrame.this.StorageMainMessageLbl.setText("System can't delete files in use or Created within 24 Hrs.");
               JOptionPane.showMessageDialog(MainFrame.this, "System storage reclaimed succesfully", "Conditional Delete", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Atleast select one option to proceed", "Conditional Delete", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
               delUsrTempFileChkBox.setSelected(true);
               delWinTempFileChkBox.setSelected(true);
               delPrefetchFileChkBox.setSelected(true);
               DelWinErrLogChkBox.setSelected(true);
               emptRecyclebinChkBox.setSelected(true);
               delMemoryDumpChkBox.setSelected(true);
               msStoreChkBox.setSelected(true);
               thumbCacheChkBox.setSelected(true);
               deliveryOptiChkBox.setSelected(true);
               windowsSoftwareDistriChkBox.setSelected(true);
               select_DeselectChkBox.setSelected(true);
               BroserCacheChkBox_1.setSelected(true);
               historyChkBox_1.setSelected(true);
               CleanCookieChkBox_1.setSelected(true);
            }

            try {
               if (result > 0 || !output.isEmpty()) {
                  delUsrTempFileChkBox.setEnabled(false);
                  delWinTempFileChkBox.setEnabled(false);
                  delPrefetchFileChkBox.setEnabled(false);
                  DelWinErrLogChkBox.setEnabled(false);
                  emptRecyclebinChkBox.setEnabled(false);
                  delMemoryDumpChkBox.setEnabled(false);
                  msStoreChkBox.setEnabled(false);
                  thumbCacheChkBox.setEnabled(false);
                  deliveryOptiChkBox.setEnabled(false);
                  windowsSoftwareDistriChkBox.setEnabled(false);
                  select_DeselectChkBox.setEnabled(false);
                  BroserCacheChkBox_1.setEnabled(false);
                  historyChkBox_1.setEnabled(false);
                  CleanCookieChkBox_1.setEnabled(false);
                  delUsrTempFileChkBox.setSelected(true);
                  delWinTempFileChkBox.setSelected(true);
                  delPrefetchFileChkBox.setSelected(true);
                  DelWinErrLogChkBox.setSelected(true);
                  emptRecyclebinChkBox.setSelected(true);
                  delMemoryDumpChkBox.setSelected(true);
                  msStoreChkBox.setSelected(true);
                  thumbCacheChkBox.setSelected(true);
                  deliveryOptiChkBox.setSelected(true);
                  windowsSoftwareDistriChkBox.setSelected(true);
                  select_DeselectChkBox.setSelected(true);
                  BroserCacheChkBox_1.setSelected(true);
                  historyChkBox_1.setSelected(true);
                  CleanCookieChkBox_1.setSelected(true);
                  TempFilesscanButton.setEnabled(true);
               }
            } catch (Exception var21) {
               MainFrame.this.logger.info("50.2 : " + var21.getMessage());
            }

         }
      });
      StorageCleanBtn.setFont(new Font("Tahoma", 1, 12));
      this.StorageMainMessageLbl = new JLabel("Manage System Storage : Remove Unwanted Files");
      this.StorageMainMessageLbl.setBackground(new Color(255, 255, 255));
      this.StorageMainMessageLbl.setForeground(new Color(58, 129, 255));
      this.StorageMainMessageLbl.setFont(new Font("Tahoma", 0, 14));
      this.StorageMainMessageLbl.setBounds(97, 55, 576, 25);
      StorgaePanel.add(this.StorageMainMessageLbl);
      StorgaePanel.add(thumbIconSize);
      JLabel StoragemainImage = new JLabel("");
      StoragemainImage.setHorizontalAlignment(0);
      StoragemainImage.setBounds(45, 35, 40, 40);
      StoragemainImage.setIcon(createImageIcon("/com/resource/storage32.png", "Storage"));
      StorgaePanel.add(StoragemainImage);
      JLabel lblNewLabel_18 = new JLabel("System Cache & Temp Files");
      lblNewLabel_18.setForeground(new Color(0, 0, 0));
      lblNewLabel_18.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_18.setBounds(74, 132, 266, 21);
      StorgaePanel.add(lblNewLabel_18);
      final JPanel startupAppsPanelPart1 = new JPanel();
      startupAppsPanelPart1.setBackground(new Color(255, 255, 255));
      startupAppsPanelPart1.setBounds(1942, 408, 90, 95);
      startupAppsPanelPart1.setVisible(false);
      startupAppsPanelPart1.setAutoscrolls(true);
      startupAppsPanelPart1.setLayout((LayoutManager)null);
      this.contentPane.add(startupAppsPanelPart1);
      JLabel lblNewLabel_17 = new JLabel("Remove all unwanted temp files to get more space in your drive");
      lblNewLabel_17.setForeground(new Color(0, 0, 0));
      lblNewLabel_17.setBackground(new Color(58, 129, 255));
      lblNewLabel_17.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_17.setBounds(74, 150, 478, 25);
      StorgaePanel.add(lblNewLabel_17);
      JSeparator separator_1 = new JSeparator();
      separator_1.setForeground(new Color(0, 0, 0));
      separator_1.setBounds(32, 383, 921, 2);
      StorgaePanel.add(separator_1);
      JLabel lblNewLabel_17_1 = new JLabel("Remove unwanted browsers temp files to get more space in your drive");
      lblNewLabel_17_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_17_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_17_1.setBackground(new Color(58, 129, 255));
      lblNewLabel_17_1.setBounds(74, 422, 478, 25);
      StorgaePanel.add(lblNewLabel_17_1);
      final JLabel DriveDetailsLbl = new JLabel("");
      DriveDetailsLbl.setHorizontalAlignment(0);
      DriveDetailsLbl.setFont(new Font("Gadugi", 0, 12));
      DriveDetailsLbl.setBounds(554, 416, 147, 166);
      StorgaePanel.add(DriveDetailsLbl);
      JLabel datalable = new JLabel(".");
      datalable.setBounds(167, 10, 9, 13);
      datalable.setVisible(false);
      startupAppsPanelPart1.add(datalable);
      final JCheckBox restorePointChkBox = new JCheckBox("Create System Restoration Point");
      restorePointChkBox.setBackground(new Color(255, 255, 255));
      restorePointChkBox.setFont(new Font("Tahoma", 1, 14));
      restorePointChkBox.setBounds(84, 112, 298, 32);
      startupAppsPanelPart1.add(restorePointChkBox);
      JLabel firstTimeMainLbl = new JLabel("Best System Setup Part-1");
      firstTimeMainLbl.setBackground(new Color(0, 0, 0));
      firstTimeMainLbl.setForeground(new Color(0, 0, 0));
      firstTimeMainLbl.setFont(new Font("Verdana", 1, 17));
      firstTimeMainLbl.setBounds(97, 35, 292, 32);
      startupAppsPanelPart1.add(firstTimeMainLbl);
      final JLabel firstTimeSetupLbl = new JLabel("");
      firstTimeSetupLbl.setBackground(new Color(58, 129, 255));
      firstTimeSetupLbl.setForeground(new Color(58, 129, 255));
      firstTimeSetupLbl.setFont(new Font("Tahoma", 0, 14));
      firstTimeSetupLbl.setBounds(97, 61, 323, 25);
      startupAppsPanelPart1.add(firstTimeSetupLbl);
      final JCheckBox updateWinChkBox = new JCheckBox("Download Windows Updates");
      updateWinChkBox.setBackground(new Color(255, 255, 255));
      updateWinChkBox.setFont(new Font("Tahoma", 1, 14));
      updateWinChkBox.setBounds(83, 226, 289, 29);
      startupAppsPanelPart1.add(updateWinChkBox);
      final JCheckBox policyChkBox = new JCheckBox("Disable Unwanted Policies");
      policyChkBox.setBackground(new Color(255, 255, 255));
      policyChkBox.setFont(new Font("Tahoma", 1, 14));
      policyChkBox.setBounds(93, 493, 271, 21);
      startupAppsPanelPart1.add(policyChkBox);
      final JCheckBox sysMainChkBox_1 = new JCheckBox("SysMain");
      sysMainChkBox_1.setFont(new Font("Tahoma", 0, 14));
      sysMainChkBox_1.setBackground(Color.WHITE);
      sysMainChkBox_1.setBounds(83, 365, 91, 21);
      startupAppsPanelPart1.add(sysMainChkBox_1);
      final JCheckBox TapiSrvchkBox_1 = new JCheckBox("Telephony");
      TapiSrvchkBox_1.setFont(new Font("Tahoma", 0, 12));
      TapiSrvchkBox_1.setBackground(Color.WHITE);
      TapiSrvchkBox_1.setBounds(422, 365, 91, 21);
      startupAppsPanelPart1.add(TapiSrvchkBox_1);
      final JCheckBox searchChkBox_1 = new JCheckBox("Search");
      searchChkBox_1.setFont(new Font("Tahoma", 0, 12));
      searchChkBox_1.setBackground(Color.WHITE);
      searchChkBox_1.setBounds(594, 365, 69, 21);
      startupAppsPanelPart1.add(searchChkBox_1);
      final JCheckBox icssvcChkBox_1 = new JCheckBox("Mobile Hotspot");
      icssvcChkBox_1.setToolTipText("Mobile hotspot");
      icssvcChkBox_1.setFont(new Font("Tahoma", 0, 12));
      icssvcChkBox_1.setBackground(Color.WHITE);
      icssvcChkBox_1.setBounds(83, 396, 119, 21);
      startupAppsPanelPart1.add(icssvcChkBox_1);
      final JCheckBox faxChkBox_1 = new JCheckBox("FAX");
      faxChkBox_1.setFont(new Font("Tahoma", 0, 12));
      faxChkBox_1.setBackground(Color.WHITE);
      faxChkBox_1.setBounds(783, 365, 59, 21);
      startupAppsPanelPart1.add(faxChkBox_1);
      final JCheckBox touchKbndHandChkBox_1 = new JCheckBox("Touch Kb & Pen");
      touchKbndHandChkBox_1.setToolTipText("Touch Keyboard and Handwriting Panel pen and ink");
      touchKbndHandChkBox_1.setFont(new Font("Tahoma", 0, 11));
      touchKbndHandChkBox_1.setBackground(Color.WHITE);
      touchKbndHandChkBox_1.setBounds(270, 397, 105, 21);
      startupAppsPanelPart1.add(touchKbndHandChkBox_1);
      final JCheckBox remoteRegChkBox_1 = new JCheckBox("Remote Registry");
      remoteRegChkBox_1.setToolTipText("Enables remote users to modify registry on this computer");
      remoteRegChkBox_1.setFont(new Font("Tahoma", 0, 12));
      remoteRegChkBox_1.setBackground(Color.WHITE);
      remoteRegChkBox_1.setBounds(595, 396, 119, 21);
      startupAppsPanelPart1.add(remoteRegChkBox_1);
      JSeparator separator_1_1_1 = new JSeparator();
      separator_1_1_1.setOpaque(true);
      separator_1_1_1.setBackground(new Color(255, 255, 255));
      separator_1_1_1.setBounds(22, 470, 912, 3);
      startupAppsPanelPart1.add(separator_1_1_1);
      String[] browsersList = new String[]{"Google", "CloudFlare", "Open DNS", "Level 3"};
      JLabel lblNewLabel_9 = new JLabel(createImageIcon("/com/resource/setup32.png", "time"));
      lblNewLabel_9.setHorizontalAlignment(0);
      lblNewLabel_9.setBounds(45, 33, 40, 40);
      startupAppsPanelPart1.add(lblNewLabel_9);
      final JPanel BrowserPanel = new JPanel();
      BrowserPanel.setForeground(new Color(58, 129, 255));
      BrowserPanel.setBounds(1932, 629, 100, 89);
      this.contentPane.add(BrowserPanel);
      BrowserPanel.setBorder((Border)null);
      BrowserPanel.setBackground(new Color(255, 255, 255));
      BrowserPanel.setLayout((LayoutManager)null);
      BrowserPanel.setVisible(false);
      JButton btnNewButton = new JButton("Disable");
      btnNewButton.setForeground(new Color(255, 255, 255));
      btnNewButton.setBounds(823, 421, 110, 35);
      startupAppsPanelPart1.add(btnNewButton);
      btnNewButton.setFont(new Font("Tahoma", 0, 12));
      btnNewButton.setBackground(new Color(55, 65, 82));
      final JCheckBox cuetchkBox_1 = new JCheckBox("CUET");
      cuetchkBox_1.setToolTipText("Connected User Experiences and Telemetry");
      cuetchkBox_1.setFont(new Font("Tahoma", 0, 12));
      cuetchkBox_1.setBackground(Color.WHITE);
      cuetchkBox_1.setBounds(270, 365, 69, 21);
      startupAppsPanelPart1.add(cuetchkBox_1);
      JLabel lblNewLabel_33 = new JLabel("<html>Creates a restoration point of your system , So you can revert changes to this system state.</html>");
      lblNewLabel_33.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33.setToolTipText("System Restoration Poin.");
      lblNewLabel_33.setBounds(94, 141, 261, 38);
      startupAppsPanelPart1.add(lblNewLabel_33);
      JSeparator separator_1_1_1_1 = new JSeparator();
      separator_1_1_1_1.setOpaque(true);
      separator_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1.setBounds(43, 206, 901, 3);
      startupAppsPanelPart1.add(separator_1_1_1_1);
      JLabel lblNewLabel_33_2 = new JLabel("Downloads Windows Update and install.");
      lblNewLabel_33_2.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2.setBounds(93, 255, 271, 25);
      startupAppsPanelPart1.add(lblNewLabel_33_2);
      JSeparator separator_1_1_1_1_3 = new JSeparator();
      separator_1_1_1_1_3.setOpaque(true);
      separator_1_1_1_1_3.setBackground(Color.WHITE);
      separator_1_1_1_1_3.setBounds(32, 307, 901, 3);
      startupAppsPanelPart1.add(separator_1_1_1_1_3);
      JLabel lblNewLabel_33_2_1 = new JLabel("<html>Creates a restoration point of your system , So you can revert changes to this system state.</html>");
      lblNewLabel_33_2_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_1.setBounds(103, 512, 289, 38);
      startupAppsPanelPart1.add(lblNewLabel_33_2_1);
      JLabel lblNewLabel_34 = new JLabel("Disable Unwanted Services from background");
      lblNewLabel_34.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_34.setBounds(83, 320, 373, 28);
      startupAppsPanelPart1.add(lblNewLabel_34);
      JLabel lblNewLabel_33_2_2 = new JLabel("It will stop all the selected services at once.");
      lblNewLabel_33_2_2.setForeground(new Color(58, 129, 255));
      lblNewLabel_33_2_2.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2.setBounds(549, 430, 261, 25);
      startupAppsPanelPart1.add(lblNewLabel_33_2_2);
      JSeparator separator_1_1_1_2 = new JSeparator();
      separator_1_1_1_2.setOpaque(true);
      separator_1_1_1_2.setBackground(Color.WHITE);
      separator_1_1_1_2.setBounds(22, 573, 912, 3);
      startupAppsPanelPart1.add(separator_1_1_1_2);
      JSeparator separator_1_1_1_2_1 = new JSeparator();
      separator_1_1_1_2_1.setOpaque(true);
      separator_1_1_1_2_1.setBackground(Color.WHITE);
      separator_1_1_1_2_1.setBounds(32, 681, 912, 3);
      startupAppsPanelPart1.add(separator_1_1_1_2_1);
      JLabel lblNewLabel_36_1_1_1_2 = new JLabel(createImageIcon("/com/resource/history.png", "history"));
      lblNewLabel_36_1_1_1_2.setBounds(48, 124, 30, 32);
      startupAppsPanelPart1.add(lblNewLabel_36_1_1_1_2);
      JLabel lblNewLabel_36_1_1_1_2_1 = new JLabel(createImageIcon("/com/resource/windwos.png", "windwos"));
      lblNewLabel_36_1_1_1_2_1.setBounds(47, 236, 30, 32);
      startupAppsPanelPart1.add(lblNewLabel_36_1_1_1_2_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1 = new JLabel(createImageIcon("/com/resource/process.png", "process"));
      lblNewLabel_36_1_1_1_2_1_1.setBounds(47, 326, 30, 32);
      startupAppsPanelPart1.add(lblNewLabel_36_1_1_1_2_1_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_1_1 = new JLabel(createImageIcon("/com/resource/puzzle.png", "puzzle"));
      lblNewLabel_36_1_1_1_2_1_1_1_1_1.setBounds(58, 497, 30, 32);
      startupAppsPanelPart1.add(lblNewLabel_36_1_1_1_2_1_1_1_1_1);
      JLabel linktoSiteLbl_2 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl_2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent arg0) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2.setHorizontalAlignment(0);
      linktoSiteLbl_2.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2.setBounds(810, 32, 184, 36);
      startupAppsPanelPart1.add(linktoSiteLbl_2);
      JLabel lblNewLabel_6_1_1 = new JLabel("Update All Applications");
      lblNewLabel_6_1_1.setToolTipText("Disable this for better performance.");
      lblNewLabel_6_1_1.setForeground(Color.BLACK);
      lblNewLabel_6_1_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_6_1_1.setBounds(95, 595, 205, 26);
      startupAppsPanelPart1.add(lblNewLabel_6_1_1);
      JLabel lblNewLabel_33_2_2_1_2 = new JLabel("Upgrade all MS Store listed applications in one click.");
      lblNewLabel_33_2_2_1_2.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2_1_2.setBounds(95, 614, 323, 38);
      startupAppsPanelPart1.add(lblNewLabel_33_2_2_1_2);
      JButton btnUpgrade = new JButton("Upgrade", (Icon)null);
      btnUpgrade.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (!licenceCheck) {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "Licence Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            } else if (licenceCheck) {
               FirstTimeProcess pp = new FirstTimeProcess();
               pp.upgradeApps();
            }

         }
      });
      btnUpgrade.setForeground(Color.WHITE);
      btnUpgrade.setFont(new Font("Tahoma", 0, 12));
      btnUpgrade.setBackground(new Color(55, 65, 82));
      btnUpgrade.setBounds(823, 610, 110, 35);
      startupAppsPanelPart1.add(btnUpgrade);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_2 = new JLabel(createImageIcon("/com/resource/Process1.png", "upgradeApplication"));
      lblNewLabel_36_1_1_1_2_1_1_1_2.setBounds(55, 604, 30, 32);
      startupAppsPanelPart1.add(lblNewLabel_36_1_1_1_2_1_1_1_2);
      JButton btnCheckList = new JButton("Check List", (Icon)null);
      btnCheckList.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            FirstTimeProcess pp = new FirstTimeProcess();
            pp.listAppsToUpgrade();
         }
      });
      btnCheckList.setForeground(Color.WHITE);
      btnCheckList.setFont(new Font("Tahoma", 0, 12));
      btnCheckList.setBackground(new Color(55, 65, 82));
      btnCheckList.setBounds(670, 610, 110, 35);
      startupAppsPanelPart1.add(btnCheckList);
      JButton restorePointBtn = new JButton("Setup Now", (Icon)null);
      restorePointBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (restorePointChkBox.isSelected()) {
               FirstTimeProcess pp = new FirstTimeProcess();
               pp.SystemRestore();
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Please select the checkbox to proceed.", "Create Restoration Point", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
            }

         }
      });
      restorePointBtn.setForeground(Color.WHITE);
      restorePointBtn.setFont(new Font("Tahoma", 0, 12));
      restorePointBtn.setBackground(new Color(55, 65, 82));
      restorePointBtn.setBounds(824, 131, 110, 35);
      startupAppsPanelPart1.add(restorePointBtn);
      JButton winUpdateBtn = new JButton("Setup Now", (Icon)null);
      winUpdateBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this, "Please wait! It may take some time to start.", "Update Windows", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
            if (updateWinChkBox.isSelected()) {
               FirstTimeProcess pp = new FirstTimeProcess();
               pp.widnwsUpdate();
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Please select the checkbox to proceed.", "Update Windows", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
            }

         }
      });
      winUpdateBtn.setForeground(Color.WHITE);
      winUpdateBtn.setFont(new Font("Tahoma", 0, 12));
      winUpdateBtn.setBackground(new Color(55, 65, 82));
      winUpdateBtn.setBounds(823, 245, 110, 35);
      startupAppsPanelPart1.add(winUpdateBtn);
      JButton btnNewButton_4 = new JButton("Setup Now", (Icon)null);
      btnNewButton_4.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (policyChkBox.isSelected()) {
               if (!licenceCheck) {
                  JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "License Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
               } else if (licenceCheck) {
                  FirstTimeProcess pp = new FirstTimeProcess();
                  JOptionPane.showMessageDialog(MainFrame.this, pp.disabledPolicy(), "Disable Unwanted Policies", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
                  policyChkBox.setSelected(false);
               }
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Please select the checkbox to proceed.", "Disable Unwanted Policies", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/service.png")));
            }

         }
      });
      btnNewButton_4.setForeground(Color.WHITE);
      btnNewButton_4.setFont(new Font("Tahoma", 0, 12));
      btnNewButton_4.setBackground(new Color(55, 65, 82));
      btnNewButton_4.setBounds(823, 505, 110, 35);
      startupAppsPanelPart1.add(btnNewButton_4);
      final JPanel startupAppsPanelPart2 = new JPanel();
      startupAppsPanelPart2.setForeground(new Color(255, 255, 255));
      startupAppsPanelPart2.setBackground(new Color(255, 255, 255));
      startupAppsPanelPart2.setBounds(1829, 171, 90, 89);
      this.contentPane.add(startupAppsPanelPart2);
      startupAppsPanelPart2.setVisible(false);
      final JButton widgetBtn_1 = new JButton("");
      JButton btnStage = new JButton(">>", (Icon)null);
      btnStage.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SystemPerformanceUtillity i = new SystemPerformanceUtillity();
            widgetBtn_1.setText(i.checkWidgetStatus());
            startupAppsPanelPart2.setVisible(true);
            startupAppsPanelPart2.setBounds(205, 45, 1000, 810);
            startupAppsPanelPart1.setVisible(false);
         }
      });
      btnStage.setForeground(Color.WHITE);
      btnStage.setFont(new Font("Tahoma", 0, 12));
      btnStage.setBackground(new Color(0, 64, 128));
      btnStage.setBounds(866, 714, 69, 32);
      startupAppsPanelPart1.add(btnStage);
      JCheckBox AllJoynchkBox = new JCheckBox("ALLJoyn");
      AllJoynchkBox.setToolTipText("AllJoyn Router Services");
      AllJoynchkBox.setFont(new Font("Tahoma", 0, 11));
      AllJoynchkBox.setBackground(Color.WHITE);
      AllJoynchkBox.setBounds(422, 397, 105, 21);
      startupAppsPanelPart1.add(AllJoynchkBox);
      JLabel lblActivityMayTake_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1.setBounds(241, 80, 490, 20);
      startupAppsPanelPart1.add(lblActivityMayTake_1);
      lblActivityMayTake_1.setHorizontalAlignment(0);
      lblActivityMayTake_1.setForeground(Color.RED);
      lblActivityMayTake_1.setFont(new Font("Tahoma", 1, 14));
      final JCheckBox gamebarchkbox = new JCheckBox("ALJoyn");
      gamebarchkbox.setBackground(new Color(255, 255, 255));
      gamebarchkbox.setFont(new Font("Tahoma", 0, 14));
      gamebarchkbox.setBounds(289, 249, 93, 21);
      gamebarchkbox.setToolTipText("ALJoyn Rounter Services");
      perfPannel.add(gamebarchkbox);
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ArrayList<String> s = new ArrayList();
            if (sysMainChkBox_1.isSelected()) {
               s.add("SysMain");
            }

            if (searchChkBox_1.isSelected()) {
               s.add("WSearch");
            }

            if (icssvcChkBox_1.isSelected()) {
               s.add("icssvc");
            }

            if (faxChkBox_1.isSelected()) {
               s.add("fax");
            }

            if (touchKbndHandChkBox_1.isSelected()) {
               s.add("TabletInputService");
            }

            if (remoteRegChkBox_1.isSelected()) {
               s.add("remote Registry");
            }

            if (TapiSrvchkBox_1.isSelected()) {
               s.add("TapiSrv");
            }

            if (cuetchkBox_1.isSelected()) {
               s.add("DiagTrack");
            }

            if (gamebarchkbox.isSelected()) {
               s.add("AJRouter");
            }

            if (!licenceCheck) {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "License Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            } else if (licenceCheck) {
               SystemPerformanceProcess spp = new SystemPerformanceProcess();
               spp.startStopServices(s, "stop");
               JOptionPane.showMessageDialog(MainFrame.this, "Selected services stopped successfully", "Stop Services", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            }

         }
      });
      final JCheckBox icssvcChkBox = new JCheckBox("Mobile Hotspot");
      icssvcChkBox.setBackground(new Color(255, 255, 255));
      icssvcChkBox.setFont(new Font("Tahoma", 0, 14));
      icssvcChkBox.setBounds(796, 249, 141, 21);
      icssvcChkBox.setToolTipText("Mobile hotspot");
      perfPannel.add(icssvcChkBox);
      final JCheckBox faxChkBox = new JCheckBox("FAX");
      faxChkBox.setBackground(new Color(255, 255, 255));
      faxChkBox.setFont(new Font("Tahoma", 0, 14));
      faxChkBox.setBounds(648, 219, 59, 21);
      perfPannel.add(faxChkBox);
      final JCheckBox wisvcChkBox = new JCheckBox("wisvc");
      wisvcChkBox.setBackground(new Color(255, 255, 255));
      wisvcChkBox.setFont(new Font("Tahoma", 0, 14));
      wisvcChkBox.setToolTipText("Windows Insider Services");
      wisvcChkBox.setBounds(87, 219, 69, 21);
      perfPannel.add(wisvcChkBox);
      final JCheckBox teamServicesChkBox = new JCheckBox("Remote Desktop");
      teamServicesChkBox.setBackground(new Color(255, 255, 255));
      teamServicesChkBox.setFont(new Font("Tahoma", 0, 14));
      teamServicesChkBox.setBounds(289, 219, 130, 21);
      perfPannel.add(teamServicesChkBox);
      final JCheckBox remoteRegChkBox = new JCheckBox("Remote Registry");
      remoteRegChkBox.setBackground(new Color(255, 255, 255));
      remoteRegChkBox.setFont(new Font("Tahoma", 0, 14));
      remoteRegChkBox.setBounds(87, 249, 130, 21);
      remoteRegChkBox.setToolTipText("Enables remote users to modify registry on this computer");
      perfPannel.add(remoteRegChkBox);
      final JCheckBox retailDemoChkBox = new JCheckBox("Retail Demo");
      retailDemoChkBox.setBackground(new Color(255, 255, 255));
      retailDemoChkBox.setFont(new Font("Tahoma", 0, 14));
      retailDemoChkBox.setBounds(796, 219, 141, 21);
      perfPannel.add(retailDemoChkBox);
      final JCheckBox sysMainChkBox = new JCheckBox("SysMain");
      sysMainChkBox.setBackground(new Color(255, 255, 255));
      sysMainChkBox.setFont(new Font("Tahoma", 0, 14));
      sysMainChkBox.setBounds(796, 188, 77, 21);
      perfPannel.add(sysMainChkBox);
      final JCheckBox searchChkBox = new JCheckBox("Search");
      searchChkBox.setBackground(new Color(255, 255, 255));
      searchChkBox.setFont(new Font("Tahoma", 0, 14));
      searchChkBox.setBounds(648, 188, 69, 21);
      perfPannel.add(searchChkBox);
      final JCheckBox TapiSrvchkBox = new JCheckBox("Telephony");
      TapiSrvchkBox.setBackground(new Color(255, 255, 255));
      TapiSrvchkBox.setFont(new Font("Tahoma", 0, 14));
      TapiSrvchkBox.setBounds(480, 188, 91, 21);
      perfPannel.add(TapiSrvchkBox);
      final JCheckBox cuetchkBox = new JCheckBox("CUET");
      cuetchkBox.setToolTipText("Connected User Experiences and Telemetry");
      cuetchkBox.setBackground(new Color(255, 255, 255));
      cuetchkBox.setFont(new Font("Tahoma", 0, 14));
      cuetchkBox.setBounds(87, 188, 69, 21);
      perfPannel.add(cuetchkBox);
      String[] browsersList1 = new String[]{"Google", "CloudFlare", "Open DNS", "Level 3"};
      final JButton widgetBtn = new JButton(btnVal);
      widgetBtn.setBackground(new Color(55, 65, 82));
      widgetBtn.setForeground(new Color(255, 255, 255));
      widgetBtn.setFont(new Font("Tahoma", 0, 14));
      widgetBtn.setBounds(332, 382, 120, 35);
      perfPannel.add(widgetBtn);
      JLabel lblNewLabel_5 = new JLabel("Improve System Performance");
      lblNewLabel_5.setHorizontalAlignment(2);
      lblNewLabel_5.setFont(new Font("Verdana", 1, 17));
      lblNewLabel_5.setBounds(97, 26, 300, 26);
      perfPannel.add(lblNewLabel_5);
      final JLabel sysPerfInfoLbl = new JLabel("Manage System Performance and Issues");
      sysPerfInfoLbl.setHorizontalAlignment(2);
      sysPerfInfoLbl.setForeground(new Color(58, 129, 255));
      sysPerfInfoLbl.setFont(new Font("Tahoma", 0, 14));
      sysPerfInfoLbl.setBounds(97, 50, 364, 25);
      perfPannel.add(sysPerfInfoLbl);
      final JCheckBox smartCardChkBox = new JCheckBox("Smart Card");
      smartCardChkBox.setBackground(new Color(255, 255, 255));
      smartCardChkBox.setFont(new Font("Tahoma", 0, 14));
      smartCardChkBox.setBounds(480, 219, 100, 21);
      perfPannel.add(smartCardChkBox);
      final JCheckBox MapsBrokerchkBox = new JCheckBox("Maps Broker");
      MapsBrokerchkBox.setBackground(new Color(255, 255, 255));
      MapsBrokerchkBox.setFont(new Font("Tahoma", 0, 14));
      MapsBrokerchkBox.setBounds(480, 249, 130, 21);
      MapsBrokerchkBox.setToolTipText("Downloaded Maps Manager");
      perfPannel.add(MapsBrokerchkBox);
      final JCheckBox CertPropSvcchkBox = new JCheckBox("CP Service");
      CertPropSvcchkBox.setFont(new Font("Tahoma", 0, 14));
      CertPropSvcchkBox.setBackground(new Color(255, 255, 255));
      CertPropSvcchkBox.setBounds(648, 249, 91, 21);
      CertPropSvcchkBox.setToolTipText("Certificate Property Service");
      perfPannel.add(CertPropSvcchkBox);
      final JCheckBox touchKbndHandChkBox = new JCheckBox("Touch Kb & Pen");
      touchKbndHandChkBox.setBackground(new Color(255, 255, 255));
      touchKbndHandChkBox.setFont(new Font("Tahoma", 0, 14));
      touchKbndHandChkBox.setBounds(289, 188, 130, 21);
      touchKbndHandChkBox.setToolTipText("Touch Keyboard and Pen Services");
      perfPannel.add(touchKbndHandChkBox);
      final JButton stopServiceBtn = new JButton("Stop Services");
      stopServiceBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sysPerfInfoLbl.setText("Processing Please wait..");
            ArrayList<String> s = new ArrayList();
            if (sysMainChkBox.isSelected()) {
               s.add("SysMain");
            }

            if (searchChkBox.isSelected()) {
               s.add("WSearch");
            }

            if (icssvcChkBox.isSelected()) {
               s.add("icssvc");
            }

            if (faxChkBox.isSelected()) {
               s.add("fax");
            }

            if (wisvcChkBox.isSelected()) {
               s.add(wisvcChkBox.getText());
            }

            if (teamServicesChkBox.isSelected()) {
               s.add("TermService");
            }

            if (remoteRegChkBox.isSelected()) {
               s.add("RemoteRegistry");
            }

            if (retailDemoChkBox.isSelected()) {
               s.add("RetailDemo");
            }

            if (TapiSrvchkBox.isSelected()) {
               s.add("TapiSrv");
            }

            if (smartCardChkBox.isSelected()) {
               s.add("SCardSvr");
            }

            if (cuetchkBox.isSelected()) {
               s.add("DiagTrack");
            }

            if (MapsBrokerchkBox.isSelected()) {
               s.add("MapsBroker");
            }

            if (CertPropSvcchkBox.isSelected()) {
               s.add("CertPropSvc");
            }

            if (gamebarchkbox.isSelected()) {
               s.add("AJRouter");
            }

            if (touchKbndHandChkBox.isSelected()) {
               s.add("TabletInputService");
            }

            SystemPerformanceProcess spp = new SystemPerformanceProcess();
            if ("sucess".equals(spp.startStopServices(s, "stop"))) {
               sysPerfInfoLbl.setText("Selected Process stoped");
            }

            JOptionPane.showMessageDialog(MainFrame.this, "Selected services stopped successfully", "Windows Services", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      stopServiceBtn.setForeground(new Color(255, 255, 255));
      stopServiceBtn.setBackground(new Color(55, 65, 82));
      stopServiceBtn.setFont(new Font("Tahoma", 0, 12));
      stopServiceBtn.setBounds(332, 302, 120, 35);
      perfPannel.add(stopServiceBtn);
      JLabel lblNewLabel_6 = new JLabel("Enable/ Disable Widgets");
      lblNewLabel_6.setForeground(new Color(0, 0, 0));
      lblNewLabel_6.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_6.setBounds(92, 378, 189, 26);
      lblNewLabel_6.setToolTipText("Disable this for better performance.");
      perfPannel.add(lblNewLabel_6);
      widgetBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String ss = widgetBtn.getText().equals("Enable") ? "Disable" : "Enable";
            widgetBtn.setText(ss);
            SystemPerformanceProcess spp = new SystemPerformanceProcess();
            sysPerfInfoLbl.setText(spp.disbaleWidgets(widgetBtn.getText()));
            String statusVal = ss.equals("Enable") ? "Disable" : "Enable";
            JOptionPane.showMessageDialog(MainFrame.this, "Windows widget " + statusVal + "d successfully", "Windows Services", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      JLabel lblNewLabel_7 = new JLabel("Resolve System Integrity Issues");
      lblNewLabel_7.setForeground(new Color(0, 0, 0));
      lblNewLabel_7.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_7.setBounds(92, 451, 300, 28);
      perfPannel.add(lblNewLabel_7);
      JButton sfcScanButton = new JButton("Scan Now");
      sfcScanButton.setBackground(new Color(55, 65, 82));
      sfcScanButton.setForeground(new Color(255, 255, 255));
      sfcScanButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sysPerfInfoLbl.setText("Processing..Please Wait");
            SystemPerformanceProcess spf = new SystemPerformanceProcess();
            sysPerfInfoLbl.setText(spf.runTask("sfc"));
         }
      });
      sfcScanButton.setFont(new Font("Tahoma", 0, 14));
      sfcScanButton.setBounds(796, 461, 120, 35);
      perfPannel.add(sfcScanButton);
      JLabel lblNewLabel_8 = new JLabel("Scan and Repair System Issues");
      lblNewLabel_8.setBackground(new Color(0, 0, 0));
      lblNewLabel_8.setForeground(new Color(0, 0, 0));
      lblNewLabel_8.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_8.setBounds(92, 540, 279, 26);
      perfPannel.add(lblNewLabel_8);
      JButton dismScanButton = new JButton("Scan Now");
      dismScanButton.setBackground(new Color(55, 65, 82));
      dismScanButton.setForeground(new Color(255, 255, 255));
      dismScanButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (!licenceCheck) {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "License Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            } else if (licenceCheck) {
               sysPerfInfoLbl.setText("Processing..Please Wait");
               SystemPerformanceProcess spf = new SystemPerformanceProcess();
               sysPerfInfoLbl.setText(spf.runTask("dism"));
            }

         }
      });
      dismScanButton.setFont(new Font("Tahoma", 0, 14));
      dismScanButton.setBounds(796, 551, 120, 35);
      perfPannel.add(dismScanButton);
      JLabel sysPerfImgLbl = new JLabel(createImageIcon("/com/resource/growth32.png", "Performance"));
      sysPerfImgLbl.setBounds(45, 35, 40, 40);
      perfPannel.add(sysPerfImgLbl);
      final JButton startServiceBtn = new JButton("<html>Start Services</html>");
      startServiceBtn.setFont(new Font("Tahoma", 0, 12));
      startServiceBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            sysPerfInfoLbl.setText("Processing Please wait..");
            ArrayList<String> s = new ArrayList();
            if (sysMainChkBox.isSelected()) {
               s.add("SysMain");
            }

            if (searchChkBox.isSelected()) {
               s.add("WSearch");
            }

            if (icssvcChkBox.isSelected()) {
               s.add("icssvc");
            }

            if (faxChkBox.isSelected()) {
               s.add("fax");
            }

            if (wisvcChkBox.isSelected()) {
               s.add(wisvcChkBox.getText());
            }

            if (teamServicesChkBox.isSelected()) {
               s.add("TermService");
            }

            if (remoteRegChkBox.isSelected()) {
               s.add("RemoteRegistry");
            }

            if (retailDemoChkBox.isSelected()) {
               s.add("RetailDemo");
            }

            if (TapiSrvchkBox.isSelected()) {
               s.add("TapiSrv");
            }

            if (smartCardChkBox.isSelected()) {
               s.add("SCardSvr");
            }

            if (cuetchkBox.isSelected()) {
               s.add("DiagTrack");
            }

            if (MapsBrokerchkBox.isSelected()) {
               s.add("MapsBroker");
            }

            if (CertPropSvcchkBox.isSelected()) {
               s.add("CertPropSvc");
            }

            if (gamebarchkbox.isSelected()) {
               s.add("AJRouter");
            }

            if (touchKbndHandChkBox.isSelected()) {
               s.add("TabletInputService");
            }

            SystemPerformanceProcess spp = new SystemPerformanceProcess();
            if ("sucess".equals(spp.startStopServices(s, "start"))) {
               sysPerfInfoLbl.setText("Selected Process started");
            }

            JOptionPane.showMessageDialog(MainFrame.this, "Selected services started successfully", "Windows Services", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      startServiceBtn.setBackground(new Color(55, 65, 82));
      startServiceBtn.setForeground(new Color(255, 255, 255));
      startServiceBtn.setBounds(554, 302, 120, 35);
      perfPannel.add(startServiceBtn);
      JSeparator separator_1_1 = new JSeparator();
      separator_1_1.setOpaque(true);
      separator_1_1.setBackground(Color.BLACK);
      separator_1_1.setBounds(38, 354, 923, 1);
      perfPannel.add(separator_1_1);
      JLabel lblNewLabel_10 = new JLabel("List All Startup Apps To Disable");
      lblNewLabel_10.setForeground(new Color(0, 0, 0));
      lblNewLabel_10.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_10.setBounds(92, 631, 269, 21);
      perfPannel.add(lblNewLabel_10);
      JButton startupWindowBtn = new JButton("Get List");
      startupWindowBtn.setForeground(new Color(255, 255, 255));
      startupWindowBtn.setBackground(new Color(55, 65, 82));
      startupWindowBtn.setFont(new Font("Tahoma", 0, 12));
      startupWindowBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SystemPerformanceProcess smp = new SystemPerformanceProcess();
            smp.startStopSatrtupApps();
         }
      });
      startupWindowBtn.setBounds(796, 652, 120, 35);
      perfPannel.add(startupWindowBtn);
      JLabel lblNewLabel_29 = new JLabel("Check Current Status :");
      lblNewLabel_29.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_29.setBounds(92, 114, 213, 28);
      perfPannel.add(lblNewLabel_29);
      String[] s = new String[]{"Running Services", "Stopped Services"};
      final JComboBox serviceDropBox = new JComboBox(s);
      serviceDropBox.setModel(new DefaultComboBoxModel(new String[]{"-- Select One --", "Running Services", "Stopped Services"}));
      serviceDropBox.setFont(new Font("Tahoma", 0, 12));
      serviceDropBox.setToolTipText("Curretn status of Services to Start/ Stop");
      serviceDropBox.setBounds(583, 128, 147, 30);
      perfPannel.add(serviceDropBox);
      JButton FilterServiceStatusbtn = new JButton("Check Now");
      FilterServiceStatusbtn.setBackground(new Color(55, 65, 82));
      FilterServiceStatusbtn.setForeground(new Color(255, 255, 255));
      FilterServiceStatusbtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (!licenceCheck) {
               JOptionPane.showMessageDialog(MainFrame.this, "You need to buy our licecnse to use this feature", "License Reminder", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/key.png")));
            } else if (licenceCheck) {
               SystemPerformanceProcess spu = new SystemPerformanceProcess();
               HashMap<String, String> status = spu.checkServiceStatus();
               boolean chkBoxstatus = false;
               sysMainChkBox.setSelected(chkBoxstatus);
               searchChkBox.setSelected(chkBoxstatus);
               icssvcChkBox.setSelected(chkBoxstatus);
               faxChkBox.setSelected(chkBoxstatus);
               wisvcChkBox.setSelected(chkBoxstatus);
               teamServicesChkBox.setSelected(chkBoxstatus);
               remoteRegChkBox.setSelected(chkBoxstatus);
               retailDemoChkBox.setSelected(chkBoxstatus);
               TapiSrvchkBox.setSelected(chkBoxstatus);
               smartCardChkBox.setSelected(chkBoxstatus);
               cuetchkBox.setSelected(chkBoxstatus);
               MapsBrokerchkBox.setSelected(chkBoxstatus);
               CertPropSvcchkBox.setSelected(chkBoxstatus);
               touchKbndHandChkBox.setSelected(chkBoxstatus);
               gamebarchkbox.setSelected(chkBoxstatus);
               if (serviceDropBox.getSelectedItem().equals("Running Services")) {
                  sysMainChkBox.setEnabled(Boolean.parseBoolean((String)status.get("SysMain")));
                  searchChkBox.setEnabled(Boolean.parseBoolean((String)status.get("WSearch")));
                  icssvcChkBox.setEnabled(Boolean.parseBoolean((String)status.get("icssvc")));
                  faxChkBox.setEnabled(Boolean.parseBoolean((String)status.get("fax")));
                  wisvcChkBox.setEnabled(Boolean.parseBoolean((String)status.get("wisvc")));
                  teamServicesChkBox.setEnabled(Boolean.parseBoolean((String)status.get("TermService")));
                  remoteRegChkBox.setEnabled(Boolean.parseBoolean((String)status.get("RemoteRegistry")));
                  retailDemoChkBox.setEnabled(Boolean.parseBoolean((String)status.get("RetailDemo")));
                  TapiSrvchkBox.setEnabled(Boolean.parseBoolean((String)status.get("TapiSrv")));
                  smartCardChkBox.setEnabled(Boolean.parseBoolean((String)status.get("SCardSvr")));
                  cuetchkBox.setEnabled(Boolean.parseBoolean((String)status.get("DiagTrack")));
                  MapsBrokerchkBox.setEnabled(Boolean.parseBoolean((String)status.get("MapsBroker")));
                  CertPropSvcchkBox.setEnabled(Boolean.parseBoolean((String)status.get("CertPropSvc")));
                  touchKbndHandChkBox.setEnabled(Boolean.parseBoolean((String)status.get("TabletInputService")));
                  gamebarchkbox.setEnabled(Boolean.parseBoolean((String)status.get("AJRouter")));
               } else if (serviceDropBox.getSelectedItem().equals("Stopped Services")) {
                  sysMainChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("SysMain")));
                  searchChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("WSearch")));
                  icssvcChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("icssvc")));
                  faxChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("fax")));
                  wisvcChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("wisvc")));
                  teamServicesChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("TermService")));
                  remoteRegChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("RemoteRegistry")));
                  retailDemoChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("RetailDemo")));
                  TapiSrvchkBox.setEnabled(!Boolean.parseBoolean((String)status.get("TapiSrv")));
                  smartCardChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("SCardSvr")));
                  cuetchkBox.setEnabled(!Boolean.parseBoolean((String)status.get("DiagTrack")));
                  MapsBrokerchkBox.setEnabled(!Boolean.parseBoolean((String)status.get("MapsBroker")));
                  CertPropSvcchkBox.setEnabled(!Boolean.parseBoolean((String)status.get("CertPropSvc")));
                  touchKbndHandChkBox.setEnabled(!Boolean.parseBoolean((String)status.get("TabletInputService")));
                  gamebarchkbox.setEnabled(!Boolean.parseBoolean((String)status.get("AJRouter")));
               }

               if (serviceDropBox.getSelectedItem().equals("Running Services")) {
                  sysPerfInfoLbl.setText("Listed services are Running");
                  startServiceBtn.setEnabled(false);
                  stopServiceBtn.setEnabled(true);
               } else if (serviceDropBox.getSelectedItem().equals("Stopped Services")) {
                  sysPerfInfoLbl.setText("Listed services are Not Running");
                  startServiceBtn.setEnabled(true);
                  stopServiceBtn.setEnabled(false);
               }
            }

         }
      });
      FilterServiceStatusbtn.setFont(new Font("Tahoma", 0, 12));
      FilterServiceStatusbtn.setBounds(791, 123, 120, 35);
      perfPannel.add(FilterServiceStatusbtn);
      JLabel lblNewLabel_32 = new JLabel("<html>Here you can Enable/ Disable all unwanted Services which is running in <br> the background and consuming your system resources.</html>");
      lblNewLabel_32.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32.setBounds(92, 139, 391, 37);
      perfPannel.add(lblNewLabel_32);
      JLabel lblNewLabel_32_1 = new JLabel("Enable/ Disable windows widget");
      lblNewLabel_32_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1.setBounds(92, 399, 190, 21);
      perfPannel.add(lblNewLabel_32_1);
      JLabel lblNewLabel_32_1_1 = new JLabel("<html>If you are facing some basic issues in your system. You can try this option . It will run a scan and resolve issues if any.</html>");
      lblNewLabel_32_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1_1.setBounds(94, 475, 354, 40);
      perfPannel.add(lblNewLabel_32_1_1);
      JSeparator separator_1_1_2 = new JSeparator();
      separator_1_1_2.setOpaque(true);
      separator_1_1_2.setBackground(Color.BLACK);
      separator_1_1_2.setBounds(38, 439, 923, 1);
      perfPannel.add(separator_1_1_2);
      JSeparator separator_1_1_2_1 = new JSeparator();
      separator_1_1_2_1.setOpaque(true);
      separator_1_1_2_1.setBackground(Color.BLACK);
      separator_1_1_2_1.setBounds(39, 529, 923, 1);
      perfPannel.add(separator_1_1_2_1);
      final JPanel donationPanel = new JPanel();
      donationPanel.setLayout((LayoutManager)null);
      donationPanel.setBackground(new Color(253, 244, 255));
      donationPanel.setBounds(1822, 408, 83, 89);
      this.contentPane.add(donationPanel);
      JLabel lblNewLabel_32_1_1_1 = new JLabel("<html>Its an little advanced option than previous one. If you are not satisfied with the results of previous <br>activity then try this. It will try little harder and try to resolve system issues if any.</html>");
      lblNewLabel_32_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1_1_1.setBounds(92, 562, 569, 39);
      perfPannel.add(lblNewLabel_32_1_1_1);
      JSeparator separator_1_1_2_1_1 = new JSeparator();
      separator_1_1_2_1_1.setOpaque(true);
      separator_1_1_2_1_1.setBackground(Color.BLACK);
      separator_1_1_2_1_1.setBounds(38, 616, 923, 1);
      perfPannel.add(separator_1_1_2_1_1);
      JLabel lblNewLabel_32_1_1_1_1 = new JLabel("<html>Disable all unwanted startup process which is eating up your resources and making your startup slow.</html>");
      lblNewLabel_32_1_1_1_1.setHorizontalAlignment(2);
      lblNewLabel_32_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1_1_1_1.setBounds(92, 650, 337, 57);
      perfPannel.add(lblNewLabel_32_1_1_1_1);
      JLabel lblNewLabel_36_2 = new JLabel(createImageIcon("/com/resource/Status.png", "Status"));
      lblNewLabel_36_2.setBounds(59, 119, 30, 32);
      perfPannel.add(lblNewLabel_36_2);
      JLabel lblNewLabel_36_2_1 = new JLabel(createImageIcon("/com/resource/Widget.png", "Widget"));
      lblNewLabel_36_2_1.setBounds(56, 386, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1);
      JLabel lblNewLabel_36_2_1_1 = new JLabel(createImageIcon("/com/resource/puzzle.png", "Puzzle"));
      lblNewLabel_36_2_1_1.setBounds(56, 461, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1_1);
      JLabel lblNewLabel_36_2_1_1_1 = new JLabel(createImageIcon("/com/resource/scan.png", "scan"));
      lblNewLabel_36_2_1_1_1.setBounds(56, 547, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1_1_1);
      JLabel lblNewLabel_36_2_1_1_1_1 = new JLabel(createImageIcon("/com/resource/list.png", "Performance"));
      lblNewLabel_36_2_1_1_1_1.setBounds(56, 637, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1_1_1_1);
      JSeparator separator_1_1_2_1_1_1 = new JSeparator();
      separator_1_1_2_1_1_1.setOpaque(true);
      separator_1_1_2_1_1_1.setBackground(Color.BLACK);
      separator_1_1_2_1_1_1.setBounds(38, 719, 923, 1);
      perfPannel.add(separator_1_1_2_1_1_1);
      JLabel lblNewLabel_10_2 = new JLabel("Setup Paging or Virtual memory");
      lblNewLabel_10_2.setForeground(Color.BLACK);
      lblNewLabel_10_2.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_10_2.setBounds(92, 730, 269, 21);
      perfPannel.add(lblNewLabel_10_2);
      JLabel lblNewLabel_32_1_1_1_1_1 = new JLabel("<html>This will increase your virtual memory . Its benifecial if your memory is less but required work requires more</html>");
      lblNewLabel_32_1_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1_1_1_1_1.setBounds(92, 746, 313, 40);
      perfPannel.add(lblNewLabel_32_1_1_1_1_1);
      final JLabel CurrentRamLbl = new JLabel("New label");
      CurrentRamLbl.setFont(new Font("Tahoma", 1, 14));
      JButton NotepadListForPerfBtn = new JButton("Setup");
      NotepadListForPerfBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               String txtVal = MainFrame.this.virtualRamTxtbox.getText();
               if (!Pattern.matches("^[1-9]\\d*$", txtVal)) {
                  JOptionPane.showMessageDialog(MainFrame.this, "Please type only Integer values", "Faulty Inputs", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
                  MainFrame.this.virtualRamTxtbox.setText("");
               }

               String currentRamVal = CurrentRamLbl.getText().split(":")[1].split(" ")[1].trim();
               if (Integer.parseInt(currentRamVal) * 3 < Integer.parseInt(currentRamVal)) {
                  JOptionPane.showMessageDialog(MainFrame.this, "It is not recomonded to cross the maximum limit of virtual memory which is 3 times your actual RAM size.", "Faulty Inputs", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
               } else {
                  SystemPerformanceProcess p = new SystemPerformanceProcess();
                  String result = p.setVirualRam(Integer.parseInt(currentRamVal), Integer.parseInt(txtVal));
                  sysPerfInfoLbl.setText(result);
                  JOptionPane.showMessageDialog(MainFrame.this, result, "Action Successful", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
               }
            } catch (Exception var6) {
               var6.printStackTrace();
               MainFrame.this.logger.info("00.00" + var6.getMessage());
            }

         }
      });
      NotepadListForPerfBtn.setForeground(Color.WHITE);
      NotepadListForPerfBtn.setFont(new Font("Tahoma", 0, 12));
      NotepadListForPerfBtn.setBackground(new Color(55, 65, 82));
      NotepadListForPerfBtn.setBounds(796, 737, 120, 35);
      perfPannel.add(NotepadListForPerfBtn);
      JLabel lblNewLabel_36_2_1_1_1_1_1 = new JLabel(createImageIcon("/com/resource/list.png", "Performance"));
      lblNewLabel_36_2_1_1_1_1_1.setBounds(59, 736, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1_1_1_1_1);
      JLabel linktoSiteLbl_2_1_1 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_1_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2_1_1.setHorizontalAlignment(0);
      linktoSiteLbl_2_1_1.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_1_1.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_1_1.setBounds(810, 36, 184, 36);
      perfPannel.add(linktoSiteLbl_2_1_1);
      final JButton cortanaBtn = new JButton("Disable");
      cortanaBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            SystemPerformanceProcess p = new SystemPerformanceProcess();
            String s = cortanaBtn.getText();
            sysPerfInfoLbl.setText(p.disableEnableCortana(s));
            if (s.equals("Enable")) {
               cortanaBtn.setText("Disable");
            } else {
               cortanaBtn.setText("Enable");
            }

         }
      });
      cortanaBtn.setForeground(Color.WHITE);
      cortanaBtn.setFont(new Font("Tahoma", 0, 14));
      cortanaBtn.setBackground(new Color(55, 65, 82));
      cortanaBtn.setBounds(800, 382, 120, 35);
      perfPannel.add(cortanaBtn);
      JLabel lblNewLabel_6_2 = new JLabel("Enable/ Disable Cortana");
      lblNewLabel_6_2.setToolTipText("Disable this for better performance.");
      lblNewLabel_6_2.setForeground(Color.BLACK);
      lblNewLabel_6_2.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_6_2.setBounds(587, 378, 189, 26);
      perfPannel.add(lblNewLabel_6_2);
      JLabel lblNewLabel_32_1_2 = new JLabel("Enable/ Disable windows Cortana");
      lblNewLabel_32_1_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_32_1_2.setBounds(587, 399, 190, 21);
      perfPannel.add(lblNewLabel_32_1_2);
      JLabel lblNewLabel_36_2_1_2 = new JLabel(createImageIcon("/com/resource/cortana.png", "Puzzle"));
      lblNewLabel_36_2_1_2.setBounds(554, 386, 30, 32);
      perfPannel.add(lblNewLabel_36_2_1_2);
      CurrentRamLbl.setBounds(541, 740, 107, 35);
      perfPannel.add(CurrentRamLbl);
      this.virtualRamTxtbox = new JTextField();
      this.virtualRamTxtbox.setBounds(648, 742, 46, 35);
      perfPannel.add(this.virtualRamTxtbox);
      this.virtualRamTxtbox.setColumns(10);
      JLabel lblNewLabel_43 = new JLabel("GB");
      lblNewLabel_43.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_43.setBounds(699, 740, 45, 35);
      perfPannel.add(lblNewLabel_43);
      JLabel lblActivityMayTake_1_1_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1_1_1_1.setBounds(257, 81, 490, 20);
      perfPannel.add(lblActivityMayTake_1_1_1_1_1);
      final JPanel extraFeaturesPanel = new JPanel();
      extraFeaturesPanel.setBackground(new Color(255, 255, 255));
      extraFeaturesPanel.setBounds(1952, 101, 90, 89);
      this.contentPane.add(extraFeaturesPanel);
      final JPanel DiskFeaturesPanel = new JPanel();
      DiskFeaturesPanel.setLayout((LayoutManager)null);
      DiskFeaturesPanel.setBackground(new Color(255, 255, 255));
      DiskFeaturesPanel.setBounds(1952, 295, 90, 89);
      DiskFeaturesPanel.setVisible(false);
      this.contentPane.add(DiskFeaturesPanel);
      extraFeaturesPanel.setLayout((LayoutManager)null);
      final JLabel cmptInfoLbl = new JLabel("Components Information");
      cmptInfoLbl.setBounds(383, 254, 293, 37);
      cmptInfoLbl.setHorizontalAlignment(2);
      cmptInfoLbl.setFont(new Font("Tahoma", 1, 16));
      extraFeaturesPanel.add(cmptInfoLbl);
      final JLabel ExtraOutputLbl = new JLabel("");
      ExtraOutputLbl.setBackground(new Color(255, 255, 255));
      ExtraOutputLbl.setBounds(31, 315, 937, 477);
      JScrollPane scroll = new JScrollPane(ExtraOutputLbl);
      scroll.setBounds(31, 301, 937, 491);
      scroll.setBackground(new Color(255, 255, 255));
      scroll.setVerticalScrollBarPolicy(22);
      extraFeaturesPanel.add(scroll);
      final JLabel listofAllDriveIndexingStatusLbl = new JLabel("");
      listofAllDriveIndexingStatusLbl.setFont(new Font("Tahoma", 1, 12));
      listofAllDriveIndexingStatusLbl.setHorizontalAlignment(0);
      listofAllDriveIndexingStatusLbl.setBounds(10, 519, 37, 46);
      listofAllDriveIndexingStatusLbl.setVisible(false);
      DiskFeaturesPanel.add(listofAllDriveIndexingStatusLbl);
      final JLabel diskLblMessga = new JLabel("Improve your drive speed and manage Space.");
      diskLblMessga.setHorizontalAlignment(2);
      diskLblMessga.setForeground(new Color(0, 128, 255));
      diskLblMessga.setFont(new Font("Tahoma", 0, 14));
      diskLblMessga.setBounds(97, 50, 345, 29);
      DiskFeaturesPanel.add(diskLblMessga);
      final JComboBox<String> HDDCombo = new JComboBox();
      HDDCombo.setBounds(567, 259, 93, 21);
      DiskFeaturesPanel.add(HDDCombo);
      final JComboBox<String> SSDCombo = new JComboBox();
      SSDCombo.setBounds(566, 163, 93, 21);
      DiskFeaturesPanel.add(SSDCombo);
      JLabel lblExtraFeatuersList_1 = new JLabel("Disk Performance And Utility");
      lblExtraFeatuersList_1.setFont(new Font("Verdana", 1, 17));
      lblExtraFeatuersList_1.setBounds(97, 26, 315, 36);
      DiskFeaturesPanel.add(lblExtraFeatuersList_1);
      final JComboBox<String> AllDriveListcombo = new JComboBox();
      AllDriveListcombo.setBounds(566, 361, 93, 21);
      DiskFeaturesPanel.add(AllDriveListcombo);
      final JComboBox<String> enableDisableIndexCmbBox = new JComboBox();
      enableDisableIndexCmbBox.setBounds(566, 581, 93, 21);
      DiskFeaturesPanel.add(enableDisableIndexCmbBox);
      final JComboBox<String> AllDriveListcomboDiskCleanup = new JComboBox();
      AllDriveListcomboDiskCleanup.setBounds(567, 477, 93, 21);
      DiskFeaturesPanel.add(AllDriveListcomboDiskCleanup);
      final JButton optimizeSSDBtn = new JButton("Optimize");
      optimizeSSDBtn.setForeground(new Color(255, 255, 255));
      final JButton readWriteTestBtn = new JButton("Check Now");
      readWriteTestBtn.setForeground(new Color(255, 255, 255));
      readWriteTestBtn.setBackground(new Color(55, 65, 82));
      final JButton diskCleanupBtn = new JButton("Disk Cleanup");
      diskCleanupBtn.setForeground(new Color(255, 255, 255));
      diskCleanupBtn.setBackground(new Color(55, 65, 82));
      final JButton indexSSDBtn = new JButton("Disable");
      indexSSDBtn.setForeground(new Color(255, 255, 255));
      indexSSDBtn.setBackground(new Color(55, 65, 82));
      final JButton btnDefragHdd = new JButton("Defrag");
      btnDefragHdd.setForeground(new Color(255, 255, 255));
      btnDefragHdd.setBackground(new Color(55, 65, 82));
      final JLabel indexingStatusLbl = new JLabel("");
      indexingStatusLbl.setFont(new Font("Tahoma", 0, 12));
      indexingStatusLbl.setBounds(460, 623, 45, 17);
      indexingStatusLbl.setVisible(false);
      DiskFeaturesPanel.add(indexingStatusLbl);
      final JButton btnChangeNow = new JButton("Disable");
      final JLabel ssdDataLbl = new JLabel("");
      ssdDataLbl.setBounds(79, 771, 269, 29);
      ssdDataLbl.setVisible(false);
      DiskFeaturesPanel.add(ssdDataLbl);
      final JLabel dnsClearDate = new JLabel("");
      dnsClearDate.setForeground(new Color(0, 128, 255));
      dnsClearDate.setFont(new Font("Tahoma", 0, 12));
      dnsClearDate.setBounds(597, 576, 149, 40);
      BrowserPanel.add(dnsClearDate);
      startupAppsPanelPart2.setLayout((LayoutManager)null);
      JLabel lblNewLabel_28 = new JLabel("<html>pieces of information websites store on your computer. Like UserID,Session ID</html>");
      lblNewLabel_28.setFont(new Font("Tahoma", 0, 11));
      lblNewLabel_28.setBounds(51, 547, 204, 31);
      StorgaePanel.add(lblNewLabel_28);
      JLabel lblNewLabel_28_1 = new JLabel("<html>Visited websites media, temp files. Helps to load website faster</html>");
      lblNewLabel_28_1.setFont(new Font("Tahoma", 0, 11));
      lblNewLabel_28_1.setBounds(51, 483, 204, 31);
      StorgaePanel.add(lblNewLabel_28_1);
      JLabel lblNewLabel_28_1_1 = new JLabel("<html>List of your browsing history</html>");
      lblNewLabel_28_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_28_1_1.setBounds(51, 610, 204, 21);
      StorgaePanel.add(lblNewLabel_28_1_1);
      JLabel lblNewLabel_36_1_1 = new JLabel(createImageIcon("/com/resource/cookie.png", "logo"));
      lblNewLabel_36_1_1.setBounds(35, 404, 30, 32);
      StorgaePanel.add(lblNewLabel_36_1_1);
      JLabel lblNewLabel_36_1_1_1 = new JLabel(createImageIcon("/com/resource/cleanNew.png", "clean"));
      lblNewLabel_36_1_1_1.setBounds(35, 132, 30, 32);
      StorgaePanel.add(lblNewLabel_36_1_1_1);
      JSeparator separator_1_3 = new JSeparator();
      separator_1_3.setForeground(Color.BLACK);
      separator_1_3.setBounds(35, 703, 921, 2);
      StorgaePanel.add(separator_1_3);
      JLabel lblNewLabel_18_1 = new JLabel("Disable system hibernation");
      lblNewLabel_18_1.setForeground(Color.BLACK);
      lblNewLabel_18_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_18_1.setBounds(77, 714, 266, 21);
      StorgaePanel.add(lblNewLabel_18_1);
      JLabel lblNewLabel_17_2 = new JLabel("Disabling hibernate from system will delete the occupied space");
      lblNewLabel_17_2.setForeground(new Color(0, 0, 0));
      lblNewLabel_17_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_17_2.setBackground(new Color(58, 129, 255));
      lblNewLabel_17_2.setBounds(76, 734, 478, 25);
      StorgaePanel.add(lblNewLabel_17_2);
      JLabel lblNewLabel_36_1_1_1_1 = new JLabel(createImageIcon("/com/resource/hibernation.png", "hibernation"));
      lblNewLabel_36_1_1_1_1.setBounds(35, 720, 30, 32);
      StorgaePanel.add(lblNewLabel_36_1_1_1_1);
      JLabel linktoSiteLbl = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl.setHorizontalAlignment(0);
      linktoSiteLbl.setForeground(new Color(255, 0, 128));
      linktoSiteLbl.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent arg0) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl.setBounds(810, 40, 184, 36);
      StorgaePanel.add(linktoSiteLbl);
      JButton disablehibernateBtn = new JButton("Disable");
      disablehibernateBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            MainProcessor mp = new MainProcessor();
            MainFrame.this.StorageMainMessageLbl.setText(mp.disableHibernate("Disable"));
            hibernateSize.setText("0KB");
            JOptionPane.showMessageDialog(MainFrame.this, "Hibernation disbaled successfully. You can reenable it by clicking 'Enable' button", "Hibernation status", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      disablehibernateBtn.setForeground(new Color(255, 255, 255));
      disablehibernateBtn.setBackground(new Color(55, 65, 82));
      disablehibernateBtn.setFont(new Font("Tahoma", 1, 12));
      disablehibernateBtn.setBounds(830, 722, 112, 40);
      StorgaePanel.add(disablehibernateBtn);
      final JLabel SysInfoImage = new JLabel(createImageIcon("/com/resource/CPU.png", "Setup"));
      SysInfoImage.setBounds(343, 254, 40, 40);
      extraFeaturesPanel.add(SysInfoImage);
      JButton enablehibernateBtn = new JButton("Enable");
      enablehibernateBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainProcessor mp = new MainProcessor();
            MainFrame.this.StorageMainMessageLbl.setText(mp.disableHibernate("Enable"));
            JOptionPane.showMessageDialog(MainFrame.this, "Hibernation enabled successfully. You can disable it by clicking 'Disable' button", "Hibernation status", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      enablehibernateBtn.setForeground(Color.WHITE);
      enablehibernateBtn.setFont(new Font("Tahoma", 1, 12));
      enablehibernateBtn.setBackground(new Color(55, 65, 82));
      enablehibernateBtn.setBounds(680, 722, 112, 40);
      StorgaePanel.add(enablehibernateBtn);
      final JLabel cautionLbl = new JLabel("<html>Keep SSDs occupency less than 75% to write data on full speed.</html>");
      cautionLbl.setForeground(new Color(255, 0, 0));
      cautionLbl.setFont(new Font("Tahoma", 0, 12));
      cautionLbl.setVisible(false);
      cautionLbl.setBounds(722, 573, 258, 38);
      StorgaePanel.add(cautionLbl);
      JLabel lblActivityMayTake_1_1_1_1_1_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1_1_1_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1_1_1_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1_1_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1_1_1_1_1_1_1.setBounds(248, 90, 496, 20);
      StorgaePanel.add(lblActivityMayTake_1_1_1_1_1_1_1_1);
      ExtraOutputLbl.setFont(new Font("Tahoma", 0, 15));
      ExtraOutputLbl.setHorizontalAlignment(0);
      JButton BatteryReportBtn = new JButton("Battery", createImageIcon("/com/resource/Battery.png", "Battery"));
      BatteryReportBtn.setBounds(735, 126, 110, 45);
      BatteryReportBtn.setToolTipText("Battery Information");
      BatteryReportBtn.setHorizontalAlignment(2);
      BatteryReportBtn.setFont(new Font("Tahoma", 0, 11));
      BatteryReportBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            ExtrasProcess ep = new ExtrasProcess();
            cmptInfoLbl.setText("Battery Information");
            ExtraOutputLbl.setText(ep.checkLaptoporDesktop());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/Battery.png", "Battery"));
         }
      });
      extraFeaturesPanel.add(BatteryReportBtn);
      JLabel lblExtraFeatuersList = new JLabel("System Information");
      lblExtraFeatuersList.setBounds(104, 27, 267, 36);
      lblExtraFeatuersList.setFont(new Font("Verdana", 1, 20));
      extraFeaturesPanel.add(lblExtraFeatuersList);
      JButton btnNewButton_2 = new JButton("Status", createImageIcon("/com/resource/Status.png", "OS"));
      btnNewButton_2.setBounds(855, 126, 100, 45);
      btnNewButton_2.setHorizontalAlignment(2);
      btnNewButton_2.setToolTipText("All Components Running Status");
      btnNewButton_2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            ExtrasProcess ep = new ExtrasProcess();
            cmptInfoLbl.setText("Components Working Status");
            ExtraOutputLbl.setText(ep.componentsStatus());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/Status.png", "status"));
         }
      });
      btnNewButton_2.setFont(new Font("Tahoma", 0, 11));
      extraFeaturesPanel.add(btnNewButton_2);
      JButton CpuInfoBtn = new JButton("CPU", createImageIcon("/com/resource/CPU.png", "CPU"));
      CpuInfoBtn.setBounds(66, 126, 100, 45);
      CpuInfoBtn.setHorizontalAlignment(2);
      CpuInfoBtn.setFont(new Font("Tahoma", 0, 12));
      CpuInfoBtn.setToolTipText("CPU Info");
      CpuInfoBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("CPU Information");
            ExtraOutputLbl.setText(minfo.getCPUInfo());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/CPU.png", "Setup"));
         }
      });
      extraFeaturesPanel.add(CpuInfoBtn);
      JButton OSInfoBtn = new JButton("OS", createImageIcon("/com/resource/OS.png", "OS"));
      OSInfoBtn.setBounds(626, 126, 100, 45);
      OSInfoBtn.setFont(new Font("Tahoma", 0, 13));
      OSInfoBtn.setToolTipText("Operating System Info");
      OSInfoBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("OS Information");
            ExtraOutputLbl.setText(minfo.getOSInfo());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/OS.png", "OS"));
         }
      });
      extraFeaturesPanel.add(OSInfoBtn);
      JButton GPUInfoBtn = new JButton("GPU", createImageIcon("/com/resource/GPU.png", "GPU"));
      GPUInfoBtn.setBounds(175, 126, 100, 45);
      GPUInfoBtn.setHorizontalAlignment(2);
      GPUInfoBtn.setFont(new Font("Tahoma", 0, 12));
      GPUInfoBtn.setToolTipText("Video Card Info");
      GPUInfoBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("GPU Information");
            ExtraOutputLbl.setText(minfo.getGPUInfo());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/GPU.png", "GPU"));
         }
      });
      extraFeaturesPanel.add(GPUInfoBtn);
      JButton RAMInfoBtn = new JButton("RAM", createImageIcon("/com/resource/RAM.png", "RAM"));
      RAMInfoBtn.setBounds(285, 126, 100, 45);
      RAMInfoBtn.setHorizontalAlignment(2);
      RAMInfoBtn.setFont(new Font("Tahoma", 0, 12));
      GPUInfoBtn.setToolTipText("RAM Info");
      RAMInfoBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("Memory Information");
            ExtraOutputLbl.setText(minfo.getRAMInfoNew());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/RAM.png", "RAM"));
         }
      });
      RAMInfoBtn.setToolTipText("Memory Info");
      extraFeaturesPanel.add(RAMInfoBtn);
      JButton DriveInfoBtn = new JButton("Drive", createImageIcon("/com/resource/hdd40.png", "Drive"));
      DriveInfoBtn.setBounds(394, 127, 114, 45);
      DriveInfoBtn.setHorizontalAlignment(2);
      DriveInfoBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("Drive Information");
            ExtraOutputLbl.setText(minfo.getStorgaeInfo());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/storage32.png", "SSD"));
         }
      });
      DriveInfoBtn.setFont(new Font("Tahoma", 0, 12));
      GPUInfoBtn.setToolTipText("Drive Info");
      DriveInfoBtn.setToolTipText("Drive Info");
      extraFeaturesPanel.add(DriveInfoBtn);
      JButton RAMInfoBtn_1_1 = new JButton("Board", createImageIcon("/com/resource/Board.png", "Storage"));
      RAMInfoBtn_1_1.setBounds(518, 126, 100, 45);
      RAMInfoBtn_1_1.setHorizontalAlignment(2);
      RAMInfoBtn_1_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("Board Information");
            ExtraOutputLbl.setText(minfo.getBoardInfo());
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/Board.png", "Board"));
         }
      });
      RAMInfoBtn_1_1.setFont(new Font("Tahoma", 0, 11));
      RAMInfoBtn_1_1.setToolTipText("Main Board Info");
      extraFeaturesPanel.add(RAMInfoBtn_1_1);
      JLabel lblGetAllThe = new JLabel("Get all the main major Components complete Information to help you understand your system better");
      lblGetAllThe.setBounds(76, 182, 863, 37);
      lblGetAllThe.setForeground(new Color(0, 134, 198));
      lblGetAllThe.setHorizontalAlignment(0);
      lblGetAllThe.setFont(new Font("Tahoma", 0, 14));
      extraFeaturesPanel.add(lblGetAllThe);
      JLabel clenspeedBrowserImage_1 = new JLabel(createImageIcon("/com/resource/SysInfo32.png", "time"));
      clenspeedBrowserImage_1.setBounds(50, 32, 40, 40);
      extraFeaturesPanel.add(clenspeedBrowserImage_1);
      JLabel lblSystemMainComponents = new JLabel("System Main Components Details");
      lblSystemMainComponents.setBounds(104, 57, 423, 29);
      lblSystemMainComponents.setHorizontalAlignment(2);
      lblSystemMainComponents.setForeground(new Color(0, 134, 198));
      lblSystemMainComponents.setFont(new Font("Tahoma", 0, 14));
      extraFeaturesPanel.add(lblSystemMainComponents);
      JLabel linktoSiteLbl_2_2_1 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_2_1.setBounds(810, 43, 184, 36);
      linktoSiteLbl_2_2_1.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl_2_2_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2_2_1.setHorizontalAlignment(0);
      linktoSiteLbl_2_2_1.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_2_1.setFont(new Font("Tahoma", 3, 12));
      extraFeaturesPanel.add(linktoSiteLbl_2_2_1);
      JSeparator separator_2 = new JSeparator();
      separator_2.setBounds(66, 231, 873, 2);
      extraFeaturesPanel.add(separator_2);
      JLabel lblActivityMayTake_1_1_1_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1_1_1_1.setBounds(241, 96, 485, 20);
      lblActivityMayTake_1_1_1_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      extraFeaturesPanel.add(lblActivityMayTake_1_1_1_1_1_1);
      btnDefragHdd.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            DiskProcess p = new DiskProcess();
            if (HDDCombo.getSelectedItem().toString().isEmpty()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Please choose a Drive", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            } else {
               String s = HDDCombo.getSelectedItem().toString();
               p.defragHDDisk(s);
            }

         }
      });
      btnChangeNow.setForeground(new Color(255, 255, 255));
      btnChangeNow.setBackground(new Color(55, 65, 82));
      btnChangeNow.setFont(new Font("Tahoma", 0, 12));
      btnChangeNow.setBounds(830, 662, 112, 35);
      DiskFeaturesPanel.add(btnChangeNow);
      btnDefragHdd.setFont(new Font("Tahoma", 0, 12));
      btnDefragHdd.setBounds(830, 250, 112, 35);
      DiskFeaturesPanel.add(btnDefragHdd);
      readWriteTestBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(MainFrame.this, "Please Wait while we test and confirm you the results.", "Check Disk For Errors or Problems", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            if (!AllDriveListcombo.isEnabled()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Please choose Drive to Cehck Read/ Write Speed.", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            } else {
               DiskProcess dp = new DiskProcess();
               dp.chkDskCommand(AllDriveListcombo.getSelectedItem().toString().split(":")[0]);
            }

         }
      });
      readWriteTestBtn.setFont(new Font("Tahoma", 0, 12));
      readWriteTestBtn.setBounds(830, 348, 112, 35);
      DiskFeaturesPanel.add(readWriteTestBtn);
      optimizeSSDBtn.setBackground(new Color(55, 65, 82));
      optimizeSSDBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            DiskProcess p = new DiskProcess();
            if (SSDCombo.getSelectedItem().toString().isEmpty()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Please choose a Drive", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            } else {
               String s = SSDCombo.getSelectedItem().toString();
               p.optimizeSSD(s);
            }

         }
      });
      optimizeSSDBtn.setFont(new Font("Tahoma", 0, 12));
      optimizeSSDBtn.setBounds(830, 140, 112, 35);
      DiskFeaturesPanel.add(optimizeSSDBtn);
      JLabel lblNewLabel_22 = new JLabel("Optimize Your HDD");
      lblNewLabel_22.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_22.setBounds(92, 231, 330, 17);
      DiskFeaturesPanel.add(lblNewLabel_22);
      JLabel lblNewLabel_23 = new JLabel("Choose your drive");
      lblNewLabel_23.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23.setBounds(566, 231, 112, 17);
      DiskFeaturesPanel.add(lblNewLabel_23);
      JLabel lblNewLabel_23_1 = new JLabel("Choose your drive");
      lblNewLabel_23_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1.setBounds(566, 136, 112, 17);
      DiskFeaturesPanel.add(lblNewLabel_23_1);
      JLabel lblNewLabel_22_1 = new JLabel("Optimize Your SSD");
      lblNewLabel_22_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_22_1.setBounds(90, 136, 159, 17);
      DiskFeaturesPanel.add(lblNewLabel_22_1);
      enableDisableIndexCmbBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (enableDisableIndexCmbBox.getSelectedIndex() != -1) {
               String selectedValue = enableDisableIndexCmbBox.getSelectedItem().toString();
               String[] s = listofAllDriveIndexingStatusLbl.getText().split("#");

               for(int i = 0; i < s.length; ++i) {
                  if (s[i].contains(selectedValue.split(":")[0])) {
                     indexingStatusLbl.setText("<html>Indexing Enabled " + selectedValue.split(":")[0] + " : " + s[i].split(":")[0].trim().split(" ")[1] + "</html>");
                  }
               }

               if (indexingStatusLbl.getText().contains("True")) {
                  indexSSDBtn.setEnabled(true);
               } else if (indexingStatusLbl.getText().contains("False")) {
                  indexSSDBtn.setEnabled(false);
               }
            }

         }
      });
      JLabel diskSize = new JLabel(s12);
      diskSize.setVisible(false);
      JSeparator separator_1_1_1_1_2 = new JSeparator();
      separator_1_1_1_1_2.setOpaque(true);
      separator_1_1_1_1_2.setForeground(Color.BLACK);
      separator_1_1_1_1_2.setBackground(Color.BLACK);
      separator_1_1_1_1_2.setBounds(45, 310, 927, 1);
      DiskFeaturesPanel.add(separator_1_1_1_1_2);
      JLabel lblNewLabel_24 = new JLabel("Choose Your Drive");
      lblNewLabel_24.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_24.setBounds(566, 342, 112, 13);
      DiskFeaturesPanel.add(lblNewLabel_24);
      JLabel lblNewLabel_25 = new JLabel("Fix Errors | Recover data from Bad Sectors");
      lblNewLabel_25.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_25.setBounds(86, 336, 324, 20);
      DiskFeaturesPanel.add(lblNewLabel_25);
      JLabel lblNewLabel_26 = new JLabel("Enable/ Disable Indexing of SSD");
      lblNewLabel_26.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_26.setBounds(86, 559, 234, 21);
      DiskFeaturesPanel.add(lblNewLabel_26);
      indexSSDBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            DiskProcess p = new DiskProcess();
            if (enableDisableIndexCmbBox.getSelectedItem().toString().isEmpty()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Please choose a Drive", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            } else {
               diskLblMessga.setText(p.enableDisableIndexing(enableDisableIndexCmbBox.getSelectedItem().toString()));
               JOptionPane.showMessageDialog(MainFrame.this, "SSD drive indexing completed successfully", "Indexing SSD Drive", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            }

         }
      });
      indexSSDBtn.setFont(new Font("Tahoma", 0, 12));
      indexSSDBtn.setBounds(830, 566, 112, 35);
      DiskFeaturesPanel.add(indexSSDBtn);
      JLabel lblNewLabel_27 = new JLabel("Disk Cleanup");
      lblNewLabel_27.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_27.setBounds(86, 442, 112, 21);
      DiskFeaturesPanel.add(lblNewLabel_27);
      diskCleanupBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (AllDriveListcomboDiskCleanup.getSelectedItem().toString().isEmpty()) {
               JOptionPane.showMessageDialog(MainFrame.this, "Please choose a Drive", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "System Will Choose and Remove Files Which Are No Longer Needed", "Item Selection Popup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/select.png")));
               DiskProcess pp = new DiskProcess();
               pp.diskCleanup(AllDriveListcomboDiskCleanup.getSelectedItem().toString());
            }

         }
      });
      diskCleanupBtn.setFont(new Font("Tahoma", 0, 12));
      diskCleanupBtn.setBounds(830, 468, 112, 35);
      DiskFeaturesPanel.add(diskCleanupBtn);
      JLabel lblNewLabel_31 = new JLabel(createImageIcon("/com/resource/SSD32.png", "Storage"));
      lblNewLabel_31.setBounds(45, 35, 40, 40);
      DiskFeaturesPanel.add(lblNewLabel_31);
      JSeparator separator_1_1_1_1_2_1 = new JSeparator();
      separator_1_1_1_1_2_1.setOpaque(true);
      separator_1_1_1_1_2_1.setForeground(Color.BLACK);
      separator_1_1_1_1_2_1.setBackground(Color.BLACK);
      separator_1_1_1_1_2_1.setBounds(45, 413, 927, 1);
      DiskFeaturesPanel.add(separator_1_1_1_1_2_1);
      JLabel lblNewLabel_23_1_1 = new JLabel("<html>Fix erros if any/ Normal scan to recover data from bad sectors.</html>");
      lblNewLabel_23_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1.setBounds(86, 356, 368, 27);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1);
      JLabel lblNewLabel_23_1_1_1 = new JLabel("<html>Choose your drive to perfrom disk cleanup. We have provided disk cleanup for all drives (Excluding external drives).<html>");
      lblNewLabel_23_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1_1.setBounds(86, 464, 368, 40);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1_1);
      JLabel lblNewLabel_24_2 = new JLabel("Choose Your Drive");
      lblNewLabel_24_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_24_2.setBounds(567, 454, 112, 13);
      DiskFeaturesPanel.add(lblNewLabel_24_2);
      JSeparator separator_1_1_1_1_2_1_1 = new JSeparator();
      separator_1_1_1_1_2_1_1.setOpaque(true);
      separator_1_1_1_1_2_1_1.setForeground(Color.BLACK);
      separator_1_1_1_1_2_1_1.setBackground(Color.BLACK);
      separator_1_1_1_1_2_1_1.setBounds(45, 527, 927, 1);
      DiskFeaturesPanel.add(separator_1_1_1_1_2_1_1);
      JLabel lblNewLabel_23_1_1_1_1 = new JLabel("For better performance keep it disabled");
      lblNewLabel_23_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1_1_1.setBounds(86, 585, 252, 17);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1_1_1);
      JLabel lblNewLabel_23_1_1_2 = new JLabel("<html>It performs trim operation internally to optimize your SSD performance</html>");
      lblNewLabel_23_1_1_2.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1_2.setBounds(90, 158, 344, 35);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1_2);
      JLabel lblNewLabel_23_1_1_2_1 = new JLabel("Optimize your HDD for best performance");
      lblNewLabel_23_1_1_2_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1_2_1.setBounds(92, 253, 344, 17);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1_2_1);
      JLabel lblNewLabel_26_1 = new JLabel("Disable Defragmentation Schedule");
      lblNewLabel_26_1.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_26_1.setBounds(86, 651, 315, 21);
      DiskFeaturesPanel.add(lblNewLabel_26_1);
      JLabel lblNewLabel_23_1_1_1_1_1 = new JLabel("<html>This will enable/ disable Defrag for all drives. Suggest to disable defragmentation for your SSDs. For HDD you can use our defrag option from above <html>");
      lblNewLabel_23_1_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_23_1_1_1_1_1.setBounds(86, 674, 393, 55);
      DiskFeaturesPanel.add(lblNewLabel_23_1_1_1_1_1);
      btnChangeNow.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            DiskProcess dp = new DiskProcess();
            String result = dp.enableDisableDefrag();
            if (result.equals("Disabled")) {
               JOptionPane.showMessageDialog(MainFrame.this, "Defragmentation is already disabled.No need to do it again.", "Disable Defragmentation", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else {
               diskLblMessga.setText(result);
               JOptionPane.showMessageDialog(MainFrame.this, "Defragmentation completed successfully", "Disable Defragmentation", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            }

         }
      });
      JSeparator separator_1_1_1_1_2_1_1_1 = new JSeparator();
      separator_1_1_1_1_2_1_1_1.setOpaque(true);
      separator_1_1_1_1_2_1_1_1.setForeground(Color.BLACK);
      separator_1_1_1_1_2_1_1_1.setBackground(Color.BLACK);
      separator_1_1_1_1_2_1_1_1.setBounds(35, 630, 927, 1);
      DiskFeaturesPanel.add(separator_1_1_1_1_2_1_1_1);
      JSeparator separator_1_1_1_1_2_2 = new JSeparator();
      separator_1_1_1_1_2_2.setOpaque(true);
      separator_1_1_1_1_2_2.setForeground(Color.BLACK);
      separator_1_1_1_1_2_2.setBackground(Color.BLACK);
      separator_1_1_1_1_2_2.setBounds(52, 203, 927, 1);
      DiskFeaturesPanel.add(separator_1_1_1_1_2_2);
      JLabel lblNewLabel_36_1 = new JLabel(createImageIcon("/com/resource/storage32.png", "Performance"));
      lblNewLabel_36_1.setBounds(52, 140, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1);
      JLabel lblNewLabel_36_1_2 = new JLabel(createImageIcon("/com/resource/write.png", "Read Write"));
      lblNewLabel_36_1_2.setBounds(52, 342, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1_2);
      JLabel lblNewLabel_36_1_2_1 = new JLabel(createImageIcon("/com/resource/cleanNew.png", "Clean"));
      lblNewLabel_36_1_2_1.setBounds(52, 447, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1_2_1);
      JLabel lblNewLabel_36_1_2_1_1 = new JLabel(createImageIcon("/com/resource/index.png", "Indexing"));
      lblNewLabel_36_1_2_1_1.setBounds(52, 565, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1_2_1_1);
      JLabel lblNewLabel_36_1_2_1_1_1 = new JLabel(createImageIcon("/com/resource/defragmentation.png", "defragmentation"));
      lblNewLabel_36_1_2_1_1_1.setBounds(52, 657, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1_2_1_1_1);
      JLabel linktoSiteLbl_2_2 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_2.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl_2_2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent arg0) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.10 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2_2.setHorizontalAlignment(0);
      linktoSiteLbl_2_2.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_2.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_2.setBounds(810, 39, 184, 36);
      DiskFeaturesPanel.add(linktoSiteLbl_2_2);
      diskSize.setVisible(false);
      diskSize.setBounds(10, 10, 45, 13);
      DiskFeaturesPanel.add(diskSize);
      JLabel lblNewLabel_36_1_3 = new JLabel(createImageIcon("/com/resource/hdd40.png", "HDD"));
      lblNewLabel_36_1_3.setBounds(52, 235, 30, 32);
      DiskFeaturesPanel.add(lblNewLabel_36_1_3);
      JLabel lblNewLabel_24_2_1 = new JLabel("Choose Your Drive");
      lblNewLabel_24_2_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_24_2_1.setBounds(566, 558, 112, 13);
      DiskFeaturesPanel.add(lblNewLabel_24_2_1);
      JLabel lblActivityMayTake_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1_1.setBounds(240, 80, 493, 20);
      DiskFeaturesPanel.add(lblActivityMayTake_1_1_1);
      final JButton BrowserCacheScnBtn = new JButton("Scan Now");
      BrowserCacheScnBtn.setForeground(new Color(255, 255, 255));
      BrowserCacheScnBtn.setBackground(new Color(55, 65, 82));
      BrowserCacheScnBtn.setFont(new Font("Verdana", 0, 15));
      BrowserCacheScnBtn.setBounds(378, 456, 115, 35);
      BrowserCacheScnBtn.setOpaque(true);
      BrowserPanel.add(BrowserCacheScnBtn);
      final JCheckBox CleanCookieChkBox = new JCheckBox("Clear Cookies - All browser");
      CleanCookieChkBox.setBackground(new Color(255, 255, 255));
      CleanCookieChkBox.setEnabled(false);
      CleanCookieChkBox.setSelected(true);
      CleanCookieChkBox.setFont(new Font("Tahoma", 1, 14));
      CleanCookieChkBox.setBounds(81, 265, 263, 23);
      BrowserPanel.add(CleanCookieChkBox);
      CleanCookieChkBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (CleanCookieChkBox.isSelected()) {
               JOptionPane.showMessageDialog(MainFrame.this, "This option will log you out from all of your websites.", "Removing Cookies", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/Trash.png")));
            }

         }
      });
      final JLabel cookieCleanlbl = new JLabel("0 KB");
      cookieCleanlbl.setForeground(new Color(58, 129, 255));
      cookieCleanlbl.setFont(new Font("Tahoma", 0, 14));
      cookieCleanlbl.setBounds(874, 293, 60, 25);
      BrowserPanel.add(cookieCleanlbl);
      JLabel outputLbl = new JLabel("Clean & Speedup Browser");
      outputLbl.setBounds(97, 26, 297, 26);
      BrowserPanel.add(outputLbl);
      outputLbl.setFont(new Font("Verdana", 1, 17));
      final JCheckBox BroserCacheChkBox = new JCheckBox("Clear cache - All Browser");
      BroserCacheChkBox.setBackground(new Color(255, 255, 255));
      BroserCacheChkBox.setEnabled(false);
      BroserCacheChkBox.setSelected(true);
      BroserCacheChkBox.setFont(new Font("Tahoma", 1, 14));
      BroserCacheChkBox.setToolTipText("Currently only supporting Brave, Chrome, Edge browsers Rest all Coming soon..");
      BroserCacheChkBox.setBounds(81, 161, 290, 25);
      BrowserPanel.add(BroserCacheChkBox);
      final JLabel cacheCleanlbl = new JLabel("0 KB");
      cacheCleanlbl.setForeground(new Color(58, 129, 255));
      cacheCleanlbl.setFont(new Font("Tahoma", 0, 14));
      cacheCleanlbl.setBounds(874, 190, 60, 25);
      BrowserPanel.add(cacheCleanlbl);
      final JCheckBox historyChkBox = new JCheckBox("Clear History - All Browser");
      historyChkBox.setBackground(new Color(255, 255, 255));
      historyChkBox.setEnabled(false);
      historyChkBox.setSelected(true);
      historyChkBox.setFont(new Font("Tahoma", 1, 14));
      historyChkBox.setBounds(81, 369, 246, 21);
      BrowserPanel.add(historyChkBox);
      final JLabel clnSpeedInfoLbl = new JLabel("Manage Unwated Browser Files and Services");
      clnSpeedInfoLbl.setHorizontalAlignment(2);
      clnSpeedInfoLbl.setForeground(new Color(58, 129, 255));
      clnSpeedInfoLbl.setFont(new Font("Tahoma", 0, 14));
      clnSpeedInfoLbl.setBounds(97, 50, 423, 29);
      BrowserPanel.add(clnSpeedInfoLbl);
      JButton BrowserCacheCleanBtn = new JButton("Clean All");
      BrowserCacheCleanBtn.setFont(new Font("Verdana", 0, 15));
      BrowserCacheCleanBtn.setForeground(new Color(255, 255, 255));
      BrowserCacheCleanBtn.setBackground(new Color(55, 65, 82));
      BrowserCacheCleanBtn.setOpaque(true);
      BrowserCacheCleanBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            boolean a = BroserCacheChkBox.isSelected();
            boolean b = historyChkBox.isSelected();
            boolean c = CleanCookieChkBox.isSelected();
            boolean statusChkBox = a || b || c;
            if (statusChkBox) {
               BrowserProcessor bp = new BrowserProcessor();
               int result = bp.purgeBrowserData(a, b, c);
               if (result > 0) {
                  clnSpeedInfoLbl.setText("Selected Browser Data deleted successfully");
               } else {
                  clnSpeedInfoLbl.setText("Somethign went wrong. Try it again..");
               }

               BrowserCacheScnBtn.setEnabled(true);
               BroserCacheChkBox.setSelected(true);
               historyChkBox.setSelected(true);
               CleanCookieChkBox.setSelected(true);
               BroserCacheChkBox.setEnabled(false);
               historyChkBox.setEnabled(false);
               CleanCookieChkBox.setEnabled(false);
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Atleast select one box to proceed.", "Browser cleanup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/Trash.png")));
               BroserCacheChkBox.setSelected(true);
               historyChkBox.setSelected(true);
               CleanCookieChkBox.setSelected(true);
            }

            JOptionPane.showMessageDialog(MainFrame.this, "Browser is clean and clear now..", "Browser Cleaning Status", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      BrowserCacheCleanBtn.setBounds(545, 456, 115, 35);
      BrowserPanel.add(BrowserCacheCleanBtn);
      final JLabel historyCleanlbl = new JLabel("0 KB");
      historyCleanlbl.setForeground(new Color(58, 129, 255));
      historyCleanlbl.setFont(new Font("Tahoma", 0, 14));
      historyCleanlbl.setBounds(874, 389, 60, 25);
      BrowserPanel.add(historyCleanlbl);
      JLabel lblNewLabel_2 = new JLabel("Clear Local DNS Entries");
      lblNewLabel_2.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_2.setBounds(86, 576, 246, 21);
      BrowserPanel.add(lblNewLabel_2);
      JButton DNSFlushBtn = new JButton("Clear DNS");
      DNSFlushBtn.setForeground(new Color(255, 255, 255));
      DNSFlushBtn.setBackground(new Color(55, 65, 82));
      DNSFlushBtn.setFont(new Font("Verdana", 0, 14));
      DNSFlushBtn.setBounds(815, 579, 112, 35);
      BrowserPanel.add(DNSFlushBtn);
      JSeparator separator = new JSeparator();
      separator.setBounds(212, 569, 1, 2);
      BrowserPanel.add(separator);
      JLabel lblNewLabel_4 = new JLabel("Change DNS Server");
      lblNewLabel_4.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_4.setBounds(86, 675, 199, 29);
      BrowserPanel.add(lblNewLabel_4);
      JButton googleDNSBtn = new JButton("Update DNS");
      googleDNSBtn.setHorizontalAlignment(2);
      googleDNSBtn.setForeground(new Color(255, 255, 255));
      googleDNSBtn.setBackground(new Color(55, 65, 82));
      googleDNSBtn.setFont(new Font("Tahoma", 0, 14));
      googleDNSBtn.setBounds(816, 702, 112, 35);
      googleDNSBtn.setToolTipText("Best For Gaming as per cloudzy reviews");
      BrowserPanel.add(googleDNSBtn);
      JLabel chromeLbl = new JLabel("<html>Supporting Browsers Chrome, Brave, MS Edge, Opera, Opera GX</html>");
      chromeLbl.setForeground(new Color(58, 129, 255));
      chromeLbl.setFont(new Font("Tahoma", 0, 14));
      chromeLbl.setHorizontalAlignment(2);
      chromeLbl.setBounds(86, 515, 407, 35);
      BrowserPanel.add(chromeLbl);
      JLabel clenspeedBrowserImage = new JLabel(createImageIcon("/com/resource/safari.png", "Browser"));
      clenspeedBrowserImage.setHorizontalAlignment(0);
      clenspeedBrowserImage.setBounds(45, 35, 40, 40);
      BrowserPanel.add(clenspeedBrowserImage);
      JLabel lblNewLabel_30 = new JLabel("<html>Clears all browsers cache files. Remember, If you tick this you will loose all of your saved passwords, visited sites etc..</html>");
      lblNewLabel_30.setForeground(new Color(0, 0, 0));
      lblNewLabel_30.setHorizontalAlignment(11);
      lblNewLabel_30.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_30.setBounds(81, 182, 398, 40);
      BrowserPanel.add(lblNewLabel_30);
      JLabel lblNewLabel_30_1 = new JLabel("<html>Cookies are small pieces of information websites store on your computer. The text can be a user ID, session ID, or any other text.</html>");
      lblNewLabel_30_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_30_1.setHorizontalAlignment(11);
      lblNewLabel_30_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_30_1.setBounds(81, 285, 401, 40);
      BrowserPanel.add(lblNewLabel_30_1);
      JLabel lblNewLabel_30_1_1 = new JLabel("<html>Its a list of web pages a user has visited, as well as associated metadata such as page title and time of visit. It is usually stored locally by web browsers</html>");
      lblNewLabel_30_1_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_30_1_1.setHorizontalAlignment(11);
      lblNewLabel_30_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_30_1_1.setBounds(81, 389, 423, 40);
      BrowserPanel.add(lblNewLabel_30_1_1);
      JSeparator separator_1_2 = new JSeparator();
      separator_1_2.setOpaque(true);
      separator_1_2.setBackground(Color.BLACK);
      separator_1_2.setBounds(67, 240, 850, 1);
      BrowserPanel.add(separator_1_2);
      JSeparator separator_1_2_1 = new JSeparator();
      separator_1_2_1.setOpaque(true);
      separator_1_2_1.setBackground(Color.BLACK);
      separator_1_2_1.setBounds(67, 346, 843, 1);
      BrowserPanel.add(separator_1_2_1);
      JSeparator separator_1_2_1_1 = new JSeparator();
      separator_1_2_1_1.setOpaque(true);
      separator_1_2_1_1.setBackground(Color.BLACK);
      separator_1_2_1_1.setBounds(72, 549, 880, 1);
      BrowserPanel.add(separator_1_2_1_1);
      JLabel lblNewLabel_30_1_1_1 = new JLabel("<html>Clears all browsers cache files. Remember, If you tick this you will loose all of your saved passwords, visited sites etc..</html>");
      lblNewLabel_30_1_1_1.setForeground(new Color(104, 104, 104));
      lblNewLabel_30_1_1_1.setHorizontalAlignment(11);
      lblNewLabel_30_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_30_1_1_1.setBounds(86, 593, 391, 45);
      BrowserPanel.add(lblNewLabel_30_1_1_1);
      JSeparator separator_1_2_1_1_1 = new JSeparator();
      separator_1_2_1_1_1.setOpaque(true);
      separator_1_2_1_1_1.setBackground(Color.BLACK);
      separator_1_2_1_1_1.setBounds(72, 650, 883, 1);
      BrowserPanel.add(separator_1_2_1_1_1);
      JLabel lblNewLabel_30_1_1_1_1 = new JLabel("<html>Clears all browsers cache files. Remember, If you tick this you will loose all of your saved passwords, visited sites etc..</html>");
      lblNewLabel_30_1_1_1_1.setForeground(new Color(104, 104, 104));
      lblNewLabel_30_1_1_1_1.setHorizontalAlignment(11);
      lblNewLabel_30_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_30_1_1_1_1.setBounds(86, 700, 380, 40);
      BrowserPanel.add(lblNewLabel_30_1_1_1_1);
      final JComboBox browsersListCombo = new JComboBox(browsersList1);
      browsersListCombo.setModel(new DefaultComboBoxModel(new String[]{"-- Select One --", "Google", "CloudFlare", "Open DNS", "Level 3"}));
      browsersListCombo.setFont(new Font("Tahoma", 0, 14));
      browsersListCombo.setBounds(597, 702, 153, 30);
      BrowserPanel.add(browsersListCombo);
      JLabel lblNewLabel_36_1_1_2 = new JLabel(createImageIcon("/com/resource/cookie.png", "Cookies"));
      lblNewLabel_36_1_1_2.setBounds(45, 272, 30, 32);
      BrowserPanel.add(lblNewLabel_36_1_1_2);
      JLabel lblNewLabel_36_1_1_2_1 = new JLabel(createImageIcon("/com/resource/history.png", "history"));
      lblNewLabel_36_1_1_2_1.setBounds(48, 374, 30, 32);
      BrowserPanel.add(lblNewLabel_36_1_1_2_1);
      JLabel lblNewLabel_36_1_1_2_2 = new JLabel(createImageIcon("/com/resource/cache.png", "cache"));
      lblNewLabel_36_1_1_2_2.setBounds(45, 169, 30, 32);
      BrowserPanel.add(lblNewLabel_36_1_1_2_2);
      JLabel lblNewLabel_36_1_1_2_3 = new JLabel(createImageIcon("/com/resource/DNS.png", "DNS"));
      lblNewLabel_36_1_1_2_3.setBounds(50, 582, 30, 32);
      BrowserPanel.add(lblNewLabel_36_1_1_2_3);
      JLabel lblNewLabel_36_1_1_2_3_1 = new JLabel(createImageIcon("/com/resource/server.png", "server"));
      lblNewLabel_36_1_1_2_3_1.setBounds(50, 685, 30, 32);
      BrowserPanel.add(lblNewLabel_36_1_1_2_3_1);
      JLabel linktoSiteLbl_1_1 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_1_1.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl_1_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent arg0) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_1_1.setBounds(810, 43, 184, 36);
      BrowserPanel.add(linktoSiteLbl_1_1);
      linktoSiteLbl_1_1.setHorizontalAlignment(0);
      linktoSiteLbl_1_1.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_1_1.setFont(new Font("Tahoma", 3, 12));
      JLabel lblActivityMayTake_1_1_1_1_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1_1_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1_1_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1_1_1_1_1_1.setBounds(262, 106, 488, 20);
      BrowserPanel.add(lblActivityMayTake_1_1_1_1_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_3 = new JLabel(createImageIcon("/com/resource/list.png", "download"));
      lblNewLabel_36_1_1_1_2_1_1_1_3.setBounds(50, 340, 32, 32);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_3);
      JLabel lblBestSystemSetup = new JLabel("Best System Setup Part-2");
      lblBestSystemSetup.setBounds(97, 26, 276, 26);
      startupAppsPanelPart2.add(lblBestSystemSetup);
      lblBestSystemSetup.setForeground(Color.BLACK);
      lblBestSystemSetup.setFont(new Font("Verdana", 1, 17));
      lblBestSystemSetup.setBackground(Color.BLACK);
      final JLabel firstTimeSetupLbl_1 = new JLabel("Results of the activity");
      firstTimeSetupLbl_1.setBounds(97, 50, 276, 29);
      startupAppsPanelPart2.add(firstTimeSetupLbl_1);
      firstTimeSetupLbl_1.setForeground(new Color(58, 129, 255));
      firstTimeSetupLbl_1.setFont(new Font("Tahoma", 0, 14));
      firstTimeSetupLbl_1.setBackground(new Color(58, 129, 255));
      JLabel lblNewLabel_9_1 = new JLabel(createImageIcon("/com/resource/setup32.png", "Setup"));
      lblNewLabel_9_1.setBounds(45, 35, 40, 40);
      startupAppsPanelPart2.add(lblNewLabel_9_1);
      JLabel lblNewLabel_6_1 = new JLabel("Enable/ Disable Widgets");
      lblNewLabel_6_1.setBounds(90, 140, 176, 17);
      startupAppsPanelPart2.add(lblNewLabel_6_1);
      lblNewLabel_6_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_6_1.setToolTipText("Disable this for better performance.");
      lblNewLabel_6_1.setFont(new Font("Tahoma", 1, 14));
      JLabel lblNewLabel_36_1_1_1_2_1_1_1 = new JLabel(createImageIcon("/com/resource/Widget.png", "Widget"));
      lblNewLabel_36_1_1_1_2_1_1_1.setBounds(50, 140, 32, 32);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1);
      JLabel lblNewLabel_33_2_2_1 = new JLabel("<html>Enable or disable the new widget running in background</html>");
      lblNewLabel_33_2_2_1.setBounds(91, 159, 337, 23);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1);
      lblNewLabel_33_2_2_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1.setFont(new Font("Tahoma", 0, 12));
      widgetBtn_1.setBounds(820, 140, 112, 35);
      startupAppsPanelPart2.add(widgetBtn_1);
      widgetBtn_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            String ss = widgetBtn_1.getText().equals("Enable") ? "Disable" : "Enable";
            widgetBtn_1.setText(ss);
            SystemPerformanceProcess spp = new SystemPerformanceProcess();
            firstTimeSetupLbl.setText(spp.disbaleWidgets(widgetBtn_1.getText()));
            JOptionPane.showMessageDialog(MainFrame.this, "System Widget successfully " + ss + "ed", "System Widget", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      widgetBtn_1.setForeground(new Color(255, 255, 255));
      widgetBtn_1.setFont(new Font("Tahoma", 0, 14));
      widgetBtn_1.setBackground(new Color(55, 65, 82));
      JLabel lblNewLabel_4_1 = new JLabel("<html>Change DNS Connection</html>");
      lblNewLabel_4_1.setBounds(90, 229, 177, 17);
      startupAppsPanelPart2.add(lblNewLabel_4_1);
      lblNewLabel_4_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_4_1.setFont(new Font("Tahoma", 1, 14));
      JLabel lblNewLabel_33_2_2_1_1 = new JLabel("<html>Enable or disable the new widget running in background</html>");
      lblNewLabel_33_2_2_1_1.setBounds(90, 249, 318, 29);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1_1);
      lblNewLabel_33_2_2_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_1.setFont(new Font("Tahoma", 0, 12));
      final JComboBox ChooseDNSCombo = new JComboBox(browsersList);
      ChooseDNSCombo.setModel(new DefaultComboBoxModel(new String[]{"-- Select One --", "Google", "CloudFlare", "Open DNS", "Level 3"}));
      ChooseDNSCombo.setBounds(570, 240, 186, 30);
      startupAppsPanelPart2.add(ChooseDNSCombo);
      ChooseDNSCombo.setFont(new Font("Tahoma", 0, 13));
      JButton googleDNSBtn_1 = new JButton("Set Now");
      googleDNSBtn_1.setBounds(817, 240, 112, 35);
      startupAppsPanelPart2.add(googleDNSBtn_1);
      googleDNSBtn_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            BrowserUtilityClass uc = new BrowserUtilityClass();
            firstTimeSetupLbl.setText(uc.setDNS(ChooseDNSCombo.getSelectedItem().toString()));
            JOptionPane.showMessageDialog(MainFrame.this, "DNS server is set to :" + ChooseDNSCombo.getSelectedItem().toString(), "DNS Server Setup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      googleDNSBtn_1.setToolTipText("Best For Gaming as per cloudzy reviews");
      googleDNSBtn_1.setForeground(Color.WHITE);
      googleDNSBtn_1.setFont(new Font("Tahoma", 0, 14));
      googleDNSBtn_1.setBackground(new Color(55, 65, 82));
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_1 = new JLabel(createImageIcon("/com/resource/server.png", "server"));
      lblNewLabel_36_1_1_1_2_1_1_1_1.setBounds(50, 227, 32, 32);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_1);
      JLabel lblNewLabel_10_1 = new JLabel("List Startup Apps to Disable");
      lblNewLabel_10_1.setBounds(90, 340, 196, 23);
      startupAppsPanelPart2.add(lblNewLabel_10_1);
      lblNewLabel_10_1.setForeground(new Color(0, 0, 0));
      lblNewLabel_10_1.setFont(new Font("Tahoma", 1, 14));
      JButton startupWindowBtn_1 = new JButton("Get List");
      startupWindowBtn_1.setBounds(817, 344, 112, 35);
      startupAppsPanelPart2.add(startupWindowBtn_1);
      startupWindowBtn_1.setForeground(new Color(255, 255, 255));
      startupWindowBtn_1.setBackground(new Color(55, 65, 82));
      startupWindowBtn_1.setFont(new Font("Tahoma", 0, 12));
      JButton btnNewButton_1 = new JButton("Get List");
      btnNewButton_1.setBounds(817, 452, 112, 35);
      startupAppsPanelPart2.add(btnNewButton_1);
      btnNewButton_1.setForeground(new Color(255, 255, 255));
      btnNewButton_1.setBackground(new Color(55, 65, 82));
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            FirstTimeProcess pp = new FirstTimeProcess();
            pp.uninstallApps();
         }
      });
      btnNewButton_1.setFont(new Font("Tahoma", 0, 12));
      JLabel lblNewLabel_21_1 = new JLabel("<html>Uninstall Bloatwares</html>");
      lblNewLabel_21_1.setBounds(90, 452, 148, 17);
      startupAppsPanelPart2.add(lblNewLabel_21_1);
      lblNewLabel_21_1.setForeground(Color.BLACK);
      lblNewLabel_21_1.setFont(new Font("Tahoma", 1, 14));
      final JPanel PackageInstallerPanel = new JPanel();
      PackageInstallerPanel.setBackground(new Color(255, 255, 255));
      PackageInstallerPanel.setBounds(1952, 513, 77, 89);
      this.contentPane.add(PackageInstallerPanel);
      PackageInstallerPanel.setLayout((LayoutManager)null);
      JLabel lblOneClickDownload = new JLabel("One Click Download and Install Softwares");
      lblOneClickDownload.setBounds(79, 39, 346, 20);
      lblOneClickDownload.setHorizontalAlignment(0);
      lblOneClickDownload.setFont(new Font("Tahoma", 1, 16));
      PackageInstallerPanel.add(lblOneClickDownload);
      JLabel lblNewLabel_9_2 = new JLabel(createImageIcon("/com/resource/download32.png", "Storage"));
      lblNewLabel_9_2.setHorizontalAlignment(0);
      lblNewLabel_9_2.setBounds(29, 39, 40, 40);
      PackageInstallerPanel.add(lblNewLabel_9_2);
      JLabel lblItUsesWindows = new JLabel("It uses windows package manager to install applications");
      lblItUsesWindows.setHorizontalAlignment(2);
      lblItUsesWindows.setForeground(new Color(0, 134, 198));
      lblItUsesWindows.setFont(new Font("Tahoma", 0, 14));
      lblItUsesWindows.setBounds(82, 55, 423, 29);
      PackageInstallerPanel.add(lblItUsesWindows);
      JLabel lblNewLabel_38 = new JLabel("Web Browsers");
      lblNewLabel_38.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38.setBounds(40, 138, 123, 18);
      PackageInstallerPanel.add(lblNewLabel_38);
      JCheckBox chckbxChrome = new JCheckBox("Chrome");
      chckbxChrome.setBackground(new Color(255, 255, 255));
      chckbxChrome.setFont(new Font("Tahoma", 0, 12));
      chckbxChrome.setBounds(40, 163, 79, 21);
      PackageInstallerPanel.add(chckbxChrome);
      JCheckBox chckbxFirefox = new JCheckBox("Firefox");
      chckbxFirefox.setBackground(new Color(255, 255, 255));
      chckbxFirefox.setFont(new Font("Tahoma", 0, 12));
      chckbxFirefox.setBounds(40, 193, 79, 21);
      PackageInstallerPanel.add(chckbxFirefox);
      JCheckBox chckbxOpera = new JCheckBox("Opera");
      chckbxOpera.setBackground(new Color(255, 255, 255));
      chckbxOpera.setFont(new Font("Tahoma", 0, 12));
      chckbxOpera.setBounds(40, 223, 79, 21);
      PackageInstallerPanel.add(chckbxOpera);
      JCheckBox chckbxOperaGx = new JCheckBox("Opera GX");
      chckbxOperaGx.setBackground(new Color(255, 255, 255));
      chckbxOperaGx.setFont(new Font("Tahoma", 0, 12));
      chckbxOperaGx.setBounds(40, 253, 79, 21);
      PackageInstallerPanel.add(chckbxOperaGx);
      JLabel lblNewLabel_38_1 = new JLabel("Compression");
      lblNewLabel_38_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_1.setBounds(40, 307, 109, 18);
      PackageInstallerPanel.add(lblNewLabel_38_1);
      JCheckBox chckbxWinzip = new JCheckBox("WinZip");
      chckbxWinzip.setBackground(new Color(255, 255, 255));
      chckbxWinzip.setFont(new Font("Tahoma", 0, 12));
      chckbxWinzip.setBounds(40, 331, 79, 21);
      PackageInstallerPanel.add(chckbxWinzip);
      JCheckBox chckbxWinrar = new JCheckBox("WinRAR");
      chckbxWinrar.setBackground(new Color(255, 255, 255));
      chckbxWinrar.setFont(new Font("Tahoma", 0, 12));
      chckbxWinrar.setBounds(40, 361, 79, 21);
      PackageInstallerPanel.add(chckbxWinrar);
      JCheckBox chckbxZip = new JCheckBox("7 Zip");
      chckbxZip.setBackground(new Color(255, 255, 255));
      chckbxZip.setFont(new Font("Tahoma", 0, 12));
      chckbxZip.setBounds(40, 391, 79, 21);
      PackageInstallerPanel.add(chckbxZip);
      JLabel lblNewLabel_38_1_1 = new JLabel("Messaging & Meetings");
      lblNewLabel_38_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_1_1.setBounds(40, 424, 156, 18);
      PackageInstallerPanel.add(lblNewLabel_38_1_1);
      JCheckBox chckbxZoom = new JCheckBox("Zoom");
      chckbxZoom.setBackground(new Color(255, 255, 255));
      chckbxZoom.setFont(new Font("Tahoma", 0, 12));
      chckbxZoom.setBounds(40, 448, 79, 21);
      PackageInstallerPanel.add(chckbxZoom);
      JCheckBox chckbxMsTeams = new JCheckBox("MS Teams");
      chckbxMsTeams.setBackground(new Color(255, 255, 255));
      chckbxMsTeams.setFont(new Font("Tahoma", 0, 12));
      chckbxMsTeams.setBounds(40, 478, 96, 21);
      PackageInstallerPanel.add(chckbxMsTeams);
      JCheckBox chckbxSkype = new JCheckBox("Skype");
      chckbxSkype.setBackground(new Color(255, 255, 255));
      chckbxSkype.setFont(new Font("Tahoma", 0, 12));
      chckbxSkype.setBounds(40, 508, 79, 21);
      PackageInstallerPanel.add(chckbxSkype);
      JCheckBox chckbxDiscord = new JCheckBox("Discord");
      chckbxDiscord.setBackground(new Color(255, 255, 255));
      chckbxDiscord.setFont(new Font("Tahoma", 0, 12));
      chckbxDiscord.setBounds(40, 538, 79, 21);
      PackageInstallerPanel.add(chckbxDiscord);
      JLabel lblNewLabel_38_1_1_1 = new JLabel("Media Tools");
      lblNewLabel_38_1_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_1_1_1.setBounds(40, 569, 156, 18);
      PackageInstallerPanel.add(lblNewLabel_38_1_1_1);
      JCheckBox chckbxVlcPlayer = new JCheckBox("VLC Player");
      chckbxVlcPlayer.setBackground(new Color(255, 255, 255));
      chckbxVlcPlayer.setFont(new Font("Tahoma", 0, 12));
      chckbxVlcPlayer.setBounds(40, 593, 96, 21);
      PackageInstallerPanel.add(chckbxVlcPlayer);
      JCheckBox chckbxGomPlayer = new JCheckBox("GOM Player");
      chckbxGomPlayer.setBackground(new Color(255, 255, 255));
      chckbxGomPlayer.setFont(new Font("Tahoma", 0, 12));
      chckbxGomPlayer.setBounds(40, 623, 96, 21);
      PackageInstallerPanel.add(chckbxGomPlayer);
      JCheckBox chckbxWinamp = new JCheckBox("Winamp");
      chckbxWinamp.setBackground(new Color(255, 255, 255));
      chckbxWinamp.setFont(new Font("Tahoma", 0, 12));
      chckbxWinamp.setBounds(40, 653, 79, 21);
      PackageInstallerPanel.add(chckbxWinamp);
      JLabel lblNewLabel_38_1_1_1_1 = new JLabel("Images");
      lblNewLabel_38_1_1_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_1_1_1_1.setBounds(260, 138, 156, 18);
      PackageInstallerPanel.add(lblNewLabel_38_1_1_1_1);
      JCheckBox chckbxGimp = new JCheckBox("GIMP");
      chckbxGimp.setBackground(new Color(255, 255, 255));
      chckbxGimp.setFont(new Font("Tahoma", 0, 12));
      chckbxGimp.setBounds(260, 162, 96, 21);
      PackageInstallerPanel.add(chckbxGimp);
      JCheckBox chckbxIrfanView = new JCheckBox("Irfan view");
      chckbxIrfanView.setBackground(new Color(255, 255, 255));
      chckbxIrfanView.setFont(new Font("Tahoma", 0, 12));
      chckbxIrfanView.setBounds(260, 192, 96, 21);
      PackageInstallerPanel.add(chckbxIrfanView);
      JLabel lblNewLabel_38_2 = new JLabel("Creation");
      lblNewLabel_38_2.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2.setBounds(481, 142, 123, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2);
      JCheckBox chckbxAudacity = new JCheckBox("Audacity");
      chckbxAudacity.setBackground(new Color(255, 255, 255));
      chckbxAudacity.setFont(new Font("Tahoma", 0, 12));
      chckbxAudacity.setBounds(481, 163, 79, 21);
      PackageInstallerPanel.add(chckbxAudacity);
      JCheckBox chckbxUnity = new JCheckBox("Unity");
      chckbxUnity.setBackground(new Color(255, 255, 255));
      chckbxUnity.setFont(new Font("Tahoma", 0, 12));
      chckbxUnity.setBounds(481, 193, 79, 21);
      PackageInstallerPanel.add(chckbxUnity);
      JCheckBox chckbxBlender = new JCheckBox("Blender");
      chckbxBlender.setBackground(new Color(255, 255, 255));
      chckbxBlender.setFont(new Font("Tahoma", 0, 12));
      chckbxBlender.setBounds(481, 223, 79, 21);
      PackageInstallerPanel.add(chckbxBlender);
      JCheckBox chckbxSpotify = new JCheckBox("Spotify");
      chckbxSpotify.setBackground(new Color(255, 255, 255));
      chckbxSpotify.setFont(new Font("Tahoma", 0, 12));
      chckbxSpotify.setBounds(40, 683, 79, 21);
      PackageInstallerPanel.add(chckbxSpotify);
      JLabel lblNewLabel_38_2_1 = new JLabel("Gaming Clients");
      lblNewLabel_38_2_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_1.setBounds(264, 231, 123, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_1);
      JCheckBox chckbxEpicStore = new JCheckBox("Epic Games");
      chckbxEpicStore.setBackground(new Color(255, 255, 255));
      chckbxEpicStore.setFont(new Font("Tahoma", 0, 12));
      chckbxEpicStore.setBounds(264, 255, 96, 21);
      PackageInstallerPanel.add(chckbxEpicStore);
      JCheckBox chckbxSteam = new JCheckBox("Steam");
      chckbxSteam.setBackground(new Color(255, 255, 255));
      chckbxSteam.setFont(new Font("Tahoma", 0, 12));
      chckbxSteam.setBounds(264, 285, 79, 21);
      PackageInstallerPanel.add(chckbxSteam);
      JCheckBox chckbxXbox = new JCheckBox("XBOX");
      chckbxXbox.setBackground(new Color(255, 255, 255));
      chckbxXbox.setFont(new Font("Tahoma", 0, 12));
      chckbxXbox.setBounds(264, 315, 79, 21);
      PackageInstallerPanel.add(chckbxXbox);
      JCheckBox chckbxEaApp = new JCheckBox("Amazon Games");
      chckbxEaApp.setBackground(new Color(255, 255, 255));
      chckbxEaApp.setFont(new Font("Tahoma", 0, 12));
      chckbxEaApp.setBounds(264, 345, 109, 21);
      PackageInstallerPanel.add(chckbxEaApp);
      JCheckBox chckbxUbisoftConnect = new JCheckBox("Ubisoft Connect");
      chckbxUbisoftConnect.setBackground(new Color(255, 255, 255));
      chckbxUbisoftConnect.setFont(new Font("Tahoma", 0, 12));
      chckbxUbisoftConnect.setBounds(264, 375, 123, 21);
      PackageInstallerPanel.add(chckbxUbisoftConnect);
      JLabel lblNewLabel_38_2_1_1 = new JLabel("System Tools");
      lblNewLabel_38_2_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_1_1.setBounds(264, 433, 123, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_1_1);
      JCheckBox chckbxNvidiaGeforceExperience = new JCheckBox("<html>NVIDIA GeForce Experience</html>");
      chckbxNvidiaGeforceExperience.setBackground(new Color(255, 255, 255));
      chckbxNvidiaGeforceExperience.setVerticalAlignment(1);
      chckbxNvidiaGeforceExperience.setFont(new Font("Tahoma", 0, 12));
      chckbxNvidiaGeforceExperience.setBounds(264, 577, 123, 42);
      PackageInstallerPanel.add(chckbxNvidiaGeforceExperience);
      JCheckBox chckbxObsStudio = new JCheckBox("OBS Studio");
      chckbxObsStudio.setBackground(new Color(255, 255, 255));
      chckbxObsStudio.setFont(new Font("Tahoma", 0, 12));
      chckbxObsStudio.setBounds(264, 547, 109, 21);
      PackageInstallerPanel.add(chckbxObsStudio);
      JCheckBox chckbxHwinfo = new JCheckBox("HWiNFO");
      chckbxHwinfo.setBackground(new Color(255, 255, 255));
      chckbxHwinfo.setFont(new Font("Tahoma", 0, 12));
      chckbxHwinfo.setBounds(264, 517, 79, 21);
      PackageInstallerPanel.add(chckbxHwinfo);
      JCheckBox chckbxGpuz = new JCheckBox("GPUz");
      chckbxGpuz.setBackground(new Color(255, 255, 255));
      chckbxGpuz.setFont(new Font("Tahoma", 0, 12));
      chckbxGpuz.setBounds(264, 487, 79, 21);
      PackageInstallerPanel.add(chckbxGpuz);
      JCheckBox chckbxCpuz = new JCheckBox("CPUz");
      chckbxCpuz.setBackground(new Color(255, 255, 255));
      chckbxCpuz.setFont(new Font("Tahoma", 0, 12));
      chckbxCpuz.setBounds(264, 457, 96, 21);
      PackageInstallerPanel.add(chckbxCpuz);
      JCheckBox chckbxHwMonitor = new JCheckBox("HW Monitor");
      chckbxHwMonitor.setBackground(new Color(255, 255, 255));
      chckbxHwMonitor.setFont(new Font("Tahoma", 0, 12));
      chckbxHwMonitor.setBounds(264, 623, 109, 21);
      PackageInstallerPanel.add(chckbxHwMonitor);
      JLabel lblNewLabel_38_2_1_1_1 = new JLabel("Office & Tools");
      lblNewLabel_38_2_1_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_1_1_1.setBounds(481, 268, 123, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_1_1_1);
      JCheckBox chckbxLibreOffice = new JCheckBox("Libre Office");
      chckbxLibreOffice.setBackground(new Color(255, 255, 255));
      chckbxLibreOffice.setFont(new Font("Tahoma", 0, 12));
      chckbxLibreOffice.setBounds(481, 293, 96, 21);
      PackageInstallerPanel.add(chckbxLibreOffice);
      JLabel lblNewLabel_38_2_2 = new JLabel("Development Tools");
      lblNewLabel_38_2_2.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_2.setBounds(728, 138, 148, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_2);
      JCheckBox chckbxNotepad = new JCheckBox("Notepad++");
      chckbxNotepad.setBackground(new Color(255, 255, 255));
      chckbxNotepad.setFont(new Font("Tahoma", 0, 12));
      chckbxNotepad.setBounds(728, 163, 109, 21);
      PackageInstallerPanel.add(chckbxNotepad);
      JCheckBox chckbxPutty = new JCheckBox("PuTTY");
      chckbxPutty.setBackground(new Color(255, 255, 255));
      chckbxPutty.setFont(new Font("Tahoma", 0, 12));
      chckbxPutty.setBounds(728, 193, 79, 21);
      PackageInstallerPanel.add(chckbxPutty);
      JCheckBox chckbxWinscp = new JCheckBox("WinScp");
      chckbxWinscp.setBackground(new Color(255, 255, 255));
      chckbxWinscp.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp.setBounds(728, 223, 79, 21);
      PackageInstallerPanel.add(chckbxWinscp);
      JCheckBox chckbxAndroidStudio = new JCheckBox("Android Studio");
      chckbxAndroidStudio.setBackground(new Color(255, 255, 255));
      chckbxAndroidStudio.setFont(new Font("Tahoma", 0, 12));
      chckbxAndroidStudio.setBounds(728, 253, 109, 21);
      PackageInstallerPanel.add(chckbxAndroidStudio);
      JCheckBox chckbxPostman = new JCheckBox("Postman");
      chckbxPostman.setBackground(new Color(255, 255, 255));
      chckbxPostman.setFont(new Font("Tahoma", 0, 12));
      chckbxPostman.setBounds(728, 283, 79, 21);
      PackageInstallerPanel.add(chckbxPostman);
      JCheckBox chckbxWinscp_1_1 = new JCheckBox("<html>IntelliJ IDEA Community Edition</html>");
      chckbxWinscp_1_1.setBackground(new Color(255, 255, 255));
      chckbxWinscp_1_1.setVerticalAlignment(1);
      chckbxWinscp_1_1.setHorizontalAlignment(0);
      chckbxWinscp_1_1.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_1_1.setBounds(728, 343, 136, 42);
      PackageInstallerPanel.add(chckbxWinscp_1_1);
      JCheckBox chckbxGithubDesktop = new JCheckBox("GitHub Desktop");
      chckbxGithubDesktop.setBackground(new Color(255, 255, 255));
      chckbxGithubDesktop.setFont(new Font("Tahoma", 0, 12));
      chckbxGithubDesktop.setBounds(728, 313, 109, 21);
      PackageInstallerPanel.add(chckbxGithubDesktop);
      JCheckBox chckbxEvernote = new JCheckBox("Evernote");
      chckbxEvernote.setBackground(new Color(255, 255, 255));
      chckbxEvernote.setFont(new Font("Tahoma", 0, 12));
      chckbxEvernote.setBounds(481, 323, 96, 21);
      PackageInstallerPanel.add(chckbxEvernote);
      JLabel lblNewLabel_38_2_2_1 = new JLabel("Security Tools");
      lblNewLabel_38_2_2_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_2_1.setBounds(728, 399, 148, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_2_1);
      JCheckBox chckbxOpenvpn = new JCheckBox("Open VPN");
      chckbxOpenvpn.setBackground(new Color(255, 255, 255));
      chckbxOpenvpn.setFont(new Font("Tahoma", 0, 12));
      chckbxOpenvpn.setBounds(728, 423, 109, 21);
      PackageInstallerPanel.add(chckbxOpenvpn);
      JCheckBox chckbxMalwareBytes = new JCheckBox("<html>Malwarebytes Windows Firewall Control</html>");
      chckbxMalwareBytes.setBackground(new Color(255, 255, 255));
      chckbxMalwareBytes.setVerticalAlignment(1);
      chckbxMalwareBytes.setHorizontalAlignment(0);
      chckbxMalwareBytes.setFont(new Font("Tahoma", 0, 12));
      chckbxMalwareBytes.setBounds(728, 513, 169, 42);
      PackageInstallerPanel.add(chckbxMalwareBytes);
      JCheckBox chckbxMalwarebytes = new JCheckBox("Malwarebytes");
      chckbxMalwarebytes.setBackground(new Color(255, 255, 255));
      chckbxMalwarebytes.setFont(new Font("Tahoma", 0, 12));
      chckbxMalwarebytes.setBounds(728, 483, 109, 21);
      PackageInstallerPanel.add(chckbxMalwarebytes);
      JCheckBox chckbxWinscp_2_1 = new JCheckBox("Nord VPN");
      chckbxWinscp_2_1.setBackground(new Color(255, 255, 255));
      chckbxWinscp_2_1.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_2_1.setBounds(728, 453, 79, 21);
      PackageInstallerPanel.add(chckbxWinscp_2_1);
      JLabel lblNewLabel_38_2_2_1_1 = new JLabel("Others");
      lblNewLabel_38_2_2_1_1.setFont(new Font("Tahoma", 1, 13));
      lblNewLabel_38_2_2_1_1.setBounds(481, 397, 148, 18);
      PackageInstallerPanel.add(lblNewLabel_38_2_2_1_1);
      JCheckBox chckbxFacebook = new JCheckBox(".Net FrameWork");
      chckbxFacebook.setBackground(new Color(255, 255, 255));
      chckbxFacebook.setFont(new Font("Tahoma", 0, 12));
      chckbxFacebook.setBounds(481, 450, 136, 21);
      PackageInstallerPanel.add(chckbxFacebook);
      JCheckBox chckbxCutepdf = new JCheckBox("CutePDF Writer");
      chckbxCutepdf.setBackground(new Color(255, 255, 255));
      chckbxCutepdf.setFont(new Font("Tahoma", 0, 12));
      chckbxCutepdf.setBounds(481, 353, 144, 21);
      PackageInstallerPanel.add(chckbxCutepdf);
      JCheckBox chckbxWinscp_2_1_1_1 = new JCheckBox("FXSounds");
      chckbxWinscp_2_1_1_1.setBackground(new Color(255, 255, 255));
      chckbxWinscp_2_1_1_1.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_2_1_1_1.setBounds(481, 480, 109, 21);
      PackageInstallerPanel.add(chckbxWinscp_2_1_1_1);
      JCheckBox chckbxWinscp_2_1_1_1_1 = new JCheckBox("Droid Cam");
      chckbxWinscp_2_1_1_1_1.setBackground(new Color(255, 255, 255));
      chckbxWinscp_2_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_2_1_1_1_1.setBounds(481, 510, 109, 21);
      PackageInstallerPanel.add(chckbxWinscp_2_1_1_1_1);
      JCheckBox chckbxBrave = new JCheckBox("Brave");
      chckbxBrave.setBackground(new Color(255, 255, 255));
      chckbxBrave.setFont(new Font("Tahoma", 0, 12));
      chckbxBrave.setBounds(41, 280, 79, 21);
      PackageInstallerPanel.add(chckbxBrave);
      JCheckBox chckbxWinscp_2_1_1_2 = new JCheckBox("WhatsApp");
      chckbxWinscp_2_1_1_2.setBackground(new Color(255, 255, 255));
      chckbxWinscp_2_1_1_2.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_2_1_1_2.setBounds(481, 420, 109, 21);
      PackageInstallerPanel.add(chckbxWinscp_2_1_1_2);
      JCheckBox chckbxWinscp_2_1_1_2_1 = new JCheckBox("WebEx");
      chckbxWinscp_2_1_1_2_1.setBackground(new Color(255, 255, 255));
      chckbxWinscp_2_1_1_2_1.setFont(new Font("Tahoma", 0, 12));
      chckbxWinscp_2_1_1_2_1.setBounds(481, 538, 109, 21);
      PackageInstallerPanel.add(chckbxWinscp_2_1_1_2_1);
      JButton btnNewButton_3 = new JButton("Install");
      btnNewButton_3.setForeground(new Color(255, 255, 255));
      btnNewButton_3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            InstallWinGetPackagePage page = new InstallWinGetPackagePage();
            Thread t = new Thread(page);
            t.start();
            ArrayList<String> list = new ArrayList();
            Component[] components = PackageInstallerPanel.getComponents();
            Component[] var9 = components;
            int var8 = components.length;

            for(int var7 = 0; var7 < var8; ++var7) {
               Component component = var9[var7];
               if (component.getClass().equals(JCheckBox.class) && ((JCheckBox)component).isSelected()) {
                  list.add(((JCheckBox)component).getText().replace(" ", "_"));
               }
            }

            FirstTimeProcess p = new FirstTimeProcess();
            p.installApps(list, "install");
         }
      });
      btnNewButton_3.setBackground(new Color(55, 65, 82));
      btnNewButton_3.setFont(new Font("Tahoma", 1, 12));
      btnNewButton_3.setBounds(377, 743, 119, 40);
      PackageInstallerPanel.add(btnNewButton_3);
      JCheckBox chckbxEaGames = new JCheckBox("EA Games");
      chckbxEaGames.setBackground(new Color(255, 255, 255));
      chckbxEaGames.setFont(new Font("Tahoma", 0, 12));
      chckbxEaGames.setBounds(264, 402, 79, 21);
      PackageInstallerPanel.add(chckbxEaGames);
      JSeparator separator_3 = new JSeparator();
      separator_3.setBounds(26, 715, 937, 2);
      PackageInstallerPanel.add(separator_3);
      JCheckBox chckbxLivelyWallpaper = new JCheckBox("Lively Wallpaper");
      chckbxLivelyWallpaper.setBackground(new Color(255, 255, 255));
      chckbxLivelyWallpaper.setFont(new Font("Tahoma", 0, 12));
      chckbxLivelyWallpaper.setBounds(481, 568, 109, 21);
      PackageInstallerPanel.add(chckbxLivelyWallpaper);
      JCheckBox chckbxlogmeinHamachi = new JCheckBox("<html>LogMeIn Hamachi</html>");
      chckbxlogmeinHamachi.setBackground(new Color(255, 255, 255));
      chckbxlogmeinHamachi.setVerticalAlignment(1);
      chckbxlogmeinHamachi.setHorizontalAlignment(2);
      chckbxlogmeinHamachi.setFont(new Font("Tahoma", 0, 12));
      chckbxlogmeinHamachi.setBounds(481, 598, 130, 29);
      PackageInstallerPanel.add(chckbxlogmeinHamachi);
      JCheckBox chckbxExpressvpn = new JCheckBox("ExpressVPN");
      chckbxExpressvpn.setBackground(new Color(255, 255, 255));
      chckbxExpressvpn.setVerticalAlignment(1);
      chckbxExpressvpn.setHorizontalAlignment(2);
      chckbxExpressvpn.setFont(new Font("Tahoma", 0, 12));
      chckbxExpressvpn.setBounds(728, 553, 130, 29);
      PackageInstallerPanel.add(chckbxExpressvpn);
      JLabel lblNewLabel_39 = new JLabel(createImageIcon("/com/resource/imagesSmall.png", "Images"));
      lblNewLabel_39.setBounds(240, 140, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39);
      JLabel lblNewLabel_39_1 = new JLabel(createImageIcon("/com/resource/safari16.png", "Images"));
      lblNewLabel_39_1.setBounds(20, 140, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_1);
      JLabel lblNewLabel_39_1_1 = new JLabel(createImageIcon("/com/resource/Archive.png", "Images"));
      lblNewLabel_39_1_1.setBounds(20, 308, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_1_1);
      JLabel lblNewLabel_39_1_1_1 = new JLabel(createImageIcon("/com/resource/Messages.png", "Images"));
      lblNewLabel_39_1_1_1.setBounds(20, 426, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_1_1_1);
      JLabel lblNewLabel_39_1_1_1_1 = new JLabel(createImageIcon("/com/resource/Media.png", "Images"));
      lblNewLabel_39_1_1_1_1.setBounds(20, 571, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_1_1_1_1);
      JLabel lblNewLabel_39_1_1_1_1_1 = new JLabel(createImageIcon("/com/resource/optionSmall.png", "Images"));
      lblNewLabel_39_1_1_1_1_1.setBounds(240, 435, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_1_1_1_1_1);
      JLabel lblNewLabel_39_2 = new JLabel(createImageIcon("/com/resource/game.png", "Images"));
      lblNewLabel_39_2.setBounds(240, 232, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2);
      JLabel lblNewLabel_39_2_1 = new JLabel(createImageIcon("/com/resource/internetSmall.png", "Images"));
      lblNewLabel_39_2_1.setBounds(460, 142, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2_1);
      JLabel lblNewLabel_39_2_1_1 = new JLabel(createImageIcon("/com/resource/growth.png", "Images"));
      lblNewLabel_39_2_1_1.setBounds(460, 271, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2_1_1);
      JLabel lblNewLabel_39_2_1_1_1 = new JLabel(createImageIcon("/com/resource/setup.png", "Images"));
      lblNewLabel_39_2_1_1_1.setBounds(460, 396, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2_1_1_1);
      JLabel lblNewLabel_39_2_1_1_1_1 = new JLabel(createImageIcon("/com/resource/internetS.png", "Images"));
      lblNewLabel_39_2_1_1_1_1.setBounds(707, 401, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2_1_1_1_1);
      JLabel lblNewLabel_39_2_1_1_1_1_1 = new JLabel(createImageIcon("/com/resource/Learn1.png", "Images"));
      lblNewLabel_39_2_1_1_1_1_1.setBounds(707, 142, 16, 16);
      PackageInstallerPanel.add(lblNewLabel_39_2_1_1_1_1_1);
      JButton btnNewButton_3_1 = new JButton("UnInstall");
      btnNewButton_3_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ArrayList<String> list = new ArrayList();
            Component[] components = PackageInstallerPanel.getComponents();
            Component[] var7 = components;
            int var6 = components.length;

            for(int var5 = 0; var5 < var6; ++var5) {
               Component component = var7[var5];
               if (component.getClass().equals(JCheckBox.class) && ((JCheckBox)component).isSelected()) {
                  list.add(((JCheckBox)component).getText().replace(" ", "_"));
               }
            }

            FirstTimeProcess p = new FirstTimeProcess();
            p.installApps(list, "uninstall");
         }
      });
      btnNewButton_3_1.setForeground(Color.WHITE);
      btnNewButton_3_1.setFont(new Font("Tahoma", 1, 12));
      btnNewButton_3_1.setBackground(new Color(55, 65, 82));
      btnNewButton_3_1.setBounds(604, 743, 119, 40);
      PackageInstallerPanel.add(btnNewButton_3_1);
      JLabel lblActivityMayTake = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake.setForeground(new Color(255, 0, 0));
      lblActivityMayTake.setHorizontalAlignment(0);
      lblActivityMayTake.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake.setBounds(240, 90, 483, 20);
      PackageInstallerPanel.add(lblActivityMayTake);
      JButton AppDwlBtn = new JButton("package List");
      AppDwlBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               startupAppsPanelPart2.setVisible(false);
               PackageInstallerPanel.setVisible(true);
               PackageInstallerPanel.setBounds(205, 45, 1000, 810);
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.14 : " + var3.getMessage());
            }

         }
      });
      AppDwlBtn.setBounds(817, 561, 112, 35);
      startupAppsPanelPart2.add(AppDwlBtn);
      AppDwlBtn.setForeground(new Color(255, 255, 255));
      AppDwlBtn.setBackground(new Color(55, 65, 82));
      AppDwlBtn.setFont(new Font("Tahoma", 0, 12));
      JLabel lblNewLabel_21 = new JLabel("<html>Download and install Free Apps</html>");
      lblNewLabel_21.setBounds(90, 561, 283, 17);
      startupAppsPanelPart2.add(lblNewLabel_21);
      lblNewLabel_21.setForeground(new Color(0, 0, 0));
      lblNewLabel_21.setFont(new Font("Tahoma", 1, 14));
      JLabel lblNewLabel_33_2_2_1_3 = new JLabel("Diownload required Apps from our website tubeandtech.com");
      lblNewLabel_33_2_2_1_3.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_3.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2_1_3.setBounds(90, 576, 314, 23);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1_3);
      JLabel lblNewLabel_33_2_2_1_1_1 = new JLabel("<html>Enable or disable the new widget running in background</html>");
      lblNewLabel_33_2_2_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2_1_1_1.setBounds(90, 360, 318, 29);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1_1_1);
      JLabel lblNewLabel_33_2_2_1_1_1_1 = new JLabel("<html>Enable or disable the new widget running in background</html>");
      lblNewLabel_33_2_2_1_1_1_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_1_1_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2_1_1_1_1.setBounds(90, 472, 318, 29);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_3_1 = new JLabel(createImageIcon("/com/resource/uninstall.png", "server"));
      lblNewLabel_36_1_1_1_2_1_1_1_3_1.setBounds(42, 444, 40, 40);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_3_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_3_1_1 = new JLabel(createImageIcon("/com/resource/download.png", "server"));
      lblNewLabel_36_1_1_1_2_1_1_1_3_1_1.setBounds(42, 559, 40, 40);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_3_1_1);
      JLabel linktoSiteLbl_2_1 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2_1.setHorizontalAlignment(0);
      linktoSiteLbl_2_1.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_1.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_1.setBounds(810, 36, 184, 36);
      startupAppsPanelPart2.add(linktoSiteLbl_2_1);
      JSeparator separator_1_1_1_1_1 = new JSeparator();
      separator_1_1_1_1_1.setOpaque(true);
      separator_1_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_1.setBounds(49, 200, 901, 3);
      startupAppsPanelPart2.add(separator_1_1_1_1_1);
      JSeparator separator_1_1_1_1_1_1 = new JSeparator();
      separator_1_1_1_1_1_1.setOpaque(true);
      separator_1_1_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_1_1.setBounds(45, 303, 901, 3);
      startupAppsPanelPart2.add(separator_1_1_1_1_1_1);
      JSeparator separator_1_1_1_1_1_1_1 = new JSeparator();
      separator_1_1_1_1_1_1_1.setOpaque(true);
      separator_1_1_1_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_1_1_1.setBounds(44, 417, 901, 3);
      startupAppsPanelPart2.add(separator_1_1_1_1_1_1_1);
      JSeparator separator_1_1_1_1_1_1_1_1 = new JSeparator();
      separator_1_1_1_1_1_1_1_1.setOpaque(true);
      separator_1_1_1_1_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_1_1_1_1.setBounds(31, 529, 901, 3);
      startupAppsPanelPart2.add(separator_1_1_1_1_1_1_1_1);
      JSeparator separator_1_1_1_1_1_1_1_1_1 = new JSeparator();
      separator_1_1_1_1_1_1_1_1_1.setOpaque(true);
      separator_1_1_1_1_1_1_1_1_1.setBackground(Color.WHITE);
      separator_1_1_1_1_1_1_1_1_1.setBounds(31, 633, 901, 3);
      startupAppsPanelPart2.add(separator_1_1_1_1_1_1_1_1_1);
      JLabel lblNewLabel_21_2 = new JLabel("Setup Windows Visual Effects");
      lblNewLabel_21_2.setForeground(Color.BLACK);
      lblNewLabel_21_2.setFont(new Font("Tahoma", 1, 14));
      lblNewLabel_21_2.setBounds(93, 662, 315, 17);
      startupAppsPanelPart2.add(lblNewLabel_21_2);
      JLabel lblNewLabel_33_2_2_1_3_1 = new JLabel("<html>Choose to make changes in system visual effects. More visually stunning more resources it utilize.</html>");
      lblNewLabel_33_2_2_1_3_1.setToolTipText("System Restoration Poin.");
      lblNewLabel_33_2_2_1_3_1.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_33_2_2_1_3_1.setBounds(93, 677, 314, 33);
      startupAppsPanelPart2.add(lblNewLabel_33_2_2_1_3_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_1 = new JLabel(createImageIcon("/com/resource/virtual.png", "Performance"));
      lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_1.setBounds(45, 660, 40, 40);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_1);
      JLabel lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_2 = new JLabel((Icon)null);
      lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_2.setBounds(330, 660, 32, 23);
      startupAppsPanelPart2.add(lblNewLabel_36_1_1_1_2_1_1_1_3_1_1_2);
      String[] visualFX = new String[]{"Let Windows Choose", "Best Appearance", "Best Performance"};
      final JComboBox visualFXCombo = new JComboBox(visualFX);
      visualFXCombo.setModel(new DefaultComboBoxModel(new String[]{"-- Select One --", "Let Windows Choose", "Best Appearance", "Best Performance"}));
      visualFXCombo.setFont(new Font("Tahoma", 0, 13));
      visualFXCombo.setBounds(570, 680, 186, 30);
      startupAppsPanelPart2.add(visualFXCombo);
      JButton setupFXBtn = new JButton("Update");
      setupFXBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            FirstTimeProcess pp = new FirstTimeProcess();
            String s = pp.setupVisualFX(visualFXCombo.getSelectedIndex());
            visualFXCombo.removeAllItems();

            for(int i = 0; i < s.split(",").length; ++i) {
               visualFXCombo.addItem(s.split(",")[i]);
               if (s.split(",")[i].contains("*")) {
                  firstTimeSetupLbl_1.setText(s.split(",")[i].split("*")[0]);
               }
            }

            JOptionPane.showMessageDialog(MainFrame.this, "Visual Effects set to : " + visualFXCombo.getSelectedIndex(), "DNS Server Setup", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      final JLabel visualFxDetails = new JLabel("");
      visualFxDetails.setFont(new Font("Tahoma", 0, 12));
      visualFxDetails.setBounds(570, 722, 186, 58);
      startupAppsPanelPart2.add(visualFxDetails);
      visualFXCombo.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if (visualFXCombo.getSelectedIndex() != -1) {
               int selectedIndex = visualFXCombo.getSelectedIndex();
               if (selectedIndex == 0) {
                  visualFxDetails.setText("<html>Windows will choose whats best for your system</html>");
               } else if (selectedIndex == 1) {
                  visualFxDetails.setText("<html>It will set for best apperance but consumes system resources</html>");
               } else if (selectedIndex == 2) {
                  visualFxDetails.setText("<html>It will set for best performance and releases system resources for other use</html>");
               }
            }

         }
      });
      setupFXBtn.setForeground(Color.WHITE);
      setupFXBtn.setFont(new Font("Tahoma", 0, 12));
      setupFXBtn.setBackground(new Color(55, 65, 82));
      setupFXBtn.setBounds(817, 677, 112, 35);
      startupAppsPanelPart2.add(setupFXBtn);
      JButton btnGetKey_1 = new JButton("<<");
      btnGetKey_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            startupAppsPanelPart2.setVisible(false);
            startupAppsPanelPart1.setVisible(true);
         }
      });
      btnGetKey_1.setForeground(Color.WHITE);
      btnGetKey_1.setFont(new Font("Tahoma", 0, 12));
      btnGetKey_1.setBackground(new Color(0, 64, 128));
      btnGetKey_1.setBounds(21, 763, 61, 26);
      startupAppsPanelPart2.add(btnGetKey_1);
      JLabel lblActivityMayTake_1_1_1_1 = new JLabel("Once you click on any activity please wait for some time to finish it.");
      lblActivityMayTake_1_1_1_1.setHorizontalAlignment(0);
      lblActivityMayTake_1_1_1_1.setForeground(Color.RED);
      lblActivityMayTake_1_1_1_1.setFont(new Font("Tahoma", 1, 14));
      lblActivityMayTake_1_1_1_1.setBounds(254, 92, 494, 20);
      startupAppsPanelPart2.add(lblActivityMayTake_1_1_1_1);

      try {
         FileReader ff = new FileReader(new File(System.getProperty("user.dir") + "\\private\\udd.dll"));
         new BufferedReader(ff);
      } catch (Exception var507) {
         this.logger.info("50.15" + var507.getMessage());
      }

      JButton registerBtn = new JButton("Register", (Icon)null);
      registerBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            ProcessMe me = new ProcessMe();
            String result = me.process(MainFrame.this.ActCodeTxtField.getText());
            if (result.equals("S")) {
               JOptionPane.showMessageDialog(MainFrame.this, "Congratulations! Activation Succesfull..Kinldy restart the application.", "Congratulations", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            } else if (result.equals("N")) {
               JOptionPane.showMessageDialog(MainFrame.this, "Activation Failed. Please check the ID and Code then try again.", "Activation Failed", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
            }

         }
      });
      registerBtn.setToolTipText("Battery Information");
      registerBtn.setFont(new Font("Tahoma", 0, 13));
      registerBtn.setBounds(678, 659, 100, 37);
      registerBtn.setForeground(new Color(255, 255, 255));
      registerBtn.setBackground(new Color(0, 134, 198));
      donationPanel.add(registerBtn);
      JButton cancleBtn = new JButton("Clear", (Icon)null);
      cancleBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            MainFrame.this.ActCodeTxtField.setText("");
         }
      });
      cancleBtn.setToolTipText("All Components Running Status");
      cancleBtn.setFont(new Font("Tahoma", 0, 13));
      cancleBtn.setBounds(854, 659, 100, 37);
      cancleBtn.setForeground(new Color(255, 255, 255));
      cancleBtn.setBackground(new Color(0, 134, 198));
      donationPanel.add(cancleBtn);
      JLabel lblThanksForRegistering_1 = new JLabel("Thanks for registering this copy. It will help us grow and boot our confidance to make more such applications.");
      lblThanksForRegistering_1.setHorizontalAlignment(0);
      lblThanksForRegistering_1.setForeground(new Color(0, 0, 255));
      lblThanksForRegistering_1.setFont(new Font("Tahoma", 0, 14));
      lblThanksForRegistering_1.setBounds(66, 749, 863, 37);
      donationPanel.add(lblThanksForRegistering_1);
      this.ActCodeTxtField = new JTextField();
      if (licenceCheck) {
         this.ActCodeTxtField.setEnabled(false);
         cancleBtn.setEnabled(false);
         registerBtn.setEnabled(false);
      } else {
         this.ActCodeTxtField.setEnabled(true);
         cancleBtn.setEnabled(true);
         registerBtn.setEnabled(true);
      }

      this.ActCodeTxtField.setColumns(10);
      this.ActCodeTxtField.setBounds(678, 603, 276, 35);
      donationPanel.add(this.ActCodeTxtField);
      JButton BtnWebsite = new JButton("Website", (Icon)null);
      BtnWebsite.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://www.tubeandtech.com/services-1"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.18 : " + var3.getMessage());
            }

         }
      });
      if (licenceCheck) {
         BtnWebsite.setEnabled(false);
      } else {
         BtnWebsite.setEnabled(true);
      }

      BtnWebsite.setToolTipText("Battery Information");
      BtnWebsite.setFont(new Font("Tahoma", 0, 13));
      BtnWebsite.setBounds(313, 659, 116, 37);
      BtnWebsite.setForeground(new Color(255, 255, 255));
      BtnWebsite.setBackground(new Color(0, 134, 198));
      donationPanel.add(BtnWebsite);
      JLabel lblNewLabel_19_3 = new JLabel("<html>Before requesting for activation code please copy<br> your user ID. You can buy from our website or Discord<br> as well</html>");
      lblNewLabel_19_3.setHorizontalAlignment(0);
      lblNewLabel_19_3.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_19_3.setBounds(305, 585, 312, 53);
      donationPanel.add(lblNewLabel_19_3);
      JLabel lblNewLabel_19_3_1 = new JLabel("Step-2");
      lblNewLabel_19_3_1.setFont(new Font("Tahoma", 1, 12));
      lblNewLabel_19_3_1.setBounds(660, 557, 55, 23);
      donationPanel.add(lblNewLabel_19_3_1);
      JLabel lblNewLabel_19_3_1_1 = new JLabel("Step-1");
      lblNewLabel_19_3_1_1.setFont(new Font("Tahoma", 1, 12));
      lblNewLabel_19_3_1_1.setBounds(313, 557, 55, 23);
      donationPanel.add(lblNewLabel_19_3_1_1);
      JLabel lblNewLabel_19_3_3 = new JLabel("Enter the Activation Code you received");
      lblNewLabel_19_3_3.setHorizontalAlignment(0);
      lblNewLabel_19_3_3.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_19_3_3.setBounds(678, 573, 274, 20);
      donationPanel.add(lblNewLabel_19_3_3);
      JLabel lblDonateus = new JLabel("Donate US");
      lblDonateus.setHorizontalAlignment(0);
      lblDonateus.setFont(new Font("Tahoma", 1, 16));
      lblDonateus.setBounds(76, 510, 94, 37);
      donationPanel.add(lblDonateus);
      JSeparator separator_4 = new JSeparator();
      separator_4.setOrientation(1);
      separator_4.setBounds(284, 540, 1, 157);
      donationPanel.add(separator_4);
      JSeparator separator_4_1 = new JSeparator();
      separator_4_1.setOrientation(1);
      separator_4_1.setBounds(634, 598, 1, 99);
      donationPanel.add(separator_4_1);
      JLabel lblNewLabel_19_3_4 = new JLabel("<html>It will help us to grow and make more applications like this for  you</html>");
      lblNewLabel_19_3_4.setHorizontalAlignment(0);
      lblNewLabel_19_3_4.setFont(new Font("Tahoma", 0, 12));
      lblNewLabel_19_3_4.setBounds(30, 573, 212, 53);
      donationPanel.add(lblNewLabel_19_3_4);
      JLabel lblNewLabel_40 = new JLabel();
      lblNewLabel_40.setFont(new Font("Calibri", 1, 26));
      lblNewLabel_40.setText("Why you must have RepairME ?");
      lblNewLabel_40.setBounds(313, 23, 369, 53);
      donationPanel.add(lblNewLabel_40);
      JButton donateUsButton = new JButton("Donate Us", (Icon)null);
      donateUsButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://www.tubeandtech.com/services-1"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.18 : " + var3.getMessage());
            }

         }
      });
      donateUsButton.setToolTipText("Battery Information");
      donateUsButton.setForeground(Color.WHITE);
      donateUsButton.setFont(new Font("Tahoma", 0, 13));
      donateUsButton.setBackground(new Color(0, 134, 198));
      donateUsButton.setBounds(30, 651, 206, 45);
      donationPanel.add(donateUsButton);
      JLabel lblBuyLicense = new JLabel("Buy License");
      lblBuyLicense.setHorizontalAlignment(0);
      lblBuyLicense.setFont(new Font("Tahoma", 1, 16));
      lblBuyLicense.setBounds(353, 510, 116, 37);
      donationPanel.add(lblBuyLicense);
      JButton btnDiscord = new JButton("Discord", (Icon)null);
      btnDiscord.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://discord.gg/wTd7sR5aDJ"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.18 : " + var3.getMessage());
            }

         }
      });
      if (licenceCheck) {
         btnDiscord.setEnabled(false);
      }

      btnDiscord.setToolTipText("Battery Information");
      btnDiscord.setForeground(Color.WHITE);
      btnDiscord.setFont(new Font("Tahoma", 0, 13));
      btnDiscord.setBackground(new Color(0, 134, 198));
      btnDiscord.setBounds(487, 659, 116, 37);
      donationPanel.add(btnDiscord);
      JLabel lblNewLabel_40_1 = new JLabel();
      lblNewLabel_40_1.setText("<html>1. 1-Click Software Download| Setup | Update.<br>2. Problem wise Solution design.<br>3. Best System Setup for Best Output.<br>4. Cheapest License Price.<br>5. *Complete Offline System.<br>6. Optimize For Gaming, Performance, Disks Usage, First Time Setup etc..<br></html>");
      lblNewLabel_40_1.setFont(new Font("Calibri", 0, 26));
      lblNewLabel_40_1.setBounds(41, 128, 857, 231);
      donationPanel.add(lblNewLabel_40_1);
      JSeparator separator_6 = new JSeparator();
      separator_6.setBounds(290, 74, 400, 2);
      donationPanel.add(separator_6);
      JLabel lblNewLabel = new JLabel(createImageIcon("/com/resource/donate.png", "Donation"));
      lblNewLabel.setBounds(36, 510, 30, 30);
      donationPanel.add(lblNewLabel);
      JLabel lblNewLabel_3 = new JLabel(createImageIcon("/com/resource/key.png", "Donation"));
      lblNewLabel_3.setBounds(313, 510, 30, 30);
      donationPanel.add(lblNewLabel_3);
      JLabel lblActivateLicense = new JLabel("Activate License");
      lblActivateLicense.setHorizontalAlignment(0);
      lblActivateLicense.setFont(new Font("Tahoma", 1, 16));
      lblActivateLicense.setBounds(691, 510, 141, 37);
      donationPanel.add(lblActivateLicense);
      JLabel lblNewLabel_3_1 = new JLabel(createImageIcon("/com/resource/Status.png", "Donation"));
      lblNewLabel_3_1.setBounds(660, 510, 30, 30);
      donationPanel.add(lblNewLabel_3_1);
      JLabel linktoSiteLbl_2_3_1 = new JLabel("Official Website");
      linktoSiteLbl_2_3_1.setHorizontalAlignment(0);
      linktoSiteLbl_2_3_1.setForeground(new Color(255, 255, 255));
      linktoSiteLbl_2_3_1.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_3_1.setBounds(1093, 11, 122, 28);
      this.contentPane.add(linktoSiteLbl_2_3_1);
      linktoSiteLbl_2_3_1.setCursor(Cursor.getPredefinedCursor(12));
      linktoSiteLbl_2_3_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent arg0) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("http://tubeandtech.com/"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.16 : " + var3.getMessage());
            }

         }
      });
      new JLabel(createImageIcon("/com/resource/premium.png", "Game"));
      JLabel lblNewLabel_35;
      if (licenceCheck) {
         lblNewLabel_35 = new JLabel(createImageIcon("/com/resource/premium.png", "Game"));
      } else {
         lblNewLabel_35 = new JLabel(createImageIcon("/com/resource/freeCopy.png", "Game"));
      }

      JLabel lblVersion = new JLabel("Version 1.0.0");
      lblVersion.setHorizontalAlignment(11);
      lblVersion.setForeground(Color.WHITE);
      lblVersion.setFont(new Font("Tahoma", 0, 14));
      lblVersion.setBounds(1064, 864, 136, 30);
      this.contentPane.add(lblVersion);
      final JPanel mainPanel = new JPanel();
      mainPanel.setBorder((Border)null);
      mainPanel.setBackground(new Color(55, 65, 82));
      mainPanel.setBounds(0, 0, 200, 918);
      this.contentPane.add(mainPanel);
      mainPanel.setLayout((LayoutManager)null);
      JSeparator separator_5 = new JSeparator();
      separator_5.setForeground(new Color(255, 255, 255));
      separator_5.setBounds(26, 726, 150, 2);
      mainPanel.add(separator_5);
      final JLabel SysInfoMainMenuLbl = new JLabel("System information", createImageIcon("/com/resource/SysInfo.png", "SysInfo"), 2);
      SysInfoMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      final JPanel sysInfoPanel = new JPanel();
      sysInfoPanel.setBackground(new Color(55, 65, 82));
      sysInfoPanel.setBounds(0, 118, 200, 50);
      mainPanel.add(sysInfoPanel);
      sysInfoPanel.setLayout((LayoutManager)null);
      MainUserSystemInfo minfo = new MainUserSystemInfo();
      cmptInfoLbl.setText("CPU Information");
      SysInfoImage.setIcon(createImageIcon("/com/resource/CPU.png", "CPU"));
      ExtraOutputLbl.setText(minfo.getCPUInfo());
      SysInfoMainMenuLbl.setBounds(20, 10, 148, 25);
      sysInfoPanel.add(SysInfoMainMenuLbl);
      SysInfoMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            MainUserSystemInfo minfo = new MainUserSystemInfo();
            cmptInfoLbl.setText("CPU Information");
            SysInfoImage.setIcon(MainFrame.createImageIcon("/com/resource/CPU.png", "Setup"));
            ExtraOutputLbl.setText(minfo.getCPUInfo());
            GamingPanel.setVisible(false);
            BrowserPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(true);
            DiskFeaturesPanel.setVisible(false);
            extraFeaturesPanel.setBounds(205, 45, 1000, 810);
            startupAppsPanelPart2.setVisible(false);
            donationPanel.setVisible(false);
            PackageInstallerPanel.setVisible(false);
            IntroPanel.setVisible(false);
            MainFrame.this.colourCoding(SysInfoMainMenuLbl, sysInfoPanel);
         }

         public void mouseEntered(MouseEvent e) {
            sysInfoPanel.setBackground(new Color(120, 136, 164));
            SysInfoMainMenuLbl.setBackground(new Color(120, 136, 164));
            SysInfoMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            sysInfoPanel.setBackground(new Color(55, 65, 82));
            SysInfoMainMenuLbl.setBackground(new Color(55, 65, 82));
            SysInfoMainMenuLbl.setOpaque(true);
         }
      });
      SysInfoMainMenuLbl.setForeground(new Color(255, 255, 255));
      SysInfoMainMenuLbl.setBackground(new Color(255, 255, 255));
      SysInfoMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      final JPanel BrowserMainMenuPanel = new JPanel();
      BrowserMainMenuPanel.setLayout((LayoutManager)null);
      BrowserMainMenuPanel.setBackground(new Color(55, 65, 82));
      BrowserMainMenuPanel.setBounds(0, 168, 200, 50);
      mainPanel.add(BrowserMainMenuPanel);
      final JLabel BrowserBtnMainMenuLbl = new JLabel("<html>Browser Performance</html>", createImageIcon("/com/resource/safari16.png", "browser"), 2);
      BrowserBtnMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      BrowserBtnMainMenuLbl.setBounds(20, 5, 180, 40);
      BrowserMainMenuPanel.add(BrowserBtnMainMenuLbl);
      BrowserBtnMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            BrowserPanel.setVisible(true);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            GamingPanel.setVisible(false);
            PackageInstallerPanel.setVisible(false);
            donationPanel.setVisible(false);
            IntroPanel.setVisible(false);
            BrowserPanel.setBounds(205, 45, 1000, 810);

            try {
               File fff = new File(System.getProperty("user.dir") + "\\private\\SysFile.dll");
               if (fff.exists()) {
                  FileReader ff = new FileReader(fff);
                  BufferedReader bufferedReader = new BufferedReader(ff);

                  String line;
                  while((line = bufferedReader.readLine()) != null) {
                     if (line.contains("DT::")) {
                        dnsClearDate.setText("<html>Last DNS Cache Cleared on " + line.substring(line.indexOf("DT::") + 4, line.indexOf("DN::")) + "</HTML>");
                        break;
                     }
                  }

                  bufferedReader.close();
                  ff.close();
               }
            } catch (Exception var6) {
               var6.printStackTrace();
               MainFrame.this.logger.info("50.17" + var6.getMessage());
            }

            MainFrame.this.colourCoding(BrowserBtnMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            BrowserMainMenuPanel.setBackground(new Color(120, 136, 164));
            BrowserBtnMainMenuLbl.setBackground(new Color(120, 136, 164));
            BrowserBtnMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            BrowserMainMenuPanel.setBackground(new Color(55, 65, 82));
            BrowserBtnMainMenuLbl.setBackground(new Color(55, 65, 82));
            BrowserBtnMainMenuLbl.setOpaque(true);
         }
      });
      BrowserBtnMainMenuLbl.setForeground(Color.WHITE);
      BrowserBtnMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      BrowserBtnMainMenuLbl.setBackground(Color.WHITE);
      final JPanel SysPerfmainMenuPanel = new JPanel();
      SysPerfmainMenuPanel.setLayout((LayoutManager)null);
      SysPerfmainMenuPanel.setBackground(new Color(55, 65, 82));
      SysPerfmainMenuPanel.setBounds(0, 218, 200, 50);
      mainPanel.add(SysPerfmainMenuPanel);
      final JLabel SysPerfMainMenuLbl = new JLabel("<html>System Performance</html>", createImageIcon("/com/resource/growth.png", "Performance"), 2);
      SysPerfMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      SysPerfMainMenuLbl.setBounds(20, 5, 160, 40);
      SysPerfmainMenuPanel.add(SysPerfMainMenuLbl);
      SysPerfMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            SystemPerformanceProcess sp = new SystemPerformanceProcess();
            cortanaBtn.setText(sp.cortanaStatus());
            CurrentRamLbl.setText("RAM: " + sp.curretnRamSize());
            MainFrame.this.virtualRamTxtbox.setText(String.valueOf(Integer.parseInt(sp.curretnRamSize().split(" ")[0]) * 2));
            BrowserPanel.setVisible(false);
            perfPannel.setVisible(true);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            donationPanel.setVisible(false);
            GamingPanel.setVisible(false);
            PackageInstallerPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            IntroPanel.setVisible(false);
            perfPannel.setBounds(205, 45, 1000, 810);
            boolean chkBoxstatus = false;
            sysMainChkBox.setEnabled(chkBoxstatus);
            searchChkBox.setEnabled(chkBoxstatus);
            icssvcChkBox.setEnabled(chkBoxstatus);
            faxChkBox.setEnabled(chkBoxstatus);
            wisvcChkBox.setEnabled(chkBoxstatus);
            teamServicesChkBox.setEnabled(chkBoxstatus);
            remoteRegChkBox.setEnabled(chkBoxstatus);
            retailDemoChkBox.setEnabled(chkBoxstatus);
            TapiSrvchkBox.setEnabled(chkBoxstatus);
            smartCardChkBox.setEnabled(chkBoxstatus);
            cuetchkBox.setEnabled(chkBoxstatus);
            MapsBrokerchkBox.setEnabled(chkBoxstatus);
            CertPropSvcchkBox.setEnabled(chkBoxstatus);
            touchKbndHandChkBox.setEnabled(chkBoxstatus);
            gamebarchkbox.setEnabled(chkBoxstatus);
            stopServiceBtn.setEnabled(chkBoxstatus);
            startServiceBtn.setEnabled(chkBoxstatus);
            MainFrame.this.colourCoding(SysPerfMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            SysPerfmainMenuPanel.setBackground(new Color(120, 136, 164));
            SysPerfMainMenuLbl.setBackground(new Color(120, 136, 164));
            SysPerfMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            SysPerfmainMenuPanel.setBackground(new Color(55, 65, 82));
            SysPerfMainMenuLbl.setBackground(new Color(55, 65, 82));
            SysPerfMainMenuLbl.setOpaque(true);
         }
      });
      SysPerfMainMenuLbl.setForeground(Color.WHITE);
      SysPerfMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      SysPerfMainMenuLbl.setBackground(Color.WHITE);
      final JPanel BestSysPerfMainMenuPanel = new JPanel();
      BestSysPerfMainMenuPanel.setLayout((LayoutManager)null);
      BestSysPerfMainMenuPanel.setBackground(new Color(55, 65, 82));
      BestSysPerfMainMenuPanel.setBounds(0, 268, 200, 50);
      mainPanel.add(BestSysPerfMainMenuPanel);
      final JLabel sysSetupMainMenuLbl = new JLabel("<html>Best System Setup</html>", createImageIcon("/com/resource/setup.png", "Setup"), 2);
      sysSetupMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      sysSetupMainMenuLbl.setBounds(20, 5, 160, 40);
      BestSysPerfMainMenuPanel.add(sysSetupMainMenuLbl);
      sysSetupMainMenuLbl.setForeground(Color.WHITE);
      sysSetupMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      sysSetupMainMenuLbl.setBackground(Color.WHITE);
      final JPanel DiskMainMenuPanel = new JPanel();
      DiskMainMenuPanel.setLayout((LayoutManager)null);
      DiskMainMenuPanel.setBackground(new Color(55, 65, 82));
      DiskMainMenuPanel.setBounds(0, 318, 200, 50);
      mainPanel.add(DiskMainMenuPanel);
      final JLabel DiskUtilityMainMenuLbl = new JLabel("<html>Disk Performance</html>", createImageIcon("/com/resource/ssd.png", "Drive"), 2);
      DiskUtilityMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      DiskUtilityMainMenuLbl.setBounds(20, 5, 160, 40);
      DiskMainMenuPanel.add(DiskUtilityMainMenuLbl);
      DiskUtilityMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            DiskUtilityMainMenuLbl.setBackground(new Color(44, 52, 66));
            File f = new File(System.getProperty("user.dir") + "\\private\\DiskFile.dll");
            boolean bb;
            if (f.exists() && f.length() != 0L) {
               bb = true;
               optimizeSSDBtn.setEnabled(bb);
               btnDefragHdd.setEnabled(bb);
               readWriteTestBtn.setEnabled(bb);
               diskCleanupBtn.setEnabled(bb);
               indexSSDBtn.setEnabled(bb);
               btnChangeNow.setEnabled(bb);
               String list = "";
               DiskProcess p = new DiskProcess();
               if (ssdDataLbl.getText().equals("")) {
                  ssdDataLbl.setText(p.getDriveList());
                  list = ssdDataLbl.getText();
               } else {
                  list = ssdDataLbl.getText();
               }

               String[] listDrives = list.split("@");
               String ssdList = listDrives[0];
               SSDCombo.removeAllItems();
               AllDriveListcombo.removeAllItems();
               enableDisableIndexCmbBox.removeAllItems();
               AllDriveListcomboDiskCleanup.removeAllItems();
               HDDCombo.removeAllItems();
               SSDCombo.addItem("--Select--");
               AllDriveListcombo.addItem("--Select--");
               enableDisableIndexCmbBox.addItem("--Select--");
               AllDriveListcomboDiskCleanup.addItem("--Select--");
               HDDCombo.addItem("--Select--");
               if (ssdList.length() > 0) {
                  for(int i = 0; i < ssdList.split("#").length; ++i) {
                     SSDCombo.addItem(ssdList.split("#")[i].toString().trim() + ": SSD");
                     AllDriveListcombo.addItem(ssdList.split("#")[i].toString().trim() + ": SSD");
                     enableDisableIndexCmbBox.addItem(ssdList.split("#")[i].toString().trim() + ": SSD");
                     AllDriveListcomboDiskCleanup.addItem(ssdList.split("#")[i].toString().trim() + ": SSD");
                  }
               }

               String txtValue;
               if (listDrives.length > 1) {
                  btnDefragHdd.setEnabled(true);
                  txtValue = listDrives[1];
                  if (txtValue.length() > 0) {
                     for(int ix = 0; ix < txtValue.split("#").length; ++ix) {
                        HDDCombo.addItem(txtValue.split("#")[ix].toString().trim() + ": HDD");
                        AllDriveListcombo.addItem(txtValue.split("#")[ix].toString().trim() + ": HDD");
                        AllDriveListcomboDiskCleanup.addItem(txtValue.split("#")[ix].toString().trim() + ": HDD");
                     }
                  }
               } else {
                  btnDefragHdd.setEnabled(false);
               }

               txtValue = "";
               String[] sss = p.chckIndexingStatusofDrive().split("#");

               for(int ixx = 0; sss.length > ixx; ixx += 2) {
                  if (!sss[ixx].trim().equals("-")) {
                     txtValue = txtValue + sss[ixx] + sss[ixx + 1] + "#";
                  }
               }

               listofAllDriveIndexingStatusLbl.setText(txtValue);
            } else {
               JOptionPane.showMessageDialog(MainFrame.this, "Something went wrong. Please restart the application and try again.", "Error Occured", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/info1.png")));
               bb = false;
               optimizeSSDBtn.setEnabled(bb);
               btnDefragHdd.setEnabled(bb);
               readWriteTestBtn.setEnabled(bb);
               diskCleanupBtn.setEnabled(bb);
               indexSSDBtn.setEnabled(bb);
               btnChangeNow.setEnabled(bb);
            }

            IntroPanel.setVisible(false);
            GamingPanel.setVisible(false);
            BrowserPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(true);
            PackageInstallerPanel.setVisible(false);
            DiskFeaturesPanel.setBounds(205, 45, 1000, 810);
            startupAppsPanelPart2.setVisible(false);
            donationPanel.setVisible(false);
            MainFrame.this.colourCoding(DiskUtilityMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            DiskMainMenuPanel.setBackground(new Color(120, 136, 164));
            DiskUtilityMainMenuLbl.setBackground(new Color(120, 136, 164));
            DiskUtilityMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            DiskMainMenuPanel.setBackground(new Color(55, 65, 82));
            DiskUtilityMainMenuLbl.setBackground(new Color(55, 65, 82));
            DiskUtilityMainMenuLbl.setOpaque(true);
         }
      });
      DiskUtilityMainMenuLbl.setForeground(Color.WHITE);
      DiskUtilityMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      DiskUtilityMainMenuLbl.setBackground(Color.WHITE);
      final JPanel GameMainMenuPanel = new JPanel();
      GameMainMenuPanel.setLayout((LayoutManager)null);
      GameMainMenuPanel.setBackground(new Color(55, 65, 82));
      GameMainMenuPanel.setBounds(0, 368, 200, 50);
      mainPanel.add(GameMainMenuPanel);
      final JLabel GamingPermainLbl = new JLabel("Gaming Performance", createImageIcon("/com/resource/Joy16.png", "Game"), 2);
      GamingPermainLbl.setCursor(Cursor.getPredefinedCursor(12));
      GamingPermainLbl.setBounds(20, 5, 160, 40);
      GameMainMenuPanel.add(GamingPermainLbl);
      GamingPermainLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            BrowserPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            donationPanel.setVisible(false);
            GamingPanel.setVisible(true);
            PackageInstallerPanel.setVisible(false);
            GamingPanel.setBounds(205, 45, 1000, 810);
            IntroPanel.setVisible(false);
            GameProcess gp = new GameProcess();
            PowerplanValuesLbl.setText(gp.testCallable());
            String s1 = PowerplanValuesLbl.getText();
            String[] valus = s1.split("%");

            for(int i = 0; i < 2; ++i) {
               if (valus[i].length() == 2) {
                  VMPBtn.setText(valus[1].equals("ON") ? "Disable" : "Enable");
               } else if (valus[i].length() == 3) {
                  memoryIntegirtyBtn.setText(valus[1].equals("DIS") ? "Disable" : "Enable");
               } else if (valus[i].length() > 4) {
                  int j = valus[i].split("@").length;
                  powerPlanCombo.removeAllItems();
                  powerPlanCombo.addItem("-- Select One --");

                  for(int jj = 0; jj < j; ++jj) {
                     powerPlanCombo.addItem(valus[i].split("@")[jj].split("#")[1]);
                  }
               }
            }

            MainFrame.this.colourCoding(GamingPermainLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            GameMainMenuPanel.setBackground(new Color(120, 136, 164));
            GamingPermainLbl.setBackground(new Color(120, 136, 164));
            GamingPermainLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            GameMainMenuPanel.setBackground(new Color(55, 65, 82));
            GamingPermainLbl.setBackground(new Color(55, 65, 82));
            GamingPermainLbl.setOpaque(true);
         }
      });
      GamingPermainLbl.setForeground(Color.WHITE);
      GamingPermainLbl.setFont(new Font("Tahoma", 0, 14));
      GamingPermainLbl.setBackground(Color.WHITE);
      final JPanel StoragemainMenuPanel = new JPanel();
      StoragemainMenuPanel.setLayout((LayoutManager)null);
      StoragemainMenuPanel.setBackground(new Color(55, 65, 82));
      StoragemainMenuPanel.setBounds(0, 418, 200, 50);
      mainPanel.add(StoragemainMenuPanel);
      final JLabel StorageBtnMainMenuLbl = new JLabel("<html>Storage Cleaner</html>", createImageIcon("/com/resource/ssdSmall.png", "Storage"), 2);
      StorageBtnMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      StorageBtnMainMenuLbl.setBounds(20, 5, 160, 40);
      StoragemainMenuPanel.add(StorageBtnMainMenuLbl);
      StorageBtnMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            SystemDriveSize size = new SystemDriveSize();
            String[] ss = size.osDriveSize(System.getenv("WINDIR")).split("#");
            DriveDetailsLbl.setText("<html><body><table border =1><tr><td>Win Drive</td><td>" + ss[0].split(":")[0] + " Drive</td></tr>" + "<tr><td>Capacity</td><td>" + ss[1] + "</td></tr>" + "<tr><td>Free Space</td><td>" + ss[2] + "</td></tr>" + "<tr><td>Occupied %</td><td>" + ss[3] + "%</td></tr></table></body></html>");
            if (Integer.parseInt(ss[3]) > 75) {
               cautionLbl.setVisible(true);
            }

            JLabel ChartLabel = new JLabel(MainFrame.createImageIcon("/com/resource/" + ss[4] + ".png", "Graph"));
            ChartLabel.setBounds(721, 420, 235, 170);
            StorgaePanel.add(ChartLabel);
            StorgaePanel.setBounds(5, 5, 1000, 810);
            BrowserPanel.setVisible(false);
            IntroPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(true);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            donationPanel.setVisible(false);
            GamingPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            PackageInstallerPanel.setVisible(false);
            StorgaePanel.setBounds(205, 45, 1000, 810);
            MainFrame.this.colourCoding(StorageBtnMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            StoragemainMenuPanel.setBackground(new Color(120, 136, 164));
            StorageBtnMainMenuLbl.setBackground(new Color(120, 136, 164));
            StorageBtnMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            StoragemainMenuPanel.setBackground(new Color(55, 65, 82));
            StorageBtnMainMenuLbl.setBackground(new Color(55, 65, 82));
            StorageBtnMainMenuLbl.setOpaque(true);
         }
      });
      StorageBtnMainMenuLbl.setForeground(Color.WHITE);
      StorageBtnMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      StorageBtnMainMenuLbl.setBackground(Color.WHITE);
      final JPanel DwlMainMenuPanel = new JPanel();
      DwlMainMenuPanel.setLayout((LayoutManager)null);
      DwlMainMenuPanel.setBackground(new Color(55, 65, 82));
      DwlMainMenuPanel.setBounds(0, 468, 200, 50);
      mainPanel.add(DwlMainMenuPanel);
      final JLabel DownloadSoftsLbl = new JLabel("Downloads", createImageIcon("/com/resource/download16.png", "DownloadSofts"), 2);
      DownloadSoftsLbl.setCursor(Cursor.getPredefinedCursor(12));
      DownloadSoftsLbl.setBounds(20, 5, 160, 40);
      DwlMainMenuPanel.add(DownloadSoftsLbl);
      DownloadSoftsLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            IntroPanel.setVisible(false);
            donationPanel.setVisible(false);
            GamingPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            BrowserPanel.setVisible(false);
            MainFrame.this.colourCoding(DownloadSoftsLbl, mainPanel);
            PackageInstallerPanel.setVisible(true);
            PackageInstallerPanel.setBounds(205, 45, 1000, 810);
         }

         public void mouseEntered(MouseEvent e) {
            DwlMainMenuPanel.setBackground(new Color(120, 136, 164));
            DownloadSoftsLbl.setBackground(new Color(120, 136, 164));
            DownloadSoftsLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            DwlMainMenuPanel.setBackground(new Color(55, 65, 82));
            DownloadSoftsLbl.setBackground(new Color(55, 65, 82));
            DownloadSoftsLbl.setOpaque(true);
         }
      });
      DownloadSoftsLbl.setForeground(Color.WHITE);
      DownloadSoftsLbl.setFont(new Font("Tahoma", 0, 14));
      DownloadSoftsLbl.setBackground(Color.WHITE);
      final JPanel ResourcemainMenuPanel = new JPanel();
      ResourcemainMenuPanel.setLayout((LayoutManager)null);
      ResourcemainMenuPanel.setBackground(new Color(55, 65, 82));
      ResourcemainMenuPanel.setBounds(0, 518, 200, 50);
      mainPanel.add(ResourcemainMenuPanel);
      final JLabel learnmainmenuLbl = new JLabel("<html>Resource Center</html>", createImageIcon("/com/resource/Learn1.png", "Learn"), 2);
      learnmainmenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      learnmainmenuLbl.setBounds(20, 5, 160, 40);
      ResourcemainMenuPanel.add(learnmainmenuLbl);
      learnmainmenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("http://tubeandtech.com/"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.18 : " + var3.getMessage());
            }

            MainFrame.this.colourCoding(learnmainmenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            ResourcemainMenuPanel.setBackground(new Color(120, 136, 164));
            learnmainmenuLbl.setBackground(new Color(120, 136, 164));
            learnmainmenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            ResourcemainMenuPanel.setBackground(new Color(55, 65, 82));
            learnmainmenuLbl.setBackground(new Color(55, 65, 82));
            learnmainmenuLbl.setOpaque(true);
         }
      });
      learnmainmenuLbl.setForeground(Color.WHITE);
      learnmainmenuLbl.setFont(new Font("Tahoma", 0, 14));
      learnmainmenuLbl.setBackground(Color.WHITE);
      final JPanel DonateMainMenuPanel = new JPanel();
      DonateMainMenuPanel.setLayout((LayoutManager)null);
      DonateMainMenuPanel.setBackground(new Color(55, 65, 82));
      DonateMainMenuPanel.setBounds(0, 568, 200, 50);
      mainPanel.add(DonateMainMenuPanel);
      final JLabel donationMainMenuLbl = new JLabel("<html>Help Us to Grow</html>", createImageIcon("/com/resource/License.png", "Donation"), 2);
      donationMainMenuLbl.setCursor(Cursor.getPredefinedCursor(12));
      donationMainMenuLbl.setBounds(20, 5, 160, 40);
      DonateMainMenuPanel.add(donationMainMenuLbl);
      donationMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            IntroPanel.setVisible(false);
            GamingPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            startupAppsPanelPart1.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            BrowserPanel.setVisible(false);
            donationPanel.setVisible(true);
            PackageInstallerPanel.setVisible(false);
            donationPanel.setBounds(205, 45, 1000, 810);
            MainFrame.this.colourCoding(donationMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            DonateMainMenuPanel.setBackground(new Color(120, 136, 164));
            donationMainMenuLbl.setBackground(new Color(120, 136, 164));
            donationMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            DonateMainMenuPanel.setBackground(new Color(55, 65, 82));
            donationMainMenuLbl.setBackground(new Color(55, 65, 82));
            donationMainMenuLbl.setOpaque(true);
         }
      });
      donationMainMenuLbl.setForeground(Color.WHITE);
      donationMainMenuLbl.setFont(new Font("Tahoma", 0, 14));
      donationMainMenuLbl.setBackground(Color.WHITE);
      JLabel mainTopBannerLable = new JLabel("REPAIRME");
      mainTopBannerLable.setBounds(55, 10, 131, 25);
      mainPanel.add(mainTopBannerLable);
      mainTopBannerLable.setHorizontalAlignment(2);
      mainTopBannerLable.setFont(new Font("Russo One", 1, 20));
      mainTopBannerLable.setBackground(new Color(147, 158, 183));
      mainTopBannerLable.setForeground(new Color(255, 255, 255));
      JLabel lblNewLabel_12 = new JLabel("One Performancec Center");
      lblNewLabel_12.setBounds(55, 30, 145, 25);
      mainPanel.add(lblNewLabel_12);
      lblNewLabel_12.setForeground(new Color(196, 201, 215));
      lblNewLabel_12.setFont(new Font("Tahoma", 0, 11));
      JLabel lblNewLabel_15 = new JLabel(createImageIcon("/com/resource/smallLogo.png", "logo"));
      lblNewLabel_15.setBounds(10, 11, 35, 35);
      mainPanel.add(lblNewLabel_15);
      JButton btnNewButton_5 = new JButton("Copy");
      btnNewButton_5.setBounds(117, 772, 69, 25);
      mainPanel.add(btnNewButton_5);
      btnNewButton_5.setForeground(new Color(255, 255, 255));
      btnNewButton_5.setBackground(new Color(55, 65, 82));
      lblNewLabel_35.setHorizontalAlignment(2);
      lblNewLabel_35.setBounds(50, 57, 100, 30);
      mainPanel.add(lblNewLabel_35);
      final JLabel userID = new JLabel("");
      userID.setBounds(16, 737, 170, 30);
      mainPanel.add(userID);
      userID.setForeground(new Color(255, 255, 255));
      userID.setFont(new Font("Tahoma", 0, 14));

      try {
         FileReader ff = new FileReader(new File(System.getProperty("user.dir") + "\\private\\udd.dll"));
         BufferedReader br = new BufferedReader(ff);
         userID.setText("User ID : " + br.readLine());
      } catch (Exception var506) {
         this.logger.info("50.10" + var506.getMessage());
      }

      btnNewButton_5.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            if (userID.getText().isEmpty()) {
               try {
                  FileReader ff = new FileReader(new File(System.getProperty("user.dir") + "\\private\\udd.dll"));
                  BufferedReader br = new BufferedReader(ff);
                  String line = "";
                  if ((line = br.readLine()) != null) {
                     userID.setText("User ID : " + line);
                  }
               } catch (Exception var5) {
                  MainFrame.this.logger.info("50.11" + var5.getMessage());
               }
            }

            StringSelection stringSelection = new StringSelection(userID.getText().split(":")[1].trim());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, (ClipboardOwner)null);
         }
      });
      sysSetupMainMenuLbl.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            startupAppsPanelPart1.setVisible(true);
            startupAppsPanelPart1.setBounds(205, 45, 1000, 810);
            PackageInstallerPanel.setVisible(false);
            BrowserPanel.setVisible(false);
            perfPannel.setVisible(false);
            StorgaePanel.setVisible(false);
            GamingPanel.setVisible(false);
            extraFeaturesPanel.setVisible(false);
            DiskFeaturesPanel.setVisible(false);
            startupAppsPanelPart2.setVisible(false);
            PackageInstallerPanel.setVisible(false);
            IntroPanel.setVisible(false);
            MainFrame.this.colourCoding(sysSetupMainMenuLbl, mainPanel);
         }

         public void mouseEntered(MouseEvent e) {
            BestSysPerfMainMenuPanel.setBackground(new Color(120, 136, 164));
            sysSetupMainMenuLbl.setBackground(new Color(120, 136, 164));
            sysSetupMainMenuLbl.setOpaque(true);
         }

         public void mouseExited(MouseEvent e) {
            BestSysPerfMainMenuPanel.setBackground(new Color(55, 65, 82));
            sysSetupMainMenuLbl.setBackground(new Color(55, 65, 82));
            sysSetupMainMenuLbl.setOpaque(true);
         }
      });
      JPanel panel = new JPanel();
      panel.setBackground(new Color(55, 65, 82));
      panel.setBounds(215, 859, 126, 35);
      this.contentPane.add(panel);
      JLabel lblNewLabel_20 = new JLabel(createImageIcon("/com/resource/youtube.png", "YouTube"));
      lblNewLabel_20.setToolTipText("Join our YouTube channel for more fun and tech updates");
      panel.add(lblNewLabel_20);
      lblNewLabel_20.setCursor(Cursor.getPredefinedCursor(12));
      lblNewLabel_20.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://www.youtube.com/@TechnoRoast/videos"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.19 : " + var3.getMessage());
            }

         }
      });
      JLabel lblNewLabel_37 = new JLabel(createImageIcon("/com/resource/discord.png", "Discord"));
      lblNewLabel_37.setToolTipText("Join our Dicrod channel for Queries, Discussions, updates, License and Payments");
      lblNewLabel_37.setCursor(Cursor.getPredefinedCursor(12));
      lblNewLabel_37.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://discord.gg/nyF7eXvCyS"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.20 : " + var3.getMessage());
            }

         }
      });
      lblNewLabel_37.setToolTipText("Join the discord communiity for all of you questions and help");
      panel.add(lblNewLabel_37);
      JLabel lblNewLabel_37_1 = new JLabel(createImageIcon("/com/resource/Learn1.png", "Learning"));
      lblNewLabel_37_1.setToolTipText("Learn Abou this software and options from our website");
      panel.add(lblNewLabel_37_1);
      lblNewLabel_37_1.setCursor(Cursor.getPredefinedCursor(12));
      IntroPanel.setBackground(new Color(255, 255, 255));
      IntroPanel.setBounds(210, 45, 1000, 810);
      this.contentPane.add(IntroPanel);
      IntroPanel.setLayout((LayoutManager)null);
      JLabel lblExtraFeatuersList_1_1_1 = new JLabel("Welcome to RepairMe");
      lblExtraFeatuersList_1_1_1.setBounds(350, 10, 264, 33);
      lblExtraFeatuersList_1_1_1.setFont(new Font("Verdana", 1, 20));
      IntroPanel.add(lblExtraFeatuersList_1_1_1);
      JLabel lblExtraFeatuersList_1_1_1_1 = new JLabel("1. Download from source only.");
      lblExtraFeatuersList_1_1_1_1.setFont(new Font("Verdana", 1, 16));
      lblExtraFeatuersList_1_1_1_1.setBounds(44, 167, 320, 33);
      IntroPanel.add(lblExtraFeatuersList_1_1_1_1);
      JLabel lblNewLabel_42 = new JLabel("Points to Remember..");
      lblNewLabel_42.setFont(new Font("Tahoma", 0, 14));
      lblNewLabel_42.setBounds(70, 122, 143, 17);
      IntroPanel.add(lblNewLabel_42);
      JLabel lblNewLabel_42_1 = new JLabel("<html>Download 'RepairMe' only from trusted source which is our website tubeandtech.com. Never download or receive the copy from somewhere else. It may cause a problem to you later. </html>");
      lblNewLabel_42_1.setFont(new Font("Arial", 0, 14));
      lblNewLabel_42_1.setBounds(68, 198, 867, 46);
      IntroPanel.add(lblNewLabel_42_1);
      JLabel lblExtraFeatuersList_1_1_1_1_1 = new JLabel("2. Regsitry Backup");
      lblExtraFeatuersList_1_1_1_1_1.setFont(new Font("Verdana", 1, 16));
      lblExtraFeatuersList_1_1_1_1_1.setBounds(44, 270, 320, 33);
      IntroPanel.add(lblExtraFeatuersList_1_1_1_1_1);
      JLabel lblNewLabel_42_1_1 = new JLabel("<html>Our system creates regsitry backup. So that if you feel something is off you can revert it back. We create 2 backups one under folder backup/OldBackup/ here you will find the first copy of your registry when you first time starts our application. Second backup will be available under /Backup folder which we generate daily and new backup will overwrites the old one. If you are not happy with the backup you can always delete it. but remember we will not be able to revert it back.</html>");
      lblNewLabel_42_1_1.setFont(new Font("Arial", 0, 14));
      lblNewLabel_42_1_1.setBounds(68, 301, 867, 96);
      IntroPanel.add(lblNewLabel_42_1_1);
      JLabel lblExtraFeatuersList_1_1_1_1_2 = new JLabel("3. Follow our guide for best output.");
      lblExtraFeatuersList_1_1_1_1_2.setFont(new Font("Verdana", 1, 16));
      lblExtraFeatuersList_1_1_1_1_2.setBounds(44, 421, 320, 33);
      IntroPanel.add(lblExtraFeatuersList_1_1_1_1_2);
      JLabel lblNewLabel_42_1_2 = new JLabel("<html>Our application have multiple options to provide you best results and all these options are scattered in diffrent menu. So With our application we also provide a guide - (Instructions.txt) which will tell you what steps to follow for best results.</html>");
      lblNewLabel_42_1_2.setFont(new Font("Arial", 0, 14));
      lblNewLabel_42_1_2.setBounds(68, 452, 867, 60);
      IntroPanel.add(lblNewLabel_42_1_2);
      JLabel lblExtraFeatuersList_1_1_1_1_2_1 = new JLabel("4. Consider buying License or donate us.");
      lblExtraFeatuersList_1_1_1_1_2_1.setFont(new Font("Verdana", 1, 16));
      lblExtraFeatuersList_1_1_1_1_2_1.setBounds(44, 544, 383, 33);
      IntroPanel.add(lblExtraFeatuersList_1_1_1_1_2_1);
      JLabel lblNewLabel_42_1_2_1 = new JLabel("<html>I worked really hard to build this tool for all of you.Most of the part of this application is completly free and stay free for all of you. I will keep making new updates and most of them will be free only. But it really hard to survive without any motivation. where you can help us with buying our License or donate us. <br> You can do this from our application which will redirect you to our website or our discord server. Link for both is given at the bottom of the application.</html>Or visit \"Help Us to grow\" Menu.");
      lblNewLabel_42_1_2_1.setFont(new Font("Arial", 0, 14));
      lblNewLabel_42_1_2_1.setBounds(68, 575, 867, 106);
      IntroPanel.add(lblNewLabel_42_1_2_1);
      lblNewLabel_37_1.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI("https://www.tubeandtech.com/single-post/repairme-best-system-maintenance-program"));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      startupWindowBtn_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            SystemPerformanceProcess smp = new SystemPerformanceProcess();
            smp.startStopSatrtupApps();
         }
      });
      googleDNSBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BrowserUtilityClass uc = new BrowserUtilityClass();
            clnSpeedInfoLbl.setText(uc.setDNS(browsersListCombo.getSelectedItem().toString()));
            JOptionPane.showMessageDialog(MainFrame.this, "DNS server is now set to " + browsersListCombo.getSelectedItem().toString(), "DNS Server Update", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      DNSFlushBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            clnSpeedInfoLbl.setText("Processing..Please Wait..");
            BrowserProcessor bp = new BrowserProcessor();
            String result = bp.clearDNS();
            if (result.contains("Sucess")) {
               clnSpeedInfoLbl.setText("Successfully flushed the DNS Resolver Cache");
            } else {
               clnSpeedInfoLbl.setText(result);
            }

            SimpleDateFormat form = new SimpleDateFormat("dd-MMM-YY");
            dnsClearDate.setText("<html>Last DNS Cache Cleared on " + form.format(new Date()) + "</html>");
            bp.writeDateDNS(form.format(new Date()));
            JOptionPane.showMessageDialog(MainFrame.this, "DNS local entries are cleared now", "DNS Cache Cleaning", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      BrowserCacheScnBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            CleanCookieChkBox.setEnabled(true);
            BroserCacheChkBox.setEnabled(true);
            historyChkBox.setEnabled(true);
            BrowserProcessor mp = new BrowserProcessor();
            String fileSize = mp.tempDirSizeOnDisk();
            String[] ar = fileSize.split("_");
            cacheCleanlbl.setText(ar[1]);
            cookieCleanlbl.setText(ar[2]);
            historyCleanlbl.setText(ar[3]);
            clnSpeedInfoLbl.setText(ar[0] + " to be removed (Approximate Size.)");
            BrowserCacheScnBtn.setEnabled(false);
            JOptionPane.showMessageDialog(MainFrame.this, "Scan complete select the options and click on 'Clean'", "Browser Cleaning Status", 1, new ImageIcon(MainFrame.class.getResource("/com/resource/infoBlack.png")));
         }
      });
      ArrayList<JLabel> lbl = new ArrayList();
      lbl.add(lblActivityMayTake_1_1_1_1_1_1);
      lbl.add(lblActivityMayTake_1_1_1_1_1);
      lbl.add(lblActivityMayTake_1);
      lbl.add(lblActivityMayTake_1_1_1);
      lbl.add(lblActivityMayTake_1_1_1_1);
      lbl.add(lblActivityMayTake_1_1);
      lbl.add(lblActivityMayTake_1_1_1_1_1_1_1);
      lbl.add(lblActivityMayTake_1_1_1_1_1_1_1_1);
      lbl.add(lblActivityMayTake);
      JLabel linktoSiteLbl_2_3_2 = new JLabel("<html>Learn From tubeandtech</html>");
      linktoSiteLbl_2_3_2.addMouseListener(new MouseAdapter() {
         public void mouseClicked(MouseEvent e) {
            try {
               RepairMeCommon c = new RepairMeCommon();
               c.openWebpage(new URI(webURL));
            } catch (Exception var3) {
               MainFrame.this.logger.info("50.21 : " + var3.getMessage());
            }

         }
      });
      linktoSiteLbl_2_3_2.setHorizontalAlignment(0);
      linktoSiteLbl_2_3_2.setForeground(new Color(255, 0, 128));
      linktoSiteLbl_2_3_2.setFont(new Font("Tahoma", 3, 12));
      linktoSiteLbl_2_3_2.setBounds(803, 45, 169, 36);
      PackageInstallerPanel.add(linktoSiteLbl_2_3_2);
      this.changelbl(lbl);
   }
}
