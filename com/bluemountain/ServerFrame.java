package com.bluemountain;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.WindowConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.SwingUtilities;

import com.bluemountain.AMSServer.Control;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ServerFrame extends javax.swing.JFrame {
  public static final String HELP_MESSAGE = "Usage: java book.net.GeneralServer "
    + "[-control <password> <port>] "
    + "[<servicename> <port> ... ]";
  public static final String CMD_MESSAGE = "-control pass 6666 " +
  		"com.bluemountain.MessageServer 8100 " +
  		"com.bluemountain.UserLogin 8000 " +
  		"com.bluemountain.FileServer 8200 " +
  		"com.bluemountain.TableServer 8300 " +
  		"com.bluemountain.DiscussServer 8400 " +
  		"com.bluemountain.RegisterServer 9000";
  private JPanel jPanel1;
  private JScrollPane jScrollPane1;
  private JButton jButton1;
  private JLabel jLabel1;
  private JButton jButton3;
  private JButton jButton2;
  private JTextArea jTextArea1;
  private AMSServer generalServer;
  /**
  * Auto-generated main method to display this JFrame
  */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        ServerFrame inst = new ServerFrame();
        inst.setLocationRelativeTo(null);
        inst.setVisible(true);
      }
    });
  }
  
  public ServerFrame() {
    super();
    initGUI();
    GUIPrintStream g=new GUIPrintStream(System.out, jTextArea1);
    System.setOut(g);
    System.setErr(g);
  }
  
  private void initGUI() {
    try {
      setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      getContentPane().setLayout(null);
      {
        jPanel1 = new JPanel();
        getContentPane().add(jPanel1);
        jPanel1.setLayout(null);
        jPanel1.setBounds(0, 0, 774, 458);
        {
          jScrollPane1 = new JScrollPane();
          jPanel1.add(jScrollPane1);
          jScrollPane1.setBounds(25, 45, 594, 392);
          {
            jTextArea1 = new JTextArea();
            jTextArea1.setEditable(false);
            jScrollPane1.setViewportView(jTextArea1);
            jTextArea1.setBounds(25, 45, 594, 392);
            jTextArea1.setText(" ");
          }
        }
        {
          jButton1 = new JButton();
          jPanel1.add(jButton1);
          jButton1.setText("Exit");
          jButton1.setBounds(671, 397, 84, 33);
          jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
             
             System.exit(0);
            }
          });
        }
        {
          jButton2 = new JButton();
          jPanel1.add(jButton2);
          jButton2.setText("Control");
          jButton2.setBounds(662, 45, 85, 34);
        }
        {
          jButton3 = new JButton();
          jPanel1.add(jButton3);
          jButton3.setText("Start");
          jButton3.setBounds(662, 100, 85, 31);
          jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
    
              if(generalServer==null)
              {String[] args=CMD_MESSAGE.split("\\s{1,}");
                try {
                  // 参数数目必须大于等于2。
                  if (args.length < 2) // Check number of arguments
                    throw new IllegalArgumentException("Must specify a service");
                 
                  // 本例使用标准的输出流当作日志信息输出流，同时连接数最大为10
                  AMSServer server = new AMSServer(System.out, 4000);

                  // 解析参数
                  int i = 0;
                  while (i < args.length) {
                    // 处理-control参数
                    if (args[i].equals("-control")) {
                      i++;
                      // 获取控制的密码
                      String password = args[i++];
                      // 获取控制的端口
                      int port = Integer.parseInt(args[i++]);
                      // 加载控制服务实例，在端口上工作。
                      server.addService(new Control(server, password), port);
                    } else {
                      // 处理初始启动的服务参数，并动态加载服务实例
                      // 获取服务的类名
                      String serviceName = args[i++];
                      // 根据服务类名生成实例
                      Class serviceClass = Class.forName(serviceName);
                      Service service = (Service) serviceClass.newInstance();
                      // 获取端口
                      int port = Integer.parseInt(args[i++]);
                      // 启动服务
                      server.addService(service, port);
                    }
                  }
                } catch (Exception e) {
                  // 参数错误
                  System.err.println("Server: " + e);
                  System.err.println(HELP_MESSAGE);
                  System.exit(1);
                }
              }

            }
          });
        }
        {
          jLabel1 = new JLabel();
          jPanel1.add(jLabel1);
          jLabel1.setText("jLabel1");
          jLabel1.setBounds(674, 182, 41, 17);
          jLabel1.addMouseListener(new MouseAdapter() {
            
          });
        }
      }
      this.pack();
      this.setSize(790, 494);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
