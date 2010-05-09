
package com.bluemountain;

import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.*;
import com.mysql.jdbc.*;
import com.mysql.*;

public class UserLogin implements Service
{
  private static Map<String, ObjectOutputStream> sockets;
  private Socket socket;

  public synchronized void transSockets(Map<String, ObjectOutputStream> m,Socket s)
  {

    if(sockets==null)sockets=m;
    socket=s;
  }

  public void serve(InputStream in,OutputStream out) throws IOException
  {

    
    ObjectOutputStream output=(ObjectOutputStream)out;
    ObjectInputStream input=(ObjectInputStream)in;
    
    

    
    long userID=0;

    
    try
    {

      try{
        userID=CheckUserInfo.userCheck(input,output);
        
      }catch(Exception e)
      {
        e.printStackTrace();
      }

      if(userID==0)
      {
        System.out.println("One Login Failed!");
        output.writeUTF("Disconnect");
        output.flush();
        output.close();
        input.close();
        return;

      }
      else
      {

        synchronized(sockets)
        {
          if(sockets.containsKey(Long.toString(userID))){
            output.writeUTF("Disconnect");
            output.flush();
            output.close();
            input.close();
            return;
         
          }
          else{
            sockets.put(Long.toString(userID),output);
            System.out.println("new One Login Success!");
            output.writeUTF("Welcome");
            output.flush();
          }
        }

 
      

      }

      // ��ǳ�ʱ
      long lastMessage=(new java.util.Date()).getTime();

      
      int timeOutCount=0;
      while(true)
      {

        
        if(input.available()!=0)
        {// �յ�������³�ʱʱ��
          timeOutCount=0;

          String s=input.readUTF();

         // System.out.println(s);
          if(s.equals("Leaving"))
          {// ���
            break;
          }
        }
        if(socket.isClosed())
        {
          break;
        }
        try
        {
          Thread.sleep(500);
        
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
        // ����20sû���κ�ͨ��,�Ͽ�
        timeOutCount++;
        if(timeOutCount>500)
        {
          output.writeUTF("TimeOut");
          break;
        }
      }

      try{output.close();
      input.close();
      }
      catch(Exception e)
      {
        
      }
      synchronized(sockets)
      {if(sockets.containsKey(Long.toString(userID)))
        sockets.remove(Long.toString(userID));
      
      }
      CheckUserInfo.removeLoginInfo(userID);
      return;

      
    }
    catch(EOFException e)
    {
      synchronized(sockets)
      {if(sockets.containsKey(Long.toString(userID)))
        sockets.remove(Long.toString(userID));
      }
      CheckUserInfo.removeLoginInfo(userID);
      // �Է��ر���
      output.close();
      input.close();
    }
    catch(Exception f)
    {f.printStackTrace();
      synchronized(sockets)
      {if(sockets.containsKey(Long.toString(userID)))
        sockets.remove(Long.toString(userID));
      }
      CheckUserInfo.removeLoginInfo(userID);
      System.out.println("time?out?"+f.getLocalizedMessage());
      f.printStackTrace();
      
      
    }

  }

}
