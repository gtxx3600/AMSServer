
package com.bluemountain.mysql;

import java.util.*;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.bluemountain.datatype.*;

public class DBTableOperator extends DBOperator
{


  public ArrayList<Discussion> getDiscussionList()
  {
    String sql="select * from discuss";
    ArrayList<Discussion> dl=new ArrayList<Discussion>();
    PreparedStatement psm=null;
    try{
      psm=con.prepareStatement(sql);
      ResultSet rs=psm.executeQuery();
      
      rs.beforeFirst();
      while(rs.next())
      {
        dl.add(new Discussion(rs.getLong("DiscussID"),rs.getString("DiscussName"),rs.getLong("CourseID"),rs.getString("CourseName")));
      }
      
    }catch(Exception e)
    {
      System.out.println("getDiscussionList Exception "+e.getLocalizedMessage());
    }
    return dl;
    
  }
  public void setScore(AssignmentStudent as)throws Exception
  {
    String sql="update userassignment set status=3,score=?,scoretype=? where UserID=? and UploadTime=?";
    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);
      psm.setDouble(1,as.getScore());
      psm.setInt(2,as.getScoreType());
      psm.setLong(3,as.getUserID());
      psm.setLong(4,as.getUploadDate());
      psm.execute();
      con.commit();
      
      
    }
    catch(Exception e)
    {
      //e.printStackTrace();
      throw e;
    }
  }
  public ArrayList<AssignmentStudent> getAssignmentStudentList(long assID)
  {
    ArrayList<AssignmentStudent> as=new ArrayList<AssignmentStudent>();
    String sql="select * from userassignment where AssID=?";
    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,assID);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      while(rs.next())
      {

        as.add(new AssignmentStudent(rs.getLong("UserID"),"Student",rs
            .getInt("Status")>=2 ? true : false,rs.getLong("UploadTime"),rs
            .getInt("ScoreType"),rs.getInt("Score"),this.getAppendixList(assID,
            rs.getLong("UserID"))));

      }

      rs.close();
      psm.close();

    }
    catch(Exception e)
    {System.out.println("getAssignmentStudentList Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
    }
    return as;
  }

  // public ArrayList<User> getCourseStudentList(long courseID)
  // {
  // ArrayList<User> cs=new ArrayList<User>();
  // String sql="select * from contact ";
  // PreparedStatement psm=null;
  // try
  // {
  //
  // psm=con.prepareStatement(sql);
  // psm.setLong(1,courseID);
  // ResultSet rs=psm.executeQuery();
  //
  // while(rs.next())
  // {
  // long userID=rs.getLong("UserID");
  // String userName=rs.getString("UserName");
  //
  // cs.add(new SimpleUser(userName,userID,false));
  //
  // }
  //
  // rs.close();
  // psm.close();
  //
  // }
  // catch(Exception e)
  // {
  // e.printStackTrace();
  // }
  //
  // return cs;
  //
  // }

  public void addContact(long userID,User su) throws Exception
  {
    long cID=su.getUserID();
    String cName=su.getUserName();
    boolean cType=su.isType();

    String sql=
        "insert into contact (UserID,ContactID,ContactName,ContactType) values(?,?,?,?)";
    PreparedStatement psm=null;
    try
    {

      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      psm.setLong(2,cID);
      psm.setString(3,cName);
      psm.setBoolean(4,cType);
      psm.execute();

      con.commit();
      psm.close();

    }
    catch(Exception e)
    {
      //e.printStackTrace();
      throw e;

    }

  }

  public void studentUploadAssignment(long userID,Assignment ass)
      throws Exception
  {
    ArrayList<Appendix> appendix=ass.getAppendixes();
    long assID=ass.getAssignmentID();

    String sql=
        "update userassignment set status=2,UploadTime=? where AssID=? and UserID=?";
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,new java.util.Date().getTime());
      psm.setLong(2,assID);
      psm.setLong(3,userID);
      psm.execute();
      con.commit();

      Iterator<Appendix> it=appendix.iterator();

      while(it.hasNext())

      {
        this.setFileStatus(it.next().getFileNum(),assID,false);

      }
    }
    catch(Exception e)
    {
      System.out.println(e.getLocalizedMessage());
     // e.printStackTrace();
      throw e;
    }

  }

  // public ArrayList<SimpleUser> getCourse
  public ArrayList<User> getAllUser()
  {
    ArrayList<User> allUser=new ArrayList<User>();
    String sql="select * from users";
    PreparedStatement psm=null;
    try
    {

      psm=con.prepareStatement(sql);
      ResultSet rs=psm.executeQuery();

      while(rs.next())
      {
        long userID=rs.getLong("UserID");
        String userName=rs.getString("UserName");
        boolean userType=rs.getBoolean("UserType");

        allUser.add(new User(userID,userName,userType));

      }

      rs.close();
      psm.close();

    }
    catch(Exception e)
    {System.out.println("getAllUser Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
    }

    return allUser;

  }

  // public ArrayList<SimpleAssignment> getCourseAssignmentList(long courseID)
  // {
  // ArrayList<SimpleAssignment> courseAssignmentList=new
  // ArrayList<SimpleAssignment>();
  // String sql="select * from ?ca";
  //    
  // PreparedStatement psm=null;
  // try{
  // psm=con.prepareStatement(sql);
  // psm.setLong(1,courseID);
  // ResultSet rs=psm.executeQuery();
  //      
  // while(rs.next())
  // {
  // long assID=rs.getLong("AssID");
  // String assName=rs.getString("AssName");
  // long fileNum=rs.getLong("FileNum");
  // long deadLine=rs.getLong("DeadLine");
  // int status=rs.getInt("Status");
  //        
  // courseAssignmentList.add(new
  // SimpleStudentAssignment(assID,assName,status,deadLine,fileNum));
  //
  // }
  //      
  // rs.close();
  // psm.close();
  //      
  // }catch(Exception e)
  // {
  // e.printStackTrace();
  //
  //
  // }
  //
  // return courseAssignmentList;
  // }

  // public void addStudentIntoAssignment(long userID,String userName,long
  // assID)
  // {
  // String sql="insert into ?AS (UserID,UserName) values (?,?)";
  //    
  // PreparedStatement psm=null;
  //    
  // try{
  // psm=con.prepareStatement(sql);
  // psm.setLong(1,assID);
  // psm.setLong(2,userID);
  // psm.setString(3,userName);
  //      
  // psm.executeUpdate();
  //      
  // con.commit();
  //      
  //      
  // }catch(Exception e)
  // {System.out.println("addStudentIntoAssignment Exception!");
  // e.printStackTrace();
  //      
  // }
  //    
  //    
  //    
  // }
  // public void addAssignmentToStudent(long assID,String assName,long
  // fileNum,long deadLine,long userID)
  // {
  // String
  // sql="insert into "+userID+"UA (AssID,AssName,FileNum,DeadLine) values(?,?,?,?)";
  // PreparedStatement psm=null;
  // try{
  // psm=con.prepareStatement(sql);
  // psm.setLong(1,assID);
  // psm.setString(2,assName);
  // psm.setLong(3,fileNum);
  // psm.setLong(4,deadLine);
  //      
  // psm.executeUpdate();
  //      
  // con.commit();
  //      
  // }
  // catch(Exception e)
  // {
  // System.out.println("failed to insert ass into student "+userID+"!");
  // }
  // }
  public void addStudentIntoAssignment(long userID,long assID)
  {
    String sql=
        "insert into userassignment (UserID,AssID,Status,Score,ScoreType,UploadTime) values(?,?,0,0,0,0)";
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
    {System.out.println("addStudentIntoAssignment Exception "+e.getLocalizedMessage());
     // e.printStackTrace();
    }
  }

  public void setFileStatus(long fileNum,long assID,boolean type)
  {
    String sql="update files set AssignmentID=?,UploadType=? where FileNum=?";
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,assID);
      psm.setBoolean(2,type);
      psm.setLong(3,fileNum);
      psm.execute();

      con.commit();
      psm.close();
    }
    catch(Exception e)
    {System.out.println("setFileStatus Exception "+e.getLocalizedMessage());
     // e.printStackTrace();
    }
  }

  public ArrayList<Long> releaseAssignment(Assignment ast)
  {
    ArrayList<Long> studentList=new ArrayList<Long>();
    long assID=0;
    String assName=ast.getAssignmentName();

    String addToAList=
        "insert into assignment (AssName,DeadLine,Content,CourseID,CourseName) values(?,?,?,?,?)";

    String getAssID=
        "select * from assignment where AssName=? and DeadLine=? and Content=?";
    String getStudentList="select * from coursestudent where CourseID=?";
    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(addToAList);

      psm.setString(1,assName);
      psm.setLong(2,ast.getDeadLine());
      psm.setString(3,ast.getContent());
      psm.setLong(4,ast.getCourseID());
      psm.setString(5,ast.getCourseName());

      psm.executeUpdate();

      con.commit();

      psm=con.prepareStatement(getAssID);
      psm.setString(1,assName);
      psm.setLong(2,ast.getDeadLine());
      psm.setString(3,ast.getContent());
      ResultSet rs=psm.executeQuery();

      rs.last();
      assID=rs.getLong("AssID");

      rs.close();

      psm.close();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    // 取得学生列表
    try
    {
      if(assID==0)
      {
        throw new Exception("first step error,second step quit!");
      }
      psm=con.prepareStatement(getStudentList);
      psm.setLong(1,ast.getCourseID());
      // psm.setLong(2,assID);
      // psm.setString(3,assName);
      // psm.setLong(4,fileNum);
      // psm.setLong(5,deadLine);
      // psm.executeUpdate();
      // con.commit();
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      while(rs.next())
      {
        studentList.add(new Long(rs.getLong("UserID")));

      }

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    // 将作业写入学生的作业列表
    try
    {
      if(assID==0)
      {
        throw new Exception("first step error,third step quit!");
      }
      // psm=con.prepareStatement(getStuList);
      // psm.setLong(1,courseID);
      //      
      // ResultSet rs=psm.executeQuery();
      //      
      // rs.beforeFirst();
      // while(rs.next())
      // {long stuID=rs.getLong("UserID");
      // String stuName=rs.getString("UserName");
      // this.addAssignmentToStudent(assID,assName,fileNum,deadLine,stuID);
      // this.addStudentIntoAssignment(stuID,stuName,assID);
      // studentList.add(new Long(stuID));
      //        
      // }
      // con.commit();
      // rs.close();
      // psm.close();
      Iterator<Long> it=studentList.iterator();
      while(it.hasNext())
      {
        this.addStudentIntoAssignment(it.next().longValue(),assID);
      }
      ArrayList<Appendix> appendix=ast.getAppendixes();
      Iterator<Appendix> app=appendix.iterator();
      while(app.hasNext())
      {
        this.setFileStatus(app.next().getFileNum(),assID,true);

      }

      //studentList.add(0,assID);
      return studentList;

    }
    catch(Exception e)
    {System.out.println("releaseAssignment Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
      return null;
    }

  }

  public ArrayList<TeacherAssignment> getTeacherAssignment(long userID)
  {
    ArrayList<TeacherAssignment> tAL=new ArrayList<TeacherAssignment>();
    ArrayList<Course> cAL=new ArrayList<Course>();
    String sql="select * from assignment where CourseID=?";
    String sql2="select * from course where TeacherID=?";
    PreparedStatement psm=null;
    PreparedStatement psm2=null;
    try
    {
      psm2=con.prepareStatement(sql2);
      psm2.setLong(1,userID);
      ResultSet rs2=psm2.executeQuery();
      rs2.beforeFirst();
      while(rs2.next())
      {
        try
        {
          psm=con.prepareStatement(sql);
          psm.setLong(1,rs2.getLong("CourseID"));

          ResultSet rs=psm.executeQuery();

          while(rs.next())
          {
            // long assID=rs.getLong("AssID");
            // String assName=rs.getString("AssName");

            tAL.add(new TeacherAssignment(rs.getLong("AssID"),rs
                .getString("AssName"),rs.getLong("DeadLine"),rs
                .getString("Content"),rs.getLong("CourseID"),rs
                .getString("CourseName"),null));
          }

        }
        catch(Exception e)
        {System.out.println("getTeacherAssignment Exception "+e.getLocalizedMessage());
         // e.printStackTrace();
        }
      }
    }
    catch(Exception e)
    {

    }
    return tAL;

  }

  public ArrayList<Appendix> getAppendixList(long assID,long userID)
  {
    ArrayList<Appendix> appendix=new ArrayList<Appendix>();
    String sql="select * from files where AssignmentID=? and Uploader=?";

    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,assID);
      psm.setLong(2,userID);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      while(rs.next())
      {
        appendix.add(new Appendix(rs.getLong("FileNum"),rs
            .getString("FileName"),rs.getLong("FileLength"),rs
            .getBoolean("UploadType"),rs.getLong("Uploader"),rs
            .getLong("UploadTime")));

      }

    }
    catch(Exception e)
    {
      System.out.println("getAppendixList Exception "+e.getLocalizedMessage());
     // e.printStackTrace();
    }

    return appendix;
  }

  public ArrayList<Appendix> getAppendixList(long assID,boolean type)
  {
    ArrayList<Appendix> appendix=new ArrayList<Appendix>();
    String sql="select * from files where AssignmentID=? and UploadType=?";

    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,assID);
      psm.setBoolean(2,type);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      while(rs.next())
      {
        appendix.add(new Appendix(rs.getLong("FileNum"),rs
            .getString("FileName"),rs.getLong("FileLength"),rs
            .getBoolean("UploadType"),rs.getLong("Uploader"),rs
            .getLong("UploadTime")));

      }

    }
    catch(Exception e)
    {System.out.println("getAppendixList Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
    }

    return appendix;
  }

  public StudentAssignment getStudentAssignment(long assID,int status,
      double score,int scoreType)
  {
    StudentAssignment sa=null;
    String sql="select * from Assignment where AssID=?";

    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,assID);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      if(rs.next())
      {
        sa=
            new StudentAssignment(assID,rs.getString("AssName"),rs
                .getLong("DeadLine"),rs.getString("Content"),rs
                .getLong("CourseID"),rs.getString("CourseName"),this
                .getAppendixList(assID,true),score,status);
        sa.setScoreType(scoreType);

      }

      rs.close();
      psm.close();

    }
    catch(Exception e)
    {System.out.println("getStudentList Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
    }

    return sa;

  }

  /**
   * 获得学生的作业列表
   * 
   * @param userID
   * @return
   */
  public ArrayList<StudentAssignment> getStudentAssignmentList(long userID)
  {
    ArrayList<StudentAssignment> studentAssignmentList=
        new ArrayList<StudentAssignment>();
    String sql="select * from UserAssignment where UserID=?";

    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      ResultSet rs=psm.executeQuery();

      while(rs.next())
      {
        long assID=rs.getLong("AssID");

        int status=rs.getInt("Status");

        double score=rs.getDouble("Score");
        int scoreType=rs.getInt("ScoreType");

        studentAssignmentList.add(this.getStudentAssignment(assID,status,score,
            scoreType));

      }

    }
    catch(Exception e)
    {System.out.println("getstudentAssignemtList Exception "+e.getLocalizedMessage());
     // e.printStackTrace();

    }

    return studentAssignmentList;
  }

  /**
   * 获得指定ID的Message列表
   * 
   * @param userID
   * @return
   */
  public ArrayList<Message> getMessageList(long userID)
  {
    ArrayList<Message> messageList=new ArrayList<Message>();
    String sql="select * from message where reader=?";
    String sql2="delete from message where reader=?";
    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      while(rs.next())
      {

        try
        {
          String content=rs.getString("Content");
          long fromID=rs.getLong("Writer");

          long sentTime=rs.getLong("SentTime");
          messageList.add(new Message(content,sentTime,fromID,userID));

        }
        catch(Exception e)
        {
          continue;
        }

      }
      rs.close();
      psm=con.prepareStatement(sql2);
      psm.setLong(1,userID);
      psm.execute();
      con.commit();

    }
    catch(Exception e)
    {System.out.println("getMessageList Exception "+e.getLocalizedMessage());
     // e.printStackTrace();
      return messageList;
    }

    return messageList;
  }

  public Course getCourse(long courseID)
  {
    String sql="select * from course where CourseID=?";
    Course c=new Course(0,null,0,null);
    PreparedStatement psm=null;
    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,courseID);
      ResultSet rs=psm.executeQuery();

      rs.beforeFirst();
      if(rs.next())
      {
        c.setCourseID(rs.getLong("CourseID"));
        c.setCourseName(rs.getString("CourseName"));
        c.setTeacherID(rs.getLong("TeacherID"));
        c.setTeacherName(rs.getString("TeacherName"));

      }
      rs.close();
      psm.close();

    }
    catch(Exception e)
    {System.out.println("getCourseList Exception "+e.getLocalizedMessage());
     // e.printStackTrace();
    }
    return c;
  }

  /**
   * 获得指定ID的课程列表
   * 
   * @param userID
   * @return
   */
  public ArrayList<Course> getCourseList(long userID)
  {
    ArrayList<Course> courseList=new ArrayList<Course>();
    boolean type=false;
    try
    {
      type=this.getUserType(userID);
    }
    catch(Exception e)
    {
      return courseList;
    }
    if(type)
    {
      String sql="select * from course where TeacherID=?";

      PreparedStatement psm=null;

      try
      {
        psm=con.prepareStatement(sql);
        psm.setLong(1,userID);
        ResultSet rs=psm.executeQuery();

        rs.beforeFirst();
        while(rs.next())
        {

          try
          {
            String courseName=rs.getString("CourseName");

            long courseID=rs.getLong("CourseID");

            courseList.add(new Course(courseID,courseName,0,null));

          }
          catch(Exception e)
          {
            continue;
          }

        }

      }
      catch(Exception e)
      {System.out.println("getCOurseLIst Exception "+e.getLocalizedMessage());
       // e.printStackTrace();
        return courseList;
      }
    }
    else
    {
      String sql="select * from coursestudent where UserID=?";

      PreparedStatement psm=null;

      try
      {
        psm=con.prepareStatement(sql);
        psm.setLong(1,userID);
        ResultSet rs=psm.executeQuery();

        rs.beforeFirst();
        while(rs.next())
        {

          try
          {

            long courseID=rs.getLong("CourseID");

            courseList.add(this.getCourse(courseID));

          }
          catch(Exception e)
          {
            continue;
          }

        }
        rs.close();
        psm.close();

      }
      catch(Exception e)
      {System.out.println("getCourseList Exception "+e.getLocalizedMessage());
       // e.printStackTrace();
        return courseList;
      }

    }
    return courseList;
  }

  public ArrayList<User> getContactList(long userID)
  {
    ArrayList<User> contactList=new ArrayList<User>();
    String sql="select * from contact where UserID=?";

    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,userID);
      ResultSet rs=psm.executeQuery();
      rs.beforeFirst();
      while(rs.next())
      {

        try
        {
          String userName=rs.getString("ContactName");
          boolean userType=rs.getBoolean("ContactType");
          long contactID=rs.getLong("ContactID");
          contactList.add(new User(contactID,userName,userType));

        }
        catch(Exception e)
        {
          continue;
        }

      }

    }
    catch(Exception e)
    {System.out.println("getContactLIst Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
      return contactList;
    }

    return contactList;
  }

  /**
   * 根据ID获取一个simpleuser
   * 
   * @param userID
   * @return
   */
  public User getSimpleUser(long userID)
  {
    String userName;
    boolean userType;

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
        rs.close();
        psm.close();

        return null;
      }
      userName=rs.getString("UserName");
      userType=rs.getBoolean("UserType");
      return new User(userID,userName,userType);

    }
    catch(Exception e)
    {System.out.println("getSimpleUser Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
      return null;
    }

  }

  public void addStudentIntoCourse(User su,Course sc) throws Exception
  {
    long userID=su.getUserID();
    // String userName=su.getUserName();
    long courseID=sc.getCourseID();
    // String courseName=sc.getName();
    // boolean userType=su.getType();
    String sql="insert into coursestudent (CourseID,UserID) values (?,?)";
    // String sql2="insert into ?cs (UserID,UserName,UserType) values(?,?,?)";

    PreparedStatement psm=null;

    try
    {
      psm=con.prepareStatement(sql);
      psm.setLong(1,courseID);
      psm.setLong(2,userID);
      // psm.setString(3,courseName);

      psm.execute();

      con.commit();

      // psm=con.prepareStatement(sql2);
      // psm.setLong(1,courseID);
      // psm.setLong(2,userID);
      // psm.setString(3,userName);
      // psm.setBoolean(4,userType);
      //      
      // psm.execute();
      //      
      // con.commit();

    }
    catch(Exception e)
    {System.out.println("addStudentIntoCourse Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
    }

  }

  public long addNewCourse(Course newCourse) throws Exception
  {
    long userID=newCourse.getTeacherID();
    String teacherName=newCourse.getTeacherName();

    String courseName=newCourse.getCourseName();

    String sql=
        "insert into course (CourseName,TeacherID,TeacherName) values(?,?,?)";
    // String sql2="select * from asm.course where CourseName=?";
    String sql2="insert into discuss (DiscussName,CourseID,CourseName) values (?,?,?)";
    String sql3="select * from course where TeacherID=? and CourseName=?";
    PreparedStatement psm=null;
    PreparedStatement psm2=null;
    PreparedStatement psm3=null;
    try
    {
      
      psm=con.prepareStatement(sql);
      psm2=con.prepareStatement(sql2);
      psm.setString(1,courseName);
      psm.setLong(2,userID);
      psm.setString(3,teacherName);
      psm.execute();

      con.commit();
      psm3=con.prepareStatement(sql3);
      psm3.setLong(1,userID);
      psm3.setString(2,courseName);
      ResultSet rs=psm3.executeQuery();
       rs.beforeFirst();
       if(!rs.next())
       {
         throw new Exception ("addNewCOurse Unknown error!");
       }
       long courseID=rs.getLong("CourseID");
      rs.close();
      
      
      psm2.setString(1,teacherName+"'s "+courseName+" Discuss");
      psm2.setLong(2,courseID);
      psm2.setString(3,courseName);
      psm2.execute();
      
      con.commit();

      // psm=con.prepareStatement(sql2);
      // psm.setString(1,courseName);
      //      
      // ResultSet rs=psm.executeQuery();
      //      
      // rs.last();
      //      
      //      
      // long courseID=rs.getLong("CourseID");
     return courseID;
    }
    catch(Exception e)
    {System.out.println("addnewCourse Exception "+e.getLocalizedMessage());
      //e.printStackTrace();
     // con.rollback();
      throw e;

    }

  }

  public DBTableOperator() throws Exception
  {
    super();

  }

}
