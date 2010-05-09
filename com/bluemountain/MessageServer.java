
package com.bluemountain;


import java.io.*;
import java.net.*;
import java.util.*;
import com.bluemountain.datatype.Message;
import com.bluemountain.mysql.*;


public class MessageServer implements Service
{
  private static Map<String, ObjectOutputStream> sockets;

  public synchronized void transSockets(Map<String, ObjectOutputStream> m)
  {
    if(sockets==null)
    {
      
      sockets=m;
      
    }

  }

  public static void sentMessage(Message m)throws Exception 
  {
    
    boolean isSent=false;
    try
    {
      if(sockets!=null){
      if(sockets.containsKey(Long.toString(m.userTo())))
      {
        ObjectOutputStream sentMessage=
            sockets.get(Long.toString(m.userTo()));
        synchronized(sentMessage)
        {
        sentMessage.writeUTF("NewMessage");

        sentMessage.flush();

        sentMessage.writeObject(m);

        sentMessage.flush();
        
        isSent=true;

        sentMessage=null;
        }
      }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    if(!isSent)
    {
      try{
        new DBOperator().saveMessage(m);
      }catch(Exception e)
      {System.out.println("sentMessage Exception :"+e.getLocalizedMessage());
        e.printStackTrace();
      }

    }
    
    
    
  }
  public void serve(InputStream in,OutputStream out) throws IOException
  {
    ObjectOutputStream output=new ObjectOutputStream((out));
    ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(in));
    long userID=0;
    try
    {
      try{
        userID=CheckUserInfo.userCheck(input,output);
      }catch(Exception e)
      {
        
      }

      if(userID==0)
      {
        System.out.println("check unpass!");
        output.writeUTF("Failed");
        output.flush();
      }
      System.out.println("check pass");
      output.writeUTF("Welcome");
      output.flush();

      System.out.println("Recieving message!");
      Message tmp=(Message)input.readObject();

      System.out.println("From:"+tmp.userFrom()+" TO: "+tmp.userTo()
          +" \nMessage: "+tmp.getMessage()+"\nDate: "+tmp.getSendingDate());

      output.writeUTF("Done");

      MessageServer.sentMessage(tmp);
      

    
    }
    catch(Exception e)
    {
      output.writeUTF("ServerError");
      output.flush();
      e.printStackTrace();
    }

    output.close();
    input.close();

  }
}
