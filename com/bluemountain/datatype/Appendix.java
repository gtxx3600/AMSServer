/**
 * 
 */
package com.bluemountain.datatype;

import java.io.Serializable;

public class Appendix implements Serializable
{
  private static final long serialVersionUID = 2079351938688339876L;
  
  private long fileNum;
  private String fileName;
  private long fileLength;
  private boolean type;
  private long uploaderID;
  private long uploaderTime;
  
  public Appendix(long fileNum, String fileName, long fileLength, boolean type,
      long uploaderID, long uploaderTime)
  {
    super();
    this.fileNum = fileNum;
    this.fileName = fileName;
    this.fileLength = fileLength;
    this.type = type;
    this.uploaderID = uploaderID;
    this.uploaderTime = uploaderTime;
  }
  /**
   * @return the fileNum
   */
  public long getFileNum()
  {
    return fileNum;
  }
  /**
   * @param fileNum the fileNum to set
   */
  public void setFileNum(long fileNum)
  {
    this.fileNum = fileNum;
  }
  /**
   * @return the fileName
   */
  public String getFileName()
  {
    return fileName;
  }
  /**
   * @param fileName the fileName to set
   */
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }
  /**
   * @return the fileLength
   */
  public long getFileLength()
  {
    return fileLength;
  }
  /**
   * @param fileLength the fileLength to set
   */
  public void setFileLength(long fileLength)
  {
    this.fileLength = fileLength;
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
   * @return the uploaderID
   */
  public long getUploaderID()
  {
    return uploaderID;
  }
  /**
   * @param uploaderID the uploaderID to set
   */
  public void setUploaderID(long uploaderID)
  {
    this.uploaderID = uploaderID;
  }
  /**
   * @return the uploaderTime
   */
  public long getUploaderTime()
  {
    return uploaderTime;
  }
  /**
   * @param uploaderTime the uploaderTime to set
   */
  public void setUploaderTime(long uploaderTime)
  {
    this.uploaderTime = uploaderTime;
  }
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "Appendix [fileLength=" + fileLength + ", fileName=" + fileName
        + ", fileNum=" + fileNum + ", type=" + type + ", uploaderID="
        + uploaderID + ", uploaderTime=" + uploaderTime + "]";
  }
}
