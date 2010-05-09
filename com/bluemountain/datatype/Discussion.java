//Discussion.java

/**
 *
 */

package com.bluemountain.datatype;

import java.io.Serializable;

public class Discussion implements Serializable 
{
  private static final long serialVersionUID = 8561797226265678411L;
  
  private long discussionID;
  private String discussionName;
  private long courseID;
  private String courseName;
  
  public Discussion(long discussionID, String discussionName, long courseID,
      String courseName)
  {
    super();
    this.discussionID = discussionID;
    this.discussionName = discussionName;
    this.courseID = courseID;
    this.courseName = courseName;
  }


  /**
   * @return the discussionID
   */
  public long getDiscussionID()
  {
    return discussionID;
  }


  /**
   * @param discussionID the discussionID to set
   */
  public void setDiscussionID(long discussionID)
  {
    this.discussionID = discussionID;
  }


  /**
   * @return the discussionName
   */
  public String getDiscussionName()
  {
    return discussionName;
  }


  /**
   * @param discussionName the discussionName to set
   */
  public void setDiscussionName(String discussionName)
  {
    this.discussionName = discussionName;
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
   * @return the serialversionuid
   */
  public static long getSerialversionuid()
  {
    return serialVersionUID;
  }


  public String toString()
  {
    return this.getDiscussionName();
  }
}