package com.bluemountain;


import java.sql.*;
import java.io.*;
import java.util.*;
import com.bluemountain.mysql.*;



public class CheckUserInfo
{
  
  private static Map<Long,String> loginInfo=new TreeMap<Long,String>();
  public static synchronized void addLoginInfo(long userID,String userPass)
  {
    loginInfo.put(new Long(userID),new String(userPass));
  }
  public static synchronized void removeLoginInfo(long userID)
  {
    Long l=new Long(userID);
    if(loginInfo.containsKey(l))
    loginInfo.remove(l);
  }
  public static long userCheck(ObjectInputStream input,ObjectOutputStream output)throws Exception
  {

    
    boolean isPassed=false;
    
    boolean userType=false;
    
    String userPass=null;
    
    long userID=0;
    try
    {
      output.writeUTF("UserID");
      output.flush();
      userID=input.readLong();
  
      output.writeUTF("UserPass");
      output.flush();
      userPass=input.readUTF();

      output.writeUTF("UserType");
      output.flush();
      userType=input.readBoolean();
   

      if(loginInfo.containsKey(new Long(userID)))
      {
        if(loginInfo.get(new Long(userID)).equals(userPass))
        {
          return userID;
        }
        else{
          return 0;
        }
      }
      isPassed=CheckUserInfo.checkUserInfo(userID,userPass,userType);
     if(isPassed)
     {
       CheckUserInfo.addLoginInfo(userID,userPass);
       return userID;
     }
     else
     {
       return 0;
     }
      
    }catch(Exception e)
    {

      throw new Exception ("CheckUserInfo Exception:"+e.getLocalizedMessage());
    }
  }
  public static boolean checkUserInfo(long userID,String password,boolean userType)throws Exception 
  {
    
    Connection con=null;
    String sql="select * from users where UserID=? and UserType=?";
    PreparedStatement psm=null;
    
    try
    {
      con=DBConnector.getMySQLConnection(null,null,"ams","java","java");
      
      
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new Exception("服务器连接数据库失败");
     
    }
    try{
      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      psm.setBoolean(2,userType);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      if(!rs.next())
      {
        throw new Exception("没有符合条件的用户!");
      }
      if( rs.getString("UserPass").equals(password))
      {
        rs.close();
        psm.close();
        con.close();
        return true;
      }
      else
      {
        rs.close();
        psm.close();
        con.close();
        return false;
      }
    }
    catch(Exception e)
    {
      
      throw e;
    }
    
    
    
  }
  

}
