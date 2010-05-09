
package com.bluemountain;

import java.io.*;
import java.sql.*;
import java.util.*;

public class FileServer implements Service
{
  public void serve(InputStream in,OutputStream out) throws IOException
  {
    long userID=0;
    ObjectOutputStream output=new ObjectOutputStream((out));
    ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(in));
    System.out.println("fileserver started!");
    try
    {

      userID=CheckUserInfo.userCheck(input,output);

      if(userID==0)
      {  System.out.println("check unpass!");
        output.writeUTF("Failed");
        output.flush();
      }

      output.writeUTF("Welcome");
      output.flush();
      long lastMessage=(new java.util.Date()).getTime();
      while(true)
      {
        if(input.available()!=0)
        {
          String s=input.readUTF();
          if(s.equals("Download"))
          {
            System.out.println("Download");
            FileUtil fu=new FileUtil();
       
            
            long fileNum=input.readLong();
            System.out.println("long:" +fileNum);
            String fileName=fu.getFileName(fileNum);
            
            output.writeUTF(fileName);
            output.flush();
            
            long fileLength=fu.getFileLength(fileNum);
            
            output.writeLong(fileLength);
            output.flush();
            
            String filePath=fu.getFilePath(fileNum);
            if(input.readUTF().equals("Ready"))
            {
              System.out.println("download get ready!");
            fu.fileDownload(filePath,fileLength,output);
//            fu.setAssignmentStatus(userID,fileNum);
            System.out.println("Download finished");
            }
            
            
            
          }else if(s.equals("Upload"))
          {System.out.println("Upload");
            FileUtil fu=new FileUtil();
            
            
            /**
             * 
             */
            
            
            String fileName=input.readUTF();
            long fileLength=input.readLong();
            java.util.Date date=new java.util.Date();
            String fileSavePath="D:\\files\\"+userID+"\\"+date.getTime()+"\\"+fileName;
            output.writeUTF("Ready");
            
            System.out.println("Upload sent ready");
            output.flush();
            
            long fileNum=fu.fileUpload(fileName,fileSavePath,fileLength,input,userID);
            System.out.println("Upload finished!");
            System.out.println("filenum:"+fileNum);
            output.writeLong(fileNum);
            output.flush();
            
            
            
          }
          
        
          
          break;
        }
        
        try
        {
          Thread.sleep(300);
        }
        catch(Exception e)
        {
           
        }
        if((new java.util.Date().getTime()-lastMessage)/1000>20)
        {System.out.println("TimeOut");
          output.writeUTF("TimeOut");
          output.flush();
          break;
        }
        
      }
      
      output.close();
      input.close();
      
      
      
    }
    
    catch(Exception e)
    {
      output.writeUTF("ServerError");
      e.printStackTrace();
      return;

    }

  }

}
