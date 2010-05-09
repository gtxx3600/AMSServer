
package com.bluemountain;

import java.util.*;
//import com.bluemountain.*;
import com.bluemountain.mysql.*;
import com.bluemountain.datatype.*;

public class TableUtil
{
  public static ArrayList<Discussion> getDiscussList()
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      
      e.printStackTrace();

    }
    return dbto.getDiscussionList();
  }
public static void setScore(AssignmentStudent as)throws Exception 
{
  DBTableOperator dbto=null;
  try
  {
    dbto=new DBTableOperator();
    
  }
  catch(Exception e)
  {
    
    e.printStackTrace();

  }
  dbto.setScore(as);
}
  public static void setAssignmentReaded(long userID,long assID) throws Exception
  { 
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      
      e.printStackTrace();

    }
    dbto.setAssignmentReaded(userID,assID);
    
  }
  public static ArrayList<AssignmentStudent> getAssignmentStudentList(long assID)
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      
      e.printStackTrace();

    }
    
    return dbto.getAssignmentStudentList(assID);
    
  }
//  public static ArrayList<SimpleUser> getCourseStudentList(long courseID)
//  {
//    DBTableOperator dbto=null;
//    try
//    {
//      dbto=new DBTableOperator();
//      
//    }
//    catch(Exception e)
//    {
//      
//      e.printStackTrace();
//
//    }
//    return dbto.getCourseStudentList(courseID);
//    
//    
//    
//  }
  public static void addContact(long userID,User su)throws Exception
  {
    
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    dbto.addContact(userID,su);
    
  }
  
  public static void teacherReleaseAssignment(Assignment ass)throws Exception
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    ArrayList<Long> stuList=dbto.releaseAssignment(ass);
    if(stuList==null)
    {
      throw new Exception("teacherReleaseAsst Exception !");
    }
    ListIterator<Long> it=stuList.listIterator();
    //stuList.remove(0);
    while(it.hasNext())
    {
      try{
        long dst=it.next().longValue();
        MessageServer.sentMessage(new Message("You got a new work to do!",new java.util.Date().getTime(),0,dst));
      }catch(Exception e)
      {
        e.printStackTrace();
        System.out.println("teacherReleaseAsst got Exception while sending message!");
        continue;
      }
      
    }
     
    
    
    
  }
  public static void studentUploadAssignment(long userID,Assignment ass)throws Exception 
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    dbto.studentUploadAssignment(userID,ass);
  }
  
  
  public static ArrayList<Course> getTeacherCourse(long userID)
  {
    return TableUtil.getCourseList(userID);
  }

  public static ArrayList<User> getAllUser()
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    return dbto.getAllUser();
  }
  public static void addStudentIntoCourse(User su,Course sc)
      throws Exception
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
      
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    dbto.addStudentIntoCourse(su,sc);
    
    
  }

  public static ArrayList<Message> getUserMessageList(long userID)
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    return dbto.getMessageList(userID);

  }

  public static ArrayList getUserAssignmentList(long userID)
  {

    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    boolean type=false;

    try
    {
      type=dbto.getUserType(userID);

    }
    catch(Exception e)
    {
      System.err.println("getUserAssignmentList Exception :Can't get userType:"
          +e.getLocalizedMessage());

      return new ArrayList();
    }
      
    if(type)
    {
      return dbto.getTeacherAssignment(userID);
    }
    else
    {
      return dbto.getStudentAssignmentList(userID);
    }

  }

  public static ArrayList<User> getContactList(long userID)
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    return dbto.getContactList(userID);

  }

  public static ArrayList<Course> getCourseList(long userID)
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    return dbto.getCourseList(userID);

  }

//  public static void releaseAssignment(Assignment ass) throws Exception
//  {
//    DBTableOperator dbto=null;
//    try
//    {
//      dbto=new DBTableOperator();
//
//    }
//    catch(Exception e)
//    {
//      e.printStackTrace();
//
//    }
//   // dbto.releaseAssignment(ass);
//  }

  /**
   * 老师添加新课程
   * 
   * @param c
   *          课程的Course类
   * @throws Exception
   */
  public static long addNewCourse(Course c)
      throws Exception
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();

    }
    catch(Exception e)
    {
      e.printStackTrace();

    }
    try
    {
      return dbto.addNewCourse(c);

    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw e;
    }

  }

  public static User getMySelf(long userID) // throws Exception
  {
    DBTableOperator dbto=null;
    try
    {
      dbto=new DBTableOperator();
    }
    catch(Exception e)
    {
      e.printStackTrace();

    }

    User su=dbto.getSimpleUser(userID);

    if(su!=null)
    {
      return su;

    }
    else
    {
      return null;
    }

  }

}
