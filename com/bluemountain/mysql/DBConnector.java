
package com.bluemountain.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import com.mysql.jdbc.*;


/**
 * �������ݿ�ķ���
 */
public class DBConnector{

  //private Connection con;

  /**
   * ������ݿ�����
   * 
   * @param driverClassName
   *          �������ݿ��õ��������������
   * @param dbURL
   *          ���ݿ��URL
   * @param userName
   *          ��½���ݿ���û���
   * @param password
   *          ��½���ݿ������
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static 
  Connection getConnection(String driverClassName,String dbURL,
      String userName,String password) throws ClassNotFoundException,
      SQLException{
    Connection con=null;

    // �����������ݿ��������
    Class.forName(driverClassName);
    // ���û����������������ݿ�
    con=DriverManager.getConnection(dbURL,userName,password);

    return con;
  }

  /**
   * ���MySQL���ݿ������
   * 
   * @param dricerClassName
   *          �������ݿ��õ��������������
   * @param serverHost
   *          ���ݿ����ڷ�������IP������
   * @param serverPort
   *          ���ݿ����ڷ������Ķ˿�
   * @param dbName
   *          ���ݿ���
   * @param userName
   *          ��½���ݿ���û���
   * @param password
   *          ��½���ݿ������
   * @return һ������
   * @throws ClassNotFoundException
   *           ���ݿ��������޷��ҵ����׳����쳣
   * @throws SQLException
   *           ��������ʱ�����׳����쳣
   */
  public static 
  Connection getMySQLConnection(String serverHost,String serverPort,
      String dbName,String userName,String password)
      throws ClassNotFoundException,SQLException{
    // ���û���ṩ��Щ���Ӳ���������Ĭ��ֵ
    String dricerClassName="com.mysql.jdbc.Driver";

    if(serverHost==null){
      serverHost="0.0.0.0";
    }
    if(serverPort==null){
      serverPort="3306";
    }
    // ��������SQL Server���ݿ��URL
    String dbURL="jdbc:mysql://"+serverHost+":"+serverPort+"/"+dbName+"?useUnicode=true&characterEncoding=gb2312";
    return getConnection(dricerClassName,dbURL,userName,password);
  }

//  public 
//  DBConnector(String serverHost,String serverPort,String dbName,
//      String userName,String password) throws ClassNotFoundException,
//      SQLException{
//    con=this.getMySQLConnection(serverHost,serverPort,dbName,userName,password);
  //}

//  public static void main(String[] args) throws ClassNotFoundException,
//      SQLException{
//    // ��ñ���MySQL������ʵ����ʹ��MySQL��Ҫȥwww.mysql.com�������µ�MySQL��װ�����Java����
//    // MySQL�ж������MySQL�������࣬��org.gjt.mm.mysql.Driver��
//    // ����ʹ��MySQL�ٷ���վ���ṩ��������
//    String mySQLDirver="com.mysql.jdbc.Driver";
//    String dbName="java";
//    String userName="java";
//    String password="java";
//    Connection con=
//        DBConnector.getMySQLConnection(mySQLDirver,null,null,dbName,userName,
//            password);
//    System.out.println("����MySQL���ݿ�ɹ���");
//    con.close();
//    System.out.println("�ɹ��ر���MySQL���ݿ�����ӣ�");
//    String url="jdbc:mysql://59.78.15.9:3306/"+dbName;
//    con=DBConnector.getConnection(mySQLDirver,url,userName,password);
//    System.out.println("����MySQL���ݿ�ɹ���");
//    con.close();
//
//    System.out.println("�ɹ��ر���MySQL���ݿ�����ӣ�");
//
//  }
}
