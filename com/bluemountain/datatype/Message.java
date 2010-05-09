//Message.java

/**
 *
 */

package com.bluemountain.datatype;

import java.io.Serializable;

public class Message implements Serializable
{
  /**
   * 
   */
  private static final long serialVersionUID = -7272010023219340228L;
  private String content;
  private long sendingDate;
  private long sourceID;
  private long destinationID;
  
  public Message(String content, long sendingDate,
      long sourceID, long destinationID)
  {
    this.content = content;
    this.sendingDate = sendingDate;
    this.sourceID = sourceID;
    this.destinationID = destinationID;
  }
  
  public String getMessage()
  {
    return content;
  }

  public boolean isEmpty()
  {
    if(content == null)
      return true;
    return false;
  }

  public long getSendingDate()
  {
    return sendingDate;
  }

  public long userFrom()
  {
    return sourceID;
  }

  public long userTo()
  {
    return destinationID;
  }
  
} // end class Message
