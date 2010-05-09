/**
 * 
 */

package com.bluemountain.datatype;

import java.io.Serializable;

public class User implements Serializable
{
  private static final long serialVersionUID = 5259915565902014638L;

  private long userID;
  private String userName;
  private boolean type; // false: student, true: teacher
  
  public User(long userID, String userName, boolean type)
  {
    super();
    this.userID = userID;
    this.userName = userName;
    this.type = type;
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
   * @return the type
   */
  public boolean isType()
  {
    return type;
  }
  /**
   * @param type the type to set
   */
  public void setType(boolean type)
  {
    this.type = type;
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
    return this.getUserName()+"("+this.getUserID()+")";
  }
}
