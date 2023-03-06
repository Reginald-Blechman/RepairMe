package com.RepairMe.UtillityFiles;

import com.RepairMe.Process.MyLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.log4j.Logger;

public class FirstTimeUtillity {
   Logger logger = Logger.getLogger(MyLogger.class);

   public void installApps(ArrayList<String> al, String command) {
      StringBuffer s = new StringBuffer();

      for(int i = 0; i < al.size(); ++i) {
         s.append("winget " + command + " --id=" + this.packagename((String)al.get(i)) + " -e --silent ;");
      }

      try {
         Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start powershell.exe " + s});
         p.waitFor();
      } catch (InterruptedException | IOException var5) {
         var5.printStackTrace();
      }

   }

   private String packagename(String chkBxName) {
      String retVal = "";
      switch(chkBxName.hashCode()) {
      case -1989358688:
         if (chkBxName.equals("GitHub_Desktop")) {
            retVal = "GitHub.GitHubDesktop";
            return retVal;
         }
         break;
      case -1703902524:
         if (chkBxName.equals("WinSCP")) {
            retVal = "WinSCP.WinSCP";
            return retVal;
         }
         break;
      case -1703902521:
         if (chkBxName.equals("WinRar")) {
            retVal = "RARLab.WinRAR";
            return retVal;
         }
         break;
      case -1703894587:
         if (chkBxName.equals("WinZip")) {
            retVal = "Corel.WinZip";
            return retVal;
         }
         break;
      case -1703887736:
         if (chkBxName.equals("Winamp")) {
            retVal = "Winamp.Winamp";
            return retVal;
         }
         break;
      case -1436295706:
         if (chkBxName.equals("Android_Studio")) {
            retVal = "Google.AndroidStudio";
            return retVal;
         }
         break;
      case -1229464733:
         if (chkBxName.equals("CutePDF_Writer")) {
            retVal = "AcroSoftware.CutePDFWriter";
            return retVal;
         }
         break;
      case -958933748:
         if (chkBxName.equals("Discord")) {
            retVal = "Discord";
            return retVal;
         }
         break;
      case -909801021:
         if (chkBxName.equals("Malwarebytes_Windows_Firewall_Control")) {
            retVal = "BiniSoft.WindowsFirewallControl";
            return retVal;
         }
         break;
      case -545816390:
         if (chkBxName.equals("Irfan_View")) {
            retVal = "IrfanSkiljan.IrfanView";
            return retVal;
         }
         break;
      case -494898079:
         if (chkBxName.equals("Notepad++")) {
            retVal = "Notepad++.Notepad++";
            return retVal;
         }
         break;
      case -439692929:
         if (chkBxName.equals("Open_VPN")) {
            retVal = "OpenVPNTechnologies.OpenVPN";
            return retVal;
         }
         break;
      case -435930883:
         if (chkBxName.equals("Opera_GX")) {
            retVal = "Opera.OperaGX";
            return retVal;
         }
         break;
      case -334070118:
         if (chkBxName.equals("Spotify")) {
            retVal = "Spotify.Spotify";
            return retVal;
         }
         break;
      case -263543781:
         if (chkBxName.equals("GOM_Player")) {
            retVal = "GOMLab.GOMPlayer";
            return retVal;
         }
         break;
      case -38900538:
         if (chkBxName.equals("Amazon_Games")) {
            retVal = "Amazon.Games";
            return retVal;
         }
         break;
      case 2075634:
         if (chkBxName.equals("CPUz")) {
            retVal = "CPUID.CPU-Z";
            return retVal;
         }
         break;
      case 2187781:
         if (chkBxName.equals("GIMP")) {
            retVal = "GIMP.GIMP";
            return retVal;
         }
         break;
      case 2194798:
         if (chkBxName.equals("GPUz")) {
            retVal = "TechPowerUp.GPU-Z";
            return retVal;
         }
         break;
      case 2687571:
         if (chkBxName.equals("XBOX")) {
            retVal = "XBOX.XBOX";
            return retVal;
         }
         break;
      case 2791411:
         if (chkBxName.equals("Zoom")) {
            retVal = "Zoom.Zoom";
            return retVal;
         }
         break;
      case 28308558:
         if (chkBxName.equals(".Net_FrameWork")) {
            retVal = "Microsoft.DotNet.Framework.DeveloperPack_4";
            return retVal;
         }
         break;
      case 53713657:
         if (chkBxName.equals("7_Zip")) {
            retVal = "7zip.7zip";
            return retVal;
         }
         break;
      case 64445536:
         if (chkBxName.equals("Brave")) {
            retVal = "Brave.Brave";
            return retVal;
         }
         break;
      case 76395443:
         if (chkBxName.equals("Opera")) {
            retVal = "Opera.Opera";
            return retVal;
         }
         break;
      case 77450644:
         if (chkBxName.equals("PuTTY")) {
            retVal = "PuTTY.PuTTY";
            return retVal;
         }
         break;
      case 79959734:
         if (chkBxName.equals("Skype")) {
            retVal = "Microsoft.Skype";
            return retVal;
         }
         break;
      case 80208176:
         if (chkBxName.equals("Steam")) {
            retVal = "Valve.Steam";
            return retVal;
         }
         break;
      case 81880917:
         if (chkBxName.equals("Unity")) {
            retVal = "Unity.UnityHub";
            return retVal;
         }
         break;
      case 83451655:
         if (chkBxName.equals("WebEx")) {
            retVal = "Cisco.WebexTeams";
            return retVal;
         }
         break;
      case 206254525:
         if (chkBxName.equals("MS_Teams")) {
            retVal = "Microsoft.Teams";
            return retVal;
         }
         break;
      case 251920803:
         if (chkBxName.equals("Libre_Office")) {
            retVal = "TheDocumentFoundation.LibreOffice";
            return retVal;
         }
         break;
      case 341363006:
         if (chkBxName.equals("EA_Games")) {
            retVal = "ElectronicArts.EADesktop";
            return retVal;
         }
         break;
      case 346293808:
         if (chkBxName.equals("Evernote")) {
            retVal = "evernote.evernote";
            return retVal;
         }
         break;
      case 420203769:
         if (chkBxName.equals("LogMeIn_Hamachi")) {
            retVal = "LogMeIn.Hamachi";
            return retVal;
         }
         break;
      case 500980924:
         if (chkBxName.equals("Lively_Wallpaper")) {
            retVal = "rocksdanister.LivelyWallpaper";
            return retVal;
         }
         break;
      case 715909302:
         if (chkBxName.equals("FXSounds")) {
            retVal = "FxSoundLLC.FxSound";
            return retVal;
         }
         break;
      case 815200953:
         if (chkBxName.equals("Firefox")) {
            retVal = "Mozilla.Firefox";
            return retVal;
         }
         break;
      case 870933984:
         if (chkBxName.equals("Nvidia_GeForce_Experience")) {
            retVal = "Nvidia.GeForceExperience";
            return retVal;
         }
         break;
      case 1032820924:
         if (chkBxName.equals("Audacity")) {
            retVal = "Audacity.Audacity";
            return retVal;
         }
         break;
      case 1273454746:
         if (chkBxName.equals("Postman")) {
            retVal = "Postman.Postman";
            return retVal;
         }
         break;
      case 1294034764:
         if (chkBxName.equals("Droid_Cam")) {
            retVal = "dev47apps.DroidCam";
            return retVal;
         }
         break;
      case 1365411338:
         if (chkBxName.equals("HW_Monitor")) {
            retVal = "CPUID.HWMonitor";
            return retVal;
         }
         break;
      case 1387357654:
         if (chkBxName.equals("Malwarebytes")) {
            retVal = "Malwarebytes.Malwarebytes";
            return retVal;
         }
         break;
      case 1439195010:
         if (chkBxName.equals("Ubisoft_Connects")) {
            retVal = "Ubisoft.Connect";
            return retVal;
         }
         break;
      case 1463330568:
         if (chkBxName.equals("Intellij_IDEA_Community_Edition")) {
            retVal = "JetBrains.IntelliJIDEA.Community.EAP";
            return retVal;
         }
         break;
      case 1587904104:
         if (chkBxName.equals("Nord_VPN")) {
            retVal = "NordVPN.NordVPN";
            return retVal;
         }
         break;
      case 1634301086:
         if (chkBxName.equals("Blender")) {
            retVal = "BlenderFoundation.Blender";
            return retVal;
         }
         break;
      case 1817380103:
         if (chkBxName.equals("Epic_Games")) {
            retVal = "EpicGames.EpicGamesLauncher";
            return retVal;
         }
         break;
      case 1843100517:
         if (chkBxName.equals("Express_VPN")) {
            retVal = "ExpressVPN.ExpressVPN";
            return retVal;
         }
         break;
      case 1962882867:
         if (chkBxName.equals("VLC_Player")) {
            retVal = "VideoLAN.VLC.Nightly";
            return retVal;
         }
         break;
      case 1999394194:
         if (chkBxName.equals("WhatsApp")) {
            retVal = "WhatsApp.WhatsApp";
            return retVal;
         }
         break;
      case 2017705626:
         if (chkBxName.equals("Chrome")) {
            retVal = "Google.Chrome";
            return retVal;
         }
         break;
      case 2143928925:
         if (chkBxName.equals("HWInfo")) {
            retVal = "REALiX.HWiNFO";
            return retVal;
         }
         break;
      case 2147198517:
         if (chkBxName.equals("OBS_Studio")) {
            retVal = "OBSProject.OBSStudio";
            return retVal;
         }
      }

      retVal = "";
      return retVal;
   }

   public void appsList() {
      File ff = new File(System.getProperty("java.io.tmpdir") + "AplicationsList.txt");

      try {
         ff.createNewFile();
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      try {
         String[] s = new String[]{"powershell.exe", "winget upgrade"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
         if (p.exitValue() == 0) {
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line1 = "";
            FileWriter f = new FileWriter(ff);

            for(int j = 0; (line1 = in.readLine()) != null; ++j) {
               if (j > 1) {
                  f.write(line1);
                  f.write("\n");
               }
            }

            f.close();
            in.close();
         }

         Process p1 = Runtime.getRuntime().exec(new String[]{"C:\\Windows\\System32\\notepad.exe", System.getProperty("java.io.tmpdir") + "AplicationsList.txt"});
         p1.waitFor();
         ff.deleteOnExit();
      } catch (Exception var9) {
         this.logger.info("5.1 : " + var9.getMessage());
      }

   }

   public void upgradeWinApps() {
      try {
         String[] s = new String[]{"cmd", "/c", "start powershell.exe winget upgrade --all"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
         this.logger.info(" Updating Applications with latest version..");
      } catch (Exception var3) {
         this.logger.info("5.2 : " + var3.getMessage());
      }

   }

   public void systemRestore() {
      Date d = new Date();
      SimpleDateFormat format = new SimpleDateFormat("dd_MM_YY");
      String fileName = "RepairMe_" + format.format(d);

      try {
         String[] command = new String[]{"cmd", "/c", "start", "cmd.exe", "/K", "wmic /Namespace:\\\\root\\default Path SystemRestore Call CreateRestorePoint \"" + fileName + "\", 100, 12"};
         Process process = Runtime.getRuntime().exec(command);
         if (!process.isAlive()) {
            this.logger.info(" Restoration Point created with Name : " + fileName);
         }
      } catch (Exception var6) {
         this.logger.info("5.3 : " + var6.getMessage());
      }

   }

   public void uninstallApps() {
      try {
         String[] s = new String[]{"powershell.exe", "Start ms-settings:appsfeatures"};
         Process p = Runtime.getRuntime().exec(s);
         p.waitFor();
      } catch (Exception var3) {
         this.logger.info("5.4 : " + var3.getMessage());
      }

   }

   public void updateWindwos() {
      String[] executePolicycommand = new String[]{"powershell.exe", "Set-ExecutionPolicy RemoteSigned -Force"};
      String[] getWinUpdatecommand = new String[]{"cmd", "/c", "start powershell.exe Get-WindowsUpdate -AcceptAll -Install"};

      try {
         Process process = Runtime.getRuntime().exec(executePolicycommand);
         process.waitFor();
         if (!process.isAlive()) {
            this.logger.info(" Execution policy Enabled to download windows updates");
         }

         Process process1 = Runtime.getRuntime().exec(getWinUpdatecommand);
         process1.waitFor();
         if (!process1.isAlive()) {
            this.logger.info(" Windows updates are downloaded and installed");
         }
      } catch (Exception var5) {
         this.logger.info("5.5 : " + var5.getMessage());
      }

   }

   public String updateVisualFX(int index) {
      String visualFX = "";
      String[] executePolicycommand = new String[]{"powershell.exe", "Set-ItemProperty -Path 'HKCU:\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\VisualEffects' -Name 'VisualFXSetting' -Value " + index};

      try {
         Process process = Runtime.getRuntime().exec(executePolicycommand);
         process.waitFor();
         if (!process.isAlive()) {
            this.logger.info(" Setting up visual effects : Currently set to :" + this.visualFXchoose(index));
         }

         if (index == 0) {
            visualFX = "Let Windows Choose *,Best Appearance, Best Performance";
         } else if (index == 1) {
            visualFX = "Let Windows Choose,Best Appearance *, Best Performance";
         } else if (index == 2) {
            visualFX = "Let Windows Choose,Best Appearance, Best Performance *";
         }
      } catch (Exception var5) {
         this.logger.info("5.6 : " + var5.getMessage());
      }

      return visualFX;
   }

   private String visualFXchoose(int index) {
      String retVal = "";
      switch(index) {
      case 0:
         retVal = "Let Windows Choose";
         break;
      case 1:
         retVal = "Best Appearance";
         break;
      case 2:
         retVal = "Best Performance";
         break;
      default:
         retVal = "UNKNOWN";
      }

      return retVal;
   }
}
