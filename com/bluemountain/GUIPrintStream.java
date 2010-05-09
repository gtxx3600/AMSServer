package com.bluemountain;

import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.SwingUtilities;
import javax.swing.text.JTextComponent;

/** 
 * ������ı����������
 * 

 */
public class GUIPrintStream extends PrintStream{
    
 
    private JTextComponent component;
    private StringBuffer sb = new StringBuffer();
    
    public GUIPrintStream(OutputStream out, JTextComponent component){
        super(out);
        this.component = component;
    }
    
    /** 
     * ��дwrite()�������������Ϣ��䵽GUI�����
     * @param buf
     * @param off
     * @param len
     */
    @Override
    public void write(byte[] buf, int off, int len) {
        final String message = new String(buf, off, len); 

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                sb.append(message);
                component.setText(sb.toString());
            }
        });
    }
}