package com.bluemountain;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * ����Ľӿڶ��塣�����������з��񶼱���ʵ�ָýӿڡ�
 * ���ڷ�����ʹ���˷������ͨ����������޲������췽�����������ʵ����
 * �������еķ���ʵ���඼�����ṩһ���޲����Ĺ��췽����
 */
public interface Service {
  /**
   * ���񷽷�
   * @param in  �ͻ��˵�������
   * @param out �ͻ��˵������
   * @throws IOException
   */
  public void serve(InputStream in, OutputStream out) throws IOException;
}