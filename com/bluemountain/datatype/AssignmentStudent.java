
/**
 *
 */

package com.bluemountain.datatype;

import java.io.*;
import java.util.*;

public class AssignmentStudent implements Serializable
{
  private static final long serialVersionUID = 5259563456964484083L;
  
  private long userID;
  private String userName;
  private boolean isUpload;
  private long uploadDate;
  private int scoreType;
  private int score;
  private ArrayList<Appendix> appendixes;
  
  public AssignmentStudent(long userID, String userName, boolean isUpload,
      long uploadDate, int scoreType, int score, ArrayList<Appendix> appendixes)
  {
    super();
    this.userID = userID;
    this.userName = userName;
    this.isUpload = isUpload;
    this.uploadDate = uploadDate;
    this.scoreType = scoreType;
    this.score = score;
    this.appendixes = appendixes;
  }


  /**
   * @return the userID
   */
  public long getUserID()
  {
    return userID;
  }


  /**
   * @param userID the userID to set
   */
  public void setUserID(long userID)
  {
    this.userID = userID;
  }


  /**
   * @return the userName
   */
  public String getUserName()
  {
    return userName;
  }


  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName)
  {
    this.userName = userName;
  }


  /**
   * @return the isUpload
   */
  public boolean getIsUpload()
  {
    return isUpload;
  }


  /**
   * @param isUpload the isUpload to set
   */
  public void setUpload(boolean isUpload)
  {
    this.isUpload = isUpload;
  }


  /**
   * @return the uploadDate
   */
  public long getUploadDate()
  {
    return uploadDate;
  }


  /**
   * @param uploadDate the uploadDate to set
   */
  public void setUploadDate(long uploadDate)
  {
    this.uploadDate = uploadDate;
  }


  /**
   * @return the scoreType
   */
  public int getScoreType()
  {
    return scoreType;
  }


  /**
   * @param scoreType the scoreType to set
   */
  public void setScoreType(int scoreType)
  {
    this.scoreType = scoreType;
  }


  /**
   * @return the score
   */
  public int getScore()
  {
    return score;
  }


  /**
   * @param score the score to set
   */
  public void setScore(int score)
  {
    this.score = score;
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


  public String toString()
  {
    return this.getUserName() + "(" + this.getUserID() + 
      "):" + this.getIsUpload();
  }
}
