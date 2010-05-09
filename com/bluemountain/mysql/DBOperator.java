
package com.bluemountain.mysql;

import java.sql.*;
import java.util.*;
import javax.swing.JProgressBar;
import com.bluemountain.datatype.*;

public class DBOperator
{

  protected Connection con;

  public long addNewUser(String uName,String uPass,boolean type)throws Exception
  {
    String sql="insert into users (UserName,UserPass,UserType) values (?,?,?)";
    String sql2="select * from users where UserName=? and UserPass=? and UserType=?";
    PreparedStatement psm=null;
    long userID=0;
    try
    {
       psm=con.prepareStatement(sql);
       psm.setString(1,uName);
       psm.setString(2,uPass);
       psm.setBoolean(3,type);
       psm.execute();
       con.commit();
       
    }catch(Exception e)
    {
      //e.printStackTrace();
      throw e;
    }
    try{
      psm=con.prepareStatement(sql2);
      psm.setString(1,uName);
      psm.setString(2,uPass);
      psm.setBoolean(3,type);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      if(rs.next())
      {
        userID=rs.getLong("UserID");
      }
      
     
      rs.close();
      psm.close();
      return userID;
      
    }catch(Exception e)
    {
     // e.printStackTrace();
      throw e;
    }
    
    
    
    
  }
  public void setAssignmentReaded(long userID,long assID) throws Exception
  {
    String sql=
        "update userassignment set Status=1 where Status < 2 and UserID=? and AssID=?";
    PreparedStatement psm=null;
    try
    {

      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      psm.setLong(2,assID);

      psm.execute();

      con.commit();

    }
    catch(Exception e)
    {
      System.out.println("setAssignmetnStatus Exception :"
          +e.getLocalizedMessage());
//      e.printStackTrace();
      throw e;
    }
  }

  public boolean getUserType(long userID) throws Exception
  {
    String sql="select * from users where UserID=?";
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);

      psm.setLong(1,userID);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();

      if(!rs.next())
      {
        throw new Exception("No such userID!");
      }

      return rs.getBoolean("UserType");
    }
    catch(Exception e)
    {
      System.out.println("getUserType Exception :"+e.getLocalizedMessage());
      throw e;
    }
  }

  public boolean isTableExists(String tableName) throws Exception
  {
    String sql="show tables like ?";
    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);

      psm.setString(1,tableName);

      ResultSet rs=psm.executeQuery();

      if(!rs.next())
      {
        rs.close();
        psm.close();
        return false;
      }
      else
      {
        rs.close();
        psm.close();
        return true;
      }
    }
    catch(Exception e)
    {
      System.out.println("isTableExists Exception:"+e.getLocalizedMessage());
      throw e;
    }

  }

  /**
   * 
   */
  public void saveMessage(Message tmp) throws Exception
  {
    long writer=tmp.userFrom();
    long reader=tmp.userTo();
    long sentTime=tmp.getSendingDate();
    String content=tmp.getMessage();
    String sql=
        "insert into message (Writer,Reader,SentTime,Content) values(?,?,?,?)";

    PreparedStatement psm=null;
    try
    {

      psm=con.prepareStatement(sql);

      psm.setLong(2,reader);
      psm.setLong(1,writer);
      psm.setLong(3,sentTime);
      psm.setString(4,content);
      psm.execute();

      con.commit();

      psm.close();
    }
    catch(Exception e)
    {
      con.rollback();
//      e.printStackTrace();
      throw e;

    }

  }

  // public void createASTable(long AssID) throws Exception
  // {
  // String sql=
  // "create table "+AssID+"AS"+" ("+"UserID BIGINT(20) UNSIGNED NOT NULL,"
  // +"UserName VARCHAR(45) NOT NULL,"
  // +"IsUploaded TINYINT(1) NOT NULL DEFAULT 0,"
  // +"UploadTime BIGINT(20) NOT NULL DEFAULT 0,"
  // +"FileNum BIGINT(20) NOT NULL DEFAULT 0,"+"PRIMARY KEY (UserID)"
  // +") "+"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  // }
  //
  // public void createCSTable(long courseID) throws Exception
  // {
  // String sql=
  // "create table "+courseID+"CS"+" ("
  // +"UserID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
  // +"UserName VARCHAR(45) NOT NULL,"+"UserType TINYINT(1) NOT NULL,"
  // +"PRIMARY KEY (UserID)"+") "+"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  // }
  //
  // public void createCATable(long courseID) throws Exception
  // {
  // String sql=
  // "create table "+courseID+"CA"+" ("
  // +"AssID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
  // +"AssName VARCHAR(45) NOT NULL,"+"FileNum BIGINT(20) NOT NULL,"
  // +"DeadLine BIGINT(20) NOT NULL,"+"PRIMARY KEY (AssID)"+") "
  // +"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  // }
  //
  // public void createUMTable(long userID) throws Exception
  // {
  // String sql=
  // "create table "+userID+"UM"+" ("+"FromID BIGINT(20) UNSIGNED NOT NULL,"
  // +"SentTime BIGINT(20) UNSIGNED NOT NULL,"+"Message VARCHAR(1000) ,"
  // +") "+"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  // }
  //
  // public void createUATable(long userID) throws Exception
  // {
  // String sql=
  // "create table "+userID+"UA ("+"AssID BIGINT(20) UNSIGNED NOT NULL ,"
  // +"AssName VARCHAR(45) NOT NULL,"+"FileNum BIGINT(20) NOT NULL,"
  // +"DeadLine BIGINT(20) NOT NULL,"
  // +"Status int(10) NOT NULL DEFAULT 0,"+"PRIMARY KEY (AssID)"+") "
  // +"ENGINE = InnoDB";
  // String sql2=
  // "create table "+userID+"UA ("+"AssID BIGINT(20) UNSIGNED NOT NULL ,"
  // +"AssName VARCHAR(45) NOT NULL,"+"PRIMARY KEY (AssID) )"
  // +"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // if(this.getUserType(userID))
  // {
  // psm=con.prepareStatement(sql2);
  // }
  // else
  // {
  // psm=con.prepareStatement(sql);
  // }
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  //
  // }
  //
  // public void createUCourseTable(long userID) throws Exception
  // {
  // String sql=
  // "create table "+userID+"UCourse"+" ("
  // +"CourseID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
  // +"CourseName VARCHAR(45) NOT NULL,"+"PRIMARY KEY (CourseID),"
  // +"CONSTRAINT FK_"+userID+"UCourse"+"_1 FOREIGN KEY FK_"+userID
  // +"UCourse"+"_1 (CourseID, CourseName) "
  // +"REFERENCES course (CourseID, CourseName) "+"ON DELETE CASCADE "
  // +"ON UPDATE CASCADE "+") "+"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  // }
  //
  // public void createUCTable(long userID) throws Exception
  // {
  //
  // String sql=
  // "create table "+userID+"UC"+" ("
  // +"UserID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
  // +"UserName VARCHAR(45) NOT NULL,"+"UserType BOOLEAN NOT NULL,"
  // +"PRIMARY KEY (UserID),"+"CONSTRAINT FK_"+userID+"UC"
  // +"_1 FOREIGN KEY FK_"+userID+"UC"
  // +"_1 (UserID, UserName, UserType) "
  // +"REFERENCES users (UserID, UserName, UserType) "
  // +"ON DELETE CASCADE "+"ON UPDATE CASCADE "+") "+"ENGINE = InnoDB";
  // PreparedStatement psm=null;
  // try
  // {
  // psm=con.prepareStatement(sql);
  //
  // psm.execute();
  //
  // con.commit();
  // psm.close();
  // }
  // catch(Exception e)
  // {
  // con.rollback();
  // throw e;
  //
  // }
  //
  // }

  public boolean isFileExists(long fileNum) throws Exception
  {
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement("select * from files where FileNum=?");
      psm.setLong(1,fileNum);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(!rs.next())
      {
        rs.close();
        psm.close();
        return false;
      }

      rs.close();
      psm.close();
      return true;

    }
    catch(Exception e)
    {
//      e.printStackTrace();
      throw e;
    }
  }

  public String getFileName(long fileNum) throws Exception
  {
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement("select * from files where FileNum=?");
      psm.setLong(1,fileNum);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(!rs.next())
      {
        rs.close();
        psm.close();
        return null;
      }
      String tmp=rs.getString("FileName");

      rs.close();
      psm.close();
      return tmp;

    }
    catch(Exception e)
    {
//      e.printStackTrace();
      throw e;
    }
  }

  public long getFileLength(long fileNum) throws Exception
  {
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement("select * from files where FileNum=?");
      psm.setLong(1,fileNum);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(!rs.next())
      {
        rs.close();
        psm.close();
        return 0;
      }
      long tmp=rs.getLong("FileLength");

      rs.close();
      psm.close();
      return tmp;

    }
    catch(Exception e)
    {
//      e.printStackTrace();
      throw e;
    }

  }

  public String getFilePath(long fileNum) throws Exception
  {
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement("select * from files where FileNum=?");
      psm.setLong(1,fileNum);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(!rs.next())
      {
        rs.close();
        psm.close();
        return null;
      }
      String tmp=rs.getString("FileSavePath");

      rs.close();
      psm.close();
      return tmp;

    }
    catch(Exception e)
    {
//      e.printStackTrace();
      throw e;
    }
  }

  public long uploadFile(long userID,String fileName,String fileSavePath,
      long fileLength) throws Exception
  {

    long tmp=0;
    PreparedStatement psm=null;
    try
    {
      psm=
          con
              .prepareStatement("insert into files(FileName,FileSavePath,FileLength,Uploader,UploadTime,AssignmentID,UploadType) values(?,?,?,?,?,0,0)");
      psm.setString(1,fileName);
      psm.setString(2,fileSavePath);
      psm.setLong(3,fileLength);
      psm.setLong(4,userID);
      long uploadTime=new java.util.Date().getTime();
      psm.setLong(5,uploadTime);

      psm.executeUpdate();

      con.commit();

      psm.close();

      psm=
          con
              .prepareStatement("select * from files where UploadTime=? and FileName=?");

      psm.setLong(1,uploadTime);
      psm.setString(2,fileName);

      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(rs.next())
        tmp=rs.getLong("FileNum");

      rs.close();

      psm.close();
      return tmp;
    }
    catch(Exception e)
    {
//      e.printStackTrace();

      throw new Exception("Insert Failed!");

    }

  }

  public boolean isClosed()
  {
    try
    {
      return con.isClosed();
    }
    catch(Exception e)
    {
      return true;
    }
  }

  protected void rollback() throws Exception
  {
    try
    {
      con.rollback();
    }
    catch(Exception e)
    {
      throw new Exception(e.getLocalizedMessage());
    }
  }

  public void disConnect() throws Exception
  {
    try
    {
      con.close();
    }
    catch(Exception e)
    {
      throw new Exception(e.getLocalizedMessage());
    }
  }

  protected void commit() throws Exception
  {
    try
    {
      con.commit();
    }
    catch(Exception e)
    {
      throw new Exception(e.getLocalizedMessage());
    }
  }
  public DBOperator() throws Exception
  {
    try
    {
      con=DBConnector.getMySQLConnection("0.0.0.0","3306","ams","java","java");
      con.setAutoCommit(false);
    }
    catch(Exception e)
    {

    }
  }

  public DBOperator(Connection c) throws Exception
  {

    try
    {
      con=c;

      con.setAutoCommit(false);
    }
    catch(Exception e)
    {
      throw new Exception("!!!!!!"+e.getLocalizedMessage());
    }

  }

  public DBOperator(String serverHost,String serverPort,String dbName,
      String userName,String password) throws Exception
  {

    try
    {
      con=
          DBConnector.getMySQLConnection(serverHost,serverPort,dbName,userName,
              password);

      con.setAutoCommit(false);
    }
    catch(Exception e)
    {
      throw new Exception("connect failed"+e.getLocalizedMessage());
    }
  }

  protected void finalize()
  {
    try
    {
      con.close();
    }
    catch(Exception e)
    {

    }
  }

}
