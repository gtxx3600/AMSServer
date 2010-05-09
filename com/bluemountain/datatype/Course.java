/**
 * 
 */
package com.bluemountain.datatype;

import java.io.Serializable;

public class Course implements Serializable
{
  private static final long serialVersionUID = -6898841112652270544L;

  private long courseID;
  private String courseName;
  private long teacherID;
  private String teacherName;
  
  public Course(long courseID, String courseName, long teacherID,
      String teacherName)
  {
    super();
    this.courseID = courseID;
    this.courseName = courseName;
    this.teacherID = teacherID;
    this.teacherName = teacherName;
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
   * @return the teacherID
   */
  public long getTeacherID()
  {
    return teacherID;
  }
  /**
   * @param teacherID the teacherID to set
   */
  public void setTeacherID(long teacherID)
  {
    this.teacherID = teacherID;
  }
  /**
   * @return the teacherName
   */
  public String getTeacherName()
  {
    return teacherName;
  }
  /**
   * @param teacherName the teacherName to set
   */
  public void setTeacherName(String teacherName)
  {
    this.teacherName = teacherName;
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
    return "Course [courseID=" + courseID + ", courseName=" + courseName
        + ", teacherID=" + teacherID + ", teacherName=" + teacherName + "]";
  }
}
