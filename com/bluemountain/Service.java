package com.bluemountain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 服务的接口定义。服务器上所有服务都必须实现该接口。
 * 由于服务器使用了反射机制通过服务类的无参数构造方法创建服务的实例，
 * 所以所有的服务实现类都必须提供一个无参数的构造方法。
 */
public interface Service {
  /**
   * 服务方法
   * @param in  客户端的输入流
   * @param out 客户端的输出流
   * @throws IOException
   */
  public void serve(InputStream in, OutputStream out) throws IOException;
}