
package com.bluemountain;

import java.io.*;
import java.net.*;
import java.util.*;
import com.bluemountain.datatype.*;
import com.bluemountain.mysql.*;

public class TableServer implements Service
{
  public void serve(InputStream in,OutputStream out) throws IOException
  {
    ObjectOutputStream output=new ObjectOutputStream((out));
    ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(in));

    long userID=0;
    try
    {
      try
      {
        userID=CheckUserInfo.userCheck(input,output);
      }
      catch(Exception e)
      {
        System.err.println("TableServer Exception :"+e.getLocalizedMessage());
        //e.printStackTrace();
      }

      if(userID==0)
      {
        System.out.println("check unpass!");
        output.writeUTF("Failed");
        output.flush();

      }
      else
      {
        
        output.writeUTF("Welcome");
        output.flush();

        int timeOutCount=0;
        while(true)
        {

          if(input.available()!=0)
          {
            timeOutCount=0;

            String s=input.readUTF();
            System.out.println("Got a "+s);

            if(s.equals("GetMySelf"))
            {
              User su=TableUtil.getMySelf(userID);         
              output.writeObject(su);
              output.flush();
              System.out.println(su.getUserName());
              break;
            }
//            if(s.equals("TeacherReleaseCourse"))
//            {
//              output.writeUTF("Ready");
//              output.flush();
//
//              SimpleUser t=(SimpleUser)input.readObject();
//
//              SimpleCourse sc=(SimpleCourse)input.readObject();
//
//              try
//              {
//                TableUtil.addNewCourse(t,sc);
//              }
//              catch(Exception e)
//              {
//                output.writeUTF("Failed");
//                output.flush();
//                break;
//
//              }
//              output.writeUTF("Done");
//              output.flush();
//
//              break;
//            }
            if(s.equals("GetUC"))
            {
              ArrayList<User> uc=TableUtil.getContactList(userID);
              output.writeObject(uc);
              output.flush();
              break;
            }
            if(s.equals("GetUCourse"))
            {
              output.writeUTF("Ready");
              output.flush();
              User su=(User)input.readObject();
              
              ArrayList<Course> uc=TableUtil.getCourseList(su.getUserID());
              output.writeObject(uc);
              output.flush();
//              Iterator<SimpleCourse> it=uc.iterator();
//            while(it.hasNext())
//            {
//              System.out.println("~course~"+(it.next()).getName());
//            }
              break;
            }
            if(s.equals("GetUA"))
            {
              ArrayList ua=TableUtil.getUserAssignmentList(userID);

              output.writeObject(ua);

              output.flush();
              break;

            }
            if(s.equals("GetUM"))
            {
              ArrayList<Message> um=TableUtil.getUserMessageList(userID);
              output.writeObject(um);
              output.flush();
              output.writeUTF("~");
              output.flush();
              //Iterator<Message> it=um.iterator();
//              while(it.hasNext())
//              {
//                System.out.println("~m~"+(it.next()).getMessage());
//              }
           
              break;

            }
            if(s.equals("TeacherReleaseCourse"))
            {
              long courseID=0;
              output.writeUTF("Ready");
              output.flush();

//              User t=(User)input.readObject();
//              System.out.println("get a simpleuser");
              Course sc=(Course)input.readObject();
              System.out.println("get a simplecourse");
              try
              {
               courseID= TableUtil.addNewCourse(sc);
                output.writeUTF(Long.toString(courseID));
                output.flush();

              }
              catch(Exception e)
              {
                System.err.println("TableServer :addNewCourse Exception "
                    +e.getLocalizedMessage());
                output.writeUTF(Long.toString(courseID));
                output.flush();
              }
              break;
            }
            if(s.equals("StudentJoinCourse"))
            {
              output.writeUTF("Ready");
              output.flush();

              User su=(User)input.readObject();
              System.out.println("get a simpleuser");
              Course sc=(Course)input.readObject();
              System.out.println("get a simplecourse");
              try
              {
                TableUtil.addStudentIntoCourse(su,sc);
                output.writeUTF("Done");
                output.flush();

              }
              catch(Exception e)
              {
                System.err
                    .println("TableServer :addStudentIntoCourse Exception "
                        +e.getLocalizedMessage());
                output.writeUTF("Failed");
                output.flush();
              }
              break;

            }
         
            if(s.equals("GetAllUser"))
            {
              ArrayList<User> au=TableUtil.getAllUser();
//              Iterator<SimpleUser> it=au.iterator();
//              while(it.hasNext())
//              {
//                System.out.println("all user: "+it.next().getName());
//              }
              output.writeObject(au);
              output.flush();
              break;

            }
         
            if(s.equals("GetTeacherCourse"))
            {
              User t=(User)input.readObject();
              output.writeObject(TableUtil.getTeacherCourse(t.getUserID()));
              output.flush();
              break;

            }
          
            if(s.equals("UploadAssignment"))
            {
              output.writeUTF("Ready");
              output.flush();
              
              Assignment ass=(Assignment)input.readObject();
              
              try{
                TableUtil.studentUploadAssignment(userID,ass);
                output.writeUTF("Done");
                output.flush();
              }catch(Exception e)
              {e.printStackTrace();
                output.writeUTF("Failed");
                output.flush();
              }
             
              break;
            }
        
            if(s.equals("ReleaseAssignment"))
            {
              output.writeUTF("Ready");
              output.flush();
              
              Assignment ass=(Assignment)input.readObject();
              
              try{
                TableUtil.teacherReleaseAssignment(ass);
                output.writeUTF("Done");
                output.flush();
              }catch(Exception e)
              {
                output.writeUTF("Failed");
                output.flush();
                System.err.println("Release Ass failed!");
              }
              
              
              break;
            }
       
            if(s.equals("AddUC"))
            {
              output.writeUTF("Ready");
              output.flush();
              User su=(User)input.readObject();
              
              try{
                TableUtil.addContact(userID,su);
                output.writeUTF("Done");
                output.flush();
                
              }
              catch(Exception e)
              {
                output.writeUTF("Failed");
                output.flush();
                e.printStackTrace();
                System.err.println("Add contact failed!");
              }
              
              
              break;
            }
            if(s.equals("SetReaded"))
            {
              output.writeUTF("Ready");
              output.flush();
              StudentAssignment sa=(StudentAssignment)input.readObject();
              try{
                TableUtil.setAssignmentReaded(userID,sa.getAssignmentID());
                output.writeUTF("Done");
                output.flush();
              }catch(Exception e)
              {
                output.writeUTF("Failed");
                output.flush();
              }
              break;
              
            }
            if(s.equals("GetD"))
            {
              ArrayList<Discussion>  aa=TableUtil.getDiscussList();
              output.writeObject(aa);

              output.flush();
              Iterator<Discussion> it=aa.iterator();
              while(it.hasNext())
              {
                System.out.println(it.next());
              }
              
              break;
            }
            if(s.equals("GetAS"))
            {
              output.writeUTF("Ready");
              output.flush();
              Long assID=(Long)input.readObject();
              ArrayList<AssignmentStudent> as=TableUtil.getAssignmentStudentList(assID.longValue());
              output.writeObject(as);
              Iterator<AssignmentStudent> it=as.iterator();
              while(it.hasNext()){
                System.out.println(it.next());
              }
              
              break;
            }
            if(s.equals("SetScore"))
            {
              output.writeUTF("Ready");
              output.flush();
              AssignmentStudent as=(AssignmentStudent)input.readObject();
              
             try{
               TableUtil.setScore(as);
               output.writeUTF("Done");
               output.flush();
              }
              catch(Exception e)
              {
                output.writeUTF("Failed");
                output.flush();
                System.out.println("SetScoreFailed!");
              }
              break;
            }
            if(s.equals(""))
            {
              
              
              
              
              break;
            }


          }
          try
          {
            Thread.sleep(500);

          }
          catch(Exception e)
          {
            e.printStackTrace();
          }

          timeOutCount++;
          if(timeOutCount>50)
          {
            output.writeUTF("TimeOut");
            output.flush();

            break;
          }
        }

      }
      output.close();
      input.close();

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
