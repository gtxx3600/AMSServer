
package com.bluemountain;

import java.util.*;
import java.io.*;
import com.bluemountain.datatype.*;

public class DiscussServer implements Service
{

  private static Map<String, Map<String, ObjectOutputStream>> allDiscuss;
static {
  allDiscuss=new TreeMap<String, Map<String, ObjectOutputStream>>();
}

  public static synchronized Map<String, ObjectOutputStream> getDiscuss(
      String discussID)
  {
    if(!allDiscuss.containsKey(discussID))
    {

      allDiscuss.put(discussID,new TreeMap<String, ObjectOutputStream>());

    }
    return allDiscuss.get(discussID);
  }

  public static synchronized void deleteConnect(String disID,String userID)
  {
    
    getDiscuss(disID).remove(userID);
  }

  public static void sentMessages(Message m)
  {
    long disID=m.userTo();
    Map<String, ObjectOutputStream> discuss;
    Map<String, ObjectOutputStream> discusstmp=
        DiscussServer.getDiscuss(Long.toString(disID));
    synchronized(discusstmp)
    {
      discuss=new TreeMap<String, ObjectOutputStream>(discusstmp);
    }
    Set<String> ks=discuss.keySet();
    Iterator<String> it=ks.iterator();
    while(it.hasNext())
    {
      String userID=it.next();
      ObjectOutputStream tmp=discuss.get(userID);
      synchronized(tmp)
      {
        try
        {
          System.out.println("new message");
          tmp.writeUTF("NewMessage");
          tmp.flush();
          tmp.writeObject(m);
          tmp.flush();

        }
        catch(Exception e)
        {
          e.printStackTrace();
          try{
            tmp.close();
          }catch(Exception f)
          {
            e.printStackTrace();
          }
          deleteConnect(Long.toString(disID),userID);

        }

      }

    }

  }

  public void serve(InputStream input,OutputStream output) throws IOException
  {
    ObjectOutputStream out=new ObjectOutputStream((output));
    ObjectInputStream in=new ObjectInputStream(new BufferedInputStream(input));

    long userID=0;
    try
    {
      try
      {
        userID=CheckUserInfo.userCheck(in,out);
      }
      catch(Exception e)
      {
        
      }

      if(userID==0)
      {
        System.out.println("check unpass!");
        out.writeUTF("Failed");
        out.flush();
      }

      out.writeUTF("Welcome");
      out.flush();

      String job=in.readUTF();
      if(job.equals("JoinDiscuss"))
      {
        long discussID=in.readLong();

        if(!getDiscuss(Long.toString(discussID)).containsKey(
            Long.toString(userID)))
        {
          System.out.println("Joined");
          out.writeUTF("Done");
          out.flush();
          getDiscuss(Long.toString(discussID)).put(Long.toString(userID),out);
          in.readUTF();
          deleteConnect(Long.toString(discussID),Long.toString(userID));
          System.out.println("Left");
          out.close();
          in.close();

        }
        else
        {
          System.out.println("Joined");
          out.writeUTF("Done");
          out.flush();
          deleteConnect(Long.toString(discussID),Long.toString(userID));
          getDiscuss(Long.toString(discussID)).put(Long.toString(userID),out);
          in.readUTF();
          System.out.println("Left");
          deleteConnect(Long.toString(discussID),Long.toString(userID));
          out.close();
          in.close();
        }

      }
      if(job.equals("NewMessage"))
      {
        Message tmp=(Message)in.readObject();
        System.out.println("new Discuss Message");
        DiscussServer.sentMessages(tmp);       
        out.writeUTF("Done");
        out.flush();
        out.close();
        in.close();

      }
      if(job.equals("Leaving"))
      {
        
      }
    }
    catch(Exception e)
    {
      out.writeUTF("ServerError");
      out.flush();
      out.close();
      in.close();
      e.printStackTrace();

    }

  }

}
