package com.bluemountain.datatype;

import java.io.Serializable;
import java.util.ArrayList;

public class TeacherAssignment extends Assignment implements Serializable
{
  private static final long serialVersionUID = -6111482950351208297L;

  public TeacherAssignment(long assignmentID, String assignmentName,
      long deadLine, String content, long courseID, String courseName,
      ArrayList<Appendix> appendixes)
  {
    super(assignmentID, assignmentName, deadLine, content, courseID, courseName,
        appendixes);
    // TODO Auto-generated constructor stub
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "TeacherAssignment [getAppendixes()=" + getAppendixes()
        + ", getAssignmentID()=" + getAssignmentID() + ", getAssignmentName()="
        + getAssignmentName() + ", getContent()=" + getContent()
        + ", getCourseID()=" + getCourseID() + ", getCourseName()="
        + getCourseName() + ", getDeadLine()=" + getDeadLine()
        + ", toString()=" + super.toString() + ", getClass()=" + getClass()
        + ", hashCode()=" + hashCode() + "]";
  }

}
