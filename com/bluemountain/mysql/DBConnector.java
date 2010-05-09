
package com.bluemountain.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import com.mysql.jdbc.*;


/**
 * 连接数据库的方法
 */
public class DBConnector{

  //private Connection con;

  /**
   * 获得数据库连接
   * 
   * @param driverClassName
   *          连接数据库用到的驱动类的类名
   * @param dbURL
   *          数据库的URL
   * @param userName
   *          登陆数据库的用户名
   * @param password
   *          登陆数据库的密码
   * @return
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public static 
  Connection getConnection(String driverClassName,String dbURL,
      String userName,String password) throws ClassNotFoundException,
      SQLException{
    Connection con=null;

    // 加载连接数据库的驱动类
    Class.forName(driverClassName);
    // 用用户名、密码连接数据库
    con=DriverManager.getConnection(dbURL,userName,password);

    return con;
  }

  /**
   * 获得MySQL数据库的连接
   * 
   * @param dricerClassName
   *          连接数据库用到的驱动类的类名
   * @param serverHost
   *          数据库所在服务器的IP或域名
   * @param serverPort
   *          数据库所在服务器的端口
   * @param dbName
   *          数据库名
   * @param userName
   *          登陆数据库的用户名
   * @param password
   *          登陆数据库的密码
   * @return 一个连接
   * @throws ClassNotFoundException
   *           数据库驱动类无法找到是抛出该异常
   * @throws SQLException
   *           创建连接时可能抛出该异常
   */
  public static 
  Connection getMySQLConnection(String serverHost,String serverPort,
      String dbName,String userName,String password)
      throws ClassNotFoundException,SQLException{
    // 如果没有提供这些连接参数，则用默认值
    String dricerClassName="com.mysql.jdbc.Driver";

    if(serverHost==null){
      serverHost="0.0.0.0";
    }
    if(serverPort==null){
      serverPort="3306";
    }
    // 构建访问SQL Server数据库的URL
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
//    // 获得本地MySQL的连接实例，使用MySQL需要去www.mysql.com下载最新的MySQL安装程序和Java驱动
//    // MySQL有多个连接MySQL的驱动类，如org.gjt.mm.mysql.Driver。
//    // 这里使用MySQL官方网站上提供的驱动类
//    String mySQLDirver="com.mysql.jdbc.Driver";
//    String dbName="java";
//    String userName="java";
//    String password="java";
//    Connection con=
//        DBConnector.getMySQLConnection(mySQLDirver,null,null,dbName,userName,
//            password);
//    System.out.println("连接MySQL数据库成功！");
//    con.close();
//    System.out.println("成功关闭与MySQL数据库的连接！");
//    String url="jdbc:mysql://59.78.15.9:3306/"+dbName;
//    con=DBConnector.getConnection(mySQLDirver,url,userName,password);
//    System.out.println("连接MySQL数据库成功！");
//    con.close();
//
//    System.out.println("成功关闭与MySQL数据库的连接！");
//
//  }
}
