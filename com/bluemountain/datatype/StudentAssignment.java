/**
 * 
 */

package com.bluemountain.datatype;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentAssignment extends Assignment implements Serializable
{
  private static final long serialVersionUID = 9071303697919187044L;
  private int scoreType; // 0: double, 1: ABCD
  private double score;
  private int status; // 0: new, 1: unfinished, 2: finished
  
  public StudentAssignment(long assignmentID, String assignmentName,
      long deadLine, String content, long courseID, String courseName,
      ArrayList<Appendix> appendixes, double score, int status)
  {
    super(assignmentID, assignmentName, deadLine, content, courseID,
        courseName, appendixes);
    this.score = score;
    this.status = status;
  }

  /**
   * @return the scoreType
   */
  public int getScoreType()
  {
    return scoreType;
  }

  /**
   * @return the score
   */
  public double getScore()
  {
    return score;
  }

  /**
   * @param score the score to set
   */
  public void setScore(double score)
  {
    this.score = score;
  }

  /**
   * @return the status
   */
  public int getStatus()
  {
    return status;
  }

  /**
   * 
   * @param scoreType
   */
  public void setScoreType(int scoreType)
  {
    this.scoreType = scoreType;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status)
  {
    this.status = status;
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
    if (getStatus() == 0) return (this.getAssignmentName() + "(new)");
    else if(getStatus() == 1) return (this.getAssignmentName() + "(unfinished)");
    else return (this.getAssignmentName() + "(finished)");
  }
}
