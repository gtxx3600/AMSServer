
package com.bluemountain;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.bluemountain.mysql.*;
import com.bluemountain.datatype.*;

public class RegisterServer implements Service
{

  public void serve(InputStream in,OutputStream out) throws IOException
  {

    ObjectOutputStream output=new ObjectOutputStream((out));
    ObjectInputStream input=new ObjectInputStream(new BufferedInputStream(in));
    long userID=0;

    output.writeUTF("Welcome");
    output.flush();
    DBOperator dbo=null;
    try
    {
      User tmpu=(User)input.readObject();
      
      String pass=input.readUTF();
      
      
      try
      {
        dbo=new DBOperator();

      }
      catch(Exception e)
      {
        e.printStackTrace();

      }

      if(!(dbo==null))
      {
        try{
          userID=dbo.addNewUser(tmpu.getUserName(),pass,tmpu.isType());
        }catch(Exception e)
        {
          e.printStackTrace();
        }

      }
      
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
    output.writeLong(userID);
    output.flush();
    output.close();
    input.close();

  }

}
