/**
 * 
 */

package com.bluemountain.datatype;

import java.io.Serializable;
import java.util.ArrayList;

public class Assignment implements Serializable
{
  private static final long serialVersionUID = -6312175299244994241L;

  private long assignmentID;
  private String assignmentName;
  private long deadLine;
  private String content;
  private long courseID;
  private String courseName;
  private ArrayList<Appendix> appendixes;
  
  public Assignment(long assignmentID, String assignmentName, long deadLine,
      String content, long courseID, String courseName,
      ArrayList<Appendix> appendixes)
  {
    super();
    this.assignmentID = assignmentID;
    this.assignmentName = assignmentName;
    this.deadLine = deadLine;
    this.content = content;
    this.courseID = courseID;
    this.courseName = courseName;
    this.appendixes = appendixes;
  }
  /**
   * @return the assignmentID
   */
  public long getAssignmentID()
  {
    return assignmentID;
  }
  /**
   * @param assignmentID the assignmentID to set
   */
  public void setAssignmentID(long assignmentID)
  {
    this.assignmentID = assignmentID;
  }
  /**
   * @return the assignmentName
   */
  public String getAssignmentName()
  {
    return assignmentName;
  }
  /**
   * @param assignmentName the assignmentName to set
   */
  public void setAssignmentName(String assignmentName)
  {
    this.assignmentName = assignmentName;
  }
  /**
   * @return the deadLine
   */
  public long getDeadLine()
  {
    return deadLine;
  }
  /**
   * @param deadLine the deadLine to set
   */
  public void setDeadLine(long deadLine)
  {
    this.deadLine = deadLine;
  }
  /**
   * @return the content
   */
  public String getContent()
  {
    return content;
  }
  /**
   * @param content the content to set
   */
  public void setContent(String content)
  {
    this.content = content;
  }
  /**
   * @return the courseID
   */
  public long getCourseID()
  {
    return courseID;
  }
  /**
   * @param courseID the courseID to set
   */
  public void setCourseID(long courseID)
  {
    this.courseID = courseID;
  }
  /**
   * @return the courseName
   */
  public String getCourseName()
  {
    return courseName;
  }
  /**
   * @param courseName the courseName to set
   */
  public void setCourseName(String courseName)
  {
    this.courseName = courseName;
  }
  /**
   * @return the appendixes
   */
  public ArrayList<Appendix> getAppendixes()
  {
    return appendixes;
  }
  /**
   * @param appendixes the appendixes to set
   */
  public void setAppendixes(ArrayList<Appendix> appendixes)
  {
    this.appendixes = appendixes;
  }
  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid()
  {
    return serialVersionUID;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "Assignment [appendixes=" + appendixes + ", assignmentID="
        + assignmentID + ", assignmentName=" + assignmentName + ", content="
        + content + ", courseID=" + courseID + ", courseName=" + courseName
        + ", deadLine=" + deadLine + "]";
  }
  
  
}
