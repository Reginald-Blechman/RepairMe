package com.RepairMe.bckThreads;

public class commons {
   public String convertContentSize(double size_bytes) {
      float size_kb = (float)Math.round(size_bytes / 1024.0D);
      int size_mb = Math.round(size_kb / 1024.0F);
      int size_gb = size_mb / 1024;
      String cnt_size;
      if (size_gb > 0) {
         cnt_size = size_gb + " GB";
      } else if (size_mb > 0) {
         cnt_size = size_mb + " MB";
      } else {
         cnt_size = size_kb + " KB";
      }

      return cnt_size;
   }

   public String convertContentSizeKBToGB(long size_bytes) {
      String cnt_size = "";
      float size_mb = (float)(size_bytes / 1024L);
      float size_gb = (float)Math.round(size_mb / 1024.0F);
      if (size_gb > 0.0F) {
         cnt_size = size_gb + " GB";
      } else if (size_mb > 0.0F) {
         cnt_size = size_mb + " MB";
      }

      return cnt_size;
   }
}
