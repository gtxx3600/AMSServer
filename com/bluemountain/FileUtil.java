
package com.bluemountain;

import java.io.*;
import java.sql.*;
import java.util.*;
import com.bluemountain.mysql.*;;

public class FileUtil
{
  private DBOperator dbo;

  public String getFileName(long fileNum)throws Exception 
  {
    return dbo.getFileName(fileNum);
  }
  public long getFileLength(long fileNum)throws Exception 
  {
    return dbo.getFileLength(fileNum);
  }
  public String getFilePath(long fileNum)throws Exception
  {
    return dbo.getFilePath(fileNum);
  }
// public void setAssignmentStatus(long userID,long fileNum)
// {
//   dbo.setAssignmentStatus(userID,fileNum);
// }
  public FileUtil() throws Exception
  {
    try
    {
      dbo=new DBOperator(null,null,"ams","java","java");
      System.out.println("sql connected!");
    }
    catch(Exception e)
    {e.printStackTrace();
      throw new Exception(e.getLocalizedMessage());
    }
  }

  public long fileUpload(String fileName,String fileSavePath,long fileLength,
      InputStream in,long userID) throws Exception
  {

    long tmp=0;
    PreparedStatement psm=null;
    File fi=new File(fileSavePath);
    {
      if(fi.exists())
      {
         
      }
      else
      {

        if(!fi.getParentFile().exists())
        {
           fi.getParentFile().mkdirs();
        }
      }
    }
    
    try
    {
      // 本地保存路径，文件名会自动从服务器端继承而来。

      int bufferSize=20480;
      byte[] buf=new byte[bufferSize];
      long passedlen=0;

      DataOutputStream fileOut=
          new DataOutputStream(new BufferedOutputStream(new FileOutputStream(
              fileSavePath)));
      long time1=new java.util.Date().getTime();
      long percent=0;
      while(true)
      {
        int read=0;
        if(in!=null)
        {
          read=in.read(buf);
        }
        passedlen+=read;
        if(read==-1)
        {
          break;
        }
        
        // 下面进度条本为图形界面的prograssBar做的，这里如果是打文件，可能会重复打印出一些相同的百分比
        
        fileOut.write(buf,0,read);
        if((passedlen*100/fileLength)>percent)
        {   percent=passedlen*100/fileLength;
           System.out.println("文件接收了"+percent+"%\n");
           if(percent==100)
           {
             break;
           }
        }
      }
      
      long time2=new java.util.Date().getTime();
      System.out.println("total -time:"+(time2-time1)+" ms");
      System.out.println("speed:"+(fileLength/(time2-time1))+"K");

      fileOut.close();
    }
    catch(Exception e)
    {

      throw new Exception("error in reading command"+e.getLocalizedMessage());
    }

    try
    {
      tmp=dbo.uploadFile(userID,fileName,fileSavePath,fileLength);
      return tmp;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new Exception(e.getLocalizedMessage());

    }

  }

  public void fileDownload(String filePath,long fileLength,ObjectOutputStream out)
      throws Exception
  {

    ObjectOutputStream output=out;
      

    File fi=new File(filePath);

    if(!fi.exists()||!fi.isFile())
    {
      throw new Exception("NO such file!");
    }

    System.out.println(filePath);
    DataInputStream input=
        new DataInputStream(new BufferedInputStream(new FileInputStream(
            filePath)));

    int bufferSize=8192;
    byte[] buf=new byte[bufferSize];

    while(true)
    {
      int read=0;
      if(input!=null)
      {
        read=input.read(buf);
      }

      if(read==-1)
      {
        break;
      }
      output.write(buf,0,read);
    }
    output.flush();

    input.close();

  }
  

}
